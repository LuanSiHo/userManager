package com.hosiluan.usermanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hosiluan.usermanager.fragment.UserInfoToolbarFragment;

import java.util.ArrayList;

import static android.support.v7.widget.helper.ItemTouchHelper.Callback.getDefaultUIUtil;

public class ListUserActivity extends AppCompatActivity
    implements RecyclerViewAdapter.AdapterListener {

    public static final String BUNDLE_TO_USER_INFO = "bundle to user info";
    public static final String OBJECT_TO_USER_INFO = "object to user info";
    private RecyclerView mUserListRecyclerView;
    private ImageButton backImageButton, addUserImageButton;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<User> mUserList;
    private DBManager mDBManager;
    private LinearLayout linearLayout;
    private TextView deleteTextView;
    private Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        setView();
        setEvent();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getUserList();
        setSwipeEvent();
    }

    private void setView() {

        backImageButton = (ImageButton) findViewById(R.id.img_btn_list_user_back);
        addUserImageButton = (ImageButton) findViewById(R.id.img_btn_list_user_add);

//        deleteTextView = (TextView) findViewById(R.id.tv_delete);

        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_user_list);

        mDBManager = new DBManager(getApplicationContext());
        mUserList = new ArrayList<>();

        mUserListRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_user);
        mAdapter = new RecyclerViewAdapter(ListUserActivity.this, mUserList,this);
        mUserListRecyclerView.setAdapter(mAdapter);

        mUserListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void setEvent() {
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addUserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListUserActivity.this, NewUserActivity.class));
            }
        });
    }

    private void getUserList() {
        mUserList.clear();
        ArrayList<User> userList = mDBManager.getUserList();
        for (int i = 0; i < userList.size(); i++) {
            mUserList.add(userList.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void setSwipeEvent() {

//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerViewItemTouchHelper(0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
//
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mUserListRecyclerView);


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                Toast.makeText(ListUserActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;

                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    p.setColor(Color.parseColor("#D32F2F"));
                    RectF background = new RectF((float) itemView.getRight() + dX,
                            (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                    c.drawRect(background, p);
                    p.setColor(Color.WHITE);
                    p.setTextSize(50);

                    c.drawText("Delete", (float) itemView.getRight() - 170,
                            (float) (itemView.getBottom() - 80), p);

                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(ListUserActivity.this, "on Swipe", Toast.LENGTH_SHORT).show();

                // get removed username to display it in snack bar
                String name = mUserList.get(viewHolder.getAdapterPosition()).getmLastName()
                        + " " + mUserList.get(viewHolder.getAdapterPosition()).getmFirstName();

                // backup of removed user for undo purpose
                final User user = mUserList.get(viewHolder.getAdapterPosition());
                final int deletedIndex = viewHolder.getAdapterPosition();


                // remove the item from recycler view
                mUserList.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyDataSetChanged();

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar.make(linearLayout, name + " removed from list", Snackbar.LENGTH_LONG);

                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mUserList.add(deletedIndex, user);
                        mAdapter.notifyDataSetChanged();
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mUserListRecyclerView);
    }

    @Override
    public void onItemClickEvent(User user) {
        Intent intent = new Intent(ListUserActivity.this,UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(OBJECT_TO_USER_INFO,user);
        intent.putExtra(BUNDLE_TO_USER_INFO,bundle);
        startActivity(intent);
    }



//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
//        if (direction == ItemTouchHelper.LEFT) {
//            Log.d("Luan", "left ");
////            View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;
//////            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
////            foregroundView.setX(foregroundView.getLeft() - 300);
//
//
//
//        } else if (direction == ItemTouchHelper.RIGHT) {
//            Log.d("Luan", "right ");
//        }
//        // get removed username to display it in snack bar
////        String name = mUserList.get(position).getmLastName() + " "
////                + mUserList.get(position).getmFirstName();
////
////        // backup of removed user for undo purpose
////        final User user = mUserList.get(position);
////        final int deletedIndex = position;
////
////        // remove the item from recycler view
////        mUserList.remove(position);
////        mAdapter.notifyDataSetChanged();
////
////        // showing snack bar with Undo option
////        Snackbar snackbar = Snackbar.make(linearLayout, name + " removed from list",
////                Snackbar.LENGTH_LONG);
////
////        snackbar.setAction("UNDO", new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                mUserList.add(deletedIndex, user);
////                mAdapter.notifyDataSetChanged();
////            }
////        });
////
////        snackbar.setActionTextColor(Color.YELLOW);
////        snackbar.show();
//    }
}
