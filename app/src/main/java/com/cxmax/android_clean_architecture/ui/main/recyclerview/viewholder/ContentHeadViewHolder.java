package com.cxmax.android_clean_architecture.ui.main.recyclerview.viewholder;

import android.view.View;
import android.widget.TextView;


import com.cxmax.android_clean_architecture.R;
import com.cxmax.android_clean_architecture.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class ContentHeadViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_daily_date)
    TextView tvDate;

    public ContentHeadViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void update() {

    }
}
