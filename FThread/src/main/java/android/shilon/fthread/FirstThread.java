package android.shilon.fthread;

public class FirstThread extends Thread
{
    //定义子类
    private int i;
    public void run()
    {
        for ( ;i<100;i++)
            System.out.println(getName()+" "+i);
    }
    //重写方法 线程执行体
    public static void main(String[] args)
    {
        for (int i=0;i<100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
            if (i==30) {
                new FirstThread().start();
                //创建实例并启动
                new FirstThread().start();
            }
        }
    }
}
