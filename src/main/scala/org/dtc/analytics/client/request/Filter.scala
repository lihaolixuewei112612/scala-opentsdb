package org.dtc.analytics.client.request

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class Filter {
  private var `type`:String = null
  private var tagk:String = null
  private var filter:String = null
  private var groupBy:Boolean = false

  def getType: String = `type`

  def setType(`type`: String): Unit = {
    this.`type` = `type`
  }

  def getTagk: String = tagk

  def setTagk(tagk: String): Unit = {
    this.tagk = tagk
  }

  def getFilter: String = filter

  def setFilter(filter: String): Unit = {
    this.filter = filter
  }

  def getGroupBy: Boolean = groupBy

  def setGroupBy(groupBy: Boolean): Unit = {
    this.groupBy = groupBy
  }

}
