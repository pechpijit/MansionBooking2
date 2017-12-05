package dev.pechy.mansionbooking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import dev.pechy.mansionbooking.model.ModelDetailBooking;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;

public class DetailRoomAndBookingActivity extends BaseActivity {

    Button btnCancel, btnPay,btnRoom;
    int bookingID;
    Context context;
    LinearLayout layoutBtn;
    TextView txtVerify,txtTitle;
    ImageView imageRoom;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_room_and_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bookingID = getIntent().getExtras().getInt("id", 0);

        txtTitle = findViewById(R.id.txtTitle);
        btnCancel = findViewById(R.id.btnCancel);
        imageRoom = findViewById(R.id.image);
        btnRoom = findViewById(R.id.btnRoom);
        layoutBtn = findViewById(R.id.layoutBtn);
        txtVerify = findViewById(R.id.txtVerify);

        btnPay = findViewById(R.id.btnPay);
        btnPay.setEnabled(false);
        btnPay.setBackgroundColor(Color.parseColor("#D5D6D6"));
        btnPay.setTextColor(Color.parseColor("#A4A5A5"));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DetailRoomAndBookingActivity.this, R.style.AppTheme_Dark_Dialog)
                        .setTitle("แจ้งเตือน")
                        .setMessage("ยืนยัน ยกเลิกการจองห้องพัก")
                        .setNegativeButton("ยืนยัน", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cancelData();
                            }
                        })
                        .setPositiveButton("ยกเลิก", null)
                        .setCancelable(true)
                        .show();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailRoomAndBookingActivity.this,UpdateImageActivity.class).putExtra("id",bookingID));
            }
        });

        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_upfileactivity")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish_upfileactivity"));

        getData(bookingID);
    }


    private void setView(String json) {
        Gson gson = new Gson();
        ModelDetailBooking booking = gson.fromJson(json, ModelDetailBooking.class);
        txtTitle.setText("ข้อมูลห้องพัก");

        Glide.with(this)
                .load(BASE_URL_PICTURE + "/images/room/" + booking.getRoom().getRoomImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(imageRoom);

        if (booking.getBooking().getStatus() == 2) {
            txtVerify.setText("กรุณารอการติดต่อกลับจากทางหอพัก");
            btnPay.setEnabled(false);
            btnPay.setBackgroundColor(Color.parseColor("#D5D6D6"));
            btnPay.setTextColor(Color.parseColor("#A4A5A5"));
        } else if (booking.getBooking().getStatus() == 3) {
            txtVerify.setText("กรุณาชำระเงิน");
            txtVerify.setTextSize(18);
            btnPay.setEnabled(true);
            btnPay.setBackgroundColor(Color.parseColor("#4CAF50"));
            btnPay.setTextColor(Color.parseColor("#ffffff"));
        } else if (booking.getBooking().getStatus() == 4) {
            txtVerify.setText("กำลังตรวจสอบสลิปการโอน");
           layoutBtn.setVisibility(View.INVISIBLE);
        } else if (booking.getBooking().getStatus() == 5) {
            txtVerify.setText("สลิปการโอนไม่ถูกต้อง กรุณาส่งใหม่");

            btnPay.setEnabled(true);
            btnPay.setText("แก้ไขสลิป");
            btnPay.setBackgroundColor(Color.parseColor("#4CAF50"));
            btnPay.setTextColor(Color.parseColor("#ffffff"));

            btnCancel.setEnabled(false);
            btnCancel.setBackgroundColor(Color.parseColor("#D5D6D6"));
            btnCancel.setTextColor(Color.parseColor("#A4A5A5"));

        } else if (booking.getBooking().getStatus() == 6) {
            txtVerify.setText("กำลังดำเนินการ");
            layoutBtn.setVisibility(View.INVISIBLE);
        } else if (booking.getBooking().getStatus() == 7) {
            txtVerify.setText("เข้าอยู่แล้ว");
            layoutBtn.setVisibility(View.INVISIBLE);
            btnRoom.setVisibility(View.VISIBLE);
        } else {
            if (booking.getBooking().getStatus() == 8) {
                txtVerify.setText("ไม่อนุมัติการจอง กรุณายกเลิกการจอง เพื่อทำการจองห้องใหม่");
            }
        }

        hideProgressDialog();
    }

    private void cancelData() {
        btnCancel.setEnabled(true);
        showProgressDialog("กำลังตรวจสอบ...");
        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BaseActivity.BASE_URL + "booking/cancel/" + bookingID);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                if (!data.equals("success")) {
                    btnCancel.setEnabled(true);
                    dialogTM("แจ้งเตือน", "เกิดข้อผิดพลาดบางอย่าง กรุณาลองใหม่อีกครั้ง");
                } else {
                    Intent home = new Intent("finish_homeactivity");
                    sendBroadcast(home);
                    Intent intent = new Intent("finish_activity");
                    sendBroadcast(intent);
                    finish();
                    startActivity(new Intent(DetailRoomAndBookingActivity.this, HomeActivity.class).putExtra("cancelbooking", true));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }

            @Override
            public void ResultError(String data) {
                btnCancel.setEnabled(true);
                hideProgressDialog();
                dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                btnCancel.setEnabled(true);
                hideProgressDialog();
                dialogResultNull();
            }
        });
    }

    private void getData(int ID) {
        showProgressDialog(BaseActivity.LOAD);

        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BaseActivity.BASE_URL + "booking/" + ID);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                if (!data.equals("fail")) {
                    setView(data);
                } else {
                    hideProgressDialog();
                    dialogTM("แจ้งเตือน", "เกิดข้อผิดพลาดบางอย่าง กรุณาลองใหม่อีกครั้ง");
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                dialogResultError();
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                dialogResultNull();
            }
        });
    }
}
