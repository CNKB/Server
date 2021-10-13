package lkd.namsic.cnkb;

import lkd.namsic.cnkb.bearer.BearerAuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
@Slf4j
@Configuration
public class WebConfig {

    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebConfig(@NonNull BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowCredentials(false);
            }
        };
    }

    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Interceptor registering");
        registry.addInterceptor(bearerAuthInterceptor)
                .addPathPatterns("/t");
    }

}
