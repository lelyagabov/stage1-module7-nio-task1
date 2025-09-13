package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder content = new StringBuilder();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer bytesBuffer = ByteBuffer.allocate(1024);
            int bytesRead;

            while ((bytesRead = channel.read(bytesBuffer)) != -1) {
                bytesBuffer.flip();

                while (bytesBuffer.hasRemaining()) {
                    content.append((char) bytesBuffer.get());
                }

                bytesBuffer.clear();
            }
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] words = content.toString().split("\\n|\\s+");
        return new Profile(words[1], Integer.parseInt(words[3]), words[5], Long.valueOf(words[7]));
    }
}
