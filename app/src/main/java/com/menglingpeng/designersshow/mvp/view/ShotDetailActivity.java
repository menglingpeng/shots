package com.menglingpeng.designersshow.mvp.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.designersshow.BaseActivity;
import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.mvp.interf.OnloadDetailImageListener;
import com.menglingpeng.designersshow.mvp.model.AuthToken;
import com.menglingpeng.designersshow.mvp.model.Shot;
import com.menglingpeng.designersshow.mvp.model.User;
import com.menglingpeng.designersshow.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.designersshow.utils.Constants;
import com.menglingpeng.designersshow.utils.ImageLoader;
import com.menglingpeng.designersshow.utils.Json;
import com.menglingpeng.designersshow.utils.ShareAndOpenInBrowserUtil;
import com.menglingpeng.designersshow.utils.SharedPrefUtil;
import com.menglingpeng.designersshow.utils.SnackUI;
import com.menglingpeng.designersshow.utils.TextUtil;
import com.menglingpeng.designersshow.utils.TimeUtil;

import java.util.HashMap;

/**
 * Created by mengdroid on 2017/11/1.
 */

public class ShotDetailActivity extends BaseActivity implements OnloadDetailImageListener, com.menglingpeng
        .designersshow.mvp.interf.RecyclerView, LoginDialogFragment.LoginDialogListener {

    private Toolbar toolbar;
    private ImageView imageView;
    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ProgressBar progressBar;
    private TextView detailTitleTv;
    private TextView detailUpdateTimeTv;
    private TextView detailUserNameTv;
    private TextView detailUserLocationTv;
    private FrameLayout detailLikesFl;
    private FrameLayout detailCommentsFl;
    private FrameLayout detailBucketsFl;
    private ImageView detailAvatarIv;
    private ImageView detailLikesIv;
    private ImageView detailCommentsIv;
    private ImageView detailBucketsIv;
    private TextView detailLikesCountTv;
    private TextView detailCommentsCountTv;
    private TextView detailBucketsCountTv;
    private TextView detailViewsCountTv;
    private Button detailAttachmentsBt;
    private TextView detailDescTv;
    private Shot shot;
    private User user;
    private String htmlUrl;
    private String hidpiUrl;
    private String imageUrl;
    private String imageName;
    private HashMap<String, String> map = new HashMap<>();
    private RecyclerPresenter presenter;
    private Boolean shotsIsLiked = false;
    private String type;
    private Boolean isLogin;
    private Context context;
    private Button loginDialogLoginBt;
    private ProgressBar loginDialogPb;
    private Dialog loginDialog;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_shot_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //获取序列化对象
        shot = (Shot) getIntent().getSerializableExtra(Constants.SHOTS);
        type = getIntent().getStringExtra(Constants.TYPE);
        isLogin = SharedPrefUtil.getState(Constants.IS_LOGIN);
        context = getApplicationContext();
        if (type.equals(Constants.USER_SHOT_DETAIL)) {
            user = (User) getIntent().getSerializableExtra(Constants.USER);
        } else {
            user = shot.getUser();
        }
        htmlUrl = shot.getHtml_url();
        imageName = shot.getTitle();
        hidpiUrl = shot.getImages().getHidpi();
        if (hidpiUrl != null) {
            imageUrl = hidpiUrl;
        } else {
            imageUrl = shot.getImages().getNormal();
        }
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        imageView = (ImageView) findViewById(R.id.detail_iv);
        toolbar = (Toolbar) findViewById(R.id.detail_tb);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShotDetailActivity.this.finish();
            }
        });
        map.put(Constants.ID, String.valueOf(shot.getId()));
        map.put(Constants.ACCESS_TOKEN, SharedPrefUtil.getAuthToken());
        if(isLogin){
            checkIfLikeTheShot();
        }
        detailLikesIv = (ImageView) findViewById(R.id.detail_likes_iv);
        detailLikesFl = (FrameLayout)findViewById(R.id.detail_likes_fl);
        detailLikesFl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin) {
                    if (shotsIsLiked) {
                        unlikeTheShot();
                    } else {
                        likeTheShot();
                    }
                }else {
                    showLoginDialog();
                }
            }
        });
        ImageLoader.loadDetailImage(context, imageUrl, imageView, this);
        imageView.setAlpha(225);
        initDescription();
    }

    private void initDescription() {
        detailTitleTv = (TextView) findViewById(R.id.detail_title_tv);
        detailTitleTv.setText(shot.getTitle());
        detailUpdateTimeTv = (TextView) findViewById(R.id.detail_update_time_tv);
        detailUpdateTimeTv.setText(TimeUtil.getTimeDifference(shot.getUpdated_at()));
        detailAvatarIv = (ImageView) findViewById(R.id.detail_avatar_iv);
        detailUserNameTv = (TextView) findViewById(R.id.detail_user_name_tv);
        detailUserLocationTv = (TextView) findViewById(R.id.detail_user_location_tv);
        ImageLoader.loadCricleImage(context, user.getAvatar_url(), detailAvatarIv);
        detailAvatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin) {
                    Intent intent = new Intent(ShotDetailActivity.this, UserProfileActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.REQUEST_SINGLE_USER);
                    intent.putExtra(Constants.ID, String.valueOf(user.getId()));
                    startActivity(intent);
                }else {
                    showLoginDialog();
                }
            }
        });
        detailUserNameTv.setText(user.getName());
        detailUserLocationTv.setText(user.getLocation());
        detailLikesCountTv = (TextView) findViewById(R.id.detail_likes_count_tv);
        detailLikesCountTv.setText(TextUtil.setBeforeBold(String.valueOf(shot.getLikes_count()), getResources()
                .getString(R.string.detail_likes_tv_text)));
        detailCommentsFl = (FrameLayout)findViewById(R.id.detail_comments_fl);
        detailCommentsIv = (ImageView) findViewById(R.id.detail_comments_iv);
        detailCommentsFl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin) {
                    Intent intent = new Intent(ShotDetailActivity.this, ShotCommentsActivity.class);
                    intent.putExtra(Constants.SHOT_ID, String.valueOf(shot.getId()));
                    intent.putExtra(Constants.COMMENTS_COUNT, String.valueOf(shot.getComments_count()));
                    intent.putExtra(Constants.USER_NAME, shot.getUser().getName());
                    startActivity(intent);
                }else {
                    showLoginDialog();
                }
            }
        });
        detailCommentsCountTv = (TextView) findViewById(R.id.detail_comments_tv);
        detailCommentsCountTv.setText(TextUtil.setBeforeBold(String.valueOf(shot.getComments_count()), getResources
                ().getString(R.string.detail_comments_tv_text)));
        detailBucketsFl = (FrameLayout) findViewById(R.id.detail_buckets_fl);
        detailBucketsFl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin) {
                    Intent intent = new Intent(ShotDetailActivity.this, ChooseBucketActivity.class);
                    intent.putExtra(Constants.SHOT_ID, String.valueOf(shot.getId()));
                    startActivity(intent);
                }else {
                    showLoginDialog();
                }
            }
        });
        detailBucketsIv = (ImageView) findViewById(R.id.detail_buckets_iv);
        detailBucketsCountTv = (TextView) findViewById(R.id.detail_buckets_count_tv);
        detailBucketsCountTv.setText(TextUtil.setBeforeBold(String.valueOf(shot.getBuckets_count()), getResources()
                .getString(R.string.detail_buckets_tv_text)));
        detailViewsCountTv = (TextView) findViewById(R.id.detail_views_count_tv);
        detailViewsCountTv.setText(TextUtil.setBeforeBold(String.valueOf(shot.getViews_count()), getResources()
                .getString(R.string.detail_views_tv_text)));
        detailAttachmentsBt = (Button) findViewById(R.id.detail_attachment_bt);
        if (shot.getAttachments_count() != 0) {
            detailAttachmentsBt.setVisibility(Button.VISIBLE);
            String attachments = new StringBuilder().append(String.valueOf(shot.getAttachments_count()))
                    .append(getResources().getString(R.string.detail_attachmets_bt_text)).toString();
            detailAttachmentsBt.setText(attachments);
            detailAttachmentsBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isLogin){
                        AttachmentsDialogFragment dialogFragment = AttachmentsDialogFragment.newInstance(String.valueOf(shot.getId()));
                        dialogFragment.show(getSupportFragmentManager(), Constants.ATTACHMENTS_DIALOG_FRAGMENT_TAG);
                    }else {
                        showLoginDialog();
                    }
                }
            });
        }

        detailDescTv = (TextView) findViewById(R.id.detail_desc_tv);
        //处理HTML文本
        TextUtil.setHtmlText(detailDescTv, shot.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                shareShots();
                break;
            case R.id.open_in_browser:
                ShareAndOpenInBrowserUtil.openInBrowser(context, htmlUrl);
                break;
            case R.id.download:
                download();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareShots() {
        String text = new StringBuilder().append(imageName).append("\n")
                .append(htmlUrl).append("\n")
                .append(getResources().getString(R.string.detail_toolbar_overflow_menu_share_footer_text))
                .toString();
        ShareAndOpenInBrowserUtil.share(context, text);
    }


    private void download() {
        ImageLoader.downloadImage(getApplicationContext(), coordinatorLayout, imageUrl, imageName);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getStringExtra(Constants.SNACKBAR_TEXT) != null) {
            SnackUI.showSnackShort(getApplicationContext(), coordinatorLayout, intent.getStringExtra(Constants

                    .SNACKBAR_TEXT));
        }
        if(intent.getData() != null){
            HashMap<String, String> map = new HashMap<>();
            loginDialogLoginBt.setVisibility(Button.GONE);
            loginDialogPb.setVisibility(ProgressBar.VISIBLE);
            Uri uri = intent.getData();
            //接收传递过来URL中的参数code
            String code = uri.getQuery();
            map.put(Constants.CLIENT_ID, Constants.CLIENT_ID_VALUE);
            map.put(Constants.CLIENT_SECRET, Constants.CLIENT_SECRET_VALUE);
            //code字符串前面带有code=,需要去掉。
            map.put(Constants.CODE, code.replace("code=", ""));
            type = Constants.REQUEST_AUTH_TOKEN;
            RecyclerPresenter presenter = new RecyclerPresenter(this, type, Constants.REQUEST_NORMAL, Constants
                    .REQUEST_POST_MEIHOD, map, this);
            presenter.loadJson();
        }
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {
    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (type) {
            case Constants.REQUEST_LIKE_A_SHOT:
                detailLikesIv.setImageResource(R.drawable.ic_favorite_red_600_24dp);
                detailLikesCountTv.setText(TextUtil.setBeforeBold(String.valueOf(shot.getLikes_count() + 1),
                        getResources().getString(R.string.detail_likes_tv_text)));
                SnackUI.showSnackShort(context, coordinatorLayout, getResources().getString(R.string
                        .detail_likes_im_like_a_shot_snack_text));
                break;
            case Constants.REQUEST_CHECK_IF_LIKE_SHOT:
                if (!json.equals(Constants.CODE_404_NOT_FOUND)) {
                    setShotIsLiked(true);
                    detailLikesIv.setImageResource(R.drawable.ic_favorite_red_600_24dp);
                }
                break;
            case Constants.REQUEST_UNLIKE_A_SHOT:
                if (json.equals(Constants.CODE_204_NO_CONTENT)) {
                    detailLikesIv.setImageResource(R.drawable.ic_favorite_grey_600);
                    detailLikesCountTv.setText(TextUtil.setBeforeBold(String.valueOf(shot.getLikes_count() - 1),
                            getResources().getString(R.string.detail_likes_tv_text)));
                    SnackUI.showSnackShort(context, coordinatorLayout, getResources().getString(R
                            .string.detail_likes_im_unlike_a_shot_snack_text));
                    setShotIsLiked(false);
                }
                break;
            case Constants.REQUEST_AUTH_TOKEN:
                loginDialogPb.setVisibility(ProgressBar.VISIBLE);
                if (json.indexOf("error") != -1) {

                } else {
                    AuthToken authToken = Json.parseJson(json, AuthToken.class);
                    Log.i("authToken", authToken.getAccess_token());
                    map.put(Constants.ACCESS_TOKEN, authToken.getAccess_token());
                    SharedPrefUtil.saveParameters(map);
                    SharedPrefUtil.saveState(Constants.IS_LOGIN, true);
                    isLogin = true;
                    type = Constants.REQUEST_AUTH_USER;
                    RecyclerPresenter presenter = new RecyclerPresenter(this, type, Constants.REQUEST_NORMAL,
                            Constants.REQUEST_GET_MEIHOD, map, this);
                    presenter.loadJson();
                }
                break;
            case Constants.REQUEST_AUTH_USER:
                User authUser = Json.parseJson(json, User.class);
                map.put(Constants.AUTH_USER_AVATAR_URL, authUser.getAvatar_url());
                map.put(Constants.AUTH_USER_NAME, authUser.getUsername());
                map.put(Constants.AUTH_USER_ID, String.valueOf(authUser.getId()));
                SharedPrefUtil.saveParameters(map);
                loginDialog.cancel();
                break;
            default:
                break;
        }
    }

    private void setShotIsLiked(Boolean isLiked) {
        shotsIsLiked = isLiked;
    }

    private void checkIfLikeTheShot() {
        type = Constants.REQUEST_CHECK_IF_LIKE_SHOT;
        presenter = new RecyclerPresenter(ShotDetailActivity.this, type, Constants.REQUEST_NORMAL, Constants
                .REQUEST_GET_MEIHOD, map, context);
        presenter.loadJson();
    }

    private void likeTheShot() {
        type = Constants.REQUEST_LIKE_A_SHOT;
        presenter = new RecyclerPresenter(ShotDetailActivity.this, type, Constants.REQUEST_NORMAL, Constants
                .REQUEST_POST_MEIHOD, map, context);
        presenter.loadJson();
    }

    private void unlikeTheShot() {
        type = Constants.REQUEST_UNLIKE_A_SHOT;
        presenter = new RecyclerPresenter(ShotDetailActivity.this, type, Constants.REQUEST_NORMAL, Constants
                .REQUEST_DELETE_MEIHOD, map, context);
        presenter.loadJson();

    }

    private void showLoginDialog(){
        LoginDialogFragment dialogFragment = new LoginDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), Constants.LOGIN_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void onLoginDialogLoginListener(Button button, ProgressBar progressBar, Dialog dialog) {
        loginDialogPb = progressBar;
        loginDialogLoginBt = button;
        loginDialog = dialog;
    }
}
