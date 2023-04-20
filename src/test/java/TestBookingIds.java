import Utilities.RequestApi;
import org.testng.annotations.Test;

public class TestBookingIds {

    private static final String bookEndPoint = System.getProperty("bookEndPoint");

    @Test
    public void testGetAllBooking() {

        RequestApi.getRequest(bookEndPoint);
    }

}
