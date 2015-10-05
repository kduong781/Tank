import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
/**
 * data members and methods for frame
 * @author KevinD
 *
 */
public class Frame extends JFrame{
	private int x = 100;
	private int y = 100;
	private int width = 910, height =910, boardX, boardY;
	/**
	 * constructor for the frame
	 * @param x
	 * @throws FileNotFoundException
	 */
	public Frame(int x) throws FileNotFoundException{

	setTitle("Tank");
	//setBackground(Color.gray);
	//setPreferredSize(new Dimension(500,500));
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenHeight= screenSize.height;
	int screenWidth= screenSize.width;
	//setSize(screenHeight,screenWidth);


	setBounds(0,0,width,height);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);

	Panel panel = new Panel(x);
	//panel.setFocusable(true);
	addKeyListener(panel);
	addMouseListener(panel);
	addMouseMotionListener(panel);
	setContentPane(panel);
	//pack();
}
}