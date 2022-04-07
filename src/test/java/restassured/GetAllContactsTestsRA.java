package restassured;

import com.jayway.restassured.RestAssured;
import dto.AllContactDto;
import dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GetAllContactsTestsRA {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";

    @BeforeMethod
    public void init(){
        RestAssured.baseURI ="https://contacts-telran.herokuapp.com/";
        RestAssured.basePath="api";
    }
    @Test
    public void getAllContactSuccess(){

         AllContactDto all = given().header("Authorization",token)
                .when()
                .get("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AllContactDto.class);

        List<ContactDto> contacts =  all.getContacts();
        for(ContactDto contact:contacts){
            System.out.println(contact.toString());
            System.out.println("*****");
            System.out.println(contact.getId());
        }

    }


    @Test
    public void getAllContactNegative(){
        //Wrong token format!

        given().header("Authorization","fgiurgh")
                .when()
                .get("contact")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",containsString("Wrong token format!"));
    }
}

