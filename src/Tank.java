/*
作者：Bryce Xu
时间：2019/4/15
功能：
*/

class Tank
{
    //横坐标
    int x=0;
    //纵坐标
    int y=0;

    //方向
    //0上1右2下3左
    int direct=0;
    //颜色
    int color=0;

    boolean isLive=true;


    public Tank(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
