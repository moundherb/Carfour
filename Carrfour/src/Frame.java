import java.awt.Color;

import javax.swing.*;

public class Frame extends JFrame{
	Panel panel = new Panel();
	Frame(){
		this.setTitle("Carrfour");
		this.add(panel);
		this.setSize(500,500);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
