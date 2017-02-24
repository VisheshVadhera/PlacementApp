package com.vishesh.tpc_stud.dashboard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernandocejas.arrow.optional.Optional;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.utils.UiUtils;
import com.vishesh.tpc_stud.dashboard.models.RecruiterModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        if (recruitersOptional.isPresent()) {

            RecruiterModel recruiterModel = recruitersOptional.get().get(position);

            holder.textJobOfferTitle.setText(recruiterModel.getJobOffer().getJobTitle());
            holder.textJobOfferDescription.setText(recruiterModel.getJobOffer().getDescription());
            holder.textPayPackageValue
                    .setText(UiUtils.toRupeeFormattedString(recruiterModel
                            .getJobOffer().getPayPackage()));
            holder.textRecruiterName.setText(recruiterModel.getName());
            holder.textProcessDateValue
                    .setText(UiUtils
                            .toFormattedDate(recruiterModel.getProcessDate()));
        }
    }

    public void setData(List<RecruiterModel> recruiterModels) {
        this.recruitersOptional = Optional.of(recruiterModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recruitersOptional.isPresent() ? recruitersOptional.get().size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_job_offer_title)
        TextView textJobOfferTitle;
        @BindView(R.id.text_recruiter_name)
        TextView textRecruiterName;
        @BindView(R.id.text_job_offer_description)
        TextView textJobOfferDescription;
        @BindView(R.id.text_process_date_value)
        TextView textProcessDateValue;
        @BindView(R.id.text_pay_package_value)
        TextView textPayPackageValue;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
