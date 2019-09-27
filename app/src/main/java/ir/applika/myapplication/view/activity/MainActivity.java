package ir.applika.myapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import ir.applika.myapplication.R;
import ir.applika.myapplication.models.ResponseBody;
import ir.applika.myapplication.network.Resource;
import ir.applika.myapplication.viewmodels.MainViewModel;
import ir.applika.myapplication.viewmodels.ViewModelProviderFactory;
import timber.log.Timber;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ViewModelProviderFactory providerFactory;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        mainViewModel.getInfo();
        subscribeInfoObservers();
    }

    private void subscribeInfoObservers(){
        mainViewModel.getInfoObserver().observe(this, new Observer<Resource<ResponseBody>>() {
            @Override
            public void onChanged(Resource<ResponseBody> responseBodyResource) {
                if (responseBodyResource!=null){
                    switch (responseBodyResource.status){
                        case ERROR:{

                            break;
                        }
                        case Response:{
                            Timber.tag("MainActivity").e(responseBodyResource.data.getStatus()+" status");
                        }

                        case LOADING:{


                            break;
                        }

                    }
                }

            }
        });
    }
}
