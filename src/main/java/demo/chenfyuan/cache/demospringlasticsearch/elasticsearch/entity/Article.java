package demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.entity;

import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.annotation.ESField;
import demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.annotation.ESId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 10:43
 */

@Document(indexName = "book", type = "article", shards = 1, replicas = 0, refreshInterval = "-1")
public class Article implements Serializable {

    @Id
    @ESId
    private String id;


    @Field
    @ESField
    private String categoryId;


    @Field
    @ESField
    private String authorId;

    @Field
    @ESField
    private String name;

    @Field
    @ESField
    private String content;

    @Field
    @ESField
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
