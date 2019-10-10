package ir.applika.myapplication.view.activity;



import android.os.Bundle;



import dagger.android.support.DaggerAppCompatActivity;
import ir.applika.myapplication.R;


public class MainActivity extends DaggerAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
//        mainViewModel.getInfo();
//        subscribeInfoObservers();
    }

//    private void subscribeInfoObservers(){
//        mainViewModel.getInfoObserver().observe(this, new Observer<Resource<ResponseBody>>() {
//            @Override
//            public void onChanged(Resource<ResponseBody> responseBodyResource) {
//                if (responseBodyResource!=null){
//                    switch (responseBodyResource.status){
//                        case ERROR:{
//
//                            break;
//                        }
//                        case Response:{
//                            Timber.tag("MainActivity").e(responseBodyResource.data.getStatus()+" status");
//                        }
//
//                        case LOADING:{
//
//
//                            break;
//                        }
//
//                    }
//                }
//
//            }
//        });
//    }
}
