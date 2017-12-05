package dev.pechy.mansionbooking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import dev.pechy.mansionbooking.model.ModelDetailRoom;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class DetailRoomActivity extends BaseActivity {

    TextView txtTitle,txtCode,txtName,txtCat, txtMoney;
    ImageView image;
    Button btnBooking;
    int locationId,buildId, roomId;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTitle = findViewById(R.id.txtTitle);
        txtCode = findViewById(R.id.txtCode);
        txtName = findViewById(R.id.txtName);
        txtCat = findViewById(R.id.txtCat);
        txtMoney = findViewById(R.id.txtMoney);
        image = findViewById(R.id.image);
        btnBooking = findViewById(R.id.btnBooking);

        txtTitle.setText("");
        txtCode.setText("");
        txtName.setText("");
        txtCat.setText("");
        txtMoney.setText("");

        int ID = getIntent().getExtras().getInt("id", 0);
        getData(ID);
    }

    private void getData(int ID) {
        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BASE_URL+"apartment/build/room/"+ID);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                setView(data);
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                dialogResultNull();
            }
        });
    }

    private void bookingRoom() {
        btnBooking.setEnabled(false);
        showProgressDialog(VERIFY);

        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);

        RequestBody requestBody = new FormBody.Builder()
                .add("location", String.valueOf(locationId))
                .add("build", String.valueOf(buildId))
                .add("room", String.valueOf(roomId))
                .add("userapp", String.valueOf(sp.getInt("id",0)))
                .build();

        ApiClient.POST post = new ApiClient.POST(this);
        post.setURL(BASE_URL+"booking/room");
        post.setRequestBody(requestBody);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                if (data.equals("confirm")) {
                    hideProgressDialog();
                    dialogTM("ไม่สามารถจองได้","ท่านมีห้องพักที่กำลังรอการติดต่อกลับจากทางหอพักอยู่ จึงไม่สามารถจองเพิ่มได้ หากต้องการจองเพิ่มกรุณายกเลิกการจอง ได้ที่เมนู 'หอพัก'");
                } else if (data.equals("pay")) {
                    hideProgressDialog();
                    dialogTM("ไม่สามารถจองได้","ท่านมีห้องพักที่กำลังรอการชำระเงินอยู่อยู่ จึงไม่สามารถจองเพิ่มได้ หากต้องการจองเพิ่มกรุณายกเลิกการจอง ได้ที่เมนู 'หอพัก'");
                } else if (data.equals("success")) {
                    Intent intent = new Intent("finish_activity");
                    sendBroadcast(intent);
                    Intent intent2 = new Intent("finish_activity");
                    sendBroadcast(intent2);
                    finish();
                    startActivity(new Intent(DetailRoomActivity.this,ListBookingRoomActivity.class).putExtra("booking",true));
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                } else {
                    hideProgressDialog();
                    btnBooking.setEnabled(true);
                    dialogTM("แจ้งเตือน","เกิดข้อผิดพลาดบางอย่าง กรุณาลองใหม่อีกครั้ง");
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                btnBooking.setEnabled(true);
                dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                btnBooking.setEnabled(true);
                dialogResultNull(data);
            }
        });
    }

    private void setView(String json) {
        Gson gson = new Gson();
        ModelDetailRoom room = gson.fromJson(json, ModelDetailRoom.class);
        txtTitle.setText("ข้อมูลห้องพัก");
        txtCode.setText("หมายเลขห้อง : "+room.getCode());
        txtName.setText("ชื่อห้อง : "+room.getName());
        txtCat.setText("ประเภทห้อง : "+room.getTypeName());
        txtMoney.setText("ราคาห้องพักรายเดือน : "+room.getMonthlyprice()+" บาท");

        locationId = room.getLocationId();
        buildId = room.getBuildId();
        roomId = room.getId();

        Glide.with(this)
                .load(BASE_URL_PICTURE + "/images/room/" + room.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(image);

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DetailRoomActivity.this, R.style.AppTheme_Dark_Dialog)
                        .setTitle("แจ้งเตือน")
                        .setMessage("หลังจากท่านจองห้องพักแล้ว กรุณารอติดต่อกลับจากทางหอพัก เพื่อตกลงทำหารจ่ายค่ามัดจำ หรือ อื่นๆ")
                        .setNegativeButton("ยืนยัน", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                bookingRoom();
                            }
                        })
                        .setPositiveButton("ยกเลิก",null)
                        .setCancelable(true)
                        .show();
            }
        });
    }

}
