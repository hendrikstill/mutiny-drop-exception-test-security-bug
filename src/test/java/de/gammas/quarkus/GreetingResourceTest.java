package de.gammas.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpointWithoutAuthorization() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(401);
    }

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    public void testHelloEndpointWithAuthorization() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }
}
