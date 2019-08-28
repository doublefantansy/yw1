package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xuexiang.xui.widget.textview.badge.BadgeView;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.RentCarAdapter.ViewHolder;
import hzkj.cc.yw.bean.RentCarInfo;
import java.util.List;

public class RentCarAdapter extends BaseAdapter<ViewHolder> {

  Context context;
  List<RentCarInfo> rentCarInfos;
  boolean isReview;
  BadgeView badgeView;
//

  public RentCarAdapter(Context context, List<RentCarInfo> rentCarInfos, boolean isReview
  ) {
    this.context = context;
    this.rentCarInfos = rentCarInfos;
    this.isReview = isReview;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.rentcar_item, viewGroup, false);
    return new RentCarAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    RentCarInfo rentCarInfo = rentCarInfos.get(i);
    viewHolder.applyTime.setText(rentCarInfo.getApplication_date());
    viewHolder.useCartTime.setText(rentCarInfo.getApplication_carDate());
    viewHolder.useCartProject.setText(rentCarInfo.getProjectInfo().getProject_name());
    viewHolder.applyUser.setText(
        rentCarInfo.getUserInfo().getDepartment().getDeptName() + "-" + rentCarInfo.getUserInfo()
            .getUser_Name());
    if ((rentCarInfo.getPic2() == null
        || rentCarInfo.getPic1() == null) && rentCarInfo.getStatus() == RentCarInfo.REVIEWSUCCESS
        && !isReview) {
      viewHolder.badgeView.setVisibility(View.VISIBLE);
      viewHolder.cardView.setBackground(context.getDrawable(R.drawable.notify_card_view_style));
    } else {
      viewHolder.badgeView.setVisibility(View.GONE);
      viewHolder.cardView.setBackground(context.getDrawable(R.drawable.normal_style));
    }
  }

  @Override
  public int getItemCount() {
    return rentCarInfos.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cardView)
    LinearLayout cardView;
    @BindView(R.id.useCartTime)
    TextView useCartTime;
    @BindView(R.id.useCartProject)
    TextView useCartProject;
    @BindView(R.id.applyUser)
    TextView applyUser;
    @BindView(R.id.applyTime)
    TextView applyTime;
    BadgeView badgeView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      badgeView = new BadgeView(context);
      badgeView = new BadgeView(context);
      badgeView.bindTarget(cardView);
      badgeView.setGravityOffset(10, true)
          .setBadgeTextSize(12, true)
          .setBadgeText("请上传图片");
      badgeView.setVisibility(View.GONE);
    }
  }
}
