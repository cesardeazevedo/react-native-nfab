package com.nfab;

import android.widget.RelativeLayout;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class RelativeLayoutManager extends ViewGroupManager<RelativeLayout> {

  private final static String REACT_CLASS = "RCTRelativeLayout";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public RelativeLayout createViewInstance(ThemedReactContext context) {
    int width = RelativeLayout.LayoutParams.MATCH_PARENT;
    int height = RelativeLayout.LayoutParams.MATCH_PARENT;

    RelativeLayout view = new RelativeLayout(context);
    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
    view.setLayoutParams(params);

    return view;
  }
}