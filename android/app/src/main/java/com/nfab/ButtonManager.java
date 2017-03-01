package com.nfab;

import android.support.design.widget.CoordinatorLayout;
import android.widget.Button;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class ButtonManager extends SimpleViewManager<Button> {

  private final static String REACT_CLASS = "RCTButtonTest";

  public Button createViewInstance(ThemedReactContext context) {
    Button view = new Button(context);

    int width  = CoordinatorLayout.LayoutParams.WRAP_CONTENT;
    int height = CoordinatorLayout.LayoutParams.WRAP_CONTENT;

    CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(width, height);
    view.setLayoutParams(params);
    view.setText("Click works under the red box");

    return view;
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public Class getShadowNodeClass() {
    return ButtonShadowNode.class;
  }

  @Override
  public LayoutShadowNode createShadowNodeInstance() {
    return new ButtonShadowNode();
  }
}
