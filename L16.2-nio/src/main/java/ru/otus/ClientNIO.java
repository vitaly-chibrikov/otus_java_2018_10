package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class ClientNIO {
    private static Logger logger = LoggerFactory.getLogger(ClientNIO.class);

    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        new Thread(() -> new ClientNIO().go("testData_1")).start();
        new Thread(() -> new ClientNIO().go("testData_2")).start();
    }

    private void go(String request) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            socketChannel.connect(new InetSocketAddress(HOST, PORT));

            logger.info("connecting to server");
            while (!socketChannel.finishConnect()) {
                logger.info("connection established");
            }
            send(socketChannel, request);
            sleep();
            logger.info("stop communication");
            send(socketChannel, "stop\n");
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }

    private void send(SocketChannel socketChannel, String request) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        buffer.put(request.getBytes());
        buffer.flip();
        logger.info("sending to server");
        socketChannel.write(buffer);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        while (true) {
            logger.info("waiting for response");
            if (selector.select() > 0) { //This method performs a blocking
                if (processServerResponse(selector)) {
                    return;
                }
            }
        }
    }

    private boolean processServerResponse(Selector selector) throws IOException {
        logger.info("something happened");
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
        while (selectedKeys.hasNext()) {
            SelectionKey key = selectedKeys.next();
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                int count = socketChannel.read(buffer);
                if (count > 0) {
                    buffer.flip();
                    String response = Charset.forName("UTF-8").decode(buffer).toString();
                    logger.info("response: " + response);
                    buffer.clear();
                    buffer.flip();
                    return true;
                }
            }
            selectedKeys.remove();
        }
        return false;
    }


    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

