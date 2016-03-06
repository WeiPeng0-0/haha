package cn.demo.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;


public class RoundProgressBar extends View {

    private Paint paint;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int max;
    private int style;

    //当前进度
    private int progress;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化一些在绘制过程当中需要使用到的局部变量和自定义属性
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //画笔
        paint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        //获取自定义属性和默认值
        //圆环的颜色
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        //圆环进度的颜色
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        //中间进度百分比文字字符串的颜色
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
        //圆环的宽度
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        //最大进度
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 1:画最外层的大圆环
         */
        paint.setColor(roundColor); //设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setAntiAlias(true);  //消除锯齿
        //设定圆心的中心点坐标(圆的宽度和高度肯定设置是相同的，所以X，y的值肯定相同)
        int center = getWidth() / 2; //获取圆心的x坐标
        int radius = (int) (center - roundWidth / 2);
        canvas.drawCircle(center, center, radius, paint); //画出圆环

        /***
         * 2:画百分比进度
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
        //中间的进度百分比，先转换成float在进行除法运算，不然都为0
        int percent = (int) (((float) progress / (float) max) * 100);
        //测量字体宽度,我们需要根据字体的宽度设置在圆环中间
        float textWidth = paint.measureText(percent + "%");
        canvas.drawText(percent + "%", center - textWidth / 2, center + textSize / 2, paint); //画出进度百分比

        /**
         * 3:画圆弧,圆环的进度
         */
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色
        paint.setStyle(Paint.Style.STROKE);
        //用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(oval, 0, 360 * progress / max, false, paint);  //根据进度画圆弧
    }

    /**
     * 根据进度，重新绘制圆环
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }
}

