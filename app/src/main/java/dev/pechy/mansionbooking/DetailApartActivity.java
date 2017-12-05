package dev.pechy.mansionbooking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;

import java.util.HashMap;

import at.blogc.android.views.ExpandableTextView;
import dev.pechy.mansionbooking.model.ModelBuild;
import dev.pechy.mansionbooking.model.ModelDetailLocation;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;

public class DetailApartActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener {
    Context mContext;
    TextView txt_title;
    LinearLayout view, view_detail, view_address;
    private SliderLayout mDemoSlider;
    HashMap<String, String> url_maps;
    String la, lo,phone;
    int id = 0;
    String TAG = "TravelDetailActivity";
    ImageView img_ex1, img_ex2;

    ExpandableTextView ex_detail, ex_address;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
        setContentView(R.layout.activity_detail_apart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mContext = this;
        mDemoSlider = findViewById(R.id.slider);
        url_maps = new HashMap<String, String>();

        txt_title = findViewById(R.id.txt_title);
        view = findViewById(R.id.view);

        view_detail = findViewById(R.id.view_detail);
        view_address = findViewById(R.id.view_address);


        ex_detail = findViewById(R.id.ex_detail);
        ex_address = findViewById(R.id.ex_address);

        img_ex1 = findViewById(R.id.img_ex1);
        img_ex2 = findViewById(R.id.img_ex2);

        setSettingEx(ex_detail);
        setSettingEx(ex_address);

        view_detail.setOnClickListener(this);
        view_address.setOnClickListener(this);
        findViewById(R.id.btn_map).setOnClickListener(this);
        findViewById(R.id.btn_book).setOnClickListener(this);
        findViewById(R.id.btnCall).setOnClickListener(this);

        Bundle i = getIntent().getExtras();
        id = i.getInt("id");
        getData(id);
    }

    private void getData(int id) {
        showProgressDialog(LOAD);
        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BaseActivity.BASE_URL+"apartment/"+id);
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

    private void setSettingEx(ExpandableTextView ex) {
        ex.setAnimationDuration(750L);
        ex.setInterpolator(new LinearOutSlowInInterpolator());
    }

    public void setView(String json) {
        Gson gson = new Gson();
        ModelDetailLocation model = gson.fromJson(json, ModelDetailLocation.class);

        for (ModelBuild image :
                model.getBuild()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(image.getBuildName())
                    .image(BaseActivity.BASE_URL_PICTURE + "/images/build/" + image.getBuildImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", image.getBuildName());

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setClickable(false);

        la = model.getLocation().getLoLatitude();
        lo = model.getLocation().getLoLongitude();
        phone = model.getLocation().getLoPhone();
        String strDetail = "";

        strDetail += "ชื่อ : "+model.getLocation().getLoName();
        strDetail += "\nอีเมล์ : "+model.getLocation().getLoEmail();
        strDetail += "\nเบอร์โทร์ : "+model.getLocation().getLoPhone();
        strDetail += "\nค่าน้ำต่อหน่วย : "+model.getPublicut().getPubWaterBTH();
        strDetail += "\nค่าไฟต่อหน่วย : "+model.getPublicut().getPubElectBTH();
        strDetail += "\nราคาห้องพัก : "+model.getPrice().getMin()+"-"+model.getPrice().getMax();

        ex_detail.setText(strDetail);
        ex_address.setText(model.getLocation().getLoAddress());

        txt_title.setText(model.getLocation().getLoName());

        view.setVisibility(View.VISIBLE);

        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish_activity"));
    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        mDemoSlider.startAutoCycle();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_map:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+la+","+lo));
                startActivity(intent);
                break;
            case R.id.view_detail:
                setEx(ex_detail, img_ex1);
                break;
            case R.id.view_address:
                setEx(ex_address, img_ex2);
                break;
            case R.id.btn_book:
                checkCustomer();
                break;
            case R.id.btnCall:
                dialogCall();
                break;
        }
    }

    private void dialogCall() {
        new AlertDialog.Builder(this,R.style.AppTheme_Dark_Dialog)
                .setTitle("ยืนยันการโทรออก")
                .setMessage("กด 'ตกลง' เพื่อยืนยันการโทรออก")
                .setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phone));
                        startActivity(callIntent);
                    }
                })
                .setPositiveButton("ยกเลิก", null)
                .show();
    }

    private void checkCustomer() {
        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        showProgressDialog(LOAD);
        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BaseActivity.BASE_URL+"profile/"+sp.getInt("id",0));
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                if (!data.equals("empty")) {
                    Intent i = new Intent(DetailApartActivity.this, AllRoomActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                } else {
                    new AlertDialog.Builder(DetailApartActivity.this,R.style.AppTheme_Dark_Dialog)
                            .setTitle("ตรวจสอบความถูกต้อง")
                            .setMessage("กรุณากรอกข้อมูลประวัติส่วนตัวก่อนทำรายการจอง")
                            .setNegativeButton("กรอกข้อมูล",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                    finish();
                                    startActivity(new Intent(DetailApartActivity.this, ProfileActivity.class));
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                }
                            })
                            .setPositiveButton("ยกเลิก", null)
                            .setCancelable(true)
                            .show();
                }
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


    private void setEx(ExpandableTextView ex, ImageView img) {
        ex.toggle();
        if (ex.isExpanded()) {
            img.animate().rotation(0).start();
            ex.collapse();
        } else {
            img.animate().rotation(180).start();
            ex.expand();
        }
    }
}

