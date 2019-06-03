/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

class Bomb {
    int x;
    int y;
    int life=9;
    boolean isLive=true;
    public Bomb(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public void lifeDown()
    {
        if(life>0)
        {
            life--;
        }
        else
        {
            this.isLive=false;
        }
    }
}
