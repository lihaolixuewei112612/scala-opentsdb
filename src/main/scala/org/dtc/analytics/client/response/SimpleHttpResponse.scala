package org.dtc.analytics.client.response

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class SimpleHttpResponse {
  private var statusCode = 0
  private var content = ""

  def isSuccess: Boolean = {
    return statusCode == 200 || statusCode == 204
  }

  def getStatusCode = statusCode

  def setStatusCode(newStatusCode: Int) = {
    this.statusCode = newStatusCode
  }

  def getContent = content

  def setContent(newContent: String): Unit = {
    this.content = newContent
  }

}

