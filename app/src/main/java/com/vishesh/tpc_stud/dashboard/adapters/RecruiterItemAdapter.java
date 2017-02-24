package com.vishesh.tpc_stud.dashboard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fernandocejas.arrow.optional.Optional;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.dashboard.models.RecruiterModel;

import java.util.List;

import javax.inject.Inject;

public class RecruiterItemAdapter extends RecyclerView.Adapter<RecruiterItemAdapter.ViewHolder> {

    private Optional<List<RecruiterModel>> recruitersOptional;

    private final Context context;

    @Inject
    public RecruiterItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recruiter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(recruitersOptional.isPresent()){
            List<RecruiterModel> recruiterModels = recruitersOptional.get();

        }
    }

    public void setData(List<RecruiterModel> recruiterModels){
        this.recruitersOptional = Optional.of(recruiterModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recruitersOptional.isPresent() ? recruitersOptional.get().size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
