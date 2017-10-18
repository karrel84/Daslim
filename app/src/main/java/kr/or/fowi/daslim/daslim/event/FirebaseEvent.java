package kr.or.fowi.daslim.daslim.event;

import java.util.List;

import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.UserInfo;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rell on 2017. 10. 16..
 */

public class FirebaseEvent {
    private static FirebaseEvent mInstance = new FirebaseEvent();
    public PublishSubject<List<ScheduleInfo>> mScheduleSubject;
    public PublishSubject<List<UserInfo>> mUserInfoSubject;

    private FirebaseEvent() {
        mScheduleSubject = PublishSubject.create();
        mUserInfoSubject = PublishSubject.create();

    }

    public static FirebaseEvent getInstance() {
        return mInstance;
    }

    public void sendScheduleEvent(List<ScheduleInfo> msg) {
        mScheduleSubject.onNext(msg);
    }

    public void sendUserEvent(List<UserInfo> msg) {
        mUserInfoSubject.onNext(msg);
    }

    public Observable<List<ScheduleInfo>> getSheduleObservable() {
        return mScheduleSubject;
    }

    public Observable<List<UserInfo>> getUserObservable() {
        return mUserInfoSubject;
    }

}
