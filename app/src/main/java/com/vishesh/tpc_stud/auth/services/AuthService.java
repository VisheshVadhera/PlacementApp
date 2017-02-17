package com.vishesh.tpc_stud.auth.services;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by vishesh on 14/2/17.
 */
public interface AuthService {

    @POST("emailLogin")
    Single<Void> emailLogin(@Body Map<String, String> map);

}
