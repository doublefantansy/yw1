package hzkj.cc.yw.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import hzkj.cc.library.LoadingDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.ui.fragment.PhotoFragment;
import java.util.List;

public class PhotoActivity extends RxAppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.photo);
    final LoadingDialog loadingDialog = new LoadingDialog(this);
    ViewPager viewPager = findViewById(R.id.viewPager);
    final TextView title = findViewById(R.id.title);
    final List<String> list = getIntent().getStringArrayListExtra("urls");
    title.setText("1/" + list.size());
    viewPager.addOnPageChangeListener(new OnPageChangeListener() {
      @Override
      public void onPageScrolled(int i, float v, int i1) {
      }

      @Override
      public void onPageSelected(int i) {

      }

      @Override
      public void onPageScrollStateChanged(int i) {
      }
    });
    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override
      public Fragment getItem(int i) {
        return new PhotoFragment(RetrofitFactory.URL_TEST + list.get(i));
      }

      @Override
      public int getCount() {
        return list.size();
      }
    });
  }
}
