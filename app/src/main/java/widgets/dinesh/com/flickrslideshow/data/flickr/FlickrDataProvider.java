package widgets.dinesh.com.flickrslideshow.data.flickr;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import widgets.dinesh.com.flickrslideshow.base.data.BaseDataProvider;
import widgets.dinesh.com.flickrslideshow.base.data.ExecutionThread;
import widgets.dinesh.com.flickrslideshow.base.data.PostExecutionThread;
import widgets.dinesh.com.flickrslideshow.data.APIService;
import widgets.dinesh.com.flickrslideshow.models.FlickrAPIWrappedData;
import widgets.dinesh.com.flickrslideshow.models.FlickrSourceData;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public class FlickrDataProvider extends BaseDataProvider implements FlickrDataSource {
    private final APIService apiService;

    @Inject
    public FlickrDataProvider(APIService apiService, ExecutionThread executionThread,
                              PostExecutionThread postExecutionThread) {
        super(executionThread, postExecutionThread);
        this.apiService = apiService;


    }



    public Flowable<FlickrAPIWrappedData> getDataFromAPI(int page) {
        //Initialising with some number



          return    apiService.getPhotos("flickr.interestingness.getList",
                    "fa4b899fa47c95faf2fb86e73f111f55", "json", "url_n", page)
                    .subscribeOn(executionThread.getScheduler())
                    .observeOn(postExecutionThread.getScheduler())
                    .concatMap(flickrAPIWrappedData -> {
                        if(flickrAPIWrappedData.getFlickrAPIData().
                                getPage()<flickrAPIWrappedData.getFlickrAPIData().getPages()){
                            return Flowable.
                                    just(flickrAPIWrappedData).
                                    concatWith(getDataFromAPI(flickrAPIWrappedData.getFlickrAPIData().getPage()+1));
                        }
                        else {
                            return Flowable.just(flickrAPIWrappedData);
                        }
                    });




    }


    @Override
    public Flowable<List<FlickrSourceData>> getData(int page) {
        return getDataFromAPI(page)
                .map(flickrAPIWrappedData -> flickrAPIWrappedData.
                        getFlickrAPIData().getFlickrSourceData());
    }
}
