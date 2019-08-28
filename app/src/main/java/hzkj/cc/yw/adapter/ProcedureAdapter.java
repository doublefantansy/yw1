package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vise.log.ViseLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.Circle;
import hzkj.cc.yw.MTextView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.Procedure;

public class ProcedureAdapter extends BaseAdapter<ProcedureAdapter.ViewHolder> {

  Context context;
  List<Procedure> datas;

  public ProcedureAdapter(Context context, List<Procedure> datas) {
    this.context = context;
    this.datas = datas;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.apply_buy_record_item, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    Procedure data = datas.get(i);
    viewHolder.itemView.measure(0, 0);
    ViseLog.d(viewHolder.advice.getMeasuredHeight());
    viewHolder.circle.initHeight(viewHolder.itemView.getMeasuredHeight());
    if (datas.size() == 1) {
      viewHolder.circle.isOnlyOne(true);
    } else {
      if (i == 0) {
        viewHolder.circle.setStatus(Circle.FIRST);
      } else if (i == getItemCount() - 1) {
        viewHolder.circle.setStatus(Circle.LAST);
      } else {
        viewHolder.circle.setStatus(Circle.MIDDLE);
      }
    }
    viewHolder.time.setText(data.getD_transaction_time_begin());
    viewHolder.advice.setText(data.getReview_reason());
    viewHolder.procedure.setText(data.getC_node_name());
    viewHolder.checker.setText(data.getUserInfo()
        .getUserName());
    viewHolder.result.setText(data.getReview_result());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.circle)
    Circle circle;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.time)
    MTextView time;
    @BindView(R.id.result)
    MTextView result;
    @BindView(R.id.checker)
    MTextView checker;
    @BindView(R.id.advice)
    MTextView advice;
    @BindView(R.id.procedure)
    MTextView procedure;

    public ViewHolder(@NonNull final View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
