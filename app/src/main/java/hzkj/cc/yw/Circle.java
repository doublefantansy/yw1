package hzkj.cc.yw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.vise.log.ViseLog;

public class Circle extends View {

  public static final int FIRST = 111111;
  public static final int MIDDLE = 111112;
  public static final int LAST = 111113;
  Paint firstCircleOutPaint;
  Paint firstCircleInPaint;
  Paint circlePaint;
  Paint linePaint;
  float firstOutRadius = 15;
  float firstInRadius = 7.5f;
  float otherRadius = 15;
  float x;
  float y;
  private int status;
  int circleLineMargin = 5;
  int height;
  boolean onlyOne;

  public Circle(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    ViseLog.d(3);
    circlePaint = new Paint();
    circlePaint.setAntiAlias(true);
    circlePaint.setColor(getResources().getColor(R.color.deepBlue));
    linePaint = new Paint();
    linePaint.setAntiAlias(true);
    linePaint.setStrokeWidth(3);
    linePaint.setColor(getResources().getColor(R.color.deepBlue));
    firstCircleOutPaint = new Paint();
    firstCircleOutPaint.setStyle(Paint.Style.STROKE);
    firstCircleOutPaint.setColor(getResources().getColor(R.color.deepBlue));
    firstCircleOutPaint.setAntiAlias(true);
    firstCircleOutPaint.setStrokeWidth(3);
    firstCircleInPaint = new Paint();
    firstCircleInPaint.setAntiAlias(true);
    firstCircleInPaint.setColor(getResources().getColor(R.color.deepBlue));
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    x = MeasureSpec.getSize(widthMeasureSpec) / 2;
    y = height / 2;
  }

  public void initHeight(int height) {
    this.height = height;
  }

  public void setStatus(int status) {
    ViseLog.d(2);
    this.status = status;
  }

  public void isOnlyOne(boolean onlyOne) {
    this.onlyOne = onlyOne;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    ViseLog.d(1);
    if (onlyOne) {
      canvas.drawCircle(x, y, firstOutRadius, firstCircleOutPaint);
      canvas.drawCircle(x, y, firstInRadius, firstCircleInPaint);
    } else {
      if (status != FIRST) {
        ViseLog.d(11);
        canvas.drawLine(x, 0, x, getHeight() / 2 - otherRadius - circleLineMargin, linePaint);
      }
      if (status == FIRST) {
        canvas.drawCircle(x, y, firstOutRadius, firstCircleOutPaint);
        canvas.drawCircle(x, y, firstInRadius, firstCircleInPaint);
      } else {
        canvas.drawCircle(x, y, otherRadius, circlePaint);
      }
      if (status != LAST) {
        if (status == FIRST) {
          canvas.drawLine(x, y + firstOutRadius + circleLineMargin, x,
              y + firstOutRadius + circleLineMargin + getHeight() / 2 - firstOutRadius, linePaint);
        } else {
          canvas.drawLine(x, y + otherRadius + circleLineMargin, x,
              y + otherRadius + circleLineMargin + getHeight() / 2 - otherRadius, linePaint);
        }
      }
    }
  }
}
