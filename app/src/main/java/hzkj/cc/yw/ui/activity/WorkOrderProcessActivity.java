package hzkj.cc.yw.ui.activity;

import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.DEVICEID;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.ENDTIME;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.FAULTTYPE;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.NEWDEVICEID;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.PRICE;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.SERVICESTANDARD;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.SOURCE;
import static hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.STARTTIME;
import static hzkj.cc.yw.bean.WorkOrderInfo.MODE;
import static me.iwf.photopicker.PhotoPicker.DEFAULT_MAX_COUNT;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.vise.log.ViseLog;
import com.xuexiang.xui.utils.SnackbarUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar.Action;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.Builder;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.InputCallback;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.ListCallback;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.ListCallbackSingleChoice;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.SingleButtonCallback;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import hzkj.cc.library.LoadingDialog;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.stateful.StateFulLayout.RefreshListenner;
import hzkj.cc.yw.R;
import hzkj.cc.yw.SmoothScrollLayoutManager;
import hzkj.cc.yw.adapter.ClickItemListenner;
import hzkj.cc.yw.adapter.WorkOrderDeviceAdapter;
import hzkj.cc.yw.bean.Attachment;
import hzkj.cc.yw.bean.FaultType;
import hzkj.cc.yw.bean.SupplyGood;
import hzkj.cc.yw.bean.SupplyInfo;
import hzkj.cc.yw.bean.WorkOrderDetail;
import hzkj.cc.yw.bean.WorkOrderInfo;
import hzkj.cc.yw.contract.WorkOrderProcessContract;
import hzkj.cc.yw.presenter.WorkOrderProcessPresenter;
import hzkj.cc.yw.utils.TimeUtil;
import hzkj.cc.yw.utils.UiUtil;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPickerActivity;

/**
 *
 */
public class WorkOrderProcessActivity extends
    BaseActivity<WorkOrderProcessContract.Presenter> implements WorkOrderProcessContract.View {

  @BindView(R.id.uuid)
  TextView uuid;
  @BindView(R.id.startTime)
  TextView startTime;
  @BindView(R.id.agency)
  TextView agency;
  @BindView(R.id.project)
  TextView project;
  @BindView(R.id.clientName)
  TextView clientName;
  @BindView(R.id.clientAddress)
  TextView clientAddress;
  @BindView(R.id.contactPersonName)
  TextView contactPersonName;
  @BindView(R.id.contactPersonTel)
  TextView contactPersonTel;
  @BindView(R.id.simpleDescriptionDisplay)
  TextView simpleDescriptionDisplay;
  @BindView(R.id.endTime)
  TextView endTime;
  @BindView(R.id.errorGrade)
  TextView errorGrade;
  @BindView(R.id.deviceName)
  TextView deviceName;
  @BindView(R.id.deviceModel)
  TextView deviceModel;
  @BindView(R.id.pic)
  ImageView pic;
  @BindView(R.id.descpition)
  ImageView descpition;
  @BindView(R.id.firstPart)
  LinearLayout firstPart;
  @BindView(R.id.fivePart)
  LinearLayout fivePart;
  @BindView(R.id.secondPart)
  LinearLayout secondPart;
  //  @BindView(R.id.next)
//  FloatingActionButton next;
  @BindView(R.id.thirdPart)
  LinearLayout thirdPart;
  @BindView(R.id.chooseServiceMode)
  ImageView chooseServiceMode;
  @BindView(R.id.chooseServicePrice)
  ImageView chooseServicePrice;
  @BindView(R.id.serviceMode)
  TextView serviceMode;
  @BindView(R.id.gonePart)
  LinearLayout gonePart;
  @BindView(R.id.pricePart)
  LinearLayout pricePart;
  @BindView(R.id.servicePart)
  LinearLayout servicePart;
  @BindView(R.id.evaluatePart)
  LinearLayout evaluatePart;
  @BindView(R.id.procedurePart)
  LinearLayout procedurePart;
  @BindView(R.id.chooseEvaluateFiles)
  ImageView chooseEvaluateFiles;
  double sumPrice = 0;
  double devicePriceSum = 0;
  int selectedServiceModeIndex = 0;
  List<Integer> selectedServiceTypeIndex = new ArrayList<Integer>() {{
    add(0);
  }};
  Integer selectedServiceStandardIndex = 0;
  List<Integer> selectedServiceStandards = new ArrayList<Integer>() {{
    add(0);
  }};
  List<Integer> selectedOldDeviceIndex = new ArrayList<Integer>() {{
    add(0);
  }};
  List<Integer> selectedNewDeviceIndex = new ArrayList<Integer>() {{
    add(0);
  }};
  List<Integer> selectedOldDeviceSourceIndex = new ArrayList<Integer>() {{
    add(0);
  }};
  //  WorkOrderInfo workOrderInfo;
  TranslateAnimation showAnim;
  int nextCount = 0;
  List<String> titleNames = new ArrayList<String>() {{
    add("服务过程描述");
    add("费用明细及构成");
    add("服务人员及服务完结情况");
    add("用户服务评价");
  }};
  private LoadingDialog loadingDialog;
  private MaterialDialog dialog;
  @BindView(R.id.recyclerView)
  public RecyclerView recyclerView;
  @BindView(R.id.add)
  public ButtonView add;
  @BindView(R.id.secondPartPic)
  public ImageView secondPartPic;
  @BindView(R.id.secondPartPicShow)
  public TextView secondPartPicShow;
  @BindView(R.id.procedurePicShow)
  public TextView procedurePicShow;
  @BindView(R.id.procedurePic)
  public ImageView procedurePic;
  @BindView(R.id.picShow)
  public TextView picShow;
  @BindView(R.id.stateLayout)
  public StateFulLayout stateLayout;
  @BindView(R.id.record)
  public MultiLineEditText record;
  @BindView(R.id.scroll)
  public ScrollView scroll;
  @BindView(R.id.procedure)
  public ImageView procedure;
  @BindView(R.id.chooseTrafficPrice)
  public ImageView chooseTrafficPrice;
  @BindView(R.id.chooseOtherPrice)
  public ImageView chooseOtherPrice;
  @BindView(R.id.count)
  public TextView count;
  @BindView(R.id.servicePrice)
  public TextView servicePrice;
  @BindView(R.id.trafficPrice)
  public TextView trafficPrice;
  @BindView(R.id.otherPrice)
  public TextView otherPrice;
  @BindView(R.id.totalPrice)
  public TextView totalPrice;
  @BindView(R.id.deviceSumPrice)
  public TextView deviceSumPrice;
  @BindView(R.id.serviceWay)
  public TextView serviceWay;
  @BindView(R.id.servicePriceShow)
  public TextView servicePriceShow;
  @BindView(R.id.trafficPriceShow)
  public TextView trafficPriceShow;
  @BindView(R.id.otherPriceShow)
  public TextView otherPriceShow;
  @BindView(R.id.totalPriceShow)
  public TextView totalPriceShow;
  @BindView(R.id.deviceSumPriceShow)
  public TextView deviceSumPriceShow;
  @BindView(R.id.chooseDetails)
  public ImageView chooseDetails;
  @BindView(R.id.fourthPart)
  public LinearLayout fourthPart;
  @BindView(R.id.serviceMan)
  public TextView serviceMan;
  @BindView(R.id.serviceDepartment)
  public TextView serviceDepartment;
  @BindView(R.id.status)
  public TextView status;
  @BindView(R.id.serviceDate)
  public TextView serviceDate;
  @BindView(R.id.chooseStatus)
  public ImageView chooseStatus;
  @BindView(R.id.chooseServiceDate)
  public ImageView chooseServiceDate;
  WorkOrderDeviceAdapter adapter;
  List<WorkOrderDetail> workOrderDetails = new ArrayList<>();
  DecimalFormat format = new DecimalFormat("0.0");
  int selectedStatus = 0;
  @BindView(R.id.serviceDepartmentShow)
  public TextView serviceDepartmentShow;
  @BindView(R.id.serviceManShow)
  public TextView serviceManShow;
  @BindView(R.id.serviceDateShow)
  public TextView serviceDateShow;
  @BindView(R.id.serviceStatusShow)
  public TextView serviceStatusShow;
  @BindView(R.id.evaluateFilesShow)
  public TextView evaluateFilesShow;
  @BindView(R.id.chooseEvaluatePic)
  public ImageView chooseEvaluatePic;
  @BindView(R.id.evaluatePic)
  public TextView evaluatePic;
  SmoothScrollLayoutManager manager;
  /**
   * 适配器list当前的对象位置
   */
  private int p;
  private int itemType = -1;
  private List<File> files = new ArrayList<>();
  private List<File> evaluateFiles = new ArrayList<>();
  private WorkOrderInfo workOrderInfo;
  List<Integer> selectFaultTypeIndex = new ArrayList<Integer>() {{
    add(0);
  }};
  double old = 0;
  Action action;

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 717) {
      if (resultCode == PhotoPickerActivity.RESULT_OK) {
        evaluateFiles.clear();
        for (String s : data.getExtras().getStringArrayList(PhotoPicker.KEY_SELECTED_PHOTOS)) {
          evaluateFiles.add(new File(s));
        }
        if (evaluateFiles.size() > 0) {
          evaluateFilesShow
              .setText(
                  "已选择" + evaluateFiles.size()
                      + "张");
        }
      }
    } else {
      if (resultCode == PhotoPickerActivity.RESULT_OK) {
        files.clear();
        for (String s : data.getExtras().getStringArrayList(PhotoPicker.KEY_SELECTED_PHOTOS)) {
          files.add(new File(s));
        }
        if (files.size() > 0) {
          secondPartPicShow
              .setText(
                  "已选择" + files.size()
                      + "张");
        }
      }
    }
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  WorkOrderProcessContract.Presenter createPresenter() {
    return new WorkOrderProcessPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    SnackbarUtils.Long(stateLayout, "请完整信息").show();
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_work_order_process;
  }

  @Override
  public void initView() {
    action = new Action() {
      @Override
      public String getText() {
        return null;
      }

      @Override
      public int getDrawable() {
        return R.drawable.next_step;
      }

      @Override
      public void performAction(final View view) {
        new Builder(WorkOrderProcessActivity.this)
            .content("确定进入下一步")
            .positiveText("确定")
            .negativeText("取消").onPositive(new SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            if (workOrderInfo.getWorkOrders_progress() == 0) {
              nextCount++;
              if (nextCount == 1) {
                firstPart.setVisibility(View.GONE);
                secondPart.startAnimation(showAnim);
                secondPart.setVisibility(View.VISIBLE);
              } else {
                getPresenter()
                    .checkProcedure(record.getContentText(), files.size());
              }
            } else if (workOrderInfo.getWorkOrders_progress() == 1) {
              nextCount++;
              if (nextCount == 1) {
                firstPart.setVisibility(View.GONE);
                thirdPart.startAnimation(showAnim);
                thirdPart.setVisibility(View.VISIBLE);
              } else {
                workOrderInfo.setDetails(workOrderDetails);
                for (WorkOrderDetail workOrderDetail : workOrderDetails) {
                  workOrderDetail.setDetails_workOrdersId(workOrderInfo.getWorkOrders_id());
                }
                getPresenter()
                    .checkPrice(workOrderInfo.getDetails_way(), servicePrice.getText().toString(),
                        trafficPrice.getText().toString(), otherPrice.getText().toString(),
                        workOrderInfo.getDetails());
              }
            } else if (workOrderInfo.getWorkOrders_progress() == 2) {
              nextCount++;
              if (nextCount == 1) {
                firstPart.setVisibility(View.GONE);
                fourthPart.startAnimation(showAnim);
                fourthPart.setVisibility(View.VISIBLE);
              } else {
                getPresenter()
                    .checkService(serviceDate.getText().toString(), status.getText().toString());
              }
            } else if (workOrderInfo.getWorkOrders_progress() == 3) {
              nextCount++;
              if (nextCount == 1) {
                firstPart.setVisibility(View.GONE);
                fivePart.startAnimation(showAnim);
                fivePart.setVisibility(View.VISIBLE);
              } else {
                getPresenter().checkFiles(evaluateFiles.size());
              }
            }
            bar.setTitle(titleNames.get(workOrderInfo.getWorkOrders_progress()));
          }
        })
            .show();
      }

      @Override
      public int leftPadding() {
        return 0;
      }

      @Override
      public int rightPadding() {
        return 0;
      }
    };
    bar.addAction(action);
    stateLayout.init(new RefreshListenner() {
      @Override
      public void refresh() {
      }
    }, scroll);
    stateLayout.showState(StateFulLayout.LOADING);
    loadingDialog = new LoadingDialog(this);
    showAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f);
    showAnim.setDuration(500);
    chooseServicePrice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("服务费")
            .inputType(InputType.TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL).input("", "",
            new InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                servicePrice.setText(input);
              }
            }).show();
      }
    });
    chooseServicePrice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("服务费")
            .inputType(InputType.TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL).input("", "",
            new InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                servicePrice.setText(input);
              }
            }).show();
      }
    });
    chooseTrafficPrice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("交通费")
            .inputType(InputType.TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL).input("", "",
            new InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                trafficPrice.setText(input);
              }
            }).show();
      }
    });
    chooseOtherPrice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("其他费用")
            .inputType(InputType.TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL).input("", "",
            new InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                otherPrice.setText(input);
              }
            }).show();
      }
    });
    TextWatcher watcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!s.toString().equals("")) {
          old = Double.parseDouble(format.format(Double.valueOf(s.toString())));
        }
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
          sumPrice -= old;
        } else {
          sumPrice += Double.valueOf(format.format(Double.valueOf(s.toString()) - old));
        }
        totalPrice.setText(sumPrice + "");
      }
    };
    servicePrice.addTextChangedListener(watcher);
    trafficPrice.addTextChangedListener(watcher);
    otherPrice.addTextChangedListener(watcher);
    manager = new SmoothScrollLayoutManager(this);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    recyclerView.setLayoutManager(manager);
    LinearSnapHelper snapHelper = new LinearSnapHelper();
    snapHelper.attachToRecyclerView(recyclerView);
  }

  private void add() {
    SupplyInfo oldDevice = new SupplyInfo();
    oldDevice.setPutIn_good(new SupplyGood());
    SupplyInfo newDevice = new SupplyInfo();
    newDevice.setPutIn_good(new SupplyGood());
    WorkOrderDetail workOrderDetail = new WorkOrderDetail();
    workOrderDetail.setDetails_equip(oldDevice);
    workOrderDetail.setDetails_nequip(newDevice);
    workOrderDetails.add(workOrderDetail);
//    adapter.notifyDataSetChanged();
  }

  private void set(int index, boolean isServiceType) {
    SupplyInfo oldDevice = new SupplyInfo();
    oldDevice.setPutIn_good(new SupplyGood());
    SupplyInfo newDevice = new SupplyInfo();
    newDevice.setPutIn_good(new SupplyGood());
    WorkOrderDetail workOrderDetail = new WorkOrderDetail();
    if (!isServiceType) {
      workOrderDetail.setDetails_type(-1);
    }
    workOrderDetail.setFaultType(workOrderDetails.get(p).getFaultType());
    workOrderDetail.setDetails_standard(workOrderDetails.get(p).getDetails_standard());
    workOrderDetail.setDetails_equip(oldDevice);
    workOrderDetail.setDetails_nequip(newDevice);
    workOrderDetails.set(index, workOrderDetail);
//    adapter.notifyDataSetChanged();
  }

  @Override
  public void initData() {
    getPresenter().startGetOne(Integer.parseInt(getIntent().getStringExtra("id")));
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == WorkOrderProcessPresenter.GETOLDPARTDEVICESUCCESS) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this).items((Collection) object).title("故障配件选择")
          .itemsCallbackSingleChoice(
              selectedOldDeviceIndex.get(p), new ListCallbackSingleChoice() {
                @Override
                public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                    CharSequence text) {
                  selectedOldDeviceIndex.set(p, which);
                  getPresenter().getOldDeviceDetail(which);
                  return true;
                }
              }).show();
    } else if (tag == WorkOrderProcessPresenter.GETNEWPARTDEVICESUCCESS) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this).items((Collection) object).title("故障配件选择")
          .itemsCallbackSingleChoice(
              selectedNewDeviceIndex.get(p), new ListCallbackSingleChoice() {
                @Override
                public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                    CharSequence text) {
                  selectedNewDeviceIndex.set(p, which);
                  getPresenter().getNewDeviceDetail(which);
                  return true;
                }
              }).show();
    } else if (tag == WorkOrderProcessPresenter.UPDATEPICSUCCESS) {
      workOrderInfo.setWorkOrders_procedure(record.getEditText().getText().toString());
      getPresenter().startUpdateProcedure(workOrderInfo);
    } else if (tag == WorkOrderProcessPresenter.UPDATEPROCEDURESUCCESS) {
      loadingDialog.dismiss();
      new CcDialog(this, CcDialog.SUCCESS_DIALOG).setMessage("提交成功").setCancelListener(
          new CancelListener() {
            @Override
            public void onClick(CcDialog dialog) {
              stateLayout.showState(StateFulLayout.LOADING);
              getPresenter().startGetOne(Integer.parseInt(getIntent().getStringExtra("id")));
            }
          }).showDialog();
    } else if (tag == WorkOrderProcessPresenter.GETOLDPARTDEVICEDETAILSUCCESS) {
      SupplyInfo supplyInfo = (SupplyInfo) object;
      workOrderDetails.get(p).setDetails_equip(supplyInfo);
      adapter.setChange(false);
    } else if (tag == WorkOrderProcessPresenter.GETONEWORKORDERSUCCESS) {
      stateLayout.showState(StateFulLayout.CONTENT);
      workOrderInfo = (WorkOrderInfo) object;
      reset();
      uuid.setText(workOrderInfo.getWorkOrders_code());
      startTime.setText(workOrderInfo.getWorkOrders_date());
      agency.setText(workOrderInfo.getUserInfo().getDepartment().getDeptName());
      project.setText(workOrderInfo.getProjectInfo().getProject_name());
      clientName.setText(workOrderInfo.getClientInfo().getCustomer_name());
      clientAddress.setText(workOrderInfo.getClientInfo().getCustomer_address());
      contactPersonName
          .setText(workOrderInfo.getClientInfo().getContactorInfo().get(0).getContactper_name());
      contactPersonTel
          .setText(workOrderInfo.getClientInfo().getContactorInfo().get(0).getContactper_phone());
      simpleDescriptionDisplay.setText(workOrderInfo.getWorkOrders_brief());
      endTime.setText(workOrderInfo.getWorkOrders_endDate());
      errorGrade.setText(workOrderInfo.getErrorGradeInfo().getGdLevel_name());
      deviceName.setText(workOrderInfo.getSupplyInfo().getPutIn_good().getSupplies_name());
      deviceModel.setText(workOrderInfo.getSupplyInfo().getPutIn_good().getSupplies_model());
      serviceWay.setText(MODE.get(workOrderInfo.getDetails_way()));
      servicePriceShow.setText(workOrderInfo.getWorkOrders_service() + "");
      deviceSumPriceShow.setText(workOrderInfo.getWorkOrders_equipsCost() + "");
      trafficPriceShow.setText(workOrderInfo.getWorkOrders_fare() + "");
      otherPriceShow.setText(workOrderInfo.getWorkOrders_other() + "");
      totalPriceShow.setText(workOrderInfo.getWorkOrders_total() + "");
      chooseEvaluateFiles.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          List<String> strings = new ArrayList<>();
          for (File evaluateFile : evaluateFiles) {
            strings.add(evaluateFile.getPath());
          }
          ViseLog.d(strings);
          PhotoPicker.builder().setPhotoCount(DEFAULT_MAX_COUNT).setSelected(
              (ArrayList<String>) strings)
              .start(WorkOrderProcessActivity.this, 717);
        }
      });
      serviceDepartmentShow.setText(
          workOrderInfo.getUserInfo().getDepartment().getParent().getDeptName() + "-"
              + workOrderInfo.getUserInfo().getDepartment().getDeptName());
      serviceManShow.setText(workOrderInfo.getUserInfo().getUser_Name());
      if (workOrderInfo.getWorkOrders_serviceDate() != null) {
        serviceDateShow
            .setText(TimeUtil.format(workOrderInfo.getWorkOrders_serviceDate(), TimeUtil.YMD));
      }
      serviceStatusShow.setText(WorkOrderInfo.STATUS.get(workOrderInfo.getWorkOrders_status()));
      chooseServiceDate.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          new TimePickerBuilder(WorkOrderProcessActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
              serviceDate.setText(TimeUtil.dateToString(date, TimeUtil.YMD));
              workOrderInfo.setWorkOrders_serviceDate(serviceDate.getText().toString());
            }
          }).build().show();
        }
      });
      chooseStatus.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("服务完结情况")
              .items(WorkOrderInfo.STATUS).itemsCallbackSingleChoice(selectedStatus,
              new ListCallbackSingleChoice() {
                @Override
                public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                    CharSequence text) {
                  selectedStatus = which;
                  status.setText(text);
                  workOrderInfo.setWorkOrders_status(which);
                  return true;
                }
              }).show();
        }
      });
      serviceMan.setText(workOrderInfo.getUserInfo().getUser_Name());
      serviceDepartment.setText(
          workOrderInfo.getUserInfo().getDepartment().getParent().getDeptName() + "-"
              + workOrderInfo.getUserInfo().getDepartment().getDeptName());
      final View view = LayoutInflater.from(WorkOrderProcessActivity.this)
          .inflate(R.layout.dialog_work_order_detail, null);
      final LinearLayout replacePart = view.findViewById(R.id.replacePart);
      final LinearLayout faultPart = view.findViewById(R.id.faultPart);
      final TextView standard = view.findViewById(R.id.standard);
      final TextView type = view.findViewById(R.id.type);
      final TextView faultType = view.findViewById(R.id.faultType);
      final TextView unit = view.findViewById(R.id.unit);
      final TextView equipType = view.findViewById(R.id.equipType);
      final TextView equipId = view.findViewById(R.id.equipId);
      final TextView source = view.findViewById(R.id.source);
      final TextView reason = view.findViewById(R.id.reason);
      final TextView nequipType = view.findViewById(R.id.nequipType);
      final TextView nequipId = view.findViewById(R.id.nequipId);
      final TextView price = view.findViewById(R.id.price);
      final TextView warrantyStart = view.findViewById(R.id.warrantyStart);
      final TextView warrantyEnd = view.findViewById(R.id.warrantyEnd);
      chooseDetails.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          List<String> names = new ArrayList<>();
          for (WorkOrderDetail detail : workOrderInfo.getDetails()) {
            names.add(detail.getFaultType().getFaultName() + "(" + detail.getDetails_id() + ")");
          }
//          for (WorkOrderDetail detail : workOrderInfo.getDetails()) {
//            names.add(detail.getDetails_equip().getPutIn_good().getSupplies_name() +
//                "( ********" + detail.getDetails_equip()
//                .getSupplies_serial()
//                .substring(detail.getDetails_equip().getSupplies_serial().length() - 9,
//                    detail.getDetails_equip().getSupplies_serial().length() - 1) + ")");
//          }
          new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("详情").items(names)
              .itemsCallback(new ListCallback() {
                @Override
                public void onSelection(MaterialDialog dialog, View itemView, int position,
                    CharSequence text) {
                  faultType.setText(
                      workOrderInfo.getDetails().get(position).getFaultType().getFaultName());
                  if (workOrderInfo.getDetails().get(position).getDetails_equip() != null) {
                    equipType.setText(
                        workOrderInfo.getDetails().get(position).getDetails_equip().getPutIn_good()
                            .getSupplies_model());
                    equipId.setText(workOrderInfo.getDetails().get(position).getDetails_equip()
                        .getSupplies_serial());
                    standard.setText(WorkOrderDetail.STANDARD
                        .get(workOrderInfo.getDetails().get(position).getDetails_standard()));
                    type.setText(
                        WorkOrderDetail.TYPE
                            .get(workOrderInfo.getDetails().get(position).getDetails_type()));
                  }
                  else {
                    faultPart.setVisibility(View.GONE);
                  }
                  if (workOrderInfo.getDetails().get(position).getDetails_nequip() != null) {
                    nequipType.setText(
                        workOrderInfo.getDetails().get(position).getDetails_nequip().getPutIn_good()
                            .getSupplies_model());
                    unit.setText(workOrderInfo.getDetails().get(position).getDetails_unit());
                    nequipId.setText(workOrderInfo.getDetails().get(position).getDetails_nequip()
                        .getSupplies_serial());
                    price.setText(workOrderInfo.getDetails().get(position).getDetails_price() + "");
                    warrantyStart.setText(TimeUtil
                        .format(workOrderInfo.getDetails().get(position).getDetails_start(),
                            TimeUtil.YMD));
                    warrantyEnd.setText(TimeUtil
                        .format(workOrderInfo.getDetails().get(position).getDetails_end(),
                            TimeUtil.YMD));
                    source.setText(WorkOrderDetail.SOURCE
                        .get(workOrderInfo.getDetails().get(position).getDetails_source()));
                  } else {

                  }

                  reason.setText(workOrderInfo.getDetails().get(position).getDetails_reason());
                  new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("详情")
                      .customView(view, true).show();
                }
              }).show();
        }
      });
      bar.setTitle("工单详情");
//      switch (workOrderInfo.getWorkOrders_progress()) {
//        case 0: {
//          procedurePart.setVisibility(View.GONE);
//          pricePart.setVisibility(View.GONE);
//          servicePart.setVisibility(View.GONE);
//          break;
//        }
//        case 1: {
//          pricePart.setVisibility(View.GONE);
//          servicePart.setVisibility(View.GONE);
//          break;
//        }
//        case 4: {
//          bar.getViewByAction(action).setVisibility(View.GONE);
//          break;
//        }
//      }
      procedure.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("服务过程描述")
              .content(workOrderInfo.getWorkOrders_procedure()).show();
        }
      });
      final List<String> firstUrls = new ArrayList<>();
      final List<String> secondUrls = new ArrayList<>();
      final List<String> threeUrls = new ArrayList<>();
      for (Attachment attachment : workOrderInfo.getAttachment()) {
        if (attachment.getAnnex_state() == Attachment.FIRST) {
          firstUrls.add(attachment.getAnnex_path());
        } else if (attachment.getAnnex_state() == Attachment.SECOND) {
          secondUrls.add(attachment.getAnnex_path());
        } else {
          threeUrls.add(attachment.getAnnex_path());
        }
      }
      chooseEvaluatePic.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          UiUtil.jumpToActivity1(WorkOrderProcessActivity.this, PhotoActivity.class,
              (ArrayList<String>) threeUrls, false, false, 1);
        }
      });
      picShow.setText(firstUrls.size() + "张图片");
      procedurePicShow.setText(secondUrls.size() + "张图片");
      evaluatePic.setText(threeUrls.size() + "张图片");
      procedurePart.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          UiUtil.jumpToActivity1(WorkOrderProcessActivity.this, PhotoActivity.class,
              (ArrayList<String>) secondUrls, false, false, 1);
        }
      });
      pic.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          UiUtil.jumpToActivity1(WorkOrderProcessActivity.this, PhotoActivity.class,
              (ArrayList<String>) firstUrls, false, false, 1);
        }
      });
      descpition.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          new MaterialDialog.Builder(WorkOrderProcessActivity.this)
              .content(workOrderInfo.getWorkOrders_detailed()).title("报障详细描述").show();
        }
      });
      chooseServiceMode.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("服务方式")
              .items(new ArrayList() {{
                add("上门服务");
                add("用户送修");
                add("远程服务");
                add("其他");
              }})
              .itemsCallbackSingleChoice(selectedServiceModeIndex, new ListCallbackSingleChoice() {
                @Override
                public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                    CharSequence text) {
                  serviceMode.setText(text);
                  selectedServiceModeIndex = which;
                  workOrderInfo.setDetails_way(which);
                  return true;
                }
              }).show();
        }
      });
      add();
      final View v = LayoutInflater.from(WorkOrderProcessActivity.this)
          .inflate(R.layout.dialog_work_order_checkbox, null);
      final CheckBox canRepair = v.findViewById(R.id.canRepair);
      final CheckBox canNotRepair = v.findViewById(R.id.canNotRepair);
      final CheckBox outOfTime = v.findViewById(R.id.outOfTime);
      CheckBox lighting = v.findViewById(R.id.lighting);
      CheckBox damaged = v.findViewById(R.id.damaged);
      CheckBox other = v.findViewById(R.id.other);
      final TextView submit = v.findViewById(R.id.submit);
      final List<CheckBox> checkBoxes = new ArrayList<>();
      final List<CheckBox> allCheckBoxs = new ArrayList<>();
      checkBoxes.add(outOfTime);
      checkBoxes.add(lighting);
      checkBoxes.add(damaged);
      checkBoxes.add(other);
      allCheckBoxs.add(canRepair);
      allCheckBoxs.addAll(checkBoxes);
      canNotRepair.setClickable(false);
      canRepair.setChecked(true);
      adapter = new WorkOrderDeviceAdapter(this, workOrderDetails, new ClickItemListenner() {
        @Override
        public void click(int p) {
        }

        @Override
        public void click(final int p, int tag) {
          if (tag == SOURCE) {
            new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("配件来源类别")
                .items(new ArrayList() {{
                  add("二级库存备件");
                  add("项目遗留物资");
                }}).itemsCallbackSingleChoice(selectedOldDeviceSourceIndex.get(0),
                new ListCallbackSingleChoice() {
                  @Override
                  public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                      CharSequence text) {
                    selectedOldDeviceSourceIndex.set(p, which);
                    workOrderDetails.get(p).setDetails_source(which);
                    adapter.setChange(false);
                    return true;
                  }
                }).show();
          } else if (tag == PRICE) {
            new MaterialDialog.Builder(WorkOrderProcessActivity.this).title("配件单价").input("", "",
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    if (workOrderDetails.get(p).getDetails_price() != -1) {
                      sumPrice -= workOrderDetails.get(p).getDetails_price();
                    }
                    workOrderDetails.get(p).setDetails_price(
                        Double.parseDouble(format.format(Double.valueOf(input.toString()))));
                    adapter.notifyDataSetChanged();
                    sumPrice += workOrderDetails.get(p).getDetails_price();
                    devicePriceSum += workOrderDetails.get(p).getDetails_price();
                    totalPrice.setText(sumPrice + "");
                    deviceSumPrice.setText(devicePriceSum + "");
                  }
                }).inputType(InputType.TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL)
                .show();
          } else if (tag == DEVICEID) {
            loadingDialog.show();
            getPresenter().chooseGetOldPartDevice(workOrderInfo.getSupplyInfo().getPutIn_id() + "");
          } else if (tag == ENDTIME) {
            new TimePickerBuilder(WorkOrderProcessActivity.this, new OnTimeSelectListener() {
              @Override
              public void onTimeSelected(Date date, View v) {
                workOrderDetails.get(p).setDetails_end(TimeUtil.dateToString(date, TimeUtil.YMD));
                adapter.notifyDataSetChanged();
              }
            }).build().show();
          } else if (tag == STARTTIME) {
            new TimePickerBuilder(WorkOrderProcessActivity.this, new OnTimeSelectListener() {
              @Override
              public void onTimeSelected(Date date, View v) {
                workOrderDetails.get(p).setDetails_start(TimeUtil.dateToString(date, TimeUtil.YMD));
                adapter.notifyDataSetChanged();
              }
            }).build().show();
          } else if (tag == FAULTTYPE) {
            loadingDialog.show();
            getPresenter().startGetFaultTypes();
          } else if (tag == NEWDEVICEID) {
            loadingDialog.show();
            getPresenter().chooseGetNewPartDevice(workOrderInfo.getSupplyInfo().getPutIn_id() + "");
          } else if (tag == SERVICESTANDARD) {
            submit.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View v) {
                dialog.dismiss();
                for (CheckBox box : allCheckBoxs) {
                  if (box.isChecked()) {
                    selectedServiceStandards.set(p, allCheckBoxs.indexOf(box));
                    workOrderDetails.get(p).setDetails_standard(selectedServiceStandards.get(p));
                    adapter.setChange(false);
                  }
                }
              }
            });
            for (final CheckBox checkBox : allCheckBoxs) {
              checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  checkBox.setClickable(false);
                  if (isChecked) {
                    for (CheckBox check : allCheckBoxs) {
                      if (check != checkBox && check.isChecked()) {
                        selectedServiceStandardIndex = allCheckBoxs.indexOf(check);
                      }
                    }
                    allCheckBoxs.get(selectedServiceStandardIndex).setChecked(false);
                    if (checkBox != canRepair) {
                      if (!canNotRepair.isChecked()) {
                        canNotRepair.setChecked(true);
                      }
                    } else {
                      canNotRepair.setChecked(false);
                    }
                  } else {
                    checkBox.setClickable(true);
                  }
                }
              });
            }
            ViseLog.d(selectedServiceStandardIndex);
            loadingDialog.show();
            dialog = new MaterialDialog.Builder(WorkOrderProcessActivity.this)
                .title("服务判断标准")
                .customView(v, true)
                .showListener(new OnShowListener() {
                  @Override
                  public void onShow(DialogInterface dialog) {
                    loadingDialog.dismiss();
                  }
                }).show();
          } else {
            new Builder(WorkOrderProcessActivity.this).title("服务类别")
                .items(new ArrayList() {{
                  add("维修");
                  add("更换");
                  add("拆卸返修");
                  add("项目物资安装");
                  add("项目物资拆除");
                }}).itemsCallbackSingleChoice(selectedServiceTypeIndex.get(p),
                new ListCallbackSingleChoice() {
                  @Override
                  public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                      CharSequence text) {
                    ViseLog.d(p);
                    set(p, true);
//                    add();
                    selectedServiceTypeIndex.set(p, which);
//                    adapter.setItemType(which);
                    workOrderDetails.get(p).setDetails_type(selectedServiceTypeIndex.get(p));
                    adapter.notifyDataSetChanged();
                    return true;
                  }
                }).show();
          }
        }
      }, itemType);
      recyclerView.setAdapter(adapter);
      secondPartPic.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          List<String> names = new ArrayList<>();
          for (File file : files) {
            names.add(file.getPath());
          }
          PhotoPicker.builder()
              .setSelected((ArrayList<String>) names)
              .setPhotoCount(DEFAULT_MAX_COUNT)
              .start(WorkOrderProcessActivity.this);
        }
      });
      recyclerView.addOnScrollListener(new OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);
          int position = manager.findFirstVisibleItemPosition();
          count.setText((position + 1) + "/" + adapter.getItemCount());
          p = position;
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
          super.onScrollStateChanged(recyclerView, newState);
          if (newState == 0) {
            adapter.notifyDataSetChanged();
            allCheckBoxs.get(selectedServiceStandards.get(p)).setChecked(true);
          }
        }
      });
      add.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          selectedServiceTypeIndex.add(0);
          selectFaultTypeIndex.add(0);
          add();
          selectedOldDeviceSourceIndex.add(0);
          selectedServiceStandards.add(0);
          selectedOldDeviceIndex.add(0);
          selectedNewDeviceIndex.add(0);
          adapter.notifyItemInserted(adapter.getItemCount());
          recyclerView.smoothScrollToPosition(adapter.getItemCount());
          ViseLog.d(workOrderDetails);
        }
      });
    } else if (tag == WorkOrderProcessPresenter.GETOLDPARTDEVICEMPTY) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this)
          .iconRes(R.drawable.ic_gantanhao)
          .title("故障配件")
          .content("没有可选择的配件").show();
    } else if (tag == WorkOrderProcessPresenter.GETFAULTTYPEDETAILSUCCESS) {
      workOrderDetails.get(p).setFaultType((FaultType) object);
      ViseLog.d(workOrderDetails.get(p).getFaultType());
      adapter.notifyDataSetChanged();
    } else if (tag == WorkOrderProcessPresenter.UPDATEPICEVALUATESUCCESS) {
      getPresenter().startUpdateProcedure(workOrderInfo);
    } else if (tag == WorkOrderProcessPresenter.GETFAULTTYPESUCCESS) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this)
          .title("故障类别")
          .items((Collection) object).itemsCallbackSingleChoice(selectFaultTypeIndex.get(p),
          new ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                CharSequence text) {
              selectFaultTypeIndex.set(p, which);
              getPresenter().getFaultTypeDetail(which);
              set(p, false);
              return true;
            }
          }).show();
    } else {
      SupplyInfo supplyInfo = (SupplyInfo) object;
      workOrderDetails.get(p).setDetails_nequip(supplyInfo);
      adapter.setChange(false);
    }
  }

  void reset() {
    nextCount = 0;
    firstPart.setVisibility(View.VISIBLE);
    secondPart.setVisibility(View.GONE);
    thirdPart.setVisibility(View.GONE);
    fourthPart.setVisibility(View.GONE);
    fivePart.setVisibility(View.GONE);
    switch (workOrderInfo.getWorkOrders_progress()) {
      case 1: {
        procedurePart.setVisibility(View.VISIBLE);
        break;
      }
      case 2: {
        procedurePart.setVisibility(View.VISIBLE);
        pricePart.setVisibility(View.VISIBLE);
        break;
      }
      case 3: {
        procedurePart.setVisibility(View.VISIBLE);
        pricePart.setVisibility(View.VISIBLE);
        servicePart.setVisibility(View.VISIBLE);
        break;
      }
      case 4: {
        procedurePart.setVisibility(View.VISIBLE);
        pricePart.setVisibility(View.VISIBLE);
        servicePart.setVisibility(View.VISIBLE);
        evaluatePart.setVisibility(View.VISIBLE);
        bar.getViewByAction(action).setVisibility(View.GONE);
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
    switch (((Integer) object)) {
      case WorkOrderProcessPresenter.CHECK_SUCCESS_PROCEDURE: {
        loadingDialog.show();
        getPresenter().startInsertPic(files, workOrderInfo.getWorkOrders_id(), 1, false);
        break;
      }
      case WorkOrderProcessPresenter.CHECK_SUCCESS_PRICE: {
        workOrderInfo.setWorkOrders_equipsCost(devicePriceSum);
        workOrderInfo.setWorkOrders_service(
            Double.parseDouble(servicePrice.getText().toString()));
        workOrderInfo.setWorkOrders_fare(
            Double.parseDouble(trafficPrice.getText().toString()));
        workOrderInfo.setWorkOrders_other(
            Double.parseDouble(otherPrice.getText().toString()));
        workOrderInfo.setWorkOrders_total(
            Double.parseDouble(totalPrice.getText().toString()));
        getPresenter().startUpdateProcedure(workOrderInfo);
        loadingDialog.show();
        break;
      }
      case WorkOrderProcessPresenter.CHECK_SUCCESS_SERVICE: {
        getPresenter().startUpdateProcedure(workOrderInfo);
        loadingDialog.show();
        break;
      }
      case WorkOrderProcessPresenter.CHECK_SUCCESS_EVALUATE: {
        getPresenter()
            .startInsertPic(evaluateFiles, workOrderInfo.getWorkOrders_id(), 2, true);
        loadingDialog.show();
        break;
      }
    }
  }
}
