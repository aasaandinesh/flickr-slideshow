package widgets.dinesh.com.flickrslideshow.views.di;

import dagger.Component;
import widgets.dinesh.com.flickrslideshow.base.di.RxModule;
import widgets.dinesh.com.flickrslideshow.base.di.ScopedActivity;
import widgets.dinesh.com.flickrslideshow.di.ApplicationComponent;
import widgets.dinesh.com.flickrslideshow.views.FlickrActivity;

/**
 * Created by ajmac1005 on 21/01/18.
 */
@ScopedActivity
@Component(
        modules = {
                FlickrModule.class,
                RxModule.class
        },
        dependencies = ApplicationComponent.class

)
public interface FlickrComponent {
    void inject(FlickrActivity activity);
}
