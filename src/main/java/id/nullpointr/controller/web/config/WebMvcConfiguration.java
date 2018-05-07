package id.nullpointr.controller.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import id.nullpointr.gauth.InterceptorGAuth;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Override
    public void addInterceptors (InterceptorRegistry registry) {
        // registry.addInterceptor(new InterceptorGAuth());
    }

}
