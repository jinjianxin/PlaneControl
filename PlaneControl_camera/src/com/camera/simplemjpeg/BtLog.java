
/**  
* @Title: Log.java
* @Package com.sp.bt.util
* @Description: TODO 日志输出
* @author jjx
* @date 2014年9月24日 上午10:49:58
* @version V1.0 
*/

 
package com.camera.simplemjpeg;


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
