package gao.netty.basic.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class ClientHandler extends ChannelInboundHandlerAdapter {


	/*channel活跃后，做业务处理*/
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client 通道激活...");
    }

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf buf = (ByteBuf) msg;
			
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);
			
			String body = new String(req, "utf-8");
			System.out.println("Client接收到返回信息 :" + body );
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client...channelReadComplete方法执行了...");
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

}