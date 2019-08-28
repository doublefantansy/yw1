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
import hzkj.cc.yw.bean.ProcedureGoods;

public class DeliverProcedureDetailAdapter extends
    BaseAdapter<DeliverProcedureDetailAdapter.ViewHolder> {

  List<ProcedureGoods> datas;
  Context context;

  public DeliverProcedureDetailAdapter(List<ProcedureGoods> datas, Context context) {
    this.datas = datas;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.item_deliver_procedure_detail, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    viewHolder.suppliesName.setText(datas.get(i)
        .getGood()
        .getSupplies_name());
    viewHolder.supplyCount.setText(datas.get(i)
        .getCount() + "");
    viewHolder.supplyType.setText(datas.get(i)
        .getGood()
        .getSupplies_category());
    viewHolder.supplyUnit.setText(datas.get(i)
        .getGood()
        .getSupplies_unit());
    viewHolder.suppliesBrand.setText(datas.get(i)
        .getGood()
        .getSupplies_brand());
    viewHolder.suppliesWholeSaler.setText(datas.get(i)
        .getGood()
        .getSupplies_wholeSaler());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends MyViewHolder {

    @BindView(R.id.suppliesName)
    TextView suppliesName;
    @BindView(R.id.suppliesCount)
    TextView supplyCount;
    @BindView(R.id.suppliesType)
    TextView supplyType;
    @BindView(R.id.suppliesUnit)
    TextView supplyUnit;
    @BindView(R.id.suppliesBrand)
    TextView suppliesBrand;
    @BindView(R.id.suppliesWholeSaler)
    TextView suppliesWholeSaler;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
