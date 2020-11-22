package example;

import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.Test;




import static io.restassured.RestAssured.given;





public class SampleTest {

    @Test
    public void getUsers_checkStatusCode_expectJson() {
        given().
                log().all().
        when().
                get("https://reqres.in/api/users/2").
        then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON)
                .body(
                        "data.id", equalTo(2),
                        "data.email", equalTo("janet.weaver@reqres.in")
                        );
        ;
    }





}
