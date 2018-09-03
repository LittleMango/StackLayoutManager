package com.littlemango.stacklayoutmanagermaster;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.littlemango.stacklayoutmanager.StackLayoutManager;
import com.littlemango.stacklayoutmanager.StackLayoutManager.ScrollOrientation;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;

    private static final int mStackCount = 30;

    private int mRandomPosition;

    private StackLayoutManager mStackLayoutManager;

    private String[] selectItems;

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
        mRecyclerView = findViewById(R.id.recycleView);

        mStackLayoutManager = new StackLayoutManager();
        mRecyclerView.setLayoutManager(mStackLayoutManager);
        mRecyclerView.setAdapter(new StackLayoutAdapter());

        selectItems = getResources().getStringArray(R.array.items);
        resetRandom();

        findViewById(R.id.floatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialDialog.Builder(MainActivity.this)
                        .items(selectItems)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        mRecyclerView.smoothScrollToPosition(mRandomPosition);
                                        resetRandom();
                                        break;
                                    case 1:
                                        mRecyclerView.scrollToPosition(mRandomPosition);
                                        resetRandom();
                                        break;
                                    case 2:
                                        mStackLayoutManager = new StackLayoutManager(ScrollOrientation.LEFT_TO_RIGHT);
                                        mRecyclerView.setLayoutManager(mStackLayoutManager);
                                        getSupportActionBar().setTitle("Picture 0");
                                        break;
                                    case 3:
                                        mStackLayoutManager = new StackLayoutManager(ScrollOrientation.RIGHT_TO_LEFT);
                                        mRecyclerView.setLayoutManager(mStackLayoutManager);
                                        getSupportActionBar().setTitle("Picture 0");
                                        break;
                                    case 4:
                                        mStackLayoutManager = new StackLayoutManager(ScrollOrientation.TOP_TO_BOTTOM);
                                        mRecyclerView.setLayoutManager(mStackLayoutManager);
                                        getSupportActionBar().setTitle("Picture 0");
                                        break;
                                    case 5:
                                        mStackLayoutManager = new StackLayoutManager(ScrollOrientation.BOTTOM_TO_TOP);
                                        mRecyclerView.setLayoutManager(mStackLayoutManager);
                                        getSupportActionBar().setTitle("Picture 0");
                                        break;
                                    case 6:
                                        mStackLayoutManager.setPagerMode(!mStackLayoutManager.getPagerMode());
                                        break;
                                    case 7:
                                        mStackLayoutManager.setItemOffset(mStackLayoutManager.getItemOffset() + 10);
                                        mStackLayoutManager.requestLayout();
                                        break;
                                    case 8:
                                        mStackLayoutManager.setItemOffset(mStackLayoutManager.getItemOffset() - 10);
                                        mStackLayoutManager.requestLayout();
                                        break;
                                    case 9:
                                        mStackLayoutManager.setVisibleItemCount(mStackLayoutManager.getVisibleItemCount() + 1);
                                        mStackLayoutManager.requestLayout();
                                        break;
                                    case 10:
                                        mStackLayoutManager.setVisibleItemCount(mStackLayoutManager.getVisibleItemCount() - 1);
                                        mStackLayoutManager.requestLayout();
                                        break;
                                    case 11:
                                        mStackLayoutManager.setPagerFlingVelocity(mStackLayoutManager.getPagerFlingVelocity() + 5000);
                                        break;
                                    case 12:
                                        mStackLayoutManager.setPagerFlingVelocity(mStackLayoutManager.getPagerFlingVelocity() - 5000);
                                        break;
                                    case 13:
                                        mStackLayoutManager.setAnimation(new FadeInFadeOutAnimation(mStackLayoutManager.getScrollOrientation(),
                                                mStackLayoutManager.getVisibleItemCount()));
                                        mStackLayoutManager.requestLayout();
                                        break;
                                }

                                mStackLayoutManager.setItemChangedListener(new StackLayoutManager.ItemChangedListener() {
                                    @Override
                                    public void onItemChanged(int position) {
                                        getSupportActionBar().setTitle("Picture " + position);
                                    }
                                });
                            }
                        })
                        .show();
            }
        });

        mStackLayoutManager.setItemChangedListener(new StackLayoutManager.ItemChangedListener() {
            @Override
            public void onItemChanged(int position) {
                getSupportActionBar().setTitle("Picture " + position);
            }
        });
    }

    class StackLayoutAdapter extends RecyclerView.Adapter<StackLayoutAdapter.StackHolder> {

        @NonNull
        @Override
        public StackHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_card, viewGroup, false);
            return new StackHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final StackHolder stackHolder, int position) {
            int res;
            switch (position % 6) {
                case 0:
                    res = R.drawable.image1;
                    break;
                case 1:
                    res = R.drawable.image2;
                    break;
                case 2:
                    res = R.drawable.image3;
                    break;
                case 3:
                    res = R.drawable.image4;
                    break;
                case 4:
                    res = R.drawable.image5;
                    break;
                default:
                    res = R.drawable.image6;
                    break;
            }
            stackHolder.imageView.setImageResource(res);
            stackHolder.textView.setText("" + position);
            stackHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (stackHolder.getAdapterPosition() == mStackLayoutManager.getFirstVisibleItemPosition()) {
                        mToast.setText("position: " + stackHolder.getAdapterPosition() + " is click!");
                        mToast.show();
                    } else {
                        mRecyclerView.smoothScrollToPosition(stackHolder.getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mStackCount;
        }

        class StackHolder extends RecyclerView.ViewHolder {
            View itemView;
            ImageView imageView;
            TextView textView;

            StackHolder(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);
            }
        }
    }

    private void resetRandom() {
        mRandomPosition = Math.abs(new Random().nextInt() % mStackCount);
        selectItems[0] = getResources().getString(R.string.smooth_scroll) + mRandomPosition;
        selectItems[1] = getResources().getString(R.string.scroll) + mRandomPosition;
    }
}
