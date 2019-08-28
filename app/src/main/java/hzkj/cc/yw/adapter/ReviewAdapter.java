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
import hzkj.cc.yw.LabelLinearLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.TransActionInfo;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Holder> {

  List<TransActionInfo> reviewInfos;
  Context context;
  ClickItemListenner clicKItemListenner;

  public ReviewAdapter(Context context, List<TransActionInfo> rentCarInfos) {
    this.reviewInfos = rentCarInfos;
    this.context = context;
  }

  public void setListenner(ClickItemListenner clicKItemListenner) {
    this.clicKItemListenner = clicKItemListenner;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.rentcarreview_item, viewGroup, false);
    return new ReviewAdapter.Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, final int i) {
    holder.person.setText(reviewInfos.get(i)
        .getUserName());
    holder.time.setText(reviewInfos.get(i)
        .getD_transAction_time_begin());
    holder.certificate.setText(reviewInfos.get(i)
        .getC_node_name());
//        holder.useCarTime.setText(reviewInfos.get(i).getD_transAction_time_begin());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        clicKItemListenner.click(i);
      }
    });
    switch (reviewInfos.get(i)
        .getN_transAction_state()) {
      case 0: {
        holder.status.setText("待审核");
        holder.status.setTextColor(context.getResources()
            .getColor(R.color.myRed));
        break;
      }
      case 1: {
        holder.status.setText("已通过");
        holder.status.setTextColor(context.getResources()
            .getColor(R.color.hzBlue));
        break;
      }
      case 5: {
        if (reviewInfos.get(i)
            .getN_business_state() == 0) {
          holder.status.setText("未通过");
          holder.status.setTextColor(context.getResources()
              .getColor(R.color.myRed));
        } else {
          holder.status.setText("已通过");
          holder.status.setTextColor(context.getResources()
              .getColor(R.color.hzBlue));
        }
        break;
      }
    }
    holder.label.setTextContent(reviewInfos.get(i)
        .getC_process_name()
        .split("流程")[0]);
  }

  @Override
  public int getItemCount() {
    return reviewInfos.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.certificate)
    TextView certificate;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.label)
    LabelLinearLayout label;

    public Holder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
