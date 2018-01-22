package widgets.dinesh.com.flickrslideshow.base.di;


import dagger.Module;
import dagger.Provides;
import widgets.dinesh.com.flickrslideshow.base.data.BackgroundThread;
import widgets.dinesh.com.flickrslideshow.base.data.ExecutionThread;
import widgets.dinesh.com.flickrslideshow.base.data.PostExecutionThread;
import widgets.dinesh.com.flickrslideshow.base.data.UIThread;


@ScopedActivity
@Module
public class RxModule {

    @Provides
    @ScopedActivity
    public ExecutionThread provideExecutionThread() {
        return new BackgroundThread();
    }

    @ScopedActivity
    @Provides
    public PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

}
