package io.github.tonyshkurenko.slidinguppanelsetup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import io.github.tonyshkurenko.slidinguppanelsetup.behaviors.BottomSheetBehaviorGoogleMapsLike;
import io.github.tonyshkurenko.slidinguppanelsetup.behaviors.SecondAppBarLayoutBehavior;

public class ThreeStatesBehaviorActivity extends AppCompatActivity {

  private static final String TAG = ThreeStatesBehaviorActivity.class.getSimpleName();

  TextView mBottomSheetTextView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_three_states_behavior);

    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(" ");
    }

    /**
     * If we want to listen for states callback
     */
    final CoordinatorLayout coordinatorLayout =
        (CoordinatorLayout) findViewById(R.id.coordinator_layout);
    final View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
    final BottomSheetBehaviorGoogleMapsLike behavior =
        BottomSheetBehaviorGoogleMapsLike.from(bottomSheet);
    behavior.addBottomSheetCallback(new BottomSheetBehaviorGoogleMapsLike.BottomSheetCallback() {
      @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
        switch (newState) {
          case BottomSheetBehaviorGoogleMapsLike.STATE_COLLAPSED:
            Log.d(TAG, "STATE_COLLAPSED");
            break;
          case BottomSheetBehaviorGoogleMapsLike.STATE_DRAGGING:
            Log.d(TAG, "STATE_DRAGGING");
            break;
          case BottomSheetBehaviorGoogleMapsLike.STATE_EXPANDED:
            Log.d(TAG, "STATE_EXPANDED");
            break;
          case BottomSheetBehaviorGoogleMapsLike.STATE_ANCHOR_POINT:
            Log.d(TAG, "STATE_ANCHOR_POINT");
            break;
          case BottomSheetBehaviorGoogleMapsLike.STATE_HIDDEN:
            Log.d(TAG, "STATE_HIDDEN");
            break;
          default:
            Log.d(TAG, "STATE_SETTLING");
            break;
        }
      }

      @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
      }
    });

    mBottomSheetTextView = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_title);

    {
      final AppBarLayout appbarLayout = ((AppBarLayout) findViewById(R.id.expanded_appbar_layout));

      final CoordinatorLayout.LayoutParams params =
          (CoordinatorLayout.LayoutParams) appbarLayout.getLayoutParams();

      final CoordinatorLayout.Behavior secondAppBarLayoutBehavior = params.getBehavior();

      if (secondAppBarLayoutBehavior instanceof SecondAppBarLayoutBehavior) {
        ((SecondAppBarLayoutBehavior) secondAppBarLayoutBehavior).setTitleView(
            mBottomSheetTextView);
      }
    }
  }
}
