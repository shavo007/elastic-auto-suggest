package com.elastic;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class ElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticApplication.class, args);
	}

	@Bean
	public JestClient jestClient() {

		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
						.scheme("http").host("localhost").port(9200)
						.build();

		final JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig
				.Builder(uriComponents.toUriString())
				.multiThreaded(true)
				.build());
		return factory.getObject();

	}
}
