import Utilities.RequestApi;
import Utilities.Resolver;
import com.shaft.api.RestActions;
import com.shaft.tools.io.JSONFileManager;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDeleteBooking {

    private static final String bookEndPoint = System.getProperty("bookEndPoint");
    private static final String authEndPoint = System.getProperty("authEndPoint");

    public String token;

    Resolver resolver = new Resolver();

    JSONFileManager jsonFileManager = new JSONFileManager("src/test/resources/testDataFiles/delete.json");


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
    public void testDeleteBook() {

        Response book_response = RequestApi.getRequest(bookEndPoint);

        String bookId = RestActions.getResponseJSONValue(book_response, "[0].bookingid");

        RequestApi.deleteRequest(bookEndPoint + bookId, token);

    }
}
