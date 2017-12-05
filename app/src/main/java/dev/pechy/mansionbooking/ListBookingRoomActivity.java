package dev.pechy.mansionbooking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import dev.pechy.mansionbooking.adapter.AdapterBooking;
import dev.pechy.mansionbooking.adapter.AdapterRandomApartment;
import dev.pechy.mansionbooking.model.ModelListBooking;
import dev.pechy.mansionbooking.model.ModelPostHome;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;

public class ListBookingRoomActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AdapterBooking adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    String TAG = "ListBookingRoomActivity";

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
        setContentView(R.layout.activity_list_booking_room);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.dummyfrag_scrollableview);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);

        boolean booking = false;
        try {
            booking = getIntent().getExtras().getBoolean("booking");
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        if (booking) {
            dialogTM("สำเร็จ","จองห้องพักสำเร็จแล้ว");
        }

        getData();

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

    private void getData() {
        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BaseActivity.BASE_URL + "booking/userapp/" + sp.getInt("id", 0));
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                mSwipeRefreshLayout.setRefreshing(false);
                setView(data);
            }

            @Override
            public void ResultError(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                hideProgressDialog();
                dialogResultError();
            }

            @Override
            public void ResultNull(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                hideProgressDialog();
                dialogResultNull();
            }
        });
    }

    private void setView(String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelListBooking>>() {
        }.getType();
        Collection<ModelListBooking> enums = gson.fromJson(json, collectionType);
        final ArrayList<ModelListBooking> posts = new ArrayList<ModelListBooking>(enums);

        adapter = new AdapterBooking(this, posts);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterBooking.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = posts.get(position).getId();
                Intent intent = new Intent(ListBookingRoomActivity.this, DetailRoomAndBookingActivity.class);
                intent.putExtra("id", ID);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }
}
