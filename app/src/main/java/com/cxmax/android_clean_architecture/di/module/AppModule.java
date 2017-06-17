package com.cxmax.android_clean_architecture.di.module;


import com.cxmax.android_clean_architecture.app.App;
import com.cxmax.android_clean_architecture.model.db.RealmHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }


    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }
}
