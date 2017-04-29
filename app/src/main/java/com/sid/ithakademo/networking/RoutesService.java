package com.sid.ithakademo.networking;

import com.sid.ithakademo.pojo.lists.Trips;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sid on 29/4/17.
 */

public interface RoutesService {

    @GET("from/{cityA}/to/{cityB}")
    Call<List<Trips>> tripsResponse(
            @Path("cityA") String cityA,
            @Path("cityB") String cityB
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dev.ithaka.travel/transport/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
