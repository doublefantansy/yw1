package hzkj.cc.yw.adapter;

import android.content.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;
import butterknife.*;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.*;
import java.util.*;

public class WorkOrderAdapter extends BaseAdapter<WorkOrderAdapter.ViewHolder> {

  List<WorkOrderInfo> workOrderInfos;
  Context context;

  public WorkOrderAdapter(List<WorkOrderInfo> workOrderInfos, Context context) {
    this.workOrderInfos = workOrderInfos;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.recycler_item_work_order, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    viewHolder.createTime.setText(workOrderInfos.get(i).getWorkOrders_date());
    viewHolder.endTime.setText(workOrderInfos.get(i).getWorkOrders_endDate());
    viewHolder.agency.setText(
        workOrderInfos.get(i).getUserInfo().getDepartment().getParent().getDeptName() + "-" + workOrderInfos
            .get(i).getUserInfo().getDepartment().getDeptName());
    viewHolder.project.setText(workOrderInfos.get(i).getProjectInfo().getProject_name());
    viewHolder.client.setText(workOrderInfos.get(i).getClientInfo().getCustomer_name());
  }

  @Override
  public int getItemCount() {
    return workOrderInfos.size();
  }

  class ViewHolder extends MyViewHolder {

    @BindView(R.id.createTime)
    TextView createTime;
    @BindView(R.id.endTime)
    TextView endTime;
    @BindView(R.id.agency)
    TextView agency;
    @BindView(R.id.project)
    TextView project;
    @BindView(R.id.client)
    TextView client;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
