/**
 * 
 */
package com.masterserver.core;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

import com.masterserver.config.CoreConfig;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author huangguanlin
 *
 * 2017年1月11日
 */
public class MasterServerNetworkNetty {
	public static final MasterServerNetworkNetty instance = new MasterServerNetworkNetty();
	private EventLoopGroup group;
	private Bootstrap boot;
	private int paksize = 1206;
	private MasterServerNetworkNetty(){
		try {
			boot = initBootStrap();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Bootstrap initBootStrap() throws InterruptedException {
		Bootstrap b = new Bootstrap();
		group = new NioEventLoopGroup();
		b.group(group);
		b.channel(NioDatagramChannel.class);
		b.bind(CoreConfig.instance.getConfig().port()).channel().closeFuture().await();
		b.handler(new MasterServerNettyHandler());
		return b;
	}
	
	public void init(){
		
	}

	public void sendpak(Channel ip, byte[] data) {
		ByteBuf buf = Unpooled.buffer(paksize);
		buf.writeBytes(data);
		ip.writeAndFlush(buf);
	}
}
