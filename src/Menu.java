

import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu extends JPanel{

	Stroke solid = new BasicStroke(Canvas.line_size);
	Stroke dashed = new BasicStroke(Canvas.line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[]{10}, 0.0f);
	Stroke dotted = new BasicStroke(Canvas.line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[]{3,4}, 0.0f);
	Stroke alternate = new BasicStroke(Canvas.line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[] {4,5,10,5}, 0.0f);
	Stroke strokeChoice = new BasicStroke(3);
	public static int choice = 0 ;
	public int grid_value = 0;
	public static Point clipPoint1 = new Point(0,0);
	public static Point clipPoint2 = new Point(0,0);
	private JPanel TopMenu = new JPanel();	 
	private JPanel RightMenu = new JPanel();
	
	private JPanel ClipPanel = new JPanel(new CardLayout());
	private JPanel ClipPanel_sub = new JPanel();
	private JButton buttonClip = new JButton("Clip");
	private JPanel RightMenuContainer = new JPanel(); 
	
	private ColorIcon HueIcon = new ColorIcon();
	
	private String imagePath = "./images/";
	
	public void resetClip_function() {
		Canvas.buttonClipPressed = false;
		
		CardLayout c1 = (CardLayout)ClipPanel.getLayout();
		c1.show(ClipPanel, "button");	
	
	}
	
	
	
	Menu(){
		
		 ListCellRenderer renderer = new ComplexCellRenderer();
		
		 TopMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
		 TopMenu.setBackground(Color.lightGray);
		 
		 JPanel TopMenuContainer = new JPanel();
		 TopMenuContainer.setBackground(Color.lightGray);
		 
		 RightMenu.setLayout(new BorderLayout());
	     RightMenu.setBackground(Color.lightGray);
	     
	     RightMenuContainer = new JPanel();
	    RightMenuContainer.setLayout(new BoxLayout(RightMenuContainer, BoxLayout.Y_AXIS));
	    // RightMenuContainer.setLayout(new GridLayout(2,1,10,10));
	    // RightMenuContainer.setLayout(new BorderLayout());
	    RightMenuContainer.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
	     RightMenuContainer.setBackground(Color.lightGray);
	     JPanel RightMenuContainer_top = new JPanel();
	     RightMenuContainer_top.setBackground(Color.lightGray);
	     RightMenuContainer_top.setMaximumSize(new Dimension(200,500));
	     RightMenuContainer_top.setLayout(new GridLayout(8,1,10,10));
	     JPanel RightMenuContainer_bottom = new JPanel();
	     
	     // START OF JCOMPONENTS FOR RIGHT MENU
	     
	     // button to toggle grid on and off
	     JButton buttonGrid;
	     //ImageIcon grid=new ImageIcon(imagePath + "grid.png");
	     ImageIcon grid=new ImageIcon(getClass().getClassLoader().getResource("grid.png"));
	     buttonGrid=new JButton("Grid",grid);
	     buttonGrid.setToolTipText("Toggles between 3 states : No grid, Axis lines only, Axis lines with grid.");
	     buttonGrid.addActionListener(new ActionListener() 
	     {
		 		@Override
		 		public void actionPerformed(ActionEvent e) 
		 		{
		 			grid_value++;
		 			grid_value %= 3;
		 			if(grid_value == 0)
		 			{
		 				Grid.grid_toggle = true;
		 			    Grid.axis_toggle = true;
		 			}
		 			if(grid_value == 1)
		 			{
		 				Grid.grid_toggle = false;
		 			    Grid.axis_toggle = true;
		 			}
		 			if(grid_value == 2)
		 			{
		 				Grid.grid_toggle = false;
		 			    Grid.axis_toggle = false;
		 			}
		 			Main.newCanvas.repaint();
		 		}
	 	 });
	     
	     // Center to set the canvas to the center
	     JButton buttonCenter = new JButton("Back to center");
	     buttonCenter.setToolTipText("Set the canvas back to the center.");
	     buttonCenter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					int differenceX = Grid.Start_grid_center_point.x - Grid.grid_center_point.x;
					int differenceY = Grid.Start_grid_center_point.y - Grid.grid_center_point.y;
					
					// Brings shapes back to default position
					for (int i = 0; i < Canvas.List_of_Shapes.size(); i++) 
					{
						for (Point2D n : Canvas.List_of_Shapes.get(i).getPoints()) {
							n.setLocation(n.getX() + differenceX, n.getY() + differenceY);
						}
					}
					
					// Brings grid back to default position
					Grid.grid_center_point.x += differenceX ;
					Grid.grid_center_point.y += differenceY;
					Main.newCanvas.repaint();
					Canvas.clearSelectedShapes();
					
			}});
	     
	     
	     
	     
	     
	     // A panel to hold the line size label and the jspinner
	     JPanel line_Size_Panel = new JPanel();
	     // JSpinner to change line size of shapes
	     JSpinner line_Size_JSpinner = new JSpinner();
	     line_Size_JSpinner.setModel(new SpinnerNumberModel(Canvas.line_size, 1, 80, 1));
	     line_Size_JSpinner.addChangeListener(new ChangeListener() {

			 @Override
		     public void stateChanged(ChangeEvent e) {
		             JSpinner s = (JSpinner) e.getSource();
		             Canvas.line_size = (Integer) s.getValue();
		             solid = new BasicStroke(Canvas.line_size);
		         	 dashed = new BasicStroke(Canvas.line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[]{10}, 0.0f);
		         	 dotted = new BasicStroke(Canvas.line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[]{3,4}, 0.0f);
		         	 alternate = new BasicStroke(Canvas.line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[] {4,5,10,5}, 0.0f);
		         	
		         	 
		         	 
		         	switch(choice)
		  			{
		  			case 0:
		  				strokeChoice = solid;
		  				break;
		  			case 1:
		  				strokeChoice = dashed;
		  				break;
		  			case 2:
		  				strokeChoice = dotted;
		  				break;
		  			case 3:
		  				strokeChoice = alternate;
		  				break;
		  				
		  				
		  			}
		         	 Canvas.canvas_stroke = strokeChoice;
		         	if(Canvas.List_of_selected_Shapes.size() > 0)
		  			{
		  				Canvas.List_of_selected_Shapes.get(0).setStroke(strokeChoice);
		  				Canvas.List_of_selected_Shapes.get(0).setLine_size((Integer)s.getValue());
		  				Main.newCanvas.repaint();
		  			}
		         	 Main.newCanvas.repaint();
		            
		             
		           }
		     });
		 JLabel line_Size_Label = new JLabel("Line size    :");
	     
		 line_Size_Panel.add(line_Size_Label);
		 line_Size_Panel.add(line_Size_JSpinner);
		 
		 
		// Combobox to select type of shape (Circle, line, free form, irregular and regular shape)
	     Object shapeTypeElements[][] = new Object[5][2];

	      for (Shapes.Shape_Type t : Shapes.Shape_Type.values()) {
	     	 
	    	  shapeTypeElements[t.ordinal()][0] = new TypeIcon(t);
	    	  shapeTypeElements[t.ordinal()][1] = t.toString().replaceAll("_", " ");
	      } 
	     JComboBox shapeTypeCombo = new JComboBox(shapeTypeElements);
	     shapeTypeCombo.setRenderer(renderer);

		 
		 
		 // If user chooses to draw regular shapes, the user can choose the number of sides
		 // Panel to contain label and jspinner of number of sides
		 
		 JPanel number_of_side_Panel = new JPanel();
		 
		 JSpinner number_of_side_Jspinner = new JSpinner();
		 number_of_side_Jspinner.setModel(new SpinnerNumberModel(Canvas.no_of_sides, 3, 40, 1));
		 number_of_side_Jspinner.addChangeListener(new ChangeListener() {

	            @Override
	          public void stateChanged(ChangeEvent e) {
	              JSpinner s = (JSpinner) e.getSource();
	              Canvas.no_of_sides = (Integer) s.getValue();
	              Canvas.no_of_clicks = 0;
	              Main.newCanvas.repaint();
	             
	              shapeTypeCombo.repaint();
	            }
	      });
	      JLabel number_of_side_label = new JLabel("No of Side :");
	      
	      number_of_side_Panel.add(number_of_side_label);
	      number_of_side_Panel.add(number_of_side_Jspinner);
	      
	      number_of_side_Panel.setVisible(false);
	      // Functionality of shape type combobox
	      shapeTypeCombo.addActionListener(new ActionListener() {
	   		@Override
	   		public void actionPerformed(ActionEvent e) {
			   		JComboBox cb = (JComboBox)e.getSource();
			   		
			   		// resets number of clicks
			   		Canvas.no_of_clicks = 0;
			   		Canvas.typeSelected = Shapes.Shape_Type.values()[cb.getSelectedIndex()];
			   			
			   		// number of side components will only be visible when user selects regular shape to draw
			   		if (Main.newCanvas.typeSelected == Shapes.Shape_Type.Regular_Shape || Main.newCanvas.typeSelected == Shapes.Shape_Type.Irregular_Shape )
			   		{		
			   			number_of_side_Panel.setVisible(true);
			   		}	
			   		else
			   		{
			   			number_of_side_Panel.setVisible(false);
			   		}
			   		
	   		}});
	    
	     
	  // Combobox to select type of strokes (dashed, solid...)
	     Object strokeElements[][] = 
	    	 {
	    		 { new MyIcon(solid), null},
	    	     { new MyIcon(dashed), null},
	    	     { new MyIcon(dotted),null},
	    	     { new MyIcon(alternate),null},
	    	 };
	    	     
	     JComboBox strokeComboBox = new JComboBox(strokeElements);
	     strokeComboBox.setRenderer(renderer);
	     
	     
	     // stroke combobox functionality
	     strokeComboBox.addActionListener(new ActionListener() 
	     {
	   			@Override
	   			public void actionPerformed(ActionEvent e) 
	   			{
		   			JComboBox cb = (JComboBox)e.getSource();
		   			choice = cb.getSelectedIndex();
		   			
		   			switch(choice)
		   			{
		   			
			   			case 0:
			   				strokeChoice = solid;
			   				break;
			   			case 1:
			   				strokeChoice = dashed;
			   				break;
			   			case 2:
			   				strokeChoice = dotted;
			   				break;
			   			case 3:
			   				strokeChoice = alternate;
			   				break;
			   				
		   			}
		   			Canvas.canvas_stroke = strokeChoice;
		   			if(Canvas.List_of_selected_Shapes.size() > 0)
		   			{
		   				Canvas.List_of_selected_Shapes.get(0).setStroke(strokeChoice);
		   				Main.newCanvas.repaint();
		   			}
		   			shapeTypeCombo.repaint();
	   			}
	   	});

	    // ClipPanel.setLayout(new GridLayout(1,1));
	     ClipPanel_sub.setLayout(new GridLayout(1,2,1,1));

	     // Center to set the canvas to the center
	     ImageIcon clip=new ImageIcon(getClass().getClassLoader().getResource("clip.png"));
	     buttonClip = new JButton("Clip",clip);
	     
	     buttonClip.setToolTipText("Click and drag to set clip area.");
	     buttonClip.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Canvas.buttonClipPressed = true;
					Canvas.buttonDrawPressed = false;
					Canvas.buttonSelectPressed = false;
					Canvas.buttonFillPressed = false;
					Canvas.buttonUnfillPressed = false;
					
					Shapes.selectNone(Canvas.List_of_Shapes);
					Canvas.clearSelectedShapes();
					Main.newCanvas.repaint();
					
					
					CardLayout c1 = (CardLayout)ClipPanel.getLayout();
					c1.show(ClipPanel, "choice");										
			}});
	     
	  // Center to set the canvas to the center
	     JButton buttonClip_Cancel = new JButton("Cancel");
	     buttonClip_Cancel.setToolTipText("Cancel the clip function.");
	     buttonClip_Cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					resetClip_function();
					Canvas.buttonDrawPressed = false;
					Canvas.buttonSelectPressed = false;
					Canvas.buttonFillPressed = false;
					Canvas.buttonUnfillPressed = false;
					
					Shapes.selectNone(Canvas.List_of_Shapes);
					Canvas.clearSelectedShapes();
					Main.newCanvas.repaint();
					
			}});
	     
	  // Center to set the canvas to the center
	     JButton buttonClip_Clip_it = new JButton("Clip it!",clip);
	     buttonClip_Clip_it.setToolTipText("Clips the shapes found in the clip area.");
	     buttonClip_Clip_it.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					resetClip_function();
					Canvas.buttonDrawPressed = false;
					Canvas.buttonSelectPressed = false;
					Canvas.buttonFillPressed = false;
					Canvas.buttonUnfillPressed = false;
					
					
					int clipMin_x = Menu.clipPoint1.x;
		    		int clipMin_y = Menu.clipPoint1.y;
		    		int clipMax_x = Menu.clipPoint2.x;
		    		int clipMax_y = Menu.clipPoint2.y;
		    		
		    		if (clipMin_x > Menu.clipPoint2.x)
		    		{
		    			clipMin_x = Menu.clipPoint2.x;
		    			clipMax_x = Menu.clipPoint1.x;
		    		}
		    		
		    		if (clipMin_y > Menu.clipPoint2.y)
		    		{
		    			clipMin_y = Menu.clipPoint2.y;
		    			clipMax_y = Menu.clipPoint1.y;
		    		}
		    		CohenSutherlandClipping.setClip(clipMin_x, clipMax_x, clipMin_y, clipMax_y);
		    		
		    		
		    		for (int i = 0 ; i < Canvas.List_of_Shapes.size(); i++)
		    		{
		    			
		    			if (Canvas.List_of_Shapes.get(i).getType() == Shapes.Shape_Type.Line)
		    			{
		    				Line2D lineTemp = new Line2D.Double();
		    				lineTemp.setLine(Canvas.List_of_Shapes.get(i).getPoints().get(0).getX(),Canvas.List_of_Shapes.get(i).getPoints().get(0).getY(), Canvas.List_of_Shapes.get(i).getPoints().get(1).getX(), Canvas.List_of_Shapes.get(i).getPoints().get(1).getY());
			
		    				if (CohenSutherlandClipping.clip(lineTemp) == false)
		    				{
		    					Canvas.List_of_Shapes.remove(i);
		    					i--;
		    					continue;
		    					
		    				}
		    				else {
		    					Canvas.List_of_Shapes.get(i).getPoints().get(0).setLocation(lineTemp.getX1(), lineTemp.getY1());	    				
		    					Canvas.List_of_Shapes.get(i).getPoints().get(1).setLocation(lineTemp.getX2(), lineTemp.getY2());
		    				}
		    				
		    			}
		    			else {
		    				
		    				
		    				SutherLand_HodgmanClipping.setClipper(clipMin_x, clipMax_x, clipMin_y, clipMax_y);
		    				SutherLand_HodgmanClipping.setPoints(Canvas.List_of_Shapes.get(i).getPoints());
		    				
		    				SutherLand_HodgmanClipping.clipPolygon();
		    				
		    				
		    				Canvas.List_of_Shapes.get(i).getPoints().clear();
		    				
		    				for (int k = 0 ; k < SutherLand_HodgmanClipping.result.size() ; k++)
		    				{
		    					double[] B = SutherLand_HodgmanClipping.result.get(k);
		    					Canvas.List_of_Shapes.get(i).addPoint(new Point((int)B[0], (int)B[1]));
		    				}
		    				
		    				if (Canvas.List_of_Shapes.get(i).getPoints().size() == 0)
		    				{
		    					Canvas.List_of_Shapes.remove(i);
		    					i--;
		    				}
		    				
		    			}
		    		}

					
					Shapes.selectNone(Canvas.List_of_Shapes);
					Canvas.clearSelectedShapes();
					Main.newCanvas.repaint();
					
			}});
	     
	     
	     
	     
	     line_Size_Panel.setOpaque(false);
	     number_of_side_Panel.setOpaque(false);
	     
	     ClipPanel_sub.add(buttonClip_Cancel);
	     ClipPanel_sub.add(buttonClip_Clip_it);
	     
	     ClipPanel.add(buttonClip, "button");
	     ClipPanel.add(ClipPanel_sub, "choice");
	     // adding components to the right menu
	     RightMenuContainer_top.add(buttonGrid);
	     RightMenuContainer_top.add(buttonCenter);
	     RightMenuContainer_top.add(strokeComboBox);
	     RightMenuContainer_top.add(shapeTypeCombo);
	     RightMenuContainer_top.add(line_Size_Panel);
	     RightMenuContainer_top.add(number_of_side_Panel);
	     //RightMenuContainer_top.add(buttonClip);
	     
	     RightMenuContainer_top.add(ClipPanel);
	     // START OF JCOMPONENTS FOR TOP MENU
	     
	     //Creating and adding functionality of Draw button
	     JButton buttonDraw;	    
	     ImageIcon draw = new ImageIcon(getClass().getClassLoader().getResource("draw2.png"));    
	     buttonDraw=new JButton("Draw",draw);
	     buttonDraw.setToolTipText("Click and drag on the canvas to draw selected shape.");
	     //buttonDraw.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	     buttonDraw.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					resetClip_function();
					Canvas.buttonDrawPressed = true;
					Canvas.buttonSelectPressed = false;
					Canvas.buttonFillPressed = false;
					Canvas.buttonUnfillPressed = false;
					Canvas.clearSelectedShapes();
					Shapes.selectNone(Canvas.List_of_Shapes);
					Main.newCanvas.repaint();
			}});
	     
	     
	     //Creating and adding functionality of Select button
	     JButton buttonSelect;
	     ImageIcon select=new ImageIcon(getClass().getClassLoader().getResource("select.png"));
	     buttonSelect=new JButton("Select",select);
	     buttonSelect.setToolTipText("Click on a shape to select it.");
	    // buttonSelect.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	     buttonSelect.addActionListener(new ActionListener() {
	    	 	@Override
				public void actionPerformed(ActionEvent e) {
	    	 		resetClip_function();
					Canvas.buttonSelectPressed = true;
					Canvas.buttonDrawPressed = false;
					Canvas.buttonFillPressed = false;
					Canvas.buttonUnfillPressed = false;
					Main.newCanvas.repaint();

	    	 }});
	     
	     
	     //Creating and adding functionality of Clear button
	     JButton buttonClear;
	     ImageIcon clear=new ImageIcon(getClass().getClassLoader().getResource("Clear-icon.png"));
	     buttonClear=new JButton("Clear",clear);
	     buttonClear.setToolTipText("Removes all shapes on the canvas to start anew.");
	     //buttonClear.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	     buttonClear.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
					  Shapes.selectNone(Canvas.List_of_Shapes);
				      Canvas.List_of_Shapes.clear();
				      Canvas.clearSelectedShapes();
				      Canvas.no_of_clicks = 0;
				      resetClip_function();
				      Canvas.buttonDrawPressed = false;
				      Canvas.buttonFillPressed = false;
				      Canvas.buttonUnfillPressed = false;
				      Main.newCanvas.repaint();
		    }});
		     
	     
	     //Creating and adding functionality of Fill button
	     JButton buttonFill;
	     ImageIcon fill=new ImageIcon(getClass().getClassLoader().getResource("fill.png"));
	     buttonFill=new JButton("Scanline Fill",fill);
	     buttonFill.setToolTipText("Click on shapes to fill them up with the current color selected.");
	     //buttonFill.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	     buttonFill.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					resetClip_function();
				      Canvas.buttonFillPressed = true;
				      Canvas.buttonSelectPressed = false;
				      Canvas.buttonDrawPressed = false;
				      Canvas.buttonUnfillPressed = false;
				      Canvas.clearSelectedShapes();
				      Shapes.selectNone(Canvas.List_of_Shapes);
				      Main.newCanvas.repaint();
			}});
	     
	     
	     //creating and adding functionality to change color    
	     JLabel hue = new JLabel(HueIcon);
	     hue.setToolTipText("Current color chosen.");
	     JButton buttonColor;
	     ImageIcon color=new ImageIcon(getClass().getClassLoader().getResource("color.png"));
	     buttonColor=new JButton("Color",color);
	     buttonColor.setToolTipText("Choose a different color.");
	     //buttonColor.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	     buttonColor.addActionListener(new ActionListener() {
		 		@Override
		 		public void actionPerformed(ActionEvent e) {
			 			Color c;
			 			if((c = JColorChooser.showDialog(null, "Choose a color", Canvas.shape_color))!= null)
			 				 Canvas.shape_color = c;
					    for (int i = 0; i < Canvas.List_of_selected_Shapes.size(); i++) 
					    {
							Canvas.List_of_selected_Shapes.get(i).setColor(Canvas.shape_color);
						}
					    resetClip_function();
				       HueIcon.setColor(Canvas.shape_color);
				       hue.repaint();
				       strokeComboBox.repaint();
				       shapeTypeCombo.repaint();
				       Main.newCanvas.repaint();
	 		}});
	     
	     
	     //Creating and adding functionality of Unfill button
	     JButton buttonUnfill;
	     ImageIcon unfill=new ImageIcon(getClass().getClassLoader().getResource("nofill.png"));
	     buttonUnfill=new JButton("Unfill",unfill);
	     buttonUnfill.setToolTipText("Click on color-filled shapes to unfill them.");
	     //buttonUnfill.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	     buttonUnfill.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					resetClip_function();
				      Canvas.buttonUnfillPressed = true;
				      Canvas.buttonFillPressed = false;
				      Canvas.buttonSelectPressed = false;
				      Canvas.buttonDrawPressed = false;
				      Canvas.clearSelectedShapes();
				      Shapes.selectNone(Canvas.List_of_Shapes);
				      Main.newCanvas.repaint();
			}});
	      
	     // add jcomponents to the top menu bar
	     TopMenuContainer.add(buttonDraw);
	     TopMenuContainer.add(buttonSelect);
	     TopMenuContainer.add(buttonClear);
	     TopMenuContainer.add(buttonFill);
	     TopMenuContainer.add(buttonUnfill);
	     TopMenuContainer.add(buttonColor);     
	     TopMenuContainer.add(hue);
	     
	     RightMenuContainer_bottom.setOpaque(false);
	     RightMenuContainer.setOpaque(false);
	     
	     RightMenuContainer.add(RightMenuContainer_top);
	     RightMenuContainer.add(RightMenuContainer_bottom);
	     
	     TopMenu.add(TopMenuContainer);
	     RightMenu.add(RightMenuContainer, BorderLayout.CENTER);
	     
	 }
	 
	 public JPanel getTopMenu() {
		return TopMenu;
	}
	public void setTopMenu(JPanel topMenu) {
		TopMenu = topMenu;
	}
	public JPanel getRightMenu() {
		return RightMenu;
	}
	public void setRightMenu(JPanel rightMenu) {
		RightMenu = rightMenu;
	}
	
	
	// used in combobox to display text with a small icon next to it
	class ComplexCellRenderer implements ListCellRenderer {
		  protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		  public Component getListCellRendererComponent(JList list, Object value, int index,
		      boolean isSelected, boolean cellHasFocus) 
		  {

		    Color theForeground = null;
		    Icon theIcon = null;
		    String theText =null;

		    JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
		        isSelected, cellHasFocus);

		    if (value instanceof Object[]) {
		      Object values[] = (Object[]) value;
		      theIcon = (Icon) values[0];
		      theText = (String) values[1];
		    } 
		    if (!isSelected) {
		      renderer.setForeground(theForeground);
		    }
		    if (theIcon != null) {
		      renderer.setIcon(theIcon);
		    }
		    
		    renderer.setText(theText);
		    return renderer;
		  }
		}
	
	// Needed to display type of shapes in combobox
	class TypeIcon implements Icon {
		
		  private Shapes.Shape_Type type;
		  private Stroke stroke;
		  private int line_size = 1;
		  
		  
		  public Shapes.Shape_Type getType()
		  {
			  return type;
		  }
		  
		  public void setType(Shapes.Shape_Type y)
		  {
			  type = y;
		  }
		  public TypeIcon(Shapes.Shape_Type t) 
		  {
			  type = t;
		  }

		  public int getIconHeight() {
		    return 40;
		  }

		  public int getIconWidth() {
		    return 40;
		  }

		  public void paintIcon(Component c, Graphics g, int x, int y) {
		    Graphics2D ga = (Graphics2D)g;
	        ga.setColor(Canvas.shape_color);
	        
	        switch(Menu.choice)
				{
				case 0:
					stroke= new BasicStroke(line_size);	         	
					break;
				case 1:
					 stroke = new BasicStroke(line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[]{10}, 0.0f);
					break;
				case 2:

		         	 stroke = new BasicStroke(line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[]{3,4}, 0.0f);
		         	
					break;
				case 3:
					stroke = new BasicStroke(line_size, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[] {4,5,10,5}, 0.0f);		         
					break;		
				}
	        ga.setStroke(stroke);
		    switch(type)
		    {
		    	case Circle:
		    		Shape ci = new Ellipse2D.Double(5, 5, 30, 30);
	         		ga.draw(ci);
		    		break;
		    	case Line:
		    		ga.drawLine(5,5,35,35);
		    		break;
		    	case Irregular_Shape:
		    		Point p[] = {new Point(10,15), new Point(38,8), new Point(30, 35), new Point(4, 36), new Point(25,20)};
		    		 GeneralPath irr_shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD, p.length);
	                 irr_shape.moveTo(p[0].getX(), p[0].getY());

	                 for (int index = 1; index < p.length; index++) 
	                 {
	                	 irr_shape.lineTo(p[index].getX(), p[index].getY());      	
	                	// ga.draw(irr_shape);
	                 }
	                 irr_shape.closePath();
	                 
	                 
	                 ga.draw(irr_shape); 		
		    		break;
		    	case Regular_Shape:
		    		int sides = Canvas.no_of_sides;
		    		double theta = 2 * Math.PI / sides;
		 
		    		Polygon polygon = new Polygon();

		    		for(int i = 0; i < sides; ++i) {
		    		    double px = Math.cos(theta * i + Math.toRadians(270))*18 + 20;
		    		    double py = Math.sin(theta * i + Math.toRadians(270))*18 + 20;
		    		    polygon.addPoint((int)px, (int)py);
		    		}
		    		g.drawPolygon(polygon);
		    		break;
		    	case Free_Form:
		    		
	                ga.drawArc(5,5, 70, 30, 150, 110);
		    		break;
		    }
		   // g.drawRect(0, 0, getIconWidth(), getIconHeight());
		  }
		}
	
	
	// Needed for type of stroke icons
	class MyIcon implements Icon {
		
		  private Stroke st;
		
		  public Stroke getStroke()
		  {
			  return st;
		  }
		  public MyIcon(Stroke s) 
		  {
			  st = s;
		  }

		  public int getIconHeight() {
		    return 40;
		  }

		  public int getIconWidth() {
		    return 125;
		  }

		  public void paintIcon(Component c, Graphics g, int x, int y) {
			  Graphics2D ga = (Graphics2D)g;
		    g.setColor(Canvas.shape_color);
		    ga.setStroke(st);
		    g.drawLine(0, getIconHeight()/2, 150, getIconHeight()/2);
		  }
		}
	
	
	// Needed to display current color chosen in a square
	class ColorIcon implements Icon {
		
		  private Color color;
		
		  public Color getColor()
		  {
			  return color;
		  }
		  
		  public void setColor(Color c)
		  {
			  color = c;
		  }
		  public ColorIcon() 
		  {
			  
		  }

		  public int getIconHeight() {
		    return 42;
		  }

		  public int getIconWidth() {
		    return 42;
		  }

		  public void paintIcon(Component c, Graphics g, int x, int y) {
		    g.setColor(Canvas.shape_color);

		    g.fillRect(0, 0, getIconWidth(), getIconHeight());
		  }
		}
	
	
	
}
