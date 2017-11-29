package Thread0830;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

/**
 * 小球类
 * @author thinkpad
 *
 */
public class Ball {
	private Graphics g;
	private JFrame jf;
	private int x,y,radius;//圆心坐标与半径
	private int vx,vy;//小球运动速度
	private Color color;
	private ArrayList<Ball> list;
	
	public Ball(JFrame jf,ArrayList<Ball> list){
		this.jf=jf;
		this.list=list;
		g=jf.getGraphics();
		//随机获取小球属性,初始化
		Random rd=new Random();
		vx=rd.nextInt(3)+1;
		vy=rd.nextInt(3)+1;
		x=rd.nextInt(500)+100;
		y=rd.nextInt(500)+100;
		radius=rd.nextInt(20)+10;
		color=new Color(rd.nextInt(256),rd.nextInt(256),rd.nextInt(256));
	}
	
	public void sport(){
		x+=vx;
		y+=vy;
		//小球出界处理
		if(x>=jf.getWidth()-radius){
			vx=-Math.abs(vx);
		}
		if(x<=radius){
			vx=Math.abs(vx);
		}
		if(y>=jf.getHeight()-radius){
			vy=-Math.abs(vy);
		}
		if(y<=100+radius){
			vy=Math.abs(vy);
		}
		//碰撞简单处理
		for(int i=0;i<list.size();i++){
			Ball mh=list.get(i);
			//判断小球是不是自己
			if(this==mh){
				continue;
			}
			int xx=Math.abs(this.x-mh.x);
			int yy=Math.abs(this.y-mh.y);
			int center=(int) Math.sqrt(xx*xx+yy*yy);//圆心距离
			
			if(center<=this.radius+mh.radius){	
				int temp=vx;//交换速度大小与方向
				vx=mh.vx;
				mh.vx=temp;
				
				int tem=vy;
				vy=mh.vy;
				mh.vy=tem;
			}
		}
	}
	
	public void draw(){
		//画圆
		g.setColor(color);
		g.fillOval(x-radius, y-radius,2*radius, 2*radius);
		//使小球变得立体,x将光点偏移
		int xx=x;
		for(int r=radius, i=1;r>1;r--,i++,xx--){
			Color newcolor=getcolor(i*7);
			g.setColor(newcolor);
			g.fillOval(xx-r, y-r,2*r, 2*r);
		}
	}
	
	private Color getcolor(int inc){
		int red=color.getRed()+inc;
		int bule=color.getBlue()+inc;
		int green=color.getGreen()+inc;
		int i=255;//中心的颜色
		if(red>=i)
			red=i;
		if(bule>=i)
			bule=i;
		if(green>=i)
			green=i;
		return new Color(red,bule,green);
	}
	/**
	 * 清除小球的痕迹
	 */
	public void clear(){
//		g.clearRect(0, 0,jf.getWidth(), jf.getHeight());//擦除屏幕
		g.setColor(jf.getBackground());
		g.fillOval(x-radius, y-radius,2*radius, 2*radius);//只清除上次所画的圆
	}
}
