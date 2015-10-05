import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * data membersand methods for main class
 * @author KevinD
 *
 */

public class main {
//	private static Tank tank = new Tank();
	private static Graphics g;
	/**
	 * start for the program 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[]args) throws FileNotFoundException {
		System.out.println("Choose A Difficulty: ");
		
		int x = getAnswer();
		while(x<0 || x >3) {
			x = getAnswer();
		}
		Frame frame = new Frame(x);
	}
	/**
	 * error checking for difficulty
	 * @return
	 */
	public static int getAnswer() {
		try{
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		while(x <0 || x > 3) {
			x = s.nextInt();
		}
		return x;
	}catch (Exception e) {
		System.out.println("Invalid Input!");
		int answer = getAnswer();
		return answer;
	}
	}
}
     