package com.vishesh.tpc_stud.gpa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.gpa.models.Cpi;

import java.util.List;

import butterknife.ButterKnife;

public class GpaItemAdapter extends RecyclerView.Adapter<GpaItemAdapter.ViewHolder> {

    private final Context context;
    private List<Cpi> cpis;

    public GpaItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Cpi> cpis) {
        this.cpis = cpis;
    }

    @Override
    public GpaItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gpa_item, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GpaItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cpis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
