/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

import java.io.*;
import java.util.Vector;

class Recorder
{
    //记录每关有多少敌人
    private static int enNum;
    //设置我有多少可以用的人
    private static int myLife=3;
    //记录总共消灭了多少敌人
    private static int killEnemy=0;
    //记录地方坦克速度
    private static int enSpeed;
    //记录英雄坦克速度
    private static int heSpeed;
    //是否游戏失败
    public static boolean isGameOver = false;
    //是否游戏获胜
    public static boolean isWin = false;
    //从文件中恢复记录
    static Vector<Node> nodes=new Vector<Node>();

    private static FileWriter fw=null;
    private static BufferedWriter bw=null;
    private static FileReader fr=null;
    private static BufferedReader br=null;

    private static Vector<EnemyTank> ets=new Vector<EnemyTank>();

    //完成读取任务
    public Vector<Node> getNodes()
    {
        try {
            fr=new FileReader("Records/myRecordings.txt");
            br=new BufferedReader(fr);
            String n="";
            n=br.readLine();
            killEnemy=Integer.parseInt(n);
            while((n=br.readLine())!=null)
            {
                String[] xyz=n.split(" ");
                Node node=new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]),Integer.parseInt(xyz[3]));
                nodes.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    //保存击毁敌人的数量和坐标方向
    public static void SaveRecording()
    {
        try {
            fw=new FileWriter("Records/myRecordings.txt");
            bw=new BufferedWriter(fw);
            bw.write(killEnemy+"\r\n");
            for(int i=0;i<ets.size();i++)
            {
                EnemyTank et=ets.get(i);
                if(et.isLive)
                {
                    String location=et.x+" "+et.y+" "+et.direct+" "+et.speed;
                    bw.write(location+"\r\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //从文件中读取,记录
    public static void getRecording()
    {
        try {
            fr=new FileReader("Records/myRecordings.txt");
            br=new BufferedReader(fr);
            String n=br.readLine();
            killEnemy=Integer.parseInt(n);
            enNum = 8-killEnemy;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //把玩家击毁敌人坦克数量保存到文件中
    public static void keepRecording()
    {
        try {
            fw=new FileWriter("Records/myRecordings.txt");
            bw=new BufferedWriter(fw);
            bw.write(killEnemy+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int getEnNum() {
        return enNum;
    }
    public static void setEnNum(int enNum) {
        Recorder.enNum = enNum;
    }
    public static int getEnSpeed() {return enSpeed;}
    public static void setEnSpeed(int enSpeed) {Recorder.enSpeed = enSpeed;}
    public static int getHeSpeed() {return heSpeed;}
    public static void setHeSpeed(int heSpeed) {Recorder.heSpeed = heSpeed;}
    public static int getMyLife() {
        return myLife;
    }
    public static void setMyLife(int myLife) {
        Recorder.myLife = myLife;
    }
    public static int getKillEnemy() {
        return killEnemy;
    }
    public static void setKillEnemy(int killEnemy) {
        Recorder.killEnemy = killEnemy;
    }
    //减少敌人数量
    public static void reduceEnNum()
    {
        enNum--;
    }
    //减少自身生命
    public static void reduceLife() {myLife--;}
    //消灭敌人
    public static void killEnemies()
    {
        killEnemy+=1;
    }
    public static Vector<EnemyTank> getEts() {
        return ets;
    }
    public static void setEts(Vector<EnemyTank> ets) {
        Recorder.ets = ets;
    }
}
