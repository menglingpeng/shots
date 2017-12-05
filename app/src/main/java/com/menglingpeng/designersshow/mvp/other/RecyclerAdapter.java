package com.menglingpeng.designersshow.mvp.other;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.mvp.interf.OnRecyclerListItemListener;
import com.menglingpeng.designersshow.mvp.model.Buckets;
import com.menglingpeng.designersshow.mvp.model.Comments;
import com.menglingpeng.designersshow.mvp.model.Follower;
import com.menglingpeng.designersshow.mvp.model.Following;
import com.menglingpeng.designersshow.mvp.model.Shots;
import com.menglingpeng.designersshow.mvp.model.User;
import com.menglingpeng.designersshow.mvp.view.UserProfileActivity;
import com.menglingpeng.designersshow.mvp.view.UserFollowingActivity;
import com.menglingpeng.designersshow.utils.Constants;
import com.menglingpeng.designersshow.utils.ImageLoader;
import com.menglingpeng.designersshow.utils.TextUtil;
import com.menglingpeng.designersshow.utils.TimeUtil;

import java.util.ArrayList;

/**
 * Created by mengdroid on 2017/10/19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_ITEM_FOOTER = 1;
    private static final int TYPE_ITEM_EMPTY = 2;
    private OnRecyclerListItemListener mListener;
    private ArrayList list = new ArrayList<>();
    private Fragment fragment;
    private Context context;
    private String type;
    private boolean isLoading;
    onLoadingMore loadingMore;
    //加载更多的提前量
    private int visibleThreshold = 2;


    public  RecyclerAdapter(RecyclerView recyclerView, Context context, Fragment fragment, final String type, OnRecyclerListItemListener listener){
        this.type = type;
        this.context = context;
        this.fragment = fragment;
        mListener = listener;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                int itemcount = layoutManager.getItemCount();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                if(itemcount >= Constants.PER_PAGE_VALUE) {
                    if (!isLoading && lastPosition >= (itemcount - visibleThreshold)) {
                        if (loadingMore != null) {
                            isLoading = true;
                            loadingMore.onLoadMore();
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int cout = 0;
        if(list.isEmpty()){
                Log.i("empty", "true");
                cout = 1;
                return cout;
        }else {
            if (list.size() < 12) {
                cout = list.size();
            } else {
                cout = list.size() + 1;
            }
        }
        return cout;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if(list.isEmpty()){
            type = TYPE_ITEM_EMPTY;
        }else {
            if (position == list.size()) {
                type = TYPE_ITEM_FOOTER;
            } else {
                type = TYPE_ITEM;
            }
        }
        return type;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case TYPE_ITEM_EMPTY:
                view = inflater.inflate(R.layout.recycler_item_empty, parent, false);
                viewHolder = new EmptyiewHolder(view);
                break;
            case TYPE_ITEM_FOOTER:
                view = inflater.inflate(R.layout.recycler_item_footer_loading, parent, false);
                viewHolder = new FooterViewHolder(view);
                break;
            default:
                switch (type) {
                    case Constants.REQUEST_LIST_COMMENTS:
                        view = inflater.inflate(R.layout.comments_recycler_view_item, parent, false);
                        viewHolder = new CommentViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_BUCKETS_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.buckets_recycler_item, parent, false);
                        viewHolder = new BucketsViewHolder(view);
                        break;
                    case Constants.REQUEST_CHOOSE_BUCKET:
                        view = inflater.inflate(R.layout.buckets_recycler_item, parent, false);
                        viewHolder = new ChooseBucketViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_DETAIL_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_detail_item, parent, false);
                        viewHolder = new DetailOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_SHOTS_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_shots_item, parent, false);
                        viewHolder = new ProfileShotViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWERS_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_follow_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_follow_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_DETAIL_FOR_A_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_detail_item, parent, false);
                        viewHolder = new DetailOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_SHOTS_FOR_A_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_shots_item, parent, false);
                        viewHolder = new ProfileShotViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWERS_FOR_A_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_follow_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWING_FOR_A_USER:
                        view = inflater.inflate(R.layout.profile_tablayout_follow_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    default:
                        view = inflater.inflate(R.layout.recycler_item, parent, false);
                        viewHolder = new ShotViewHolder(view);
                        break;
                }
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ShotViewHolder){
            final ShotViewHolder viewHolder = (ShotViewHolder)holder;
            final Shots shot = (Shots)list.get(position);
            boolean isGif = shot.isAnimated();
            int attachmentsCount = shot.getAttachments_count();
            ImageLoader.loadCricleImage(fragment, shot.getUser().getAvatar_url(), viewHolder.avatarIv);
            viewHolder.avatarIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(context, UserProfileActivity.class);
                intent.putExtra(Constants.TYPE, Constants.REQUEST_SINGLE_USER);
                intent.putExtra(Constants.ID, String.valueOf(shot.getUser().getId()));
                context.startActivity(intent);
                }
            });
            viewHolder.shotTitleTx.setText(shot.getTitle());
            viewHolder.shotUserNameTx.setText(shot.getUser().getUsername());
            viewHolder.shotCreatedTimeTx.setText(TimeUtil.getTimeDifference(shot.getUpdated_at()));
            ImageLoader.load(fragment, shot.getImages().getNormal(), viewHolder.shotIm, false);
            if(isGif){
                viewHolder.shotGifIm.setVisibility(TextView.VISIBLE);
            }
            viewHolder.shotLikesCountTx.setText(String.valueOf(shot.getLikes_count()));
            viewHolder.shotCommentsCountTx.setText(String.valueOf(shot.getComments_count()));
            viewHolder.shotViewsCountTx.setText(String.valueOf(shot.getViews_count()));
            if(attachmentsCount !=0){
                viewHolder.shotAttachmentsCountIm.setVisibility(ImageView.VISIBLE);
                viewHolder.shotAttachmentsCountTx.setVisibility(TextView.VISIBLE);
                viewHolder.shotAttachmentsCountTx.setText(String.valueOf(attachmentsCount));
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onRecyclerFragmentListListener(viewHolder, shot);
                    }
                }
            });
        }else if(holder instanceof CommentViewHolder){
            CommentViewHolder viewHolder = (CommentViewHolder)holder;
            Comments comment = (Comments)list.get(position);
            ImageLoader.loadCricleImage(context, comment.getUser().getAvatar_url(), viewHolder.commentsAvatarIm);
            viewHolder.commentsContentTv.setText(comment.getBody());
            viewHolder.commentsUsernameTv.setText(comment.getUser().getUsername());
            viewHolder.commentsCreateAtTv.setText(comment.getCreated_at());
            viewHolder.commentsLikesCountTv.setText(String.valueOf(comment.getLikes_count()));
        }else if(holder instanceof BucketsViewHolder){
            final BucketsViewHolder viewHolder = (BucketsViewHolder)holder;
            final Buckets buckets = (Buckets)list.get(position);
            viewHolder.bucketRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onRecyclerFragmentListListener(viewHolder, buckets);
                    }
                }
            });
            viewHolder.bucketNameTx.setText(buckets.getName());
            if(buckets.getDescription() != null) {
                viewHolder.bucketDescTx.setText(buckets.getDescription());
            }else {
                viewHolder.bucketDescTx.setVisibility(TextView.GONE);
            }
            viewHolder.bucketShotsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(buckets.getShots_count()), context.getString(R.string.explore_spinner_list_shots)));
        }else if(holder instanceof ChooseBucketViewHolder){
            final ChooseBucketViewHolder viewHolder = (ChooseBucketViewHolder)holder;
            final Buckets buckets = (Buckets)list.get(position);
            viewHolder.bucketRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onRecyclerFragmentListListener(viewHolder, buckets);
                    }
                }
            });
            viewHolder.bucketNameTx.setText(buckets.getName());
            if(buckets.getDescription() != null) {
                viewHolder.bucketDescTx.setText(buckets.getDescription());
            }else {
                viewHolder.bucketDescTx.setVisibility(TextView.GONE);
            }
            viewHolder.bucketShotsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(buckets.getShots_count()), context.getString(R.string.explore_spinner_list_shots)));
        }else if(holder instanceof FollowOfUserViewHolder){
            final FollowOfUserViewHolder viewHolder = (FollowOfUserViewHolder)holder;
            User user;
            if(type.indexOf(Constants.FOLLOWING) != -1){
                final Following following = (Following)list.get(position);
                user = following.getFollowee();
            }else {
                final Follower follower = (Follower) list.get(position);
                user = follower.getFollower();
            }
            final User userFollow = user;
            ImageLoader.loadCricleImage(context, userFollow.getAvatar_url(), viewHolder.followerAvatarIm);
            viewHolder.followerNameTx.setText(userFollow.getUsername());
            viewHolder.followerLocationTx.setText(userFollow.getLocation());
            viewHolder.followerShotsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(userFollow.getShots_count()), context.getString(R.string.explore_spinner_list_shots)));
            viewHolder.followersOfFollowerCountTx.setText(TextUtil.setBeforeBold(String.valueOf(userFollow.getFollowers_count()), context.getText(R.string.followers).toString()));
            viewHolder.followerRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onRecyclerFragmentListListener(viewHolder, String.valueOf(userFollow.getId()));
                    }
                }
            });
        }else if(holder instanceof DetailOfUserViewHolder){
            final DetailOfUserViewHolder viewHolder = (DetailOfUserViewHolder)holder;
            final User user = (User)list.get(position);
            viewHolder.profileTablayoutDetailShotsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(user.getShots_count()), context.getString(R.string.explore_spinner_list_shots)));
            viewHolder.profileTablayoutDetailLikesCountTx.setText(TextUtil.setBeforeBold(String.valueOf(user.getLikes_count()), context.getString(R.string.detail_likes_tx)));
            viewHolder.profileTablayoutDetailBucketsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(user.getBuckets_count()), context.getString(R.string.detail_buckets_tx)));
            viewHolder.profileTablayoutDetailProjectsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(user.getProject_count()), context.getString(R.string.project)));
            viewHolder.profileTablayoutDetailFollowersCountTx.setText(TextUtil.setBeforeBold(String.valueOf(user.getFollowers_count()), context.getString(R.string.followers)));
            viewHolder.profileTablayoutDetailFollowingsCountTx.setText(TextUtil.setBeforeBold(String.valueOf(user.getFollowing_count()), context.getString(R.string.following)));
            viewHolder.profileTablayoutDetailFollowingsRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UserFollowingActivity.class);
                    intent.putExtra(Constants.NAME, user.getUsername());
                    if(type.equals(Constants.REQUEST_LIST_DETAIL_FOR_AUTH_USER)) {
                        intent.putExtra(Constants.TYPE, Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER);
                    }else {
                        intent.putExtra(Constants.TYPE, Constants.REQUEST_LIST_FOLLOWING_FOR_A_USER);
                        intent.putExtra(Constants.ID, String.valueOf(user.getId()));
                    }
                    context.startActivity(intent);
                }
            });
        }else if(holder instanceof ProfileShotViewHolder){
            final ProfileShotViewHolder viewHolder = (ProfileShotViewHolder)holder;
            final Shots shot = (Shots)list.get(position);
            boolean isGif = shot.isAnimated();
            int attachmentsCount = shot.getAttachments_count();
            viewHolder.profileShotTitleTx.setText(shot.getTitle());
            viewHolder.profileShotCreatedTimeTx.setText(TimeUtil.getTimeDifference(shot.getUpdated_at()));
            ImageLoader.load(fragment, shot.getImages().getNormal(), viewHolder.profileShotIm, false);
            if(isGif){
                viewHolder.profileShotGifIm.setVisibility(TextView.VISIBLE);
            }
            viewHolder.profileShotLikesCountTx.setText(String.valueOf(shot.getLikes_count()));
            viewHolder.profileShotCommentsCountTx.setText(String.valueOf(shot.getComments_count()));
            viewHolder.profileShotViewsCountTx.setText(String.valueOf(shot.getViews_count()));
            if(attachmentsCount !=0){
                viewHolder.profileShotAttachmentsCountIm.setVisibility(ImageView.VISIBLE);
                viewHolder.profileShotAttachmentsCountTx.setVisibility(TextView.VISIBLE);
                viewHolder.profileShotAttachmentsCountTx.setText(String.valueOf(attachmentsCount));
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onRecyclerFragmentListListener(viewHolder, shot);
                    }
                }
            });
        } else if(holder instanceof EmptyiewHolder){
            int imId = 0;
            int txId = 0;
            EmptyiewHolder viewHolder = (EmptyiewHolder)holder;
            switch (type){
                case Constants.REQUEST_LIST_BUCKETS_FOR_AUTH_USER:
                    imId = R.drawable.ic_image_grey_400_48dp;
                    txId = R.string.no_bucket_here;
                    break;
                case Constants.REQUEST_LIST_SHOTS_FOR_AUTH_USER:
                    imId = R.drawable.ic_image_grey_400_48dp;
                    txId = R.string.no_shot_here;
                    break;
                case Constants.REQUEST_LIST_LIKES_FOR_AUTH_USER:
                    imId = R.drawable.ic_image_grey_400_48dp;
                    txId = R.string.no_liked_shot_here;
                    break;
                case Constants.REQUEST_LIST_FOLLOWERS_FOR_AUTH_USER:
                    imId = R.drawable.ic_image_grey_400_48dp;
                    txId = R.string.no_follower_here;
                    break;
                case Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER:
                    imId = R.drawable.ic_image_grey_400_48dp;
                    txId = R.string.no_following_here;
                    break;
            }
            viewHolder.emptyIm.setImageResource(imId);
            viewHolder.emptyTx.setText(context.getString(txId));
        }
    }

    public class ShotViewHolder extends RecyclerView.ViewHolder {
        public final ImageView avatarIv;
        public final TextView shotTitleTx, shotUserNameTx, shotCreatedTimeTx;
        public final ImageView shotIm, shotGifIm, shotAttachmentsCountIm;
        public final TextView shotLikesCountTx, shotCommentsCountTx, shotViewsCountTx, shotAttachmentsCountTx;
        public ShotViewHolder(View view) {
            super(view);
            avatarIv = (ImageView) view.findViewById(R.id.avatar_im);
            shotTitleTx = (TextView) view.findViewById(R.id.shot_title_tx);
            shotUserNameTx = (TextView) view.findViewById(R.id.shot_user_name_tx);
            shotCreatedTimeTx = (TextView) view.findViewById(R.id.shot_create_time_tx);
            shotIm = (ImageView) view.findViewById(R.id.shot_im);
            shotGifIm = (ImageView) view.findViewById(R.id.shot_gif_im);
            shotLikesCountTx = (TextView) view.findViewById(R.id.shot_likes_count_tx);
            shotCommentsCountTx = (TextView) view.findViewById(R.id.shot_comments_count_tx);
            shotViewsCountTx = (TextView) view.findViewById(R.id.shot_views_count_tx);
            shotAttachmentsCountIm = (ImageView)view.findViewById(R.id.shot_attachments_count_im);
            shotAttachmentsCountTx = (TextView)view.findViewById(R.id.shot_attachments_count_tx);
        }
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        public final  ImageView commentsAvatarIm, commentsLikesIm;
        public final  TextView commentsUsernameTv, commentsContentTv, commentsCreateAtTv
                ,commentsLikesCountTv;
        public CommentViewHolder(View view) {
            super(view);
            commentsAvatarIm = (ImageView)view.findViewById(R.id.detail_comments_avatar_im);
            commentsUsernameTv= (TextView)view.findViewById(R.id.detail_comments_username_tx);
            commentsContentTv = (TextView)view.findViewById(R.id.detail_comments_content_tx);
            commentsCreateAtTv = (TextView)view.findViewById(R.id.detail_comments_create_at_tx);
            commentsLikesIm = (ImageView)view.findViewById(R.id.detail_comments_likes_im);
            commentsLikesCountTv = (TextView)view.findViewById(R.id.detail_comments_likes_count_tx);
        }
    }

    public class DetailOfUserViewHolder extends RecyclerView.ViewHolder{
        public final RelativeLayout profileTablayoutDetailShotsRl, profileTablayoutDetailLikesRl, profileTablayoutDetailBucketsRl,
                profileTablayoutDetailProjectsRl, profileTablayoutDetailFollowersRl, profileTablayoutDetailFollowingsRl;
        public final TextView profileTablayoutDetailShotsCountTx, profileTablayoutDetailLikesCountTx, profileTablayoutDetailBucketsCountTx,
                profileTablayoutDetailProjectsCountTx, profileTablayoutDetailFollowersCountTx, profileTablayoutDetailFollowingsCountTx;
        public DetailOfUserViewHolder(View view) {
            super(view);
            profileTablayoutDetailShotsRl = (RelativeLayout)view.findViewById(R.id.profile_tablayout_detail_shots_rl);
            profileTablayoutDetailLikesRl = (RelativeLayout)view.findViewById(R.id.profile_tablayout_detail_likes_rl);
            profileTablayoutDetailBucketsRl = (RelativeLayout)view.findViewById(R.id.profile_tablayout_detail_buckets_rl);
            profileTablayoutDetailProjectsRl = (RelativeLayout)view.findViewById(R.id.profile_tablayout_detail_projects_rl);
            profileTablayoutDetailFollowersRl = (RelativeLayout)view.findViewById(R.id.profile_tablayout_detail_followers_rl);
            profileTablayoutDetailFollowingsRl = (RelativeLayout)view.findViewById(R.id.profile_tablayout_detail_followings_rl);
            profileTablayoutDetailShotsCountTx = (TextView)view.findViewById(R.id.profile_tablayout_detail_shots_count_tx);
            profileTablayoutDetailLikesCountTx = (TextView)view.findViewById(R.id.profile_tablayout_detail_likes_count_tx);
            profileTablayoutDetailBucketsCountTx = (TextView)view.findViewById(R.id.profile_tablayout_detail_buckets_count_tx);
            profileTablayoutDetailProjectsCountTx = (TextView)view.findViewById(R.id.profile_tablayout_detail_projects_count_tx);
            profileTablayoutDetailFollowersCountTx = (TextView)view.findViewById(R.id.profile_tablayout_detail_followers_count_tx);
            profileTablayoutDetailFollowingsCountTx = (TextView)view.findViewById(R.id.profile_tablayout_detail_followings_count_tx);


        }
    }

    public class ProfileShotViewHolder extends RecyclerView.ViewHolder{
        public final TextView profileShotTitleTx, profileShotCreatedTimeTx;
        public final ImageView profileShotIm, profileShotGifIm, profileShotAttachmentsCountIm;
        public final TextView profileShotLikesCountTx, profileShotCommentsCountTx, profileShotViewsCountTx, profileShotAttachmentsCountTx;
        public ProfileShotViewHolder(View view) {
            super(view);
            profileShotTitleTx = (TextView) view.findViewById(R.id.profile_shot_title_tx);
            profileShotCreatedTimeTx = (TextView) view.findViewById(R.id.profile_shot_create_time_tx);
            profileShotIm = (ImageView) view.findViewById(R.id.profile_shot_im);
            profileShotGifIm = (ImageView) view.findViewById(R.id.profile_shot_gif_im);
            profileShotLikesCountTx = (TextView) view.findViewById(R.id.profile_shot_likes_count_tx);
            profileShotCommentsCountTx = (TextView) view.findViewById(R.id.profile_shot_comments_count_tx);
            profileShotViewsCountTx = (TextView) view.findViewById(R.id.profile_shot_views_count_tx);
            profileShotAttachmentsCountIm = (ImageView)view.findViewById(R.id.profile_shot_attachments_count_im);
            profileShotAttachmentsCountTx = (TextView)view.findViewById(R.id.profile_shot_attachments_count_tx);
        }
    }

    public class FollowOfUserViewHolder extends RecyclerView.ViewHolder{
        public final RelativeLayout followerRl;
        public final ImageView followerAvatarIm;
        public final TextView followerNameTx, followerLocationTx, followerShotsCountTx, followersOfFollowerCountTx;

        public FollowOfUserViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout)view.findViewById(R.id.profile_tablayout_follow_rl);
            followerAvatarIm = (ImageView)view.findViewById(R.id.profile_tablayout_follow_avatar_im);
            followerLocationTx = (TextView)view.findViewById(R.id.profile_tablayout_follow_location_tx);
            followerNameTx = (TextView)view.findViewById(R.id.profile_tablayout_follow_name_tx);
            followerShotsCountTx = (TextView)view.findViewById(R.id.profile_tablayout_follow_shots_count_tx);
            followersOfFollowerCountTx = (TextView)view.findViewById(R.id.profile_tablayout_followers_of_follow_count_tx);
        }
    }

    public class BucketsViewHolder extends RecyclerView.ViewHolder{
        public final RelativeLayout bucketRl;
        public final TextView bucketNameTx, bucketDescTx, bucketShotsCountTx;

        public BucketsViewHolder(View view) {
            super(view);
            bucketRl = (RelativeLayout)view.findViewById(R.id.bucket_rl);
            bucketNameTx = (TextView)view.findViewById(R.id.bucket_name_tx);
            bucketDescTx = (TextView)view.findViewById(R.id.bucket_desc_tx);
            bucketShotsCountTx = (TextView)view.findViewById(R.id.bucket_shots_count_tx);
        }
    }
    public class ChooseBucketViewHolder extends RecyclerView.ViewHolder{
        public final RelativeLayout bucketRl;
        public final TextView bucketNameTx, bucketDescTx, bucketShotsCountTx;
        public final FloatingActionButton chooseBucketFab;

        public ChooseBucketViewHolder(View view) {
            super(view);
            bucketRl = (RelativeLayout)view.findViewById(R.id.bucket_rl);
            bucketNameTx = (TextView)view.findViewById(R.id.bucket_name_tx);
            bucketDescTx = (TextView)view.findViewById(R.id.bucket_desc_tx);
            bucketShotsCountTx = (TextView)view.findViewById(R.id.bucket_shots_count_tx);
            chooseBucketFab = (FloatingActionButton)view.findViewById(R.id.buckets_recycer_item_fab);
        }
    }

    public class EmptyiewHolder extends RecyclerView.ViewHolder{
        public final ImageView emptyIm;
        public final TextView emptyTx;

        public EmptyiewHolder(View view) {
            super(view);
            emptyIm = (ImageView)view.findViewById(R.id.recycler_item_empty_im);
            emptyTx = (TextView)view.findViewById(R.id.recycler_item_empty_tx);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View view) {
            super(view);
        }
    }

    public <T> void addData(T d){
        list.add(d);
        notifyDataSetChanged();
    }


    public void setLoading(boolean isLoading){
        this.isLoading = isLoading;
    }

    public void setLoadingMore(onLoadingMore loadingMore){
        this.loadingMore = loadingMore;
    }

    public interface onLoadingMore{
        void onLoadMore();
    }

}
