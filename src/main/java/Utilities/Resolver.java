package Utilities;

import org.json.simple.JSONObject;

public class Resolver {


    //Json Object body to use in creating token
    public JSONObject authentication(String userName, String password) {

        JSONObject createToken = new JSONObject();
        createToken.put("username", userName);
        createToken.put("password", password);

        return createToken;
    }

    //Json Object body to use in creating book
    public JSONObject createBookingBody(String firstname, String lastname, int totalprice, boolean depositpaid,
                                        String additionalneeds, String checkin, String checkout) {

        JSONObject createBookingBody = new JSONObject();
        createBookingBody.put("firstname", firstname);
        createBookingBody.put("additionalneeds", additionalneeds);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        createBookingBody.put("bookingdates", bookingDates);
        createBookingBody.put("totalprice", totalprice);
        createBookingBody.put("depositpaid", depositpaid);
        createBookingBody.put("lastname", lastname);

        return createBookingBody;
    }

    //Json Object body to use in partial update Booking
    public JSONObject updateFirstAndLastName(String firstName, String lastName) {

        JSONObject update = new JSONObject();
        update.put("firstname", firstName);
        update.put("lastname", lastName);

        return update;
    }

}
