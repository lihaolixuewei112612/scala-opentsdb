package org.dtc.analytics.client

import java.io.IOException

import org.dtc.analytics.client.ExpectResponse.ExpectResponse
import org.dtc.analytics.client.builder.MetricBuilder
import org.dtc.analytics.client.request.QueryBuilder
import org.dtc.analytics.client.response.{Response, SimpleHttpResponse}


/**
  * Created on 2019-06-17
  *
  * @author :hao.li
  */
trait HttpClient extends Client {
  @throws[IOException]
  def pushMetrics(builder: MetricBuilder, exceptResponse: ExpectResponse): Response

  @throws[IOException]
  def pushQueries(builder: QueryBuilder, exceptResponse: ExpectResponse): SimpleHttpResponse

}
