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
import hzkj.cc.yw.bean.RepairInfoDetail;

public class GoToRepairDetailRecordAdapter extends
    hzkj.cc.ccrecyclerview.BaseAdapter<GoToRepairDetailRecordAdapter.ViewHolder> {

  Context context;
  List<RepairInfoDetail> datas;

  public GoToRepairDetailRecordAdapter(Context context, List<RepairInfoDetail> datas) {
    this.context = context;
    this.datas = datas;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.go_to_repair_detail_item, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    final RepairInfoDetail data = datas.get(i);
    viewHolder.project.setText(data.getProjectInfo()
        .getProject_name());
    viewHolder.suppliesName.setText(data.getGood()
        .getSupplies_name());
    viewHolder.number.setText(data.getSl() + "");
    viewHolder.reason.setText(data.getBackBe());
    viewHolder.status.setText(data.getStatusString());
    viewHolder.checkedResult.setText(data.getBackDetailLast());
    viewHolder.suppliesName.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View view = LayoutInflater.from(context)
            .inflate(R.layout.dialog_apply_buy_detail, null);
        ((TextView) view.findViewById(R.id.supplies_name)).setText(data.getGood()
            .getSupplies_name());
        ((TextView) view.findViewById(R.id.supplies_model)).setText(data.getGood()
            .getSupplies_model());
        ((TextView) view.findViewById(R.id.supplies_brand)).setText(data.getGood()
            .getSupplies_brand());
        ((TextView) view.findViewById(R.id.supplies_category)).setText(data.getGood()
            .getSupplies_category());
        ((TextView) view.findViewById(R.id.supplies_unit)).setText(data.getGood()
            .getSupplies_unit());
        ((TextView) view.findViewById(R.id.supplies_cost)).setText(data.getGood()
            .getSupplies_cost());
        ((TextView) view.findViewById(R.id.supplies_sales)).setText(data.getGood()
            .getSupplies_sales());
        ((TextView) view.findViewById(R.id.supplies_properties)).setText(data.getGood()
            .getSupplies_properties());
        ((TextView) view.findViewById(R.id.supplies_time)).setText(data.getGood()
            .getSupplies_time());
        ((TextView) view.findViewById(R.id.supplies_checker)).setText(data.getGood()
            .getSupplies_checker());
        ((TextView) view.findViewById(R.id.supplies_wholeSaler)).setText(data.getGood()
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

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.suppliesName)
    TextView suppliesName;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.project)
    TextView project;
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.checkedResult)
    TextView checkedResult;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
