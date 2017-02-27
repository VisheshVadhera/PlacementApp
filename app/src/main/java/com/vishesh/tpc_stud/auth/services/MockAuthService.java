package com.vishesh.tpc_stud.auth.services;

import com.vishesh.tpc_stud.auth.models.AccessToken;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;

public class MockAuthService implements AuthService {

    private final MockRetrofit mockRetrofit;

    public MockAuthService(MockRetrofit mockRetrofit) {
        this.mockRetrofit = mockRetrofit;
    }

    @Override
    public Single<AccessToken> emailLogin(@Body Map<String, String> map) {

        BehaviorDelegate<AuthService> delegate = mockRetrofit.create(AuthService.class);

        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken("thisIsASampleToken");
        return delegate
                .returningResponse(accessToken)
                .emailLogin(map);
    }
}
