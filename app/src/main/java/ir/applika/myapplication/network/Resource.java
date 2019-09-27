package ir.applika.myapplication.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by AhmadKing ;)
 */
public class Resource<T> {

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public Resource(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    public static <T> Resource<T> error(@NonNull String msg, @Nullable T data) {
        return new Resource<>(AuthStatus.ERROR, data, msg);
    }
    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(AuthStatus.LOADING, data, null);
    }
    public static <T> Resource<T> Response ( @Nullable T data) {
        return new Resource<>(AuthStatus.Response, data,null);
    }

    public enum AuthStatus {  ERROR, LOADING, Response}

}
