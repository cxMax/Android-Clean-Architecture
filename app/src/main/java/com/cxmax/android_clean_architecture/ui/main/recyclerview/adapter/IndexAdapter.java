package com.cxmax.android_clean_architecture.ui.main.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.cxmax.android_clean_architecture.app.ViewTypeConstant;
import com.cxmax.android_clean_architecture.component.multitype.MultiTypeAdapter;
import com.cxmax.android_clean_architecture.component.multitype.MultiTypePool;
import com.cxmax.android_clean_architecture.model.bean.Course;
import com.cxmax.android_clean_architecture.ui.main.recyclerview.provider.ContentItemProvider;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class IndexAdapter extends MultiTypeAdapter<Course> {

    public IndexAdapter(@NonNull Context context, @Nullable List<Course> data) {
        super(context, data);
    }

    @Override
    protected void registerAllProvider(@NonNull MultiTypePool pool) {
//        pool.add(ViewTypeConstant.IndexViewType.INDEX_LIST_HEADER , new ContentHeadProvider(context));
        pool.add(ViewTypeConstant.IndexViewType.INDEX_LIST_CONTENT , new ContentItemProvider(context));
    }

    @Override
    public void updateData(List<Course> newItems) {
        // notify data when changed
    }
}
