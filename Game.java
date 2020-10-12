package SnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener,ActionListener {
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean play= true;
	 
	private ImageIcon headR;
	private ImageIcon headL;
	private ImageIcon headU;
	private ImageIcon headD;
	
	private int lenOfsnake= 3;
	private Timer timer;
	private int delay =100;
	
	private ImageIcon tail;
	private int moves =0;
	private int score=0;
	
	int[] fruitX= {25,50,75,100,125,150,175,200,225,250,275,300,325,250,375,400,425,450,475,500,525,550,575,600,
			625,650,675,700,725,750,775,800,825,850};
	int[] fruitY=  {75,100,125,150,175,200,225,250,275,300,325,250,375,400,425,450,475,500,525,550,575,600,
			625};
	private ImageIcon fruit;
	private Random r = new Random();
	private int xpos= r.nextInt(34);
	private int ypos= r.nextInt(23);
	
	
  private ImageIcon title;
  public Game() {
	  addKeyListener(this);
	  setFocusable(true);
	  setFocusTraversalKeysEnabled(true);
	  
	  timer = new Timer(delay,this);
	  timer.start();
	  
  }
  public void paint(Graphics g) {
	  if(moves==0) {
		  snakeXlength[0]=100;
		  snakeXlength[1]=75;
		  snakeXlength[2]=50;
		  
		  snakeYlength[0]=100;
		  snakeYlength[1]=100;
		  snakeYlength[2]=100;
	  }
	  title = new ImageIcon("title.png");
	  title.paintIcon(this, g, 25, 5);
	  g.setColor(Color.WHITE);
	  g.drawRect(24,74,851,577);
	  
	  g.setColor(Color.BLACK);
	  g.fillRect(25,75,850,575);
	  
	  g.setColor(Color.white);
		g.setFont(new Font("areal", Font.PLAIN, 14));
		g.drawString("Scores: " + score, 780, 30);
		
		//draw length snake
		g.setColor(Color.white);
		g.setFont(new Font("areal", Font.PLAIN, 14));
		g.drawString("Length: " + lenOfsnake, 780, 50);
	  
	  headR = new ImageIcon("headRight.png");
	  headR.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
	 
	  for(int i = 0; i< lenOfsnake; i++)
		{
			if(i==0 && right)
			{
				headR = new ImageIcon("headRight.png");
				headR.paintIcon(this,g , snakeXlength[i], snakeYlength[i]);
				
			}
			if(i==0 && left)
			{
				headL = new ImageIcon("headLeft.png");
				headL.paintIcon(this,g , snakeXlength[i], snakeYlength[i]);
				
			}
			if(i==0 && down)
			{
				headD = new ImageIcon("headDown.png");
				headD.paintIcon(this,g , snakeXlength[i], snakeYlength[i]);
				
			}
			if(i==0 && up)
			{
				headU = new ImageIcon("headUp.png");
				headU.paintIcon(this,g , snakeXlength[i], snakeYlength[i]);
				
			}
			if(i!=0)
			{
				tail = new ImageIcon("tail.png");
				tail.paintIcon(this,g , snakeXlength[i], snakeYlength[i]);

			}
			
		}
	  fruit = new ImageIcon("fruit.png");
		if(fruitX[xpos]==snakeXlength[0] &&fruitY[ypos]==snakeYlength[0]) {
			score= score+5;
			lenOfsnake++;
			xpos= r.nextInt(34);
			ypos= r.nextInt(23);
		}
		fruit.paintIcon(this, g, fruitX[xpos], fruitY[ypos]);
		
		for(int i = 1; i<lenOfsnake; i++)
		{
			if(snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0])
			{
				right = false;
				left = false;
				up = false;
				down = false;
				play = false;
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 40));
				g.drawString("Game Over! Score: " + score, 250, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Enter to restart", 350, 340);				
				
				
			}
		}
	  g.dispose();
	  
	  
  }
@Override
public void actionPerformed(ActionEvent e) {
	
	timer.restart();
	if(play) {
	if(right) 
	{
		for(int n = lenOfsnake-1; n>=0;n--)
		{
			snakeYlength[n+1] = snakeYlength[n];
		}
		for(int n = lenOfsnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeXlength[n] = snakeXlength[n]+25;
			}
			else 
			{
				snakeXlength[n] = snakeXlength[n-1];
			}
			if(snakeXlength[n] >850)
			{
				snakeXlength[n] = 25;
			}
			
		}
		repaint();
	}
	
	if(left) 
	{
		for(int n = lenOfsnake-1; n>=0;n--)
		{
			snakeYlength[n+1] = snakeYlength[n];
		}
		for(int n = lenOfsnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeXlength[n] = snakeXlength[n]-25;
			}
			else 
			{
				snakeXlength[n] = snakeXlength[n-1];
			}
			if(snakeXlength[n] < 25)
			{
				snakeXlength[n] = 850;
			}
			
		}
		repaint();			
		
	}
	if(up) 
	{
		for(int n = lenOfsnake-1; n>=0;n--)
		{
			snakeXlength[n+1] = snakeXlength[n];
		}
		for(int n = lenOfsnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeYlength[n] = snakeYlength[n]-25;
			}
			else 
			{
				snakeYlength[n] = snakeYlength[n-1];
			}
			if(snakeYlength[n] < 75)
			{
				snakeYlength[n] = 625;
			}
			
		}
		repaint();
		
	}
	if(down) 
	{
		for(int n = lenOfsnake-1; n>=0;n--)
		{
			snakeXlength[n+1] = snakeXlength[n];
		}
		for(int n = lenOfsnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeYlength[n] = snakeYlength[n]+25;
			}
			else 
			{
				snakeYlength[n] = snakeYlength[n-1];
			}
			if(snakeYlength[n] > 625)
			{
				snakeYlength[n] = 75;
			}
			
		}
		
		repaint();
	}
	}
	
	
}
	

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyCode() == KeyEvent.VK_ENTER)
	{
		moves = 0;
		score = 0;
		lenOfsnake=3;
		play= true;
		repaint();
	}
 if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
	 moves++;
	 right=true;
	 if(!left) {
		 right=true;
	 }else {
		 right= false;
		 left= true;
	 }
	 up= false;
	 down = false;
 }
 if(play) {
 if(e.getKeyCode()==KeyEvent.VK_LEFT) {
	 
	 moves++;
	 left=true;
	 if(!right) {
		left=true;
	 }else {
		left= false;
		 right= true;
	 }
	 up= false;
	 down = false;
 }
 if(e.getKeyCode()==KeyEvent.VK_UP) {
	 moves++;
	up=true;
	 if(!down) {
		up=true;
	 }else {
		 up= false;
		down= true;
	 }
	left= false;
	right = false;
 }
 if(e.getKeyCode()==KeyEvent.VK_DOWN) {
	 moves++;
	down=true;
	 if(!up) {
		down=true;
	 }else {
		down= false;
		up= true;
	 }
	left= false;
	right = false;
 }
 }
 
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
