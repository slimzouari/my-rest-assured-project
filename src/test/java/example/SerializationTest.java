package example;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import example.Serialisation.Location;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;



import static io.restassured.RestAssured.given;

public class SerializationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9876));

    @Test
    public void test_deserialization() {
        Location location =

                given().
                        when().
                        get("http://api.zippopotam.us/us/90210").
                        as(Location.class);

        Assert.assertEquals(
                "Beverly Hills",
                location.getPlaces().get(0).getPlaceName()
        );

    }

    @Test
    public void test_serialiation() {
        Location location = new Location();

        given().
                contentType(ContentType.JSON).
                body(location).
                log().body().
        when().
                post("http://localhost:9876/lv/1050").
                then().assertThat().statusCode(200);
    }
}
