package com.menglingpeng.designersshow.mvp.view;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.utils.Constants;
import com.menglingpeng.designersshow.utils.ImageLoader;
import com.menglingpeng.designersshow.utils.SharedPrefUtil;

/**
 * Created by mengdroid on 2017/12/11.
 */

public class DataPreference extends Preference {

    private View contentView;
    private RelativeLayout savingDataModeRl;
    private Switch savingDataModeSwitch;
    private RelativeLayout gifsAutoplayRl;
    private Switch gifsAutoplaySwitch;
    private RelativeLayout clearCacheRl;
    private TextView cacheSizeTv;
    private View.OnClickListener listener;

    public DataPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public DataPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DataPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DataPreference(Context context) {
        super(context);
    }


    @Override
    protected View onCreateView(ViewGroup parent) {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.pref_catagory_data, parent, false);
        return contentView;
    }

    @Override
    protected void onBindView(View view) {
        savingDataModeRl = (RelativeLayout)view.findViewById(R.id.settings_switch_preference_data_saving_mode_rl);
        savingDataModeSwitch = (Switch)view.findViewById(R.id.settings_switch_preference_data_saving_mode_switch);
        gifsAutoplayRl = (RelativeLayout)view.findViewById(R.id.settings_switch_preference_gifs_autoplay_rl);
        gifsAutoplaySwitch = (Switch)view.findViewById(R.id.settings_switch_preference_gifs_autoplay_switch);
        clearCacheRl = (RelativeLayout)view.findViewById(R.id.settings_preference_clear_cache_rl);
        cacheSizeTv = (TextView)view.findViewById(R.id.settings_preference_cache_size_tv);
        if(SharedPrefUtil.getState(Constants.GIFS_AUTO_PLAY)){
            gifsAutoplaySwitch.setChecked(true);
        }else {
            getGifsAutoplaySwitch().setChecked(false);
        }
        if(SharedPrefUtil.getState(Constants.SAVING_LOWER_IMAGE)){
            savingDataModeSwitch.setChecked(true);
        }else {
            savingDataModeSwitch.setChecked(false);
        }
        cacheSizeTv.setText(ImageLoader.getDiskCacheSize(getContext()));
        savingDataModeRl.setOnClickListener(listener);
        gifsAutoplayRl.setOnClickListener(listener);
        clearCacheRl.setOnClickListener(listener);
    }

    public void setOnclickListerner(View.OnClickListener listerner){
        this.listener = listerner;
    }

    public RelativeLayout getSavingDataModeRl(){
        return savingDataModeRl;
    }

    public Switch getSavingDataModeSwitch(){
        return  savingDataModeSwitch;
    }

    public RelativeLayout getGifsAutoplayRl(){
        return gifsAutoplayRl;
    }

    public Switch getGifsAutoplaySwitch(){
        return gifsAutoplaySwitch;
    }

    public RelativeLayout getClearCacheRl(){
        return clearCacheRl;
    }

}
