import Utilities.RequestApi;
import Utilities.Resolver;
import com.shaft.api.RestActions;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestUpdateBooking {

    private static final String bookEndPoint = System.getProperty("bookEndPoint");
    private static final String authEndPoint = System.getProperty("authEndPoint");


    public String token;
    Resolver resolver = new Resolver();
    JSONFileManager jsonFileManager = new JSONFileManager("src/test/resources/testDataFiles/updateBooking.json");


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
    public void testUpdateBooking() {

        String firstName = jsonFileManager.getTestData("validData.firstname");
        String lastName = jsonFileManager.getTestData("validData.lastname");
        String totalPrice = jsonFileManager.getTestData("validData.totalprice");
        String depositPaid = jsonFileManager.getTestData("validData.depositpaid");
        String additionalNeeds = jsonFileManager.getTestData("validData.additionalneeds");
        String checkIn = jsonFileManager.getTestData("validData.bookingdates.checkin");
        String checkOut = jsonFileManager.getTestData("validData.bookingdates.checkout");

        Response bookIds_response = RequestApi.getRequest(bookEndPoint);

        String bookId = RestActions.getResponseJSONValue(bookIds_response, "[0].bookingid");

        JSONObject updateBooking = resolver.createBookingBody(
                firstName,
                lastName,
                Integer.parseInt(totalPrice),
                Boolean.parseBoolean(depositPaid),
                additionalNeeds,
                checkIn,
                checkOut
        );

        Response update_response = RequestApi.putRequest(bookEndPoint + bookId, updateBooking, token);

        Validations.verifyThat().response(update_response).extractedJsonValue("firstname").isEqualTo(firstName).perform();
        Validations.verifyThat().response(update_response).extractedJsonValue("lastname").isEqualTo(lastName).perform();
        Validations.verifyThat().response(update_response).extractedJsonValue("totalprice").isEqualTo(totalPrice).perform();
        Validations.verifyThat().response(update_response).extractedJsonValue("depositpaid").isEqualTo(depositPaid).perform();
        Validations.verifyThat().response(update_response).extractedJsonValue("additionalneeds").isEqualTo(additionalNeeds).perform();
        Validations.verifyThat().response(update_response).extractedJsonValue("bookingdates.checkin").isEqualTo(checkIn).perform();
        Validations.verifyThat().response(update_response).extractedJsonValue("bookingdates.checkout").isEqualTo(checkOut).perform();
    }

}
