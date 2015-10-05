import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;
/**
 * data members and methods for tank
 * @author KevinD
 *
 */
public class Tank extends JPanel {
	// private Graphics g;
	private int x2 = 60, y2 = 120, rate = 0, counter = 0, shoot = 0, n;
	private Color c;
	/**
	 * missile for tank
	 */
	private Missile m;
	/**
	 * turret point
	 */
	private Point turret;
	/**
	 * array of bullets
	 */
	private ArrayList<Missile> bullet = new ArrayList<Missile>();
	/**
	 * array of rectangles for collision handling
	 */
	private ArrayList<Rectangle> rectangle = new ArrayList<Rectangle>();
	/**
	 * lives
	 */
	private int lives = 10, hit = 0;
	/**
	 * booleans for displaying on panel
	 */
	private boolean check,stop,shootBool = false, show = true, display = true;
	/**
	 * tank constructor
	 * @param p point
	 * @param color color
	 * @param direction direction
	 * @param missile missile
 	 * @param barrel barrel
	 * @param number number
	 */
	public Tank(Point p,Color color, int direction, Missile missile, Point barrel, int number) {
		// paintComponent(g);
		x2 = p.x;
		y2 = p.y;
		c = color;
		m = missile;
		n = number;

	}
	/**
	 * sets the life of tank
	 * @param x
	 */
	public void setLife(int x) {
		lives = x;
	}
	 /**
	  * sets display of tank 
	  * @param x
	  */
	public void setDisplay (boolean x) {
		display = x;
	}
	/**
	 * gets the display boolean of tank
	 * @return
	 */
	public boolean getDisplay() {
		return display;
	}
	/**
	 * returns number
	 * @return
	 */
	public int getNumber() {
		return n;
	}
	/**
	 * fires missile and adds to the array list
	 * @param x
	 * @param y
	 * @param mx
	 * @param my
	 */
	public void fireMissile(int x, int y, int mx, int my) {
		bullet.add(new Missile(x,y,mx,my, c,n));
	}
	/*
	 * returns bullet which is the array list of missiles
	 */
	public ArrayList<Missile> getBullet() {
		return bullet;
	}
/**
 * adds hit
 */
	public void addHit() {
		hit++;
	}
	/**
	 * Follows tank specfied(player tank) and moves accodirngly based on players movement
	 * @param g
	 * @param dir
	 * @param rect
	 * @param mx
	 * @param my
	 * @param t
	 * @param enemy
	 * @param s
	 */
		public void follow(Graphics g, int dir, ArrayList<Rectangle> rect, int mx, int my, Tank t, ArrayList<Tank> enemy, int s ) {
	
			//if(shootBool == true) {
			
			//}
			rate++;
			if(display == false) {
				
				//System.out.println(lives);
				lives--;
				//	System.out.println("I DIED");
					g.fillRect(-100,-100,0,0);
				}
			
			if(display == true) {
			// super.paintComponent(g);
			boolean check = false;
			boolean stop = false;
			int dx = mx - x2;
			int dy =  my - y2 ;
			int magnitude = (int) Math.sqrt(dx*dx  + dy*dy);
			int newX = dx * 50 / magnitude;
			int newY = dy * 50 / magnitude;
			if(s==1){
				if(rate % 50 == 0) {
				this.fireMissile(x2+newX+17, y2+newY+17, mx , my);
			
				}
				drawMissile(g,enemy,1);
			}
			g.setColor(c);
			g.fillRect(x2, y2, 35, 35);
			g.drawLine(x2 +  10, y2+ 10,x2+newX+17, y2+newY+17);
		
			if (counter == 0) {
				g.fillRect(x2, y2, 35, 35);
				counter++;
			}
			else if (dir == 2) {
				//System.out.println("UP");
				y2 = y2 - 5;
				Rectangle test = new Rectangle();
				test.setBounds(x2,y2,35,35);
				for(int x = 0; x < rect.size(); x++) {
					
					if(rect.get(x).intersects(test)== true) {
						check = true;
					}
					if(check == true) {
						stop = true;
					} 
				}
				if(stop == true){
					y2 = y2 + 5;
					g.fillRect(x2, y2, 35, 35);
				} else {
					g.fillRect(x2, y2, 35, 35);
				}
				dir  = 0;
			}
			else if (dir == 1) {
			//	//System.out.println("DOWN");
				y2 = y2 + 5;
				Rectangle test = new Rectangle();
				test.setBounds(x2,y2,35,35);
				for(int x = 0; x < rect.size(); x++) {
					
					if(rect.get(x).intersects(test)== true) {
						check = true;
					}
					if(check == true) {
						stop = true;
					} 
				}
				if(stop == true){
					y2 = y2 -5;
					g.fillRect(x2, y2, 35, 35);
				} else {
					g.fillRect(x2, y2, 35, 35);
				}
				dir = 0;
			}
			
			else if (dir == 4) {
				//System.out.println("LEFT");
				x2 = x2 - 5;
				Rectangle test = new Rectangle();
				test.setBounds(x2,y2,35,35);
				for(int x = 0; x < rect.size(); x++) {
					
					if(rect.get(x).intersects(test)== true) {
						check = true;
					}
					if(check == true) {
						stop = true;
					} 
				}
				if(stop == true){
					x2 = x2 +5;
					g.fillRect(x2, y2, 35, 35);
				} else {
					g.fillRect(x2, y2, 35, 35);
				}
				dir = 0;
			}

			
			else if (dir == 3) {
				//System.out.println("RIGHT");
				x2 = x2 + 5;
				Rectangle test = new Rectangle();
				test.setBounds(x2,y2,35,35);
				for(int x = 0; x < rect.size(); x++) {
					
					if(rect.get(x).intersects(test)== true) {
						check = true;
					}
					if(check == true) {
						stop = true;
					} 
				}
				if(stop == true){
					x2 = x2 -5;
					g.fillRect(x2, y2, 35, 35);
				} else {
					g.fillRect(x2, y2, 35, 35);
				}
			}
			dir = 0;
			}
		}
/**
 * moves tank up
 * @param g
 * @param rect
 * @param dir
 */
	public void goUp(Graphics g,ArrayList<Rectangle> rect, int dir) {
		rectangle = rect;
		y2 = y2 - 5;
		Rectangle test = new Rectangle();
		test.setBounds(x2,y2,35,35);
		testHit(new Point(x2,y2)); 
		if(stop == true){
			y2 = y2 + 5;
			g.fillRect(x2, y2, 35, 35);
		} else {
			g.fillRect(x2, y2, 35, 35);
		}
		dir = 0;
	}
	/**
	 * moves tank down
	 * @param g
	 * @param rect
	 * @param dir
	 */
	public void goDown(Graphics g,ArrayList<Rectangle> rect, int dir) {
		rectangle = rect;
		y2 = y2 + 5;
		Rectangle test = new Rectangle();
		test.setBounds(x2,y2,35,35);
		testHit(new Point(x2,y2)); 
		if(stop == true){
			y2 = y2 -5;
			g.fillRect(x2, y2, 35, 35);
		} else {
			g.fillRect(x2, y2, 35, 35);
		}
		dir = 0;
	}
	
	/**
	 * tests for collision
	 * @param p
	 */
	public void testHit(Point p) {
		Rectangle test = new Rectangle();
		test.setBounds(p.x,p.y,35,35);
		for(int x = 0; x < rectangle.size(); x++) {
		if(rectangle.get(x).intersects(test)== true) {
			check = true;
		}
		if(check == true) {
			stop = true;
		} 
	}
	}
	/**
	 * makes tank go left
	 * @param g
	 * @param rect
	 * @param dir
	 */
	public void goLeft(Graphics g,ArrayList<Rectangle> rect, int dir) {
		rectangle = rect;
		x2 = x2 - 5;
		Rectangle test = new Rectangle();
		test.setBounds(x2,y2,35,35);
			testHit(new Point(x2,y2)); 
		if(stop == true){
			x2 = x2 +5;
			g.fillRect(x2, y2, 35, 35);
		} else {
			g.fillRect(x2, y2, 35, 35);
		}
		dir = 0;
	//}
	}
	/**
	 * moves tank right
	 * @param g
	 * @param rect
	 * @param dir
	 */
	public void goRight(Graphics g,ArrayList<Rectangle> rect, int dir) {
		//System.out.println("RIGHT");
		rectangle = rect;
		x2 = x2 + 5;
		Rectangle test = new Rectangle();
		test.setBounds(x2,y2,35,35);
		testHit(new Point(x2,y2)); 
		if(stop == true){
			x2 = x2 -5;
			g.fillRect(x2, y2, 35, 35);
		} else {
			g.fillRect(x2, y2, 35, 35);
		}
		dir = 0;
	}
	/**
	 * draws tank
	 * @param g
	 * @param dir
	 * @param rect
	 * @param mx
	 * @param my
	 * @param shoot
	 * @param enemy
	 */
	public void drawTank(Graphics g, int dir, ArrayList<Rectangle> rect,int mx, int my, int shoot,ArrayList<Tank> enemy){
		if(display == false) {
			if(lives<=0){	
				//System.out.println("I DIED");
				g.fillRect(-100,-100,0,0);
			}//g.fillRect(100,100,300,300);
		}
		if(display == true) {
		moveTank(g,dir,rect,mx,my,shoot);
		int x = 0;
		if(shoot == 1) {
			shootBool = true;
		}
		if(shootBool == true) {
			drawMissile(g,enemy, 0);
		}
		}
	}
	/**
	 * draws missile fired
	 * @param g
	 * @param enemy
	 * @param i
	 */
	public void drawMissile(Graphics g, ArrayList<Tank> enemy, int i){
		int size = this.getBullet().size();	
		for(int x = 0; x< size; x++) {
				this.getBullet().get(x).draw(g,enemy, i);
				shoot = 0;
		}
	}
	/**
	 * moves the tank
	 * @param g
	 * @param dir
	 * @param rect
	 * @param mx
	 * @param my
	 * @param shoot
	 */
	public void moveTank(Graphics g, int dir, ArrayList<Rectangle> rect,int mx, int my, int shoot) {
		
		
		int dx = mx - x2; // position - tank
		int dy =  my - y2;
		int magnitude = (int) Math.sqrt(dx*dx  + dy*dy);
		int newX = dx * 50 / magnitude;
		int newY = dy * 50 / magnitude;
		
		check = false;
		stop = false;
		g.setColor(c);
		g.fillRect(x2, y2, 35, 35);
		g.drawLine(x2+17 , y2+17,x2+newX+17, y2+newY+17);
		if(shoot == 1) {
			this.fireMissile(x2+newX+17, y2+newY+17, mx , my);
		}
		if (counter == 0) {
			g.fillRect(x2, y2, 35, 35);
			counter++;
		}
		else if (dir == 1) {
			//System.out.println("UP");
			goUp(g,rect,dir);
		}
		else if (dir == 2) {
			//System.out.println("DOWN");
			goDown(g,rect,dir);
		}
		
		else if (dir == 3) {
			//System.out.println("LEFT");
			goLeft(g,rect,dir);
		}

		
		else if (dir == 4) {
			//System.out.println("RIGHT");
			goRight(g,rect,dir);
		}
		}
	/**
	 * returns the life  of the tank
	 * @return
	 */
	public int getLife() {
		return lives;
	}
	/**
	 * decrements the life
	 */
	public void decrementLife() {
		lives--;
	}
	/**
	 * returns x location of tank
	 * @return
	 */
	public int getTX(){
		return x2;
		
	}
	/**
	 * returns y location of tank
	 * @return
	 */
	public int getTY(){
		return y2;
	}
}
