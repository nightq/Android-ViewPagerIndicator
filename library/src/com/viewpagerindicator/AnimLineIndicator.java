package com.viewpagerindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class AnimLineIndicator extends View {
	
	private float offsetPercent;//%
    private float offsetPix;//%
    private int index;
    
    /**
     * 滾動
     */
    public void onPageScrolled(int index, float offsetPercent, int offsetPix) {
    	this.index = index;
    	this.offsetPercent = offsetPercent;
    	this.offsetPix = offsetPix;
    	invalidate();
    }
    
	public AnimLineIndicator(Context context) {
		this(context, null);    
	} 
	
	public AnimLineIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
        setBackgroundResource(android.R.color.darker_gray);
		// TODO Auto-generated constructor stub
	}

	private int height;
	private int color = Color.BLUE;
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	private LinearLayout mTabLayout;
	
	public void setTabLayout(LinearLayout mTabLayout) {
		this.mTabLayout = mTabLayout;
	}
	
    private final Paint mPaint = new Paint();
    
    @Override
 	protected void onDraw(Canvas canvas) {
 		// TODO Auto-generated method stub
 		super.onDraw(canvas);
    	if (canvas != null) { 
        	mPaint.setColor(color);
        	mPaint.setStyle(Style.FILL); 
        	if (mTabLayout != null && mTabLayout.getChildCount() > 0) {
        		int widthIndicator;
        		int left = 0;
        		if (index == mTabLayout.getChildCount() - 1) {
            		final View tabView = mTabLayout.getChildAt(index);
            		int startPosition = tabView.getLeft(); 
            		widthIndicator = tabView.getWidth();
            		left = startPosition; 
                    canvas.drawRect(new Rect(left, 0, left + widthIndicator, canvas.getHeight()), mPaint);
        		} else {
            		final View tabView0 = mTabLayout.getChildAt(index);
            		final View tabView1 = mTabLayout.getChildAt(index + 1);
            		int startPosition = tabView0.getLeft();  
            		left = (int) (startPosition + tabView0.getWidth() * offsetPercent); 
            		widthIndicator = (int) (tabView0.getWidth() + (tabView1.getWidth() - tabView0.getWidth()) * offsetPercent);
                    canvas.drawRect(new Rect(left, 0, left + widthIndicator, canvas.getHeight()), mPaint);
        		} 
        	}
    	}
 	}
}
