package kr.or.fowi.daslim.daslim.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.base.BaseActivity;
import kr.or.fowi.daslim.daslim.databinding.ActivityReservationBinding;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.presenter.ReservationPresenter;

public class ReservationActivity extends BaseActivity implements ReservationPresenter.View {

    private ReservationPresenter presenter;
    private ActivityReservationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ReservationPresenterImpl(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation);
    }

    @Override
    protected void onParseExtra() {
        super.onParseExtra();
        ScheduleInfoItem item = getIntent().getParcelableExtra("item");
        RLog.e("item : " + item);
        presenter.setScheduleInfoItem(item);
    }

    @Override
    protected void onLoadOnce() {
        super.onLoadOnce();
        setupButtons();
    }

    private void setupButtons() {
        binding.cancel.setOnClickListener(view -> finish());
        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int people = binding.txtNumPeople.getSelectedItemPosition() + 1;

                presenter.reservation(people);
            }
        });
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        presenter.setupData();
    }

    @Override
    public void setClass(String className) {
        binding.txtClass.setText(className);
    }

    @Override
    public void setIndex(String index) {
        binding.txtCount.setText(index);
    }

    @Override
    public void setTime(String time) {
        binding.txtTime.setText(time);
    }

    @Override
    public void setMaxPeople(final int count) {
        List<String> spinnerArray = new ArrayList<>();
        // 2017. 10. 24. 예약인원추가
        for (int i = 0; i < count; i++) {
            spinnerArray.add(String.format("%s명", i + 1));
        }

        Spinner spinner = binding.txtNumPeople;
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }
}
