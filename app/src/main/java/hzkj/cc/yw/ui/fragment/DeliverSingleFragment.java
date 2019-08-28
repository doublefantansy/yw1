package hzkj.cc.yw.ui.fragment;

import butterknife.BindView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.contract.DeliverSingleContract;
import hzkj.cc.yw.presenter.DeliverSinglePresenter;
import java.util.ArrayList;

public class DeliverSingleFragment extends BaseFragment<DeliverSingleContract.Presenter> implements
    DeliverSingleContract.View {

  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;
//  int count = 0;

  @Override
  void doOnVisible() {
  }

  @Override
  DeliverSingleContract.Presenter createPresenter() {
    return new DeliverSinglePresenter();
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_deliver_single;
  }

  @Override
  public void initView() {
    bottomNavigation.initBottomChildren(getActivity().getSupportFragmentManager(),
        new ArrayList<BottomChild>() {{
          add(new BottomChild("待收货", DeliverSingleEachFragment.newInstance(DeliverInfo.HASNOTGET),
              getResources().getDrawable(R.drawable.ic_daifahuo_2),
              getResources().getDrawable(R.drawable.ic_daifahuo)));
          add(new BottomChild("已收货", DeliverSingleEachFragment.newInstance(DeliverInfo.HASGET),
              getResources().getDrawable(R.drawable.ic_yishouhuo),
              getResources().getDrawable(R.drawable.ic_yishouhuo_2)));
        }}, 0);
    bottomNavigation.setTextColors(new ArrayList<Integer>() {
      {
        add(getResources().getColor(R.color.bottom_blue));
        add(getResources().getColor(R.color.bottom_green));
      }
    });
//    bottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
//      @Override
//      public void onClick(BottomChild bottomChild, int position) {
////        count++;
////        if (count != 1) {
//          ((DeliverSingleEachFragment) bottomChild.getFragment()).clickBottom();
////        }
//      }
//    });
  }

  @Override
  public void initData() {
    getData();
  }

  @Override
  public void getData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
