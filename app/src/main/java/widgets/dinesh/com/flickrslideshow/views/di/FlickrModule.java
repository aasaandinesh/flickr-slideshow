package widgets.dinesh.com.flickrslideshow.views.di;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import widgets.dinesh.com.flickrslideshow.base.data.ExecutionThread;
import widgets.dinesh.com.flickrslideshow.base.data.PostExecutionThread;
import widgets.dinesh.com.flickrslideshow.data.APIService;
import widgets.dinesh.com.flickrslideshow.data.flickr.FlickrDataProvider;
import widgets.dinesh.com.flickrslideshow.data.flickr.FlickrDataSource;
import widgets.dinesh.com.flickrslideshow.viewmodels.SlideViewModel;
import widgets.dinesh.com.flickrslideshow.views.FlickrActivity;

/**
 * Created by ajmac1005 on 21/01/18.
 */
@Module
public class FlickrModule {
    private final FlickrActivity activity;

    public FlickrModule(FlickrActivity activity) {
        this.activity = activity;
    }

    @Provides
    public APIService getService(Retrofit retrofit){
        return retrofit.create(APIService.class);
    }

    @Provides
    public FlickrDataSource provideDataSource(APIService apiService,
                                              ExecutionThread executionThread,
                                              PostExecutionThread postExecutionThread){
        return new FlickrDataProvider(apiService, executionThread, postExecutionThread);
    }

    @Provides
    public SlideViewModel provideViewModel(FlickrDataSource dataSource, PostExecutionThread postExecutionThread, ExecutionThread executionThread){
        return new SlideViewModel(dataSource, postExecutionThread, executionThread);
    }
}
