package widgets.dinesh.com.flickrslideshow.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import widgets.dinesh.com.flickrslideshow.base.CustomRxImageMergeOperator;
import widgets.dinesh.com.flickrslideshow.base.data.ExecutionThread;
import widgets.dinesh.com.flickrslideshow.base.data.PostExecutionThread;
import widgets.dinesh.com.flickrslideshow.data.flickr.FlickrDataSource;
import widgets.dinesh.com.flickrslideshow.models.FlickrSourceData;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public class SlideViewModel extends ViewModel {

    private final FlickrDataSource dataSource;
    private Flowable<List<FlickrSourceData>> listData;
    private Observable<ArrayList<String>> urlsData;
    private final PostExecutionThread postExecutionThread;
    private final ExecutionThread executionThread;
    private int height;

    @Inject
    public SlideViewModel(FlickrDataSource dataSource, PostExecutionThread postExecutionThread, ExecutionThread executionThread) {
        this.dataSource = dataSource;
        this.postExecutionThread = postExecutionThread;

        this.executionThread = executionThread;
    }
    private boolean firstElement = true;
    void init(int height){
        this.height = height;
        urlsData = dataSource.getData(1)
                .switchMap(Flowable::fromIterable)
                //.take(300)
                .observeOn(postExecutionThread.getScheduler())
                .toObservable().lift(CustomRxImageMergeOperator.customRxImageMergeOperator(height))

                .concatMap(flickrSourceData ->
                {   //A very bad way of doing this, talking to outside world :-/ Will thing of a better solution

                    if (firstElement){
                        firstElement = false;
                        return Observable.just(flickrSourceData);
                    }
                    else {
                        return Observable.just(flickrSourceData).delay(10, TimeUnit.SECONDS, AndroidSchedulers.mainThread());
                    }
                }
                )
                .map(flickrSourceDataList -> {

                    ArrayList<String> urls = new ArrayList<>();
                    for (FlickrSourceData data : flickrSourceDataList) {
                        urls.add(data.getUrl());
                    }
                    return urls;
                });



    }




    public Observable<ArrayList<String>> getUrlsData(int height) {
        if(urlsData == null){
            init(height);
        }
        return urlsData;
    }




    public void preloadImages(Context context, ArrayList<String> urls) {
        for(String url: urls){
            Picasso.with(context).load(url).fetch();
        }
    }
}
