package com.vishesh.placement.semesterGrades.views;

import android.os.Bundle;

import com.vishesh.placement.R;
import com.vishesh.placement.core.views.BaseActivity;

public class SemesterGradesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_grades);

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new SemesterGradesFragment());
        }
    }
}
