package hzkj.cc.yw.ui.fragment;

import java.util.ArrayList;

import butterknife.BindView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.contract.DeliverProcedureContract;
import hzkj.cc.yw.presenter.DeliverProcedurePresenter;

/**
 * @author cc
 */
public class DeliverProcedureFragment extends
        BaseFragment<DeliverProcedureContract.Presenter> implements DeliverProcedureContract.View {

    @BindView(R.id.bottomNavigation)
    MyBottomNavigation bottomNavigation;
    DeliverProcedureEachFragment fragment;

    @Override
    void doOnVisible() {
    }

    @Override
    DeliverProcedureContract.Presenter createPresenter() {
        return new DeliverProcedurePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_deliver_procedure;
    }

    @Override
    public void initView() {
        fragment = DeliverProcedureEachFragment.newInstance(DeliverInfo.HASGET);
        bottomNavigation.initBottomChildren(getActivity().getSupportFragmentManager(),
                new ArrayList<BottomChild>() {{
                    add(new BottomChild("待收货",
                            DeliverProcedureEachFragment.newInstance(DeliverInfo.HASNOTGET),
                            getResources().getDrawable(R.drawable.ic_daifahuo_2),
                            getResources().getDrawable(R.drawable.ic_daifahuo)));
                    add(new BottomChild("已收货", fragment,
                            getResources().getDrawable(R.drawable.ic_yishouhuo),
                            getResources().getDrawable(R.drawable.ic_yishouhuo_2)));
                }}, 0);
        bottomNavigation.setTextColors(new ArrayList<Integer>() {
            {
                add(getResources().getColor(R.color.bottom_blue));
                add(getResources().getColor(R.color.bottom_green));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getData() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void updateSuccessUi(Object object, int tag) {
    }

    @Override
    public void updateFailUi(String msg, Object object) {
        super.updateFailUi(msg, object);
    }

    @Override
    public void updateCheckSuccess(Object object) {
    }
}
