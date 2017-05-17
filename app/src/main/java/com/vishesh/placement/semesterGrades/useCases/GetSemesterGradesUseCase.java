package com.vishesh.placement.semesterGrades.useCases;

import com.vishesh.placement.core.useCases.BaseUseCase;
import com.vishesh.placement.common.repos.UserRepository;
import com.vishesh.placement.semesterGrades.models.SemesterGrade;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class GetSemesterGradesUseCase extends BaseUseCase<List<SemesterGrade>, Integer, Object> {

    private final UserRepository userRepository;

    @Inject
    public GetSemesterGradesUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                    @Named("postJobScheduler") Scheduler postJobScheduler,
                                    UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    public Single<List<SemesterGrade>> buildSingle(Integer userId, Object o) {
        return userRepository.getSemesterGrades(userId);
    }
}
