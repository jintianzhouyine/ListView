package com.example.mycomputer.listview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mycomputer on 2017/6/18.
 */

public class SideBar extends View{
    /**
     * Data
     */
    public static String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    public int choose = -1;//选中的字母所在的位子
    private Paint paint = new Paint();
    private TextView mTextDialog;
    /**
     * 触摸事件
     */
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;


    /**
     * 为SideBar 设置显示的字母的TextView
     * @param mTextDialog
     */
    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    /**
     * 构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public SideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public SideBar(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取焦点改变背景颜色
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height/b.length;//获取每个字母的高度

        for(int i = 0;i<b.length;i++){
            paint.setColor(Color.rgb(33,65,98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);//抗锯齿
            paint.setTextSize((float)(singleHeight*0.9));
            //选中的状态
            if(i == choose){
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            //x坐标等于屏幕中间 - 字符宽度/2
            float xPos = width/2 - paint.measureText(b[i])/2;
            float yPos = singleHeight * (i + 1);
            canvas.drawText(b[i],xPos,yPos,paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int)(y/getHeight()*b.length);//获取点击b中的个数

        switch (action){
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;
                invalidate();
                if(mTextDialog != null){
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                //setBackgroundResource(R.drawable.sidebar_background);
                if(oldChoose != c){
                    if(c >=0 && c<b.length){
                        if(listener != null){
                            listener.onTouchingLetterChanged(b[c]);
                        }
                        if(mTextDialog != null){
                            mTextDialog.setAlpha((float)0.5);
                            mTextDialog.setText(b[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 向外公开的方法
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener){
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }


    /**
     * 接口
     */
    public interface OnTouchingLetterChangedListener{
        public void onTouchingLetterChanged(String s);
    }
}
