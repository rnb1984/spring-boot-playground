/**
 * 
 */
package bdd.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.playground.home.api.model.HomeApiEntity;

import bdd.cache.CurrentFormCache;
import bdd.stepdefs.base.StepDefinitions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author robert.burry
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
public class HomeStepDef extends StepDefinitions {

	@Resource
	private CurrentFormCache<HomeApiEntity> homeFrom;

	@Given("a {string} form")
	public void a_form(String formType, Map<String, String> form) {

		Map<String, String> values = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		values.putAll(form);

		homeFrom.put(createHomeApi(values));
	}

	/**
	 * @param values
	 * @return
	 */
	private HomeApiEntity createHomeApi(Map<String, String> values) {
		String name = values.get("name"), id = values.get("id");
		String[] userArray = values.get("users").split(",");
		String[] neighboursArray = values.get("neighbours").split(",");
		List<String> users = Arrays.asList(userArray);
		List<HomeApiEntity> neighbours = createNeighboursApi(name, id, neighboursArray);
		HomeApiEntity home = new HomeApiEntity().name(name).id(id).users(users).neighbours(neighbours);
		return home;
	}

	/**
	 * @param name
	 * @param id
	 * @param userArray
	 * @return
	 */
	private List<HomeApiEntity> createNeighboursApi(String name, String id, String[] userArray) {

		List<HomeApiEntity> neighbours = new ArrayList<>();
		for (int i = 0; i < userArray.length; i++) {
			neighbours.add(new HomeApiEntity().name(name + "_" + i).id(userArray[i]));
		}
		return neighbours;
	}

	@Then("recieve a {string} message with code {string}")
	public void send_request(String status, String statusCode) {

		Response response = session.getApiResponse();
		assertEquals(statusCode, response.getStatusCode());
	}

	@Then("recieve a {string} message with code {string} with values of ")
	public void send_request(String status, String statusCode, Map<String, String> form) {

		Map<String, String> values = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		String id = values.get("id"), name = values.get("name");

		Response response = session.getApiResponse();
		assertEquals(response.getStatusCode(), statusCode);

		HomeApiEntity homeApi = response.as(HomeApiEntity.class);
		assertEquals(id, homeApi.getId());
		assertEquals(name, homeApi.getName());
	}
}
