package kr.or.fowi.daslim.daslim.event;

import java.util.List;

import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rell on 2017. 10. 16..
 */

public class FirebaseEvent {
    private static FirebaseEvent mInstance = new FirebaseEvent();
    public PublishSubject<List<ScheduleInfo>> mSubject;

    private FirebaseEvent() {
        mSubject = PublishSubject.create();
    }

    public static FirebaseEvent getInstance() {
        return mInstance;
    }

    public void sendEvent(List<ScheduleInfo> msg) {
        mSubject.onNext(msg);
    }

    public Observable<List<ScheduleInfo>> getObservable() {
        return mSubject;
    }

}
