package org.dtc.analytics.client.request

import java.util
import java.util.{ArrayList, HashMap, List, Map}

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class SubQueries {
  private var aggregator:String = null
  private var metric:String = null
  private var rate:Boolean = false
  private var rateOptions:util.Map[String, String] = null
  private var downsample:String = null
  private var tags:util.Map[String, String] = new util.HashMap[String, String]
  private var filters:util.List[Filter] = new util.ArrayList[Filter]

  def addAggregator(aggregator: String): SubQueries = {
    this.aggregator = aggregator
    this
  }

  def addMetric(metric: String): SubQueries = {
    this.metric = metric
    this
  }

  def addDownsample(downsample: String): SubQueries = {
    this.downsample = downsample
    this
  }

  /**
    * Tags are converted to filters in 2.2
    */
  @deprecated def addTag(tag: util.Map[String, String]): SubQueries = {
    this.tags.putAll(tag)
    this
  }

  @deprecated def addTag(tag: String, value: String): SubQueries = {
    this.tags.put(tag, value)
    this
  }

  def addFilter(filter: Filter): SubQueries = {
    this.filters.add(filter)
    this
  }

  def getAggregator: String = aggregator

  def setAggregator(aggregator: String): Unit = {
    this.aggregator = aggregator
  }

  def getMetric: String = metric

  def setMetric(metric: String): Unit = {
    this.metric = metric
  }

  def getRate: Boolean = rate

  def setRate(rate: Boolean): Unit = {
    this.rate = rate
  }

  def getRateOptions: util.Map[String, String] = rateOptions

  def setRateOptions(rateOptions: util.Map[String, String]): Unit = {
    this.rateOptions = rateOptions
  }

  def getDownsample: String = downsample

  def setDownsample(downsample: String): Unit = {
    this.downsample = downsample
  }

  def getTags: util.Map[String, String] = tags

  /**
    * Tags are converted to filters in 2.2
    */
  @deprecated def setTags(tags: util.Map[String, String]): Unit = {
    this.tags = tags
  }

  def getFilters: util.List[Filter] = filters

  def setFilters(filters: util.List[Filter]): Unit = {
    this.filters = filters
  }
}
