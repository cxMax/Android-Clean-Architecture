package com.cxmax.android_clean_architecture.presenter;


import com.cxmax.android_clean_architecture.base.RxPresenter;
import com.cxmax.android_clean_architecture.component.RxBus.CommonSubscriber;
import com.cxmax.android_clean_architecture.component.RxBus.RxBus;
import com.cxmax.android_clean_architecture.component.RxBus.RxUtil;
import com.cxmax.android_clean_architecture.presenter.contract.MainContract;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter() {
        registerEvent();
    }

    void registerEvent() {
    }

}
