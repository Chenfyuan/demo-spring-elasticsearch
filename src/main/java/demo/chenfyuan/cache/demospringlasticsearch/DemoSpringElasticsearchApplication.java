package demo.chenfyuan.cache.demospringlasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author linweijian
 */
@SpringBootApplication
@EnableElasticsearchRepositories
public class DemoSpringElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringElasticsearchApplication.class, args);
    }
}
