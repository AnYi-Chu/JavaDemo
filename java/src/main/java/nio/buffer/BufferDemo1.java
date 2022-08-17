package nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo1 {
    public static void main(String[] args) throws IOException {

    }

    public static void read() throws IOException {  //缓存读
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\gaohao\\Desktop\\随笔\\1.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int read = channel.read(buffer);

        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        file.close();
    }

    public static void inOut() {
        IntBuffer buffer = IntBuffer.allocate(8);

        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2 * (i + 1);
            buffer.put(j);  //放入
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            int value = buffer.get();   //取出
            System.out.println(value);
        }
    }
}
