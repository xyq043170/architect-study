package com.test.netty.udp;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ThreadLocalRandom;

public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{
	private static final String[] DICTIONARY={
			"黑发不知勤学早，白首方悔读书迟.",
			"三更灯火五更鸡，正是男儿读书时.",
			"古人学问无遗力，少壮工夫老始成.",
			"纸上得来终觉浅，绝知此事要躬行.",
			"历览前贤国与家，成由勤俭破由奢.",
			"一日不读书，胸臆无佳想.",
			"诚既勇兮又以武，终刚强兮不可凌.",
			"出不入兮往不反，平原忽兮路超远.",
			"归志宁无五亩园，读书本意在元元."
	};
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		String req = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println("Server:"+req);
		if(req.equals("请求查询"))
		{
			ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("查询结果："+nextQuote(), CharsetUtil.UTF_8), packet.sender()));
		}
		
	}

	private String nextQuote() {
		int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
		return DICTIONARY[quoteId];
	}
	
}
