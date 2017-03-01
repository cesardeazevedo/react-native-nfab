package com.nfab;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.github.clans.fab.FloatingActionButton;

public class NFabButtonManager extends SimpleViewManager<FloatingActionButton> {

  private FloatingActionButton view;

  private final static String REACT_CLASS = "RCTNFabButton";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public FloatingActionButton createViewInstance(ThemedReactContext reactContext) {
    view = new FloatingActionButton(reactContext);
    view.setOnClickListener(new NFabButtonListener());
    return view;
  }

  @Override
  public LayoutShadowNode createShadowNodeInstance() {
    return new NFabButtonShadowNode();
  }

  @Override
  public Class getShadowNodeClass() {
    return NFabButtonShadowNode.class;
  }

  @ReactProp(name = "src")
  public void setSrc(FloatingActionButton view, String src) {
    Drawable icon = ResourceDrawableIdHelper.getInstance().getResourceDrawable(view.getContext(), src);
    view.setImageDrawable(icon);
  }

  @ReactProp(name = "marginBottom")
  public void setMarginBottom(FloatingActionButton view, int margin) {
  }

  @ReactProp(name = "marginRight")
  public void setMarginRight(FloatingActionButton view, int margin) {
  }

  @ReactProp(name = "colorNormal")
  public void setColorNormal(FloatingActionButton view, String color) {
    view.setColorNormal(Color.parseColor(color));
  }

  @ReactProp(name = "colorPressed")
  public void setColorPressed(FloatingActionButton view, String color) {
    view.setColorPressed(Color.parseColor(color));
  }

  @ReactProp(name = "colorRipple")
  public void setColorRipple(FloatingActionButton view, String color) {
    view.setColorRipple(Color.parseColor(color));
  }

  @ReactProp(name = "showShadow")
  public void setShowShadow(FloatingActionButton view, boolean showShadow) {
    view.setShowShadow(showShadow);
  }

  @ReactProp(name = "shadowColor")
  public void setShadowColor(FloatingActionButton view, String shadowColor) {
    view.setShadowColor(Color.parseColor(shadowColor));
  }

  @ReactProp(name = "shadowRadius", defaultFloat = 4.0f)
  public void setShadowRadius(FloatingActionButton view, float radius) {
    view.setShadowRadius(radius);
  }

  @ReactProp(name = "shadowXOffset", defaultFloat = 1.0f)
  public void setShadowXOffset(FloatingActionButton view, float offset) {
    view.setShadowXOffset(offset);
  }

  @ReactProp(name = "shadowYOffset", defaultFloat = 3.0f)
  public void setShadowYOffset(FloatingActionButton view, float offset) {
    view.setShadowYOffset(offset);
  }

  @ReactProp(name = "size")
  public void setSize(FloatingActionButton view, String size) {
    view.setButtonSize(
        size.equals("mini")
            ? FloatingActionButton.SIZE_MINI
            : FloatingActionButton.SIZE_NORMAL
    );
  }

  @ReactProp(name = "label")
  public void setLabel(FloatingActionButton view, String label) {
    view.setLabelText(label);
  }

  private class NFabButtonListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
      WritableMap event = Arguments.createMap();
      ReactContext reactContext = (ReactContext) v.getContext();
      reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(v.getId(), "topChange", event);
    }
  }
}