package br.com.conf;

import javax.servlet.Filter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
	
@Configuration
public class ApiConfiguration {
	
	@Bean
    public FilterRegistrationBean<Filter> registrarFiltroCors() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(new CorsFilter());
        registration.setOrder(0);
        return registration;
    }
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
