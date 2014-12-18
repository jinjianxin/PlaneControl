package com.video.planecontrol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.video.planecontrol.ControlView.OrientationListener;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.os.Build;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	private SeekBar m_chanel_1 = null;
	private SeekBar m_chanel_2 = null;
	private SeekBar m_chanel_3 = null;
	private SeekBar m_chanel_4 = null;
	private SeekBar m_chanel_5 = null;
	private SeekBar m_chanel_6 = null;
	private SeekBar m_chanel_7 = null;
	private SeekBar m_chanel_8 = null;
	private Handler m_handler = null;

	private ControlView m_leftControl = null;
	private ControlView m_rightControl = null;
	private Timer mTimer = null;
	private Button m_menu = null;
	private int m_chanel1Value = 34;
	private int m_chanel2Value = 34;
	private int m_chanel4Value = 34;
	private int m_chanel3Value = 34;
	private int m_chanel5Value = 34;
	private int m_chanel6Value = 34;
	private int m_chanel7Value = 34;
	private int m_chanel8Value = 34;

	private Boolean m_check1Value = false;
	private Boolean m_check2Value = false;
	private Boolean m_check3Value = false;
	private Boolean m_check4Value = false;
	private Boolean m_check5Value = false;
	private Boolean m_check6Value = false;
	private Boolean m_check7Value = false;
	private Boolean m_check8Value = false;
	private Boolean m_check9Value = false;

	// private Timer testTimer = null;
	// private TextView testText = null;
	// private Handler testHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		/*
		m_chanel_1 = (VerticalSeekBar) findViewById(R.id.chanel_1);
		m_chanel_2 = (VerticalSeekBar) findViewById(R.id.chanel_2);

		m_chanel_3 = (SeekBar) findViewById(R.id.chanel_3);
		m_chanel_4 = (SeekBar) findViewById(R.id.chanel_4);
		m_chanel_5 = (SeekBar) findViewById(R.id.chanel_5);
		m_chanel_6 = (SeekBar) findViewById(R.id.chanel_6);
		m_chanel_7 = (SeekBar) findViewById(R.id.chanel_7);
		m_chanel_8 = (SeekBar) findViewById(R.id.chanel_8);

		m_chanel_1.setOnSeekBarChangeListener(this);
		m_chanel_2.setOnSeekBarChangeListener(this);
		m_chanel_3.setOnSeekBarChangeListener(this);
		m_chanel_4.setOnSeekBarChangeListener(this);
		m_chanel_5.setOnSeekBarChangeListener(this);
		m_chanel_6.setOnSeekBarChangeListener(this);
		m_chanel_7.setOnSeekBarChangeListener(this);
		m_chanel_8.setOnSeekBarChangeListener(this);*/
		
		m_chanel_1 = (SeekBar) findViewById(R.id.chanel_1);
		m_chanel_1.setOnSeekBarChangeListener(this);
		m_chanel_2 = (SeekBar) findViewById(R.id.chanel_2);
		m_chanel_2.setOnSeekBarChangeListener(this);
		m_chanel_3 =(SeekBar) findViewById(R.id.chanel_3);
		m_chanel_3.setOnSeekBarChangeListener(this);
		m_chanel_4 =(SeekBar) findViewById(R.id.chanel_4);
		m_chanel_4.setOnSeekBarChangeListener(this);

		m_leftControl = (ControlView) findViewById(R.id.left_control);

		m_leftControl.setOrientationListener(new OrientationListener() {

			@Override
			public void back() {

				// TODO Auto-generated method stub
				m_chanel1Value = 50;
				m_chanel2Value = 50;

				checkLeftValue();
				
				m_leftControl.back(0,0);

			}

			@Override
			public void valueChange(int x, int y) {
				// TODO Auto-generated method stub

				if (y < 0) {
					if (!m_check2Value) {
						m_chanel2Value = (int) (50 + Math.abs(y) * 0.2);
					} else {
						m_chanel2Value = (int) (50 - Math.abs(y) * 0.2);
					}

					if (x < 0) {
						if (!m_check1Value) {
							m_chanel1Value = (int) (50 + Math.abs(x) * 0.2);
						} else {
							m_chanel1Value = (int) (50 - Math.abs(x) * 0.2);
						}
					} else {
						if (!m_check1Value) {
							m_chanel1Value = (int) (50 - Math.abs(x) * 0.2);
						} else {
							m_chanel1Value = (int) (50 + Math.abs(x) * 0.2);
						}
					}

				} else {
					if (!m_check2Value) {
						m_chanel2Value = (int) (50 - Math.abs(y) * 0.2);
					} else {
						m_chanel2Value = (int) (50 + Math.abs(y) * 0.2);
					}

					if (x < 0) {
						if (!m_check1Value) {
							m_chanel1Value = (int) (50 + Math.abs(x) * 0.2);
						} else {
							m_chanel1Value = (int) (50 - Math.abs(x) * 0.2);
						}
					} else {
						if (!m_check1Value) {
							m_chanel1Value = (int) (50 - Math.abs(x) * 0.2);
						} else {
							m_chanel1Value = (int) (50 + Math.abs(x) * 0.2);
						}
					}
				}

				checkLeftValue();

			}
		});

		m_menu = (Button) findViewById(R.id.menu);

		m_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MenuActivity.class);
				startActivity(intent);

			}
		});

		m_rightControl = (ControlView) findViewById(R.id.right_control);
		m_rightControl.setBack(true);

		m_rightControl.setOrientationListener(new OrientationListener() {

			@Override
			public void back() {
				// TODO Auto-generated method stub
				
				BtLog.logOutPut("m_chane41Value ="+m_chanel4Value);
				BtLog.logOutPut("m_chane31Value ="+m_chanel3Value);
				
				if(m_chanel3Value>34 && m_chanel3Value<38)
				{
					m_rightControl.back();
				}
				else
				{
					m_rightControl.back(0,0);
				}
				
				m_chanel4Value = 50;
				m_chanel3Value = 50;

				
				checkRightValue();			
			}

			@Override
			public void valueChange(int x, int y) {
				// TODO Auto-generated method stub

				// BtLog.logOutPut("right");

				if (y < 0) {
					if (!m_check4Value) {
						m_chanel4Value = (int) (50 + Math.abs(y) * 0.2);
					} else {
						m_chanel4Value = (int) (50 - Math.abs(y) * 0.2);
					}

					if (x < 0) {
						if (!m_check3Value) {
							m_chanel3Value = (int) (50 + Math.abs(x) * 0.2);
						} else {
							m_chanel3Value = (int) (50 - Math.abs(x) * 0.2);
						}
					} else {
						if (!m_check3Value) {
							m_chanel3Value = (int) (50 - Math.abs(x) * 0.2);
						} else {
							m_chanel3Value = (int) (50 + Math.abs(x) * 0.2);
						}
					}

				} else {
					if (!m_check4Value) {
						m_chanel4Value = (int) (50 - Math.abs(y) * 0.2);
					} else {
						m_chanel4Value = (int) (50 + Math.abs(y) * 0.2);
					}

					if (x < 0) {
						if (!m_check3Value) {
							m_chanel3Value = (int) (50 + Math.abs(x) * 0.2);
						} else {
							m_chanel3Value = (int) (50 - Math.abs(x) * 0.2);
						}
					} else {
						if (!m_check3Value) {
							m_chanel3Value = (int) (50 - Math.abs(x) * 0.2);
						} else {
							m_chanel3Value = (int) (50 + Math.abs(x) * 0.2);
						}
					}
				}

				checkRightValue();

			}
		});

	}

	public void checkLeftValue() {
		if (m_chanel1Value >= 100) {
			m_chanel1Value = 100;
		}

		if (m_chanel2Value >= 100) {
			m_chanel2Value = 100;
		}

		if (m_chanel1Value <= 0) {
			m_chanel1Value = 0;
		}

		if (m_chanel2Value <= 0) {
			m_chanel2Value = 0;
		}

		m_chanel1Value = 25 + m_chanel1Value / 4;
		m_chanel2Value = 25 + m_chanel2Value / 4;
		
		sendData();

	}

	public void checkRightValue() {

		if (m_chanel4Value >= 100) {
			m_chanel4Value = 100;
		}

		if (m_chanel4Value <= 0) {
			m_chanel4Value = 0;
		}

		if (m_chanel3Value >= 100) {
			m_chanel3Value = 100;
		}

		if (m_chanel3Value <= 0) {
			m_chanel3Value = 0;
		}
		m_chanel3Value = 25 + m_chanel3Value / 4;
		m_chanel4Value = 25 + m_chanel4Value / 4;

		// BtLog.logOutPut("m_chanel3Value = "+m_chanel3Value);
		// BtLog.logOutPut("m_chanel4Value = "+m_chanel4Value);
		

		sendData();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

		if (seekBar == m_chanel_1) {
		
			if(progress<1)
			{
				if(!m_check1Value)
				{
					m_chanel1Value+=20;
				}
				else
				{
					m_chanel1Value-=20;
				}
			}
			else if(progress>1)
			{
				if(!m_check1Value)
				{
					m_chanel1Value-=20;
				}
				else
				{
					m_chanel1Value+=20;
				}
			}
			
			checkLeftValue();
			
		} else if (seekBar == m_chanel_2) {
			if(progress>1)
			{
				if(!m_check2Value)
				{
					m_chanel2Value+=20;
				}
				else
				{
					m_chanel2Value-=20;
				}
			}
			else if(progress<1)
			{
				if(!m_check2Value)
				{
					m_chanel2Value-=20;
				}
				else
				{
					m_chanel2Value+=20;
				}
			}
			
			checkLeftValue();
		} else if (seekBar == m_chanel_3) {
			
			
			if(progress<1)
			{
				if(!m_check3Value)
				{
					m_chanel3Value+=20;
				}
				else
				{
					m_chanel3Value-=20;
				}
			}
			else if(progress>1)
			{
				if(!m_check3Value)
				{
					m_chanel3Value-=20;
				}
				else
				{
					m_chanel3Value+=20;
				}
			}
			
			checkRightValue();

		} else if (seekBar == m_chanel_4) {
			
			if(progress>1)
			{
				if(!m_check4Value)
				{
					m_chanel4Value+=20;
				}
				else
				{
					m_chanel4Value-=20;
				}
			}
			else if(progress<1)
			{
				if(!m_check4Value)
				{
					m_chanel4Value-=20;
				}
				else
				{
					m_chanel4Value+=20;
				}
			}
			
			checkRightValue();

		} else if (seekBar == m_chanel_5) {
			if(!m_check5Value)
			{
				m_chanel5Value = 25 + progress * 25;
			}
			else
			{
				m_chanel5Value = 25 +(2-progress) * 25;
			}
			
			sendData();
			
		} else if (seekBar == m_chanel_6) {

			if(!m_check6Value)
			{
			m_chanel6Value = 25 + progress * 25;
			}
			else
			{
				m_chanel6Value = 25 + (2-progress) * 25;
			}
			
			sendData();
			
		} else if (seekBar == m_chanel_7) {

			if(!m_check7Value)
			{
				m_chanel7Value = 25 + progress / 4;
			}
			else
			{
				m_chanel7Value = 25 + (100- progress) / 4;
			}
			
			sendData();
			
		} else if (seekBar == m_chanel_8) {

			if(!m_check8Value)
			{
				m_chanel8Value = 25 + progress / 4;
			}
			else
			{
				m_chanel8Value = 25 +(100- progress) / 4;
			}

			sendData();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MyThread thread = new MyThread();
		thread.start();

		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				if (m_handler != null) {

					DecimalFormat dig = new DecimalFormat("000");

					StringBuilder builder = new StringBuilder();
					builder.append(dig.format(111) + dig.format(0)
							+ dig.format(0) + dig.format(0) + dig.format(0)
							+ dig.format(0) + dig.format(0) + dig.format(0)
							+ dig.format(0));

					Message msg = new Message();
					msg.arg1 = 2;
					Bundle bundle = new Bundle();
					// BtLog.logOutPut(builder.toString());
					bundle.putString("value", builder.toString());
					msg.setData(bundle);

					m_handler.sendMessage(msg);
				}
			}
		}, 0, 5000);

		getDefalutValue();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}

		/*
		 * if(testTimer!=null) { testTimer.cancel(); testTimer = null; }
		 */
	}

	public class MyThread extends Thread {

		UdpClientSocket socket = null;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			Looper.prepare();

			try {
				socket = new UdpClientSocket();

				ReceiverThread thread = new ReceiverThread(socket);
				thread.start();
				/*
				 * testTimer = new Timer(); testTimer.schedule(new TimerTask() {
				 * 
				 * @Override public void run() { // TODO Auto-generated method
				 * stub // testText.setText(String.valueOf(socket.getValue()));
				 * 
				 * Message msg = new Message(); msg.arg1 = socket.getValue();
				 * 
				 * testHandler.sendMessage(msg);
				 * 
				 * } }, 5000,2000);
				 */

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			m_handler = new Handler() {

				public void handleMessage(Message msg) {
					if (msg.arg1 == 1) {
						Bundle bundle = msg.getData();
						String buffer = bundle.getString("value");

						String serverHost = "192.168.16.1";
						int serverPort = 1025;
						// BtLog.logOutPut(buffer);
						try {
							MyThread.this.socket.send(serverHost, serverPort,
									buffer.getBytes());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (msg.arg1 == 2) {
						Bundle bundle = msg.getData();
						String buffer = bundle.getString("value");

						String serverHost = "192.168.16.1";
						int serverPort = 1025;
						// BtLog.logOutPut(buffer);
						try {
							MyThread.this.socket.send(serverHost, serverPort,
									buffer.getBytes());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			};

			Looper.loop();
		}
	}

	public class ReceiverThread extends Thread {
		UdpClientSocket mSocket = null;
		private byte[] buffer = new byte[1024];

		public ReceiverThread(UdpClientSocket socket) {
			// TODO Auto-generated constructor stub
			this.mSocket = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			Looper.prepare();

			String serverHost = "192.168.16.1";
			int serverPort = 1025;
			try {

				mSocket.receive(serverHost, serverPort);
				// BtLog.logOutPut("receiver =" + str);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Looper.loop();

		}
	}

	public void sendData() {

		DecimalFormat dig = new DecimalFormat("000");

		StringBuilder builder = new StringBuilder();
		builder.append(dig.format(m_chanel1Value) + dig.format(m_chanel2Value)
				+ dig.format(m_chanel3Value) + dig.format(m_chanel4Value)
				+ dig.format(m_chanel5Value) + dig.format(m_chanel6Value)
				+ dig.format(m_chanel7Value) + dig.format(m_chanel8Value));

		Message msg = new Message();
		msg.arg1 = 1;
		Bundle bundle = new Bundle();
		// BtLog.logOutPut(builder.toString());
		bundle.putString("value", builder.toString());
		msg.setData(bundle);

		m_handler.sendMessage(msg);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		getDefalutValue();
	}
	
	public void getDefalutValue() {
		SharedPreferences preferences = getApplicationContext()
				.getSharedPreferences("model", 0);
		m_check1Value = preferences.getBoolean("toggle1", false);
		m_check2Value = preferences.getBoolean("toggle2", false);
		m_check3Value = preferences.getBoolean("toggle3", false);
		m_check4Value = preferences.getBoolean("toggle4", false);
		m_check5Value = preferences.getBoolean("toggle5", false);
		m_check6Value = preferences.getBoolean("toggle6", false);
		m_check7Value = preferences.getBoolean("toggle7", false);
		m_check8Value = preferences.getBoolean("toggle8", false);
		m_check9Value = preferences.getBoolean("toggle9", false);

	}

}
