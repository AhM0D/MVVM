package ir.applika.myapplication.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.concurrent.TimeUnit;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ir.applika.myapplication.models.ResponseBody;
import ir.applika.myapplication.network.ApiConstants;
import ir.applika.myapplication.network.ApiService;
import ir.applika.myapplication.network.RequestInterceptor;
import ir.applika.myapplication.network.Resource;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ApiServiceUnitTest {
    private ApiService apiService;
    @Before
    public void createService() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        apiService = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ApiService.class);
    }

    @Test
    public void getInfo() {
        apiService.getInfo()
                .onErrorReturn(new Function<Throwable, ResponseBody>() {
                    @Override
                    public ResponseBody apply(Throwable throwable) throws Exception {
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
                .subscribe(new Subscriber<Resource<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Resource<ResponseBody> responseBodyResource) {
                     assertEquals(responseBodyResource.data.getStatus(),200);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
