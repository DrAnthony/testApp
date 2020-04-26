package com.example.testapp.ui.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.example.testapp.R;
import com.example.testapp.entity.ResponseEntity;
import com.example.testapp.entity.School;
import com.example.testapp.utils.http.HttpClient;
import com.example.testapp.view.FixItemListView;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private TextView tv_school;
    private EditText et_tel;
    private EditText et_user;
    private EditText et_stuid;
    private EditText et_pwd1;
    private EditText et_pwd2;
    private EditText et_code;
    private Button codeBtn;
    private Button regBtn;
    private Button toLoginBtn;
    private PopupWindow popupWindow;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private List<String> data;
    private int status=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setData();
        initComponent();
    }
    private void initComponent(){
        tv_school=findViewById(R.id.register_tv_school);
        et_code=findViewById(R.id.register_tv_code);
        et_pwd1=findViewById(R.id.register_tv_pwd);
        et_pwd2=findViewById(R.id.register_tv_ensure);
        et_stuid=findViewById(R.id.register_tv_stuid);
        et_tel=findViewById(R.id.register_tv_tel);
        et_user=findViewById(R.id.register_tv_user);
        codeBtn=findViewById(R.id.reg_code_btn);
        regBtn=findViewById(R.id.reg_register);
        toLoginBtn=findViewById(R.id.reg_tologin);
        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel=et_tel.getText().toString();
                if(tel==null||tel.length()!=11){
                    Toast toast=Toast.makeText(RegisterActivity.this,"手机格式错误",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else{
                    sendCode(tel);
                }

            }
        });
    }
    private void sendCode(String tel){
        Call<ResponseEntity> call=HttpClient.Builder.getHttpClient().getCode(tel);
        call.enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                ResponseEntity result=response.body();
                if(result.getCode()==0){
                    dealCodeResult(false,result.getMsg());
                }else{
                    dealCodeResult(true,result.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                dealCodeResult(false,"未知错误");
            }
        });
    }
    class CodeTimer extends Thread{
        private int count=60;
        private Drawable drawableBlue;
        private Drawable drawableGrey;
        public CodeTimer(){
            drawableBlue=getResources().getDrawable(R.drawable.login_bt_border_blue);
            drawableGrey=getResources().getDrawable(R.drawable.login_bt_border_grey);
        }
        @Override
        public void run(){
            codeBtn.setBackground(drawableGrey);
            codeBtn.setClickable(false);
            while(count>0&&status==0){
                codeBtn.setText(count+"s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
            }
            codeBtn.setBackground(drawableBlue);
            codeBtn.setClickable(true);
            codeBtn.setText("发送验证码");
        }
    }
    private void dealCodeResult(boolean mark,String content){
        Toast toast=Toast.makeText(RegisterActivity.this,content,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        if(mark){
            CodeTimer timer=new CodeTimer();
            timer.start();
        }
    }
    private void initSchoolTextView(){
        tv_school=findViewById(R.id.register_tv_school);
        tv_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSchoolList();
                if(popupWindow!=null&&!popupWindow.isShowing()){
                    popupWindow.showAsDropDown((TextView)findViewById(R.id.register_tv_school),0,10);
                }
            }
        });

    }
    private void initSchoolList(){
        lv=new ListView(this);
        adapter=new ArrayAdapter<String>(this,R.layout.popup_list_item,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=data.get(position);
                tv_school.setText(str);
                popupWindow.dismiss();
            }
        });
        popupWindow=new PopupWindow(lv,tv_school.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT,true);
        Drawable drawable=getResources().getDrawable(R.drawable.login_tv_border_white);
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
        HttpClient httpClient=HttpClient.Builder.getHttpClient();
        Call<ResponseEntity<List<School>>> call=httpClient.getSchool(0);
        call.enqueue(new Callback<ResponseEntity<List<School>>>() {
            @Override
            public void onResponse(Call<ResponseEntity<List<School>>> call, Response<ResponseEntity<List<School>>> response) {
                ResponseEntity<List<School>> result=response.body();
                if(result.getCode()==0){
                    Toast toast=Toast.makeText(RegisterActivity.this,result.getMsg(),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    data=new ArrayList<>();
                    data.add("未查询到数据");
                }else{
                    data=new ArrayList<>();
                   for(School school:result.getData()){
                       //Log.i(TAG, school.toString());
                       data.add(school.getSchoolName());
                   }
                }
                initSchoolTextView();;
            }

            @Override
            public void onFailure(Call<ResponseEntity<List<School>>> call, Throwable t) {
                Toast toast=Toast.makeText(RegisterActivity.this,"未知错误",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }
}
