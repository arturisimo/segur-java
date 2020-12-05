package org.apz.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableZuulProxy
@SpringBootApplication
public class ZuulServerApp {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApp.class, args);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin(CorsConfiguration.ALL); //Access-Control-Allow-Origin: *
	    config.addAllowedHeader(CorsConfiguration.ALL);
	    config.addAllowedMethod(CorsConfiguration.ALL);
	    //source.registerCorsConfiguration(CorsConfiguration.ALL, config);
	    source.registerCorsConfiguration("/**", config);
	    FilterRegistrationBean bean = new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
	    return bean;
	}
	
}

