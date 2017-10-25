package kr.or.fowi.daslim.daslim.view.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kr.or.fowi.daslim.daslim.databinding.ItemScheduleBinding;
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
    }

    private final View.OnClickListener onItemClickListener = view -> {
        // TODO: 2017. 10. 25. 예약이 가능한 상태인지 체크

        // TODO: 2017. 10. 25. 취소해야하는건지 체크


        final Context context = binding.getRoot().getContext();
        Intent intent = new Intent(context, ReservationActivity.class);
        intent.putExtra("item", item);
        context.startActivity(intent);
    };
}
