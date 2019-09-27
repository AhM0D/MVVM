package ir.applika.myapplication.di.components;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import ir.applika.myapplication.di.builder.ActivityBuilderModule;
import ir.applika.myapplication.di.module.AppModule;
import ir.applika.myapplication.view.base.BaseApplication;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
