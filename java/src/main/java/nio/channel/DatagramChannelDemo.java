package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class DatagramChannelDemo {

    public void sendDatagram() throws IOException, InterruptedException {   //发送
        DatagramChannel sendChannel = DatagramChannel.open();

        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1", 9999);

        while (true) {
            ByteBuffer sendBuffer = ByteBuffer.wrap("hello world".getBytes("UTF-8"));
            sendChannel.send(sendBuffer, sendAddress);
            System.out.println("已发送");
            Thread.sleep(1000);
        }
    }

    public void receiveDatagram() throws IOException {  //接收
        DatagramChannel receiveChannel = DatagramChannel.open();

        InetSocketAddress receiveAddress = new InetSocketAddress(9999);

        receiveChannel.bind(receiveAddress);

        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

        while (true) {
            receiveBuffer.clear();
            SocketAddress receive = receiveChannel.receive(receiveBuffer);
            receiveBuffer.flip();
            System.out.println(receive.toString());
            System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
        }
    }

    public void connectDatagram() throws IOException {   //连接
        DatagramChannel connectChannel = DatagramChannel.open();

        connectChannel.bind(new InetSocketAddress(9999));

        connectChannel.connect(new InetSocketAddress("127.0.0.1", 9999)); //连接

        connectChannel.write(ByteBuffer.wrap("hello world".getBytes("UTF-8")));

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        while (true) {
            readBuffer.clear();
            connectChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(Charset.forName("UTF-8").decode(readBuffer));
        }
    }
}
