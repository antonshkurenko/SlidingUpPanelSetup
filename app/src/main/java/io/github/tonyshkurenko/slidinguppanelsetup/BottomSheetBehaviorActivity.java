package io.github.tonyshkurenko.slidinguppanelsetup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import io.github.tonyshkurenko.slidinguppanelsetup.utils.Utils;

public class BottomSheetBehaviorActivity extends AppCompatActivity {

  private static final String TAG = SlidingUpPanelLibraryActivity.class.getSimpleName();

  CoordinatorLayout mCoordinatorLayout;
  View mBottomSheet;
  ImageView mImageView;
  ListView mListView;
  FrameLayout mContainer;

  FloatingActionButton mFab;

  private BottomSheetBehavior mBottomSheetBehavior;

  private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

      Toast.makeText(BottomSheetBehaviorActivity.this, "Clicked: " + v.getClass().getSimpleName(),
          Toast.LENGTH_SHORT).show();

      switch (mBottomSheetBehavior.getState()) {
        case BottomSheetBehavior.STATE_HIDDEN:
          mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
          break;
        case BottomSheetBehavior.STATE_COLLAPSED:
          mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
          break;
        default:
          mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
          break;
      }
    }
  };

  private final AdapterView.OnItemClickListener mOnItemClickListener =
      new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          Toast.makeText(BottomSheetBehaviorActivity.this,
              "Item clicked: " + parent.getAdapter().getItem(position).toString(),
              Toast.LENGTH_SHORT).show();
        }
      };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_sheet_behavior);

    mCoordinatorLayout = ((CoordinatorLayout) findViewById(R.id.main_content));
    mBottomSheet = findViewById(R.id.bottom_sheet);
    mContainer = ((FrameLayout) findViewById(R.id.container));
    mImageView = ((ImageView) findViewById(R.id.image_view));
    mListView = ((ListView) findViewById(R.id.list_view));

    mFab = ((FloatingActionButton) findViewById(R.id.fab));

    mListView.setAdapter(
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,
            getResources().getStringArray(R.array.dummy_strings)));

    mListView.setOnItemClickListener(mOnItemClickListener);
    mImageView.setOnClickListener(mOnClickListener);

    mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);

    //mBottomSheetBehavior.setPeekHeight((int) Utils.dpToPx(this, 52));
    mBottomSheetBehavior.setHideable(true);

    //mFab.hide();

    mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
        /*if (newState == BottomSheetBehavior.STATE_EXPANDED) {
          mFab.hide();
        } else {
          mFab.show();
        }*/
      }

      @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {

      }
    });
  }

  @Override public void onBackPressed() {

    if (mBottomSheetBehavior != null && (mBottomSheetBehavior.getState()
        == BottomSheetBehavior.STATE_EXPANDED)) {
      mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    } else {
      super.onBackPressed();
    }
  }
}
