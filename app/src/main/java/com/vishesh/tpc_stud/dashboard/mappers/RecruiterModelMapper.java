package com.vishesh.tpc_stud.dashboard.mappers;

import com.vishesh.tpc_stud.core.scopes.PerActivity;
import com.vishesh.tpc_stud.dashboard.models.Recruiter;
import com.vishesh.tpc_stud.dashboard.models.RecruiterModel;

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

    };
}
