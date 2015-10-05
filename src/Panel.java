import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.*;
/**
 * methods and data members for panel
 * @author KevinD
 *
 */
public class Panel extends JPanel implements Runnable, KeyListener,
		MouseListener, MouseMotionListener {
	/*
	 * creates player tank
	 */
	private Tank tank = new Tank(new Point(70, 70), Color.red, 1, new Missile(
			10, 10, 10, 10, Color.green, 3), new Point(0, 0), 3);
	//private int x = 100;
	//private int y = 100;
	// Thread data member
	private Thread thread;
	/*
	 * width, and height of panel, x,y coordinates of board
	 */
	private int width = 900, height = 900, boardX, boardY;
	/*
	 * board array
	 */
	private int[][] board;
	/*
	 * counters and mouse position 
	 */
	private int dir = 0, counter = 0, mx = 0, my = 0, counter1 = 0,
			counter2 = 0;
	//private int horizon = 7, vertical = 7, start = 0, wX = 1, wY = 1;
	// private int[] invalidX, invalidY;
	/**
	 * hit counters for lives
	 */
	private int dec = 0, hit = 0, hit2 = 0;
	/**
	 * length of turret
	 */
	private int tLength = 5, newX, newY, shoot;
//	private int dx1 = 0, dx2 = 0, magniude1 = 0;
	/*
	 * lives for enemy tanks
	 */
	private int enemy1 = 0, enemy2 = 0, enemy3 = 0;
	private boolean run = true;
	/*
	 * array of enemy tanks
	 */
	private ArrayList<Tank> enemy = new ArrayList<Tank>();
	/*
	 * array of walls for collisions
	 */
	ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
	/**
	 * map collision
	 */
	private ArrayList<Rectangle> map = new ArrayList<Rectangle>();
	//private ArrayList<Missile> missiles = new ArrayList<Missile>();
	/*
	 * arraryList for target
	 */
	private ArrayList<Tank> target = new ArrayList<Tank>();
	/**
	 * arrayList for all obstacles
	 */
	private ArrayList<Tank> obstacle = new ArrayList<Tank>();
	/*
	 * mode for game
	 */
	private int mode = -1;
	/**
	 * constructor for panel
	 * @param x
	 * @throws FileNotFoundException
	 */
	public Panel(int x) throws FileNotFoundException {
		
		super(true);
		target.add(tank);
		mode = x;
		setBounds(0, 0, width, height);
		setBackground(Color.gray);
		File f = new File("map1.txt");
		Scanner s = new Scanner(f);
		boardY = s.nextInt();
		boardX = s.nextInt();
		board = new int[boardX][boardY];
		setFocusable(true);
		setBackground(Color.gray);
		setVisible(true);
		s.nextLine();
		Random r = new Random();
		thread = new Thread(this);
		thread.start();
		createTanks(x);
		for (int i = 0; i < boardY; i++) {
			if (s.hasNextLine()) {
				s.nextLine();
			}
			for (int j = 0; j < boardX; j++) {
				if (s.hasNextInt()) {
					board[j][i] = s.nextInt();
				}
			}
		}

	}
	
/**
 * creates Tank
 * @param x  number of tanks you want to be created
 */
	public void createTanks(int x) {
		if (x == 1) {

			enemy.add(new Tank(new Point(800, 200), Color.green, 1,
					new Missile(10, 10, 10, 10, Color.green, 1),
					new Point(0, 0), 1));
		}
		if (x == 2) {
			enemy.add(new Tank(new Point(800, 200), Color.green, 1,
					new Missile(10, 10, 10, 10, Color.green, 1),
					new Point(0, 0), 1));
			enemy.add(new Tank(new Point(180, 800), Color.green, 1,
					new Missile(10, 10, 10, 10, Color.green, 2),
					new Point(0, 0), 2));
		}
		if (x == 3) {
			enemy.add(new Tank(new Point(800, 200), Color.green, 1,
					new Missile(10, 10, 10, 10, Color.green, 1),
					new Point(0, 0), 1));
			enemy.add(new Tank(new Point(180, 800), Color.green, 1,
					new Missile(10, 10, 10, 10, Color.green, 2),
					new Point(0, 0), 2));
			enemy.add(new Tank(new Point(280, 800), Color.green, 1,
					new Missile(10, 10, 10, 10, Color.green, 2),
					new Point(0, 0), 2));

	//		enemy.add(tank);
		}
	}
	/**
	 * paints on panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x2 = 0;


		paintBoard(g);
		boolean play = true;
		g.setColor(Color.BLACK);
		if (hit2 > 10) {
			hit2 = 10;
		}
		g.drawString("Lives: " + (10 - hit2), 10, 10);
		if(hit2 == 10) {
			g.drawString("Game Over", 50,50);
			thread.stop();
		}
		if (run == true) {
			for (int x = 0; x < enemy.size(); x++) {
				for (int y = 0; y < enemy.get(x).getBullet().size(); y++) {
						if (0 == enemy.get(x).getBullet().get(y)
								.killTank()) {
							hit2++;
						}
						enemy.get(x).getBullet().get(y).setKillTank(-1);
						g.setColor(Color.BLACK);

					}
				}
			for (int x = 0; x < target.size(); x++) {
				for (int y = 0; y < target.get(x).getBullet().size(); y++) {
						if (0 == target.get(x).getBullet().get(y)
								.killTank()) {
							enemy1++;
						}
						if (1 == target.get(x).getBullet().get(y)
								.killTank()) {
							enemy2++;
						}
						if (2 == target.get(x).getBullet().get(y)
								.killTank()) {
							enemy3++;
						}
						
						target.get(x).getBullet().get(y).setKillTank(-1);
						g.setColor(Color.BLACK);

					}
				}
			
		
			}
		if(enemy1 == 10) {
			enemy.get(0).setDisplay(false);
		}
		if ( enemy2 == 10) {
				enemy.get(1).setDisplay(false);
		}
		if (enemy3 == 10)  {
				enemy.get(2).setDisplay(false);
			
		}
			// while(temp != Direction.N) {
			tank.drawTank(g, dir, walls, mx, my, shoot, enemy);
			for (int y = 0; y < enemy.size(); y++) {
				//if (y != 0) {
					enemy.get(y).follow(g, dir, walls, tank.getTX(),
							tank.getTY(), tank, target, 1);
			//	}

			}
			dir = 0;

			if (shoot == 1) {
				System.out.println("SHOOT");
				shoot = 0;
			}


			if(mode == 1){
				if(enemy.get(0).getDisplay() == false) {
				g.drawString("GAME OVER", 50, 50);
				thread.stop();
				}
			}
			if(mode == 2){
				if(enemy.get(0).getDisplay() == false &&enemy.get(1).getDisplay()== false){
				g.drawString("GAME OVER", 50, 50);
				thread.stop();
				}
			}
			if( mode == 3){
				if(enemy.get(0).getDisplay() == false &&enemy.get(1).getDisplay() == false&&enemy.get(2).getDisplay()== false){
				g.drawString("GAME OVER", 50, 50);
				thread.stop();
			}
			}
		}
	//}
	/**
	 * checks for Bullet
	 * @param t
	 */
	public void checkBullet(Tank t) {
		Rectangle r = new Rectangle(t.getTX(), t.getTY(), 35, 35);
		// if(r.intersects())
	}

	// }
	/**
	 * paints the board on the panel
	 * @param g
	 */
	public void paintBoard(Graphics g) {
		g.setColor(Color.ORANGE);
		for (int i = 0; i < boardY; i++) {
			for (int j = 0; j < boardX; j++) {
				if (board[j][i] == 1) {
					g.setColor(Color.ORANGE);
					g.fillRect((j) * 70, (i) * 70, 70, 70);

					if (counter1 == 0) {
						Rectangle rect = new Rectangle();
						rect.setBounds(j * 70, i * 70, 70, 70);
						walls.add(rect);
					}

				} else {
					g.setColor(Color.gray);
					g.fillRect((j) * 70, (i) * 70, 70, 70);

				}
			}
		}

		counter1++;
	}
	/**
	 * does nothing
	 */
	public void start() {
		System.out.println("Program is running...");
	}

	@Override
	/**
	 * action listner for tank movement
	 */
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// System.out.println("X " + tank.getTX());
		// System.out.println("Y"+ tank.getTY());
		// boolean checkWall = checkWall();
		boolean stop = true;
		switch (keyCode) {
		case KeyEvent.VK_W:
			dir = 1;
			// shoot = 0;
			// this.repaint();
			break;
		case KeyEvent.VK_S:
			dir = 2;
			// this.repaint();
			// shoot = 0;
			break;
		case KeyEvent.VK_A:
			dir = 3;
			// this.repaint();
			// shoot = 0;
			break;
		case KeyEvent.VK_D:
			// shoot = 1;
			dir = 4;
			// this.repaint();
			// shoot = 0;
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		// shoot = 0;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// /*/
	}

	/**
	 * action listener for mouse entered for shooting
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		int dx = e.getX() - tank.getTX();
		int dy = e.getY() - tank.getTY();
		int magnitude = (int) Math.sqrt(dx * dx + dy * dy);
		newX = dx * 30 / magnitude;
		newY = dy * 30 / magnitude;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		int dx = e.getX() - tank.getTX();
		int dy = e.getY() - tank.getTY();
		int magnitude = (int) Math.sqrt(dx * dx + dy * dy);
		newX = dx * 30 / magnitude;
		newY = dy * 30 / magnitude;

	}

	@Override
	/**
	 * action listener for mouse shooting/ turret from tank
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		int dx = e.getX() - tank.getTX();
		int dy = e.getY() - tank.getTY();
		int magnitude = (int) Math.sqrt(dx * dx + dy * dy);
		newX = dx * 30 / magnitude;
		newY = dy * 30 / magnitude;
		shoot = 1;
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		int dx = e.getX() - tank.getTX();
		int dy = e.getY() - tank.getTY();
		int magnitude = (int) Math.sqrt(dx * dx + dy * dy);
		int newX = dx * 30 / magnitude;
		int newY = dy * 30 / magnitude;
		// shoot = 0;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		int dx = e.getX() - tank.getTX();
		int dy = e.getY() - tank.getTY();
		int magnitude = (int) Math.sqrt(dx * dx + dy * dy);
		newX = dx * 30 / magnitude;
		newY = dy * 30 / magnitude;
	}

	/**
	 * action listener for tank turret
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		int dx = e.getX() - tank.getTX();
		int dy = e.getY() - tank.getTY();
		int magnitude = (int) Math.sqrt(dx * dx + dy * dy);
		newX = dx * 50 / magnitude;
		newY = dy * 50 / magnitude;

	}
/**
 * thread to repaint every 50 milliseconds
 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			this.repaint();
			try {
				thread.sleep(50);
			} catch (Exception e) {
				// System.out.println("error");
			}
		}
	}
}
