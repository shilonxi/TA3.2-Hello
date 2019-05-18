package android.shilon.server;

import java.net.*;
import java.io.*;

public class TCPServer
{
    public static void main(String[] args)
            throws IOException
    {
        ServerSocket serverSocket=new ServerSocket(30000);
        //创建一个ServerSocket，用于监听客户端Socket的连接请求，端口号避免与通用端口号冲突
        while (true)
        {
            Socket socket=serverSocket.accept();
            //一旦接收到请求，就对应产生一个Socket
            PrintStream printStream=new PrintStream(socket.getOutputStream());
            //将Socket对应的输出流包装成打印流
            printStream.println("Hello!");
            //IO操作，内容自选
            printStream.close();
            socket.close();
            //关闭输出流和Socket
        }
        //采用循环不断接受请求
    } //使用throws声明抛出异常
}


