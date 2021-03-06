package widgets.dinesh.com.flickrslideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public class MetaData {

    @SerializedName("pages")
    @Expose
    private int pages;

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("perpage")
    @Expose
    private int perpage;

    @SerializedName("total")
    @Expose
    private int total;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
