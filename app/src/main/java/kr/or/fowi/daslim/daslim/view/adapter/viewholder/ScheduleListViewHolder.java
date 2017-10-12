package kr.or.fowi.daslim.daslim.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import kr.or.fowi.daslim.daslim.databinding.ItemScheduleBinding;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;

/**
 * Created by Rell on 2017. 10. 12..
 */

public class ScheduleListViewHolder extends RecyclerView.ViewHolder {
    private final ItemScheduleBinding binding;
    private ScheduleInfoItem item;

    public ScheduleListViewHolder(ItemScheduleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setItem(ScheduleInfoItem item) {
        this.item = item;

        // 회차
//        binding.sequence.setText(item.index);
        // 시간
        binding.time.setText(item.time);
        // 정원
        binding.to.setText(item.size + "");
    }
}
