package io.github.tonyshkurenko.slidinguppanelsetup;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.ANCHORED;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.COLLAPSED;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  SlidingUpPanelLayout mPanelLayout;
  ImageView mImageView;

  ListView mListView;

  FrameLayout mContainer;

  private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

      Toast.makeText(MainActivity.this, "Clicked: " + v.getClass().getSimpleName(),
          Toast.LENGTH_SHORT).show();

      switch (mPanelLayout.getPanelState()) {
        case HIDDEN:
          mPanelLayout.setPanelState(COLLAPSED); // ANCHORED
          break;
        case COLLAPSED:
          mPanelLayout.setAnchorPoint(0.75f); // show 75%
          mPanelLayout.setPanelState(ANCHORED);
          break;
        default:
          mPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
          break;
      }
    }
  };

  private final AdapterView.OnItemClickListener mOnItemClickListener =
      new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          Toast.makeText(MainActivity.this,
              "Item clicked: " + parent.getAdapter().getItem(position).toString(),
              Toast.LENGTH_SHORT).show();
        }
      };

  //           mPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

  @SuppressWarnings("ConstantConditions") @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mPanelLayout = ((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
    mContainer = ((FrameLayout) findViewById(R.id.container));
    mImageView = ((ImageView) findViewById(R.id.image_view));
    mListView = ((ListView) findViewById(R.id.list_view));

    mListView.setAdapter(
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,
            getResources().getStringArray(R.array.dummy_strings)));

    mListView.setOnItemClickListener(mOnItemClickListener);
    mImageView.setOnClickListener(mOnClickListener);

    mPanelLayout.setFadeOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Fade click!", Toast.LENGTH_SHORT).show();
        mPanelLayout.setPanelState(COLLAPSED);
      }
    });
  }

  @Override public void onBackPressed() {
    if (mPanelLayout != null && (mPanelLayout.getPanelState() == EXPANDED
        || mPanelLayout.getPanelState() == ANCHORED)) {
      mPanelLayout.setPanelState(COLLAPSED);
    } else {
      super.onBackPressed();
    }
  }
}
