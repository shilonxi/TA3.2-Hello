package android.shilon.ms;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MSocket implements Runnable
{
    private static final String IP="230.0.0.1";
    //定义多点广播IP地址
    public static final int PORT=30000;
    //定义多点广播目的端口
    private static final int LEN=1024;
    //定义数据报的最大大小
    private MulticastSocket socket=null;
    //定义要使用的MulticastSocket实例
    private InetAddress mAddress=null;
    //定义多点广播地址
    byte[] inBuff=new byte[LEN];
    //定义接受字节数组
    private DatagramPacket inPacket=new DatagramPacket(inBuff,inBuff.length);
    //以指定字节数组创建接受用的DatagramPacket
    private DatagramPacket outPacket=null;
    //定义发送用的DatagramPacket

    public void init() throws IOException
    {
        try
        {
            socket=new MulticastSocket(PORT);
            //创建MulticastSocket，因为该对象要同时用于发送和接受，所以使用指定端口
            mAddress=InetAddress.getByName(IP);
            //初始化多点广播地址
            if (IP!="255.255.255.255")
                socket.joinGroup(mAddress);
            //加入指定的多点广播地址，特殊情况区分
            socket.setLoopbackMode(false);
            //设置发送的内容会被回送到自身
            outPacket=new DatagramPacket(new byte[0],0,mAddress,PORT);
            //初始化发送用的DatagramPacket
            new Thread(this).start();
            //启动线程接收，下面的另一条线程负责发送
            Scanner scanner=new Scanner(System.in);
            //创建键盘输入流
            while (scanner.hasNextLine())
            {
                byte[] buff=scanner.nextLine().getBytes("UTF-8");
                //将键盘输入作为输出内容
                outPacket.setData(buff);
                //设置发送用的DatagramPacket的内容
                socket.send(outPacket);
                //发送
            }
        } finally {
            socket.close();
        }
        //关闭资源
    }

    public void run()
    {
        try
        {
            while (true)
            {
                socket.receive(inPacket);
                //读取并放入
                System.out.println("MESSAGE:"+new String(inBuff));
                ////打印接受到的消息
                inBuff=new byte[LEN];
                inPacket=new DatagramPacket(inBuff,inBuff.length);
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
            try
            {
                if (socket!=null)
                {
                    if (IP!="255.255.255.255")
                        socket.leaveGroup(mAddress);
                    //离开多点广播地址
                    socket.close();
                    //关闭socket
                }
                System.exit(1);
                //非正常退出
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        //捕捉异常，并处理
    }

    public static void main(String[] args)
            throws IOException
    {
        new MSocket().init();
    }

}

