package com.nfab;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.uimanager.LayoutShadowNode;


public class ButtonShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
  private int mWidth;
  private int mHeight;
  private boolean mMeasured;

  public ButtonShadowNode() {
    setMeasureFunction(this);
  }

  @Override
  public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
    if (!mMeasured) {
      Button nodeView = new Button(getThemedContext());
      final int spec = View.MeasureSpec.makeMeasureSpec(
          ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT);

      nodeView.measure(spec, spec);

      mWidth = nodeView.getMeasuredWidth();
      mHeight = nodeView.getMeasuredHeight();
      mMeasured = true;
    }

    return YogaMeasureOutput.make(mWidth, mHeight);
  }
}
