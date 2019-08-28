package hzkj.cc.yw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.ClientInfo;

public class SearchAdapter extends BaseAdapter {

  Context context;
  List<ClientInfo> clientInfos;
  ClickItemListenner clicKItemListenner;

  @Override
  public int getCount() {
    return clientInfos.size();
  }

  public void setClickItemListenner(ClickItemListenner clicKItemListenner) {
    this.clicKItemListenner = clicKItemListenner;
  }

  public SearchAdapter(Context context, List<ClientInfo> clientInfos) {
    this.context = context;
    this.clientInfos = clientInfos;
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    Holder holder = null;
    if (convertView == null) {
      holder = new Holder();
      convertView = LayoutInflater.from(context)
          .inflate(R.layout.list_item, null);
      holder.mText = convertView.findViewById(R.id.text);
      convertView.setTag(holder);
    } else {
      holder = (Holder) convertView.getTag();
    }
    holder.mText.setText(clientInfos.get(position)
        .getCustomer_name() + "  " + clientInfos.get(position)
        .getCustomerType_name() + "  " + clientInfos.get(position)
        .getProject_name() + "  " + clientInfos.get(position)
        .getProjectType_name());
    convertView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        clicKItemListenner.click(position);
      }
    });
    return convertView;
  }

  class Holder {

    private TextView mText;
  }
}
