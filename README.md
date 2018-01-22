## Flickr SlideShow
An app which takes consumes Flickr JSONP API and converts them into a slideshow.

Criterions:
1. The app needs to download the photos in batches. The number of photos in the first batch will be calculated based on the device screen height.  For example, if first 3 photos will comfortably fit in device’s screen but not the 4th one, then only first 3 photos should be downloaded in the 1st batch and loaded in the view. In case, if any one photo is larger than the device screen size then let us skip showing that photo.

2. Once the 1st batch of photos is downloaded, they should be loaded in the view. And in the background, the number of photos for next batch should be calculated and the download should be initiated. 
After 10 seconds the next set of photos should be loaded in the view and the view should scroll to next page automatically. In case, if the next batch of photos download is not complete by the 10th second then they should be loaded once the download completes. 

3. Like this let us do the slideshow of 100 photos.

Challenges:

* The Flickr API returns JSONP response, we need to look for converters which removes the extra element. 
* The Khichdi of Rx Streams :-/
  * The stream will consist of ```FlickrAPIWrappedData``` need to extract objects and generate a new Stream of items which will fit
  in a single screen
  
  * Once we receive these items, we need to pass on the first item immediatly, however put 10 seconds delay on subsequent items
  * Pass on this stream of items to the View layer which will add fragments, one of the challenge is that the images of next fragment
  needs to be downloaded upfront, but shall be displayed only after 10 seconds.
  
  The most difficult part was to extract the stream of objects and group them by their height such that they do not overshoot the 
  screen height.
  
  I could not find out any operator in native RxJava (There might be one) which would allow me to write custom conditions on grouping 
  of operator. I could have done it easily by having a global variable, buy boy! Thats a crime in Functional RxJava approach, you should
  be confined to your stream. Hence I was left with no other option but to write a [custom operator](https://github.com/ReactiveX/RxJava/wiki/Writing-operators-for-2.0).
  
  The operator is ```CustomRxImageMergeOperator``` and it happened to be my first custom RxJava operator :D
  This guy piles up the objects until the total height exceeds the height of the screen. Here is some code:
  
  ```
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

            }```

Here is the link to the overall code of [Custom Image Merge Operator](https://github.com/aasaandinesh/flickr-slideshow/blob/master/app/src/main/java/widgets/dinesh/com/flickrslideshow/base/CustomRxImageMergeOperator.java)
Maybe I would have over-engineered things, but thats the :( but that's the solution I could have thought off. After writing the custom 
operator I came across ```bufferUntill``` in [RxExtensions](https://github.com/akarnokd/RxJava2Extensions#flowabletransformersbufferwhile)
with accepts custom ```Predicate```. But not able to find such solution in any of the Native operators.

