package widgets.dinesh.com.flickrslideshow.base.data;


import io.reactivex.Scheduler;


public interface PostExecutionThread {
    Scheduler getScheduler();
}
