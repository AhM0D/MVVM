package ir.applika.myapplication.view.base;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ir.applika.myapplication.BuildConfig;
import ir.applika.myapplication.di.components.DaggerAppComponent;
import timber.log.Timber;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
