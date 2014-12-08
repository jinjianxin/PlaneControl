package com.camera.simplemjpeg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ControlView extends View {
	
	private Paint paint = null;
	private int width =0;
	private int height = 0;
	private int tmpx = 0;
	private int tmpy = 0;
	private int bgWidth = 0;
	private int bgHeight = 0;
	private int centerWidth = 0;
	private int centerHeight = 0;
	

	public ControlView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ControlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ControlView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas) {
		
	//	width =this.getWidth();
	//	height =this.getHeight();
		
        super.onDraw(canvas);
        paint =new Paint();
        Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.unitbg);
        
        Log.d("gui",""+b.getHeight());
        Log.d("gui",""+b.getWidth());
        
        bgWidth = b.getWidth()/2;
        bgHeight = b.getHeight()/2;
        
        paint.setColor(Color.RED);
        canvas.drawBitmap(b, this.getWidth()/2-bgWidth, this.getHeight()/2-bgHeight, paint);
        
    
        
        Bitmap controlBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.movepoint);
        
        centerWidth = controlBitmap.getWidth()/2;
        centerHeight = controlBitmap.getHeight()/2;
        
        //canvas.drawBitmap(controlBitmap, width/4+bgWidth-centerWidth+tmpx, height/4+bgHeight-centerHeight+tmpy,paint);
        
        canvas.drawBitmap(controlBitmap,this.getWidth()/2-centerWidth+tmpx,this.getHeight()/2-centerHeight+tmpy,paint);
        
    }

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		//width = w ;
		//height = h;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			

			int x = (int) event.getX();
			int y = (int) event.getY();
			int cx = x - (getWidth() / 2);
			int cy = y - (getHeight() / 2);
			
			double d = Math.sqrt(cx * cx + cy * cy);
			
			Log.d("gui", "d="+d);
			
			tmpx = cx;
			tmpy = cy;
			
			
			if(d>=120)
			{
				return true ;
			}
			
			
			invalidate();
			
			return true;
		}


		return super.onTouchEvent(event);
	}
	
	
	

}
