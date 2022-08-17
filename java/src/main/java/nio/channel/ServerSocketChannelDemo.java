package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {  //监听
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8888;

        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());

        ServerSocketChannel channel = ServerSocketChannel.open();   //打开

        channel.socket().bind(new InetSocketAddress(port));

        channel.configureBlocking(false);   //设置非阻塞

        while (true) {
            System.out.println("waiting for connections");
            SocketChannel sc = channel.accept();    //监听
            if (sc == null) {
                System.out.println("null");
                Thread.sleep(2000);
            } else {
                System.out.println("incoming connection from:" + sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
