package com.menglingpeng.designersshow.mvp.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.menglingpeng.designersshow.BaseFragment;
import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.net.API;
import com.menglingpeng.designersshow.net.HttpUtils;
import com.menglingpeng.designersshow.net.Json;
import com.menglingpeng.designersshow.utils.Constants;

import java.io.IOException;


/**
 * Created by mengdroid on 2017/10/13.
 */

public class RecyclerFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressBar progressBar;
    private String Type;
    private String list= null;
    private String timeframe = null;
    private String date = null;
    private String sort = null;
    public static final String TAB_POPULAR = "Popular";
    public static final String TAB_RECENT = "Recent";
    public static final String TAB_FOLLOWING = "Following";

    public static RecyclerFragment newInstance(String type){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        RecyclerFragment fragment = new RecyclerFragment() ;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_recycler;
    }

    @Override
    protected void initView() {
        swipeRefresh = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        new GetShotsTask().execute(API.ACESS_TOKEN);
        initFragments();

    }

    private void initFragments(){
        Type = getArguments().get(Constants.TYPE).toString();
        switch (Type){
            case TAB_FOLLOWING:
                break;
            case TAB_POPULAR:
                break;
            case TAB_RECENT:
                break;
        }

    }

    class GetShotsTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            Json.parseShots(HttpUtils.get(API.ACESS_TOKEN, list, timeframe, date, sort));

            return null;
        }
    }

    @Override
    protected void initData() {

    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
