package com.example.ProductByUser;


import com.example.ProductByUser.filter.SoulFilter;
import com.example.ProductByUser.model.UserDTO;

import com.example.ProductByUser.proxy.NeoProxy;
import com.example.ProductByUser.proxy.UserProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class ProductByUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductByUserApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return bean;
	}
	@Bean
	public FilterRegistrationBean filterUrl()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new SoulFilter());

		filterRegistrationBean.addUrlPatterns("/api/soulmate/mongodb/v1/getUser");
		return filterRegistrationBean;
	}
	@Bean
	public UserProxy soulMateProxy() {
		return new UserProxy() {
			@Override
			public ResponseEntity<?> registerUser(UserDTO user) {
				RestTemplate restTemplate = new RestTemplate();
				String url = "http://localhost:5555/api/soulmate/v1/register";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				HttpEntity<UserDTO> request = new HttpEntity<>(user, headers);
				ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, request, Object.class);

				return responseEntity;
			}
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(jsonConverter);

		return restTemplate;
	}

	@Bean
	public NeoProxy neoProxy(RestTemplate restTemplate) {
		return new NeoProxy() {
			@Override
			public ResponseEntity<?> registerUser(UserDTO user) {
				String url = "http://localhost:2222/api/neo/v1/saveUser";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

				HttpEntity<UserDTO> request = new HttpEntity<>(user, headers);
				ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, request, Object.class);

				return responseEntity;
			}
		};
	}

}
