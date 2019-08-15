package org.dtc.analytics.client.response

import scala.collection.mutable


/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class ErrorDetail {
  private var errors:List[ErrorDetailEntity] = null

  private var success:Integer = null
  private var failed:Integer = null

//  def this(errors: List[ErrorDetailEntity]) {
//    this()
//    this.errors = errors
//  }
//
//  def this(success: Integer, failed: Integer) {
//    this()
//    this.success = success
//    this.failed = failed
//  }
//
//  def this(success: Integer, failed: Integer, errors: List[ErrorDetailEntity]) {
//    this()
//    this.success = success
//    this.failed = failed
//    this.errors = errors
//  }


  def getErrors: List[ErrorDetailEntity] = errors

  def getSuccess: Integer = success

  def setSuccess(success: Integer): Unit = {
    this.success = success
  }

  def getFailed: Integer = failed

  def setFailed(failed: Integer): Unit = {
    this.failed = failed
  }

  class ErrorDetailEntity {
    private var datapoint:DataPoint = null
    private var error:String = null

    def getDatapoint: DataPoint = datapoint

    def setDatapoint(datapoint: DataPoint): Unit = {
      this.datapoint = datapoint
    }

    def getError: String = error

    def setError(error: String): Unit = {
      this.error = error
    }

    override def toString: String = "ErrorDetailEntity [datapoint=" + datapoint + ", error=" + error + "]"
  }

  class DataPoint {
    private var metric:String = null
    private var timestamp:Long = 0L
    private var value:Any = null
    private var tags:mutable.HashMap[String, String] = new mutable.HashMap[String, String]

    def getMetric: String = metric

    def setMetric(metric: String): Unit = {
      this.metric = metric
    }

    def getTimestamp: Long = timestamp

    def setTimestamp(timestamp: Long): Unit = {
      this.timestamp = timestamp
    }

    def getValue: Any = value

    def setValue(value: Any): Unit = {
      this.value = value
    }

    def getTags: mutable.HashMap[String, String] = tags

    def setTags(tags:mutable.HashMap[String, String]): Unit = {
      this.tags = tags
    }
  }

  override def toString: String = "ErrorDetail [" + "success=" + success + ", failed=" + failed + ", errors=" + errors + "]"

}
