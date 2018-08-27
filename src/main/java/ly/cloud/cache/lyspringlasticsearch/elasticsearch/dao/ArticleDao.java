package ly.cloud.cache.lyspringlasticsearch.elasticsearch.dao;

import ly.cloud.cache.lyspringlasticsearch.elasticsearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * jpa方式操作elasticsearch
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 10:48
 */
@Repository
public interface ArticleDao extends ElasticsearchRepository<Article, String> {
    /**
     * 根据Id查找
     *
     * @param id
     * @return ly.cloud.cache.lyspringlasticsearch.elasticsearch.entity.Article
     * @author linweijian
     * @date 2018/8/27 15:55
     **/
    Article getById(String id);
}
