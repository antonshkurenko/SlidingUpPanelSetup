package io.github.tonyshkurenko.slidinguppanelsetup;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class BottomSheetDialogActivity extends AppCompatActivity {

  ImageView mImageView;

  private final AdapterView.OnItemClickListener mOnItemClickListener =
      new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          Toast.makeText(BottomSheetDialogActivity.this,
              "Item clicked: " + parent.getAdapter().getItem(position).toString(),
              Toast.LENGTH_SHORT).show();
        }
      };

  private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

      Toast.makeText(BottomSheetDialogActivity.this, "Clicked: " + v.getClass().getSimpleName(),
          Toast.LENGTH_SHORT).show();

      final View contentView =
          View.inflate(BottomSheetDialogActivity.this, R.layout.fragment_bottom_sheet, null);

      final ListView listView = ((ListView) contentView.findViewById(R.id.list_view));

      listView.setAdapter(
          new ArrayAdapter<>(BottomSheetDialogActivity.this, android.R.layout.simple_list_item_1,
              android.R.id.text1, getResources().getStringArray(R.array.dummy_strings)));

      listView.setOnItemClickListener(mOnItemClickListener);

      final BottomSheetDialog dialog = new BottomSheetDialog(BottomSheetDialogActivity.this);
      dialog.setContentView(contentView);
      dialog.show();

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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_sheet_dialog);

    mImageView = ((ImageView) findViewById(R.id.image_view));
    mImageView.setOnClickListener(mOnClickListener);
  }
}
