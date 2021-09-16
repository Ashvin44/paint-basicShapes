
import java.awt.BorderLayout;

import java.awt.Color;

import java.util.*;

import javax.swing.*;

import Transformations.Transformation;
import Transformations.Rotation;
import Transformations.Translation;
import Transformations.Reflection;
import Transformations.Rotation_fixed_point;
import Transformations.Scaling;
import Transformations.Shear;
import Transformations.Scaling_fixed_point;


public class Main {

	static Canvas newCanvas = new Canvas();
	static Menu newMenu = new Menu();
	static Animation_panel newAnimation_panel = new Animation_panel();
	static JFrame f = new JFrame("Why are you reading this? Go back and use the program.");
	public static int FRAME_LENGTH = 1200;
	public static int FRAME_HEIGHT = 740;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		f.setLayout(new BorderLayout());
		
		
		f.setSize(FRAME_LENGTH ,FRAME_HEIGHT);
		 
		
		 
		f.add( newMenu.getTopMenu() , BorderLayout.NORTH);
	    f.add( newMenu.getRightMenu() , BorderLayout.EAST);
	    f.add( newCanvas, BorderLayout.CENTER);
	     
		f.add(newAnimation_panel.getMain_container(), BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
	        
	        //f.pack();
	     //   f.setLocationByPlatform(true);
		
		 
		 /*
		Animation_panel as = new Animation_panel();
		Animation_panel as2= new Animation_panel();
		as.setSize(700, 600);
		as2.setBackground(Color.BLUE);
		JPanel newPanel = new JPanel();
		newPanel.setSize(600, 600);
		
		
		JScrollPane scrollPane=new JScrollPane(newPanel, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//this.add(scrollPane);
		scrollPane.setSize(100,600);
		newPanel.add(as);
		newPanel.add(as2);
		f.add(scrollPane);
		
		
		*/
		/*
		 Animation_panel as = new Animation_panel();
		 f.add(as);

	    f.setVisible(true);
		
		ArrayList<Transformation> test = new ArrayList<Transformation>();
		
		test.add(new Translation(4,5));
		test.add(new Rotation(6));

		((Translation) test.get(0)).getX();
		
		Translation t = new Translation(5,6);

		
		Animation_transformation_panel a = new Animation_transformation_panel(t);
		((Translation) a.getTransformation()).getX();
		*/
		
		
	
		
		
	}

}
