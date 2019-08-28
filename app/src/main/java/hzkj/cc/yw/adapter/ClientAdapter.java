package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ClientAdapter.ViewHolder;
import hzkj.cc.yw.bean.ClientInfo;
import java.util.List;

public class ClientAdapter extends BaseAdapter<ViewHolder> {

  Context context;
  List<ClientInfo> datas;

  public ClientAdapter(Context context, List<ClientInfo> clientInfos) {
    this.context = context;
    this.datas = clientInfos;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.recycler_item_client, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    ClientInfo data = datas.get(i);
    viewHolder.clientName.setText(data.getCustomer_name());
    viewHolder.clientType.setText(data.getCustomerType_name());
    viewHolder.project.setText(data.getProject_name());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  class ViewHolder extends MyViewHolder {

    @BindView(R.id.clientName)
    TextView clientName;
    @BindView(R.id.clientType)
    TextView clientType;
    @BindView(R.id.project)
    TextView project;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
