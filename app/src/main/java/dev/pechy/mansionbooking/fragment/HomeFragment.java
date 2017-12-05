package dev.pechy.mansionbooking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import dev.pechy.mansionbooking.BaseActivity;
import dev.pechy.mansionbooking.DetailApartActivity;
import dev.pechy.mansionbooking.EmailSignInActivity;
import dev.pechy.mansionbooking.HomeActivity;
import dev.pechy.mansionbooking.R;
import dev.pechy.mansionbooking.adapter.AdapterRandomApartment;
import dev.pechy.mansionbooking.model.ModelPostHome;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterRandomApartment adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.dummyfrag_scrollableview);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setHasFixedSize(true);

        getData();

        return view;
    }

    private void getData() {
        ApiClient.GET post = new ApiClient.GET(getActivity());
        post.setURL(BaseActivity.BASE_URL+"apartment/random");
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                ((HomeActivity) getActivity()).hideProgressDialog();
                mSwipeRefreshLayout.setRefreshing(false);
                setView(data);
            }

            @Override
            public void ResultError(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                ((HomeActivity) getActivity()).hideProgressDialog();
                ((HomeActivity) getActivity()).dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                ((HomeActivity) getActivity()).hideProgressDialog();
                ((HomeActivity) getActivity()).dialogResultNull();
            }
        });
    }

    private void setView(String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelPostHome>>() {}.getType();
        Collection<ModelPostHome> enums = gson.fromJson(json, collectionType);
        final ArrayList<ModelPostHome> posts = new ArrayList<ModelPostHome>(enums);

        adapter = new AdapterRandomApartment(getActivity(), posts);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterRandomApartment.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID =posts.get(position).getId();
                Intent intent = new Intent(getActivity(), DetailApartActivity.class);
                intent.putExtra("id", ID);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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
