package ir.applika.myapplication.di.builder.fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ir.applika.myapplication.di.builder.fragment.main.MainFragmentModule;
import ir.applika.myapplication.di.builder.fragment.main.MainFragmentViewModel;
import ir.applika.myapplication.view.fragment.BookReviewFragment;
import ir.applika.myapplication.view.fragment.MainFragment;
import ir.applika.myapplication.view.fragment.ShowPdfFragment;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector(modules ={MainFragmentViewModel.class,
            MainFragmentModule.class,
    })
    abstract MainFragment mainFragment();

    @ContributesAndroidInjector
    abstract BookReviewFragment bookReviewFragment();

    @ContributesAndroidInjector
    abstract ShowPdfFragment showPdfFragment();
}
