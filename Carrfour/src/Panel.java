import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Changer implements Runnable {
	Graphics g;
	int Feu;
	static Color color1 = Color.green;
	static Color color2 = Color.red;
	Changer(int Feu){
		this.Feu = Feu;
	}
	public void run () {
		while(true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Feu == 1) {
				try {
					Main.Feu1.acquire();
					Main.Feu2.release();
					Feu = 2;
					color2 = Color.green;
					color1 = Color.red;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("way 2 can pass");
			}else if(Feu == 2) {
				try {
					Main.Feu2.acquire();
					Main.Feu1.release();
					Feu = 1;
					color1 = Color.green;
					color2 = Color.red;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("way 1 can pass");
			}
			
		}
	}	
}

class CarVoie1 extends Thread {
	int id;
	int y;
	int carn;
	Random random = new Random();
	CarVoie1(int id){
		this.id = id;
		y = -100;
		carn = random.nextInt(3);
	}
	
	public void run(){
		try {
			
			Main.canpass.acquire();
			
			Main.Feu1.acquire();
			Main.Feu1.release();
			
			System.out.println("v1 " + id + " start circuler");
			while(y<250) {
				y = y+1;
				System.out.println("v1 " + id + " position : " + y);
				Panel.Cars1.add(this);
				sleep(10);
			}
			Main.canpass.release();
			while(y<500) {
				y = y+1;
				System.out.println("v1 " + id + " position : " + y);
				Panel.Cars1.add(this);
				sleep(10);
			}
			System.out.println("v1 " + id + " end circuler");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Main.nbv1--;
	}
	int gety() {
		return y;
	}
	int getcarn() {
		return carn;
	}
}

class CarVoie2 extends Thread {
	int id;
	int x ;
	int carn;
	Random random = new Random();
	CarVoie2(int id){
		this.id = id;
		x = -100;
		carn = random.nextInt(3);
	}
	
	public void run(){
		try {
			Main.canpass.acquire();
			
			Main.Feu2.acquire();
			Main.Feu2.release();
			
			System.out.println("v2 " + id + "start circuler");
			while(x<250) {
				x = x+1;
				System.out.println("v2 " + id + " position : " + x);
				Panel.Cars2.add(this);
				sleep(10);
			}
			Main.canpass.release();
			while(x<500) {
				x = x+1;
				System.out.println("v2 " + id + " position : " + x);
				Panel.Cars2.add(this);
				sleep(10);
			}
			System.out.println("v2 " + id + " end circuler");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Main.nbv2--;
	}
	int getx() {
		return x;
	}
	int getcarn() {
		return carn;
	}
}
public class Panel extends JPanel {
	private ImageIcon car11;
	private ImageIcon car12;
	private ImageIcon car13;
	private ImageIcon car21;
	private ImageIcon car22;
	private ImageIcon car23;
	static ArrayList<CarVoie1> Cars1 = new ArrayList<>();
	static ArrayList<CarVoie2> Cars2 = new ArrayList<>();
	Panel(){
		car11=new ImageIcon("car11.png");
		car12=new ImageIcon("car12.png");
		car13=new ImageIcon("car13.png");
		car21=new ImageIcon("car21.png");
		car22=new ImageIcon("car22.png");
		car23=new ImageIcon("car23.png");
	}
	public void paint(Graphics g) {
		
		super.paint(g);
		g.drawLine(205, 0, 205, 193);
		g.drawLine(275, 0, 275, 193);
		g.drawLine(205, 263, 205, 500);
		g.drawLine(275, 263, 275, 500);
		
		g.drawLine(0, 193, 205, 193);
		g.drawLine(0, 263, 205, 263);
		g.drawLine(275, 193, 500, 193);
		g.drawLine(275, 263, 500, 263);
		
	    g.setColor(Changer.color1);
	    g.fillOval(180,0,25,25);  
	    g.fillOval(275,0,25,25);
	    g.setColor(Changer.color2);
	    g.fillOval(0,168,25,25);
	    g.fillOval(0,263,25,25);
	    
	    for(int i=0;i<Cars1.size();i++) {
	    	switch (Cars1.get(i).getcarn()) {
	    	 case 0:  car11.paintIcon(this,g,221,Cars1.get(i).gety());break;
	    	 case 1:  car12.paintIcon(this,g,221,Cars1.get(i).gety());break;
	    	 case 2:  car13.paintIcon(this,g,221,Cars1.get(i).gety());break;
			}
	    	
	    }
	    for(int i=0;i<Cars2.size();i++) {
	    	switch (Cars2.get(i).getcarn()) {
	    	 case 0:  car21.paintIcon(this,g,Cars2.get(i).getx(),205);break;
	    	 case 1:  car22.paintIcon(this,g,Cars2.get(i).getx(),205);break;
	    	 case 2:  car23.paintIcon(this,g,Cars2.get(i).getx(),205);break;
			}
	    	
	    }
	    
	    repaint();
	}

}
