import Utilities.RequestApi;
import Utilities.Resolver;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestCreateBooking {

    private static final String bookEndPoint = System.getProperty("bookEndPoint");
    JSONFileManager jsonFileManager = new JSONFileManager("src/test/resources/testDataFiles/createBooking.json");
    Resolver resolver = new Resolver();

    @Test
    public void testCreateBooking() {

        String firstName = jsonFileManager.getTestData("validData.firstname");
        String lastName = jsonFileManager.getTestData("validData.lastname");
        String totalPrice = jsonFileManager.getTestData("validData.totalprice");
        String depositPaid = jsonFileManager.getTestData("validData.depositpaid");
        String additionalNeeds = jsonFileManager.getTestData("validData.additionalneeds");
        String checkIn = jsonFileManager.getTestData("validData.bookingdates.checkin");
        String checkOut = jsonFileManager.getTestData("validData.bookingdates.checkout");


        JSONObject bookBody = resolver.createBookingBody(
                firstName,
                lastName,
                Integer.parseInt(totalPrice),
                Boolean.parseBoolean(depositPaid),
                additionalNeeds,
                checkIn,
                checkOut
        );

        Response response = RequestApi.postRequest(bookEndPoint, bookBody);

        Validations.verifyThat().response(response).extractedJsonValue("booking.firstname").isEqualTo(firstName).perform();
        Validations.verifyThat().response(response).extractedJsonValue("booking.lastname").isEqualTo(lastName).perform();
        Validations.verifyThat().response(response).extractedJsonValue("booking.totalprice").isEqualTo(totalPrice).perform();
        Validations.verifyThat().response(response).extractedJsonValue("booking.depositpaid").isEqualTo(depositPaid).perform();
        Validations.verifyThat().response(response).extractedJsonValue("booking.additionalneeds").isEqualTo(additionalNeeds).perform();
        Validations.verifyThat().response(response).extractedJsonValue("booking.bookingdates.checkin").isEqualTo(checkIn).perform();
        Validations.verifyThat().response(response).extractedJsonValue("booking.bookingdates.checkout").isEqualTo(checkOut).perform();

    }

}
