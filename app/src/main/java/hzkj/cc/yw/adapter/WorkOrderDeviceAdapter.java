package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.vise.log.ViseLog;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import hzkj.cc.ccrecyclerview.BaseAdapter;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.WorkOrderDeviceAdapter.ViewHolder;
import hzkj.cc.yw.bean.FaultType;
import hzkj.cc.yw.bean.WorkOrderDetail;
import java.util.List;

public class WorkOrderDeviceAdapter extends BaseAdapter<ViewHolder> {

  private final TranslateAnimation showAnim;
  Context context;
  List<WorkOrderDetail> workOrderDetails;
  ClickItemListenner clickItemListenner;
  public static final int DEVICEID = 0;
  public static final int SOURCE = 1;
  public static final int NEWDEVICEID = 6;
  public static final int SERVICETYPE = 3;
  public static final int SERVICESTANDARD = 4;
  public static final int FAULTTYPE = 14;
  public static final int PRICE = 114;
  public static final int STARTTIME = 1114;
  public static final int ENDTIME = 11141;
  ViewHolder viewHolder;

  public void setChange(boolean change) {
    notifyDataSetChanged();
  }
//  public void setItemType(int itemType) {
//    this.itemType = itemType;

  public WorkOrderDeviceAdapter(Context context, List<WorkOrderDetail> workOrderDetails,
      ClickItemListenner clickItemListenner, int itemType) {
    this.context = context;
    this.workOrderDetails = workOrderDetails;
    this.clickItemListenner = clickItemListenner;
//    this.itemType = itemType;
    showAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f);
    showAnim.setDuration(500);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.view_pager_work_order_device, viewGroup, false);
    view.measure(0, 0);
    viewHolder = new WorkOrderDeviceAdapter.ViewHolder(
        view);
    return viewHolder;
  }

  @Override
  public int getItemViewType(int position) {
    return workOrderDetails.get(position).getDetails_type();
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    if (workOrderDetails.get(i).getDetails_type() == 2
        || workOrderDetails.get(i).getDetails_type() == 3
        || workOrderDetails.get(i).getDetails_type() == 4) {
      viewHolder.oldPart.setVisibility(View.VISIBLE);
      viewHolder.newDevicePart.setVisibility(View.GONE);
    } else if (workOrderDetails.get(i).getDetails_type() == 1) {
      viewHolder.oldPart.setVisibility(View.VISIBLE);
      viewHolder.newDevicePart.setVisibility(View.VISIBLE);
    } else if (workOrderDetails.get(i).getDetails_type() == 0) {
      viewHolder.oldPart.setVisibility(View.VISIBLE);
      viewHolder.newDevicePart.setVisibility(View.GONE);
    }
    ViseLog.d(workOrderDetails.get(i).getFaultType());
    if (workOrderDetails.get(i).getFaultType() != null) {
      if (workOrderDetails.get(i).getFaultType().getId() == FaultType.DEVICE) {
        viewHolder.serviceTypePart.setVisibility(View.VISIBLE);
      } else {
        viewHolder.serviceTypePart.setVisibility(View.GONE);
        viewHolder.oldPart.setVisibility(View.GONE);
        viewHolder.newDevicePart.setVisibility(View.GONE);
      }
    } else {
      viewHolder.serviceTypePart.setVisibility(View.GONE);
    }
//    if (workOrderDetails.get(i).getDetails_standard() != -1) {
    viewHolder.serviceStandard
        .setText(WorkOrderDetail.STANDARD.get(workOrderDetails.get(i).getDetails_standard()));
//    }
//    else {
//      viewHolder.serviceStandard
//          .setText("");
//    }
    viewHolder.reason.getEditText().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        workOrderDetails.get(i).setDetails_reason(s.toString());
      }
    });
    if (workOrderDetails.get(i).getDetails_type() != -1) {
      viewHolder.serviceType
          .setText(WorkOrderDetail.TYPE.get(workOrderDetails.get(i).getDetails_type()));
    }
    viewHolder.accessory
        .setText(workOrderDetails.get(i).getDetails_equip().getPutIn_good().getSupplies_name());
    viewHolder.accessoryModel
        .setText(workOrderDetails.get(i).getDetails_equip().getPutIn_good().getSupplies_model());
    viewHolder.unity
        .setText(workOrderDetails.get(i).getDetails_equip().getPutIn_good().getSupplies_unit());
    viewHolder.accessoryUuid
        .setText(workOrderDetails.get(i).getDetails_equip().getSupplies_serial());
    viewHolder.accessorySourceType
        .setText(workOrderDetails.get(i).getDetails_source() == -1 ? "" :
            workOrderDetails.get(i).getDetails_source() == WorkOrderDetail.HASED ? "二级库存备件"
                : "项目遗留物资");
    viewHolder.newDevice
        .setText(workOrderDetails.get(i).getDetails_nequip().getPutIn_good().getSupplies_name());
    viewHolder.newDeviceModel
        .setText(workOrderDetails.get(i).getDetails_nequip().getPutIn_good().getSupplies_model());
    viewHolder.newDeviceUuid
        .setText(workOrderDetails.get(i).getDetails_nequip().getSupplies_serial());
    if (workOrderDetails.get(i).getDetails_nequip().getPutIn_shelfLife() != null) {
      viewHolder.warrantyStart
          .setText(
              workOrderDetails.get(i).getDetails_nequip().getPutIn_shelfLife().split(" - ")[0]);
      viewHolder.warrantyEnd
          .setText(
              workOrderDetails.get(i).getDetails_nequip().getPutIn_shelfLife().split(" - ")[1]);
    }
    viewHolder.price
        .setText(workOrderDetails.get(i).getDetails_price() == -1 ? ""
            : workOrderDetails.get(i).getDetails_price() + "");
    viewHolder.chooseAccessory.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, DEVICEID);
      }
    });
    viewHolder.chooseAccessorySourceType.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, SOURCE);
      }
    });
    viewHolder.chooseNewDevice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, NEWDEVICEID);
      }
    });
    viewHolder.chooseServiceStandard.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, SERVICESTANDARD);
      }
    });
    viewHolder.chooseServiceType.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, SERVICETYPE);
      }
    });
    viewHolder.chooseFaultType.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, FAULTTYPE);
      }
    });
    viewHolder.choosePrice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, PRICE);
      }
    });
    viewHolder.chooseStartTime.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, STARTTIME);
      }
    });
    viewHolder.chooseEndTime.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        clickItemListenner.click(i, ENDTIME);
      }
    });
//    if (workOrderDetails.get(i).getFaultType() != null) {
    viewHolder.faultType.setText(
        workOrderDetails.get(i).getFaultType() != null ? workOrderDetails.get(i).getFaultType()
            .getFaultName() : "");
    viewHolder.startTime.setText(
        workOrderDetails.get(i).getDetails_start());
    viewHolder.endTime.setText(
        workOrderDetails.get(i).getDetails_end());
//    }
  }

  @Override
  public int getItemCount() {
    return workOrderDetails.size();
  }

  class ViewHolder extends MyViewHolder {

    @BindView(R.id.chooseServiceStandard)
    ImageView chooseServiceStandard;
    @BindView(R.id.serviceStandard)
    TextView serviceStandard;
    @BindView(R.id.chooseServiceType)
    ImageView chooseServiceType;
    @BindView(R.id.serviceType)
    TextView serviceType;
    @BindView(R.id.chooseAccessory)
    ImageView chooseAccessory;
    @BindView(R.id.chooseAccessorySourceType)
    ImageView chooseAccessorySourceType;
    @BindView(R.id.reason)
    MultiLineEditText reason;
    @BindView(R.id.accessory)
    TextView accessory;
    @BindView(R.id.newDevice)
    TextView newDevice;
    @BindView(R.id.unity)
    TextView unity;
    @BindView(R.id.accessoryUuid)
    TextView accessoryUuid;
    @BindView(R.id.accessorySourceType)
    TextView accessorySourceType;
    @BindView(R.id.accessoryModel)
    TextView accessoryModel;
    @BindView(R.id.oldPart)
    LinearLayout oldPart;
    @BindView(R.id.newDevicePart)
    LinearLayout newDevicePart;
    @BindView(R.id.chooseNewDevice)
    ImageView chooseNewDevice;
    @BindView(R.id.newDeviceModel)
    TextView newDeviceModel;
    @BindView(R.id.newDeviceUuid)
    TextView newDeviceUuid;
    @BindView(R.id.warrantyStart)
    TextView warrantyStart;
    @BindView(R.id.warrantyEnd)
    TextView warrantyEnd;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.choosePrice)
    ImageView choosePrice;
    @BindView(R.id.faultType)
    TextView faultType;
    @BindView(R.id.chooseFaultType)
    ImageView chooseFaultType;
    @BindView(R.id.serviceTypePart)
    RelativeLayout serviceTypePart;
    @BindView(R.id.chooseStartTime)
    ImageView chooseStartTime;
    @BindView(R.id.startTime)
    TextView startTime;
    @BindView(R.id.chooseEndTime)
    ImageView chooseEndTime;
    @BindView(R.id.endTime)
    TextView endTime;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
