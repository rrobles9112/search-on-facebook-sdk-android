package com.usc.searchonfb;

import android.app.Application;

import com.usc.searchonfb.dagger.component.DaggerNetComponent;
import com.usc.searchonfb.dagger.component.NetComponent;
import com.usc.searchonfb.dagger.module.AppModule;
import com.usc.searchonfb.dagger.module.NetModule;
import com.facebook.FacebookSdk;

/**
 * Created by adarsh on 4/15/2017.
 */

public class FacebookApplication  extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://adarsh1.us-west-2.elasticbeanstalk.com/"))
                .build();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
