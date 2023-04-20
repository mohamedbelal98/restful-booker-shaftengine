import Utilities.RequestApi;
import Utilities.Resolver;
import com.shaft.tools.io.JSONFileManager;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestCreateToken {

    private static final String authEndPoint = System.getProperty("authEndPoint");

    Resolver resolver = new Resolver();
    JSONFileManager jsonFileManager = new JSONFileManager("src/test/resources/testDataFiles/authentication.json");

    @Test
    public void testAuth() {

        JSONObject authBody = resolver.authentication(
                jsonFileManager.getTestData("auth.username"),
                jsonFileManager.getTestData("auth.password")
        );

        RequestApi.postRequest(authEndPoint, authBody);
    }

}
