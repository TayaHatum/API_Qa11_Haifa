package restassured;

import com.jayway.restassured.RestAssured;
import dto.AuthDto;
import dto.ResponseAuthDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTestsRA {

    @BeforeMethod
    public void init(){
        RestAssured.baseURI ="https://contacts-telran.herokuapp.com/";
      RestAssured.basePath="api";
    }

    @Test
    public void loginPositiveTest(){
        AuthDto authDto = AuthDto.builder().email("noa@gmail.com").password("Nnoa12345$").build();

        ResponseAuthDto response = given()
                .body(authDto)
                .contentType("application/json")
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseAuthDto.class);

        System.out.println("Token -->" + response.getToken());
    }
}
