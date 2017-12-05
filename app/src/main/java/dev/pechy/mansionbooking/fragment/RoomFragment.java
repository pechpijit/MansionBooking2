package dev.pechy.mansionbooking.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import dev.pechy.mansionbooking.BaseActivity;
import dev.pechy.mansionbooking.AllRoomActivity;
import dev.pechy.mansionbooking.DetailRoomActivity;
import dev.pechy.mansionbooking.R;
import dev.pechy.mansionbooking.adapter.AdapterRoom;
import dev.pechy.mansionbooking.model.ModelRoom;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;

public class RoomFragment extends Fragment {

    private int idBuild;
    private RecyclerView recyclerView;
    private AdapterRoom adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Context context;

    public RoomFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public RoomFragment(Context context,int idBuild) {
        this.context = context;
        this.idBuild = idBuild;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        recyclerView = view.findViewById(R.id.dummyfrag_scrollableview);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);

        getData();

        return view;
    }

    private void getData() {
        ApiClient.GET post = new ApiClient.GET(getActivity());
        post.setURL(BaseActivity.BASE_URL+"apartment/build/"+idBuild+"/room");
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                ((AllRoomActivity) getActivity()).hideProgressDialog();
                mSwipeRefreshLayout.setRefreshing(false);
                setView(data);
            }

            @Override
            public void ResultError(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                ((AllRoomActivity) getActivity()).hideProgressDialog();
                ((AllRoomActivity) getActivity()).dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                ((AllRoomActivity) getActivity()).hideProgressDialog();
                ((AllRoomActivity) getActivity()).dialogResultNull();
            }
        });
    }

    private void setView(String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelRoom>>() {}.getType();
        Collection<ModelRoom> enums = gson.fromJson(json, collectionType);
        final ArrayList<ModelRoom> posts = new ArrayList<ModelRoom>(enums);

        adapter = new AdapterRoom(getActivity(), posts);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterRoom.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID =posts.get(position).getId();
                startActivity(new Intent(getActivity(), DetailRoomActivity.class).putExtra("id",ID));
                getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
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
