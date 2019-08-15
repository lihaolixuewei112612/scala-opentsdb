# scala-opentsdb
  OpenTSDB提供三种方式的读写操作：telnet、http、post，但官方并没提供JAVA版的API。
  多亏有开源贡献者“shifeng258”，他用java编写了[opentsdb-client](https://github.com/shifeng258/opentsdb-client)，
  才使得我们能对openTSDB的读写操作进行封装，[JAVA版参考资料](https://my.oschina.net/HuQingmiao/blog/701145)
最近由于使用flink写数据到opentsdb，并且使用的scala写的，所以，参考上述的程序修改成为了opentsdb-clinet-scala
[一些参数设置可以参考opentsdb官网说明](http://opentsdb.net/docs/build/html/user_guide/query/filters.html)
