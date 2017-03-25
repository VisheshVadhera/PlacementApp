package com.vishesh.tpc_stud.semesterGrades.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.semesterGrades.models.SemesterGrade;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SemesterGradeItemAdapter extends RecyclerView.Adapter<SemesterGradeItemAdapter.ViewHolder> {

    private final Context context;

    private List<SemesterGrade> semesterGrades;

    @Inject
    public SemesterGradeItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SemesterGrade> semesterGrades) {
        this.semesterGrades = semesterGrades;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_profile_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SemesterGrade semesterGrade = semesterGrades.get(position);

        holder.textProfileItemLabel
                .setText(semesterGrade.getSemester());

        holder.textProfileItemValue
                .setText(String.valueOf(semesterGrade.getSemesterGrade()));
    }

    @Override
    public int getItemCount() {
        return semesterGrades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_profile_item_label)
        TextView textProfileItemLabel;
        @BindView(R.id.text_profile_item_value)
        TextView textProfileItemValue;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
