package widgets.dinesh.com.flickrslideshow.base;

import java.util.ArrayList;

import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import widgets.dinesh.com.flickrslideshow.models.FlickrSourceData;
/***
A Custom RxJava operator to collect items until a partiular condition is reached. Was not able to
 find such operator anywhere hence wrote it down.
 ***/
public class CustomRxImageMergeOperator implements ObservableOperator<ArrayList<FlickrSourceData>, FlickrSourceData> {

    private final int height;
    ArrayList<FlickrSourceData> flickrSourceDataList = new ArrayList<>();

    public CustomRxImageMergeOperator(int height) {
        this.height = height;
    }


    public static CustomRxImageMergeOperator customRxImageMergeOperator(int height){
        return new CustomRxImageMergeOperator(height);

    }






    @Override
    public Observer<? super FlickrSourceData> apply(Observer<? super ArrayList<FlickrSourceData>> observer) throws Exception {
        return new Observer<FlickrSourceData>() {
            @Override
            public void onSubscribe(Disposable d) {
                observer.onSubscribe(d);
            }

            @Override
            public void onNext(FlickrSourceData flickrSourceData) {

                if(isLimitReached(flickrSourceData)){
                    observer.onNext(flickrSourceDataList);
                    flickrSourceDataList = new ArrayList<>();
                    flickrSourceDataList.add(flickrSourceData);

                }
                else {
                    flickrSourceDataList.add(flickrSourceData);
                }

            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                observer.onComplete();
            }
        };
    }

    private boolean isLimitReached(FlickrSourceData flickrSourceData) {
        int currentImageHeight = flickrSourceData.getHeight();
        int totalHeight = 0;
        for (FlickrSourceData data: flickrSourceDataList){
            totalHeight += data.getHeight();
        }
        totalHeight += currentImageHeight;
        return totalHeight > height;
    }
}