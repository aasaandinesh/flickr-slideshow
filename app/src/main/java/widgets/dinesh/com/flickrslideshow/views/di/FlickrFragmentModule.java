package widgets.dinesh.com.flickrslideshow.views.di;

import dagger.Module;
import dagger.Provides;
import widgets.dinesh.com.flickrslideshow.viewmodels.SlideFragmentViewModel;

/**
 * Created by ajmac1005 on 22/01/18.
 */
@Module
public class FlickrFragmentModule {
    @Provides
    SlideFragmentViewModel provideSlideFragmentViewModel(){
        return new SlideFragmentViewModel();
    }
}
