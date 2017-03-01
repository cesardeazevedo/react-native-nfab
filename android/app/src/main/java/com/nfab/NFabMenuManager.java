package com.nfab;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Map;

public class NFabMenuManager extends ViewGroupManager<FloatingActionMenu> {

  private FloatingActionMenu view;

  private final static String REACT_CLASS = "RCTNFabMenu";

  private final static int COMMAND_HIDE_MENU = 0;

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public FloatingActionMenu createViewInstance(ThemedReactContext reactContext) {
    view = new FloatingActionMenu(reactContext);

    int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
    int height = RelativeLayout.LayoutParams.WRAP_CONTENT;

    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

    view.setLayoutParams(params);

    // Debug bounds of the view, that has been hiding touch events on react-native underlying views
    Drawable d = new ColorDrawable(0xAAFF0000);
    d.setColorFilter(0xAAFF0000, PorterDuff.Mode.MULTIPLY);
    view.setBackground(d);

    view.setOnMenuToggleListener(new NFabMenuToggleListener(view));
    view.setOnMenuButtonClickListener(new NFabMenuClickListener());

    return view;
  }

  @Override
  public void addView(FloatingActionMenu parent, View child, int index) {
    parent.addMenuButton((FloatingActionButton) child);
  }

//  @Override
//  public LayoutShadowNode createShadowNodeInstance() {
//    return new NFabMenuShadowNode();
//  }
//
//  @Override
//  public Class getShadowNodeClass() {
//    return NFabMenuShadowNode.class;
//  }

  private RelativeLayout.LayoutParams getLayoutParams() {
    return (RelativeLayout.LayoutParams) view.getLayoutParams();
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  @ReactProp(name = "alignParentBottom", defaultBoolean = true)
  public void setAlignParentBottom(FloatingActionMenu view, boolean align) {
    RelativeLayout.LayoutParams params = this.getLayoutParams();
    if (align) {
      params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    } else {
      params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    }
    view.setLayoutParams(params);
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  @ReactProp(name = "alignParentRight", defaultBoolean = true)
  public void setAlignParentRight(FloatingActionMenu view, boolean align) {
    RelativeLayout.LayoutParams params = this.getLayoutParams();
    if (align) {
      params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    } else {
      params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    }
    view.setLayoutParams(params);
  }

  @ReactProp(name = "marginLeft")
  public void setMarginLeft(FloatingActionMenu view, int margin) {
    RelativeLayout.LayoutParams params = this.getLayoutParams();
    params.leftMargin = (int) PixelUtil.toPixelFromDIP(margin);
    view.setLayoutParams(params);
  }

  @ReactProp(name = "marginRight")
  public void setMarginRight(FloatingActionMenu view, int margin) {
    RelativeLayout.LayoutParams params = this.getLayoutParams();
    params.rightMargin = (int) PixelUtil.toPixelFromDIP(margin);
    view.setLayoutParams(params);
  }

  @ReactProp(name = "marginBottom")
  public void setMarginBottom(FloatingActionMenu view, int margin) {
    RelativeLayout.LayoutParams params = this.getLayoutParams();
    params.bottomMargin = (int) PixelUtil.toPixelFromDIP(margin);
    view.setLayoutParams(params);
  }

  @ReactProp(name = "colorNormal")
  public void setColorNormal(FloatingActionMenu view, String color) {
    view.setMenuButtonColorNormal(Color.parseColor(color));
  }

  @ReactProp(name = "colorPressed")
  public void setColorPressed(FloatingActionMenu view, String color) {
    view.setMenuButtonColorPressed(Color.parseColor(color));
  }

  @ReactProp(name = "colorRipple")
  public void setColorRipple(FloatingActionMenu view, String color) {
    view.setMenuButtonColorRipple(Color.parseColor(color));
  }

  @ReactProp(name = "animationDelayPerItem", defaultInt = 50)
  public void setAnimationDelayPerItem(FloatingActionMenu view, int delay) {
    view.setAnimationDelayPerItem(delay);
  }

  @ReactProp(name = "buttonSpacing")
  public void setButtonSpacing(FloatingActionMenu view, int space) {
    view.setButtonSpacing(space);
  }

  @ReactProp(name = "label")
  public void setLabel(FloatingActionMenu view, String label) {
    view.getMenuButton().setLabelText(label);
  }

  @ReactProp(name = "labelsPosition")
  public void setLabelsPosition(FloatingActionMenu view, String position) {
    view.setLabelsPosition(position.equals("left") ? 0 : 1);
  }

  @ReactProp(name = "labelsMargin")
  public void setLabelsMargin(FloatingActionMenu view, int margin) {
    view.setLabelsMargin(margin);
  }

  @ReactProp(name = "labelsPadding", defaultInt = 8)
  public void setLabelsPadding(FloatingActionMenu view, int padding) {
    view.setLabelsPadding(padding);
  }

  @ReactProp(name = "labelsPaddingTop", defaultInt = 4)
  public void setLabelsPaddingTop(FloatingActionMenu view, int padding) {
    view.setLabelsPaddingTop(padding);
  }

  @ReactProp(name = "labelsPaddingRight", defaultInt = 8)
  public void setLabelsPaddingRight(FloatingActionMenu view, int padding) {
    view.setLabelsPaddingRight(padding);
  }

  @ReactProp(name = "labelsPaddingBottom", defaultInt = 4)
  public void setLabelsPaddingBottom(FloatingActionMenu view, int padding) {
    view.setLabelsPaddingBottom(padding);
  }

  @ReactProp(name = "labelsPaddingLeft", defaultInt = 8)
  public void setLabelsPaddingLeft(FloatingActionMenu view, int padding) {
    view.setLabelsPaddingLeft(padding);
  }

  @ReactProp(name = "labelsShowShadow", defaultBoolean = true)
  public void setLabelsShowShadow(FloatingActionMenu view, boolean show) {
    view.setLabelsShowShadow(show);
  }

  @ReactProp(name = "labelsSingleLine")
  public void setLabelsSingleLine(FloatingActionMenu view, boolean singleLine) {
    view.setLabelsSingleLine(singleLine);
  }

  @ReactProp(name = "labelsEllipsize")
  public void setLabelsEllipsize(FloatingActionMenu view, String ellipsize) {
    switch (ellipsize) {
      case "start":
        view.setLabelsEllipsize(1);
        break;
      case "middle":
        view.setLabelsEllipsize(2);
        break;
      case "end":
        view.setLabelsEllipsize(3);
        break;
      case "marquee":
        view.setLabelsEllipsize(4);
        break;
    }
  }

  @ReactProp(name = "labelsMaxLines", defaultInt = 1)
  public void setLabelsMaxLines(FloatingActionMenu view, int maxLines) {
    view.setLabelsMaxLines(maxLines);
  }

  @ReactProp(name = "openDirection")
  public void setLabelsMaxLines(FloatingActionMenu view, String openDirection) {
    view.setOpenDirection(openDirection.equals("down") ? 1 : 0);
  }

  @ReactProp(name = "showShadow", defaultBoolean = true)
  public void setShowShadow(FloatingActionMenu view, boolean showShadow) {
    view.getMenuButton().setShowShadow(showShadow);
  }

  @ReactProp(name = "shadowColor")
  public void setShadowColor(FloatingActionMenu view, String shadowColor) {
    view.getMenuButton().setShadowColor(Color.parseColor(shadowColor));
  }

  @ReactProp(name = "shadowRadius", defaultFloat = 4.0f)
  public void setShadowRadius(FloatingActionMenu view, float radius) {
    view.getMenuButton().setShadowRadius(radius);
  }

  @ReactProp(name = "shadowXOffset", defaultFloat = 1.0f)
  public void setShadowXOffset(FloatingActionMenu view, float offset) {
    view.getMenuButton().setShadowXOffset(offset);
  }

  @ReactProp(name = "shadowYOffset", defaultFloat = 3.0f)
  public void setShadowYOffset(FloatingActionMenu view, float offset) {
    view.getMenuButton().setShadowYOffset(offset);
  }

  @ReactProp(name = "size")
  public void setSize(FloatingActionMenu view, String size) {
    view.getMenuButton().setButtonSize(
        size == "mini"
          ? FloatingActionButton.SIZE_MINI
          : FloatingActionButton.SIZE_NORMAL
    );
  }

  @ReactProp(name = "closeOnTouchOutside", defaultBoolean = true)
  public void setCloseOnTouchOutside(FloatingActionMenu view, boolean close) {
    view.setClosedOnTouchOutside(close);
  }

  @Override
  public Map<String,Integer> getCommandsMap() {
    return MapBuilder.of(
        "hideMenu", COMMAND_HIDE_MENU
    );
  }

  @Nullable
  @Override
  public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
    return MapBuilder.<String, Object>builder()
        .put(
            "topToggle",
            MapBuilder.of(
                "phasedRegistrationNames",
                MapBuilder.of(
                    "bubbled", "onToggle", "capture", "onToggleCapture"
                )
            )
        )
        .build();
  }

  @Override
  public void receiveCommand(FloatingActionMenu view, int commandType, @Nullable ReadableArray args) {
    switch (commandType) {
      case COMMAND_HIDE_MENU:
        boolean animated = args.getBoolean(0);
        this.hideMenuButton(view, animated);
        break;
    }
  }

  public void hideMenuButton(FloatingActionMenu view, boolean animate) {
    view.hideMenuButton(animate);
  }

  private class NFabMenuClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
      ((FloatingActionMenu) v.getParent()).toggle(true);
    }
  }

  private class NFabMenuToggleListener implements FloatingActionMenu.OnMenuToggleListener {

    View view;

    public NFabMenuToggleListener(View v) {
      this.view = v;
    }

    @Override
    public void onMenuToggle(boolean opened) {
      WritableMap event = Arguments.createMap();
      event.putBoolean("opened", opened);
      ReactContext reactContext = (ReactContext) this.view.getContext();
      reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(this.view.getId(), "topToggle", event);
    }
  }
}