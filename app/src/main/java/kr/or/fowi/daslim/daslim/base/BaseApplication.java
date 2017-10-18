package kr.or.fowi.daslim.daslim.base;

import android.app.Application;
import android.content.Context;

import kr.or.fowi.daslim.daslim.etc.PP;


/**
 * Created by 이주영 on 2017-01-17.
 */

public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        PP.CREATE(context);
    }
}
