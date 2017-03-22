package com.vishesh.tpc_stud.dashboard.services;

import com.vishesh.tpc_stud.dashboard.models.Course;
import com.vishesh.tpc_stud.dashboard.models.Degree;
import com.vishesh.tpc_stud.dashboard.models.JobOffer;
import com.vishesh.tpc_stud.dashboard.models.Recruiter;

import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;

public class MockRecruiterService implements RecruiterService {

    private final BehaviorDelegate<RecruiterService> delegate;

    public MockRecruiterService(MockRetrofit mockRetrofit) {
        delegate = mockRetrofit.create(RecruiterService.class);
    }

    @Override
    public Single<List<Recruiter>> getRecruiters(@Path("userId") int userId) {
        return delegate.returningResponse(getRecruiters())
                .getRecruiters(userId);
    }

    private List<Recruiter> getRecruiters() {

        List<Recruiter> recruiters = new ArrayList<>();
        recruiters.add(getRecruiter1());
        recruiters.add(getRecruiter2());

        return recruiters;
    }

    private Recruiter getRecruiter1() {

        Course course = new Course();
        course.setDegree(Degree.BACHELORS);
        course.setBranch("Electrical");

        Course course1 = new Course();
        course1.setDegree(Degree.MASTERS);
        course1.setBranch("Electrical");

        Course course2 = new Course();
        course2.setDegree(Degree.BACHELORS);
        course2.setBranch("Computer Science");

        List<Course> courses1 = new ArrayList<>();
        courses1.add(course);
        courses1.add(course1);

        List<Course> courses = new ArrayList<>();
        courses.add(course2);
        courses.addAll(courses1);

        Recruiter recruiter = new Recruiter();

        JobOffer jobOffer = new JobOffer();
        JobOffer jobOffer1 = new JobOffer();

        jobOffer.setId(1);
        jobOffer.setDescription("This is job offer's description. This is job offer's description. This is job offer's description. " +
                "This is job offer's description. This is job offer's description. This is job offer's description. ");
        jobOffer.setJobTitle("SDE I");
        jobOffer.setLocation("Helsinki, Finland");
        jobOffer.setNoOfOffers(3);
        jobOffer.setPayPackage(BigDecimal.valueOf(1200000));
        jobOffer.setCourses(courses);

        jobOffer1.setId(2);
        jobOffer1.setDescription("This is job offer1's description. This is job offer1's description. This is job offer1's description. " +
                "This is job offer1's description. This is job offer1's description. This is job offer1's description. ");
        jobOffer1.setJobTitle("Business Analyst");
        jobOffer1.setLocation("Helsinki, Finland");
        jobOffer1.setNoOfOffers(2);
        jobOffer1.setPayPackage(BigDecimal.valueOf(1000000));
        jobOffer.setCourses(courses1);

        List<JobOffer> jobOffers = new ArrayList<>();
        jobOffers.add(jobOffer);
        jobOffers.add(jobOffer1);

        recruiter.setId(1);
        recruiter.setProcessDate(LocalDate
                .now()
                .plusMonths(2)
                .plusDays(2));
        recruiter.setName("Company 123");
        recruiter.setDescription("This is the description. This is the description. This is the description. " +
                "This is the description. This is the description. This is the description. This is the description. ");
        recruiter.setJobOffers(jobOffers);

        return recruiter;
    }


    private Recruiter getRecruiter2() {

        Course course = new Course();
        course.setDegree(Degree.BACHELORS);
        course.setBranch("Mechanical");

        Course course2 = new Course();
        course2.setDegree(Degree.BACHELORS);
        course2.setBranch("Material Science");

        List<Course> courses1 = new ArrayList<>();
        courses1.add(course);

        List<Course> courses = new ArrayList<>();
        courses.add(course2);
        courses.addAll(courses1);

        Recruiter recruiter = new Recruiter();

        JobOffer jobOffer = new JobOffer();
        JobOffer jobOffer1 = new JobOffer();

        jobOffer.setId(3);
        jobOffer.setDescription("This is job offer3's description. This is job offer3's description. This is job offer3's description. " +
                "This is job offer3's description. This is job offer3's description. This is job offer3's description. ");
        jobOffer.setJobTitle("Well Engineer I");
        jobOffer.setLocation("Aberdeen, Scotland");
        jobOffer.setNoOfOffers(5);
        jobOffer.setPayPackage(BigDecimal.valueOf(1500000));
        jobOffer.setCourses(courses1);

        jobOffer1.setId(4);
        jobOffer1.setDescription("This is job offer4's description. This is job offer4's description. This is job offer4's description. " +
                "This is job offer4's description. This is job offer4's description. This is job offer4's description. ");
        jobOffer1.setJobTitle("Operations Engineer");
        jobOffer1.setLocation("Mumbai, India");
        jobOffer1.setNoOfOffers(4);
        jobOffer1.setPayPackage(BigDecimal.valueOf(1300000));
        jobOffer.setCourses(courses1);

        List<JobOffer> jobOffers = new ArrayList<>();
        jobOffers.add(jobOffer);
        jobOffers.add(jobOffer1);

        recruiter.setId(2);
        recruiter.setProcessDate(LocalDate
                .now()
                .plusMonths(1)
                .plusDays(10));
        recruiter.setName("Company 3456");
        recruiter.setDescription("This is the second description. This is the second description. " +
                "This is the second description. This is the second description. " +
                "This is the second description. This is the second description. This is the second description. ");
        recruiter.setJobOffers(jobOffers);

        return recruiter;
    }
}
