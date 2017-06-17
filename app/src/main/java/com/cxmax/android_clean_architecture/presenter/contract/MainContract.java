package com.cxmax.android_clean_architecture.presenter.contract;


import com.cxmax.android_clean_architecture.base.BasePresenter;
import com.cxmax.android_clean_architecture.base.BaseView;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public interface MainContract {

    interface View extends BaseView {

    }
    interface  Presenter extends BasePresenter<View> {

    }
}
