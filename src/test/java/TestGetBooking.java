import Utilities.RequestApi;
import org.testng.annotations.Test;

public class TestGetBooking {

    private static final String bookEndPoint = System.getProperty("bookEndPoint");

    @Test
    public void testGetBook() {

        RequestApi.getRequest(bookEndPoint + "7");
    }

}
