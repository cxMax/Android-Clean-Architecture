package com.cxmax.android_clean_architecture.presenter;


import com.cxmax.android_clean_architecture.base.RxPresenter;
import com.cxmax.android_clean_architecture.presenter.contract.SettingContract;

import javax.inject.Inject;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {

    @Inject
    public SettingPresenter() {
    }

}
