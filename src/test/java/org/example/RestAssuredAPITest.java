package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Iterator;

public class RestAssuredAPITest {

    @Test
    public void GetBooksDetails() throws ParseException {

        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();
        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, "");
        // Print the status and message body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
//        System.out.println("Response=>" + response.prettyPrint());

        // Get the status code of the request.
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/,
                "Correct status code returned");

        // Get the status line from the Response in a variable called statusLine
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK"
                /*expected value*/, "Correct status code returned");

        Headers headers = response.headers();

        for(Header header : headers) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }

        String serverType = response.header("Server");
        Assert.assertEquals(serverType /* actual value */, "nginx/1.17.10 (Ubuntu)" /* expected value */);

        ResponseBody body = response.getBody();

        String bodyAsString = body.asString();

        System.out.println("----------------------*************************------------------------------");
        System.out.println(bodyAsString);
        System.out.println("----------------------*************************------------------------------");


        JSONParser jsonParser = new JSONParser();
        JSONObject json = (JSONObject) jsonParser.parse(bodyAsString);

        System.out.println(json.get("books"));

        JSONArray jsonArray = new JSONArray();
        Iterator iterator = json.keySet().iterator();

        while (iterator.hasNext()){
            String key = (String) iterator.next();
            jsonArray.add(json.get(key));
        }
        System.out.println(jsonArray.size());
        System.out.println(jsonArray);
//        for (Object book : jsonArray){
//            System.out.println("---------------------------------**************************-------------------");
//            System.out.println(book.toString());
//            System.out.println("---------------------------------**************************-------------------");
//        }
    }
}
