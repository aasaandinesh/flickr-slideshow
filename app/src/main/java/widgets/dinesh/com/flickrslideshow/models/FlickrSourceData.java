package widgets.dinesh.com.flickrslideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public class FlickrSourceData {

    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("url_n")
    @Expose
    private String url;

    @SerializedName("height_n")
    @Expose
    private int height;

    @SerializedName("width_n")
    @Expose
    private int width;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
