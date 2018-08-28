package demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.dao;

import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.lang.Nullable;


/**
 * jpa方式操作elasticsearch
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 10:48
 *
 * @author linweijian
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {
    /**
     * 根据Id查找
     *
     * @param id
     * @return Article
     * @author linweijian
     * @date 2018/8/27 15:55
     **/
    @Nullable
    Article getById(String id);


}
