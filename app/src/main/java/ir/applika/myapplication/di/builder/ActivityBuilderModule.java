package ir.applika.myapplication.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ir.applika.myapplication.view.activity.MainActivity;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
