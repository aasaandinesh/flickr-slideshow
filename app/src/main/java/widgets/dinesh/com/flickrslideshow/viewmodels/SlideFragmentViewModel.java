package widgets.dinesh.com.flickrslideshow.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ajmac1005 on 22/01/18.
 */

public class SlideFragmentViewModel extends ViewModel {

    public void loadImage(ImageView imageView, String url){
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

}
