/**
 * 
 */
package bdd.stepdefs;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import bdd.cache.CurrentFormCache;
import bdd.service.ApiService.ApiRequestTypeEnum;
import bdd.stepdefs.base.StepDefinitions;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author robert.burry
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
public class WhenStepDef extends StepDefinitions {

	@Resource
	private CurrentFormCache<? extends Object> formCache;

	@When("a {string} request is sent to the path ending {string} for a {string} Form")
	public void send_request(String restCall, String pathEnd, String formType) throws Throwable {

		Response apiResponse = apiService.callApi(formCache.get(), pathEnd, ApiRequestTypeEnum.valueOf(restCall));
		log.info("\nresponse: " + apiResponse.asString() + "\n\nHeaders:\t" + apiResponse.getHeaders() + "\n\n"
				+ apiResponse.getStatusLine() + "\n");
		session.setApiResponse(apiResponse);
	}
}
