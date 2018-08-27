package demo.chenfyuan.cache.demospringlasticsearch;

import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.dao.ArticleDao;
import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringElasticsearchApplicationTests {

    @Resource
    private ArticleDao articleDao;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testAdd() {
        Article article = new Article();
        article.setId("1");
        article.setContent("你好世界");
        article.setCategoryId("1");
        article.setName("helloworld");
        article.setCreateTime(new Date());
    }

    @Test
    public void testGet() {
        Map<String, String> map = new HashMap<>();
        boolean isExistId = false;
        Class<?> clazz = Article.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            map.put(f.getName(),f.getGenericType().getTypeName());
        }
        System.out.println(map.toString());
    }
}
