/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

import java.util.Vector;

class Hero extends Tank
{
    //子弹集合
    Vector<Shot> ss=new Vector<Shot>();
    Shot s=null;
    int speed;

    public Hero(int x,int y)
    {
        super(x,y);
    }

    //发射子弹
    public void shotEnemy()
    {
        switch(this.direct)
        {
            case 0:
                //创建一颗子弹
                s=new Shot(x+9,y,0);
                //把子弹加入向量
                ss.add(s);
                break;
            case 1:
                s=new Shot(x+30,y+9,1);
                ss.add(s);
                break;
            case 2:
                s=new Shot(x+9,y+30,2);
                ss.add(s);
                break;
            case 3:
                s=new Shot(x,y+9,3);
                ss.add(s);
                break;
        }
        //启动子弹线程
        Thread t=new Thread(s);
        t.start();
    }

    public void moveUp()
    {
        y-=speed;
    }
    public void moveRight()
    {
        x+=speed;
    }
    public void moveDown()
    {
        y+=speed;
    }
    public void moveLeft()
    {
        x-=speed;
    }
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}