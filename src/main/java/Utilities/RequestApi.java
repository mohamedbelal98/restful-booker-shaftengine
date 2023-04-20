package Utilities;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RequestApi {

    private static final String Base_Url = System.getProperty("baseUrl");
    static SHAFT.API apiObject = new SHAFT.API(Base_Url);

    public static Response postRequest(String endPoint, Object requestBody) {

        return apiObject.post(endPoint).setContentType(ContentType.JSON).setRequestBody(requestBody).setTargetStatusCode(200).perform();
    }

    public static Response getRequest(String endPoint) {

        return apiObject.get(endPoint).setContentType(ContentType.JSON).setTargetStatusCode(200).perform();
    }

    public static Response deleteRequest(String endPoint, String token) {

        return apiObject.delete(endPoint).setContentType(ContentType.JSON).addHeader("Cookie", "token=" + token).setTargetStatusCode(201).perform();
    }

    public static Response putRequest(String endPoint, Object requestBody, String token) {

        return apiObject.put(endPoint).setContentType(ContentType.JSON).setRequestBody(requestBody).addHeader("Cookie", "token=" + token).setTargetStatusCode(200).perform();
    }

    public static Response patchRequest(String endPoint, Object requestBody, String token) {

        return apiObject.patch(endPoint).setContentType(ContentType.JSON).setRequestBody(requestBody).addHeader("Cookie", "token=" + token).setTargetStatusCode(200).perform();
    }

}
