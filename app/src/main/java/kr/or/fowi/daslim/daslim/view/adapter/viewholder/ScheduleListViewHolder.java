package kr.or.fowi.daslim.daslim.view.adapter.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import kr.or.fowi.daslim.daslim.base.BFDialog;
import kr.or.fowi.daslim.daslim.databinding.ItemScheduleBinding;
import kr.or.fowi.daslim.daslim.etc.DataManager;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.view.ReservationActivity;

/**
 * Created by Rell on 2017. 10. 12..
 */

public class ScheduleListViewHolder extends RecyclerView.ViewHolder {
    private ItemScheduleBinding binding = null;
    private ScheduleInfoItem item;

    public ScheduleListViewHolder(ItemScheduleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(onItemClickListener);
    }

    public void setItem(ScheduleInfoItem item) {
        this.item = item;

        // 회차
        binding.sequence.setText(item.index);
        // 시간
        binding.time.setText(item.time);
        // 정원
        binding.to.setText(String.format("%s/%s", item.reserveCount, item.maxReserve));

        // 상태
        Pair<String, Integer> pair = getStatus(item);
        binding.status.setText(pair.first);
        binding.status.setTextColor(pair.second);
    }

    private Pair<String, Integer> getStatus(ScheduleInfoItem item) {
        if (item.isReservationed()) return new Pair<>("예약됨", Color.BLUE);

        if (isReservationable()) {
            return new Pair<>("예약가능", Color.BLACK);
        } else {
            return new Pair<>("예약불가", Color.RED);
        }
    }

    private boolean isReservationable(){
        return item.maxReserve - item.reserveCount > 0;
    }

    private final View.OnClickListener onItemClickListener = view -> {
        // 이미 예약되어있으면 취소팝업 띄워줌
        if (isReservationed()) {
            cancelReservationed(view);
            return;
        }

        // 예약이 불가능한지 체크
        if(!isReservationable()){
            BFDialog.newInstance(view.getContext()).showDialog("예약이 불가능합니다.");
            return;
        }


        final Context context = binding.getRoot().getContext();
        Intent intent = new Intent(context, ReservationActivity.class);
        intent.putExtra("item", item);
        context.startActivity(intent);
    };

    private void cancelReservationed(View view) {
        DialogInterface.OnClickListener positiveListener = (dialogInterface, i) -> DataManager.getInstance().cancelReservation(item.className, item.index);
        DialogInterface.OnClickListener negativeListener = (dialogInterface, i) -> {};
        BFDialog.newInstance(view.getContext()).showDialog("예약을 취소하시겠습니까?", "확인", positiveListener, "취소", negativeListener);
    }

    private boolean isReservationed() {
        return item.isReservationed();
    }
}
