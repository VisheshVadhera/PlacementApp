package com.vishesh.tpc_stud.dashboard.mappers;

import com.vishesh.tpc_stud.core.dagger.PerActivity;
import com.vishesh.tpc_stud.dashboard.models.JobOffer;
import com.vishesh.tpc_stud.dashboard.models.Recruiter;
import com.vishesh.tpc_stud.dashboard.models.RecruiterModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by vishesh on 24/2/17.
 */
@PerActivity
public class RecruiterModelMapper {

    @Inject
    public RecruiterModelMapper() {
    }

    public List<RecruiterModel> transform(List<Recruiter> recruiters) {

        List<RecruiterModel> recruiterModels = new ArrayList<>();

        for (Recruiter recruiter : recruiters) {
            recruiterModels.addAll(transform(recruiter));
        }
        return recruiterModels;
    }


    private List<RecruiterModel> transform(Recruiter recruiter) {

        List<RecruiterModel> recruiterModels = new ArrayList<>();

        for (JobOffer jobOffer : recruiter.getJobOffers()) {

            RecruiterModel recruiterModel = new RecruiterModel();
            recruiterModel.setId(recruiter.getId());
            recruiterModel.setName(recruiter.getName());
            recruiterModel.setDescription(recruiter.getDescription());
            recruiterModel.setProcessDate(recruiter.getProcessDate());
            recruiterModel.setJobOffer(jobOffer);

            recruiterModels.add(recruiterModel);
        }
        return recruiterModels;
    }
}
