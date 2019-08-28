package hzkj.cc.yw.ui.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.google.gson.internal.LinkedTreeMap;
import com.hacknife.carouselbanner.Banner;
import com.hacknife.carouselbanner.CoolCarouselBanner;
import com.vise.log.ViseLog;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.textview.BadgeView;
import com.xuexiang.xui.widget.textview.MarqueeTextView;
import hzkj.cc.yw.ImageFactory;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.MyPagerAdapter;
import hzkj.cc.yw.adapter.MyPagerAdapter.Home;
import hzkj.cc.yw.bean.HomeBannerItem;
import hzkj.cc.yw.bean.HomeItem;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.HomeContract;
import hzkj.cc.yw.model.HomeModel;
import hzkj.cc.yw.presenter.HomePresenter;
import hzkj.cc.yw.ui.activity.ApplyBuyActivity;
import hzkj.cc.yw.ui.activity.AttenceActivity;
import hzkj.cc.yw.ui.activity.ClientManageActivity;
import hzkj.cc.yw.ui.activity.DeliverActivity;
import hzkj.cc.yw.ui.activity.GoToRepairActivity;
import hzkj.cc.yw.ui.activity.RentCarManageActivity;
import hzkj.cc.yw.ui.activity.RentCarReviewActivity;
import hzkj.cc.yw.ui.activity.WorkOrderActivity;
import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment<HomeContract.Presenter> implements
    HomeContract.View {

  @BindView(R.id.banner)
  CoolCarouselBanner banner;
  @BindView(R.id.marquee)
  MarqueeTextView marquee;
  @BindView(R.id.notifyOne)
  TextView notifyOne;
  @BindView(R.id.notifyTwo)
  TextView notifyTwo;
  //  @BindView(R.id.onePart)
//  LinearLayout onePart;
  @BindView(R.id.twoPart)
  ViewPager twoPart;
  //  @BindView(R.id.situationReport)
//  LinearLayout situationReport;
//  @BindView(R.id.attence)
//  LinearLayout attence;
  MyPagerAdapter twoAdapter;
  List<BadgeView> badgeViews = new ArrayList<>();
  List<HomeItem> homeItems;

  @Override
  void doOnVisible() {
  }

  @Override
  HomeContract.Presenter createPresenter() {
    return new HomePresenter();
  }

  @Override
  public void getData() {
//    new Handler().post(new Runnable() {
//      @Override
//      public void run() {
    getPresenter().startGetCounts();
//      }
//    });
    getPresenter().
        startGetBannerItems();
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_homepage;
  }

  @Override
  public void initView() {
    marquee.startSimpleRoll(new ArrayList<String>() {{
      add("踩刹车");
      add("说三道四四大石窟和");
      add("山东省颠三倒四颠三倒四颠三倒四颠三倒四第三代");
    }});
    marquee.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ViseLog.d(marquee.getCurrentIndex());
      }
    });
    notifyOne.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/义启嘟嘟体.ttf"));
    notifyTwo.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/义启嘟嘟体.ttf"));
  }

  @Override
  public void initData() {
  }


  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == HomeModel.HOMECOUNT) {
      LinkedTreeMap<String, Integer> map = (LinkedTreeMap) object;
      ViseLog.d(map.get(Home.RENTCARMANAGE.getMsg()));
      homeItems = new ArrayList<>();
      homeItems.add(new HomeItem(Home.GOTOREPAIR.getTag(), "返修查询",
          getResources().getDrawable(R.drawable.ic_beijiansongxiu),
          new Intent(getActivity(), GoToRepairActivity.class)));
      homeItems.add(new HomeItem("客户管理", getResources().getDrawable(R.drawable.ic_kehuguanli_2),
          new Intent(getActivity(), ClientManageActivity.class)));
      Intent intent = new Intent(getActivity(), RentCarManageActivity.class);
      intent.putExtra("count", map.get(Home.RENTCARMANAGE.getMsg()));
      homeItems.add(new HomeItem(Home.RENTCARMANAGE.getTag(), "租车管理",
          getResources().getDrawable(R.drawable.ic_chuzuche_04),
          intent));
      homeItems.add(new HomeItem(Home.RENTCAR.getTag(), "租车审核",
          getResources().getDrawable(R.drawable.ic_zhuantishenhe),
          new Intent(getActivity(), RentCarReviewActivity.class)));
      homeItems.add(new HomeItem(Home.APPLYBUY.getTag(), "申购查询",
          getResources().getDrawable(R.drawable.ic_shengou),
          new Intent(getActivity(), ApplyBuyActivity.class)));
      homeItems.add(new HomeItem(Home.DELIVER.getTag(), "物流管理",
          getResources().getDrawable(R.drawable.ic_wuliuguanli),
          new Intent(getActivity(), DeliverActivity.class)));
      homeItems.add(new HomeItem("考勤", getResources().getDrawable(R.drawable.ic_kaoqin_),
          new Intent(getActivity(), AttenceActivity.class)));
      homeItems.add(new HomeItem("情况汇报", getResources().getDrawable(R.drawable.ic_gongzuohuibao),
          null));
      homeItems.add(new HomeItem("工单管理", getResources().getDrawable(R.drawable.ic_gongdan),
          new Intent(getActivity(), WorkOrderActivity.class)));
      homeItems.add(new HomeItem("通知公告", getResources().getDrawable(R.drawable.home_notify),
          null));
      homeItems.add(new HomeItem("知识文档", getResources().getDrawable(R.drawable.home_knowledge),
          null));
      homeItems.add(new HomeItem("工作日志", getResources().getDrawable(R.drawable.home_record),
          null));
      twoAdapter = new MyPagerAdapter(getActivity(), homeItems, 4, 3, map);
      twoPart.setAdapter(twoAdapter);
    } else if (tag == HomeModel.HOMEBANNERITEM) {
      Banner.init(new ImageFactory());
      final List<HomeBannerItem> homeBannerItems = (List<HomeBannerItem>) object;
      List<BannerItem> bannerItems = new ArrayList<>();
      List<String> strings = new ArrayList<>();
      for (HomeBannerItem homeBannerItem : homeBannerItems) {
        strings.add(RetrofitFactory.BASE_URL_IMAGE + homeBannerItem.getHomePage_pic());
      }
      banner.initBanner(strings);
//      banner.setOnItemClickL(new BaseBanner.OnItemClickL() {
//        @Override
//        public void onItemClick(final int position) {
//          UiUtil.jumpToActivity(getActivity(), HomeBannerWebActivity.class,
//              new ArrayMap<String, String>() {{
//                put("url", homeBannerItems.get(position)
//                    .getHomePage_src());
//              }}, false, false, 0);
//        }
//      });
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }

  @Override
  public void onPause() {
    super.onPause();
//    banner.pause();//暂停轮播
  }

  @Override
  public void onResume() {
    super.onResume();
    getData();
//    banner.start();//开始轮播
  }
//  public static class BannerViewHolder implements MZViewHolder<HomeBannerItem> {
//
//    private ImageView mImageView;
//
//    @Override
//    public View createView(Context context) {
//      // 返回页面布局
//      View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
//      mImageView = (ImageView) view.findViewById(R.id.banner_image);
//      return view;
//    }
//
//    @Override
//    public void onBind(Context context, int i, HomeBannerItem src) {
//      ViseLog.d(src);
//      Bitmap bitmap = ImageUtil.stringToBitmap(src.getUrl());
//      mImageView.setImageBitmap(ImageUtil.stringToBitmap(src.getUrl()));
//    }
//  }
}
