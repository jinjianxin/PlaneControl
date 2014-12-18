package com.video.planecontrol;

import java.io.*;
import java.net.*;

public class UdpClientSocket {
    private byte[] buffer = new byte[1024];

    private DatagramSocket ds = null;


    public UdpClientSocket() throws Exception {
        ds = new DatagramSocket();
    }
    

    public final void setSoTimeout(final int timeout) throws Exception {
        ds.setSoTimeout(timeout);
    }


    public final int getSoTimeout() throws Exception {
        return ds.getSoTimeout();
    }

    public final DatagramSocket getSocket() {
        return ds;
    }


    public final DatagramPacket send(final String host, final int port,
            final byte[] bytes) throws IOException {
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress
                .getByName(host), port);
        ds.send(dp);
        return dp;
    }


    public final String receive(final String lhost, final int lport)
            throws Exception {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        
		while (true) {


			ds.receive(dp);

			String info = new String(dp.getData(), 0, dp.getLength());

			byte[] data = dp.getData();

			byte[] test = new byte[2];
			test[0] =data[0];
			test[1] =data[1];
			
			int iOutcome = 0;
			byte bLoop;

			for (int i = 0; i < test.length; i++) {
				bLoop = test[i];
				iOutcome += (bLoop & 0xFF) << (8 * i);
			}
			
		//	BtLog.logOutPut(info);
			//BtLog.logOutPut("iOutcome="+iOutcome);
		}
    }


    public final void close() {
        try {
            ds.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    

}