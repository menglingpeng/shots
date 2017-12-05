package com.menglingpeng.designersshow.mvp.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.designersshow.BaseActivity;
import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.utils.Constants;

public class UserLikesActivity extends BaseActivity {

    private Toolbar toolbar;
    private String type;
    private String title;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_likes;
    }

    @Override
    protected void initViews() {
        super.initViews();
        type = getIntent().getStringExtra(Constants.TYPE);
        title = new StringBuilder().append(getIntent().getStringExtra(Constants.NAME)).append(getString(R.string.s)).append(getString(R.string.likes)).toString();
        toolbar = (Toolbar)findViewById(R.id.user_likes_tb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
