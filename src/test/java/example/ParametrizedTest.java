package example;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.DataProvider;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class ParametrizedTest {


    @DataProvider
    public static Object[] [] zipCodesAndPlaces() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    };

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void requestZipCodes_checkPlaceName_exepectSthg(String countryCode, String zipCode, String expectedPlaceName){
        given().
                pathParam("countryCode", countryCode).
                pathParam("zipCode", zipCode).

        when().
                get("http://zippopotam.us/{countryCode}/{zipCode}").
        then().assertThat().body("places[0].'place name'", equalTo(expectedPlaceName));

    }






}

