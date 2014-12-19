package com.camera.simplemjpeg;

import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.camera.simplemjpeg.ControlView.OrientationListener;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MjpegActivity extends Activity implements OnSeekBarChangeListener  {
	
	private SeekBar m_chanel_1 = null;
	private SeekBar m_chanel_2 = null;
	private SeekBar m_chanel_3 = null;
	private SeekBar m_chanel_4 = null;
	private SeekBar m_chanel_5 = null;
	private SeekBar m_chanel_6 = null;
	private SeekBar m_chanel_7 = null;
	private SeekBar m_chanel_8 = null;
	
	private int m_chanel1Value = 75;
	private int m_chanel2Value = 75;
	private int m_chanel4Value = 75;
	private int m_chanel3Value = 75;
	private int m_chanel5Value = 75;
	private int m_chanel6Value = 75;
	private int m_chanel7Value = 75;
	private int m_chanel8Value = 75;
	private int mbaseNumber = 25;
	private float mCoefficient = 1.0f;

	
	private Boolean m_check1Value = false;
	private Boolean m_check2Value = false;
	private Boolean m_check3Value = false;
	private Boolean m_check4Value = false;
	private Boolean m_check5Value = false;
	private Boolean m_check6Value = false;
	private Boolean m_check7Value = false;
	private Boolean m_check8Value = false;
	private Boolean m_check9Value = false;
	
	private ControlView m_leftControl = null;
	private ControlView m_rightControl = null;
	private Button m_menu = null;
	
	private static final boolean DEBUG=false;
    private static final String TAG = "MJPEG";
    
    private Handler m_handler = null;
	 private Timer msendTimer = null;
		private Timer mTimer = null;
    
    private MjpegView mv = null;
    String URL;
    
    // for settings (network and resolution)
    private static final int REQUEST_SETTINGS = 0;
    
    private int width = 640;
    private int height = 480;
    
    private int ip_ad1 = 192;
    private int ip_ad2 = 168;
    private int ip_ad3 = 2;
    private int ip_ad4 = 1;
    private int ip_port = 80;
    private String ip_command = "?action=stream";
    
    private boolean suspending = false;
    
	final Handler handler = new Handler();
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
       
        URL = "http://192.168.100.1:80/video.cgi" ;//new String(sb);

        setContentView(R.layout.main);
     /*
     mv = (MjpegView) findViewById(R.id.mv);  
        if(mv != null){
        	mv.setResolution(width, height);
        }
        */
        initUI();
        /*
     
        new DoRead().execute(URL);*/
        
        Debug.startMethodTracing("myapp");
    }

    
    public void initUI()
    {
		
		m_chanel_1 = (SeekBar) findViewById(R.id.chanel_1);
		m_chanel_1.setOnSeekBarChangeListener(this);
		m_chanel_2 = (SeekBar) findViewById(R.id.chanel_2);
		m_chanel_2.setOnSeekBarChangeListener(this);
		m_chanel_3 =(SeekBar) findViewById(R.id.chanel_3);
		m_chanel_3.setOnSeekBarChangeListener(this);
		m_chanel_4 =(SeekBar) findViewById(R.id.chanel_4);
		m_chanel_4.setOnSeekBarChangeListener(this);
		
		m_chanel_5 =(SeekBar) findViewById(R.id.chanel_5);
		m_chanel_5.setOnSeekBarChangeListener(this);
		m_chanel_6 =(SeekBar) findViewById(R.id.chanel_6);
		m_chanel_6.setOnSeekBarChangeListener(this);
		m_chanel_7 =(SeekBar) findViewById(R.id.chanel_7);
		m_chanel_7.setOnSeekBarChangeListener(this);
		m_chanel_8 =(SeekBar) findViewById(R.id.chanel_8);
		m_chanel_8.setOnSeekBarChangeListener(this);
		
	//	testText = (TextView) findViewById(R.id.testText);

		m_leftControl = (ControlView) findViewById(R.id.left_control);

		m_leftControl.setOrientationListener(new OrientationListener() {

			@Override
			public void back() {

				// TODO Auto-generated method stub
			
			//	count = 0;
		//		testText.setText(String.valueOf(0));
				
				if(m_chanel1Value>34 && m_chanel1Value<38)
				{
					if(m_check9Value)
					{
						m_leftControl.back();	
					}
					else
					{
						m_leftControl.back(0,0);
					}
					
				}
				else
				{
					m_leftControl.back(0,0);
				}
			
				
				m_chanel4Value = 50;
				m_chanel3Value = 50;
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
						m_chanel2Value = (int) (50- Math.abs(y) * 0.2);
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
				if(!m_check9Value)
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
		
	//	BtLog.logOutPut("m_chanel1Value = "+m_chanel1Value);
		
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

		m_chanel1Value = mbaseNumber + (int)(m_chanel1Value*mCoefficient );
		m_chanel2Value = mbaseNumber + (int)(m_chanel2Value *mCoefficient);
		
		BtLog.logOutPut("m_chanel1Value = "+m_chanel1Value);
		
	//	sendData();

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
		m_chanel3Value = mbaseNumber + (int)(m_chanel3Value*mCoefficient);
		m_chanel4Value = mbaseNumber + (int)(m_chanel4Value *mCoefficient);

	    BtLog.logOutPut("m_chanel3Value = "+m_chanel3Value);
	  // BtLog.logOutPut("m_chanel4Value = "+m_chanel4Value);
		

	//	sendData();
	}
    
    public void onResume() {
    	if(DEBUG) Log.d(TAG,"onResume()");
        super.onResume();
        if(mv!=null){
        	if(suspending){
        		new DoRead().execute(URL);
        		suspending = false;
        	}
        }

	//	MyThread thread = new MyThread();
//		thread.start();
        
		MyThread thread = new MyThread();
		thread.start();

		startTimer();
		
		getDefalutValue();
        
    }

    public void onStart() {
    	if(DEBUG) Log.d(TAG,"onStart()");
        super.onStart();
    }
    public void onPause() {
    	if(DEBUG) Log.d(TAG,"onPause()");
        super.onPause();
        if(mv!=null){
        	if(mv.isStreaming()){
		        mv.stopPlayback();
		        suspending = true;
        	}
        }
        
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
		
		if(msendTimer!=null)
		{
			msendTimer.cancel();
			msendTimer = null;
		}
    }
    public void onStop() {
    	if(DEBUG) Log.d(TAG,"onStop()");
        super.onStop();
    }

    public void onDestroy() {
    	if(DEBUG) Log.d(TAG,"onDestroy()");
    	
    	if(mv!=null){
    		mv.freeCameraMemory();
    	}
    	
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
		
		if(msendTimer!=null)
		{
			msendTimer.cancel();
			msendTimer = null;
		}
    	
        super.onDestroy();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.layout.option_menu, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case R.id.settings:
    			return true;
    	}
    	return false;
    }


    public void setImageError(){
    	handler.post(new Runnable() {
    		@Override
    		public void run() {
    			setTitle(R.string.title_imageerror);
    			return;
    		}
    	});
    }
    
    public class DoRead extends AsyncTask<String, Void, MjpegInputStream> {
    	
        protected MjpegInputStream doInBackground(String... url) {
            //TODO: if camera has authentication deal with it and don't just not work
            HttpResponse res = null;         
            DefaultHttpClient httpclient = new DefaultHttpClient(); 
            
            HttpParams httpParams = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5*1000);
            HttpConnectionParams.setSoTimeout(httpParams, 5*1000);
            
            //urlConnection.setRequestProperty("connection", "close");
            
            if(DEBUG) Log.d(TAG, "1. Sending http request");
            try {
                res = httpclient.execute(new HttpGet(URI.create(url[0])));
                if(DEBUG) Log.d(TAG, "2. Request finished, status = " + res.getStatusLine().getStatusCode());
                if(res.getStatusLine().getStatusCode()==401){
                    //You must turn off camera User Access Control before this will work
                    return null;
                }
                return new MjpegInputStream(res.getEntity().getContent());  
            } catch (ClientProtocolException e) {
            	if(DEBUG){
	                e.printStackTrace();
	                Log.d(TAG, "Request failed-ClientProtocolException", e);
            	}
                //Error connecting to camera
            } catch (IOException e) {
            	if(DEBUG){
	                e.printStackTrace();
	                Log.d(TAG, "Request failed-IOException", e);
            	}
                //Error connecting to camera
            }
            return null;
        }

        protected void onPostExecute(MjpegInputStream result) {
            mv.setSource(result);
            if(result!=null){
            	result.setSkip(1);
            	//setTitle(R.string.app_name);
            }else{
            	//setTitle(R.string.title_disconnected);
            }
            mv.setDisplayMode(MjpegView.SIZE_FULLSCREEN);
            mv.showFps(true);
        }
    }
    
    public class RestartApp extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... v) {
        	MjpegActivity.this.finish();
            return null;
        }

        protected void onPostExecute(Void v) {
        	startActivity((new Intent(MjpegActivity.this, MjpegActivity.class)));
        }
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
						 BtLog.logOutPut(buffer);
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


	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
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
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		startTimer();
		
		getDefalutValue();
			
	}
	
	public void startTimer()
	{
		
		if(mTimer!=null)
		{
			mTimer.cancel();
			mTimer = null;
		}
		
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				if (m_handler != null) {

					DecimalFormat dig = new DecimalFormat("000");

					StringBuilder builder = new StringBuilder();
					builder.append(dig.format(222) + dig.format(0)
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
		
		if(msendTimer!=null)
		{
			msendTimer.cancel();
			msendTimer = null;
		}

		msendTimer = new Timer();
		msendTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sendData();
			}
		},100,20);
	}
    
	public void sendData() {

		DecimalFormat dig = new DecimalFormat("000");
		
		//checkLeftValue();
		//checkRightValue();

		StringBuilder builder = new StringBuilder();
		builder.append(dig.format(m_chanel1Value) + dig.format(m_chanel2Value)
				+ dig.format(m_chanel3Value) + dig.format(m_chanel4Value)
				+ dig.format(m_chanel5Value) + dig.format(m_chanel6Value)
				+ dig.format(m_chanel7Value) + dig.format(m_chanel8Value));

		Message msg = new Message();
		msg.arg1 = 1;
		Bundle bundle = new Bundle();
	    BtLog.logOutPut("==="+builder.toString());
		bundle.putString("value", builder.toString());
		msg.setData(bundle);

		m_handler.sendMessage(msg);
	}
	
}
