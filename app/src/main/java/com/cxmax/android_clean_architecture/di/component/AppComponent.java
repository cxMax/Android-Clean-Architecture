package com.cxmax.android_clean_architecture.di.component;


import com.cxmax.android_clean_architecture.app.App;
import com.cxmax.android_clean_architecture.di.module.AppModule;
import com.cxmax.android_clean_architecture.di.module.HttpModule;
import com.cxmax.android_clean_architecture.model.db.RealmHelper;
import com.cxmax.android_clean_architecture.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

@Singleton
@Component(modules = {AppModule.class ,  HttpModule.class})
public interface AppComponent {

    App getContext();

    RealmHelper realmHelper();

    RetrofitHelper retrofitHelper();
}
