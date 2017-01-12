/**
 * 
 */
package com.masterserver.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import com.masterserver.core.MasterServerNetwork.JobForResponse;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * @author huangguanlin
 *
 * 2017年1月11日
 */
public class MasterServerNettyHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		// TODO Auto-generated method stub
		ByteBuf buf = (ByteBuf) msg.copy().content();
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		buf.release();
		if(checkRequest(req)){
			MasterServerScheduled.instance.getExecutor().schedule(new JobForResponse(msg.sender()), 0, TimeUnit.SECONDS);
		}
		
	}
	
	private boolean checkRequest(byte[] data) throws Exception {
		if (data == null)
			return false;
		char check[] = { 0x30, 0x2e, 0x30, 0x2e, 0x30, 0x2e, 0x30, 0x3a, 0x30 };
		String che = new String(check);
		String str = new String(data,2, 9, "US-ASCII");
		if (che.equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	class JobForResponse implements Runnable {
		private InetSocketAddress source;

		public JobForResponse(InetSocketAddress inetSocketAddress) {
			this.source = inetSocketAddress;
		}

		public void run() {
			// TODO Auto-generated method stub
			MasterServer.instance.responseServerList(source);
		}
	}
}
