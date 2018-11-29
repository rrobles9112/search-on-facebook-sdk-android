package com.usc.searchonfb.dagger.component;

import android.content.SharedPreferences;

import com.usc.searchonfb.dagger.module.AppModule;
import com.usc.searchonfb.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by adarsh on 4/3/2017.
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}
