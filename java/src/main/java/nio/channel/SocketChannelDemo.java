package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

        channel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(16);
        channel.read(buffer);   //读操作
        channel.close();
    }
}
