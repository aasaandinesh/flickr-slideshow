package widgets.dinesh.com.flickrslideshow;

import android.support.multidex.MultiDexApplication;

import widgets.dinesh.com.flickrslideshow.data.network.NetworkModule;
import widgets.dinesh.com.flickrslideshow.di.ApplicationComponent;
import widgets.dinesh.com.flickrslideshow.di.ApplicationModule;
import widgets.dinesh.com.flickrslideshow.di.DaggerApplicationComponent;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public class FlickrApplication extends MultiDexApplication {

    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component =  DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();


    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
