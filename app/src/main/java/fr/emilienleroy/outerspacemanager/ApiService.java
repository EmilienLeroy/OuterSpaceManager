package fr.emilienleroy.outerspacemanager;

import fr.emilienleroy.outerspacemanager.models.ApiBuilding;
import fr.emilienleroy.outerspacemanager.models.ApiShip;
import fr.emilienleroy.outerspacemanager.models.ApiToken;
import fr.emilienleroy.outerspacemanager.models.ApiUser;
import fr.emilienleroy.outerspacemanager.models.RequestCreateShip;
import fr.emilienleroy.outerspacemanager.models.ResponseListBuilding;
import fr.emilienleroy.outerspacemanager.models.ResponseListShip;
import fr.emilienleroy.outerspacemanager.models.ResponseListUsers;
import fr.emilienleroy.outerspacemanager.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @POST("/api/v1/buildings/create/{buildingId}")
    Call<ApiBuilding> createBuilding(@Header("x-access-token") String token, @Path("buildingId") int buildingID);

    @GET("/api/v1/buildings/list")
    Call<ResponseListBuilding> getBuilding(@Header("x-access-token") String token);

    @GET("/api/v1/fleet/list")
    Call<ResponseListShip> getFleet(@Header("x-access-token") String token);

    @GET("/api/v1/ships")
    Call<ResponseListShip> getShips(@Header("x-access-token") String token);

    @GET("/api/v1/ships/{sheepId}")
    Call<ApiShip> getShip(@Header("x-access-token") String token, @Path("sheepId") int sheepID);

    @GET("/api/v1/users/{from}/{limit}")
    Call<ResponseListUsers> getRank(@Header("x-access-token") String token, @Path("from") int from, @Path("limit") int limit);

    @Headers("Content-Type: application/json")
    @POST("/api/v1/ships/create/{sheepId}")
    Call<ApiShip> createShip(@Header("x-access-token") String token, @Path("sheepId") int sheepID,@Body RequestCreateShip amount);
}
