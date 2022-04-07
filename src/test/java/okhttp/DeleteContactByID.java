package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.DeleteByIDResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByID {
    Gson gson = new Gson();
    static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";
    int id ;


    @BeforeMethod
    public void addnewContactForDelete() throws IOException {
        //add new contact
        ContactDto contactDto = ContactDto.builder()
                .name("Sonya")
                .lastName("Snow")
                .email("snow@mail.com")
                .address("Haifa")
                .description("friend")
                .phone("7777777774").build();

        RequestBody body = RequestBody.create(gson.toJson(contactDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .post(body)
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        ContactDto contact = gson.fromJson(response.body().string(),ContactDto.class);
        id=contact.getId();

    }

    @Test
    public void deleteByIDSuccess() throws IOException {
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact/"+id)
                .delete()
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        DeleteByIDResponseDto delete = gson.fromJson(response.body().string(), DeleteByIDResponseDto.class);
        System.out.println(delete.getStatus());
        Assert.assertEquals(delete.getStatus(),"Contact was deleted!");

    }
}
