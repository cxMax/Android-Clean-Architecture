package com.cxmax.android_clean_architecture.ui.main.recyclerview.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxmax.android_clean_architecture.R;
import com.cxmax.android_clean_architecture.component.multitype.AbsItemProvider;
import com.cxmax.android_clean_architecture.model.bean.Course;
import com.cxmax.android_clean_architecture.ui.main.recyclerview.viewholder.ContentItemViewHolder;


/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class ContentItemProvider extends AbsItemProvider<Course, ContentItemViewHolder> {

    public ContentItemProvider(@NonNull Context context) {
        super(context);
    }

    /**
     * determine whether this is the responsible for the given data element.
     *
     * @param item
     * @param position
     * @return
     */
    @Override
    protected boolean isForViewType(@NonNull Course item, int position) {
        return true; //cuz it only one item type in this adapter so it's always true
    }

    /**
     * this method don't need int viewtype param
     *
     * @param parent
     * @return
     */
    @NonNull
    @Override
    protected ContentItemViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_index_content, parent, false);
        ContentItemViewHolder holder = new ContentItemViewHolder(view);
        return holder;
    }

    /**
     * @param item    data source
     * @param position The position in the datasource
     * @param holder   ViewHolder
     */
    @Override
    protected void onBindViewHolder(@NonNull Course item, int position, @NonNull ContentItemViewHolder holder) {
        holder.setCourse(item);
        holder.update();
    }
}
