package kr.or.fowi.daslim.daslim.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;

import kr.or.fowi.daslim.daslim.etc.DataManger;

/**
 * Created by Rell on 2017. 10. 2..
 */

public class JoinPresenterImpl implements JoinPresenter {
    private JoinPresenter.View view;
    private DataManger dataManger;

    public JoinPresenterImpl(View view) {
        this.view = view;
        dataManger = DataManger.getInstance();

    }

    @Override
    public void join(final String name, final String nick) {
        boolean isValid = checkValid(name, nick);
        if (!isValid) return;
        final String tel = getPhoneNumber();
    }

    private boolean checkValid(String name, String nick) {
        if (name.isEmpty()) {
            return false;
        }

        if (nick.isEmpty()) {
            return false;
        }
        return true;
    }

    private String getPhoneNumber() {
        TelephonyManager t = (TelephonyManager) view.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return t.getLine1Number();
    }
}
