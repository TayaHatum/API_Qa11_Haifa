import com.google.gson.Gson;
import dto.AuthDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {

    static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();


    @Test
    public void successfulLoginTest() throws IOException {
        AuthDto authDto = AuthDto.builder().email("noa@gmail.com").password("Nnoa12345$").build();

        RequestBody body = RequestBody.create(gson.toJson(authDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(body)
                .build();


        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

    }

    @Test
    public void unsuccessfulLoginTestWrongEmailFormat() throws IOException {
        AuthDto authDto = AuthDto.builder().email("noagmail.com").password("Nnoa12345$").build();

        RequestBody body = RequestBody.create(gson.toJson(authDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(body)
                .build();


        Response response = client.newCall(request).execute();

        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);

    }
    @Test
    public void unsuccessfulLoginTestUnregisteredUser() throws IOException {
        AuthDto authDto = AuthDto.builder().email("mynoa@gmail.com").password("Nnoa12345$").build();

        RequestBody body = RequestBody.create(gson.toJson(authDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(body)
                .build();


        Response response = client.newCall(request).execute();

        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),401);

    }

}
