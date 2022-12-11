package MuhammadFarrelMirawanJSleepJS.jsleep_android.request;



import java.util.ArrayList;
import java.util.List;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Account;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.BedType;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.City;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Facility;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Payment;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Renter;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * BaseApiService
 *
 * @author Muhammad Farrel Mirawan
 *
 * Used to connect to backend
 */
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
    @POST("room/create")
    Call<Room> createRoom(
            @Query("accountId") int accountId,
            @Query("name") String name,
            @Query("size") int size,
            @Query("price") int price,
            @Query("facility") ArrayList<Facility> facility,
            @Query("city") City city,
            @Query("address") String address,
            @Query("bedType") BedType bedType
            );
    @POST("account/{id}/topUp")
    Call<Boolean> topUp(@Path("id") int id,
                        @Query("balance") double balance);
    @POST("payment/create")
    Call<Payment> createPayment(@Query("buyerId") int buyerId,
                                @Query("renterId") int renterId,
                                @Query("roomId") int roomId,
                                @Query("from") String from,
                                @Query("to") String to);
    @GET("payment/getRenterPayment")
    Call<List<Payment>> getRenterPayment(@Query("renterId") int renterId,
                                        @Query("page") int page,
                                        @Query("pageSize") int pageSize);
    @POST("payment/{id}/accept")
    Call<Boolean> accept(@Path("id") int id);

    @POST("payment/{id}/cancel")
    Call<Boolean> cancel(@Path("id") int id);
}
