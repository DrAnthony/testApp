package com.example.testapp.item;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;

import com.example.testapp.entity.Seat;

public class SeatItem {
    private static final String TAG = "SeatItem";
    private Path path;
    private String seatId;
    private int drawColor;
    private boolean clickable=false;
    public void setDrawColor(int color){
        drawColor=color;
    }
    public void setSeatId(String id){
        seatId=id;
    }
    public String getSeatId(){
        return seatId;
    }
    public SeatItem(Path path,String id){
        this.path=path;
        this.seatId=id;
    }
    public SeatItem(Path path){
        this.path=path;
    }
    public void setClickable(boolean b){
        clickable=b;
    }
    public void drawItem(Canvas canvas, Paint paint,boolean isSelected){
        if(seatId==null){
            paint.clearShadowLayer();
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GRAY);
            canvas.drawPath(path,paint);
        }else if (isSelected){
            //绘制内部颜色
            paint.clearShadowLayer();
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawPath(path,paint);
            Log.i(TAG, "drawItem: seatId "+seatId);
//            //绘制边界
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setColor(0xFFD0E8F4);
//            canvas.drawPath(path,paint);
        }else {
            paint.setStrokeWidth(2);
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            //paint.setShadowLayer(8,0,0,0xffffff);
            canvas.drawPath(path,paint);
//            //绘制边界
//            paint.clearShadowLayer();
//            paint.setColor(drawColor);
//            paint.setStyle(Paint.Style.FILL);
//            paint.setStrokeWidth(2);
//            canvas.drawPath(path,paint);
        }
    }
    public boolean isTouch(float x,float y){//注意注意这块是来判断点击位置的 主要知识点Region
        RectF rectF = new RectF();
        path.computeBounds(rectF,true);
        Region region = new Region();
        region.setPath(path,new Region((int)rectF.left,(int)rectF.top,(int)rectF.right,(int) rectF.bottom));
        return  region.contains((int)x,(int)y)&&seatId!=null;
    }

}
