package com.example.testapp.ui.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.entity.ResponseEntity;
import com.example.testapp.entity.Student;
import com.example.testapp.ui.order.OrderActivity;
import com.example.testapp.R;
import com.example.testapp.utils.GlobalUser;
import com.example.testapp.utils.http.HttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText userText;
    private EditText pwdText;
    private Button eyeBtn;
    private Button loginBtn;
    private Button regBtn;
    private TextView forgetTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"This is the second Activity");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initComponent();
    }
    private void initComponent(){
        userText=findViewById(R.id.login_tv_user);
        pwdText=findViewById(R.id.login_tv_pwd);
        eyeBtn=findViewById(R.id.login_eye);
        loginBtn=findViewById(R.id.login_login);
        regBtn=findViewById(R.id.login_register);
        forgetTv=findViewById(R.id.login_forget);
        eyeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwdText.getTransformationMethod() instanceof HideReturnsTransformationMethod){
                    pwdText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Drawable drawable=getResources().getDrawable(R.drawable.ic_close);
                    eyeBtn.setBackground(drawable);
                }else{
                    pwdText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Drawable drawable=getResources().getDrawable(R.drawable.ic_open);
                    eyeBtn.setBackground(drawable);
                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=userText.getText().toString();
                String pwd=pwdText.getText().toString();
                Student student=new Student();
                if(account.length()==11){
                    student.setStuTel(account);
                }else{
                    student.setStuId(account);
                }
                student.setStuPwd(pwd);
                Call call=HttpClient.Builder.getHttpClient().login(student);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        ResponseEntity<Student> result=(ResponseEntity<Student>)response.body();
                        if(result.getCode()==0){
                            Toast toast= Toast.makeText(LoginActivity.this,result.getMsg(),Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }else{
                            Log.i(TAG,result.toString());
                            GlobalUser.setGlobalStudent(result.getData());
                            Toast toast= Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast toast= Toast.makeText(LoginActivity.this,"未知错误",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                        t.printStackTrace();
                    }
                });
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
