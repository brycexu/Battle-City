/**
作者：Bryce Xu
时间：2019/4/15
功能：坦克大战
1.画出坦克
2.实现监听
（a.实现接口KeyListener,MouseListener,ActionListener,WindowListener）
（b.把接口处理方法重新编写override）
（c.在事件源上注册监听）
（d.事件传递是靠事件对象）
3.发射单发子弹
4.发射连发子弹(最多5颗)
5.敌方坦克被击中后消失(爆炸的效果)
6.玩家坦克被击中后爆炸
7.防止敌人坦克重叠运动
    决定把判断是否碰撞的函数写到EnemyTank里面去
8.可以分关
    做一个开始的Pannel
    闪烁效果
9.可以暂停和继续
    当用户点击暂停时,子弹和坦克速度设为0,并让坦克的方向不要发生变化
10.可以记录玩家的成绩
    用文件流
    单写一个记录类
    先完成保存共击毁了多少敌人坦克
    存盘退出,并可以恢复
11.java操作声音文件
**/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTankGame extends JFrame implements ActionListener
{
    //定义游戏面板
    MyPanel mp=null;
    //定义开始面板
    MyStartPanel msp=null;
    //作出我需要的菜单
    JMenuBar jmb=null;
    JMenu jm1=null;
    JMenu jm2=null;
      //开始游戏
    JMenuItem jmi01=null;
    JMenuItem jmi10=null;
    JMenuItem jmi11=null;
      //退出游戏
    JMenuItem jmi2=null;
      //存盘退出
    JMenuItem jmi3=null;
      //恢复
    JMenuItem jmi4=null;
    public static void main(String[] args)
    {
        MyTankGame mytankgame=new MyTankGame();
    }
    public MyTankGame()
    {
        //菜单
        jmb=new JMenuBar();
        jm1=new JMenu("Game");
        jm2=new JMenu("Start");
        jmi01=new JMenuItem("Easy");
        jmi01.addActionListener(this);
        jmi01.setActionCommand("easy");
        jmi10=new JMenuItem("Medium");
        jmi10.addActionListener(this);
        jmi10.setActionCommand("medium");
        jmi11=new JMenuItem("Hard");
        jmi11.addActionListener(this);
        jmi11.setActionCommand("hard");
        jmi2=new JMenuItem("Exit");
        jmi2.addActionListener(this);
        jmi2.setActionCommand("exitGame");
        jmi3=new JMenuItem("Save and quit");
        jmi3.addActionListener(this);
        jmi3.setActionCommand("saveGame");
        jmi4=new JMenuItem("Return");
        jmi4.addActionListener(this);
        jmi4.setActionCommand("returnGame");

        jm1.add(jm2);
        jm1.add(jmi2);
        jm1.add(jmi3);
        jm1.add(jmi4);
        jm2.add(jmi01);
        jm2.add(jmi10);
        jm2.add(jmi11);
        jmb.add(jm1);

        msp=new MyStartPanel();
        Thread t=new Thread(msp);
        t.start();

        this.setJMenuBar(jmb);
        this.add(msp);

        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("easy"))
        {
            mp=new MyPanel("easy");
            this.remove(msp);

            this.add(mp);
            this.addKeyListener(mp);
            //启动mp线程
            Thread t=new Thread(mp);
            t.start();
            //需要刷新
            this.setVisible(true);
        }
        else if(e.getActionCommand().equals("medium"))
        {
            mp=new MyPanel("medium");
            this.remove(msp);

            this.add(mp);
            this.addKeyListener(mp);
            //启动mp线程
            Thread t=new Thread(mp);
            t.start();
            //需要刷新
            this.setVisible(true);
        }
        else if(e.getActionCommand().equals("hard"))
        {
            mp=new MyPanel("hard");
            this.remove(msp);

            this.add(mp);
            this.addKeyListener(mp);
            //启动mp线程
            Thread t=new Thread(mp);
            t.start();
            //需要刷新
            this.setVisible(true);
        }
        else if(e.getActionCommand().equals("exitGame"))
        {
            //保存击毁敌人数量
            Recorder.keepRecording();
            System.exit(0);
        }
        else if(e.getActionCommand().equals("saveGame"))
        {
            //做工作
            //保存击毁敌人的数量和坐标方向
            Recorder.setEts(mp.ets);
            Recorder.SaveRecording();
            System.exit(0);
        }
        else if(e.getActionCommand().equals("returnGame"))
        {
            mp=new MyPanel("con");
            this.remove(msp);
            this.add(mp);
            this.addKeyListener(mp);
            //启动mp线程
            Thread t=new Thread(mp);
            t.start();
            //需要刷新
            this.setVisible(true);
        }
    }
}
