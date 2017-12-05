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
import dev.pechy.mansionbooking.model.ModelRoom;


public class AdapterBooking extends RecyclerView.Adapter<AdapterBooking.VersionViewHolder> {
    ArrayList<ModelListBooking> model;
    Context context;
    OnItemClickListener clickListener;

    public AdapterBooking(Context applicationContext, ArrayList<ModelListBooking> model) {
        this.context = applicationContext;
        this.model = model;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_booking, viewGroup, false);

        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        versionViewHolder.txtLocation.setText(model.get(i).getLocationName());
        if (model.get(i).getStatus() == 2) {
            versionViewHolder.txtStatus.setText("(รอติดต่อกลับ)");
        } else if (model.get(i).getStatus() == 3) {
            versionViewHolder.txtStatus.setText("(กรุณาชำระเงิน)");
        } else if (model.get(i).getStatus() == 4) {
            versionViewHolder.txtStatus.setText("(รอตรวจสอบสลิป)");
        }else if (model.get(i).getStatus() == 5) {
            versionViewHolder.txtStatus.setText("(แก้ไขสลิป)");
        }else if (model.get(i).getStatus() == 6) {
            versionViewHolder.txtStatus.setText("(กำลังดำเนินการ)");
        }else if (model.get(i).getStatus() == 8) {
            versionViewHolder.txtStatus.setText("(ไม่อนุมัติการจอง)");
        }
        versionViewHolder.txtBuild.setText("อาคาร:"+model.get(i).getBuildName());
        versionViewHolder.txtRoom.setText("ห้อง:"+model.get(i).getRoomName());
    }

    @Override
    public int getItemCount() {
        return model == null ? 0 : model.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtLocation;
        TextView txtBuild;
        TextView txtRoom;
        TextView txtStatus;

        public VersionViewHolder(View itemView) {
            super(itemView);
            txtLocation =  itemView.findViewById(R.id.txtLocation);
            txtBuild =  itemView.findViewById(R.id.txtBuild);
            txtRoom =  itemView.findViewById(R.id.txtRoom);
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
