package spring.web.test.resttemplate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-05 23:16
 * @Description:TODO
 */
public class RestTemplateTest {

	private RestTemplate restTemplate;

	@Before
	public void init(){
		restTemplate = new RestTemplate();
	}

	@Test
	public List<UserEntity> getAll2() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:9999/getAll", List.class);
		HttpHeaders headers = responseEntity.getHeaders();
		HttpStatus statusCode = responseEntity.getStatusCode();
		int code = statusCode.value();

		List<UserEntity> list = responseEntity.getBody();

		System.out.println(list.toString());
		return list;


	}
}
