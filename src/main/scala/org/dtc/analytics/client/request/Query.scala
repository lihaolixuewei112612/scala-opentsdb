package org.dtc.analytics.client.request

import java.util
import java.util.{ArrayList, List}

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class Query {
  private var start = 0L
  private var end = 0L
  private var queries:util.List[SubQueries] = new util.ArrayList[SubQueries]
  private var noAnnotations = false
  private var globalAnnotations = false
  private var msResolution = false
  private var showTSUIDs = false
  private var showSummary = false
  private var showQuery = false
  private var delete = false


  def this(start: Long) {
    this()
    this.start = start
  }

  def addSubQuery(subQueries: SubQueries): Query = {
    this.queries.add(subQueries)
    this
  }

  def addStart(start: Long): Query = {
    this.start = start
    this
  }

  def addEnd(end: Long): Query = {
    this.end = end
    this
  }

  def getStart: Long = start

  def setStart(start: Long): Unit = {
    this.start = start
  }

  def getEnd: Long = end

  def setEnd(end: Long): Unit = {
    this.end = end
  }

  def getQueries: util.List[SubQueries] = queries

  def setQueries(queries: util.List[SubQueries]): Unit = {
    this.queries = queries
  }

  def getNoAnnotations: Boolean = noAnnotations

  def setNoAnnotations(noAnnotations: Boolean): Unit = {
    this.noAnnotations = noAnnotations
  }

  def getGlobalAnnotations: Boolean = globalAnnotations

  def setGlobalAnnotations(globalAnnotations: Boolean): Unit = {
    this.globalAnnotations = globalAnnotations
  }

  def getMsResolution: Boolean = msResolution

  def setMsResolution(msResolution: Boolean): Unit = {
    this.msResolution = msResolution
  }

  def getShowTSUIDs: Boolean = showTSUIDs

  def setShowTSUIDs(showTSUIDs: Boolean): Unit = {
    this.showTSUIDs = showTSUIDs
  }

  def getShowSummary: Boolean = showSummary

  def setShowSummary(showSummary: Boolean): Unit = {
    this.showSummary = showSummary
  }

  def getShowQuery: Boolean = showQuery

  def setShowQuery(showQuery: Boolean): Unit = {
    this.showQuery = showQuery
  }

  def getDelete: Boolean = delete

  def setDelete(delete: Boolean): Unit = {
    this.delete = delete
  }

}
