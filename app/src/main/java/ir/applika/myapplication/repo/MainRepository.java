package ir.applika.myapplication.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ir.applika.myapplication.models.ResponseBody;
import ir.applika.myapplication.network.ApiService;
import ir.applika.myapplication.network.Resource;
import timber.log.Timber;

public class MainRepository  {
    private static final String TAG = "MainRepository";
    private MediatorLiveData<Resource<ResponseBody>> getInfo = new MediatorLiveData<>();
    private final ApiService apiService;
    @Inject
    public MainRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public MediatorLiveData<Resource<ResponseBody>> getInfo(){
        getInfo.setValue(Resource.loading((ResponseBody) null));
        final LiveData<Resource<ResponseBody>> source = LiveDataReactiveStreams.fromPublisher(
                apiService.getInfo()
                        .onErrorReturn(new Function<Throwable, ResponseBody>() {
                            @Override
                            public ResponseBody apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable );
                                ResponseBody responseBody = new ResponseBody();
                                responseBody.setStatus(-1);
                                return responseBody;
                            }
                        })
                        .map(new Function<ResponseBody, Resource<ResponseBody>>() {
                            @Override
                            public Resource<ResponseBody> apply(ResponseBody responseBody) throws Exception {
                                if (responseBody.getStatus() ==-1){
                                    return Resource.error("Could not check for update", null);
                                }else {
                                    return Resource.Response(responseBody);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
        getInfo.addSource(source, new Observer<Resource<ResponseBody>>() {
            @Override
            public void onChanged(Resource<ResponseBody> user) {
                getInfo.setValue(user);
                getInfo.removeSource(source);
            }
        });
        return getInfo;
    }
}
