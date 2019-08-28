package hzkj.cc.yw.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.progress.loading.MiniLoadingView;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.DeliverGoodsAdapter;
import hzkj.cc.yw.bean.DeliverCompany;
import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.bean.Event;
import hzkj.cc.yw.bean.SingleGood;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.DeliverInsertContract;
import hzkj.cc.yw.presenter.DeliverInsertPresenter;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class DeliverInsertActivity extends BaseActivity<DeliverInsertContract.Presenter> implements
    DeliverInsertContract.View {

  //    @BindView(R.id.openChooseGoods)
//    ImageView openChooseGoods;
  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;
  //    @BindView(R.id.postAddress)
//    ImageView postAddress;
//    @BindView(R.id.getAddress)
//    ImageView getAddress;
  @BindView(R.id.getPerson)
  ImageView getPerson;
  @BindView(R.id.deliverId)
  ImageView deliverId;
  @BindView(R.id.deliverCompany)
  ImageView deliverCompany;
  //    @BindView(R.id.displayPostAddress)
//    TextView displayPostAddress;
  @BindView(R.id.displayGetPerson)
  TextView displayGetPerson;
  //    @BindView(R.id.displayGetAddress)
//    TextView displayGetAddress;
  @BindView(R.id.displayDeliverCompany)
  TextView displayDeliverCompany;
  @BindView(R.id.displayDeliverId)
  TextView displayDeliverId;
  boolean isDown = true;
  @BindView(R.id.loading)
  MiniLoadingView loading;
  @BindView(R.id.getGoods)
  ImageView getGoods;
  @BindView(R.id.insertGood)
  ImageView insertGood;
  @BindView(R.id.insertGoodLayout)
  RelativeLayout insertGoodLayout;
  DeliverGoodsAdapter adapter;
  List<SingleGood> datas = new ArrayList<>();
  int departmentLastId;
  String name;
  UserInfo selectUserInfo;
  DeliverCompany selectDeliverCompany;
  int count = 0;
  //  List<String> names = new ArrayList<>();
  List<Department> departments = new ArrayList<>();
//  List<UserInfo> userInfos = new ArrayList<>();

  @Override
  DeliverInsertContract.Presenter createPresenter() {
    return new DeliverInsertPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_deliver_insert;
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  public void initView() {
    bar.setTitle("物流发货");
    getPerson.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        name = "";
        loading.setVisibility(View.VISIBLE);
        getPresenter().startGetDepartment(1, DeliverInsertPresenter.DEPARTMENT);
      }
    });
    deliverId.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(DeliverInsertActivity.this).title("快递单号")
            .input("请输入快递单号", "", new MaterialDialog.InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                displayDeliverId.setText(input);
              }
            })
            .inputType(InputType.TYPE_CLASS_NUMBER)
            .negativeText("我不输了")
            .show();
      }
    });
    deliverCompany.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        loading.setVisibility(View.VISIBLE);
        getPresenter().startGetDeliverCompanys(DeliverInsertPresenter.DELIVERCOMPANY);
      }
    });
    bar.addAction(new TitleBar.Action() {
      @Override
      public String getText() {
        return "提交";
      }

      @Override
      public int getDrawable() {
        return 0;
      }

      @Override
      public void performAction(View view) {
        getPresenter().check(datas, displayGetPerson.getText()
            .toString(), displayDeliverCompany.getText()
            .toString(), displayDeliverId.getText()
            .toString());
      }

      @Override
      public int leftPadding() {
        return 0;
      }

      @Override
      public int rightPadding() {
        return 0;
      }
    });
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
    getGoods.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (recyclerView.getVisibility() == View.GONE) {
          insertGoodLayout.setVisibility(View.VISIBLE);
          getGoods
              .setImageDrawable(getResources().getDrawable(R.drawable.ic_iconfonticonfonti2copy));
          recyclerView.setVisibility(View.VISIBLE);
        } else {
          insertGoodLayout.setVisibility(View.GONE);
          getGoods.setImageDrawable(getResources().getDrawable(R.drawable.ic_xiajiantou));
          recyclerView.setVisibility(View.GONE);
        }
      }
    });
    insertGood.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        View view = LayoutInflater.from(DeliverInsertActivity.this)
            .inflate(R.layout.dialog_open_goods_choose, null);
        final MaterialDialog dialog = new MaterialDialog.Builder(DeliverInsertActivity.this)
            .customView(view, false)
            .title("添加货物信息")
            .show();
        TextView cancel = view.findViewById(R.id.cancel);
        TextView submit = view.findViewById(R.id.submit);
        final MaterialEditText supplyName = view.findViewById(R.id.supplyName);
        final MaterialEditText supplyType = view.findViewById(R.id.supplyType);
        final MaterialEditText supplyCount = view.findViewById(R.id.supplyCount);
        final MaterialEditText supplyUnit = view.findViewById(R.id.supplyUnit);
        final MaterialEditText remark = view.findViewById(R.id.remark);
        submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            datas.add(new SingleGood(supplyName.getText()
                .toString(), supplyType.getText()
                .toString(), supplyCount.getText()
                .toString(), supplyUnit.getText()
                .toString(), remark.getText()
                .toString()));
            adapter.notifyDataSetChanged();
            dialog.dismiss();
          }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            dialog.dismiss();
          }
        });
      }
    });
  }

  @Override
  public void initData() {
    adapter = new DeliverGoodsAdapter(this, datas);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == DeliverInsertPresenter.USER) {
      final List<UserInfo> userInfos = (List<UserInfo>) object;
      List<String> names = new ArrayList<>();
      for (UserInfo userInfo : userInfos) {
        names.add(userInfo.getUser_Name());
      }
      new MaterialDialog.Builder(DeliverInsertActivity.this).title("收货人")
          .items(names)
          .cancelable(false)
          .itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position,
                CharSequence text) {
              name += text;
              displayGetPerson.setText(name);
              selectUserInfo = userInfos.get(position);
            }
          }).dismissListener(new OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
          count = 0;
        }
      })
          .show();
    } else if (tag == DeliverInsertPresenter.USERNOTEND) {
      final List<UserInfo> userInfos = (List<UserInfo>) object;
      List<String> names = new ArrayList<>();
      for (Department department : departments) {
        names.add(department.getDeptName());
      }
      for (UserInfo userInfo : userInfos) {
        names.add(userInfo.getUser_Name());
      }
      new MaterialDialog.Builder(DeliverInsertActivity.this).title("收货人")
          .items(names)
          .cancelable(false)
          .itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position,
                CharSequence text) {
              if (position > departments.size() - 1) {
                name += text;
                displayGetPerson.setText(name);
                selectUserInfo = userInfos.get(position - departments.size());
              } else {
                departmentLastId = departments.get(position).getDeptId();
                name += text + "-";
                getPresenter()
                    .startUserByDepartmentId(departmentLastId, DeliverInsertPresenter.USER);
              }
            }
          }).dismissListener(new OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
          count = 0;
        }
      })
          .show();
    } else if (tag == DeliverInsertPresenter.DELIVERCOMPANY) {
      final List<DeliverCompany> deliverCompanies = (List<DeliverCompany>) object;
      List<String> names = new ArrayList<>();
      for (DeliverCompany deliverCompany : deliverCompanies) {
        names.add(deliverCompany.getKdgs());
      }
      new MaterialDialog.Builder(DeliverInsertActivity.this).title("快递公司")
          .items(names)
          .itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position,
                CharSequence text) {
              displayDeliverCompany.setText(text);
              selectDeliverCompany = deliverCompanies.get(position);
            }
          })
          .show();
    } else if (tag == DeliverInsertPresenter.DEPARTMENT) {
      List<String> names = new ArrayList<>();
      departments.clear();
      count++;
      departments.addAll((Collection<? extends Department>) object);
      for (Department department : departments) {
        names.add(department.getDeptName());
      }
      if (count == 2) {
        getPresenter().startUserByDepartmentId(departmentLastId, DeliverInsertPresenter.USERNOTEND);
      } else {
        new MaterialDialog.Builder(DeliverInsertActivity.this).title("选择用户")
            .items(names)
            .cancelable(false)
            .itemsCallback(new MaterialDialog.ListCallback() {
              @Override
              public void onSelection(MaterialDialog dialog, View itemView, int position,
                  CharSequence text) {
                departmentLastId = departments.get(position)
                    .getDeptId();
                name += text + "-";
                loading.setVisibility(View.VISIBLE);
                getPresenter()
                    .startGetDepartment(departmentLastId, DeliverInsertPresenter.DEPARTMENT);
              }
            })
            .show();
      }
//
    } else if (tag == DeliverInsertPresenter.DEPARTMENTEND) {
      getPresenter().startUserByDepartmentId(departmentLastId, DeliverInsertPresenter.USER);
      loading.setVisibility(View.VISIBLE);
    } else if (tag == DeliverInsertPresenter.USEREMTPY) {
      new MaterialDialog.Builder(DeliverInsertActivity.this)
          .cancelable(false)
          .iconRes(R.drawable.ic_chahao)
          .title("提示")
          .content("该部门没有人员")
          .positiveText("确定")
          .show();
    } else {
      new CcDialog(this, CcDialog.SUCCESS_DIALOG).setMessage("发货成功")
          .setCancelListener(new CancelListener() {
            @Override
            public void onClick(CcDialog dialog) {
              EventBus.getDefault().postSticky(new Event(9, null));
              finish();
            }
          })
          .showDialog();
    }
    loading.setVisibility(View.GONE);
  }

  @Override
  public void updateCheckSuccess(Object object) {
    getPresenter().startInsertDeliverInfo(DeliverInsertPresenter.INSERTDELIVER,
        selectDeliverCompany.getKdId()
        , displayDeliverId.getText()
            .toString(),
        TimeUtil.dateToString(new Date(), TimeUtil.YMDHMS),
        StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getUserId(),
        selectUserInfo.getUserId(), datas);
  }
}
