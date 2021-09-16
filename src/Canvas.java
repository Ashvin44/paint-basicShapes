

import java.awt.AlphaComposite;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Transformations.Transformation;
import Transformations.*;



public class Canvas extends JPanel implements MouseListener, MouseWheelListener, MouseMotionListener {

	
	
	public static ArrayList<Shapes> List_of_Shapes = new ArrayList<Shapes>();
	public static ArrayList<Shapes> List_of_selected_Shapes = new ArrayList<Shapes>();
	
	public Point mousePointPressed= new Point();
	public static Point mousePointDragged = new Point(2, 2);
	public Point mousePointMoved = new Point(2,2);
	public Point difference = new Point(0, 0);
	
	public static Color shape_color = (new Color(22, 140, 224));
	public static Shapes.Shape_Type typeSelected = Shapes.Shape_Type.Circle;
	public static int no_of_clicks = 0;
	public static int no_of_movements = 0;
	public static int line_size = 2;
	public static int no_of_sides = 6;
	public static Stroke canvas_stroke = new BasicStroke(line_size);
	
	public int scale_counter = 0;
	public static double canvas_scale = 0.999999;
	public static double canvas_prev_scale = canvas_scale;
	public static double xOffset = 0;
	public static double yOffset = 0;
	public static boolean zoom = false;
	
	public Grid grid = new Grid();
	
	public static boolean buttonDrawPressed = false;
	public static boolean buttonSelectPressed = true;
	public static boolean buttonFillPressed = false;
	public static boolean buttonUnfillPressed = false;
	public static boolean buttonClipPressed = false;
	
	public static Shapes previewShape = new Shapes();
	public static Shapes previewShape_second = new Shapes();
	public static Shapes animatedPreviewShape = new Shapes();
	
	public static Shapes centerPoint_previewShape = new Shapes(Color.red, Shapes.Shape_Type.Circle, new BasicStroke(4), 4);
	
	Canvas() {
		this.setOpaque(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this); 
		
		this.setBackground(Color.white);
	}
	
	
	
	public static void clearSelectedShapes()
	{
		for (int i = 0; i < List_of_selected_Shapes.size(); i++) {
			List_of_selected_Shapes.get(i).setSelected(false);
		}
		List_of_selected_Shapes.clear();
	}
	
	
	public int rescalePointX(int p) {
		p -= xOffset;
		p /= canvas_scale;
		return p;
	}
	public int rescalePointY(int p) {
		p -= yOffset;
		p /= canvas_scale;
		return p;
	}
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
		Point n = e.getPoint();
		
		n.y -= yOffset;
		n.x -= xOffset;
		n.x /= canvas_scale;
		n.y /= canvas_scale;

		mousePointPressed = n;
		mousePointDragged = n;
		
		
		// If Draw button has been pressed
		if (buttonDrawPressed) {
			
			
			// Irregular Shapes
			if (typeSelected == Shapes.Shape_Type.Irregular_Shape) {
				no_of_clicks++;
				if (no_of_clicks == 1) {
					Shapes a = new Shapes(shape_color, typeSelected, canvas_stroke, line_size);
					a.addPoint(new Point(mousePointPressed));
					List_of_Shapes.add(a);

				} else if (no_of_clicks == no_of_sides) {
					List_of_Shapes.get(List_of_Shapes.size() - 1).addPoint(new Point(mousePointPressed));
					e.getComponent().repaint();
					no_of_clicks = 0;
					
				} else if (no_of_clicks < no_of_sides) {
					List_of_Shapes.get(List_of_Shapes.size() - 1).addPoint(new Point(mousePointPressed));
					e.getComponent().repaint();
				}
			}
			
			
			// Line
			else if (typeSelected == Shapes.Shape_Type.Line)
	            {
	               
						Shapes a = new Shapes(shape_color, typeSelected, canvas_stroke, line_size);
	                    a.addPoint(mousePointPressed);
	                    a.addPoint(new Point(mousePointPressed.x, mousePointPressed.y));
	                    List_of_Shapes.add(a);
	  
	            }
			
			//circle
			else if (typeSelected == Shapes.Shape_Type.Circle)
            {
						Shapes a = new Shapes(shape_color, typeSelected, canvas_stroke, line_size);
	                    List_of_Shapes.add(a);	              
	        }
			
			// Regular shapes, free form
			else {
				no_of_clicks = 0;
				no_of_movements = 0;
				Shapes a = new Shapes(shape_color, typeSelected, canvas_stroke, line_size);
				a.addPoint(new Point(mousePointPressed));
				List_of_Shapes.add(a);
			
			}
		}
		
		
		
		// if the user selects one of the shapes
		if (buttonSelectPressed) {
			
				if (Shapes.selectOne(List_of_Shapes, mousePointPressed) == false)			
				{
					clearSelectedShapes();
				}	
		}
		
		
		
		// if the Fill button has been pressed
		if (buttonFillPressed) {			
			if (Shapes.selectOne(List_of_Shapes, mousePointPressed) == false)			
			{
				clearSelectedShapes();
			}
			else
			{
				List_of_selected_Shapes.get(0).setColor_fill(shape_color);
				List_of_selected_Shapes.get(0).setFill(true);
				//refreshResizors(List_of_selected_Shapes.get(0).getBox());
			}			
		}
		
		
		
		// if the Unfill button has been pressed
		if (buttonUnfillPressed) {
			
			if (Shapes.selectOne(List_of_Shapes, mousePointPressed) == false)			
			{
				clearSelectedShapes();
			}
			else
			{
				List_of_selected_Shapes.get(0).setFill(false);
				//refreshResizors(List_of_selected_Shapes.get(0).getBox());
			}
		}
		
		
		//if the Clip button has been pressed
		if (buttonClipPressed) {
			Point ne = e.getPoint();

			ne.y -= yOffset;
			ne.x -= xOffset;
			ne.x /= canvas_scale;
			ne.y /= canvas_scale;
			
			Menu.clipPoint1.x = ne.x;
			Menu.clipPoint1.y = ne.y;
			Menu.clipPoint2.x = ne.x;
			Menu.clipPoint2.y = ne.y;
		}
		
		e.getComponent().repaint();
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		// If the draw button was pressed
		if (buttonDrawPressed) {
			
			// Drawing free form shape
			if (typeSelected == Shapes.Shape_Type.Free_Form) { 
				
				no_of_movements++;
				
				if (no_of_movements > 5)
				{
				Point ne = e.getPoint();
				//ne.setLocation(e.getPoint());
				ne.y -= yOffset;
				ne.x -= xOffset;
				ne.x /= canvas_scale;
				ne.y /= canvas_scale;
				
				List_of_Shapes.get(List_of_Shapes.size() - 1).addPoint(new Point(ne));
				
				no_of_movements = 0;
				}
			}
			
			
			// Drawing regular shape
			if (typeSelected == Shapes.Shape_Type.Regular_Shape) {
				Point ne = e.getPoint();
				//ne.setLocation(e.getPoint());
				ne.y -= yOffset;
				ne.x -= xOffset;
				ne.x /= canvas_scale;
				ne.y /= canvas_scale;
				double bufX = 0.0;
				double bufY = 0.0;
				
				while(List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().size() < no_of_sides)
					List_of_Shapes.get(List_of_Shapes.size() - 1).addPoint(new Point(ne));
				
				
				List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(0).setLocation(ne.x, ne.y);

				for(int i = 1; i < no_of_sides; i++)
				{
					bufX = mousePointPressed.x + (List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(i - 1).getX() -  mousePointPressed.x)*Math.cos(Math.toRadians(360.0/no_of_sides)) - (List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(i - 1).getY() -  mousePointPressed.y)*Math.sin(Math.toRadians(360.0/no_of_sides));
					bufY = mousePointPressed.y + (List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(i - 1).getX() -  mousePointPressed.x)*Math.sin(Math.toRadians(360.0/no_of_sides)) + (List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(i - 1).getY() -  mousePointPressed.y)*Math.cos(Math.toRadians(360.0/no_of_sides));
					List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(i).setLocation(bufX, bufY);
				
				}
				
			}
			
			
			// Drawing line
			else if (typeSelected == Shapes.Shape_Type.Line)
			{
				Point ne = e.getPoint();

				ne.y -= yOffset;
				ne.x -= xOffset;
				ne.x /= canvas_scale;
				ne.y /= canvas_scale;
				
				List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(1).setLocation(ne.x, ne.y);
				List_of_Shapes.get(List_of_Shapes.size() - 1).getOriginal_points().get(1).setLocation(ne.x, ne.y);
				e.getComponent().repaint();
			}
			
			
			
			// Drawing circle using bresenham algorithm
			else if (typeSelected == Shapes.Shape_Type.Circle)
			{
				
				
				List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().clear();
				
				
				Point ne = e.getPoint();

				ne.y -= yOffset;
				ne.x -= xOffset;
				ne.x /= canvas_scale;
				ne.y /= canvas_scale;
				
				//int xc1 = (int)List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(0).getX();
	           // int yc1 = (int)List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().get(0).getY();
				int xc1 = mousePointPressed.x;
				int yc1 = mousePointPressed.y;
	            int xc2 = (int)ne.x;
	            int yc2 = (int)ne.y;
	            
	            double r ;
	            r = Math.sqrt( ((xc2-xc1)*(xc2-xc1))+((yc2-yc1)*(yc2-yc1)) );
	              
	            int R = (int) r;
	            int xd=0,yd=R;
	            int d=1-R;
	            int c1=3, c2=5-2*R;
	           
	            //List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+xd, yc1+yd));
	            //List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-xd, yc1+yd));
	           // List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-xd, yc1-yd));
	           // List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+xd, yc1-yd));
	              
	          //  List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+yd, yc1+xd));
	          //  List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-yd, yc1+xd));
	          //  List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-yd, yc1-xd));
	           // List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+yd, yc1-xd));
	           
	            
	            //create 8 temporary arraylist to store points
	            ArrayList<Point> tempArray1 = new ArrayList<Point>();
	            ArrayList<Point> tempArray2 = new ArrayList<Point>();
	            ArrayList<Point> tempArray3 = new ArrayList<Point>();
	            ArrayList<Point> tempArray4 = new ArrayList<Point>();
	            ArrayList<Point> tempArray5 = new ArrayList<Point>();
	            ArrayList<Point> tempArray6 = new ArrayList<Point>();
	            ArrayList<Point> tempArray7 = new ArrayList<Point>();
	            ArrayList<Point> tempArray8 = new ArrayList<Point>();
	            
	            while(xd<yd)
	            {
	                 if(d<0)
	                 {
	                          d+=c1;
	                          c2+=2;
	                 }
	                 else
	                 {
	                          d+=c2;
	                          c2+=4;
	                          yd--;
	                 }
	                 c1+=2;
	                 xd++;
	                          
	                 // points bottom right close to bottom
	                // List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+xd, yc1+yd));
	                 tempArray1.add(new Point(xc1+xd, yc1+yd));
	                 
	                //points right but a bit bottom
	                // List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+yd, yc1+xd));
	                 tempArray2.add(new Point(xc1+yd, yc1+xd));
	                 
	                //points right but a bit top
	               //  List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+yd, yc1-xd));
	                 tempArray3.add(new Point(xc1+yd, yc1-xd));
	                 
	                 //points top right close to top
	                // List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1+xd, yc1-yd));
	                 tempArray4.add(new Point(xc1+xd, yc1-yd));
	                 
	                 //points top left close to top
	                // List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-xd, yc1-yd));
	                 tempArray5.add(new Point(xc1-xd, yc1-yd));
	                 
	                //points left but a bit top
	                 //List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-yd, yc1-xd));
	                 tempArray6.add(new Point(xc1-yd, yc1-xd));
	                 
	                 //points left but a bit bottom
	                 //List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-yd, yc1+xd));
	                 tempArray7.add(new Point(xc1-yd, yc1+xd));
	                 
	                 //points bottom left close to bottom
	                 //List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(new Point(xc1-xd, yc1+yd));
	                 tempArray8.add(new Point(xc1-xd, yc1+yd));                 
	            }
	             
	            for(int i = 0; i < tempArray1.size() ; i++)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray1.get(i));
	            }
	            
	            
	            
	            for(int i = tempArray2.size() - 1; i >= 0  ; i--)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray2.get(i));
	            }
	            
	            
	            for(int i = 0; i < tempArray3.size() ; i++)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray3.get(i));
	            }
	            
	            
	            for(int i = tempArray4.size() - 1; i >= 0  ; i--)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray4.get(i));
	            }
	            
	            
	            for(int i = 0; i < tempArray5.size() ; i++)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray5.get(i));
	            }
	            
	            
	            for(int i = tempArray6.size() - 1; i >= 0  ; i--)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray6.get(i));
	            }
	            
	            
	            for(int i = 0; i < tempArray7.size() ; i++)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray7.get(i));
	            }
	            
	            
	            for(int i = tempArray8.size() - 1; i >= 0  ; i--)
	            {
	            	List_of_Shapes.get(List_of_Shapes.size() - 1).getPoints().add(tempArray8.get(i));
	            }
	            
			}
			
			
		}
		
		
		// If the select button was pressed
		if (buttonSelectPressed) {
			
			difference.setLocation((rescalePointX(e.getX())) - mousePointDragged.x, (rescalePointY(e.getY())) - mousePointDragged.y);

			mousePointDragged.setLocation((rescalePointX(e.getX())),(rescalePointY(e.getY())) );
			
			// if no shape was selected
			if (List_of_selected_Shapes.size() == 0) {
				
				grid.setCenterX((int)(grid.getCenterX() + difference.x));
				grid.setCenterY((int)(grid.getCenterY()  + difference.y));
				for (int i = 0; i < List_of_Shapes.size(); i++) 
				{
					for (Point2D n : List_of_Shapes.get(i).getPoints()) 
					{	
						n.setLocation(n.getX() + difference.x , n.getY() + difference.y);					
					}
					for (int j = 0; j < List_of_Shapes.get(i).getAnimation_panel_list().size(); j++)
					{
						Animation_transformation_panel temp;
						temp = List_of_Shapes.get(i).getAnimation_panel_list().get(j);
						
						switch (temp.getTransformation().getType())
						{
							
							case Rotation :
								
								 ((Rotation) temp.getTransformation()).setxOffset( ((Rotation) temp.getTransformation()).getxOffset() + difference.x);
								 
								 ((Rotation) temp.getTransformation()).setyOffset( ((Rotation) temp.getTransformation()).getyOffset() + difference.y);
								 temp.getxOffset_JSpinner().setValue(((Rotation) temp.getTransformation()).getxOffset());
								 temp.getyOffset_JSpinner().setValue(((Rotation) temp.getTransformation()).getyOffset());
								break;
							
							case Scaling :
								
								 ((Scaling) temp.getTransformation()).setxOffset( ((Scaling) temp.getTransformation()).getxOffset() + difference.x);
								 
								 ((Scaling) temp.getTransformation()).setyOffset( ((Scaling) temp.getTransformation()).getyOffset() + difference.y);
								 temp.getxOffset_JSpinner().setValue(((Scaling) temp.getTransformation()).getxOffset());
								 temp.getyOffset_JSpinner().setValue(((Scaling) temp.getTransformation()).getyOffset());
								break;
							case Reflection :
							
								 ((Reflection) temp.getTransformation()).setxOffset( ((Reflection) temp.getTransformation()).getxOffset() + difference.x);
								 
								 ((Reflection) temp.getTransformation()).setyOffset( ((Reflection) temp.getTransformation()).getyOffset() + difference.y);
								 temp.getxOffset_JSpinner().setValue(((Reflection) temp.getTransformation()).getxOffset());
								 temp.getyOffset_JSpinner().setValue(((Reflection) temp.getTransformation()).getyOffset());
								break;
							case Rotation_fixed_point :
								 ((Rotation_fixed_point) temp.getTransformation()).setxOffset( ((Rotation_fixed_point) temp.getTransformation()).getxOffset() + difference.x);
								 
								 ((Rotation_fixed_point) temp.getTransformation()).setyOffset( ((Rotation_fixed_point) temp.getTransformation()).getyOffset() + difference.y);
								 temp.getxOffset_JSpinner().setValue(((Rotation_fixed_point) temp.getTransformation()).getxOffset());
								 temp.getyOffset_JSpinner().setValue(((Rotation_fixed_point) temp.getTransformation()).getyOffset());
								break;
							case Scaling_fixed_point :
								 ((Scaling_fixed_point) temp.getTransformation()).setxOffset( ((Scaling_fixed_point) temp.getTransformation()).getxOffset() + difference.x);
								 ((Scaling_fixed_point) temp.getTransformation()).setyOffset( ((Scaling_fixed_point) temp.getTransformation()).getyOffset() + difference.y);
								 temp.getxOffset_JSpinner().setValue(((Scaling_fixed_point) temp.getTransformation()).getxOffset());
								 temp.getyOffset_JSpinner().setValue(((Scaling_fixed_point) temp.getTransformation()).getyOffset());
								break;
							
							case Shear :
								 ((Shear) temp.getTransformation()).setxOffset( ((Shear) temp.getTransformation()).getxOffset() + difference.x);
								 ((Shear) temp.getTransformation()).setyOffset( ((Shear) temp.getTransformation()).getyOffset() + difference.y);	
								 temp.getxOffset_JSpinner().setValue(((Shear) temp.getTransformation()).getxOffset());
								 temp.getyOffset_JSpinner().setValue(((Shear) temp.getTransformation()).getyOffset());
								break;
							
					
							default:
							break;
						}
						
					}
				}
				
				if (Animation_panel.SelectedPanel != null)
				{
					Animation_panel.unselectPanel();
				}
				
			}
			else {
				for (int i = 0; i < List_of_selected_Shapes.size(); i++) {
					for (Point2D n : List_of_selected_Shapes.get(i).getPoints()) 
					{
						n.setLocation(n.getX() + difference.x , n.getY() + difference.y);
					}
					
					for (int j = 0; j < List_of_selected_Shapes.get(i).getAnimation_panel_list().size(); j++)
					{
						Animation_transformation_panel temp;
						temp = List_of_selected_Shapes.get(i).getAnimation_panel_list().get(j);
						
						switch (temp.getTransformation().getType())
						{
						
						case Reflection :
							
							 ((Reflection) temp.getTransformation()).setxOffset( ((Reflection) temp.getTransformation()).getxOffset() + difference.x);
							 
							 ((Reflection) temp.getTransformation()).setyOffset( ((Reflection) temp.getTransformation()).getyOffset() + difference.y);
							 temp.getxOffset_JSpinner().setValue(((Reflection) temp.getTransformation()).getxOffset());
							 temp.getyOffset_JSpinner().setValue(((Reflection) temp.getTransformation()).getyOffset());
							break;
						case Rotation_fixed_point :
							 ((Rotation_fixed_point) temp.getTransformation()).setxOffset( ((Rotation_fixed_point) temp.getTransformation()).getxOffset() + difference.x);
							 
							 ((Rotation_fixed_point) temp.getTransformation()).setyOffset( ((Rotation_fixed_point) temp.getTransformation()).getyOffset() + difference.y);
							 temp.getxOffset_JSpinner().setValue(((Rotation_fixed_point) temp.getTransformation()).getxOffset());
							 temp.getyOffset_JSpinner().setValue(((Rotation_fixed_point) temp.getTransformation()).getyOffset());
							break;
					
							
						case Scaling_fixed_point :
							 ((Scaling_fixed_point) temp.getTransformation()).setxOffset( ((Scaling_fixed_point) temp.getTransformation()).getxOffset() + difference.x);
							 ((Scaling_fixed_point) temp.getTransformation()).setyOffset( ((Scaling_fixed_point) temp.getTransformation()).getyOffset() + difference.y);
							 temp.getxOffset_JSpinner().setValue(((Scaling_fixed_point) temp.getTransformation()).getxOffset());
							 temp.getyOffset_JSpinner().setValue(((Scaling_fixed_point) temp.getTransformation()).getyOffset());
							break;
						case Shear :
							 ((Shear) temp.getTransformation()).setxOffset( ((Shear) temp.getTransformation()).getxOffset() + difference.x);
							 ((Shear) temp.getTransformation()).setyOffset( ((Shear) temp.getTransformation()).getyOffset() + difference.y);	
							 temp.getxOffset_JSpinner().setValue(((Shear) temp.getTransformation()).getxOffset());
							 temp.getyOffset_JSpinner().setValue(((Shear) temp.getTransformation()).getyOffset());
							break;
							
					
							default:
							break;
						}
						
					}
					
				}
				if (Animation_panel.SelectedPanel != null)
				{
					Animation_panel.selectPanel(Animation_panel.SelectedPanel);
				}
			}
		}
		

		//if the Clip button has been pressed
		if (buttonClipPressed) {
			
			Point ne = e.getPoint();

			ne.y -= yOffset;
			ne.x -= xOffset;
			ne.x /= canvas_scale;
			ne.y /= canvas_scale;
			
			Menu.clipPoint2.x = ne.x;
			Menu.clipPoint2.y = ne.y;
			
		}
		
		e.getComponent().repaint();
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		if (buttonDrawPressed) {
		
		
			
			
		}
		
		e.getComponent().repaint();
	}
	
	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		canvas_prev_scale = canvas_scale;

		// Zoom in
		if (e.getWheelRotation() < 0 && canvas_scale < 4.0) {
			zoom = true;
			canvas_scale += 0.075;
			scale_counter++;

		}
		// Zoom out
		if (e.getWheelRotation() > 0 && canvas_scale > 0.11) {
			zoom = true;
			canvas_scale -= 0.075;
			scale_counter--;
		}
		
		if (scale_counter == 2 || scale_counter == -2)
		{
		grid.refreshUnit();
		scale_counter = 0;
		}
		/*
		for (int i = 0; i < List_of_Shapes.size(); i++) {
			List_of_Shapes.get(i).setScale(canvas_scale);
		}*/
		repaint();
		
	}
	
	

	//Un-used mouse events
	public void mouseMoved(MouseEvent arg0) {
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}

	

	

	
	@Override
	public void paintComponent(Graphics g) {
		//creating graphics 
		Graphics2D ga = (Graphics2D) g;
		super.paintComponent(g);
		
		if (zoom) {
			double zoomDiv = canvas_scale / canvas_prev_scale;

			 xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * (Main.FRAME_LENGTH/2); //* xRel;
			 yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * (Main.FRAME_HEIGHT/2);//* yRel;

			zoom = false;
		}
		
		ga.translate(xOffset, yOffset);
		ga.scale(canvas_scale, canvas_scale);
		grid.draw(ga);
		 
		
		
		//Draw shapes
		for (int i = 0; i < List_of_Shapes.size(); i++) {		
			List_of_Shapes.get(i).draw(ga);
		}
		
		

    	
    	if(Canvas.buttonClipPressed)
    	{
    		
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
    		
    		ga.setPaint(Color.red);
    		ga.drawRect(clipMin_x, clipMin_y , clipMax_x - clipMin_x , clipMax_y - clipMin_y);
    		
    		AlphaComposite oalcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.45f);
            ga.setComposite(oalcom);
            ga.setPaint(Color.black);
    		ga.setFont(new Font("TimesRoman", Font.PLAIN, (int) (50/Canvas.canvas_scale)));
            ga.drawString("Click and drag on screen", (int)(((Main.newCanvas.getWidth()/2 - 250) - xOffset)/canvas_scale) ,(int) ((100 - yOffset)/canvas_scale) );
    		
            
            
    		if (Menu.clipPoint1 != null && Menu.clipPoint2 != null)
    		{
    			
    			AlphaComposite palcom = AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, 0.25f);
                ga.setComposite(palcom);
                ga.setPaint(Color.GRAY);
                
                ga.fillRect(-100000, -100000, clipMin_x - (-100000), 500000);
                ga.fillRect(-100000, -100000, 500000,  clipMin_y - (-100000));
                ga.fillRect(clipMax_x, -100000, 500000,  500000);
                
                ga.fillRect(-100000, clipMax_y, 500000,  500000);
                
    		}
    		
    		
    	}
    	
		
		//draw preview shape if available
		if (Animation_panel.SelectedPanel != null) {
			ga.setPaint(shape_color);
			
			AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.45f);
            ga.setComposite(alcom);
            
            previewShape_second.draw(ga);
			previewShape.draw(ga);
			animatedPreviewShape.draw(ga);
			
			
			if (Animation_panel.SelectedPanel.getTransformation().getType() != Transformation.Type.Translation)
			{
				double preview_center_radius = 10;
				preview_center_radius = preview_center_radius/Canvas.canvas_scale;
				
				
				AlphaComposite alcom2 = AlphaComposite.getInstance(
	                    AlphaComposite.SRC_OVER, 0.70f);
	            ga.setComposite(alcom2);
				
				Shape c = new Ellipse2D.Double( (int) Animation_panel.SelectedPanel.getxOffset_JSpinner().getValue() - preview_center_radius/2, (int) Animation_panel.SelectedPanel.getyOffset_JSpinner().getValue() - preview_center_radius/2, preview_center_radius, preview_center_radius);
				ga.setPaint(Color.black);
				ga.draw(c);
				ga.setColor(Color.red);
            	ga.fill(c);
            	
            	
            	if (Animation_panel.SelectedPanel.getTransformation().getType() == Transformation.Type.Reflection)
    			{
            		ga.setPaint(Color.DARK_GRAY);
            		ga.setStroke(new BasicStroke((float)(3/Canvas.canvas_scale), BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, new float[]{(float) (10/Canvas.canvas_scale)}, 0.0f));
            		
            		int linePreview_length = 100000;
            		double angle = Math.toRadians(((Reflection) Animation_panel.SelectedPanel.getTransformation()).getAngle());
            		int x1 = (int)Animation_panel.SelectedPanel.getxOffset_JSpinner().getValue() - (int)(linePreview_length * Math.cos(angle));
            		int x2 = (int)Animation_panel.SelectedPanel.getxOffset_JSpinner().getValue() + (int)(linePreview_length * Math.cos(angle));
            		int y1 = (int)Animation_panel.SelectedPanel.getyOffset_JSpinner().getValue() - (int)(linePreview_length * Math.sin(angle));
            		int y2 = (int)Animation_panel.SelectedPanel.getyOffset_JSpinner().getValue() + (int)(linePreview_length * Math.sin(angle));
            		ga.drawLine( x1 , y1, x2, y2);
    			}
            	
			}
			
		}
		
		
	}
	
}
