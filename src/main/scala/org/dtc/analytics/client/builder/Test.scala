package org.dtc.analytics.client.builder

import com.google.gson
import com.google.gson.Gson

import scala.collection.mutable
import scala.collection.mutable._

/**
  * Created on 2019-08-14
  *
  * @author :hao.li
  */
object Test {
  def main(args: Array[String]): Unit = {
    var tags:java.util.Map[String,String] = new java.util.HashMap[String, String]
    tags.put("abc","test")
//    tags+=("abc"->"def")
//    tags+=("def"->"abc")
    val gson = new Gson()
    val str = gson.toJson(new Student("lihao","12",tags))
    println(str)

  }


}
case class Student(name:String,no:String,tags:java.util.Map[String,String])
case class Lihao(age:String,sex:String)
