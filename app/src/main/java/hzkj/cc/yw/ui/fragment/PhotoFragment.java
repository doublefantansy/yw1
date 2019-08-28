package hzkj.cc.yw.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import hzkj.cc.library.LoadingDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.ui.activity.PhotoActivity;
//import hzkj.cc.yw.ui.activity.PhotoActivity.Callback;

public class PhotoFragment extends Fragment {

  private final String url;
  private PhotoView photoView;

  public PhotoFragment(String url) {
    this.url = url;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
    View view = inflater.inflate(R.layout.photo_fragment, container, false);
    photoView = view.findViewById(R.id.photoView);
    loadingDialog.show();
    Glide.with(getActivity()).load(url).listener(new RequestListener<Drawable>() {
      @Override
      public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
          boolean isFirstResource) {
        return false;
      }

      @Override
      public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
          DataSource dataSource, boolean isFirstResource) {
        loadingDialog.dismiss();
        return false;
      }
    })
        .into(photoView);
    photoView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getActivity().finish();
      }
    });
    return view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}
