# scala-opentsdb（仅作为个人学习记录使用）

 	目前工作需要使用flink接kafka数据写入opentsdb中，且使用的是scala。在网上找了好多都没有找到相关scala的jar包，但是幸运的是找到了java版的写opentsdb的程序：[opentsdb-client](https://github.com/shifeng258/opentsdb-client)。在这个基础上对其进行修改成为所需要的scala版本：[opentsdb-scala-client](https://github.com/LihaoLixue/scala-opentsdb/tree/master)。经过测试是成功的。

​	由于本人能力有限，此处仅测试了写入功能，并且程序的健壮性还有待测试。如果有看到该项目的请勿喷，并请留下各位的宝贵意见，以督促我进一步提高自己的能力从而完善该项目。

[一些参数设置可以参考opentsdb官网说明](http://opentsdb.net/docs/build/html/user_guide/query/filters.html)





## 参考：

[1. 基于scala的OpenTSDB的查询（参考JAVA版的OpenTSDB API）](https://blog.csdn.net/qq_24084925/article/details/80366757)

[2. OpenTSDB的读写API](https://my.oschina.net/HuQingmiao/blog/701145)


