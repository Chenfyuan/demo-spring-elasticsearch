package demo.chenfyuan.cache.demospringlasticsearch.elasticsearch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   Es注解，标记ID
 * Description: ly-spring-lasticsearch
 * <p>
 * Created by linweijian on 2018/8/27 16:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface ESId {
}
