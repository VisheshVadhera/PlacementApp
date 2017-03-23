package com.vishesh.tpc_stud.gpa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.gpa.models.SemesterGrade;

import java.util.List;

import butterknife.ButterKnife;

public class SemesterGradeItemAdapter extends RecyclerView.Adapter<SemesterGradeItemAdapter.ViewHolder> {

    private final Context context;
    private List<SemesterGrade> semesterGrades;

    public SemesterGradeItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SemesterGrade> semesterGrades) {
        this.semesterGrades = semesterGrades;
    }

    @Override
    public SemesterGradeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gpa_item, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SemesterGradeItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return semesterGrades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
