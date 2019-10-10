package ir.applika.myapplication.di.builder.fragment.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

import ir.applika.myapplication.di.module.ViewModelKey;
import ir.applika.myapplication.viewmodels.MainViewModel;


@Module
public abstract class MainFragmentViewModel {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindAuthViewModel(MainViewModel viewModel);

}
