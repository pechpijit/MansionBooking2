package dev.pechy.mansionbooking.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import dev.pechy.mansionbooking.AccountSettingActivity;
import dev.pechy.mansionbooking.EmailSignInActivity;
import dev.pechy.mansionbooking.HomeActivity;
import dev.pechy.mansionbooking.ListBookingRoomActivity;
import dev.pechy.mansionbooking.MainActivity;
import dev.pechy.mansionbooking.ProfileActivity;
import dev.pechy.mansionbooking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener{

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView txtName = view.findViewById(R.id.txt_fullname);
        TextView txtEmail = view.findViewById(R.id.txt_email);

        SharedPreferences sp = getActivity().getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        txtName.setText(sp.getString("name", ""));
        txtEmail.setText(sp.getString("email", ""));

        view.findViewById(R.id.setting).setOnClickListener(this);
        view.findViewById(R.id.profile).setOnClickListener(this);
        view.findViewById(R.id.apartment).setOnClickListener(this);
        view.findViewById(R.id.logout).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                ((HomeActivity)getActivity()).LogoutApp();
                break;
            case R.id.setting:
                startActivity(new Intent(getActivity(), AccountSettingActivity.class));
                getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
            case R.id.profile:
                startActivity(new Intent(getActivity(), ProfileActivity.class));
                getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
            case R.id.apartment:
                startActivity(new Intent(getActivity(), ListBookingRoomActivity.class));
                getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
        }
    }
}
