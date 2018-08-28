package demo.chenfyuan.cache.demospringlasticsearch;

import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.dao.ArticleRepository;
import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.entity.Article;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * jpa的操作方式
 *
 * @author linweijian
 * @date 2018/8/28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    Logger logger = LoggerFactory.getLogger(JpaTest.class);
    @Resource
    private ArticleRepository articleRepository;


    /**
     * 初始化数据
     *
     * @param
     * @return void
     * @author linweijian
     * @date 2018/8/28 16:23
     **/
    @Test
    public void initData() {
        String[] title = FakeData.setTitle();
        String[] content = FakeData.setContent();
        for (int i = 0; 0 < title.length; i++) {
            Article article = new Article();
            article.setId("" + i);
            article.setAuthorId("auth" + i);
            article.setName(title[i]);
            article.setContent(content[i]);
            article.setCategoryId("cat" + i);
            article.setCreateTime("2018-08-08");
            articleRepository.save(article);
        }


    }

    @Test
    public void testQueryById() {
        Article article = articleRepository.getById("1");
        logger.info(article.toString());
        Assert.assertNotNull(article);
    }

}
