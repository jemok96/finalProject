package com.wegoing.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wegoing.TimeFilter;

@Configuration
public class WebConfig {
	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new TimeFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/board","/notice");
		
		return filterRegistrationBean;
		
	}

}
