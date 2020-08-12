package lk.config;

import lk.component.LoginHandlerInterceptor;
import lk.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web服务器相关的配置类
 * @author likeLove
 * @time 2020-08-04  16:00
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加视图解析器
     *
     * @param registry ViewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //首页映射
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //进入登录后的页面
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 使用 WebMvcConfigurationSupport 而静态文件不显示CSS样式的，这是因为替换之后之前的静态资源文件 会被拦截，导致无法可用。
     * 解决办法：重写 addResourceHandlers（）方法，加入静态文件路径即可
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       /* registry.addResourceHandler("/**")//"/webjars/**"
                .addResourceLocations("classpath:/META-INF/resources/webjars/");*/
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:\\");
    }

    /**
     * 添加自定义国际化内容
     *
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    /**
     * 注册拦截器
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                //拦截的请求
                .addPathPatterns("/**")
                //放行的请求
                .excludePathPatterns("/index.html",  // 排除掉首页请求
                        "/",              // 排除掉首页请求
                        "/user/login",
                        "/asserts/css/*.css",
                        "/asserts/img/*.svg",
                        "/asserts/js/*.js",
                        "/webjars/**",
                        "/mancenter/*",
                        "/error", "/asserts/lib/jquery/*", "/asserts/lib/*.js");

    }
}
