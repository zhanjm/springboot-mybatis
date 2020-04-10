# 映射器的配置元素
映射器<mapper>的属性namespace(命名空间)所对应的是一个接口的全限定名，mybatis上下文是通过它找到对应的接口

## 1. select元素 
查询语句用到该元素，可以自定义参数，返回结果集等

### 1.1 id属性
它和mapper的命名空间组合起来是唯一的，Mybatis上下文通过命名空间和id找到对应的接口的方法，如果命名空间和id结合起来不唯一，Mybatis将抛出异常

### 1.2 parameterType属性
可以给出类的全命名，也可以给出别名，但是别名必须是Mybatis内部定义或者自定义的，可以选择JAVA Bean、Map等简单的参数类型传递给SQL

### 1.3 resultType属性
表示sql的返回值类型，定义类的全路径，也可以使用别名，但是不能resultMap同时使用。

### 1.4 resultMap属性
表示sql返回的自定义结果集，它与ResultMap的id绑定，不能与resultType一起使用。

### 1.5 flushCache属性
取布尔值，默认为false，它的作用是在调用SQL后，是否要求Mybatis清空之前查询本地缓存和二级缓存

### 1.6 useCache属性
启动二级缓存开关，是否要求MyBatis将此次结果缓存取布尔值，默认为true

### 1.7 timeout属性
设置超时参数，超时时将抛出异常，单位为秒

### 1.8 fetchSize属性
获取记录的总条数设定

### 1.9 statementType属性
使用哪个JDBC的statement工作，取值为STATEMENT(statement)、PREPARED(preparedStatement)、CALLABlE（CallableStatement），默认为PREPARED

### 1.10 resultSetType属性



