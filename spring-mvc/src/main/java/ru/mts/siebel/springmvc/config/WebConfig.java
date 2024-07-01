package ru.mts.siebel.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.mts.siebel.springmvc.interceptor.CustomInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //Задание 7: Использование интерцепторов
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor());
    }

}
