package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuexiang.xui.widget.button.ButtonView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.DeliverInfo;

public class DeliverProcedureAdapter extends BaseAdapter<DeliverProcedureAdapter.ViewHolder> {

  Context context;
  List<DeliverInfo> datas;
  ClickItemListenner listenner;

  public void setListenner(ClickItemListenner listenner) {
    this.listenner = listenner;
  }

  public DeliverProcedureAdapter(Context context, List<DeliverInfo> datas) {
    this.context = context;
    this.datas = datas;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.deliver_item, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    DeliverInfo deliverInfo = datas.get(i);
    viewHolder.deliverId.setText(deliverInfo.getLogisticsId() + "");
    viewHolder.postTime.setText(deliverInfo.getFhsj());
    viewHolder.getPerson.setText(deliverInfo.getGetDepartment()
        .getDeptName() + "-" + deliverInfo.getGetUser()
        .getUserName());
    viewHolder.postPerson.setText(deliverInfo.getPostDepartment()
        .getDeptName() + "-" + deliverInfo.getPostUser()
        .getUserName());
    viewHolder.deliverCompany.setText(deliverInfo.getKdgs()
        .getKdgs());
    viewHolder.deliverThingId.setText(deliverInfo.getKddh());
    viewHolder.status.setText(deliverInfo.getStateString());
    if (datas.get(i)
        .getClState() == DeliverInfo.HASNOTGET) {
      viewHolder.getDeliver.setVisibility(View.VISIBLE);
      viewHolder.getTime.setVisibility(View.GONE);
    } else {
      viewHolder.getTime.setVisibility(View.VISIBLE);
      viewHolder.getTime.setText(deliverInfo.getShsj());
      viewHolder.getDeliver.setVisibility(View.GONE);
    }
    viewHolder.getDeliver.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listenner.click(i);
      }
    });
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.deliverId)
    TextView deliverId;
    @BindView(R.id.postTime)
    TextView postTime;
    @BindView(R.id.getTime)
    TextView getTime;
    @BindView(R.id.postPerson)
    TextView postPerson;
    @BindView(R.id.deliverCompany)
    TextView deliverCompany;
    @BindView(R.id.deliverThingId)
    TextView deliverThingId;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.getPerson)
    TextView getPerson;
    @BindView(R.id.getDeliver)
    ButtonView getDeliver;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
