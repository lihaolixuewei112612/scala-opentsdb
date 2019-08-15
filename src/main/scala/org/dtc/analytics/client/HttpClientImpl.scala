package org.dtc.analytics.client

import com.google.common.base.Preconditions.checkNotNull
import com.google.gson.{Gson, GsonBuilder}
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.dtc.analytics.client.ExpectResponse.ExpectResponse
import org.dtc.analytics.client.builder.MetricBuilder
import org.dtc.analytics.client.request.QueryBuilder
import org.dtc.analytics.client.response.{ErrorDetail, Response, SimpleHttpResponse}

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */

class HttpClientImpl extends HttpClient {
  private val logger = Logger.getLogger(classOf[HttpClientImpl])

  var serviceUrl: String = null

  var mapper: Gson = null

  private val httpClient = new PoolingHttpClient

  def HttpClientImpl(serviceUrl: String): Unit = {
    this.serviceUrl = serviceUrl
    val builder = new GsonBuilder
    mapper = builder.create
  }

  private def buildUrl(serviceUrl: String, postApiEndPoint: String, expectResponse: ExpectResponse) = {
    var url = serviceUrl + postApiEndPoint
    import util.control.Breaks._
    breakable {
      expectResponse match {
        case ExpectResponse.SUMMARY =>
          url += "?summary"
          break() //todo: break is not supported

        case ExpectResponse.DETAIL =>
          url += "?details"
          break() //todo: break is not supported
        case _ =>
          break() //todo: break is not supported
      }
    }
    url
  }

  def getResponse(httpResponse: SimpleHttpResponse): Response = {
    var response: Response = new Response(httpResponse.getStatusCode)
    var content = httpResponse.getContent
    if (StringUtils.isNotEmpty(content)) {
      if (response.isSuccess) {
        val errorDetail = mapper.fromJson(content, classOf[ErrorDetail])
        response.setErrorDetail(errorDetail)
      } else {

      }
      logger.error("request failed!" + httpResponse)

    }
    response
  }

  /**
    * Sends metrics from the builder to the KairosDB server.
    *
    * @param builder
    * metrics builder
    * @return response from the server
    *
    *         problem occurred sending to the server
    */
  override def pushMetrics(builder: MetricBuilder): Response = {
    pushMetrics(builder, ExpectResponse.STATUS_CODE)
  }

  override def pushQueries(builder: QueryBuilder): SimpleHttpResponse = ???

  def pushMetrics(builder: MetricBuilder, exceptResponse: ExpectResponse): Response = {
    checkNotNull(builder)
    // TODO 错误处理，比如IOException或者failed>0，写到队列或者文件后续重试。
    val response: SimpleHttpResponse = httpClient.doPost(buildUrl(serviceUrl, PUT_POST_API, exceptResponse), builder.build)
    getResponse(response)
  }

  def pushQueries(builder: QueryBuilder, exceptResponse: ExpectResponse): SimpleHttpResponse = ???

}


