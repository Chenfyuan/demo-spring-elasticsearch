package demo.chenfyuan.cache.demospringlasticsearch.elasticsearch;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;


/**
 * ElasticsearchTemplate操作ES
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 16:08
 *
 * @author linweijian
 */
@Component
public class ElasticsearchTemplateOperator implements Elasticsearch {
    private ElasticsearchTemplate elasticsearchTemplate;

    public ElasticsearchTemplateOperator(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public boolean isIndexExist(String indexName) {
        return elasticsearchTemplate.indexExists(indexName);
    }

    @Override
    public boolean deleteIndex(String indexName) {
        return elasticsearchTemplate.deleteIndex(indexName);
    }

    @Override
    public <T> boolean addIndexAndType(String indexName, String type, T t) throws IOException {
        return false;
    }

    @Override
    public <T> long add(String indexName, String type, T t) throws IOException {
        return 0;
    }

    @Override
    public <T> List<T> queryForList(String keyword, Class<T> t) {
        String preTag = "<font color=‘#dd4b39‘>";
        String postTag = "</font>";
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(keyword)).withHighlightFields(new HighlightBuilder.Field("name").preTags(preTag).postTags(postTag)
                , new HighlightBuilder.Field("content").preTags(preTag).postTags(postTag)).build();
        return elasticsearchTemplate.queryForList(searchQuery, t);

    }

    @Override
    public <T> List<T> queryForList(Map<String, String> params, Class<T> t) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            queryBuilder.withQuery(matchQuery(entry.getKey(), entry.getValue()));
        }
        return elasticsearchTemplate.queryForList(queryBuilder.build(), t);

    }

    @Override
    public <T> List<T> queryHighlighted(String keyword) {
        return null;
    }

    @Override
    public <T> Page<T> queryForPage(Map<String, String> params, Pageable pageable, Class<T> t) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            queryBuilder.withQuery(matchQuery(entry.getKey(), entry.getValue()));
        }
        queryBuilder.withPageable(pageable);
        queryBuilder.withHighlightFields(new HighlightBuilder.Field("content"));
        SearchQuery searchQuery = queryBuilder.build();
        return elasticsearchTemplate.queryForPage(searchQuery, t);
    }
}
