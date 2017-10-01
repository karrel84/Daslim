package kr.or.fowi.daslim.daslim.presenter;

/**
 * Created by kimmihye on 2017. 10. 1..
 */

public class MainPresenterImpl implements MainPresenter {
    private MainPresenter.View view;

    public MainPresenterImpl(MainPresenter.View view) {
        this.view = view;
    }
}
