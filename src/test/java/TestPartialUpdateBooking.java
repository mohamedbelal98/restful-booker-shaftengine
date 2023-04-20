import Utilities.RequestApi;
import Utilities.Resolver;
import com.shaft.api.RestActions;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPartialUpdateBooking {

    private static final String bookEndPoint = System.getProperty("bookEndPoint");
    private static final String authEndPoint = System.getProperty("authEndPoint");
    public String token;
    Resolver resolver = new Resolver();
    JSONFileManager jsonFileManager = new JSONFileManager("src/test/resources/testDataFiles/partialUpdateBooking.json");

    @BeforeClass
    public void beforeClass() {

        JSONObject authBody = resolver.authentication(
                jsonFileManager.getTestData("auth.username"),
                jsonFileManager.getTestData("auth.password")
        );

        Response auth_response = RequestApi.postRequest(authEndPoint, authBody);

        token = RestActions.getResponseJSONValue(auth_response, "token");
    }

    @Test
    public void testPartialUpdateBook() {

        String firstName = jsonFileManager.getTestData("validData.firstname");
        String lastName = jsonFileManager.getTestData("validData.lastname");

        Response bookIds_response = RequestApi.getRequest(bookEndPoint);

        String bookId = RestActions.getResponseJSONValue(bookIds_response, "[0].bookingid");

        JSONObject updateFirstAndLastName = resolver.updateFirstAndLastName(firstName, lastName);

        Response update_response = RequestApi.patchRequest(bookEndPoint + bookId, updateFirstAndLastName, token);

        Validations.verifyThat().response(update_response).extractedJsonValue("firstname").isEqualTo(firstName).perform();
        Validations.verifyThat().response(update_response).extractedJsonValue("lastname").isEqualTo(lastName).perform();
    }

}
