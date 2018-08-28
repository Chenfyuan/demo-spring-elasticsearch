# [ElasticSearch](https://www.elastic.co/cn/)

### 一、ElasticSearch是什么

> ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。
> 我们建立一个网站或应用程序，并要添加搜索功能，但是想要完成搜索工作的创建是非常困难的。我们希望搜索解决方案要运行速度快，我们希望能有一个零配置和一个完全免费的搜索模式，我们希望能够简单地使用JSON通过HTTP来索引数据，我们希望我们的搜索服务器始终可用，我们希望能够从一台开始并扩展到数百台，我们要实时搜索，我们要简单的多租户，我们希望建立一个云的解决方案。因此我们利用Elasticsearch来解决所有这些问题及可能出现的更多其它问题。

### 二、ElasticSearch能做什么







### 三、概念

 #### cluster
代表一个集群，集群中有多个节点，其中有一个为主节点，这个主节点是可以通过选举产生的，主从节点是对于集群内部来说的。es的一个概念就是去中心化，字面上理解就是无中心节点，这是对于集群外部来说的，因为从外部来看es集群，在逻辑上是个整体，你与任何一个节点的通信和与整个es集群通信是等价的。
#### shards
代表索引分片，es可以把一个完整的索引分成多个分片，这样的好处是可以把一个大的索引拆分成多个，分布到不同的节点上。构成分布式搜索。分片的数量只能在索引创建前指定，并且索引创建后不能更改。
#### replicas
代表索引副本，es可以设置多个索引的副本，副本的作用一是提高系统的容错性，当某个节点某个分片损坏或丢失时可以从副本中恢复。二是提高es的查询效率，es会自动对搜索请求进行负载均衡。
#### recovery
代表数据恢复或叫数据重新分布，es在有节点加入或退出时会根据机器的负载对索引分片进行重新分配，挂掉的节点重新启动时也会进行数据恢复。
#### river
代表es的一个数据源，也是其它存储方式（如：数据库）同步数据到es的一个方法。它是以插件方式存在的一个es服务，通过读取river中的数据并把它索引到es中，官方的river有couchDB的，RabbitMQ的，Twitter的，Wikipedia的。
#### gateway
代表es索引快照的存储方式，es默认是先把索引存放到内存中，当内存满了时再持久化到本地硬盘。gateway对索引快照进行存储，当这个es集群关闭再重新启动时就会从gateway中读取索引备份数据。es支持多种类型的gateway，有本地文件系统（默认），分布式文件系统，Hadoop的HDFS和amazon的s3云存储服务。
#### discovery.zen
代表es的自动发现节点机制，es是一个基于p2p的系统，它先通过广播寻找存在的节点，再通过多播协议来进行节点之间的通信，同时也支持点对点的交互。
#### Transport
代表es内部节点或集群与客户端的交互方式，默认内部是使用tcp协议进行交互，同时它支持http协议（json格式）、thrift、servlet、memcached、zeroMQ等的传输协议（通过插件方式集成）。

#### Index

Elastic 会索引所有字段，经过处理后写入一个反向索引（Inverted Index）。查找数据的时候，直接查找该索引。

所以，Elastic 数据管理的顶层单位就叫做 Index（索引）。它是单个数据库的同义词。每个 Index （即数据库）的名字必须是小写。

###  Document

Index 里面单条的记录称为 Document（文档），类似数据库中的一条数据。许多条 Document 构成了一个 Index。

Document 使用 JSON 格式表示，下面是一个例子。

```json
{
  "user": "张三",
  "title": "工程师",
  "desc": "数据库管理"
}
```

同一个 Index 里面的 Document，不要求有相同的结构（scheme），但是最好保持相同，这样有利于提高搜索效率

### Type

Document 可以分组，比如weather这个 Index 里面，可以按城市分组（北京和上海），也可以按气候分组（晴天和雨天）。这种分组就叫做 Type，它是虚拟的逻辑分组，用来过滤 Document。

不同的 Type 应该有相似的结构（schema），举例来说，id字段不能在这个组是字符串，在另一个组是数值。这是与关系型数据库的表的一个区别。性质完全不同的数据（比如products和logs）应该存成两个 Index，而不是一个 Index 里面的两个 Type（虽然可以做到）。

根据规划，Elastic 6.x 版只允许每个 Index 包含一个 Type，7.x 版将会彻底移除 Type

#### 类比MYSQL

| elastic           | mysql    |
| ----------------- | -------- |
| index             | database |
| type              | table    |
| document          | row      |
| filed             | column   |
| mapping           | schema   |
| everything        | index    |
| PUT index/type    | insert   |
| DELETE index/type | delete   |
| PUT index/type    | update   |
| GET index/type    | select   |



### 四、solr和Elasticsearch的区别和使用方式

- Solr建立索引时候，搜索效率下降，实时搜索效率不高，es实时搜索效率高。
- Solr利用Zookeeper进行分布式管理，而Elasticsearch自身带有分布式协调管理功能。
- Solr支持更多格式的数据，比如JSON、XML、CSV，而Elasticsearch仅支持json文件格式。
- Solr官方提供的功能更多，而Elasticsearch本身更注重于核心功能，高级功能多有第三方插件提供。
- Solr在传统的搜索应用中表现好于Elasticsearch，但在处理实时搜索应用时效率明显低于Elasticsearch。
- Solr是传统搜索应用的有力解决方案，但Elasticsearch更适用于新兴的实时搜索应用。
- Solr有一个更大、更成熟的用户、开发和贡献者社区。
- Solr支持多种数据格式的索引，比如：JSON、XML、CSV等多种数据格式。
- Solr搜索海量历史数据，速度非常快，毫秒级返回数据。
- es支持分布式，节点对外表现对等，加入节点自动均衡。
- es完全支持Apache Lucene的接近实时的搜索。
- es处理多租户multitenancy不需要特殊配置，而Solr需要更多的高级设置。
- es采用Gateway的概念，使得数据持久化更简单。
- es各节点组成对等的网络结构，某些节点出现故障时会自动分配其他节点代替其进行工作。
- solr一般要部署到web服务器上，比如tomcat，启动tomcat，配置solr和tomcat的关联。
- es一般可以单独启动，然后es和spring整合，调用SpringDataElasticSearch里面提供的方法。

###  五、Elasticsearch的安装

#### 所需工具与开发环境

- jdk1.8及以上
- [Elasticsearch压缩包](https://www.elastic.co/cn/downloads/elasticsearch)
- [Kibana](https://www.elastic.co/cn/downloads/kibana)(可选，图形化管理工具)

#### 安装步骤

解压Elasticsearch，运行`bin/elasticsearch` (或者 `bin\elasticsearch.bat` on Windows)

运行 `curl http://localhost:9200/` or `Invoke-RestMethod http://localhost:9200` with PowerShell

```json
{
  "name": "PWz79R3",
  "cluster_name": "elasticsearch",
  "cluster_uuid": "ygy_OV3CRC-1q143BfFWdQ",
  "version": {
    "number": "6.4.0",
    "build_flavor": "default",
    "build_type": "zip",
    "build_hash": "595516e",
    "build_date": "2018-08-17T23:18:47.308994Z",
    "build_snapshot": false,
    "lucene_version": "7.4.0",
    "minimum_wire_compatibility_version": "5.6.0",
    "minimum_index_compatibility_version": "5.0.0"
  },
  "tagline": "You Know, for Search"
}
```

上面代码中，请求9200端口，Elastic 返回一个 JSON 对象，包含当前节点、集群、版本等信息，按下 `Ctrl + C`，Elastic 就会停止运行

**[注意]默认情况下，Elastic 只允许本机访问，如果需要远程访问，可以修改 Elastic 安装目录的config/elasticsearch.yml文件，去掉network.host的注释，将它的值改成0.0.0.0，然后重新启动 Elastic。**

(可选)安装图形化管理界面

- 用文本编辑器编辑 `config/kibana.yml`文件，加入` elasticsearch.url =172.0.0.1`
- 运行`bin/kibana` (or `bin\kibana.bat` on Windows)
- 浏览器打开`http://localhost:5601`

#### 安装中文分词器

  ##### 安装

> sudo bin/elasticsearch-plugin install analysis-smartcn

##### 卸载

> sudo bin/elasticsearch-plugin remove analysis-smartcn

安装完成后需要重启elasticsearch

##### 测试(在kibana控制台输入)

> POST /_analyze/ 
>
> {"analyzer":"standard","text":"我是中国人"}  

输入如下

```json
{
  "tokens": [
    {
      "token": "我",
      "start_offset": 0,
      "end_offset": 1,
      "type": "word",
      "position": 0
    },
    {
      "token": "是",
      "start_offset": 1,
      "end_offset": 2,
      "type": "word",
      "position": 1
    },
    {
      "token": "中国",
      "start_offset": 2,
      "end_offset": 4,
      "type": "word",
      "position": 2
    },
    {
      "token": "人",
      "start_offset": 4,
      "end_offset": 5,
      "type": "word",
      "position": 3
    }
  ]
}
```



### 六、Elasticsearch的基本命令

#### REST API

Elasticsearch提供了非常全面和强大的REST API，我们可以通过它去跟集群交互。通过API我们可以完成如下的功能：

- 检查集群，节点和索引的健康状况，状态和统计数据
- 管理集群，节点和索引的数据和原数据
- 执行CRUD(增删改查)操作，依靠索引进行搜索
- 执行高级搜索操作，比如分页，排序，过滤，脚本化，聚集等等

#### 新增



#### 修改

#### 删除

#### 查询

### 七、使用java api操作Elasticsearch

 #### Elasticsearch JAVA操作有以下方式

- TransportClient
- JestClient
- RestClient
- spring-data-elasticsearch (ElasticSearchTemplate or ElasticSearchRepository)

##### TransportClient

maven坐标

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>transport</artifactId>
    <version>6.4.0</version>
</dependency>
```

当结合springboot项目使用时，只需引入以下maven依赖

```xml
 <dependency>
      <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
  </dependency>
```



##### spring-data-elasticsearch



### 八、引用

[全文搜索引擎 Elasticsearch 入门教程](http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html)

[ElasticSearch基础操作及ElasticsearchTemplate API](https://blog.csdn.net/mj86534210/article/details/79910909)

[Spring Data Repositories](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/)

[ElasticsearchTemplate的详细使用](https://blog.csdn.net/tianyaleixiaowu/article/details/77965257)

[TransportClient的使用](https://blog.csdn.net/qq_28988969/article/details/78933709)



