package widgets.dinesh.com.flickrslideshow.data.flickr;

import java.util.List;

import io.reactivex.Flowable;
import widgets.dinesh.com.flickrslideshow.models.FlickrSourceData;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public interface FlickrDataSource {
    Flowable<List<FlickrSourceData>> getData(int page);
}
