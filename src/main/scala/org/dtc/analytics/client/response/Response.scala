package org.dtc.analytics.client.response

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class Response {
  private var statusCode = 0
  private var errorDetail:ErrorDetail = null

  def isSuccess: Boolean = statusCode == 200 || statusCode == 204

  def this(statusCode: Int) {
    this()
    this.statusCode = statusCode
  }

  def getStatusCode: Int = statusCode

  def setStatusCode(statusCode: Int): Unit = {
    this.statusCode = statusCode
  }

  def getErrorDetail: ErrorDetail = errorDetail

  def setErrorDetail(errorDetail: ErrorDetail): Unit = {
    this.errorDetail = errorDetail
  }

  override def toString: String = "Response [statusCode=" + statusCode + ", errorDetail=" + errorDetail + "]"

}
