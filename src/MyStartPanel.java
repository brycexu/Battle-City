/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

import javax.swing.*;
import java.awt.*;

class MyStartPanel extends JPanel implements Runnable
{
    int times=0;
    public void paint(Graphics g)
    {
        super.paint(g);
        g.fillRect(0,0,400,300);
        //提示信息
        if(times%2==0)
        {
            g.setColor(Color.orange);
            Font myFont=new Font("Times New Roman",Font.BOLD,30);
            g.setFont(myFont);
            g.drawString("Battle City",120,150);
        }
    }
    public void run()
    {
        while(true)
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
