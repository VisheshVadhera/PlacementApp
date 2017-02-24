package com.vishesh.tpc_stud.dashboard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fernandocejas.arrow.optional.Optional;
import com.vishesh.tpc_stud.dashboard.models.RecruiterModel;

import java.util.List;

public class RecruiterItemAdapter extends RecyclerView.Adapter<RecruiterItemAdapter.ViewHolder> {


    private Optional<List<RecruiterModel>> recruitersOptional;

    public RecruiterItemAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    public void setData(List<RecruiterModel> recruiterModels){
        this.recruitersOptional = Optional.of(recruiterModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recruitersOptional.isPresent() ? recruitersOptional.get().size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
