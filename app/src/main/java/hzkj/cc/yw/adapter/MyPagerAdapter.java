package hzkj.cc.yw.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.internal.LinkedTreeMap;
import com.xuexiang.xui.widget.textview.BadgeView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.HomeItem;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class MyPagerAdapter extends PagerAdapter {


  @Getter
  public enum Home {
    RENTCAR(1, "1"),
    APPLYBUY(2, "2"),
    GOTOREPAIR(3, "3"),
    DELIVER(4, "物流流程"),
    RENTCARMANAGE(5, "租车管理");
    final int tag;
    final String msg;

    Home(int tag, String msg) {
      this.tag = tag;
      this.msg = msg;
    }
  }

  private final LinkedTreeMap<String, Integer> map;
  private Context mContext;
  private List<HomeItem> mData;
  int pageNum;
  List<BadgeView> badgeViews = new ArrayList<>();
  int lines;

  public MyPagerAdapter(Context context, List<HomeItem> list, int pageNum, int lines,
      LinkedTreeMap<String, Integer> map) {
    mContext = context;
    mData = list;
    this.map = map;
    this.pageNum = pageNum;
    this.lines = lines;
    init();
  }

  private void init() {
  }

  @Override
  public int getCount() {
    if (mData.size() % (lines * pageNum) == 0) {
      return mData.size() / pageNum / lines;
    } else {
      return (mData.size() / pageNum / lines) + 1;
    }
  }

  Integer getIndex(int tag) {
    for (int i = 0; i < mData.size(); i++) {
      if (mData.get(i).getTag() == tag) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    int temp;
//    if (position == getCount() - 1) {
//      temp = (mData.size() - position * lines * pageNum) / pageNum + 1;
//    } else {
    temp = lines;
//    }
    LinearLayout superLayout = new LinearLayout(mContext);
    LinearLayout.LayoutParams superLayoutParams = new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    superLayout.setLayoutParams(superLayoutParams);
    superLayout.setOrientation(LinearLayout.VERTICAL);
    container.addView(superLayout);
    for (int j = 0; j < temp; j++) {
      LinearLayout linearLayout = new LinearLayout(mContext);
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT, 0);
      layoutParams.weight = 1;
      linearLayout.setLayoutParams(layoutParams);
      linearLayout.setPadding(UiUtil.dipTopx(mContext, 5), UiUtil.dipTopx(mContext, 10), 0, 0);
      for (int i = 0; i < pageNum; i++) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.inside_item, null);
        int index = (position * lines * pageNum) + j * pageNum + i;
        if (index < mData.size()) {
          mData.get(index).setView(view);
        }
        if (badgeViews.size() > 0) {
          for (BadgeView badgeView : badgeViews) {
            badgeView.hide();
          }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
          BadgeView badgeView = new BadgeView(mContext);
          badgeViews.add(badgeView);
          for (Home home : Home.values()) {
            if (home.getMsg().equals(entry.getKey())) {
              badgeView.setTargetView(mData.get(getIndex(home.getTag())).getView());
            }
          }
          if (entry.getValue() > 0) {
            badgeView.setBadgeCount(entry.getValue());
            badgeView.setBadgeMargin(0, 0, UiUtil.dipTopx(mContext, 5), 0);
            badgeView.show();
          } else {
            badgeView.hide();
          }
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
            ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
//        if (j != 0) {
//          params.topMargin = UiUtil.dipTopx(mContext, 10);
//        }
        view.setLayoutParams(params);
        TextView s = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.image);
//        if (position == getCount() - 1 & j == temp - 1 & i > mData.size() % pageNum - 1) {
//          imageView.setVisibility(View.GONE);
//          s.setVisibility(View.GONE);
//        } else {
        final int location = position * lines * pageNum + j * pageNum + i;
        imageView.setImageDrawable(mData.get(location)
            .getDrawable());
        s.setText(mData.get(location)
            .getTitle());
        view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (mData.get(location)
                .getIntent() != null) {
              mContext.startActivity(mData.get(location)
                  .getIntent());
            }
          }
        });
//        }
        linearLayout.addView(view);
      }
      superLayout.addView(linearLayout);
    }

    return superLayout;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    // super.destroyItem(container,position,object); 这一句要删除，否则报错
    container.removeView((View) object);
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }
}

