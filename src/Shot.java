/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

class Shot implements Runnable
{
    int x;
    int y;
    int direct;
    int speed=5;
    boolean isLive=true;
    public Shot(int x,int y,int direct)
    {
        this.x=x;
        this.y=y;
        this.direct=direct;
    }
    //子弹移动进程
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(50);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            switch (direct)
            {
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }
            //子弹何时死亡
            if(x<0||x>400||y<0||y>300)
            {
                this.isLive=false;
                break;
            }
        }
    }
}
