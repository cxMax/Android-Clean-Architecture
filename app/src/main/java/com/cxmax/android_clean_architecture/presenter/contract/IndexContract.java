package com.cxmax.android_clean_architecture.presenter.contract;


import com.cxmax.android_clean_architecture.base.BasePresenter;
import com.cxmax.android_clean_architecture.base.BaseView;
import com.cxmax.android_clean_architecture.model.bean.Course;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public interface IndexContract {
    interface View extends BaseView {
        void showContent(List<Course> courses);
        void addContent(String result);
    }
    interface Presenter extends BasePresenter<View> {
        void getIndexData();
    }
}
