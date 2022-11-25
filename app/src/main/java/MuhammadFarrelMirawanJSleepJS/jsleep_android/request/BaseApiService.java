package MuhammadFarrelMirawanJSleepJS.jsleep_android.request;



import java.util.List;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Account;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Renter;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount(@Path("id") int id);
    @POST("account/login")
    Call<Account> login(@Query("email") String email,
                        @Query("password") String password);
    @POST("account/register")
    Call<Account> register(@Query("name") String name,
                           @Query("email") String email,
                           @Query("password") String password);
    @POST("account/{id}/registerRenter")
    Call<Renter> registerRenter(@Path("id") int id,
                                @Query("username") String username,
                                @Query("address") String address,
                                @Query("phoneNumber") String phoneNumber);
    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom(@Query("page") int page,
                                @Query("pageSize") int pageSize);
}
