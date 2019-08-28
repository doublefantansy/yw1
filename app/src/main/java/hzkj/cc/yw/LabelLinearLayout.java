package hzkj.cc.yw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import cn.label.library.LabelViewHelper;

public class LabelLinearLayout extends LinearLayout {

  private LabelViewHelper mLabelViewHelper;
  private boolean mLabelVisable = true;

  public LabelLinearLayout(Context context) {
    this(context, null);
  }

  public LabelLinearLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LabelLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mLabelViewHelper = new LabelViewHelper(context, attrs);
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (mLabelVisable) {
      mLabelViewHelper.drawLabel(this, canvas);
    }
  }

  public void setTextContent(String content) {
    mLabelViewHelper.setTextContent(content);
    invalidate();
  }

  public void setTextTitle(String title) {
    mLabelViewHelper.setTextTitle(title);
    invalidate();
  }

  public void setLabelBackGroundColor(int color) {
    mLabelViewHelper.setLabelBackGroundColor(color);
    invalidate();
  }

  public void setLabelVisable(boolean visable) {
    mLabelVisable = visable;
    postInvalidate();
  }
}

