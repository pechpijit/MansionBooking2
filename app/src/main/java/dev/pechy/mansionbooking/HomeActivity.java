package dev.pechy.mansionbooking;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import dev.pechy.mansionbooking.fragment.HomeFragment;
import dev.pechy.mansionbooking.fragment.UserFragment;

public class HomeActivity extends BaseActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private BottomBar bottomBar;
    String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        boolean cancelbooking = false;
        try {
            cancelbooking = getIntent().getExtras().getBoolean("cancelbooking");
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        if (cancelbooking) {
            dialogTM("สำเร็จ","ยกเลิกการจองห้องพักสำเร็จแล้ว");
        }


        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        bottomBar = findViewById(R.id.bottomBar);
        setSupportActionBar(toolbar);

        fab.setVisibility(View.INVISIBLE);

        toolbar.setBackgroundColor(getResources().getColor(R.color.menu1));
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        toolbar.setBackgroundColor(getResources().getColor(R.color.menu1));
                        setFram(new HomeFragment());
                        break;
                    case R.id.tab_map:
                        toolbar.setBackgroundColor(getResources().getColor(R.color.menu4));
                        break;
                    case R.id.tab_user:
                        toolbar.setBackgroundColor(getResources().getColor(R.color.menu5));
                        setFram(new UserFragment());
                        break;
                }
            }
        });

        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_homeactivity")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish_homeactivity"));
    }

    public void setFram(Fragment fram) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content, fram);
        ft.commit();
    }
}
