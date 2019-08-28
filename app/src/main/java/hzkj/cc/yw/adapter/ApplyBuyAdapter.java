package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.ApplyBuyInfo;

public class ApplyBuyAdapter extends
    hzkj.cc.ccrecyclerview.BaseAdapter<ApplyBuyAdapter.ViewHolder> {

  List<ApplyBuyInfo> datas;
  Context context;

  public ApplyBuyAdapter(List<ApplyBuyInfo> datas, Context context) {
    this.datas = datas;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.apply_buy_item, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    ApplyBuyInfo applyBuyInfo = datas.get(i);
    viewHolder.applyBuyId.setText(applyBuyInfo.getBuyId() + "");
    viewHolder.applyBuyIntroduction.setText(applyBuyInfo.getYt());
    viewHolder.applyBuyTime.setText(applyBuyInfo.getApplicationDate() + "");
    viewHolder.applyBuyPerson.setText(applyBuyInfo
        .getUserInfo()
        .getUserName());
    if (applyBuyInfo.getDepartment() != null) {
      viewHolder.applyBuyDepartment.setText(applyBuyInfo.getDepartment()
          .getDeptName());
    }
    viewHolder.procedure.setText(applyBuyInfo.getProcedure()
        .getC_node_name());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.applyBuyId)
    TextView applyBuyId;
    @BindView(R.id.applyBuyIntroduction)
    TextView applyBuyIntroduction;
    @BindView(R.id.applyBuyTime)
    TextView applyBuyTime;
    @BindView(R.id.applyBuyPerson)
    TextView applyBuyPerson;
    @BindView(R.id.applyBuyDepartment)
    TextView applyBuyDepartment;
    @BindView(R.id.procedure)
    TextView procedure;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
