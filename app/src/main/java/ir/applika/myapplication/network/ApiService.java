package ir.applika.myapplication.network;



import io.reactivex.Flowable;
import ir.applika.myapplication.models.ResponseBody;
import retrofit2.http.GET;
/**
 * Created by AhmadKing ;)
 */
public interface ApiService {
    @GET("getInfo")
    Flowable<ResponseBody> getInfo();
}
