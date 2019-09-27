package ir.applika.myapplication.network;



import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This okhttp interceptor is responsible for adding the common query parameters and headers
 * for every service calls
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request newRequest  = chain.request().newBuilder()
                .addHeader("Authorization", ApiConstants.API_KEY)
                .build();
        return chain.proceed(newRequest);
    }
}