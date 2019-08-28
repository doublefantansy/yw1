package hzkj.cc.yw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import hzkj.cc.yw.utils.UiUtil;

public class ExampleView extends View {

  Paint paint;
  int color;
  int radius = UiUtil.dipTopx(getContext(), 7);
  int strokeWidth = UiUtil.dipTopx(getContext(), 1);

  public ExampleView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ExampleView);
    color = array.getColor(R.styleable.ExampleView_color, Color.BLACK);
    array.recycle();
    init();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
      setMeasuredDimension((radius + strokeWidth) * 2, (radius + strokeWidth) * 2);
    }
  }

  private void init() {
    paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(UiUtil.dipTopx(getContext(), 1));
    paint.setAntiAlias(true);
  }

  public void setColor(int color) {
    this.color = color;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    paint.setColor(color);
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
  }
}
