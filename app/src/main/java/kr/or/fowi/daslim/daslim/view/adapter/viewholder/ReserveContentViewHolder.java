package kr.or.fowi.daslim.daslim.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import kr.or.fowi.daslim.daslim.databinding.ItemReservationedScheduleBinding;
import kr.or.fowi.daslim.daslim.model.ReservationItem;

/**
 * Created by Rell on 2017. 10. 12..
 */

public class ReserveContentViewHolder extends RecyclerView.ViewHolder {
    private ItemReservationedScheduleBinding binding = null;
    private ReservationItem item;


    public ReserveContentViewHolder(ItemReservationedScheduleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setItem(ReservationItem item) {
        this.item = item;

        binding.className.setText(item.className);
        // 회차
        binding.sequence.setText(item.index);
        // 시간
        binding.time.setText(item.time);
        // 정원
        binding.people.setText(item.people + "명");

    }
}
