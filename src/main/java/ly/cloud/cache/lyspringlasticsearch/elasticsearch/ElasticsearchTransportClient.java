package ly.cloud.cache.lyspringlasticsearch.elasticsearch;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TransportClient操作ES
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 16:08
 *
 * @author linweijian
 */
@Component
public class ElasticsearchTransportClient implements Elasticsearch {
    private final TransportClient client;
    public ElasticsearchTransportClient(TransportClient transportClient) {
        this.client = transportClient;
    }


    @Override
    public boolean isIndexExist(String indexName) {
        return client.admin().indices().prepareExists(indexName).execute().actionGet().isExists();
    }

    @Override
    public boolean deleteIndex(String indexName) {
        return isIndexExist(indexName) && client.admin().indices().prepareDelete(indexName).execute().actionGet().isAcknowledged();
    }

    @Override
    public <T> boolean addIndexAndType(String indexName, String type, T t) throws IOException {
        // 创建索引映射,相当于创建数据库中的表操作
        Map<String, String> params = getParams(t.getClass());
        CreateIndexRequestBuilder cib = client.admin().indices().prepareCreate(indexName);
        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject("properties")
                .startObject("goodsName").field("type", "string").endObject()
                .startObject("goodsPrice").field("type", "double").endObject()
                .startObject("goodsUser").field("type", "string").endObject()
                .startObject("goodsTime").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                .endObject().endObject();
        cib.addMapping(type, mapping);
        return cib.execute().actionGet().isAcknowledged();
    }

    @Override
    public <T> long add(String indexName, String type, T t) throws IOException {

        if (!isIndexExist(indexName)) {

        }

        // 自定义主键id,这个id也可以不要,让es自动为我们生成id
        String id = UUID.randomUUID().toString().replace("-", "");
        // 创建文档,相当于往表里面insert一行数据
        IndexResponse response = client.prepareIndex(indexName, type, id)
                .setSource(XContentFactory.jsonBuilder().startObject()// 开始
                        .field("goodsName", "大学英语")// 商品名称
                        .field("goodsPrice", 22.33)// 商品价格
                        .field("goodsUser", "大拿")// 商品主人
                        .field("goodsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))// 商品上架时间
                        .endObject())
                .get();
        return response.getVersion();
    }

    /**
     * 通过反射获取字段名称
     *
     * @param clazz
     * @return java.util.Map<java.lang.String       ,       java.lang.String>
     * @author linweijian
     * @date 2018/8/27 17:35
     **/
    private Map<String, String> getParams(Class<?> clazz) {
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            map.put(f.getName(), f.getGenericType().getTypeName());
        }
        return map;
    }
}
