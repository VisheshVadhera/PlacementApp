package com.vishesh.tpc_stud.common.services;

import com.vishesh.tpc_stud.common.models.User;
import com.vishesh.tpc_stud.dashboard.models.Course;
import com.vishesh.tpc_stud.dashboard.models.Degree;
import com.vishesh.tpc_stud.networkProfiles.models.Network;
import com.vishesh.tpc_stud.networkProfiles.models.NetworkProfile;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;
import com.vishesh.tpc_stud.semesterGrades.models.SemesterGrade;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;

public class MockUserService implements UserService {

    private final BehaviorDelegate<UserService> delegate;

    private static final User mockUser = getMockUser();

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
        mockUserProfile.setNetworkProfiles(new ArrayList<NetworkProfile>());

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
        return delegate.returningResponse(getStubNetworkProfiles())
                .getNetworkProfiles(userId);
    }

    @Override
    public Single<NetworkProfile> saveNetworkProfile(@Path("userId") Integer userId, @Body NetworkProfile networkProfile) {
        return delegate.returningResponse(getStubGitHubNetworkProfile())
                .saveNetworkProfile(userId, networkProfile);
    }

    @Override
    public Single<List<SemesterGrade>> getSemesterGrades(@Path("userId") int userId) {
        return delegate.returningResponse(getStubSemesterGrades())
                .getSemesterGrades(userId);
    }

    private List<SemesterGrade> getStubSemesterGrades() {

        List<SemesterGrade> semesterGrades = new ArrayList<>();

        semesterGrades.add(getSemesterGrade("Semester 1", 9.20));
        semesterGrades.add(getSemesterGrade("Semester 2", 7.90));
        semesterGrades.add(getSemesterGrade("Semester 3", 7.45));
        semesterGrades.add(getSemesterGrade("Semester 4", 8.10));
        semesterGrades.add(getSemesterGrade("Semester 5", 6.70));
        semesterGrades.add(getSemesterGrade("Semester 6", 9.00));
        semesterGrades.add(getSemesterGrade("Semester 7", 7.34));

        return semesterGrades;
    }

    private List<NetworkProfile> getStubNetworkProfiles() {
        List<NetworkProfile> networkProfiles = new ArrayList<>();

        NetworkProfile networkProfile = getStubGitHubNetworkProfile();

        NetworkProfile networkProfile1 = getStubLinkedInNetworkProfile();

        networkProfiles.add(networkProfile);
        networkProfiles.add(networkProfile1);

        return networkProfiles;
    }

    private NetworkProfile getStubGitHubNetworkProfile() {
        NetworkProfile networkProfile = new NetworkProfile();
        networkProfile.setNetwork(Network.GITHUB);
        networkProfile.setUrl("https://github.com");
        return networkProfile;
    }

    private NetworkProfile getStubLinkedInNetworkProfile() {
        NetworkProfile networkProfile1 = new NetworkProfile();
        networkProfile1.setNetwork(Network.LINKEDIN);
        networkProfile1.setUrl("http://linkedin.com");
        return networkProfile1;
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

    private SemesterGrade getSemesterGrade(String semester, Double grade) {
        SemesterGrade semesterGrade = new SemesterGrade();
        semesterGrade.setSemester(semester);
        semesterGrade.setSemesterGrade(grade);
        return semesterGrade;
    }
}
