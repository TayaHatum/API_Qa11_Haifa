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
    ContactDto contact = ContactDto.builder()
            .name("Fox")
            .lastName("Red")
            .email("red@gmail.com")
            .phone("011111333344")
            .address("Tel Aviv")
            .description("Friend").build();
    int id;

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com/";
        RestAssured.basePath = "api";


    id = given().header("Authorization",token)
                .body(contact)
                .contentType(ContentType.JSON)
                .when()
                .post("contact")
                .then().assertThat().statusCode(200)
                .extract().path("id");
        System.out.println(id);
    }

    @Test
    public void updateExistsContactSuccess(){

       // ContactDto(address=NY, description=Friend, email=miaBio@gmail.com, id=828, lastName=Bio, name=Mia, phone=0527899879)
        contact.setId(id);
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        contact.setName("ww"+i);

        given().header("Authorization",token)
                .body(contact)
                .contentType(ContentType.JSON)
                .when()
                .put("contact")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("name",containsString("ww"+i));

    }
}
