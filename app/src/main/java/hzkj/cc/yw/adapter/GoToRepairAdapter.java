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
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.RepairInfo;

public class GoToRepairAdapter extends BaseAdapter<GoToRepairAdapter.ViewHolder> {

  Context context;
  List<RepairInfo> datas;

  public GoToRepairAdapter(Context context, List<RepairInfo> datas) {
    this.context = context;
    this.datas = datas;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.go_to_repair_item, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    RepairInfo data = datas.get(i);
    viewHolder.repairId.setText(data.getBackInfoId() + "");
    viewHolder.time.setText(data.getOperDate());
    viewHolder.repairTime.setText(data.getBackTime());
    viewHolder.department.setText(data.getDepartment()
        .getDeptName());
    viewHolder.user.setText(data.getProcedure()
        .getUserInfo()
        .getUserName());
    viewHolder.procedure.setText(data.getProcedure()
        .getC_node_name());
    viewHolder.repairIntrodution.setText(data.getBackTitle());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.repairId)
    TextView repairId;
    @BindView(R.id.repairIntrodution)
    TextView repairIntrodution;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.repairTime)
    TextView repairTime;
    @BindView(R.id.project)
    TextView project;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.procedure)
    TextView procedure;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
