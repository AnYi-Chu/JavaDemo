package nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo { //通道读

    private static void read() throws IOException { //通道读
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\gaohao\\Desktop\\随笔\\1.txt", "rw");   //文件定位
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);    //创建缓冲区

        int read = channel.read(buffer);
        while (read != -1) {
            System.out.println("读取：" + read);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        file.close();
    }

    private static void write() throws IOException {    //通道写
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\gaohao\\Desktop\\随笔\\1.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.clear();

        String newDate = "江生浩荡";
        buffer.put(newDate.getBytes());
        buffer.flip();

        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

        channel.close();
    }

    private static void transport() throws IOException {    //通道传输
        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\gaohao\\Desktop\\随笔\\1.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("C:\\Users\\gaohao\\Desktop\\随笔\\2.txt", "rw");
        FileChannel toChannel = bFile.getChannel();

        long position = 0;
        long size = fromChannel.size();
//        toChannel.transferFrom(fromChannel, position, size);
        fromChannel.transferTo(position, size, toChannel);

        aFile.close();
        bFile.close();
    }
}
