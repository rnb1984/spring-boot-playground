/**
 * 
 */
package bdd.stepdefs.base;

import org.springframework.stereotype.Component;

import io.restassured.response.Response;
import lombok.Data;

/**
 * @author robert.burry
 *
 */
@Data
@Component
public class CucucmberSession {
	private String scenario, feature;
	private Response apiResponse;
}
