package eu.nerdfactor.springutil.generatedexample.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

	public static HttpHeaders withHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
