package dev.pechy.mansionbooking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dev.pechy.mansionbooking.fragment.RoomFragment;
import dev.pechy.mansionbooking.model.ModelBuild;
import dev.pechy.mansionbooking.model.ModelPostHome;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;

public class AllRoomActivity extends BaseActivity {
    Toolbar toolbar;
    int locationID;
    ViewPager viewPager;
    TabLayout tabLayout;

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
        setContentView(R.layout.activity_booking_room);
        toolbar = findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        locationID = getIntent().getExtras().getInt("id");

        viewPager = findViewById(R.id.tabanim_viewpager);

        tabLayout = findViewById(R.id.tabanim_tabs);

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
        showProgressDialog(LOAD);
        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BaseActivity.BASE_URL+"apartment/"+locationID+"/build");
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                setupViewPager(data);
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

    private void setupViewPager(String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelBuild>>() {}.getType();
        Collection<ModelBuild> enums = gson.fromJson(json, collectionType);
        final ArrayList<ModelBuild> posts = new ArrayList<ModelBuild>(enums);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for(ModelBuild build : posts) {
            adapter.addFrag(new RoomFragment(this,build.getId()),build.getBuildName());
        }

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition()); //สำหรับกดแท็บแล้วเปลี่ยน page

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}