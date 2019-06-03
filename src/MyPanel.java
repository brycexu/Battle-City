/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

class MyPanel extends JPanel implements KeyListener,Runnable
{
    //定义一个我的坦克
    Hero hero=null;
    //定义敌人的坦克组
    Vector<EnemyTank> ets=new Vector<EnemyTank>();
    Vector<Node> nodes=new Vector<Node>();
    //定义炸弹集合
    Vector<Bomb> bombs=new Vector<Bomb>();
    //定义三张图片,组成一颗炸弹
    Image image1=null;
    Image image2=null;
    Image image3=null;
    int times;
    //最多射击子弹数
    int maxShot;
    //构造函数
    public MyPanel(String flag)
    {
        //初始化玩家坦克
        hero=new Hero(200,200);
        hero.setColor(0);
        //初始化敌方坦克
        if(flag.equals("easy"))
        {
            hero.setSpeed(5);
            maxShot = 8;
            int enSize = 5;
            Recorder.setEnNum(5);
            for (int i = 0; i < enSize; i++)
            {
                EnemyTank et = new EnemyTank((i + 1) * 30, 50);
                et.setColor(1);
                et.setSpeed(2);
                int initialdirect = (int) (Math.random() * 4);
                et.setDirect(initialdirect);
                //将MyPanel的敌人坦克向量交给该敌人坦克
                et.setEts(ets);
                //启动敌人坦克
                Thread t = new Thread(et);
                t.start();
                //给敌人坦克添加子弹
                Shot s = new Shot(et.x + 10, et.y + 30, initialdirect);
                et.ss.add(s);
                Thread t2 = new Thread(s);
                t2.start();
                ets.add(et);
            }
        }
        else if(flag.equals("medium"))
        {
            hero.setSpeed(4);
            maxShot = 6;
            int enSize = 8;
            Recorder.setEnNum(8);
            for (int i = 0; i < enSize; i++)
            {
                EnemyTank et = new EnemyTank((i + 1) * 30, 50);
                et.setColor(1);
                et.setSpeed(3);
                int initialdirect = (int) (Math.random() * 4);
                et.setDirect(initialdirect);
                //将MyPanel的敌人坦克向量交给该敌人坦克
                et.setEts(ets);
                //启动敌人坦克
                Thread t = new Thread(et);
                t.start();
                //给敌人坦克添加子弹
                Shot s = new Shot(et.x + 10, et.y + 30, initialdirect);
                et.ss.add(s);
                Thread t2 = new Thread(s);
                t2.start();
                ets.add(et);
            }
        }
        else if(flag.equals("hard"))
        {
            hero.setSpeed(3);
            maxShot = 4;
            int enSize = 12;
            Recorder.setEnNum(8);
            for (int i = 0; i < enSize; i++)
            {
                EnemyTank et = new EnemyTank((i + 1) * 30, 50);
                et.setColor(1);
                et.setSpeed(3);
                int initialdirect = (int) (Math.random() * 4);
                et.setDirect(initialdirect);
                //将MyPanel的敌人坦克向量交给该敌人坦克
                et.setEts(ets);
                //启动敌人坦克
                Thread t = new Thread(et);
                t.start();
                //给敌人坦克添加子弹
                Shot s = new Shot(et.x + 10, et.y + 30, initialdirect);
                et.ss.add(s);
                Thread t2 = new Thread(s);
                t2.start();
                ets.add(et);
            }
        }
        else
        {
            //恢复记录
            Recorder.getRecording();
            nodes=new Recorder().getNodes();
            for (int i = 0; i < nodes.size(); i++)
            {
                Node node=nodes.get(i);
                EnemyTank et = new EnemyTank(node.x, node.y);
                et.setSpeed(node.speed);
                et.setColor(1);
                et.setDirect(node.direct);
                //将MyPanel的敌人坦克向量交给该敌人坦克
                et.setEts(ets);
                //启动敌人坦克
                Thread t = new Thread(et);
                t.start();
                //给敌人坦克添加子弹
                Shot s = new Shot(et.x + 10, et.y + 30, node.direct);
                et.ss.add(s);
                Thread t2 = new Thread(s);
                t2.start();
                ets.add(et);
            }
            hero.setSpeed(4);
            maxShot = 6;
        }
        //初始化图片
        try {
            image1=ImageIO.read(new File("Materials/stage1.jpg"));
            image2=ImageIO.read(new File("Materials/stage2.jpg"));
            image3=ImageIO.read(new File("Materials/stage3.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showInfo(Graphics g)
    {
        //画出提示信息坦克
        this.drawTank(80,330,g,0,1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getEnNum()+"",110,350);
        this.drawTank(150,330,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getMyLife()+"",180,350);
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font f=new Font("Times New Roman",Font.BOLD,20);
        g.setFont(f);
        g.drawString("Your score:",420,40);
        this.drawTank(420,60,g,0,1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getKillEnemy() * 10+"",460,80);
    }
    public void paint(Graphics g)
    {
        if(!Recorder.isGameOver && !Recorder.isWin)
        {
            super.paint(g);
            g.fillRect(0,0,400,300);
            //画出提示信息
            this.showInfo(g);
            //画出自己坦克
            if(hero.isLive)
            {
                this.drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),hero.getColor());
            }
            //从ss中取出每颗子弹,并画出
            for(int i=0;i<hero.ss.size();i++)
            {
                Shot myShot=hero.ss.get(i);
                //画出一颗子弹
                if(myShot!=null&&myShot.isLive==true)
                {
                    g.draw3DRect(myShot.x,myShot.y,1,1,false);
                }
                //如果子弹已经死亡,从向量中删除该子弹
                if(!myShot.isLive)
                {
                    hero.ss.remove(i);
                }
            }
            if(!hero.isLive)
            {
                hero.setX(200);
                hero.setY(200);
                hero.isLive = true;
            }
            //画出炸弹
            for(int i=0;i<bombs.size();i++)
            {
                Bomb b=bombs.get(i);
                if(b.life>6)
                {
                    g.drawImage(image1,b.x,b.y,30,30,this);
                }
                else if(b.life>3)
                {
                    g.drawImage(image2,b.x,b.y,30,30,this);
                }
                else
                {
                    g.drawImage(image3,b.x,b.y,30,30,this);
                }
                b.lifeDown();
                if(!b.isLive)
                {
                    bombs.remove(b);
                }
            }
            //画出敌人坦克
            for(int i=0;i<ets.size();i++)
            {
                EnemyTank et=ets.get(i);
                if(et.isLive)
                {
                    this.drawTank(et.getX(),et.getY(),g,et.getDirect(),et.getColor());
                    for(int j=0;j<et.ss.size();j++)
                    {
                        Shot enemyShot=et.ss.get(j);
                        if(enemyShot.isLive&&enemyShot!=null)
                        {
                            g.draw3DRect(enemyShot.x,enemyShot.y,1,1,false);
                        }
                        else if(!enemyShot.isLive)
                        {
                            et.ss.remove(j);
                        }
                    }
                }
                if(!et.isLive)
                {
                    ets.remove(i);
                }
            }
        }
        else if(Recorder.isWin)
        {
            super.paint(g);
            g.fillRect(0,0,400,300);
            //提示信息
            if(times%2==0)
            {
                g.setColor(Color.orange);
                Font myFont=new Font("Times New Roman",Font.BOLD,30);
                g.setFont(myFont);
                g.drawString("You Won!",120,150);
            }
        }
        else if(Recorder.isGameOver)
        {
            super.paint(g);
            g.fillRect(0,0,400,300);
            //提示信息
            if(times%2==0)
            {
                g.setColor(Color.orange);
                Font myFont=new Font("Times New Roman",Font.BOLD,30);
                g.setFont(myFont);
                g.drawString("Game Over!",120,150);
            }
        }
    }
    //写个函数专门判断子弹是否击中敌人坦克
    public boolean hitTank(Shot s, Tank et)
    {
        boolean bool=false;
        //判断该坦克的方向
        switch (et.direct)
        {
            //上或下
            case 0:
            case 2:
                if(s.x>et.x && s.x<et.x+20 && s.y>et.y && s.y<et.y+30)
                {
                    //子弹死亡
                    s.isLive=false;
                    //敌人坦克死亡
                    et.isLive=false;
                    bool=true;
                    //创建一个炸弹,加入集合
                    Bomb b=new Bomb(et.getX(),et.getY());
                    bombs.add(b);
                }
                break;
            case 1:
            case 3:
                if(s.x>et.x && s.x<et.x+30 && s.y>et.y && s.y<et.y+20)
                {
                    s.isLive=false;
                    et.isLive=false;
                    bool=true;
                    Bomb b=new Bomb(et.getX(),et.getY());
                    bombs.add(b);
                }
                break;
        }
        return bool;
    }
    //画坦克
    public void drawTank(int x,int y,Graphics g,int direct,int type)
    {
        //坦克类型
        switch (type)
        {
            //玩家坦克
            case 0:
                g.setColor(Color.ORANGE);
                break;
            //敌方坦克
            case 1:
                g.setColor(Color.CYAN);
                break;
        }
        //判断方向
        switch (direct)
        {
            //向上
            case 0:
                //1.左边的矩形
                g.fill3DRect(x,y,5,30,false);
                //2.右边矩形
                g.fill3DRect(x+15,y,5,30,false);
                //3.中间矩形
                g.fill3DRect(x+5,y+5,10,20,false);
                //4.中间圆形
                g.fillOval(x+5,y+10,10,10);
                //5.中间线
                g.drawLine(x+10,y,x+10,y+12);
                break;
            //向右
            case 1:
                //1.上面的矩形
                g.fill3DRect(x,y,30,5,false);
                //2.下面的矩形
                g.fill3DRect(x,y+15,30,5,false);
                //3.中间的矩形
                g.fill3DRect(x+5,y+5,20,10,false);
                //4.中间的圆形
                g.fillOval(x+10,y+5,10,10);
                //5.中间的线
                g.drawLine(x+15,y+10,x+30,y+10);
                break;
            //向下
            case 2:
                //1.左边的矩形
                g.fill3DRect(x,y,5,30,false);
                //2.右边矩形
                g.fill3DRect(x+15,y,5,30,false);
                //3.中间矩形
                g.fill3DRect(x+5,y+5,10,20,false);
                //4.中间圆形
                g.fillOval(x+5,y+10,10,10);
                //5.中间线
                g.drawLine(x+10,y+12,x+10,y+30);
                break;
            //向左
            case 3:
                //1.上面的矩形
                g.fill3DRect(x,y,30,5,false);
                //2.下面的矩形
                g.fill3DRect(x,y+15,30,5,false);
                //3.中间的矩形
                g.fill3DRect(x+5,y+5,20,10,false);
                //4.中间的圆形
                g.fillOval(x+10,y+5,10,10);
                //5.中间的线
                g.drawLine(x+15,y+10,x,y+10);
                break;
        }
    }
    //判断敌人坦克是否被击中
    public void hitEnemyTank()
    {
        //判断是否击中
        for(int i=0;i<hero.ss.size();i++)
        {
            Shot myShot=hero.ss.get(i);
            if(myShot.isLive)
            {
                for(int j=0;j<ets.size();j++)
                {
                    EnemyTank et=ets.get(j);
                    if(et.isLive)
                    {
                        if(this.hitTank(myShot,et))
                        {
                            Recorder.reduceEnNum();
                            Recorder.killEnemies();
                        }
                    }
                }
            }
        }
    }
    //判断玩家坦克是否被击中
    public void hitMe()
    {
        for(int i=0;i<this.ets.size();i++)
        {
            EnemyTank et=ets.get(i);
            for(int j=0;j<et.ss.size();j++)
            {
                Shot enemyShot=et.ss.get(j);
                if(hero.isLive)
                {
                    if(this.hitTank(enemyShot,hero))
                    {
                        Recorder.reduceLife();
                        if(Recorder.getMyLife() == 0)
                        {
                            Recorder.isGameOver = true;
                        }
                    }
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    //键盘按下的响应
    @Override
    public void keyPressed(KeyEvent e)
    {
        //System.out.println(e.getKeyCode());
        //上
        if(e.getKeyCode()==38)
        {
            this.hero.setDirect(0);
            this.hero.moveUp();
        }
        //下
        else if(e.getKeyCode()==40)
        {
            this.hero.setDirect(2);
            this.hero.moveDown();
        }
        //左
        else if(e.getKeyCode()==37)
        {
            this.hero.setDirect(3);
            this.hero.moveLeft();
        }
        //右
        else if(e.getKeyCode()==39)
        {
            this.hero.setDirect(1);
            this.hero.moveRight();
        }
        //开火
        if(e.getKeyCode()==32)
        {
            //只能一次发射5颗子弹
            if(hero.ss.size()<=maxShot-1)
            {
                this.hero.shotEnemy();
            }
        }
        //重新绘制
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void run()
    {
        //每隔100ms重画
        while(!Recorder.isGameOver && !Recorder.isWin)
        {
            if(ets.size() == 0)
                Recorder.isWin = true;
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            this.hitEnemyTank();
            this.hitMe();
            //判断是否需要给坦克加入新的子弹
            for(int i=0;i<ets.size();i++)
            {
                EnemyTank et=ets.get(i);
                if(et.isLive)
                {
                    et.bullet++;
                    if(et.bullet%30==0)
                    {
                        et.bullet=0;
                        Shot s=null;
                        switch(et.direct)
                        {
                            case 0:
                                //创建一颗子弹
                                s=new Shot(et.x+9,et.y,0);
                                //把子弹加入向量
                                et.ss.add(s);
                                break;
                            case 1:
                                s=new Shot(et.x+30,et.y+9,1);
                                et.ss.add(s);
                                break;
                            case 2:
                                s=new Shot(et.x+9,et.y+30,2);
                                et.ss.add(s);
                                break;
                            case 3:
                                s=new Shot(et.x,et.y+9,3);
                                et.ss.add(s);
                                break;
                        }
                        Thread t=new Thread(s);
                        t.start();
                    }

                }
            }
            this.repaint();
        }
        while(Recorder.isWin)
        {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.repaint();
            times++;
        }
        while(Recorder.isGameOver)
        {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.repaint();
            times++;
        }
    }
}
