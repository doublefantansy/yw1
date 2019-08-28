package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuexiang.xui.widget.button.ButtonView;

import java.util.List;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.DeliverInfo;

public class DeliverSingleAdapter extends BaseAdapter<DeliverSingleAdapter.ViewHolder> {

  Context context;
  List<DeliverInfo> datas;
  ClickItemListenner listenner;

  public void setListenner(ClickItemListenner listenner) {
    this.listenner = listenner;
  }

  public DeliverSingleAdapter(Context context, List<DeliverInfo> datas) {
    this.context = context;
    this.datas = datas;
  }

  @NonNull
  @Override
  public DeliverSingleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new DeliverSingleAdapter.ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.item_deliver_single, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull DeliverSingleAdapter.ViewHolder viewHolder, final int i) {
    DeliverInfo deliverInfo = datas.get(i);
    viewHolder.deliverId.setText(deliverInfo.getLogisticsId() + "");
    viewHolder.postTime.setText(deliverInfo.getFhsj());
    viewHolder.postPerson.setText(deliverInfo.getPostDepartment()
        .getDeptName() + "-" + deliverInfo.getPostUser()
        .getUserName());
    viewHolder.deliverCompany.setText(deliverInfo.getKdgs()
        .getKdgs());
    viewHolder.deliverThingId.setText(deliverInfo.getKddh());
    viewHolder.status.setText(deliverInfo.getStateString());
    viewHolder.getDeliver.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listenner.click(i);
      }
    });
    if (datas.get(i)
        .getClState() == DeliverInfo.HASGET) {
      viewHolder.getDeliver.setVisibility(View.GONE);
      viewHolder.getTimeLayout.setVisibility(View.VISIBLE);
      viewHolder.getTime.setText(deliverInfo.getShsj());
    } else {
      viewHolder.getTimeLayout.setVisibility(View.GONE);
    }
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends MyViewHolder {

    @BindView(R.id.deliverId)
    TextView deliverId;
    @BindView(R.id.postTime)
    TextView postTime;
    @BindView(R.id.postPerson)
    TextView postPerson;
    @BindView(R.id.deliverCompany)
    TextView deliverCompany;
    @BindView(R.id.deliverThingId)
    TextView deliverThingId;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.getDeliver)
    ButtonView getDeliver;
    @BindView(R.id.getTime)
    TextView getTime;
    @BindView(R.id.getTimeLayout)
    LinearLayout getTimeLayout;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
