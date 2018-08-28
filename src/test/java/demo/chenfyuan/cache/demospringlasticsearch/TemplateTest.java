package demo.chenfyuan.cache.demospringlasticsearch;

import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.ElasticsearchTemplateOperator;
import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ElasticsearchTemplate操作
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/28 9:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTest {
    @Resource
    private ElasticsearchTemplateOperator elasticsearchTemplateOperator;

    @Test
    public void queryForList() {
        List<Article> list = elasticsearchTemplateOperator.queryForList("你", Article.class);
        System.out.println(list);

    }
    @Test
    public void queryForListByMap() {
        Map<String,String> map=new HashMap<>();
        map.put("id","1");
        map.put("authorId","auth3");
        List<Article> list = elasticsearchTemplateOperator.queryForList(map, Article.class);
        System.out.println(list);

    }

    @Test
    public void queryForPage() {
        Map<String,String> map=new HashMap<>();
        map.put("content","你");
        Pageable p=PageRequest.of(0,10);
        Page<Article> page = elasticsearchTemplateOperator.queryForPage(map, p, Article.class);
        System.out.println(page.getContent());

    }
}
