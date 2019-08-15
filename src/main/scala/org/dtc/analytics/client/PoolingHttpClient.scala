package org.dtc.analytics.client

import java.io.IOException
import java.util.concurrent.TimeUnit


import org.apache.commons.lang.StringUtils
import org.apache.http.{HeaderElement, HeaderElementIterator, HttpEntity, HttpResponse}
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.{HttpPost, HttpUriRequest}
import org.apache.http.conn.{ConnectionKeepAliveStrategy, HttpClientConnectionManager}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.message.BasicHeaderElementIterator
import org.apache.http.protocol.{HTTP, HttpContext}
import org.apache.http.util.EntityUtils
import org.dtc.analytics.client.response.SimpleHttpResponse

import util.control.Breaks._

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class PoolingHttpClient {
  val DEFAULT_MAX_TOTAL_CONNECTIONS = 200

  val DEFAULT_MAX_CONNECTIONS_PER_ROUTE = DEFAULT_MAX_TOTAL_CONNECTIONS

  val DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS = 10 * 1000
  val DEFAULT_READ_TIMEOUT_MILLISECONDS = 10 * 1000
  val DEFAULT_WAIT_TIMEOUT_MILLISECONDS = 10 * 1000

  val DEFAULT_KEEP_ALIVE_MILLISECONDS = 5 * 60 * 1000

  val DEFAULT_CHARSET = "UTF-8"

  val DEFAULT_RETRY_COUNT = 2

  var keepAlive = DEFAULT_KEEP_ALIVE_MILLISECONDS

  var maxTotalConnections = DEFAULT_MAX_TOTAL_CONNECTIONS
  var maxConnectionsPerRoute = DEFAULT_MAX_CONNECTIONS_PER_ROUTE

  var connectTimeout = DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS
  var readTimeout = DEFAULT_READ_TIMEOUT_MILLISECONDS
  var waitTimeout = DEFAULT_WAIT_TIMEOUT_MILLISECONDS

  var retries = DEFAULT_RETRY_COUNT
  var connManager = new PoolingHttpClientConnectionManager
  var httpClient: CloseableHttpClient = null
  val keepAliveStrategy = new ConnectionKeepAliveStrategy() {
    override def getKeepAliveDuration(response: HttpResponse, context: HttpContext): Long = {
      val it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE))
      while (it.hasNext) {
        val he = it.nextElement
        val param = he.getName
        val value = he.getValue
        if (value != null && param.equalsIgnoreCase("timeout"))
          return value.toLong * 1000
      }
      keepAlive
    }
  }

    connManager.setMaxTotal(maxTotalConnections)
    // Increase default max connection per route
    connManager.setDefaultMaxPerRoute(maxConnectionsPerRoute)

    // config timeout
    val config: RequestConfig = RequestConfig.custom.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(waitTimeout).setSocketTimeout(readTimeout).build

    httpClient = HttpClients.custom.setKeepAliveStrategy(keepAliveStrategy).setConnectionManager(connManager).setDefaultRequestConfig(config).build

    // detect idle and expired connections and close them
    val staleMonitor = new IdleConnectionMonitorThread(connManager)
    staleMonitor.start()



  @throws[IOException]
  def doPost(url: String, data: String): SimpleHttpResponse = {
    val requestEntity = new StringEntity(data)
    val postMethod = new HttpPost(url)
    postMethod.setEntity(requestEntity)
    val response = execute(postMethod)
    val statusCode = response.getStatusLine.getStatusCode
    val simpleResponse = new SimpleHttpResponse
    simpleResponse.setStatusCode(statusCode)
    val entity = response.getEntity
    if (entity != null) { // should return: application/json; charset=UTF-8
      val ctype = entity.getContentType.getValue
      val charset = getResponseCharset(ctype)
      val content = EntityUtils.toString(entity, charset)
      simpleResponse.setContent(content)
    }
    simpleResponse
  }

  private def getResponseCharset(ctype: String) = {
    import util.control.Breaks._
    var charset = DEFAULT_CHARSET
    if (!StringUtils.isEmpty(ctype)) {
      val params = ctype.split(";")
      breakable {
      for (param <- params) {
        var param_copy = param.trim
        if (param_copy.startsWith("charset")) {
          val pair = param_copy.split("=", 2)
          if (pair.length == 2)
            if (!StringUtils.isEmpty(pair(1)))
              charset = pair(1).trim
          break() //todo: break is not supported
        }
      }
      }
    }
    charset
  }

  @throws[IOException]
  def execute(request: HttpUriRequest): HttpResponse = {
    var response: HttpResponse = null
    var tries = {
      retries += 1
      retries
    }
    import util.control.Breaks._
    breakable {
      while (true) {
        tries -= 1
        try {
          response = httpClient.execute(request)
          break()
        } catch {
          case e: IOException =>
            if (tries < 1) throw e
        }
      }
    }
    response
  }

  @throws[IOException]
  def shutdown(): Unit = {
    httpClient.close()
  }

}

class IdleConnectionMonitorThread extends Thread {
  var connMgr: HttpClientConnectionManager = null
  private var lihao = false

  def this(connMgr: HttpClientConnectionManager) {
    this()
    this.connMgr = connMgr
  }

  override def run(): Unit = {
    try
        while (lihao == false) {
          this synchronized wait(5000)
          connMgr.closeExpiredConnections()
          connMgr.closeIdleConnections(60, TimeUnit.SECONDS)
        }
    catch {
      case ex: InterruptedException =>
        // terminate
        shutdown()

    }
  }

  def shutdown(): Unit = {
    lihao = true
    this.synchronized {
      notifyAll()
    }

  }

}
