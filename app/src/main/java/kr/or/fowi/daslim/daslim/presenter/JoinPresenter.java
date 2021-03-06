package kr.or.fowi.daslim.daslim.presenter;

import android.content.Context;

/**
 * Created by Rell on 2017. 10. 2..
 */

public interface JoinPresenter {

    void join(String name, String nick);

    void changedNick(String s);

    interface View {

        Context getContext();

        void showMessage(String s);

        void finish();

        void duplicatedNick();

        void enableNick();
    }
}
