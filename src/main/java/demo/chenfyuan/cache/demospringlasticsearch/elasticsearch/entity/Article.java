package demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 10:43
 * @author linweijian
 */

@Document(indexName = "book", type = "article", shards = 1, replicas = 0, refreshInterval = "-1")
public class Article implements Serializable, Cloneable {

    @Id
    private String id;
    @Field(  analyzer = "smartcn",searchAnalyzer = "smartcn")
    private String categoryId;
    @Field(analyzer = "smartcn",searchAnalyzer = "smartcn")
    private String authorId;
    @Field(analyzer = "smartcn",searchAnalyzer = "smartcn")
    private String name;
    @Field(analyzer = "smartcn",searchAnalyzer = "smartcn")
    private String content;
    @Field(type = FieldType.Date)
    private String createTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    @Override
    protected Article clone() throws CloneNotSupportedException {
        return (Article) super.clone();

    }
}
