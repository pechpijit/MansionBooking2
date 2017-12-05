package dev.pechy.mansionbooking.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import dev.pechy.mansionbooking.BaseActivity;
import dev.pechy.mansionbooking.R;
import dev.pechy.mansionbooking.model.ModelPostHome;

public class AdapterRandomApartment extends RecyclerView.Adapter<AdapterRandomApartment.VersionViewHolder> {
    ArrayList<ModelPostHome> posts;
    String url;
    Context context;
    OnItemClickListener clickListener;

    public AdapterRandomApartment(Activity applicationContext, ArrayList<ModelPostHome> posts) {
        this.context = applicationContext;
        this.posts = posts;
        this.url = BaseActivity.BASE_URL_PICTURE;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_random_apartment, viewGroup, false);
        switch (posts.get(i).getImage().size()) {
            case 1:
                Log.d("AdapterRandomApartment", "1");
                view = LayoutInflater.from(context).inflate(R.layout.adapter_random_apartment, viewGroup, false);
                break;
            case 2:
                Log.d("AdapterRandomApartment", "2");
                view = LayoutInflater.from(context).inflate(R.layout.adapter_random_apartment_tow, viewGroup, false);
                break;
            case 3:
                Log.d("AdapterRandomApartment", "3");
                view = LayoutInflater.from(context).inflate(R.layout.adapter_random_apartment_three, viewGroup, false);
                break;
        }
        Log.d("AdapterRandomApartment", "success");
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        versionViewHolder.txt_name.setText(posts.get(i).getName());
        versionViewHolder.txt_phone.setText("Tel: " + posts.get(i).getPhone());
        versionViewHolder.txt_email.setText("Email: " + posts.get(i).getEmail());
        versionViewHolder.txt_address.setText(posts.get(i).getAddress());

        if (posts.get(i).getPrice().getMin() == null || posts.get(i).getPrice().getMax() == null) {
            versionViewHolder.txtPrice.setText("ราคาโดยเฉลี่ย : ยังไม่มีการกำหนดราคา");
        } else {
            versionViewHolder.txtPrice.setText("ราคาโดยเฉลี่ย : " + posts.get(i).getPrice().getMin() + "-" + posts.get(i).getPrice().getMax());
        }

        switch (posts.get(i).getImage().size()) {
            case 1:
                Glide.with(context)
                        .load(url + "/images/build/" + posts.get(i).getImage().get(0).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(R.drawable.nopic)
                        .into(versionViewHolder.img1);
                break;
            case 2:
                Glide.with(context)
                        .load(url + "/images/build/" + posts.get(i).getImage().get(0).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(R.drawable.nopic)
                        .into(versionViewHolder.img1);

                Glide.with(context)
                        .load(url + "/images/build/" + posts.get(i).getImage().get(1).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(R.drawable.nopic)
                        .into(versionViewHolder.img2);

                break;
            case 3:
                Glide.with(context)
                        .load(url + "/images/build/" + posts.get(i).getImage().get(0).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(R.drawable.nopic)
                        .into(versionViewHolder.img1);

                Glide.with(context)
                        .load(url + "/images/build/" + posts.get(i).getImage().get(1).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(R.drawable.nopic)
                        .into(versionViewHolder.img2);


                Glide.with(context)
                        .load(url + "/images/build/" + posts.get(i).getImage().get(2).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(R.drawable.nopic)
                        .into(versionViewHolder.img3);

                break;
        }
    }

    @Override
    public int getItemCount() {
        return posts.isEmpty() ? 0 : posts.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name, txt_phone, txt_email, txt_address, txtPrice;

        ImageView img1, img2, img3;

        public VersionViewHolder(View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_phone = itemView.findViewById(R.id.txt_phone);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_address = itemView.findViewById(R.id.txt_address);
            txtPrice = itemView.findViewById(R.id.txtPrice);

            img1 = itemView.findViewById(R.id.image1);
            img2 = itemView.findViewById(R.id.image2);
            img3 = itemView.findViewById(R.id.image3);

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