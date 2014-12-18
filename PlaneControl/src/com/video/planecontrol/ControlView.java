package com.video.planecontrol;

import android.R.bool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ControlView extends View {

	private Paint paint = null;
	private int width = 0;
	private int height = 0;
	private int tmpx = 0;
	private int tmpy = 0;
	private int bgWidth = 0;
	private int bgHeight = 0;
	private int centerWidth = 0;
	private int centerHeight = 0;
	private boolean isBack = false;

	private Point centerPoint;
	private Point topPoint;

	public interface OrientationListener {
		void back();
		void valueChange(int x, int y);

	}

	public void setBack(boolean back) {
		this.isBack = back;
	}

	private OrientationListener mOrientationListener = null;

	void setOrientationListener(OrientationListener listener) {
		mOrientationListener = listener;
	}

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

		// width =this.getWidth();
		// height =this.getHeight();

		super.onDraw(canvas);
		paint = new Paint();
		Bitmap b = BitmapFactory.decodeResource(getResources(),
				R.drawable.unitbg);

		bgWidth = b.getWidth() / 2;
		bgHeight = b.getHeight() / 2;

		paint.setColor(Color.RED);
		canvas.drawBitmap(b, this.getWidth() / 2 - bgWidth, this.getHeight()
				/ 2 - bgHeight, paint);

		/*
		 * paint.setStyle(Paint.Style.STROKE); paint.setStrokeWidth(5);
		 * 
		 * canvas.drawCircle(this.getWidth() / 2 , this.getHeight() / 2, 200,
		 * paint);
		 */

		Bitmap controlBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.movepoint);

		centerWidth = controlBitmap.getWidth() / 2;
		centerHeight = controlBitmap.getHeight() / 2;

		if(tmpy<-130 && tmpy<=-156)
		{
			tmpy = -156;
		}
		
		if(tmpy>170 && tmpy>=208)
		{
			tmpy = 188;
		}
		
		canvas.drawBitmap(controlBitmap, this.getWidth() / 2 - centerWidth
				+ tmpx, this.getHeight() / 2 - centerHeight + tmpy, paint);

		canvas.drawCircle(this.getWidth() / 2 + tmpx, this.getHeight() / 2
				+ tmpy, 5, paint);

		centerPoint = new Point(this.getWidth() / 2, this.getHeight() / 2);
		topPoint = new Point(this.getWidth() / 2, this.getHeight() / 2
				- bgHeight);

		Point topPoint1 = new Point(this.getWidth() / 2 + bgHeight,
				this.getHeight() / 2);

		// canvas.drawLine(centerPoint.x,centerPoint.y , topPoint.x ,
		// topPoint.y, paint);
		// canvas.drawLine(centerPoint.x,centerPoint.y , topPoint1.x ,
		// topPoint1.y, paint);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP: {

/*			tmpx = 0;
			tmpy = 0;
			mOrientationListener.back();
			invalidate();*/
			
			mOrientationListener.back();

		}
			break;
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:

			int x = (int) event.getX();
			int y = (int) event.getY();
			int cx = x - (getWidth() / 2);
			int cy = y - (getHeight() / 2);

			double d = Math.sqrt(cx * cx + cy * cy);

			tmpx = cx;
			tmpy = cy;

			ParseData();

			if (d >= 175) {
				return true;
			}

			invalidate();

			return true;
		}

		return super.onTouchEvent(event);
	}

	private void ParseData() {

		Point endPoint = new Point(this.getWidth() / 2 + tmpx, this.getHeight()
				/ 2 + tmpy);

		int x = endPoint.x - centerPoint.x;
		int y = endPoint.y - centerPoint.y;

		mOrientationListener.valueChange(x, y);
	}
	
	public void back(int x,int y)
	{
		tmpx = 0;
		tmpy = 0;
		invalidate();
	}

	public void back()
	{
		tmpx = 0;		
		BtLog.logOutPut("tmpy = "+tmpy);
		
		invalidate();
	}
}
