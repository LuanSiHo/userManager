package com.hosiluan.usermanager;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Ho Si Luan on 10/29/2017.
 */

public class RecyclerViewItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;
    private Paint p = new Paint();


    public RecyclerViewItemTouchHelper(int dragDirs, int swipeDirs,
                                       RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        final int fromPos = viewHolder.getAdapterPosition();
        final int toPos = target.getAdapterPosition();
        Log.d("Luan", "from " + fromPos + " to " + toPos);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

//            Log.d("Luan", "On child raw " + dX);
            if (dX > 0) {
                View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;
                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);

                foregroundView.setX(foregroundView.getLeft());
                viewHolder.itemView.setX((float) viewHolder.itemView.getLeft());
//                if (foregroundView.getX() >= foregroundView.getLeft()){
//                    Log.d("Luan", "oke boy");
//                }

            } else {

                View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;
                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);

                if (foregroundView.getX() < foregroundView.getLeft() - 300) {
                    foregroundView.setX(foregroundView.getLeft() - 301);
                }
                viewHolder.itemView.setX((float) viewHolder.itemView.getLeft());


            }
        }
//
//        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
//            View itemView = viewHolder.itemView;
//            float height = (float) itemView.getBottom() - (float) itemView.getTop();
//            float width = height / 3;
//
//            p.setColor(Color.parseColor("#D32F2F"));
//            RectF background = new RectF((float) itemView.getRight() + dX,
//                    (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
//            c.drawRect(background,p);
//            c.drawText("Delete", (float) itemView.getRight() - 170,
//                            (float) (itemView.getBottom() - 80), p);
//        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);

        if (viewHolder != null) {
            View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
