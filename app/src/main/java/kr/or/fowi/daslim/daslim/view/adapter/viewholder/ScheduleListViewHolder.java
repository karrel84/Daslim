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
        binding.sequence.setText(String.format("%s회", item.index));
        // 시간
        binding.time.setText(item.time);
        // 정원
        binding.to.setText(String.format("%s/%s", item.curCount, item.maxCount));
    }

    private final View.OnClickListener onItemClickListener = view -> {
        final Context context = binding.getRoot().getContext();
        context.startActivity(new Intent(context, ReservationActivity.class));
    };
}
