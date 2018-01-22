package widgets.dinesh.com.flickrslideshow.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import widgets.dinesh.com.flickrslideshow.base.di.ApplicationContext;
import widgets.dinesh.com.flickrslideshow.data.network.NetworkModule;


@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetworkModule.class,

        }
)
public interface ApplicationComponent {

    @ApplicationContext
    Context getApplicationContext();

    Application getApplication();
   Retrofit getRetrofit();


}
