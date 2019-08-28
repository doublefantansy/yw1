package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.SingleGood;

public class DeliverGoodsAdapter extends RecyclerView.Adapter<DeliverGoodsAdapter.ViewHolder> {

  Context context;
  List<SingleGood> singleGoods;

  public DeliverGoodsAdapter(Context context, List<SingleGood> singleGoods) {
    this.context = context;
    this.singleGoods = singleGoods;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.item_child_not_first, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
    final SingleGood data = singleGoods.get(i);
    viewHolder.delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        singleGoods.remove(i);
        notifyDataSetChanged();
      }
    });
    viewHolder.supplyName.setText(data.getSupplies_name());
    viewHolder.supplyType.setText(data.getSupplies_category());
    viewHolder.supplyCount.setText(data.getSupplies_count());
    viewHolder.supplyUnit.setText(data.getSupplies_unit());
    viewHolder.remark.setText(data.getRemark());
    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View view = LayoutInflater.from(context)
            .inflate(R.layout.dialog_open_goods_choose, null);
        final MaterialDialog dialog = new MaterialDialog.Builder(context).customView(view, false)
            .title("编辑货物信息")
            .show();
        TextView cancel = view.findViewById(R.id.cancel);
        TextView submit = view.findViewById(R.id.submit);
        final MaterialEditText supplyName = view.findViewById(R.id.supplyName);
        final MaterialEditText supplyType = view.findViewById(R.id.supplyType);
        final MaterialEditText supplyCount = view.findViewById(R.id.supplyCount);
        final MaterialEditText supplyUnit = view.findViewById(R.id.supplyUnit);
        final MaterialEditText remark = view.findViewById(R.id.remark);
        supplyName.setText(data.getSupplies_name());
        supplyType.setText(data.getSupplies_category());
        supplyCount.setText(data.getSupplies_count());
        supplyUnit.setText(data.getSupplies_unit());
        remark.setText(data.getRemark());
        submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            data.setSupplies_name(supplyName.getText()
                .toString());
            data.setSupplies_category(supplyType.getText()
                .toString());
            data.setSupplies_count(supplyCount.getText()
                .toString());
            data.setSupplies_unit(supplyUnit.getText()
                .toString());
            data.setRemark(remark.getText()
                .toString());
            notifyDataSetChanged();
            dialog.dismiss();
          }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            dialog.dismiss();
          }
        });
      }
    });
  }

  @Override
  public int getItemCount() {
    return singleGoods.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.delete)
    ImageView delete;
    @BindView(R.id.supplyName)
    TextView supplyName;
    @BindView(R.id.supplyType)
    TextView supplyType;
    @BindView(R.id.supplyCount)
    TextView supplyCount;
    @BindView(R.id.supplyUnit)
    TextView supplyUnit;
    @BindView(R.id.remark)
    TextView remark;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
