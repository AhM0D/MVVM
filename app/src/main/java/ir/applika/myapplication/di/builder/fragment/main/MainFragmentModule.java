package ir.applika.myapplication.di.builder.fragment.main;

import dagger.Module;
import dagger.Provides;
import ir.applika.myapplication.network.ApiService;
import retrofit2.Retrofit;

@Module
public class MainFragmentModule {

    @Provides
    static ApiService privudeMainApi(Retrofit retrofit) { return retrofit.create(ApiService.class);}
}
