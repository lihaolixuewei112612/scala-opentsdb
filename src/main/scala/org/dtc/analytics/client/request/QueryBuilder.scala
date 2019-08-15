package org.dtc.analytics.client.request

import java.io.IOException
import com.google.common.base.Preconditions.checkState
import com.google.gson.Gson
import com.google.gson.GsonBuilder
/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class QueryBuilder {
  private val query:Query = new Query
  private var mapper:Gson = null


  def getInstance = new QueryBuilder

  def getQuery: Query = this.query

  @throws[IOException]
  def build: String = { // verify that there is at least one tag for each metric
//    checkState(query.getStart > 0, " must contain start.")
//    checkState(query.getQueries != null, " must contain at least one subQuery.")
    mapper.toJson(query)
  }

}
