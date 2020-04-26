package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.testapp.ui.account.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ImageView imageView=findViewById(R.id.welcome_gif);
        Glide.with(this)
                .load(R.drawable.welcome)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new GlideDrawableImageViewTarget(imageView,1));
        MyTimer timer=new MyTimer(MainActivity.this,LoginActivity.class,2000);
        timer.start();
    }
    class MyTimer extends Thread{
        private AppCompatActivity preActivity;
        private Class nextActivity;
        private int time;
        public MyTimer(AppCompatActivity pre,Class next,int mills){
            preActivity=pre;
            nextActivity=next;
            time=mills;
        }
        @Override
        public void run(){
            try {
                Thread.sleep(time);
                Intent intent=new Intent(preActivity,nextActivity);
                startActivity(intent);
                preActivity.finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
