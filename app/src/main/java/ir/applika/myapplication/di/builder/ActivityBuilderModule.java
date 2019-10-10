package ir.applika.myapplication.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ir.applika.myapplication.di.builder.fragment.MainFragmentBuilderModule;
import ir.applika.myapplication.di.builder.fragment.main.MainFragmentModule;
import ir.applika.myapplication.di.builder.fragment.main.MainFragmentViewModel;
import ir.applika.myapplication.view.activity.MainActivity;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules ={MainFragmentBuilderModule.class,})
    abstract MainActivity contributeMainActivity();

}
