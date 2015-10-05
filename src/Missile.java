import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 * data members and methods for missile class
 * @author KevinD
 *
 */
public class Missile{
	/*
	 * point of missile
	 */
	private Point p;
	/*
	 * position of missile
	 */
	private int dx, dy, size;
	/**
	 * color of missile
	 */
	private Color color;
	//private int speed = 5;
	private int x5 = 0, y5 = 0;
	/*
	 * updated position of bullet
	 */
	private int tX, tY,z=5,c2=0;
	/**
	 * mouse position
	 */
	private int mX, mY, addX, addY;
	/**
	 * counter
	 */
	private int counter = 0, lives = 0;
	/**
	 * boolean for brawing
	 */
	boolean drawB, stop;
	private int killTank = -1;
	private int n;
	/**
	 * Missile constructor 
	 * @param x1
	 * @param y1
	 * @param mx
	 * @param my
	 * @param c
	 * @param number
	 */
public Missile(int x1, int y1, int mx, int my, Color c, int number){
	p = new Point(x1, y1);
	dx = dy;
	//speed = 5;
	p = new Point(x1, y1);
	dx = dy;
	//speed = 5;
	n = number;
	//speed = 5;
	//size = radius;
	color = c;
	tX = x1; // tip of turret
	tY = y1; //tip of turret
	mX = mx;
	mY = my;
	drawB = true;
}
/**
 * returns getNumber
 * @return
 */
public int getNumber(){
	return n;
}
/**
 * returns what tank was hit
 * @param x
 */
	public void setKillTank(int x) {
		killTank = x;
	}
	/**
	 * draws missile
	 * @param g
	 * @param enemy enemy to check collision
	 * @param i
	 */
	public void draw(Graphics g, ArrayList<Tank> enemy, int i){
		g.setColor(Color.black);
		if(drawB == true) {
		g.fillRect(tX, tY, 10, 10);
		Rectangle rect = new Rectangle(tX,tY,10,10);
		for (int x = 0; x < enemy.size(); x++) {
			if (rect.intersects(new Rectangle(enemy.get(x).getTX(),enemy.get(x).getTY(),35,35))) {
				//System.out.println("I GOT HIT!");
				killTank = x;
				c2++;
				//n = killTank;
			}		
			
		}
		} else {
			g.fillRect(-50, -50, 0, 0);
		}
		//tX = tX + addX;
		//tY = tY + addY;
		//while(new Rectangle(tX,tY,10,10).intersects)
		if(i == 0){
			this.move(g);
		}
		if (i == 1) {
			this.toPlayer(g, enemy.get(enemy.size()-1));
		}
		}
	/**
	 * returns killTank
	 * @return
	 */
	public int killTank() {
		return killTank;
	}
	/**
	 * returns location of missile x
	 * @return
	 */
	public int getBullX() {
		return tX;
	}
	/**
	 * returnsn location of missile y
	 * @return
	 */
	public int getBullY(){
		return tY;
	}
	/**
	 * moves missile
	 * @param g
	 */
	public void move(Graphics g) {
	//	g.setColor(Color.gray);
	//	g.drawRect(tX, tY, 10, 10);		
		g.setColor(Color.black);
		try {
		int dx = mX  - tX; // position - tip
		int dy =  mY - tY;
		int magnitude = (int) Math.sqrt(dx*dx  + dy*dy);
		if(magnitude < 50) {
			drawB = false;
		}
		int newX = dx * 20 / magnitude;
		int newY = dy * 20 / magnitude;
		tX = tX +newX;
		tY = tY+newY;
		} catch(Exception e) {
			drawB = false;
		}
		//counter++;
			
		//tX+=5;
		//tY+=5;
		}
	/**
	 * shifts bullet to tank specified
	 * @param g
	 * @param t
	 */
	public void toPlayer(Graphics g, Tank t) {
	//	g.setColor(Color.gray);
	//	g.drawRect(tX, tY, 10, 10);		
		g.setColor(Color.black);
		try {
			if(counter == 0){
				x5 = t.getTX();
				y5 = t.getTY();
				counter++;
			}
		int dx = x5  - tX; // position - tip
		int dy =  y5 - tY;
		int magnitude = (int) Math.sqrt(dx*dx  + dy*dy);
		if(magnitude < 50) {
			drawB = false;
		}
		int newX = dx * 20 / magnitude;
		int newY = dy * 20 / magnitude;
		tX = tX +newX;
		tY = tY+newY;
		} catch(Exception e) {
			drawB = false;
		}
		//counter++;
			
		//tX+=5;
		//tY+=5;
		}
	}
	
		//}