package kr.or.fowi.daslim.daslim.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.databinding.ItemReservationedScheduleBinding;
import kr.or.fowi.daslim.daslim.databinding.ItemReserveTitleBinding;
import kr.or.fowi.daslim.daslim.databinding.ItemScheduleBinding;
import kr.or.fowi.daslim.daslim.model.ReservationItem;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.view.adapter.viewholder.ReserveContentViewHolder;
import kr.or.fowi.daslim.daslim.view.adapter.viewholder.ReserveTitleViewHolder;
import kr.or.fowi.daslim.daslim.view.adapter.viewholder.ScheduleListViewHolder;

/**
 * Created by Rell on 2017. 10. 12..
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ScheduleInfoItem> scheduleList;
    private List<ReservationItem> myReserveList;
    // 스케쥴 정보
    private int TYPE_SCHEDULE = 0;
    // 예약 타이틀
    private int TYPE_RESERVE_TITLE = 1;
    // 예약 정보
    private int TYPE_RESERVE_CONTENT = 2;

    public ScheduleListAdapter() {
        scheduleList = new ArrayList<>();
        myReserveList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RLog.e("onCreateViewHolder");
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_SCHEDULE) {
            RLog.e("TYPE_SCHEDULE");
            ItemScheduleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_schedule, parent, false);
            holder = new ScheduleListViewHolder(binding);
        } else if (viewType == TYPE_RESERVE_TITLE) {
            RLog.e("TYPE_RESERVE_TITLE");
            ItemReserveTitleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_reserve_title, parent, false);
            holder = new ReserveTitleViewHolder(binding);
        } else { // 예약내역
            RLog.e("TYPE_RESERVE_CONTENT");
            ItemReservationedScheduleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_reservationed_schedule, parent, false);
            holder = new ReserveContentViewHolder(binding);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int viewType = getItemViewType(position);
        if (viewType == TYPE_SCHEDULE) {
            ((ScheduleListViewHolder) holder).setItem(scheduleList.get(position));
        }
        if (viewType == TYPE_RESERVE_CONTENT) {
            ((ReserveContentViewHolder) holder).setItem(myReserveList.get(position - scheduleList.size() - 1));
        }
    }

    @Override
    public int getItemCount() {
        // 나의 예약 정보가 없으면 스케쥴만 표기하면된다
        if (myReserveList.isEmpty()) return scheduleList.size();
        else return scheduleList.size() + myReserveList.size() + 1; // 스케쥴 정보와 나의 예약정보를 합친 사이즈를 보낸다.
    }

    @Override
    public int getItemViewType(int position) {
        // 나의 예약 내역이 없으면 스케쥴 정보이다
        if (position < scheduleList.size()) return TYPE_SCHEDULE;

        // 스케쥴 정보 바로 다음 아이템은 타이틀 헤더이다
        if (position == scheduleList.size()) return TYPE_RESERVE_TITLE;

        return TYPE_RESERVE_CONTENT;
    }

    public void addItem(ScheduleInfoItem item) {
        scheduleList.add(item);
        notifyDataSetChanged();
    }

    public void addReserveItem(ReservationItem item) {
        myReserveList.add(item);
        notifyDataSetChanged();
    }

    /**
     * 아이템의 변경에 대한 갱신을 진행
     */
    public void updateInfo(ScheduleInfo info) {
        RLog.d("myReserveList");
//        scheduleList.clear();
        for (ScheduleInfoItem item : info.scheduleInfoItems) {
//            scheduleList.add(item);
//            notifyDataSetChanged();
            addItem(item);
        }

        myReserveList.clear();
        for (ScheduleInfoItem item : info.scheduleInfoItems) {
            if (item.isReservationed()) {
//                myReserveList.add(item.getReservationInfo());
//                notifyDataSetChanged();
                addReserveItem(item.getReservationInfo());
            }
        }

        RLog.e("title " + info.title);
//        RLog.e("notifyItemRangeChange " + getItemCount());
//        notifyItemRangeChanged(0, getItemCount());
//        notifyDataSetChanged();
    }
}
