package android.shilon.client;

import java.net.*;
import java.io.*;

public class TCPClient
{
    public static void main(String[] args)
            throws IOException
    {
        Socket socket=new Socket("127.0.0.1",30000);
        //使用Socket建立与本机IP，指定端口的连接
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //将Socket对应的输入流转换成字符输入流再包装成缓冲流
        String line=bufferedReader.readLine();
        //一次读取一行内容
        System.out.println("come from Server:"+line);
        //IO操作
        bufferedReader.close();
        socket.close();
        //关闭输入流和Socket
    } //使用throws声明抛出异常
}

