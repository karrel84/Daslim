package kr.or.fowi.daslim.daslim.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 이주영 on 2017-01-17.
 */

public class BaseActivity extends AppCompatActivity {
    private int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(orientation);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        parseExtra();
        loadOnce();
        reload();
        updateUI();
    }

    /**
     * Intent로 받은 데이터 파싱
     */
    final public void parseExtra() {
        try {
            onParseExtra();
        } catch (Exception e) {
        }
    }

    final public void loadOnce() {
        onLoadOnce();
    }

    final public void reload() {
        onReload();
    }

    final public void clear() {
        try {
            onClear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final public void load() {
        try {
            onLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final public void updateUI() {
        try {
            onUpdateUI();
        } catch (Exception e) {
        }
    }

    protected void onParseExtra() {
    }

    protected void onLoadOnce() {
    }

    protected void onReload() {
        clear();
        load();
    }

    protected void onClear() {
    }

    protected void onLoad() {
    }

    protected void onUpdateUI() {
    }
}
