package go.netty.websocket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 *  类说明：对websocket的数据进行处理
 */
public class ProcesssWsFrameHandler
        extends SimpleChannelInboundHandler<WebSocketFrame> {

    private final ChannelGroup group;

    public ProcesssWsFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    private static final Logger logger
            = LoggerFactory.getLogger(ProcesssWsFrameHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                WebSocketFrame frame) throws Exception {
        if(frame instanceof TextWebSocketFrame){
            String request = ((TextWebSocketFrame)frame).text();
            ctx.channel().writeAndFlush(
                    new TextWebSocketFrame(request.toUpperCase(Locale.CHINA)));
            /*群发*/
            group.writeAndFlush(
                    new TextWebSocketFrame(
                            "Client"+ctx.channel()+"say:"+request.toUpperCase(Locale.CHINA)
                    ));

        }else{
            throw new UnsupportedOperationException("unsupport data frame");
        }
    }

    /*重写 userEventTriggered()方法以处理自定义事件*/
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx,
                                   Object evt) throws Exception {
        /*检查事件类型，如果是ws握手成功事件，群发通知*/
       if(evt == WebSocketServerProtocolHandler.
               ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
           group.writeAndFlush(
                   new TextWebSocketFrame("Client"+ctx.channel()+" joined"));
           group.add(ctx.channel());

       }
    }
}
