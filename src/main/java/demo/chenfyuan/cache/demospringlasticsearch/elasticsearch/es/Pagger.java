package demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.es;

import java.util.List;

/**
 * 分页
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/28 17:21
 */

public class Pagger<T> {
    //页码
    private long pageIndex;
    //一页大小
    private long pageSize;
    private List<T> content;
    private long total;

    public Pagger(long pageIndex, long pageSize, List<T> content, long total) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.content = content;
        this.total = total;
    }

    public Pagger() {

    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
