package com.example.testapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class FixItemListView extends ListView {
    private int mMaxItemCount;
    private static final String TAG = "FixItemListView";
    public FixItemListView(Context context) {
        super(context);
    }

    public FixItemListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixItemListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        resetListViewHeight();
    }

    public void setFixItemCount(int count){
        this.mMaxItemCount = count;
        resetListViewHeight();
    }

    private void resetListViewHeight(){
        ListAdapter  listAdapter = getAdapter();
        if(listAdapter == null || mMaxItemCount == 0 || listAdapter.getCount() == 0){
            return;
        }
        View itemView = listAdapter.getView(0, null, this);
        itemView.measure(0,0);
        int itemHeight = itemView.getMeasuredHeight();
        Log.i(TAG,"最大数量"+String.valueOf(mMaxItemCount));
        Log.i(TAG,"高度"+String.valueOf(itemHeight));
        int itemCount = listAdapter.getCount();
        Log.i(TAG,"数量"+String.valueOf(itemCount));
        //ConstraintLayout.LayoutParams layoutParams=null;
        LinearLayout.LayoutParams layoutParams = null;
        if(itemCount <= mMaxItemCount) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.WRAP_CONTENT);
        }else{
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,itemHeight*mMaxItemCount);
        }
        Log.i(TAG,String.valueOf(layoutParams.height));
        setLayoutParams(layoutParams);
    }
}
