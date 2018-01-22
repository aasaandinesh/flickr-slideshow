package widgets.dinesh.com.flickrslideshow.views.di;

import dagger.Component;
import widgets.dinesh.com.flickrslideshow.base.di.ScopedActivity;
import widgets.dinesh.com.flickrslideshow.di.ApplicationComponent;
import widgets.dinesh.com.flickrslideshow.views.PhotoslideFragment;

/**
 * Created by ajmac1005 on 22/01/18.
 */
@ScopedActivity
@Component(
        modules = FlickrFragmentModule.class,
        dependencies = ApplicationComponent.class
)
public interface FlickrFragmentComponent {
    void inject(PhotoslideFragment fragment);
}
