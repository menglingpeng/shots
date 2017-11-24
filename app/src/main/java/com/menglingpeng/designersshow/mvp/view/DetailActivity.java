package com.menglingpeng.designersshow.mvp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.designersshow.BaseActivity;
import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.mvp.interf.OnloadDetailImageListener;
import com.menglingpeng.designersshow.mvp.model.Shots;
import com.menglingpeng.designersshow.net.HttpUtils;
import com.menglingpeng.designersshow.utils.Constants;
import com.menglingpeng.designersshow.utils.ImageLoader;
import com.menglingpeng.designersshow.utils.TextUtil;
import com.menglingpeng.designersshow.utils.TimeUtil;

import java.util.HashMap;

import okhttp3.HttpUrl;

/**
 * Created by mengdroid on 2017/11/1.
 */

public class DetailActivity extends BaseActivity implements OnloadDetailImageListener {

    private Toolbar toolbar;
    private ImageView imageView;
    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ProgressBar progressBar;
    private TextView detailTitleTx, detailUpdateTimeTx, detailUserNameTx, detailUserLocationTx;
    private ImageView detailAvatarIm, detailLikesIm, detailCommentsIm, detailBucketsIm, detailViewsIm;
    private TextView  detailLikesCountTx, detailCommentsCountTx, detailBucketsCountTx, detailViewsCountTx;
    private Button detailAttachmentsBt;
    private TextView detailDescTx;
    private Shots shots;
    private String htmlUrl, hidpiUrl, imageUrl, imageName;
    private HashMap<String, String> map = new HashMap<>();

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //获取序列化对象
        shots = (Shots) getIntent().getSerializableExtra("shots");
        htmlUrl = shots.getHtml_url();
        imageName = shots.getTitle();
        hidpiUrl = shots.getImages().getHidpi();
        if(hidpiUrl != null) {
            imageUrl = hidpiUrl;
        }else {
            imageUrl = shots.getImages().getNormal();
        }
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);
        imageView = (ImageView)findViewById(R.id.detail_im);
        toolbar = (Toolbar)findViewById(R.id.detail_tb);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        toolbar.setTitle(shots.getTitle());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.this.finish();
            }
        });
        detailLikesIm = (ImageView)findViewById(R.id.detail_likes_im);
        detailLikesIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put(Constants.ID, String.valueOf(shots.getId()));
                HttpUtils.getJson(map, Constants.REQUEST_LIKE_A_SHOT, Constants.REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD);
                detailLikesIm.setImageResource(R.drawable.ic_favorite_red_600_36dp);
            }
        });
        new CheckIfLikeShotTask().execute(String.valueOf(shots.getId()));
        ImageLoader.loadDetailImage(getApplicationContext(), imageUrl, imageView, this);
        initDescription();
    }

    private void initDescription(){
        detailTitleTx = (TextView)findViewById(R.id.detail_title_tx);
        detailTitleTx.setText(shots.getTitle());
        detailUpdateTimeTx = (TextView)findViewById(R.id.detail_update_time_tx);
        detailUpdateTimeTx.setText(TimeUtil.getTimeDifference(shots.getUpdated_at()));
        detailAvatarIm = (ImageView)findViewById(R.id.detail_avatar_im);
        ImageLoader.loadCricleImage(getApplicationContext(), shots.getUser().getAvatar_url(), detailAvatarIm);
        detailUserNameTx = (TextView)findViewById(R.id.detail_user_name_tx);
        detailUserNameTx.setText(shots.getUser().getUsername());
        detailUserLocationTx = (TextView)findViewById(R.id.detail_user_location_tx);
        detailUserLocationTx.setText(shots.getUser().getLocation());
        detailLikesCountTx = (TextView)findViewById(R.id.detail_likes_count_tx);
        detailLikesCountTx.setText(TextUtil.setBeforeBold(String.valueOf(shots.getLikes_count()), getResources().getString(R.string.detail_likes_tx)));
        detailCommentsIm = (ImageView)findViewById(R.id.detail_comments_im);
        detailCommentsIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, CommentsActivity.class);
                intent.putExtra(Constants.REQUEST_COMMENTS, String.valueOf(shots.getId()));
                startActivity(intent);
            }
        });
        detailCommentsCountTx= (TextView)findViewById(R.id.detail_comments_tx);
        detailCommentsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(shots.getComments_count()), getResources().getString(R.string.detail_comments_tx)));
        detailBucketsIm = (ImageView)findViewById(R.id.detail_buckets_im);
        detailBucketsCountTx = (TextView)findViewById(R.id.detail_buckets_count_tx);
        detailBucketsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(shots.getBuckets_count()), getResources().getString(R.string.detail_buckets_tx)));
        detailViewsCountTx = (TextView)findViewById(R.id.detail_views_count_tx);
        detailViewsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(shots.getViews_count()), getResources().getString(R.string.detail_views_tx)));
        detailAttachmentsBt = (Button)findViewById(R.id.detail_attachment_bt);
        if(shots.getAttachments_count() != 0){
            detailAttachmentsBt.setVisibility(Button.VISIBLE);
            String attachments = new StringBuilder().append(String.valueOf(shots.getAttachments_count()))
                    .append(getResources().getString(R.string.detail_attachmets_bt_tx)).toString();
            detailAttachmentsBt.setText(attachments);
            detailAttachmentsBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        detailDescTx = (TextView)findViewById(R.id.detail_desc_tx);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            detailDescTx.setText(Html.fromHtml(shots.getDescription(), Html.FROM_HTML_MODE_LEGACY));
        }else {
            detailDescTx.setText(Html.fromHtml(shots.getDescription()));
        }
        detailDescTx.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                shareShots();
                break;
            case R.id.open_in_browser:
                openInBrowser();
                break;
            case R.id.download:
                download();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareShots(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = new StringBuilder().append(imageName).append("\n")
                .append(htmlUrl).append("\n")
                .append(getResources().getString(R.string.detail_toolbar_overflow_menu_share_footer_text))
                .toString();
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.detail_toolbar_overflow_menu_share_create_chooser_title)));
    }

    private void openInBrowser(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(htmlUrl);
        intent.setData(uri);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.detail_toolbar_overflow_menu_open_in_browser_create_chooser_title)));
        }
    }

    private void download(){
        ImageLoader.downloadImage(getApplicationContext(), coordinatorLayout, imageUrl, imageName);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void onFailure(String msg) {

    }

    class CheckIfLikeShotTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... id) {
            String likeJson = null;
            HashMap<String, String> map = new HashMap<>();
            map.put(Constants.SHOTS, id[0]);
            HttpUtils.getJson(map,Constants.REQUEST_NORMAL, Constants.REQUEST_CHECK_IF_LIKE_SHOT, Constants.REQUEST_GET_MEIHOD);
            return likeJson;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            if(json != null){
                detailLikesIm.setImageResource(R.drawable.ic_favorite_red_600_24dp);
            }else {
                detailLikesIm.setImageResource(R.drawable.ic_favorite_grey_600);
            }
        }
    }

}
