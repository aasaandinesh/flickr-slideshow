package widgets.dinesh.com.flickrslideshow.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import widgets.dinesh.com.flickrslideshow.data.GsonPConverterFactory;


@Module
public class NetworkModule {

    private static final String API_BASE_URL = "https://api.flickr.com/";
    public static final String FORMAT_ONE = "yyyy-MM-dd'T'HH:mm:ss";

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat(FORMAT_ONE)
                .create();
    }

    @Provides
    @Singleton
    public Interceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return  new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(new GsonPConverterFactory(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }
}
