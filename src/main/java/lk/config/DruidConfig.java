package lk.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 德鲁伊数据库连接池配置类
 *
 * @author likeLove
 * @time 2020-08-08  9:06
 */
@Configuration
public class DruidConfig {

    //返回德鲁伊数据库连接池
    @ConfigurationProperties (prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置druid的监控，
    //管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //处理那些网页的请求
        ServletRegistrationBean bean =
                new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String, String> initParams = new HashMap<>();
        //初始化参数
        //设置用户名和密码
        initParams.put("loginUsername", "root");
        initParams.put("loginPassword", "1");
        initParams.put("allow", "");//允许访问，默认所有
        initParams.put("deny", "192.168.88.88");//拒绝访问
        bean.setInitParameters(initParams);
        return bean;
    }

    //过滤请求的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/**");//排除
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));//拦截所有请求
        return bean;
    }
}
