package kr.or.fowi.daslim.daslim.presenter;

import android.Manifest;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.etc.DataManger;
import kr.or.fowi.daslim.daslim.etc.PP;
import kr.or.fowi.daslim.daslim.event.FirebaseEvent;
import kr.or.fowi.daslim.daslim.model.UserInfo;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rell on 2017. 10. 2..
 */

public class JoinPresenterImpl implements JoinPresenter {
    private JoinPresenter.View view;
    private DataManger dataManger;
    private String telNumber, name, nick;
    private List<UserInfo> userInfos;

    public JoinPresenterImpl(View view) {
        this.view = view;
        dataManger = DataManger.getInstance();

        TedPermission.with(view.getContext())
                .setPermissionListener(permissionlistener)
                .setPermissions(Manifest.permission.READ_PHONE_STATE)
                .check();

        subscribeUserInfoEvent();
    }

    private void subscribeUserInfoEvent() {
        Observable<List<UserInfo>> observable = FirebaseEvent.getInstance().getUserObservable();
        observable
                .onBackpressureDrop()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct();

        observable.subscribe(userInfos -> {
            for (UserInfo info : userInfos) {
                // check join success
                checkJoined(info);


            }

            this.userInfos = userInfos;
        });
    }

    private void checkJoined(UserInfo info) {
        if (this.name != null && this.nick != null) {
            if (info.name.equals(this.name) && info.nick.equals(this.nick)) {
                PP.name.set(this.name);
                PP.nick.set(this.nick);

                view.finish();
            }
        }
    }

    @Override
    public void join(final String name, final String nick) {
        // 유효성 체크
        boolean isValid = checkValid(name, nick);
        if (!isValid) return;

        this.name = name;
        this.nick = nick;

        // 아이디 등록
        dataManger.join(name, nick, telNumber);
    }

    @Override
    public void changedNick(String nick) {
        if (userInfos == null) return;

        // 같은 닉테임이 있는지 테스트
        boolean haveNick = false;
        for (UserInfo info : userInfos) {
            if (info.nick.equals(nick)) {
                haveNick = true;
                break;
            }
        }

        if(haveNick) view.duplicatedNick();
        else view.enableNick();
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            telNumber = getPhoneNumber();
            RLog.d("telNumber : " + telNumber);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            telNumber = "";
        }


    };

    private boolean checkValid(String name, String nick) {
        if (nick.isEmpty()) {
            view.showMessage("닉네임을 입력해주세요");
            return false;
        }
        if (name.isEmpty()) {
            view.showMessage("이름을 입력해주세요");
            return false;
        }
        return true;
    }

    private String getPhoneNumber() {
        TelephonyManager t = (TelephonyManager) view.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return t.getLine1Number();
    }
}
