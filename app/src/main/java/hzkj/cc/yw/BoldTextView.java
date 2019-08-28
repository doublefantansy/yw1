package hzkj.cc.yw;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class BoldTextView extends android.support.v7.widget.AppCompatTextView {

  public BoldTextView(Context context) {
    super(context);
  }

  public BoldTextView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    getPaint().setFakeBoldText(true);
  }
}
