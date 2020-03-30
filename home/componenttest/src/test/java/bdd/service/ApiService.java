package bdd.service;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiService {

	@Value("${api.port:8080}")
	private String apiPort;

	/**
	 * enum of all request types
	 *
	 */
	public enum ApiRequestTypeEnum {
		POST, GET, PUT
	}

	/**
	 * @throws Throwable
	 * @throws JsonProcessingException
	 * 
	 */
	public Response callApi(Object form, String pathEnd, ApiRequestTypeEnum reqType) throws Throwable {

		RequestSpecification requestSpecification = given().baseUri("http://localhost:" + apiPort)
				.urlEncodingEnabled(false).header("Content-Type", "application/json");

		log.info("\nRequest:\t" + toJson(form) + "\n");

		switch (reqType) {
		case GET:
			return requestSpecification.get(pathEnd);
		case POST:
			return requestSpecification.body(toJson(form)).post(pathEnd);
		case PUT:
			return requestSpecification.body(toJson(form)).put(pathEnd);
		default:
			fail("reqType not handled: " + reqType);
			return null;
		}
	}

	/**
	 * Convert a Pojo to a Json String
	 * 
	 * @throws Throwable
	 */
	private String toJson(Object form) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.writeValueAsString(form);
	}
}
