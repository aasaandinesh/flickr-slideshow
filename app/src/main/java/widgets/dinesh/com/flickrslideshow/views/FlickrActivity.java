package widgets.dinesh.com.flickrslideshow.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import widgets.dinesh.com.flickrslideshow.FlickrApplication;
import widgets.dinesh.com.flickrslideshow.R;
import widgets.dinesh.com.flickrslideshow.viewmodels.SlideViewModel;
import widgets.dinesh.com.flickrslideshow.views.di.DaggerFlickrComponent;
import widgets.dinesh.com.flickrslideshow.views.di.FlickrModule;

public class FlickrActivity extends AppCompatActivity {

    @Inject
    SlideViewModel viewModel;
    private int fragmentCounter = 0;

    TextView tvFragmentCount;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flickr);
        DaggerFlickrComponent
                .builder()
                .applicationComponent(FlickrApplication.getComponent())
                .flickrModule(new FlickrModule(this))
                .build()
                .inject(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        disposable =  viewModel.getUrlsData(height)
        . subscribe(this::addFragment);
        tvFragmentCount = (TextView) findViewById(R.id.tv_fragment_number);
        fragmentCounter = 0;
        tvFragmentCount.setText(String.valueOf(fragmentCounter));

    }

    private PhotoslideFragment nextFragment=null;
    private PhotoslideFragment currentFragment=null;

    private void addFragment(ArrayList<String> strings) {
        viewModel.preloadImages(this, strings);
        fragmentCounter +=1;
        tvFragmentCount.setText(String.valueOf(fragmentCounter));

//        fragment.getImageState().subscribe(state -> {
//            if (state == PhotoslideFragment.ImageLoadingState.COMPLETED){
//                viewModel.fetchNextImages();
//            }
//        });
        if(currentFragment == null){
            currentFragment = PhotoslideFragment.newinstance(strings);
            getSupportFragmentManager().beginTransaction().add(R.id.container, currentFragment).commit();
        }
        else {
            if(nextFragment == null){
                nextFragment = PhotoslideFragment.newinstance(strings);
            }
            currentFragment = nextFragment;
            nextFragment = PhotoslideFragment.newinstance(strings);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, currentFragment).commit();
        }




    }

    @Override
    protected void onPause() {
        super.onPause();
        //On rotation of screen, disposing the current stream as value of height will change. Now, we
        // can handle this as well by observing to the height of screen, however it would become
        // take some time to implement it. For brevity restarting the stream
        disposable.dispose();
    }
}
