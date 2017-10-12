package kr.or.fowi.daslim.daslim.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.databinding.ItemScheduleBinding;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.view.adapter.viewholder.ScheduleListViewHolder;

/**
 * Created by Rell on 2017. 10. 12..
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListViewHolder> {

    private List<ScheduleInfoItem> itemList;

    public ScheduleListAdapter() {
        itemList = new ArrayList<>();
    }

    @Override
    public ScheduleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemScheduleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_schedule, parent, false);

        ScheduleListViewHolder holder = new ScheduleListViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ScheduleListViewHolder holder, int position) {
        holder.setItem(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addItem(ScheduleInfoItem item) {
        itemList.add(item);
        notifyDataSetChanged();
    }
}
