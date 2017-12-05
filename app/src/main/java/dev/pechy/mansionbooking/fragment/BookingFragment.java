package dev.pechy.mansionbooking.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dev.pechy.mansionbooking.BaseActivity;
import dev.pechy.mansionbooking.DetailRoomActivity;
import dev.pechy.mansionbooking.DetailRoomAndBookingActivity;
import dev.pechy.mansionbooking.HomeActivity;
import dev.pechy.mansionbooking.ListBookingRoomActivity;
import dev.pechy.mansionbooking.R;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment{

    Button btnCancel, btnPay;
    int bookingID;
    Context context;

    public BookingFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public BookingFragment(Context context, int bookingID) {
        this.context = context;
        this.bookingID = bookingID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        btnCancel = view.findViewById(R.id.btnCancel);
        btnPay = view.findViewById(R.id.btnPay);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context, R.style.AppTheme_Dark_Dialog)
                        .setTitle("แจ้งเตือน")
                        .setMessage("ยืนยัน ยกเลิกการจองห้องพัก")
                        .setNegativeButton("ยืนยัน", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cancelData();
                            }
                        })
                        .setPositiveButton("ยกเลิก",null)
                        .setCancelable(true)
                        .show();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void cancelData() {
        btnCancel.setEnabled(true);
        ((DetailRoomAndBookingActivity) getActivity()).showProgressDialog("กำลังตรวจสอบ...");
        ApiClient.GET post = new ApiClient.GET(getActivity());
        post.setURL(BaseActivity.BASE_URL + "booking/cancel/" + bookingID);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                ((DetailRoomAndBookingActivity) getActivity()).hideProgressDialog();
                if (!data.equals("success")) {
                    btnCancel.setEnabled(true);
                    ((DetailRoomAndBookingActivity) getActivity()).dialogTM("แจ้งเตือน", "เกิดข้อผิดพลาดบางอย่าง กรุณาลองใหม่อีกครั้ง");
                } else {
                    Intent home = new Intent("finish_homeactivity");
                    getActivity().sendBroadcast(home);
                    Intent intent = new Intent("finish_activity");
                    getActivity().sendBroadcast(intent);
                    getActivity().finish();
                    startActivity(new Intent(context,HomeActivity.class).putExtra("cancelbooking",true));
                    getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }

            @Override
            public void ResultError(String data) {
                btnCancel.setEnabled(true);
                ((DetailRoomAndBookingActivity) getActivity()).hideProgressDialog();
                ((DetailRoomAndBookingActivity) getActivity()).dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                btnCancel.setEnabled(true);
                ((DetailRoomAndBookingActivity) getActivity()).hideProgressDialog();
                ((DetailRoomAndBookingActivity) getActivity()).dialogResultNull();
            }
        });
    }
}
