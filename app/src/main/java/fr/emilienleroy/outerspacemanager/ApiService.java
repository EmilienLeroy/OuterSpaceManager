package fr.emilienleroy.outerspacemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by eleroy on 16/01/2018.
 */

public interface ApiService {
    @POST("/api/v1/auth/create")
    Call<ApiToken> createUser(@Body User user);
}
