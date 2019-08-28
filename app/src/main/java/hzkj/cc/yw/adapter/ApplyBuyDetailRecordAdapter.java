package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.ApplyBuyInfoDetail;

public class ApplyBuyDetailRecordAdapter extends
    hzkj.cc.ccrecyclerview.BaseAdapter<ApplyBuyDetailRecordAdapter.Viewholder> {

  List<ApplyBuyInfoDetail> datas;
  Context context;

  public ApplyBuyDetailRecordAdapter(List<ApplyBuyInfoDetail> datas, Context context) {
    this.datas = datas;
    this.context = context;
  }

  @NonNull
  @Override
  public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new Viewholder(LayoutInflater.from(context)
        .inflate(R.layout.apply_buy_detail_item, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
    final ApplyBuyInfoDetail data = datas.get(i);
    viewholder.supplies.setText(data
        .getProcedureGood()
        .getSupplies_name());
    viewholder.xqsl.setText(data.getXqsl() + "");
    viewholder.hdsl.setText(data.getHdsl() + "");
    viewholder.zksl.setText(data.getZksl() + "");
    viewholder.cgsl.setText(data.getCgsl() + "");
    viewholder.supplies.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View view = LayoutInflater.from(context)
            .inflate(R.layout.dialog_apply_buy_detail, null);
        ((TextView) view.findViewById(R.id.supplies_name)).setText(data.getProcedureGood()
            .getSupplies_name());
        ((TextView) view.findViewById(R.id.supplies_model)).setText(data.getProcedureGood()
            .getSupplies_model());
        ((TextView) view.findViewById(R.id.supplies_brand)).setText(data.getProcedureGood()
            .getSupplies_brand());
        ((TextView) view.findViewById(R.id.supplies_category)).setText(data.getProcedureGood()
            .getSupplies_category());
        ((TextView) view.findViewById(R.id.supplies_unit)).setText(data.getProcedureGood()
            .getSupplies_unit());
        ((TextView) view.findViewById(R.id.supplies_cost)).setText(data.getProcedureGood()
            .getSupplies_cost());
        ((TextView) view.findViewById(R.id.supplies_sales)).setText(data.getProcedureGood()
            .getSupplies_sales());
        ((TextView) view.findViewById(R.id.supplies_properties)).setText(data.getProcedureGood()
            .getSupplies_properties());
        ((TextView) view.findViewById(R.id.supplies_time)).setText(data.getProcedureGood()
            .getSupplies_time());
        ((TextView) view.findViewById(R.id.supplies_checker)).setText(data.getProcedureGood()
            .getSupplies_checker());
        ((TextView) view.findViewById(R.id.supplies_wholeSaler)).setText(data.getProcedureGood()
            .getSupplies_wholeSaler());
        new MaterialDialog.Builder(context).customView(view, false)
            .build()
            .show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }


  class Viewholder extends RecyclerView.ViewHolder {

    @BindView(R.id.supplies)
    TextView supplies;
    @BindView(R.id.xqsl)
    TextView xqsl;
    @BindView(R.id.hdsl)
    TextView hdsl;
    @BindView(R.id.zksl)
    TextView zksl;
    @BindView(R.id.cgsl)
    TextView cgsl;

    public Viewholder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
