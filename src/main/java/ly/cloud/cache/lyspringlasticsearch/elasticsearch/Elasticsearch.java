package ly.cloud.cache.lyspringlasticsearch.elasticsearch;

import java.io.IOException;

/**
 * Elasticsearch接口
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 16:19
 * @author linweijian
 */
public interface Elasticsearch {
    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return boolean
     * @author linweijian
     * @date 2018/8/27 16:20
     **/
    boolean isIndexExist(String indexName);

    /**
     * 删除索引
     *
     * @param indexName
     * @return boolean
     * @author linweijian
     * @date 2018/8/27 16:21
     **/
    boolean deleteIndex(String indexName);

    /**
     * @param indexName
     * @param type
     * @param t
     * @return boolean
     * @author linweijian
     * @date 2018/8/27 16:33
     **/
    <T> boolean addIndexAndType(String indexName, String type, T t) throws IOException;

    /**
     * 添加文档
     *
     * @param indexName
     * @param type
     * @param t
     * @return long
     * @author linweijian
     * @date 2018/8/27 16:39
     **/
    <T> long add(String indexName, String type, T t) throws IOException;

}
