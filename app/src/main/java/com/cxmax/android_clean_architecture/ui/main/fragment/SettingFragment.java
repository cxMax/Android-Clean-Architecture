package com.cxmax.android_clean_architecture.ui.main.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;

import com.cxmax.android_clean_architecture.R;
import com.cxmax.android_clean_architecture.app.Constants;
import com.cxmax.android_clean_architecture.base.BaseFragment;
import com.cxmax.android_clean_architecture.component.SharedPreferences.SharedPrefer;
import com.cxmax.android_clean_architecture.presenter.SettingPresenter;
import com.cxmax.android_clean_architecture.presenter.contract.SettingContract;
import com.trello.rxlifecycle2.android.FragmentEvent;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public class SettingFragment extends BaseFragment<SettingPresenter> implements CompoundButton.OnCheckedChangeListener, SettingContract.View {

    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox cbSettingNight;
    @BindView(R.id.cb_setting_voice)
    AppCompatCheckBox cbSettingVoice;

    private boolean isNull = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNull = savedInstanceState == null;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        initCheckBoxes();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_setting_night:
                if (isNull) {   //防止夜间模式MainActivity执行reCreate后重复调用
                    asynchronousEditNightModeSharedPrefer(b);
                }
                break;
            case R.id.cb_setting_voice:
                AudioManager audioManager = (AudioManager) context
                        .getSystemService(Context.AUDIO_SERVICE);
                if (b) {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                } else {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }
                break;
        }
    }

    private void asynchronousEditNightModeSharedPrefer(final boolean status) {
        Observable.just("1")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .compose(this.<String>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {
                        SharedPrefer.from(context)
                                .open(Constants.NIGHT_MODE_FILE)
                                .edit()
                                .putBoolean(Constants.SP_NIGHT_MODE, status)
                                .apply();
                    }
                });
    }

    private void initCheckBoxes() {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                boolean status = SharedPrefer.from(context)
                        .open(Constants.NIGHT_MODE_FILE)
                        .read(Context.MODE_ENABLE_WRITE_AHEAD_LOGGING)
                        .getBoolean(Constants.SP_NIGHT_MODE, false);
                e.onNext(status);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Boolean>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean status) throws Exception {
                        cbSettingNight.setChecked(status);
                    }
                });
        cbSettingNight.setOnCheckedChangeListener(this);
        cbSettingVoice.setOnCheckedChangeListener(this);
    }
}
