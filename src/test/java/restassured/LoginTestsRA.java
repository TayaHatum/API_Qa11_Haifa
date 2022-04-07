package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthDto;
import dto.ErrorDto;
import dto.ResponseAuthDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

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

    @Test
    public void unsuccessfulLoginTestWrongEmailFormat()  {
        AuthDto authDto = AuthDto.builder().email("noagmail.com").password("Nnoa12345$").build();
        ErrorDto errorDto =
                given()
                        .body(authDto)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("login")
                        .then()
                        .assertThat().statusCode(400)
                        .extract().response().as(ErrorDto.class);
        Assert.assertEquals(errorDto.getMessage(),"Wrong email format! Example: name@mail.com");


    }

    @Test
    public void unsuccessfulLoginTestWrongEmailFormat2()  {


                given()
                        .body(AuthDto.builder().email("noagmail.com").password("Nnoa12345$").build())
                        .contentType(ContentType.JSON)
                        .when()
                        .post("login")
                        .then()
                        .assertThat().statusCode(400)
                        .assertThat().body("message",containsString("Wrong email format!"));


    }

    @Test
    public void unsuccessfulLoginTestUnregisteredUser() {


        given()
                .body(AuthDto.builder().email("mynoa@gmail.com").password("Nnoa12345$").build())
                .contentType(ContentType.JSON)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",containsString("Wrong email or password!"));

    }

    }
