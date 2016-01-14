package com.lhdz.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 李南 on 2016/1/13  9:53
 * Email:fengyunzhinan@163.com
 */
public class CustomView extends View {

    private Context mContext;

    private int MODE_TRIANGLE;


    /**
     * 自定义view的总数
     */
    private int mWaveCount;

    /**
     * 自定义wave的宽度与view的宽度
     */
    private float mWaveWidth;
    private int mViewWidth;
    private int mViewHeight;
    private float mWaveHeight;

    /**
     * 自定义的view类型
     */
    private int ViewMode;

    /**
     * 自定义view姿色
     */
    private int mColor;


    /**
     * 矩形函数
     */
    private float mRectWidth;
    private float mRectHeight;

    private float mRadius;


    public CustomView(Context context) {
        super(context);

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveView, defStyleAttr, 0);

        mWaveCount = typedArray.getInt(R.styleable.WaveView_waveCount, 10);

        mWaveWidth = typedArray.getInt(R.styleable.WaveView_waveWidth, 20);
        //默认为三角形
        ViewMode = typedArray.getInt(R.styleable.WaveView_mode, -2);

        mColor = typedArray.getColor(R.styleable.WaveView_android_color, Color.parseColor("#2c97de"));


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 重写onMeasure()  来告诉系统这个view有多大。
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//得到父容器的宽度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//得到父容器的高度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//得到MeasureSpec.EXACTLY MeasureSpec.AT_MOST,MeasureSpec.UNSPECIFIED。
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      /*
      MeasureSpec由大小和模式组成。它有三种模式：UNSPECIFIED(未指定),父元素不对子元素施加任何束缚，
      子元素可以得到任意想要的大小；EXACTLY(完全)，父元素决定子元素的确切大小，
      子元素将被限定在给定的边界里而忽略它本身大小；AT_MOST(至多)，子元素至多达到指定大小的值。
       */
        if (widthMode == MeasureSpec.EXACTLY) {
            mViewWidth = widthSize;
            mRectWidth = (float) (mViewWidth * 0.8);

        } else if (widthMode == MeasureSpec.AT_MOST) {
//            mViewWidth = PxUtils.dpToPx(300, mContext);
            mViewWidth = widthSize;
            mRectWidth = (float) (mViewWidth * 0.8);

        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mViewHeight = heightSize;
            mRectHeight = (float) (mViewHeight * 0.8);
        } else if (heightMode == MeasureSpec.AT_MOST) {
//            mViewHeight = PxUtils.dpToPx(200, mContext);
            mViewWidth = widthSize;
            mRectHeight = (float) (mViewHeight * 0.8);

        }
        setMeasuredDimension(mViewWidth, mViewHeight);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(mColor);
        //计算每个三角形的高
        mWaveHeight = mRectHeight / mWaveCount;
        //计算padding
        float padding = (mViewWidth - mRectWidth) / 2;
        canvas.drawRect(padding, padding, mRectWidth + padding, mRectHeight + padding, p);
        //如果是三角模式的话
        if (ViewMode == MODE_TRIANGLE) {
            float startX = padding + mRectWidth;
            float startY = padding;
            Path path = new Path();
            path.moveTo(startX, startY);
            for (int i = 0; i < mWaveCount; i++) {
                path.lineTo(startX - mWaveWidth, startY + i * mWaveHeight + (mWaveHeight / 2));
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }

            path.close();
            canvas.drawPath(path, p);
        } else {
            mRadius = mRectHeight / mWaveCount;
            // //绘制右边波浪
            float startX = padding + mRectWidth;
            float startY = padding;
            for (int i = 0; i < mWaveCount / 2; i++) {
                canvas.drawCircle(startX, startY + i * mRadius * 2 + mRadius, mRadius, p);

            }
            //绘制左边波浪
            startX = padding;
            startY = padding;
            for (int i = 0; i < mWaveCount / 2; i++) {

                canvas.drawCircle(startX, startY + i * mRadius * 2 + mRadius, mRadius, p);
            }

        }

    }
}
