package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactByIdRA {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";
    int id;
    @BeforeMethod
    public void init(){
        RestAssured.baseURI ="https://contacts-telran.herokuapp.com/";
        RestAssured.basePath="api";

        ContactDto contactDto = ContactDto.builder()
                .name("Sonya")
                .lastName("Snow")
                .email("snow1@mail.com")
                .address("Haifa")
                .description("friend")
                .phone("7777777771").build();

        id = given().header("Authorization",token)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");
    }

    @Test
    public void deleteContactById(){

        String status = given().header("Authorization",token)
                .when()
                .delete("contact/"+id)
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");

        Assert.assertEquals(status,"Contact was deleted!");

    }

    @Test
    public void deleteContactById2(){

       given().header("Authorization",token)
                .when()
                .delete("contact/"+id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("status",containsString("Contact was deleted!"));

    }

    @Test
    public void deleteContactIDNotFound(){

        given().header("Authorization",token)
                .when()
                .delete("contact/12343445")
                .then()
                .assertThat().statusCode(404)
                .assertThat().body("message",containsString("Contact with id: 12343445 not found!"));
    }

    @Test
    public void deleteContactIDWrongFormat(){

        given().header("Authorization",token)
                .when()
                .delete("contact/"+"111111")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",containsString("Contact with id: 12343445 not found!"));
    }
    @Test
    public void deleteContactWrongToken(){

        given().header("Authorization","fgrgrgb")
                .when()
                .delete("contact/"+111)
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",containsString("Wrong token format!"));
    }
}
