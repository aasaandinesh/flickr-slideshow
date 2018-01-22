package widgets.dinesh.com.flickrslideshow.data;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import widgets.dinesh.com.flickrslideshow.models.FlickrAPIWrappedData;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public interface APIService {
    @GET("services/rest")
    Flowable<FlickrAPIWrappedData> getPhotos(@Query("method") String method,
                                                              @Query("api_key") String apiKey,
                                                              @Query("format") String format,
                                                              @Query("extras") String extras,
                                                              @Query("page") int page);


}
