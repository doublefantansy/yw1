package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.vise.log.ViseLog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.RentCarSearchAdapter.ViewHolder;
import hzkj.cc.yw.bean.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class RentCarSearchAdapter extends RecyclerView.Adapter<ViewHolder> {

  Context context;
  List<UserInfo> userInfos;
  ClickItemListenner clickItemListenner;
  List<UserInfo> selectedUsers = new ArrayList<>();

  public List<UserInfo> getSelectedUsers() {
    return selectedUsers;
  }

  public RentCarSearchAdapter(Context context, List<UserInfo> userInfos) {
    this.context = context;
    this.userInfos = userInfos;
  }

  public void setClickItemListenner(ClickItemListenner clickItemListenner) {
    this.clickItemListenner = clickItemListenner;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_rentcar_search, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
    viewHolder.checkBox.setChecked(false);
    for (UserInfo selectedUser : selectedUsers) {
      if (selectedUser.getUserId() == userInfos.get(i).getUserId()) {
        viewHolder.checkBox.setChecked(true);
      }
    }
    viewHolder.name.setText(userInfos.get(i).getUser_Name());
    viewHolder.checkBox.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (viewHolder.checkBox.isChecked() == true) {
          selectedUsers.add(userInfos.get(i));
        } else {
          remove(userInfos.get(i));
        }
        ViseLog.d(selectedUsers);
        if (clickItemListenner != null) {
          clickItemListenner.click(i);
        }
      }
    });
  }

  void remove(UserInfo userInfo) {
    int temp = 0;
    for (int i = 0; i < selectedUsers.size(); i++) {
      if (selectedUsers.get(i).getUserId() == userInfo.getUserId()) {
        temp = i;
      }
    }
    selectedUsers.remove(temp);
  }

  @Override
  public int getItemCount() {
    return userInfos.size();
  }


  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.name)
    TextView name;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
