package cn.demo.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class MyListView extends ListView
{

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        
    }

    public MyListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        
    }

    public MyListView(Context context)
    {
        super(context);
        
    }
    
    @Override
    public void addHeaderView(View v)
    {
        super.addHeaderView(v);
    }
    
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent)
    {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

}
