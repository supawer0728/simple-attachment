package com.parfait.study.simpleattachment.config;

import com.parfait.study.simpleattachment.config.interceptor.AttachmentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AttachmentInterceptor attachmentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(attachmentInterceptor).addPathPatterns("/**");
    }
}
