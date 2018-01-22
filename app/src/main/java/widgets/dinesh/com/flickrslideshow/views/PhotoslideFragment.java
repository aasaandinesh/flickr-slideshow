package widgets.dinesh.com.flickrslideshow.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import widgets.dinesh.com.flickrslideshow.FlickrApplication;
import widgets.dinesh.com.flickrslideshow.R;
import widgets.dinesh.com.flickrslideshow.viewmodels.SlideFragmentViewModel;
import widgets.dinesh.com.flickrslideshow.views.di.DaggerFlickrFragmentComponent;
import widgets.dinesh.com.flickrslideshow.views.di.FlickrFragmentModule;

/**
 * Created by ajmac1005 on 21/01/18.
 */

public class PhotoslideFragment extends Fragment {
    private static final String URLS_LIST = "urls_list";


    @Inject
    SlideFragmentViewModel viewModel;

    LinearLayout llPhotos;

    public static PhotoslideFragment newinstance(ArrayList<String> urls){
        PhotoslideFragment fragment = new PhotoslideFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(URLS_LIST, urls);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerFlickrFragmentComponent.builder()
                .applicationComponent(FlickrApplication.getComponent())
                .flickrFragmentModule(new FlickrFragmentModule())
                .build()
                .inject(this);
        updateView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_photo_slide_page, container, false);
        llPhotos = rootView.findViewById(R.id.ll_photos);



        return rootView;
    }

    private void updateView() {
        List<String> urls = getArguments().getStringArrayList(URLS_LIST);

        if (urls != null) {

            for (String url : urls) {
                ImageView imageView = new ImageView(getContext());
                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                llParams.gravity = Gravity.CENTER;
                imageView.setLayoutParams(llParams);
                viewModel.loadImage(imageView, url);
                llPhotos.addView(imageView);

            }
        }

    }


}


