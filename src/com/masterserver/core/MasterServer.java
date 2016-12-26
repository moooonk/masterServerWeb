/**
 * 
 */
package com.masterserver.core;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huangguanlin
 * 
 *         2016年12月23日
 */
public class MasterServer {
	public static final MasterServer instance = new MasterServer();
	private ArrayList<String[]> serverList;
	private boolean isActivity;

	private int offHour = -1;
	private int offMin = -1;
	private int onHour = -1;
	private int onMin = -1;
	private long lastOffTime = 0;
	private long lastOnTime = 0;

	private MasterServer() {
		serverList = new ArrayList<String[]>();
		MasterServerScheduled.instance.getExecutor().scheduleWithFixedDelay(new JobForUpdate(), 0, 30, TimeUnit.SECONDS);
		MasterServerNetwork.instance.start();
		isActivity = true;
	}

	public void init() {

	}

	private void updateServerList() {
		MasterServerDao.instance.loadServerList(serverList);
		System.out.println("列表大小：" + serverList.size());
	}

	public void responseServerList(SocketAddress source) {
		if (!isActivity) {
			return;
		}
		int sendlength = 0;
		while (sendlength < serverList.size()) {
			send(source, sendlength);
			sendlength += 200;
		}
		System.out.println(new Date() + " 回复请求" + source);
	}

	private byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	private void send(SocketAddress source, int begin) {
		int size = 6;
		int num;
		byte[] sbuf = new byte[1206];
		sbuf[0] = (byte) 0xff;
		sbuf[1] = (byte) 0xff;
		sbuf[2] = (byte) 0xff;
		sbuf[3] = (byte) 0xff;
		sbuf[4] = (byte) 0x66;
		sbuf[5] = (byte) 0x0a;
		for (int i = begin; i < serverList.size(); i++) {
			num = 0;
			String ip = serverList.get(i)[0];
			String port = serverList.get(i)[1];
			char tmp[] = ip.toCharArray();
			for (int j = 0; j < ip.length(); j++) {
				if (tmp[j] != '.' && j != ip.length() - 1) {
					num = num * 10 + tmp[j] - '0';
				} else if (j == ip.length() - 1) {
					num = num * 10 + tmp[j] - '0';
					sbuf[size] = (byte) num;
					num = 0;
					size++;
				} else {
					sbuf[size] = (byte) num;
					num = 0;
					size++;
				}

			}
			byte portchange[] = Integer.toHexString(Integer.parseInt(port))
					.toUpperCase().getBytes();
			switch (portchange.length) {
			case 1:
				sbuf[size] = uniteBytes((byte) '0', (byte) '0');
				size++;
				sbuf[size] = uniteBytes((byte) '0', portchange[0]);
				size++;
				break;
			case 2:
				sbuf[size] = uniteBytes((byte) '0', (byte) 0);
				size++;
				sbuf[size] = uniteBytes(portchange[0], portchange[1]);
				size++;
				break;
			case 3:
				sbuf[size] = uniteBytes((byte) '0', portchange[0]);
				size++;
				sbuf[size] = uniteBytes(portchange[1], portchange[2]);
				size++;
				break;
			case 4:
				sbuf[size] = uniteBytes(portchange[0], portchange[1]);
				size++;
				sbuf[size] = uniteBytes(portchange[2], portchange[3]);
				size++;
				break;

			}
			if (size == 1206)
				break;
		}
		MasterServerNetwork.instance.sendpak(source, sbuf);
	}

	public boolean isActivity() {
		return isActivity;
	}

	public void setActivity(boolean isActivity) {
		this.isActivity = isActivity;
	}

	private boolean checkautoOff() {
		if (offHour != -1 && offMin != -1) {
			Calendar restarttime = Calendar.getInstance();
			restarttime.set(Calendar.HOUR_OF_DAY, offHour);
			restarttime.set(Calendar.MINUTE, offMin);
			if (restarttime.getTime().getTime() <= System.currentTimeMillis()
					&& System.currentTimeMillis() - lastOffTime > 1000 * 60 * 60 * 24) {
				lastOffTime = System.currentTimeMillis();
				return true;
			}
		}
		return false;
	}

	private boolean checkautoOn() {
		if (onHour != -1 && onMin != -1) {
			Calendar restarttime = Calendar.getInstance();
			restarttime.set(Calendar.HOUR_OF_DAY, onHour);
			restarttime.set(Calendar.MINUTE, onMin);
			if (restarttime.getTime().getTime() <= System.currentTimeMillis()
					&& System.currentTimeMillis() - lastOnTime > 1000 * 60 * 60 * 24) {
				lastOnTime = System.currentTimeMillis();
				return true;
			}
		}
		return false;
	}

	private void autoOnOrOff() {
		if (checkautoOff()) {
			isActivity = false;
		}
		if (checkautoOn()) {
			isActivity = true;
		}
	}

	class JobForUpdate implements Runnable {

		public void run() {
			// TODO Auto-generated method stub
			MasterServer.instance.updateServerList();
		}

	}
}
