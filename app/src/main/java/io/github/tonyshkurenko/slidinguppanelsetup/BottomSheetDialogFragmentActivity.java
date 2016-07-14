package io.github.tonyshkurenko.slidinguppanelsetup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class BottomSheetDialogFragmentActivity extends AppCompatActivity {

  ImageView mImageView;

  private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

      Toast.makeText(BottomSheetDialogFragmentActivity.this,
          "Clicked: " + v.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();

      mBottomSheetFragment = new BottomDialogFragment();
      mBottomSheetFragment.show(getSupportFragmentManager(),
          BottomDialogFragment.class.getSimpleName());

      /*if(mBottomSheetFragment == null || mBottomSheetFragment.mBehavior == null) {
        return;
      }

      switch (mBottomSheetFragment.mBehavior.getState()) {
        case BottomSheetBehavior.STATE_HIDDEN:
          mBottomSheetFragment.mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
          break;
        case BottomSheetBehavior.STATE_COLLAPSED:
          mBottomSheetFragment.mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
          break;
        default:
          mBottomSheetFragment.mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
          break;
      }*/
    }
  };

  private BottomDialogFragment mBottomSheetFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_sheet_dialog_fragment);

    mImageView = ((ImageView) findViewById(R.id.image_view));
    mImageView.setOnClickListener(mOnClickListener);
  }

  public static class BottomDialogFragment extends BottomSheetDialogFragment {

    ListView mListView;

    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback =
        new BottomSheetBehavior.BottomSheetCallback() {

          @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
              dismiss();
            }
          }

          @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
          }
        };

    private final AdapterView.OnItemClickListener mOnItemClickListener =
        new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(),
                "Item clicked: " + parent.getAdapter().getItem(position).toString(),
                Toast.LENGTH_SHORT).show();
          }
        };

    private BottomSheetBehavior mBehavior;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

      final Context context = getContext();

      final View contentView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

      mListView = ((ListView) contentView.findViewById(R.id.list_view));

      mListView.setAdapter(
          new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1,
              getResources().getStringArray(R.array.dummy_strings)));

      mListView.setOnItemClickListener(mOnItemClickListener);

      if ((container) != null) {

        final CoordinatorLayout.LayoutParams params =
            (CoordinatorLayout.LayoutParams) (container).getLayoutParams();

        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
          mBehavior = (BottomSheetBehavior) behavior;
          mBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
      }

      return contentView;
    }

    /*@Override public void setupDialog(Dialog dialog, int style) {
      super.setupDialog(dialog, style);

      final Context context = getContext();

      final View contentView = View.inflate(context, R.layout.fragment_bottom_sheet, null);

      mListView = ((ListView) contentView.findViewById(R.id.list_view));

      mListView.setAdapter(
          new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1,
              getResources().getStringArray(R.array.dummy_strings)));

      mListView.setOnItemClickListener(mOnItemClickListener);

      dialog.setContentView(contentView);

      final CoordinatorLayout.LayoutParams params =
          (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
      CoordinatorLayout.Behavior behavior = params.getBehavior();

      if (behavior != null && behavior instanceof BottomSheetBehavior) {
        mBehavior = (BottomSheetBehavior) behavior;
        mBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
      }
    }*/
  }
}
