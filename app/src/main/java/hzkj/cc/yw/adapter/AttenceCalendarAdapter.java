package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.AttenceInfo;

public class AttenceCalendarAdapter extends BaseAdapter<AttenceCalendarAdapter.ViewHolder> {

  Context context;
  Map<String, AttenceInfo> map;

  public AttenceCalendarAdapter(Context context, Map<String, AttenceInfo> map) {
    this.context = context;
    this.map = map;
  }

  @NonNull
  @Override
  public AttenceCalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.attence_calendar_item, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull AttenceCalendarAdapter.ViewHolder viewHolder, int i) {
    String key = (String) map.keySet()
        .toArray()[i];
    viewHolder.address.setText(map.get(key)
        .getBaiduGpsComment());
    viewHolder.time.setText(map.get(key)
        .getAttenceTime());
    switch (map.get(key)
        .getLeaveType()) {
      case AttenceInfo.NORMAL: {
        viewHolder.status.setText("正常");
        viewHolder.status.setTextColor(context.getResources()
            .getColor(R.color.myGreen));
        break;
      }
      case AttenceInfo.LATE: {
        viewHolder.status.setText("迟到");
        viewHolder.status.setTextColor(context.getResources()
            .getColor(R.color.red));
        break;
      }
      case AttenceInfo.EARLY: {
        viewHolder.status.setText("早退");
        viewHolder.status.setTextColor(context.getResources()
            .getColor(R.color.red));
        break;
      }
    }
  }

  @Override
  public int getItemCount() {
    return map.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.status)
    TextView status;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
