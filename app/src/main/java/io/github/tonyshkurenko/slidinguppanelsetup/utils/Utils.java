package io.github.tonyshkurenko.slidinguppanelsetup.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by: Anton Shkurenko (tonyshkurenko)
 * Project: SlidingUpPanelSetup
 * Date: 7/14/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public final class Utils {

  private Utils() {
    throw new UnsupportedOperationException("No instances");
  }

  public static float dpToPx(Context ctx, float dp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
        ctx.getResources().getDisplayMetrics());
  }
}
