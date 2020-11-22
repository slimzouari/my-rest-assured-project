package example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }


    @Test
    public void test_use_spec() {
        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                spec(responseSpec).
                and().assertThat().body("palces[0].'palce name'", equalTo("Beverly Hills"));
    }

    @Test
    public void test_extract_value_from_output() {
        String placeName = given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                spec(responseSpec).
                and().assertThat().body("places[0].'place name'", equalTo("Beverly Hills")).
                extract().
                path("places[0].'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");



    }



}
