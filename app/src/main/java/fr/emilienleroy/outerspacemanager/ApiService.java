package fr.emilienleroy.outerspacemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by eleroy on 16/01/2018.
 */

public interface ApiService {
    @POST("/api/v1/auth/create")
    Call<ApiToken> createUser(@Body User user);

    @POST("/api/v1/auth/login")
    Call<ApiToken> login(@Body User user);

    @GET("/api/v1/users/get")
    Call<ApiUser> currentuser(@Header("x-access-token") String token);
}
