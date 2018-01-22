package widgets.dinesh.com.flickrslideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public class FlickrAPIWrappedData {
    @SerializedName("photos")
    @Expose
    private FlickrAPIData flickrAPIData;

    public FlickrAPIData getFlickrAPIData() {
        return flickrAPIData;
    }

    public void setFlickrAPIData(FlickrAPIData flickrAPIData) {
        this.flickrAPIData = flickrAPIData;
    }
}
