package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateExistContact {


    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";


    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com/";
        RestAssured.basePath = "api";
    }

    @Test
    public void updateExistsContactSuccess(){

        ContactDto contact = ContactDto.builder()
                .name("wwwww")
                .lastName("abed")
                .email("paki1920@mail.ru")
                .phone("1081920")
                .address("pakistan")
                .id(29603)
                .description("paki").build();

        given().header("Authorization",token)
                .body(contact)
                .contentType(ContentType.JSON)
                .when()
                .put("contact")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("name",containsString("wwwww"));

    }
}
