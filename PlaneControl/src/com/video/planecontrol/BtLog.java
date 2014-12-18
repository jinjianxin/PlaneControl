
/**  
* @Title: Log.java
* @Package com.sp.bt.util
* @Description: TODO聽鏃ュ織杈撳嚭
* @author jjx
* @date 2014骞�9鏈�24鏃� 涓婂崍10:49:58
* @version V1.0 
*/

 
package com.video.planecontrol;


/**
 * @author jjx
 *
 */

public class BtLog {

	private final static  String TAG = "guidongli";
	public static void logOutPut(String log)
	{
		 android.util.Log.d(TAG, log);
	}
}
