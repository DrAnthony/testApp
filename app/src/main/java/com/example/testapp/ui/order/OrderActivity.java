package com.example.testapp.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.view.FixItemListView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private static final String TAG = "OrderActivity";
    private TextView tv;
    private PopupWindow popupWindow;
    private FixItemListView lv;
    private ArrayAdapter<String> adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initComponent();
    }
    private void initComponent(){
        Drawable icon=getResources().getDrawable(R.drawable.right);
        icon.setBounds(0,0,40,40);
        TextView tmp=findViewById(R.id.tv_lib);
        tmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
                if(popupWindow!=null&&!popupWindow.isShowing()){
                    popupWindow.showAsDropDown((TextView)findViewById(R.id.tv_lib),0,10);
                }
            }
        });
        tmp.setCompoundDrawables(null,null,icon,null);
        tmp=findViewById(R.id.tv_floor);
        tmp.setCompoundDrawables(null,null,icon,null);
        tmp=findViewById(R.id.tv_seat);
        tmp.setCompoundDrawables(null,null,icon,null);
        tmp=findViewById(R.id.tv_start);
        tmp.setCompoundDrawables(null,null,icon,null);
        tmp=findViewById(R.id.tv_end);
        tmp.setCompoundDrawables(null,null,icon,null);
    }
    private void initPopupWindow(){
        tv=(TextView)findViewById(R.id.tv_lib);
        lv =new FixItemListView(this);
        lv.setFixItemCount(5);
        setData();
        adapter=new ArrayAdapter<>(this,R.layout.popup_list_item,data);
        lv.setAdapter(adapter);
        Log.i(TAG,String.valueOf(lv.getHeight()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=data.get(position);
                tv.setText(str);
                popupWindow.dismiss();
            }
        });
        popupWindow=new PopupWindow(lv,tv.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT,true);
        Drawable drawable=getResources().getDrawable(R.drawable.list_border);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
    }
    private void setData(){
        data=new ArrayList<>();
        for(int i=0;i<10;i++){
            data.add("数据"+(i+1));
        }
    }
}
