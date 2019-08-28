package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.SingleGood;

public class DeliverSingleDetailAdapter extends BaseAdapter<DeliverSingleDetailAdapter.ViewHolder> {

  Context context;
  List<SingleGood> datas;


  public DeliverSingleDetailAdapter(Context context, List<SingleGood> datas) {
    this.context = context;
    this.datas = datas;
  }


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.item_deliver_single_detail, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    SingleGood data = datas.get(i);
    viewHolder.name.setText(data.getSupplies_name());
    viewHolder.unit.setText(data.getSupplies_unit());
    viewHolder.number.setText(data.getSupplies_count());
    viewHolder.remark.setText(data.getRemark());
    viewHolder.type.setText(data.getSupplies_category());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends MyViewHolder {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.type)
    TextView type;


    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
