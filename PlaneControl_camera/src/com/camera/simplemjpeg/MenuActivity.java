package com.camera.simplemjpeg;

import java.io.IOException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class MenuActivity  extends Activity implements OnCheckedChangeListener{
	
	private ToggleButton m_toggle1 = null;
	private ToggleButton m_toggle2 = null;
	private ToggleButton m_toggle3 = null;
	private ToggleButton m_toggle4 = null;
	private ToggleButton m_toggle5 = null;
	private ToggleButton m_toggle6 = null;
	private ToggleButton m_toggle7 = null;
	private ToggleButton m_toggle8 = null;
	private ToggleButton m_toggle9 = null;
	private Button m_button = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu_layout);
		
		m_toggle1 =(ToggleButton) findViewById(R.id.togglt_1);
		m_toggle2 =(ToggleButton) findViewById(R.id.togglt_2);
		m_toggle3 =(ToggleButton) findViewById(R.id.togglt_3);
		m_toggle4 =(ToggleButton) findViewById(R.id.togglt_4);
		m_toggle5 =(ToggleButton) findViewById(R.id.togglt_5);
		m_toggle6 =(ToggleButton) findViewById(R.id.togglt_6);
		m_toggle7 =(ToggleButton) findViewById(R.id.togglt_7);
		m_toggle8 =(ToggleButton) findViewById(R.id.togglt_8);
		m_toggle9 =(ToggleButton) findViewById(R.id.togglt_9);
		
		m_toggle1.setOnCheckedChangeListener(this);
		m_toggle2.setOnCheckedChangeListener(this);
		m_toggle3.setOnCheckedChangeListener(this);
		m_toggle4.setOnCheckedChangeListener(this);
		m_toggle5.setOnCheckedChangeListener(this);
		m_toggle6.setOnCheckedChangeListener(this);
		m_toggle7.setOnCheckedChangeListener(this);
		m_toggle8.setOnCheckedChangeListener(this);
		m_toggle9.setOnCheckedChangeListener(this);
		
		
		m_button = (Button) findViewById(R.id.save);
		m_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveState();
				finish();
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setState();
	}

	
	public void setState()
	{
		SharedPreferences preferences = getApplicationContext().getSharedPreferences("model", 0);
		m_toggle1.setChecked(preferences.getBoolean("toggle1", false));
		m_toggle2.setChecked(preferences.getBoolean("toggle2", false));
		m_toggle3.setChecked(preferences.getBoolean("toggle3", false));
		m_toggle4.setChecked(preferences.getBoolean("toggle4", false));
		m_toggle5.setChecked(preferences.getBoolean("toggle5", false));
		m_toggle6.setChecked(preferences.getBoolean("toggle6", false));
		m_toggle7.setChecked(preferences.getBoolean("toggle7", false));
		m_toggle8.setChecked(preferences.getBoolean("toggle8", false));
		m_toggle9.setChecked(preferences.getBoolean("toggle9", false));
		
	}
	
	public void saveState()
	{
		SharedPreferences preferences = getApplicationContext().getSharedPreferences("model", 0);
		Editor editor= preferences.edit();
		editor.putBoolean("toggle1", m_toggle1.isChecked());
		editor.putBoolean("toggle2", m_toggle2.isChecked());
		editor.putBoolean("toggle3", m_toggle3.isChecked());
		editor.putBoolean("toggle4", m_toggle4.isChecked());
		editor.putBoolean("toggle5", m_toggle5.isChecked());
		editor.putBoolean("toggle6", m_toggle6.isChecked());
		editor.putBoolean("toggle7", m_toggle7.isChecked());
		editor.putBoolean("toggle8", m_toggle8.isChecked());
		editor.putBoolean("toggle9", m_toggle9.isChecked());
		editor.commit();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
			
	}
	

	
}
