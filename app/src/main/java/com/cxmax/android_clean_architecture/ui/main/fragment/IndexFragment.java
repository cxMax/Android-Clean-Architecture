package com.cxmax.android_clean_architecture.ui.main.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.cxmax.android_clean_architecture.R;
import com.cxmax.android_clean_architecture.base.BaseFragment;
import com.cxmax.android_clean_architecture.model.bean.Course;
import com.cxmax.android_clean_architecture.model.bean.DecorationItem;
import com.cxmax.android_clean_architecture.presenter.IndexPresenter;
import com.cxmax.android_clean_architecture.presenter.contract.IndexContract;
import com.cxmax.android_clean_architecture.ui.calendar.activity.CalendarActivity;
import com.cxmax.android_clean_architecture.ui.main.recyclerview.adapter.IndexAdapter;
import com.cxmax.android_clean_architecture.util.CircularAnimUtil;
import com.cxmax.android_clean_architecture.util.SnackbarUtil;
import com.cxmax.android_clean_architecture.widget.CustomItemDecoration;
import com.cxmax.android_clean_architecture.widget.ProgressImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class IndexFragment extends BaseFragment<IndexPresenter> implements IndexContract.View {
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rv_index_list)
    RecyclerView rvIndexList;
    @BindView(R.id.iv_progress)
    ProgressImageView ivProgress;

    IndexAdapter adapter;
    List<Course> data = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initEventAndData() {
        adapter = new IndexAdapter(context , data);
        rvIndexList.setLayoutManager(new LinearLayoutManager(context));
        DecorationItem item = new DecorationItem(
                getResources().getDimensionPixelOffset(R.dimen.content_padding), 0, 0, 0);
        rvIndexList.addItemDecoration(new CustomItemDecoration(item));
        rvIndexList.setAdapter(adapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getIndexData();
            }
        });
        presenter.getIndexData();
    }

    @Override
    public void showError(String msg) {
        SnackbarUtil.showShort(rvIndexList,msg);
    }

    @Override
    public void showContent(List<Course> courses) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        adapter.setData(courses);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addContent(String result) {
        SnackbarUtil.showShort(rvIndexList,"插入课程成功 : " +result);
    }

    @OnClick(R.id.fab_calender)
    void startCalender() {
        Intent it = new Intent();
        it.setClass(context,CalendarActivity.class);
        CircularAnimUtil.startActivity(activity,it,fabCalender,R.color.fab_bg);
    }
}
