
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.*;

import Transformations.*;




// Animation panel is where all the animation_transfomation_panel will be

public class Animation_panel extends JPanel{

	private JPanel Animation_MainPanel = new JPanel();
	private JPanel Main_container = new JPanel();
	
	public static JPanel LeftPanel = new JPanel();
	
	public static ArrayList<Timer> List_of_Timers = new ArrayList<Timer>();
	
	public static Color backgroundColor = new Color(109, 123, 145);
	
	public static Animation_transformation_panel SelectedPanel = null;
	
	private ArrayList<Animation_transformation_panel> List_of_Panels = new ArrayList<Animation_transformation_panel>();
	
	public static int frames = 100;
	
	public static int current_frame = 0;
	
	public static double valueHolder = 1000;
	public static double initialValue = 0 ;
	public static double increment = 0;
	
	public static double valueHolder_second = 1000;
	public static double initialValue_second = 0 ;
	public static double increment_second = 0;
	
	private String imagePath = "/images/";
	
	public ArrayList<Timer> getTimer() {
		return List_of_Timers;
	}

	public void setList_of_Timers( ArrayList<Timer> timer) {
		this.List_of_Timers = timer;
	}

	public static void applyTransformation (ArrayList<Point2D> points, Animation_transformation_panel a)
	{
		for (int i = 0; i < points.size() ; i++)
		{
			//System.out.printf("before : %d     ", (int)points.get(i).getX());
			double tempPoint[][] = new double[3][1];
			tempPoint[0][0] = points.get(i).getX();
			tempPoint[1][0] = points.get(i).getY();
			tempPoint[2][0] = 1;
			
			double resultPoints[][] = new double[3][1];
			
			resultPoints = Transformation.multiply3x3by3x1Matrices(a.getTransformation().getTransformationMatrix(), tempPoint);
			points.get(i).setLocation(resultPoints[0][0],resultPoints[1][0]);
			
			
		}
		//return points;
	};
	
	public static void applyTransformationForTimer (ArrayList<Point2D> points, Transformation a)
	{
		for (int i = 0; i < points.size() ; i++)
		{
			//System.out.printf("before : %d     ", (int)points.get(i).getX());
			double tempPoint[][] = new double[3][1];
			tempPoint[0][0] = points.get(i).getX();
			tempPoint[1][0] = points.get(i).getY();
			tempPoint[2][0] = 1;
			
			double resultPoints[][] = new double[3][1];
			
			resultPoints = Transformation.multiply3x3by3x1Matrices(a.getTransformationMatrix(), tempPoint);
			points.get(i).setLocation(resultPoints[0][0],resultPoints[1][0]);
			
			//System.out.printf("after : %d", (int)points.get(i).getX());
			//System.out.printf("\n");
		}
		//return points;
	};
	
	public static void selectPanel(Animation_transformation_panel a)
	{
		current_frame = 0;
		
		if (SelectedPanel != null)
		{
			SelectedPanel.getCenter_Panel().setBackground(Color.LIGHT_GRAY);		
			SelectedPanel = a;
			SelectedPanel.getCenter_Panel().setBackground(Color.black);
		}
		else
		{
			SelectedPanel = a;
			SelectedPanel.getCenter_Panel().setBackground(Color.black);
		}
		
		if (Canvas.List_of_selected_Shapes.size() > 0)
		{
			Canvas.previewShape.copyShape(Canvas.List_of_selected_Shapes.get(0));			
			Canvas.animatedPreviewShape.copyShape(Canvas.List_of_selected_Shapes.get(0));
			Canvas.previewShape_second.copyShape(Canvas.List_of_selected_Shapes.get(0));
			
			
			if (a.getIndex() > 0)
			{
				for(int i = 0 ; i < a.getIndex(); i++)
				{
					applyTransformation(Canvas.previewShape.getPoints(), Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().get(i));
					applyTransformation(Canvas.previewShape_second.getPoints(), Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().get(i));
				}
			}
			Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
			
			applyTransformation(Canvas.previewShape.getPoints(), a);
			
			/*double differencesArray[][] = new double[Canvas.List_of_selected_Shapes.get(0).getPoints().size()][2];
			
			for(int i = 0 ; i < Canvas.List_of_selected_Shapes.get(0).getPoints().size(); i++)
			{
				differencesArray[i][0] = ((Canvas.previewShape.getPoints().get(i).getX() - Canvas.List_of_selected_Shapes.get(0).getPoints().get(i).getX()) / frames);
				differencesArray[i][1] = ((Canvas.previewShape.getPoints().get(i).getY() - Canvas.List_of_selected_Shapes.get(0).getPoints().get(i).getY()) / frames);
				System.out.printf("zzz%d   ", (int)Canvas.previewShape.getPoints().get(i).getX());
				System.out.printf("xxx%d   ", (int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(i).getX());
				System.out.printf("%d   ", (int)differencesArray[i][0]);
			}
			
			current_frame = 0;
			*/
			
			switch(a.getTransformation().getType())
			{
				case Translation : 
					valueHolder = ((Translation)a.getTransformation()).getX();
					initialValue = 0;
					increment = (valueHolder - initialValue) / frames;
					
					valueHolder_second = ((Translation)a.getTransformation()).getY();
					initialValue_second = 0;
					increment_second = (valueHolder_second - initialValue_second) / frames;
					
					break;
					
				case Rotation :
					
					valueHolder = ((Rotation)a.getTransformation()).getAngle();
					initialValue = 0;
					increment = (valueHolder - initialValue) / frames;
					
					break;
					
				case Rotation_fixed_point :
					
					valueHolder = ((Rotation_fixed_point)a.getTransformation()).getAngle();
					initialValue = 0;
					increment = (valueHolder - initialValue) / frames;
					
					break;
					
				case Scaling :
					/*
					valueHolder = ((Scaling)a.getTransformation()).getScaleX();
					initialValue = 1;
					increment = ((valueHolder - 1) / 80) + 1 ;
					
					valueHolder_second = ((Scaling)a.getTransformation()).getScaleY();
					initialValue_second = 1;
					increment_second = ((valueHolder_second - 1) / 80) + 1;
					*/
					valueHolder = ((Scaling)a.getTransformation()).getScaleX();
					initialValue = 1;
					increment = (valueHolder - initialValue) / frames;
					
					valueHolder_second = ((Scaling)a.getTransformation()).getScaleY();
					initialValue_second = 1;
					increment_second = (valueHolder_second - initialValue_second) / frames;
					break;
					
				case Scaling_fixed_point :
					
					valueHolder = ((Scaling_fixed_point)a.getTransformation()).getScaleX();
					initialValue = 1;
					increment = (valueHolder - initialValue) / frames;
					
					valueHolder_second = ((Scaling_fixed_point)a.getTransformation()).getScaleY();
					initialValue_second = 1;
					increment_second = (valueHolder_second - initialValue_second) / frames;
					
					break;
					
					/*
				case Reflection :
						
					valueHolder = ((Reflection)a.getTransformation()).getAngle();
					initialValue = 0;
					increment = (valueHolder - initialValue) / frames;
		
					
					break;
					*/
				case Shear :
					
					valueHolder = ((Shear)a.getTransformation()).getX();
					initialValue = 0;
					increment = (valueHolder - initialValue) / frames;
					
					valueHolder_second = ((Shear)a.getTransformation()).getY();
					initialValue_second = 0;
					increment_second = (valueHolder_second - initialValue_second) / frames;
					
					break;
					
			}
		
			
			//Animated preview
			 if (List_of_Timers.size() > 0)
			 {
				 List_of_Timers.get(0).stop();
				 List_of_Timers.clear();
			 }
			 
			 
			Timer timer = new Timer(20, new ActionListener(){
				public void actionPerformed (ActionEvent e) {
					
					current_frame++;
					//System.out.printf("%d \n", current_frame);
					
					switch(a.getTransformation().getType())
					{
						case Rotation_fixed_point :	
						case Rotation :
							initialValue = initialValue + increment;
							
							if (increment > 0)
							{
								if(initialValue >= valueHolder)
								{
									initialValue = 0;
									initialValue_second = 0;
									Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
									current_frame = 0;
								}
							}
							else 
							{
								if(initialValue < valueHolder)
								{
									initialValue = 0;
									initialValue_second = 0;
									Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
									current_frame = 0;
								}
							}
							
							break;
							
						case Translation : 
							
							initialValue = initialValue + increment;
							initialValue_second = initialValue_second + increment_second;
							
							if (increment > 0)
							{
								if(initialValue >= valueHolder)
								{
									initialValue = 0;
									initialValue_second = 0;
									Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
									current_frame = 0;
								}
							}
							else 
							{
								if(initialValue < valueHolder)
								{
									initialValue = 0;
									initialValue_second = 0;
									Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
									current_frame = 0;
								}
							}
						
							if (increment_second > 0)
							{
								if(initialValue_second >= valueHolder_second)
								{
									initialValue = 0;
									initialValue_second = 0;
									Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
									current_frame = 0;
								}
							}
							else 
							{
								if (increment_second < 0) {
									if(initialValue_second < valueHolder_second)
									{
										initialValue = 0;
										initialValue_second = 0;
										Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
										current_frame = 0;
									}
								}
							}
							break;
							
						case Scaling :
						case Scaling_fixed_point :	
							
							initialValue = initialValue + increment;
							initialValue_second = initialValue_second + increment_second;
							
							Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
							
							if (increment > 0)
							{
								if(initialValue >= valueHolder)
								{
									current_frame = 0;
									initialValue = 1;
									initialValue_second = 1;
									
								}
							}
							else 
							{
								if(initialValue < valueHolder)
								{
									current_frame = 0;
									initialValue = 1;
									initialValue_second = 1;
									
								}
							}
						
							if (increment_second > 0)
							{
								if(initialValue_second >= valueHolder_second)
								{
									current_frame = 0;
									initialValue = 1;
									initialValue_second = 1;
									
								}
							}
							else 
							{
								if (increment_second < 0) {
									if(initialValue_second < valueHolder_second)
									{
										current_frame = 0;
										initialValue = 1;
										initialValue_second = 1;
										
									}
								}
							}
							
							break;
						
							/*
						case Reflection :
							
							initialValue = initialValue + increment;
							
							Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
							
							if (increment > 0)
							{
								if(initialValue >= valueHolder)
								{
									current_frame = 0;
									initialValue = 0;			
								}
							}
							else 
							{
								if(initialValue < valueHolder)
								{
									current_frame = 0;
									initialValue = 0;					
								}
							}
							
							break;
							*/
						case Shear :
							
							initialValue = initialValue + increment;
							initialValue_second = initialValue_second + increment_second;
							
							Canvas.animatedPreviewShape.copyShape(Canvas.previewShape_second);
							
							if (increment > 0)
							{
								if(initialValue >= valueHolder)
								{
									current_frame = 0;
									initialValue = 0;
									initialValue_second = 0;
									
								}
							}
							else 
							{
								if(initialValue < valueHolder)
								{
									current_frame = 0;
									initialValue = 0;
									initialValue_second = 0;
									
								}
							}
						
							if (increment_second > 0)
							{
								if(initialValue_second >= valueHolder_second)
								{
									current_frame = 0;
									initialValue = 0;
									initialValue_second = 0;
									
								}
							}
							else 
							{
								if (increment_second < 0) {
									if(initialValue_second < valueHolder_second)
									{
										current_frame = 0;
										initialValue = 0;
										initialValue_second = 0;
										
									}
								}
							}
							
							break;
							
					}

					switch(a.getTransformation().getType())
					{
						case Rotation_fixed_point :
							Transformation tempTransformation_Rot_fix = new Rotation_fixed_point(increment, ((Rotation_fixed_point)a.getTransformation()).getxOffset() , ((Rotation_fixed_point)a.getTransformation()).getyOffset());					
							applyTransformationForTimer	(Canvas.animatedPreviewShape.getPoints(), tempTransformation_Rot_fix);
							break;
							
						case Translation : 
							
							Transformation tempTransformation_Trans = new Translation(increment, increment_second);					
							applyTransformationForTimer	(Canvas.animatedPreviewShape.getPoints(), tempTransformation_Trans);		
							break;
							
						case Rotation :
							
							Transformation tempTransformation_Rot = new Rotation(increment, Grid.grid_center_point.x, Grid.grid_center_point.y);					
							applyTransformationForTimer	(Canvas.animatedPreviewShape.getPoints(), tempTransformation_Rot);	
							break;
							
						case Scaling :
							
							Transformation tempTransformation_Scal = new Scaling(initialValue, initialValue_second, Grid.grid_center_point.x, Grid.grid_center_point.y);						
							applyTransformationForTimer	(Canvas.animatedPreviewShape.getPoints(), tempTransformation_Scal);	
							
							
							break;
						
							
						case Scaling_fixed_point :
							
							Transformation tempTransformation_Scal_fix = new Scaling_fixed_point(initialValue, initialValue_second, ((Scaling_fixed_point)a.getTransformation()).getxOffset() , ((Scaling_fixed_point)a.getTransformation()).getyOffset());					
							applyTransformationForTimer	(Canvas.animatedPreviewShape.getPoints(), tempTransformation_Scal_fix);	
						
							break;
							
							/*
						case Reflection :
							
							Transformation tempTransformation_Ref = new Reflection(initialValue, ((Reflection)a.getTransformation()).getxOffset() , ((Reflection)a.getTransformation()).getyOffset());																
							applyTransformationForTimer	(Canvas.animatedPreviewShape.getPoints(), tempTransformation_Ref);
							
							break;
							*/
							
						case Shear :
							
							Transformation tempTransformation_Shear = new Shear(initialValue, initialValue_second , ((Shear)a.getTransformation()).getxOffset() , ((Shear)a.getTransformation()).getyOffset());																
							applyTransformationForTimer	(Canvas.animatedPreviewShape.getPoints(), tempTransformation_Shear);
							
							break;
					}
					
					
					Main.newCanvas.repaint();
					
					
				}
			
			});
			
			List_of_Timers.add(timer);
			List_of_Timers.get(0).start();
			Main.newCanvas.repaint();
			LeftPanel.revalidate();
			
		}
		
	}
	
	
	
	
	public static void unselectPanel()
	{
		if (List_of_Timers.size() > 0)
		 {
			 List_of_Timers.get(0).stop();
			 List_of_Timers.clear();
		 }
		
		if (SelectedPanel != null)
		{
			SelectedPanel.getCenter_Panel().setBackground(Color.LIGHT_GRAY);
			
			SelectedPanel = null;
		}
		else
		{
		}
		LeftPanel.revalidate();
	}
	
	public JPanel getMain_container() {
		return Main_container;
	}


	public void setMain_container(JPanel main_container) {
		Main_container = main_container;
	}


	public JPanel getAnimation_MainPanel() {
		return Animation_MainPanel;
	}


	public void setAnimation_MainPanel(JPanel animation_MainPanel) {
		Animation_MainPanel = animation_MainPanel;
	}


	public static void updatePanel(Shapes shape) {
		
		LeftPanel.removeAll();
		
		
		
		for(int i = 0 ; i < shape.getAnimation_panel_list().size() ; i++)
		{
			//List_of_Panels.add(shape.getAnimation_panel_list().get(i));
			LeftPanel.add(shape.getAnimation_panel_list().get(i));
			
		}
		
		LeftPanel.revalidate();
	
	}
	
	
	Animation_panel(){
		
		this.setOpaque(true);
		//this.setSize(700, 700);
		this.setBackground(Color.BLACK);
		//this.setPreferredSize(new Dimension(1000,200));
		//this.setMaximumSize(new Dimension(1000,200));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//this.setLayout(new GridLayout(1,10));
		//this.setLayout(new FlowLayout());
		
	//	this.getMain_container().setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getMain_container().setLayout(new BorderLayout());
		//this.getMain_container().setLayout(new WrapLayout());
		//this.getAnimation_MainPanel().setPreferredSize(new Dimension(800,600));
		this.getAnimation_MainPanel().setBackground(backgroundColor);
		this.getAnimation_MainPanel().setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		LeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		LeftPanel.setBackground(backgroundColor);
		JPanel RightPanel = new JPanel();
		
		
		// plus Panel to add other transformations 
		
		JPanel Add_transformation_panel = new JPanel();
		Add_transformation_panel.setPreferredSize(new Dimension(190,150));
		Add_transformation_panel.setLayout(new BorderLayout());
		JPanel Add_transformation_Top_panel = new JPanel();
		JPanel Add_transformation_Center_panel = new JPanel();
		
		
		
		
		// JCOMPONENTS IN TOP OF Add_transformation_panel
		// JCOMBOBOX
		ListCellRenderer renderer = new ComplexCellRenderer();
		
		Object TypeElements[][] = new Object[7][1];

	     
	      for (Transformation.Type t : Transformation.Type.values()) {
	    	  
	    	 TypeElements[t.ordinal()][0] = t.toString().replaceAll("_", " ");
	     	// TypeElements[t.ordinal()][1] = new TypeIcon(t);
	     	 
	      }
		
		JComboBox Transformation_typeCombo = new JComboBox(TypeElements);
		Transformation_typeCombo.setPreferredSize(new Dimension(180,25));
		Transformation_typeCombo.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		Transformation_typeCombo.setBackground(Color.white);
		Transformation_typeCombo.setRenderer(renderer);
		
		Add_transformation_Top_panel.add(Transformation_typeCombo);
		
		
		
		// JCOMPONENTS IN CENTER OF Add_transformation_panel
		// Button to add another transformation
		
		// Creating the button
		JButton Transformation_add_button = new JButton("<html><p style=\"font-size : 50px; text-align: center;\" >+</p><p style=\"font-size : 11.5px; text-align: center; \" >Add Transformation</p></html>");
		Transformation_add_button.setPreferredSize(new Dimension(180,110));
		//Transformation_add_button.setBackground(Color.red);
		//Transformation_add_button.setFont(new Font("TimesRoman", Font.BOLD, 20));
		Transformation_add_button.setForeground(Color.GRAY);
		
		
		// Functionality of the button
		Transformation_add_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int chosen = Transformation_typeCombo.getSelectedIndex();
				
				
				Transformation temp;
				
				switch (chosen) {
				
				
				
				case 0: // Translation
					if (Canvas.List_of_selected_Shapes.size() > 0)
					{
					temp = new Translation(0,0);					
					Animation_transformation_panel TranslationPanel = new Animation_transformation_panel(temp);
					
						Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().add(TranslationPanel);
					
					TranslationPanel.getTransformation_remove_button().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().remove(TranslationPanel);
							Canvas.List_of_selected_Shapes.get(0).reIndex_List_of_transformation();
							LeftPanel.remove(TranslationPanel);
							getMain_container().revalidate();
							}
						});
					
					LeftPanel.add(TranslationPanel);
					}
					
					break;
				case 1: // Rotation
					if (Canvas.List_of_selected_Shapes.size() > 0)
					{
					temp = new Rotation(0, Grid.grid_center_point.x, Grid.grid_center_point.y);
					
					Animation_transformation_panel RotationPanel = new Animation_transformation_panel(temp);
					RotationPanel.getxOffset_JSpinner().setValue(Grid.grid_center_point.x);
					RotationPanel.getyOffset_JSpinner().setValue(Grid.grid_center_point.y);
						Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().add(RotationPanel);
					
					RotationPanel.getTransformation_remove_button().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().remove(RotationPanel);
							Canvas.List_of_selected_Shapes.get(0).reIndex_List_of_transformation();
							LeftPanel.remove(RotationPanel);
							getMain_container().revalidate();
							}
						});
					
					
					LeftPanel.add(RotationPanel);
					}
					break;
				case 2: // Rotation_fixed_point
					if (Canvas.List_of_selected_Shapes.size() > 0)
					{
					temp = new Rotation_fixed_point(0,(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getX(),(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getY());
					Animation_transformation_panel Rotation_fixed_pointPanel = new Animation_transformation_panel(temp);
						
					Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().add(Rotation_fixed_pointPanel);
					Rotation_fixed_pointPanel.getTransformation_remove_button().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().remove(Rotation_fixed_pointPanel);
							Canvas.List_of_selected_Shapes.get(0).reIndex_List_of_transformation();
							LeftPanel.remove(Rotation_fixed_pointPanel);
							getMain_container().revalidate();
							}
						});
					
					
						
						
					LeftPanel.add(Rotation_fixed_pointPanel);
					}
					break;
				case 3: // Scaling
					
					if (Canvas.List_of_selected_Shapes.size() > 0)
					{
					temp = new Scaling(1,1, Grid.grid_center_point.x, Grid.grid_center_point.y);	
					Animation_transformation_panel ScalingPanel = new Animation_transformation_panel(temp);
					ScalingPanel.getxOffset_JSpinner().setValue(Grid.grid_center_point.x);
					ScalingPanel.getyOffset_JSpinner().setValue(Grid.grid_center_point.y);
						Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().add(ScalingPanel);
						
						ScalingPanel.getTransformation_remove_button().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().remove(ScalingPanel);
								Canvas.List_of_selected_Shapes.get(0).reIndex_List_of_transformation();
								LeftPanel.remove(ScalingPanel);
								getMain_container().revalidate();
								}
							});
						
					
					LeftPanel.add(ScalingPanel);
					}
					
					break;
				case 4: // Scaling_fixed_point
					if (Canvas.List_of_selected_Shapes.size() > 0)
					{
					temp = new Scaling_fixed_point(1,1,(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getX(),(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getY());
					Animation_transformation_panel Scaling_fixed_pointPanel = new Animation_transformation_panel(temp);
						Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().add(Scaling_fixed_pointPanel);
						Scaling_fixed_pointPanel.getTransformation_remove_button().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().remove(Scaling_fixed_pointPanel);
								Canvas.List_of_selected_Shapes.get(0).reIndex_List_of_transformation();
								LeftPanel.remove(Scaling_fixed_pointPanel);
								getMain_container().revalidate();
								}
							});
					
					LeftPanel.add(Scaling_fixed_pointPanel);
					}
					
					break;
				case 5: // Reflection
					if (Canvas.List_of_selected_Shapes.size() > 0)
					{
					temp = new Reflection(0,(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getX(),(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getY());
					Animation_transformation_panel ReflectionPanel = new Animation_transformation_panel(temp);
					
						Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().add(ReflectionPanel);
						ReflectionPanel.getTransformation_remove_button().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().remove(ReflectionPanel);
								Canvas.List_of_selected_Shapes.get(0).reIndex_List_of_transformation();
								LeftPanel.remove(ReflectionPanel);
								getMain_container().revalidate();
								}
							});
					
					LeftPanel.add(ReflectionPanel);
					}
					break;
				case 6: // Shear
					if (Canvas.List_of_selected_Shapes.size() > 0)
					{
					temp = new Shear(0,0,(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getX(),(int)Canvas.List_of_selected_Shapes.get(0).getPoints().get(0).getY());;
					Animation_transformation_panel ShearPanel = new Animation_transformation_panel(temp);
						Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().add(ShearPanel);
						ShearPanel.getTransformation_remove_button().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().remove(ShearPanel);
								Canvas.List_of_selected_Shapes.get(0).reIndex_List_of_transformation();
								LeftPanel.remove(ShearPanel);
								getMain_container().revalidate();
								}
							});
					
					LeftPanel.add(ShearPanel);
					}
					break;
				}
				if (Canvas.List_of_selected_Shapes.size() > 0)
				{
				Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().get(Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().size() - 1).setIndex(Canvas.List_of_selected_Shapes.get(0).getAnimation_panel_list().size() - 1);
				}
				getMain_container().revalidate();		
			}});
		
		Add_transformation_Center_panel.add(Transformation_add_button);
		
		// Add top panel and center panel to Add_transformation_panel
		Add_transformation_panel.add(Add_transformation_Top_panel, BorderLayout.NORTH);
		Add_transformation_panel.add(Add_transformation_Center_panel, BorderLayout.CENTER);
		
		
		
		
		RightPanel.add(Add_transformation_panel);
		
		this.getAnimation_MainPanel().add(LeftPanel);
		this.getAnimation_MainPanel().add(RightPanel);
		
		JScrollPane scrollPane=new JScrollPane(this.getAnimation_MainPanel(), 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		
		scrollPane.setPreferredSize(new Dimension(1200,180));
		
		// TOP BAR CONTAINING PLAY BUTTONS AND OTHERS..
		JPanel Top_bar = new JPanel();
		Top_bar.setLayout(new BorderLayout());
		
		JPanel Top_bar_buttons = new JPanel();
		Top_bar_buttons.setBackground(Color.BLACK);
		Top_bar_buttons.setLayout(new FlowLayout());
		
		//creating play buttons
		ImageIcon play=new ImageIcon(getClass().getClassLoader().getResource("play.png"));
		ImageIcon pause=new ImageIcon(getClass().getClassLoader().getResource("pause.png"));
		JButton Top_bar_buttons_play = new JButton("  play  ",play);
		
		JButton Top_bar_buttons_fast_play = new JButton("fast play");
		JButton Top_bar_buttons_end_play = new JButton("end play");
		
		
		//adding functionality of play button
		Top_bar_buttons_play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// if it is visible, hide it
				if (Top_bar_buttons_play.getText() == "  play  ")	{			
					
					for ( int i = 0; i < Canvas.List_of_Shapes.size() ; i++)
					{
						if (Canvas.List_of_Shapes.get(i).getCurrent_frame() == 0)
							Shapes.copyPoints(Canvas.List_of_Shapes.get(i).getPoints(), Canvas.List_of_Shapes.get(i).getOriginal_points());
						
						Canvas.List_of_Shapes.get(i).updateAnimationInfo(); 
						Canvas.List_of_Shapes.get(i).getTimer().restart();
					}
					Top_bar_buttons_play.setIcon(pause);
					Top_bar_buttons_play.setText("pause");					
				}
				// else make it visible
				else {		
					
					for ( int i = 0; i < Canvas.List_of_Shapes.size() ; i++)
					{
						 Canvas.List_of_Shapes.get(i).getTimer().stop();
					}
					
					Top_bar_buttons_play.setIcon(play);
					Top_bar_buttons_play.setText("  play  ");
				}
			}});
		
		//adding functionality of fast play button
		Top_bar_buttons_fast_play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// if it is visible, hide it
				if (Top_bar_buttons_play.getText() == "  play  ")	{			
					
					
					
					Top_bar_buttons_play.setText("pause");					
				}
				// else make it visible
				else {		
					
					
					
					Top_bar_buttons_play.setText("  play  ");
				}
			}});
		
		
		//adding functionality of end play button
		Top_bar_buttons_end_play.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						
					
					if (Top_bar_buttons_play.getText() == "  play  ")	{			
							
							
						
						
							
						Top_bar_buttons_play.setText("pause");					
					}
						
					else {		
							
							
							
						Top_bar_buttons_play.setText("  play  ");
					}
				}});
		
		
		
		//adding buttons
		Top_bar_buttons.add(Top_bar_buttons_play);
		//Top_bar_buttons.add(Top_bar_buttons_fast_play);
		//Top_bar_buttons.add(Top_bar_buttons_end_play);
		
		//creating toggle bar
		JPanel Top_bar_toggle = new JPanel();
		Top_bar_toggle.setLayout(new GridLayout(1,1));
		Top_bar_toggle.setBackground(Color.lightGray);
		
		JButton Top_bar_toggle_button = new JButton("v          Animations          v");
		
		Top_bar_toggle_button.setToolTipText("Hides animation panel.");
		
		//adding functionality of toggle bar
		Top_bar_toggle_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// if it is visible, hide it
				if (scrollPane.isVisible())	{			
					scrollPane.setVisible(false);
					Top_bar_toggle_button.setText("^          Animations          ^");
					Top_bar_toggle_button.setToolTipText("Shows animation panel.");
				}
				// else make it visible
				else {
					scrollPane.setVisible(true);
					Top_bar_toggle_button.setText("v          Animations          v");
					Top_bar_toggle_button.setToolTipText("Hides animation panel.");
				}
			}});
		
		
		
		
		Top_bar_toggle.add(Top_bar_toggle_button);
		
		Top_bar.add(Top_bar_buttons, BorderLayout.EAST);
		Top_bar.add(Top_bar_toggle, BorderLayout.CENTER);
		
		this.getMain_container().add(Top_bar, BorderLayout.NORTH);
		this.getMain_container().add(scrollPane, BorderLayout.CENTER);
		
		
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(700, 700);
    }
	public void add_animation_transformation_panel(Animation_transformation_panel a) {
		
		this.getAnimation_MainPanel().add(a);
		
	}
	
	
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
		      //theIcon = (Icon) values[0];
		      theText = (String) values[0];
		    } 
		    
		    if (!isSelected) {
		      renderer.setForeground(theForeground);
		    }
		    if (theIcon != null) {
		      //renderer.setIcon(theIcon);
		    }
		    
		    renderer.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		    renderer.setText(theText);
		    return renderer;
		  }
		}
	
}
