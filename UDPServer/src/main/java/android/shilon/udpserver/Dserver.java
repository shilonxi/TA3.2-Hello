package android.shilon.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Dserver
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket=null;
        try
        {
            datagramSocket=new DatagramSocket(30000);
            //创建DatagramSocket
            while (true)
            {
                byte[] buf=new byte[1024];
                //定义每个数据报最大为1KB
                DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length);
                //创建准备接收数据的DatagramPacket
                datagramSocket.receive(datagramPacket);
                //此方法会一直阻塞，直到获取到数据
                System.out.println(new String(buf));
                //将接收到的数据转换成字符串后输出
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket!=null)
                datagramSocket.close();
        } //先捕获异常，再关闭资源
    }
}

