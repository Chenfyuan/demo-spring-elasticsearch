package demo.chenfyuan.cache.demospringlasticsearch.elasticsearch;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch接口
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 16:19
 *
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

    /**
     * 通过关键字查找列表
     *
     * @param keyword 关键字,默认索引全部字段
     * @param t       泛型
     * @return java.util.List<T>
     * @author linweijian
     * @date 2018/8/28 9:53
     **/
    <T> List<T> queryForList(String keyword, Class<T> t);

    /**
     * 按字段查询
     *
     * @param params 查询的字段map
     * @param t
     * @return java.util.List<T>
     * @author linweijian
     * @date 2018/8/28 10:19
     **/
    <T> List<T> queryForList(Map<String, String> params, Class<T> t);

    /**
     * 高亮字段
     *
     * @param keyword
     * @return java.util.List<T>
     * @author linweijian
     * @date 2018/8/28 16:03
     **/
    <T> List<T> queryHighlighted(String keyword);

    /**
     * 分页
     *
     * @param params
     * @param pageable
     * @param t
     * @return org.springframework.data.domain.Page<T>
     * @author linweijian
     * @date 2018/8/28 16:05
     **/

    <T> Page<T> queryForPage(Map<String, String> params, Pageable pageable, Class<T> t);

}
