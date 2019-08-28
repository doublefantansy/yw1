package hzkj.cc.yw.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import hzkj.cc.yw.R;
import hzkj.cc.yw.config.MyApplication;
import java.util.ArrayList;

/**
 * ui操作工具类
 */
public class UiUtil {

  public static void jumpToActivity(Activity activity,
      Class<? extends Activity> targetActivityClass, ArrayMap<String, String> arrayMap,
      boolean isFinish, boolean hasResult, int resultCode) {
    Intent intent = new Intent(activity, targetActivityClass);
    if (arrayMap != null) {
      for (int i = 0; i < arrayMap.size(); i++) {
        intent.putExtra(arrayMap.keyAt(i), arrayMap.valueAt(i));
      }
    }
    if (hasResult) {
      activity.startActivityForResult(intent, resultCode);
    } else {
      activity.startActivity(intent);
    }
//        context.startActivity(intent);
    if (isFinish) {
      activity.finish();
    }
    activity.overridePendingTransition(R.anim.left_in, R.anim.right_out);
  }

  public static void jumpToActivity1(Activity activity,
      Class<? extends Activity> targetActivityClass, ArrayList<String> list,
      boolean isFinish, boolean hasResult, int resultCode) {
    Intent intent = new Intent(activity, targetActivityClass);
    if (list != null) {
      intent.putStringArrayListExtra("urls", list);
    }
    if (hasResult) {
      activity.startActivityForResult(intent, resultCode);
    } else {
      activity.startActivity(intent);
    }
//        context.startActivity(intent);
    if (isFinish) {
      activity.finish();
    }
    activity.overridePendingTransition(R.anim.left_in, R.anim.right_out);
  }

  public static void fragmentJumpToActivity(Fragment fragment,
      Class<? extends Activity> targetActivityClass, ArrayMap<String, String> arrayMap,
      boolean hasResult, int resultCode) {
    Intent intent = new Intent(fragment.getActivity(), targetActivityClass);
    if (arrayMap != null) {
      for (int i = 0; i < arrayMap.size(); i++) {
        intent.putExtra(arrayMap.keyAt(i), arrayMap.valueAt(i));
      }
    }
    if (hasResult) {
      fragment.startActivityForResult(intent, resultCode);
    } else {
      fragment.startActivity(intent);
    }
    fragment.getActivity().overridePendingTransition(R.anim.left_in, R.anim.right_out);
  }

  public static int dipTopx(Context context, float dpValue) {
    final float scale = context.getResources()
        .getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int spTopx(Context context, float spValue) {
    final float fontScale = context.getResources()
        .getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
  }

  public static int pxTodip(Context context, float pxValue) {
    final float scale = context.getResources()
        .getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public static void disableStatusBar(Context context) {
    ((AppCompatActivity) context).getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }

  public static Bitmap blurBitmap(Bitmap bitmap) {
    //Let's create an empty bitmap with the same size of the bitmap we want to blur
    Bitmap outBitmap = Bitmap
        .createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    //Instantiate a new Renderscript
    RenderScript rs = RenderScript.create(MyApplication.getApplication());
    //Create an Intrinsic Blur Script using the Renderscript
    ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
    //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
    Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
    Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
    //Set the radius of the blur
    blurScript.setRadius(25.f);
    //Perform the Renderscript
    blurScript.setInput(allIn);
    blurScript.forEach(allOut);
    //Copy the final bitmap created by the out Allocation to the outBitmap
    allOut.copyTo(outBitmap);
    //recycle the original bitmap
    bitmap.recycle();
    //After finishing everything, we destroy the Renderscript.
    rs.destroy();
    return outBitmap;
  }
}
