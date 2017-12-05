package dev.pechy.mansionbooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dev.pechy.mansionbooking.R;
import dev.pechy.mansionbooking.model.ModelListBooking;


public class AdapterRoomBill extends RecyclerView.Adapter<AdapterRoomBill.VersionViewHolder> {
    ArrayList<ModelListBooking> model;
    Context context;
    OnItemClickListener clickListener;

    public AdapterRoomBill(Context applicationContext, ArrayList<ModelListBooking> model) {
        this.context = applicationContext;
        this.model = model;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_room_bill, viewGroup, false);

        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        versionViewHolder.txtTitle.setText(model.get(i).getLocationName());
        if (model.get(i).getStatus() == 1) {
            versionViewHolder.txtStatus.setText("(ค้างชำระ)");
        } else if (model.get(i).getStatus() == 2) {
            versionViewHolder.txtStatus.setText("(ชำระแล้ว)");
        }
        versionViewHolder.txtTime.setText(model.get(i).getBuildName());
    }

    @Override
    public int getItemCount() {
        return model == null ? 0 : model.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle;
        TextView txtTime;
        TextView txtStatus;

        public VersionViewHolder(View itemView) {
            super(itemView);
            txtTitle =  itemView.findViewById(R.id.txtTitle);
            txtTime =  itemView.findViewById(R.id.txtTime);
            txtStatus =  itemView.findViewById(R.id.txtStatus);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
