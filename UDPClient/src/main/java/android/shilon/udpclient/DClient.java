package android.shilon.udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DClient
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket=null;
        try
        {
            datagramSocket=new DatagramSocket();
            //创建DatagramSocket
            Scanner scanner=new Scanner(System.in);
            //创建键盘输入流
            while (scanner.hasNextLine())
            {
                byte[] buf=scanner.nextLine().getBytes("UTF-8");
                //将一行输入字符串转换成数组
                DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),30000);
                //创建发送用的DatagramPacket
                datagramSocket.send(datagramPacket);
                //发送数据报
            }
            //不断读取键盘输入
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket!=null)
                datagramSocket.close();
        } //先捕获异常，再关闭资源
    }
}
