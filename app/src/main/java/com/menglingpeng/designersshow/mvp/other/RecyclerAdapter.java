package com.menglingpeng.designersshow.mvp.other;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.mvp.interf.OnRecyclerListItemListener;
import com.menglingpeng.designersshow.mvp.model.Shots;
import com.menglingpeng.designersshow.utils.Json;
import com.menglingpeng.designersshow.utils.ImageLoader;
import com.menglingpeng.designersshow.utils.TimeUtil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mengdroid on 2017/10/19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private OnRecyclerListItemListener mListener;
    private ArrayList<Shots> shotses;
    private Fragment fragment;


    public RecyclerAdapter(Fragment fragment, String shotsJson, String type, OnRecyclerListItemListener listener){
        this.fragment = fragment;
        mListener = listener;
        shotses = Json.parseShots(shotsJson);
        Log.i("type", type);
        Log.i("ShotsJson", shotses.get(0).getTitle());
    }

    @Override
    public int getItemViewType(int position) {
        if(position == shotses.size()){
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == TYPE_FOOTER){
            view = inflater.inflate(R.layout.recycler_item_footer_loading, parent, false);
            return new FooterViewHolder(view);
        }else {
            view = inflater.inflate(R.layout.recycler_item, parent, false);
            return  new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewHolder){
            final ViewHolder viewHolder = (ViewHolder)holder;
            boolean isGif = shotses.get(position).isAnimated();
            ImageLoader.loadCricleImage(fragment, shotses.get(position).getUser().getAvatar_url(), viewHolder.avatarIv);
            viewHolder.shotsTitleTx.setText(shotses.get(position).getTitle());
            viewHolder.shots_userTx.setText(shotses.get(position).getUser().getUsername());
            viewHolder.shotsCreatedTimeTx.setText(TimeUtil.getTimeDifference(shotses.get(position).getUpdated_at()));
            ImageLoader.load(fragment, shotses.get(position).getImages().getNormal(), viewHolder.shotsIm, isGif);
            if(isGif){
                viewHolder.shotsGifIm.setVisibility(TextView.VISIBLE);
            }
            viewHolder.itemLikesCountTx.setText(String.valueOf(shotses.get(position).getLikes_count()));
            viewHolder.itemCommentsCountTx.setText(String.valueOf(shotses.get(position).getComments_count()));
            viewHolder.itemViewsCountTx.setText(String.valueOf(shotses.get(position).getViews_count()));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onRecyclerFragmentListListener(viewHolder, shotses.get(position));
                    }
                }
            });
        }

    }

    public void addShots(Shots shots){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return shotses.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final CircleImageView avatarIv;
        public final TextView shotsTitleTx, shots_userTx, shotsCreatedTimeTx;
        public final ImageView shotsIm, shotsGifIm;
        public final TextView itemLikesCountTx, itemCommentsCountTx, itemViewsCountTx;

        public ViewHolder(View view) {
            super(view);
            avatarIv = (CircleImageView) view.findViewById(R.id.avatar_im);
            shotsTitleTx = (TextView) view.findViewById(R.id.shots_title_tx);
            shots_userTx = (TextView) view.findViewById(R.id.shots_user_name_tx);
            shotsCreatedTimeTx = (TextView) view.findViewById(R.id.shots_create_time_tx);
            shotsIm = (ImageView) view.findViewById(R.id.shots_im);
            shotsGifIm = (ImageView) view.findViewById(R.id.shots_gif_im);
            itemLikesCountTx = (TextView) view.findViewById(R.id.item_likes_count_tx);
            itemCommentsCountTx = (TextView) view.findViewById(R.id.item_comments_count_tx);
            itemViewsCountTx = (TextView) view.findViewById(R.id.item_view_count_tx);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
