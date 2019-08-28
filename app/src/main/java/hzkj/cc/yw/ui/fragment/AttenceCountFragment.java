package hzkj.cc.yw.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.AttenceCount;
import hzkj.cc.yw.contract.AttenceCountContract;
import hzkj.cc.yw.presenter.AttenceCountPresenter;
import hzkj.cc.yw.utils.TimeUtil;

public class AttenceCountFragment extends BaseFragment<AttenceCountContract.Presenter> implements
    AttenceCountContract.View, OnChartValueSelectedListener {

  @BindView(R.id.layout)
  RelativeLayout layout;
  @BindView(R.id.stateLayout)
  StateFulLayout stateFulLayout;
  @BindView(R.id.chart)
  PieChart pieChart;
  @BindView(R.id.displayStartTime)
  TextView displayStartTime;
  @BindView(R.id.displayEndTime)
  TextView displayEndTime;
  @BindView(R.id.content)
  TextView content;
  List<AttenceCount> list;
  int count;
  String startDate = TimeUtil.getLimitDateString(Calendar.getInstance()
      .get(Calendar.YEAR), Calendar.getInstance()
      .get(Calendar.MONTH), TimeUtil.MONTHSTART, TimeUtil.YMD);
  String endDate = TimeUtil.dateToString(new Date(), TimeUtil.YMD);

  @Override
  void doOnVisible() {
  }

  @Override
  AttenceCountContract.Presenter createPresenter() {
    return new AttenceCountPresenter();
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_attencecount;
  }

  @Override
  public void initView() {
    stateFulLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        getPresenter().startGetAttenceCount(startDate, endDate);
      }
    }, layout);
    stateFulLayout.showState(StateFulLayout.LOADING);
    initPieChart();
    displayStartTime
        .setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/义启嘟嘟体.ttf"));
    displayEndTime
        .setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/义启嘟嘟体.ttf"));
    content.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/义启嘟嘟体.ttf"));
  }

  private void initPieChart() {
    //  是否显示中间的洞
    pieChart.setDrawHoleEnabled(false);
//        pieChart.setHoleRadius(40f);//设置中间洞的大小
    // 半透明圈
    pieChart.setTransparentCircleRadius(30f);
    pieChart.setTransparentCircleColor(Color.WHITE); //设置半透明圆圈的颜色
    pieChart.setTransparentCircleAlpha(125); //设置半透明圆圈的透明度
    //饼状图中间可以添加文字
    pieChart.setDrawCenterText(false);
    pieChart.setCenterText("民族"); //设置中间文字
    pieChart.setCenterTextColor(Color.parseColor("#a1a1a1")); //中间问题的颜色
    pieChart.setCenterTextSizePixels(36);  //中间文字的大小px
    pieChart.setCenterTextRadiusPercent(1f);
    pieChart.setCenterTextTypeface(Typeface.DEFAULT); //中间文字的样式
    pieChart.setCenterTextOffset(0, 0); //中间文字的偏移量
    pieChart.setRotationAngle(0);// 初始旋转角度
    pieChart.setRotationEnabled(false);// 可以手动旋转
    pieChart.setUsePercentValues(false);//显示成百分比
    pieChart.setHighlightPerTapEnabled(false);
    pieChart.getDescription()
        .setEnabled(false); //取消右下角描述
    //是否显示每个部分的文字描述
    pieChart.setDrawEntryLabels(false);
    pieChart.setEntryLabelColor(Color.RED); //描述文字的颜色
    pieChart.setEntryLabelTextSize(14);//描述文字的大小
    pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD); //描述文字的样式
    //图相对于上下左右的偏移
    pieChart.setExtraOffsets(25, 0, 25, 0);
    //图标的背景色
    pieChart.setBackgroundColor(Color.TRANSPARENT);
//        设置pieChart图表转动阻力摩擦系数[0,1]
    pieChart.setDragDecelerationFrictionCoef(0.75f);
    //获取图例
    Legend legend = pieChart.getLegend();
    legend.setOrientation(Legend.LegendOrientation.VERTICAL);  //设置图例水平显示
    legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //顶部
    legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //右对其
//        legend.setXEntrySpace(7f);//x轴的间距
//        legend.setYEntrySpace(3f); //y轴的间距
//        legend.setYOffset(20f);  //图例的y偏移量
//        legend.setXOffset(20f);  //图例x的偏移量
    legend.setTextColor(Color.parseColor("#a1a1a1")); //图例文字的颜色
    legend.setTextSize(13);  //图例文字的大小
  }

  public void showRingPieChart(List<PieEntry> yvals, List<Integer> colors) {
    //显示为圆环
    pieChart.setDrawHoleEnabled(true);
    pieChart.setHoleRadius(45f);//设置中间洞的大小
    //数据集合
    PieDataSet dataset = new PieDataSet(yvals, "");
    //填充每个区域的颜色
    dataset.setColors(colors);
    //是否在图上显示数值
    dataset.setDrawValues(true);
//        文字的大小
    dataset.setValueTextSize(14);
//        文字的颜色
    dataset.setValueTextColor(Color.RED);
//        文字的样式
    dataset.setValueTypeface(Typeface.DEFAULT_BOLD);
//      当值位置为外边线时，表示线的前半段长度。
    dataset.setValueLinePart1Length(0.4f);
//      当值位置为外边线时，表示线的后半段长度。
    dataset.setValueLinePart2Length(0.4f);
//      当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
    dataset.setValueLinePart1OffsetPercentage(80f);
    // 当值位置为外边线时，表示线的颜色。
    dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
//        设置Y值的位置是在圆内还是圆外
    dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        设置Y轴描述线和填充区域的颜色一致
    dataset.setUsingSliceColorAsValueLineColor(false);
//        设置每条之前的间隙
    dataset.setSliceSpace(0);
    //设置饼状Item被选中时变化的距离
    dataset.setSelectionShift(5f);
    //填充数据
    PieData pieData = new PieData(dataset);
//        格式化显示的数据为%百分比
    pieData.setValueFormatter(new ValueFormatter() {
      @Override
      public String getFormattedValue(float value) {
        return ((int) value) + "次";
      }
    });
//        显示试图
    pieChart.setData(pieData);
  }

  @Override
  public void initData() {
  }

  @Override
  public void getData() {
    getPresenter().startGetAttenceCount(startDate, endDate);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    displayStartTime.setText(startDate);
    displayEndTime.setText(endDate);
    count = 0;
//        pieChart.setCenterText(startDate + " 至 " + endDate);
    stateFulLayout.showState(StateFulLayout.CONTENT);
    ArrayList<Integer> colors = new ArrayList<Integer>();
    ArrayList<Integer> results = new ArrayList<Integer>();
    colors.add(getResources().getColor(R.color.myGreen));
    colors.add(getResources().getColor(R.color.myRed));
    colors.add(getResources().getColor(R.color.myBlue));
    colors.add(getResources().getColor(R.color.backGroundGray));
    ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
    list = (List<AttenceCount>) object;
    for (int i = 0; i < list.size(); i++) {
      AttenceCount attenceCount = list.get(i);
//            count+=attenceCount.getValue();
      if (attenceCount.getValue() != 0) {
        count += attenceCount.getValue();
        entries.add(new PieEntry(attenceCount.getValue(), attenceCount.getText()));
        results.add(colors.get(i));
      }
    }
    showRingPieChart(entries, results);
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }

  @Override
  public void getSignalByActivity(String startDate, String endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
    getPresenter().startGetAttenceCount(this.startDate, this.endDate);
    stateFulLayout.showState(StateFulLayout.LOADING);
//        pieChart.setCenterText(this.startDate + "-" + this.endDate);
  }

  @Override
  public void onValueSelected(Entry e, Highlight h) {
  }

  @Override
  public void onNothingSelected() {
  }
}
