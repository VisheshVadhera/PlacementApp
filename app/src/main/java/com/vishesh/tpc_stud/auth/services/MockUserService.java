package com.vishesh.tpc_stud.auth.services;

import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.Course;
import com.vishesh.tpc_stud.dashboard.models.Degree;
import com.vishesh.tpc_stud.dashboard.models.Network;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;

public class MockUserService implements UserService {

    private final MockRetrofit mockRetrofit;
    private final BehaviorDelegate<UserService> delegate;

    private static User mockUser = getMockUser();

    private static User getMockUser() {
        User mockUser = new User();
        mockUser.setFirstName("Abcde");
        mockUser.setLastName("Vwxyz");
        mockUser.setId(1);
        mockUser.setEmail("abcd@example.com");
        mockUser.setPicUrl("http://doubledutch.me/wp-content/uploads/teammembers/placeholder-user-photo.png");
        return mockUser;
    }

    public MockUserService(MockRetrofit mockRetrofit) {
        this.mockRetrofit = mockRetrofit;
        delegate = mockRetrofit.create(UserService.class);
    }


    @Override
    public Single<User> updateUser(@Path("userId") int userId, @Body User user) {
        return delegate
                .returningResponse(mockUser)
                .updateUser(userId, user);
    }

    @Override
    public Single<User> getCurrentUser() {
        return delegate
                .returningResponse(mockUser)
                .getCurrentUser();
    }

    @Override
    public Single<UserProfile> getProfile(@Path("userId") Integer userId) {

        UserProfile mockUserProfile = new UserProfile();
        mockUserProfile.setCourse(getCourse());
        mockUserProfile.setCvUrl(getCvUrl());
        mockUserProfile.setGpa(getGpa());
        mockUserProfile.setNetworkProfiles(getNetworkProfiles());

        return delegate
                .returningResponse(mockUserProfile)
                .getProfile(userId);
    }

    @Override
    public Single<Object> logout() {
        return delegate.returningResponse(new Object())
                .logout();
    }

    @Override
    public Single<List<NetworkProfile>> getNetworkProfiles(int userId) {
        return delegate.returningResponse(getNetworkProfiles(userId))
                .getNetworkProfiles(userId);
    }

    @Override
    public Single<NetworkProfile> saveNetworkProfile(@Path("userId") Integer userId, @Body NetworkProfile networkProfile) {
        return delegate.returningResponse(saveNetworkProfile(userId, networkProfile))
                .saveNetworkProfile(userId, networkProfile);
    }

    private List<NetworkProfile> getNetworkProfiles() {
        List<NetworkProfile> networkProfiles = new ArrayList<>();

        NetworkProfile networkProfile = new NetworkProfile();
        networkProfile.setNetwork(Network.GITHUB);
        networkProfile.setUrl("https://github.com");

        NetworkProfile networkProfile1 = new NetworkProfile();
        networkProfile1.setNetwork(Network.LINKEDIN);
        networkProfile1.setUrl("http://linkedin.com");

        networkProfiles.add(networkProfile);
        networkProfiles.add(networkProfile1);

        return networkProfiles;
    }

    private String getCvUrl() {
        return "http://www.pdf995.com/samples/pdf.pdf";
    }

    private Course getCourse() {
        Course course = new Course();
        course.setBranch("Electrical Engineering");
        course.setDegree(Degree.BACHELORS);

        return course;
    }

    private Double getGpa() {
        return 8.7;
    }
}
