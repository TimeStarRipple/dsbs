package com.whut.dsbs.customer;

import com.whut.dsbs.customer.filter.CorsFilter;
import com.whut.dsbs.customer.filter.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	@Order(Integer.MAX_VALUE - 1)//数字小的先执行
	public FilterRegistrationBean corsFilterRegistrationBean() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new CorsFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	@Order(Integer.MAX_VALUE)
	public FilterRegistrationBean loginFilterRegistrationBean() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new LoginFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}
}
