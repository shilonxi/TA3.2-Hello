package android.shilon.sthread;

public class SecondThread implements Runnable
{
    //定义实现类
    private int i;
    public void run()
    {
        for ( ;i<100;i++)
            System.out.println(Thread.currentThread().getName()+" "+i);
    }
    //重写方法 线程执行体
    public static void main(String[] args)
    {
        for (int i=0;i<100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
            if (i==30) {
                SecondThread secondThread=new SecondThread();
                //创建实例
                new Thread(secondThread,"thread 1").start();
                new Thread(secondThread,"thread 2").start();
                //以该实例作为Thread的target来创建Thread对象并启动
            }
        }
    }
}
