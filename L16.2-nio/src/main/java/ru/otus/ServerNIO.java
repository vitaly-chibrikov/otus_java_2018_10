package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class ServerNIO {
    private static Logger logger = LoggerFactory.getLogger(ServerNIO.class);

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        new ServerNIO().go();
    }

    private void go() throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(PORT));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            logger.info("waiting for client connection");
            if (selector.select() > 0) { //This method performs a blocking
                performIO(selector);
            }
        }
    }

    private void performIO(Selector selector) throws IOException {
        logger.info("something happened");
        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

        while (keys.hasNext()) {
            SelectionKey key = keys.next();
            if (key.isAcceptable()) {
                acceptConnection(key, selector);
            } else if (key.isReadable()) {
                readWriteClient(key);
            }

            keys.remove();
        }
    }

    private void acceptConnection(SelectionKey key, Selector selector) throws IOException {
        logger.info("accept client connection");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept(); //The socket channel for the new connection

        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void readWriteClient(SelectionKey selectionKey) throws IOException {
        logger.info("read from client");
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        ByteBuffer buffer = ByteBuffer.allocate(5);
        StringBuilder inputBuffer = new StringBuilder(100);

        while (socketChannel.read(buffer) > 0) {
            buffer.flip();
            String input = Charset.forName("UTF-8").decode(buffer).toString();
            logger.info("from client: {} ", input);

            buffer.flip();
            buffer.clear();
            inputBuffer.append(input);
        }

        String requestFromClient = inputBuffer.toString();
        logger.info("requestFromClient: {} ", requestFromClient);

        byte[] response = processClientRequest(requestFromClient).getBytes();
        for (byte b : response) {
            buffer.put(b);
            if (buffer.position() == buffer.limit()) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.flip();
                buffer.clear();
            }
        }
        if (buffer.hasRemaining()) {
            buffer.flip();
            socketChannel.write(buffer);
        }

        if ("stop\n".equals(requestFromClient)) {
            socketChannel.close();
        }
    }

    private String processClientRequest(String input) {
        return "echo:" + input;
    }


}
