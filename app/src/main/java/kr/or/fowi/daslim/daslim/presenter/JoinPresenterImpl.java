package kr.or.fowi.daslim.daslim.presenter;

import android.Manifest;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;

import kr.or.fowi.daslim.daslim.etc.DataManger;

/**
 * Created by Rell on 2017. 10. 2..
 */

public class JoinPresenterImpl implements JoinPresenter {
    private JoinPresenter.View view;
    private DataManger dataManger;
    private String telNumber;

    public JoinPresenterImpl(View view) {
        this.view = view;
        dataManger = DataManger.getInstance();

        TedPermission.with(view.getContext())
                .setPermissionListener(permissionlistener)
                .setPermissions(Manifest.permission.READ_PHONE_STATE)
                .check();
    }

    @Override
    public void join(final String name, final String nick) {
        // 유효성 체크
        boolean isValid = checkValid(name, nick);
        if (!isValid) return;

        // 아이디 등록
        dataManger.join(name, nick, telNumber);
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
