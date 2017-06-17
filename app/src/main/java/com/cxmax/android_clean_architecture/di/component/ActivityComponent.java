package com.cxmax.android_clean_architecture.di.component;

import android.app.Activity;

import com.cxmax.android_clean_architecture.di.module.ActivityModule;
import com.cxmax.android_clean_architecture.di.scope.ActivityScope;
import com.cxmax.android_clean_architecture.ui.main.activity.MainActivity;

import dagger.Component;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
