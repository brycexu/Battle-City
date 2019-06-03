/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

import java.util.Vector;

class EnemyTank extends Tank implements Runnable
{
    int bullet=0;
    int speed;
    //定义一个向量,可以访问到MyPanel上所有敌人的坦克
    Vector<EnemyTank> ets=new Vector<EnemyTank>();
    //定义一个向量,可以存放敌人的子弹
    Vector<Shot> ss=new Vector<Shot>();
    //敌人添加子弹,应当在刚刚创建坦克和敌人坦克子弹死亡后
    public EnemyTank(int x,int y)
    {
        super(x,y);
    }
    //得到MyPanel的敌人坦克
    public void setEts(Vector<EnemyTank> vv)
    {
        this.ets=vv;
    }
    public void run()
    {
        while(true)
        {
            int randomtime=(int)(Math.random()*30);
            for(int i=0;i<randomtime;i++)
            {
                switch (this.direct)
                {
                    case 0:
                        if(y>0 && !isTouchOtherEnemy())
                        {
                            y-=speed;
                        }
                        break;
                    case 1:
                        if(x<370 && !isTouchOtherEnemy())
                        {
                            x+=speed;
                        }
                        break;
                    case 2:
                        if(y<270 && !isTouchOtherEnemy())
                        {
                            y+=speed;
                        }
                        break;
                    case 3:
                        if(x>0 && !isTouchOtherEnemy())
                        {
                            x-=speed;
                        }
                        break;
                }
                try
                {
                    Thread.sleep(50);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            //让坦克随机产生一个新的方向
            this.direct=(int)(Math.random()*4);
            //判断敌人坦克是否死亡
            if(this.isLive==false)
            {
                break;
            }
        }
    }
    //判断此敌人坦克是否和其它敌人坦克碰撞
    public boolean isTouchOtherEnemy()
    {
        boolean b=false;
        switch (this.direct)
        {
            case 0:
                for(int i=0;i<ets.size();i++)
                {
                    EnemyTank et=ets.get(i);
                    if(et!=this)
                    {
                        //如果敌人的方向是向下或者向上
                        if(et.direct==0||et.direct==2)
                        {
                            if(this.x>=et.x && this.x<=et.x+20 && this.y>=et.y && this.y<=et.y+30)
                            {
                                return true;
                            }
                            if(this.x+20>=et.x && this.x+20<=et.x+20 && this.y>=et.y && this.y<=et.y+30)
                            {
                                return true;
                            }
                        }
                        //如果敌人的方向是向左或者向右
                        if(et.direct==3||et.direct==1)
                        {
                            if(this.x>=et.x && this.x<=et.x+30 && this.y>=et.y && this.y<=et.y+20)
                            {
                                return true;
                            }
                            if(this.x+20>=et.x && this.x+20<=et.x+30 && this.y>=et.y && this.y<=et.y+20)
                            {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int i=0;i<ets.size();i++)
                {
                    EnemyTank et=ets.get(i);
                    if(et!=this)
                    {
                        //如果敌人的方向是向下或者向上
                        if(et.direct==0||et.direct==2)
                        {
                            if(this.x+30>=et.x && this.x+30<=et.x+20 && this.y>=et.y && this.y<=et.y+30)
                            {
                                return true;
                            }
                            if(this.x+30>=et.x && this.x+30<=et.x+20 && this.y+20>=et.y && this.y+20<=et.y+30)
                            {
                                return true;
                            }
                        }
                        //如果敌人的方向是向左或者向右
                        if(et.direct==3||et.direct==1)
                        {
                            if(this.x+30>=et.x && this.x+30<=et.x+30 && this.y>=et.y && this.y<=et.y+20)
                            {
                                return true;
                            }
                            if(this.x+30>=et.x && this.x+30<=et.x+30 && this.y+20>=et.y && this.y+20<=et.y+20)
                            {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i=0;i<ets.size();i++)
                {
                    EnemyTank et=ets.get(i);
                    if(et!=this)
                    {
                        //如果敌人的方向是向下或者向上
                        if(et.direct==0||et.direct==2)
                        {
                            if(this.x>=et.x && this.x<=et.x+20 && this.y+30>=et.y && this.y+30<=et.y+30)
                            {
                                return true;
                            }
                            if(this.x+20>=et.x && this.x+20<=et.x+20 && this.y+30>=et.y && this.y+30<=et.y+30)
                            {
                                return true;
                            }
                        }
                        //如果敌人的方向是向左或者向右
                        if(et.direct==3||et.direct==1)
                        {
                            if(this.x>=et.x && this.x<=et.x+30 && this.y+30>=et.y && this.y+30<=et.y+20)
                            {
                                return true;
                            }
                            if(this.x+20>=et.x && this.x+20<=et.x+30 && this.y+30>=et.y && this.y+30<=et.y+20)
                            {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i=0;i<ets.size();i++)
                {
                    EnemyTank et=ets.get(i);
                    if(et!=this)
                    {
                        //如果敌人的方向是向下或者向上
                        if(et.direct==0||et.direct==2)
                        {
                            if(this.x>=et.x && this.x<=et.x+20 && this.y>=et.y && this.y<=et.y+30)
                            {
                                return true;
                            }
                            if(this.x>=et.x && this.x<=et.x+20 && this.y+20>=et.y && this.y+20<=et.y+30)
                            {
                                return true;
                            }
                        }
                        //如果敌人的方向是向左或者向右
                        if(et.direct==3||et.direct==1)
                        {
                            if(this.x>=et.x && this.x<=et.x+30 && this.y>=et.y && this.y<=et.y+20)
                            {
                                return true;
                            }
                            if(this.x>=et.x && this.x<=et.x+30 && this.y+20>=et.y && this.y+20<=et.y+20)
                            {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return b;
    }
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}