package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.TimeBean;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

  List<TimeBean> timeBeans;
  Context context;

  public TimeAdapter(List<TimeBean> timeBeans, Context context) {
    this.timeBeans = timeBeans;
    this.context = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.time_item, viewGroup, false);
    return new TimeAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    viewHolder.name.setText(timeBeans.get(i)
        .getName());
    viewHolder.time.setText(timeBeans.get(i)
        .getTime());
    String text = null;
    String s[] = timeBeans.get(i)
        .getResult()
        .split(",");
    switch (s[1]) {
      case "1": {
        text = "流转";
        viewHolder.result.setTextColor(context.getResources()
            .getColor(R.color.hzBlue));
        break;
      }
      case "2": {
        text = "回退";
        viewHolder.result.setTextColor(context.getResources()
            .getColor(R.color.yellow));
        break;
      }
      case "3": {
        text = "回退逐步重走";
        break;
      }
      case "4": {
        text = "回退一步重走";
        break;
      }
      case "5": {
        if (s[0].equals("0")) {
          text = "拒绝";
          viewHolder.result.setTextColor(context.getResources()
              .getColor(R.color.myRed));
        } else {
          text = "通过";
          viewHolder.result.setTextColor(context.getResources()
              .getColor(R.color.hzBlue));
        }
        break;
      }
    }
    viewHolder.result.setText(text);
    viewHolder.image.setImageResource(timeBeans.get(i)
        .getSrc());
    if (i == timeBeans.size() - 1) {
      viewHolder.down.setVisibility(View.GONE);
    }
    viewHolder.content.setText(timeBeans.get(i)
        .getContent());
  }

  @Override
  public int getItemCount() {
    return timeBeans.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text)
    SuperTextView textView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.image)
    ImageView image;
    //        @BindView(R.id.up)
//        TextView up;
    @BindView(R.id.down)
    TextView down;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
