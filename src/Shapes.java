
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Timer;

import Transformations.Reflection;
import Transformations.Rotation;
import Transformations.Rotation_fixed_point;
import Transformations.Scaling;
import Transformations.Scaling_fixed_point;
import Transformations.Shear;
import Transformations.Transformation;
import Transformations.Translation;



public class Shapes {

	
	private Color color;
	private Color color_fill;	
	private boolean fill = false;
    private boolean selected = false;
    private boolean closed = true;
    private Rectangle box = new Rectangle();
    
    private Shape_Type type;
    private Stroke stroke = new BasicStroke(2);
    private ArrayList<Point2D> points = new ArrayList<Point2D>();
    private ArrayList<Point2D> original_points = new ArrayList<Point2D>();
    private int line_size;
    private ArrayList<Animation_transformation_panel> Animation_panel_list = new ArrayList<Animation_transformation_panel>();
    private Edge[] sortedEdges;
    
    
    private Timer timer;
    private int timer_delay = 20;
    
    private  double valueHolder = 1000;
    private  double initialValue = 0 ;
    private  double increment = 0;
	
    private  double valueHolder_second = 1000;
    private  double initialValue_second = 0 ;
    private  double increment_second = 0;
    

	private int frames = 100;
	private int current_frame = 0;
	private int current_panel = 0;
	private boolean tempBool = false;
	
	public Shapes returnShape()
	{
		return this;
	}
	
	public void reIndex_List_of_transformation()
	{
		for (int i = 0 ; i < Animation_panel_list.size() ; i++)
		{
			Animation_panel_list.get(i).setIndex(i);			
		}
	}
	
    private ActionListener action_for_timer = new ActionListener(){
		public void actionPerformed (ActionEvent e) {
			
			if (current_frame >= (frames * Animation_panel_list.size()) - 1)
			{
			tempBool = true;
			}
			
			if (tempBool)
			{
				Shapes.copyPoints(points, original_points);
				Animation_panel_list.clear();
				Animation_panel.updatePanel(returnShape());
				current_frame = 0;
				tempBool = false;
				timer.stop();
				return;
			}	
			
			current_frame++;
		
			
			switch(Animation_panel_list.get(current_panel).getTransformation().getType())
			{
				case Rotation_fixed_point :	
				case Rotation :
					initialValue = initialValue + increment;
					Shapes.copyPoints(original_points, points);
					
					
					if (increment > 0)
					{
						if(initialValue >= valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
						}
					}
					else 
					{
						if(initialValue < valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
						}
					}
					
					break;
					
				case Translation : 
					
					initialValue = initialValue + increment;
					initialValue_second = initialValue_second + increment_second;
					
					Shapes.copyPoints(original_points, points);
					
					if (increment > 0)
					{
						if(initialValue >= valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo(); 
						}
					}
					else 
					{
						if(initialValue < valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo(); 
						}
					}
				
					if (increment_second > 0)
					{
						if(initialValue_second >= valueHolder_second)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo(); 
						}
					}
					else 
					{
						if (increment_second < 0) {
							if(initialValue_second < valueHolder_second)
							{
								Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
								Shapes.copyPoints(original_points, points);
								updateAnimationInfo(); 
							}
						}
					}
					break;
					
				case Scaling :
				case Scaling_fixed_point :	
					
					initialValue = initialValue + increment;
					initialValue_second = initialValue_second + increment_second;
					
					Shapes.copyPoints(original_points, points);
					
					if (increment > 0)
					{
						if(initialValue >= valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
							
						}
					}
					else 
					{
						if(initialValue < valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
							
						}
					}
				
					if (increment_second > 0)
					{
						if(initialValue_second >= valueHolder_second)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
							
						}
					}
					else 
					{
						if (increment_second < 0) {
							if(initialValue_second < valueHolder_second)
							{
								 
								Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
								Shapes.copyPoints(original_points, points);
								updateAnimationInfo();
							}
						}
					}
					
					break;
					
				case Reflection :
					
					initialValue = initialValue + increment;
					
					Shapes.copyPoints(original_points, points);
					
					if (increment > 0)
					{
						if(initialValue >= valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();			
						}
					}
					else 
					{
						if(initialValue < valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();					
						}
					}
					
					break;
					
				case Shear :
					
					initialValue = initialValue + increment;
					initialValue_second = initialValue_second + increment_second;
					
					Shapes.copyPoints(original_points, points);
					
					if (increment > 0)
					{
						if(initialValue >= valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
							
						}
					}
					else 
					{
						if(initialValue < valueHolder)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
							
						}
					}
				
					if (increment_second > 0)
					{
						if(initialValue_second >= valueHolder_second)
						{
							Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
							Shapes.copyPoints(original_points, points);
							updateAnimationInfo();
							
						}
					}
					else 
					{
						if (increment_second < 0) {
							if(initialValue_second < valueHolder_second)
							{
								Animation_panel.applyTransformation(original_points, Animation_panel_list.get(current_panel));
								Shapes.copyPoints(original_points, points);
								updateAnimationInfo();
							}
						}
					}
					
					break;
					
			}

			switch(Animation_panel_list.get(current_panel).getTransformation().getType())
			{
				case Rotation_fixed_point :
					Transformation tempTransformation_Rot_fix = new Rotation_fixed_point(initialValue, ((Rotation_fixed_point)Animation_panel_list.get(current_panel).getTransformation()).getxOffset() , ((Rotation_fixed_point)Animation_panel_list.get(current_panel).getTransformation()).getyOffset());					
					Animation_panel.applyTransformationForTimer	(points, tempTransformation_Rot_fix);
					break;
					
				case Translation : 
					
					Transformation tempTransformation_Trans = new Translation(initialValue, initialValue_second);					
					Animation_panel.applyTransformationForTimer	(points, tempTransformation_Trans);		
					break;
					
				case Rotation :
					
					Transformation tempTransformation_Rot = new Rotation(initialValue, Grid.grid_center_point.x, Grid.grid_center_point.y);					
					Animation_panel.applyTransformationForTimer	(points, tempTransformation_Rot);	
					break;
					
				case Scaling :
					
					Transformation tempTransformation_Scal = new Scaling(initialValue, initialValue_second, Grid.grid_center_point.x, Grid.grid_center_point.y);					
					Animation_panel.applyTransformationForTimer	(points, tempTransformation_Scal);	
					
					
					break;
					
				case Scaling_fixed_point :
					
					Transformation tempTransformation_Scal_fix = new Scaling_fixed_point(initialValue, initialValue_second, ((Scaling_fixed_point)Animation_panel_list.get(current_panel).getTransformation()).getxOffset() , ((Scaling_fixed_point)Animation_panel_list.get(current_panel).getTransformation()).getyOffset());					
					Animation_panel.applyTransformationForTimer	(points, tempTransformation_Scal_fix);	
				
					break;
					
					
					
				case Reflection :
					
					Transformation tempTransformation_Ref = new Reflection(initialValue, ((Reflection)Animation_panel_list.get(current_panel).getTransformation()).getxOffset() , ((Reflection)Animation_panel_list.get(current_panel).getTransformation()).getyOffset());																
					Animation_panel.applyTransformationForTimer	(points, tempTransformation_Ref);
					
					break;
					
				case Shear :
					
					Transformation tempTransformation_Shear = new Shear(initialValue, initialValue_second , ((Shear)Animation_panel_list.get(current_panel).getTransformation()).getxOffset() , ((Shear)Animation_panel_list.get(current_panel).getTransformation()).getyOffset());																
					Animation_panel.applyTransformationForTimer	(points, tempTransformation_Shear);
					
					break;
			}
			
			
			Main.newCanvas.repaint();
			
			
		}
    };
    
    public enum Shape_Type {
        Circle,  Line, Free_Form, Regular_Shape, Irregular_Shape
     }
       
    
    public Shapes() {
    	
    }
    
    public Shapes(Color color, Shape_Type t, Stroke stroke, int line_size) {
    	
    	this.color = color;
        this.type = t;
        this.selected = false;
        this.stroke = stroke;
        this.line_size = line_size;
        
        this.timer = new Timer(timer_delay, action_for_timer);
        
        if (t == Shape_Type.Free_Form)
        	closed = false;
        
    }
    
    
    public void updateAnimationInfo() {
    	
    	if (Animation_panel_list.size() == 0)
    		return;
    	
    	current_panel = current_frame/frames;
    	
    	if (current_panel > Animation_panel_list.size())
    		return;
    	
    	//current_panel--;
    	int current_frame_on_panel = current_frame%frames;
    	
    	
    	
    	switch(Animation_panel_list.get(current_panel).getTransformation().getType())
		{
			case Translation : 
				valueHolder = ((Translation)Animation_panel_list.get(current_panel).getTransformation()).getX();
				initialValue = 0;
				increment = (valueHolder - initialValue) / frames;
				initialValue = 0 + (increment * current_frame_on_panel);
				
				
				valueHolder_second = ((Translation)Animation_panel_list.get(current_panel).getTransformation()).getY();
				initialValue_second =  0;
				increment_second = (valueHolder_second - initialValue_second) / frames;
				initialValue_second =  0 + (increment_second * current_frame_on_panel);
				
				break;
				
			case Rotation :
				
				valueHolder = ((Rotation)Animation_panel_list.get(current_panel).getTransformation()).getAngle();
				initialValue = 0;
				increment = (valueHolder - initialValue) / frames;
				initialValue = 0 + (increment * current_frame_on_panel);
				break;
				
			case Rotation_fixed_point :
				
				valueHolder = ((Rotation_fixed_point)Animation_panel_list.get(current_panel).getTransformation()).getAngle();
				initialValue = 0;
				increment = (valueHolder - initialValue) / frames;
				initialValue = 0 + (increment * current_frame_on_panel);
				break;
				
			case Scaling :
				/*
				valueHolder = ((Scaling)Animation_panel_list.get(current_panel).getTransformation()).getScaleX();
				initialValue = 1;
				increment = ((valueHolder - 1) / 80) + 1 ;
				
				valueHolder_second = ((Scaling)Animation_panel_list.get(current_panel).getTransformation()).getScaleY();
				initialValue_second = 1;
				increment_second = ((valueHolder_second - 1) / 80) + 1;
				*/
				valueHolder = ((Scaling)Animation_panel_list.get(current_panel).getTransformation()).getScaleX();
				initialValue = 1;
				increment = (valueHolder - initialValue) / frames;
				initialValue = 1 + (increment * current_frame_on_panel);
				
				
				valueHolder_second = ((Scaling)Animation_panel_list.get(current_panel).getTransformation()).getScaleY();
				initialValue_second = 1;
				increment_second = (valueHolder_second - initialValue_second) / frames;
				initialValue_second = 1 + (increment_second * current_frame_on_panel);
				break;
				
			case Scaling_fixed_point :
				
				valueHolder = ((Scaling_fixed_point)Animation_panel_list.get(current_panel).getTransformation()).getScaleX();
				initialValue = 1;
				increment = (valueHolder - initialValue) / frames;
				initialValue = 1 + (increment * current_frame_on_panel);
				
				valueHolder_second = ((Scaling_fixed_point)Animation_panel_list.get(current_panel).getTransformation()).getScaleY();
				initialValue_second = 1;
				increment_second = (valueHolder_second - initialValue_second) / frames;
				initialValue_second = 1 + (increment_second * current_frame_on_panel);
				break;
				
			case Reflection :
					
				valueHolder = ((Reflection)Animation_panel_list.get(current_panel).getTransformation()).getAngle();
				initialValue = 0;
				increment = (valueHolder - initialValue) / frames;
				initialValue = 0 + (increment * current_frame_on_panel);
				
				break;
				
			case Shear :
				
				valueHolder = ((Shear)Animation_panel_list.get(current_panel).getTransformation()).getX();
				initialValue = 0;
				increment = (valueHolder - initialValue) / frames;
				initialValue = 0 + (increment * current_frame_on_panel);
				
				
				valueHolder_second = ((Shear)Animation_panel_list.get(current_panel).getTransformation()).getY();
				initialValue_second = 0;
				increment_second = (valueHolder_second - initialValue_second) / frames;
				initialValue_second = 0 + (increment_second * current_frame_on_panel);
				
				break;
				
		}
    	
    	
    	
    	
    }
    
    
    
    public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getCurrent_frame() {
		return current_frame;
	}

	public void setCurrent_frame(int current_frame) {
		this.current_frame = current_frame;
	}

	public int getCurrent_panel() {
		return current_panel;
	}

	public void setCurrent_panel(int current_panel) {
		this.current_panel = current_panel;
	}

	public int getTimer_delay() {
		return timer_delay;
	}

	public void setTimer_delay(int timer_delay) {
		this.timer_delay = timer_delay;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public ActionListener getAction_for_timer() {
		return action_for_timer;
	}

	public void setAction_for_timer(ActionListener action_for_timer) {
		this.action_for_timer = action_for_timer;
	}

	public void addPoint(Point p) {
    	
     points.add(p);
     
   	 Point2D m = new Point2D.Double(p.getX(),p.getY());
   	 original_points.add(m);
   	 
   	 //refreshBox();
    }
    
    
   
    
    public void refreshBox() {
    	
	     double minX = 2000000;
	   	 double minY = 2000000;
	   	 double maxX = -9999999;
	   	 double maxY = -9999999;
	   	 
	   	 for (Point2D n : this.points) 
	   	 {		 
	   		 
	   		if (n.getX() < minX)
				 minX = n.getX();
			 if (n.getY() < minY)
				 minY = n.getY();
			 if (n.getX() > maxX)
				 maxX = n.getX();
			 if (n.getY() > maxY)
				 maxY = n.getY();		 
	   	 }
	   	  box.setBounds((int)minX, (int)minY, (int)(maxX - minX), (int)(maxY - minY)); 	   	 
    }
    
    
    
    public void copyShape(Shapes copy) {
    	
    	this.color = copy.getColor();
    	this.color_fill = copy.getColor_fill();
    	this.fill = copy.isFill();
    	this.selected = false;
    	this.closed = copy.closed;
    	
    	Rectangle tempBox = new Rectangle();
    	tempBox.setBounds(copy.getBox());
    	this.box = tempBox;
    	
    	this.type = copy.getType();
    	this.stroke = copy.getStroke();
    	
    	
    	this.points.clear();
    	
    	for(int i = 0 ; i < copy.getPoints().size(); i++)
    	{
    		this.points.add(new Point2D.Double(copy.getPoints().get(i).getX(), copy.getPoints().get(i).getY()));
    	}
    	
    	this.line_size = copy.getLine_size();
    	
    }
    
    public static void copyPoints(ArrayList<Point2D> fromPoints, ArrayList<Point2D> toPoints)
    {
    	toPoints.clear();
    	for(int i = 0 ; i < fromPoints.size(); i++)
    	{
    		toPoints.add(new Point2D.Double(fromPoints.get(i).getX(), fromPoints.get(i).getY() ));
    	}
    	
    	
    	
    }
    
    
    public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}



	public Color getColor_fill() {
		return color_fill;
	}
	public void setColor_fill(Color color_fill) {
		this.color_fill = color_fill;
	}

	
	
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	

	
	public Rectangle getBox() {
		return box;
	}
	public void setBox(Rectangle box) {
		this.box = box;
	}



	public Shape_Type getType() {
		return type;
	}
	public void setType(Shape_Type type) {
		this.type = type;
	}

	

	public Stroke getStroke() {
		return stroke;
	}
	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}

	

	
	public ArrayList<Point2D> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Point2D> points) {
		this.points = points;
		refreshBox();
	}


	
	public ArrayList<Point2D> getOriginal_points() {
		return original_points;
	}
	public void setOriginal_points(ArrayList<Point2D> original_points) {
		this.original_points = original_points;
	}

	
	
	public int getLine_size() {
		return line_size;
	}
	public void setLine_size(int line_size) {
		this.line_size = line_size;
	}

	
	
	public ArrayList<Animation_transformation_panel> getAnimation_panel_list() {
		return Animation_panel_list;
	}
	public void setAnimation_panel_list(ArrayList<Animation_transformation_panel> animation_panel_list) {
		Animation_panel_list = animation_panel_list;
	}
	
	
	 public static void selectNone(ArrayList<Shapes> list) {
         for (Shapes n : list) {
             n.setSelected(false);
             Canvas.List_of_selected_Shapes.clear();
            
             Animation_panel.unselectPanel();
             Animation_panel.LeftPanel.removeAll();
             Animation_panel.LeftPanel.revalidate();
         }
     }
     
	//Checks if point p lies inside the box of the shape
     public boolean contains(Point p) {
         return box.contains(p);
     }
	 
     public static boolean selectOne(ArrayList<Shapes> list, Point p) {
         for (Shapes n : list) 
         {
             if (n.contains(p)) {
                 if (!n.isSelected()) 
                 {
                     
                	 Shapes.selectNone(list);
                     n.setSelected(true);
                     
                     Main.newAnimation_panel.updatePanel(n);
                     
                     Canvas.List_of_selected_Shapes.add(n);
                 }
                 return true;
             }
             else
             {
            	 Shapes.selectNone(list);
             }
         }
         return false;
     }
     
	
     public Edge[] createEdges()
     {
		 if(points.get(0)!=points.get(points.size()-1)) {
			 points.add(points.get(0));
		 }
         Edge[] sortedEdges = new Edge[points.size()-1];
         for (int i = 0; i < points.size() - 1; i++)
         {
             //if (polygon.elementAt(i).y == polygon.elementAt(i+1).y) continue;
             if ((points.get(i)).getY() < (points.get(i+1)).getY())
                 sortedEdges[i] = new Edge(points.get(i), points.get(i+1));
             else
                 sortedEdges[i] = new Edge(points.get(i+1), points.get(i));
         }
         points.remove(points.size()-1);//removes final point because of deformation during click and drag
         return sortedEdges;
     }


	public void draw(Graphics g) {
        
		//creating graphics
        Graphics2D ga = (Graphics2D)g;
        ga.setColor(this.color);
        ga.setStroke(stroke);
    
        
        if(points.size() == 0)
        {
 			//break;
        }
        else
        {
	 		 refreshBox();
	 		 
	 		 //Using list of points, the shape will be drawn
	 		 GeneralPath r_shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());
	         r_shape.moveTo(points.get(0).getX(), points.get(0).getY());
	
	         for (int index = 1; index < points.size(); index++) 
	         {
	        	 r_shape.lineTo(points.get(index).getX(), points.get(index).getY());              	        
	         }
	         
	         if (closed == true)
	         	r_shape.closePath();
	         
	         
	         ga.draw(r_shape);
	         
	         //If the fill boolean is true, the shape will be filled
	         if (fill)    
	         {
	         	ga.setColor(color_fill);
	         	//ga.fill(r_shape);
	         	
	         	
	         	
	         	Edge[] sortedEdges = this.createEdges();
	         	 // sort all edges by y coordinate, smallest one first, lousy bubblesort
	            Edge tmp;
	            
	            for (int i = 0; i < sortedEdges.length - 1; i++)
	                for (int j = 0; j < sortedEdges.length - 1; j++)
	                {
	                    if (sortedEdges[j].p1.y > sortedEdges[j+1].p1.y) 
	                    {
	                        // swap both edges
	                        tmp = sortedEdges[j];
	                        sortedEdges[j] = sortedEdges[j+1];
	                        sortedEdges[j+1] = tmp;
	                    }  
	                }
	            
	            // find biggest y-coord of all vertices
	            int scanlineEnd = 0;
	            for (int i = 0; i < sortedEdges.length; i++)
	            {
	                if (scanlineEnd < sortedEdges[i].p2.y)
	                    scanlineEnd = sortedEdges[i].p2.y;
	            }
	            
	            // --- DEBUG ---
	            /*
	            System.out.println("==============");
	            for (int i = 0; i < sortedEdges.length; i++)
	                System.out.println("X: " + sortedEdges[i].p1.x + " Y: " + sortedEdges[i].p1.y + 
	                       " --- " + "X: " + sortedEdges[i].p2.x + " Y: " + sortedEdges[i].p2.y);
	            */
	            
	            
	            // scanline starts at smallest y coordinate
	            int scanline = sortedEdges[0].p1.y;
	            
	            // this list holds all cutpoints from current scanline with the polygon
	            ArrayList<Integer> list = new ArrayList<Integer>();

	            
	            // move scanline step by step down to biggest one
	            for (scanline = sortedEdges[0].p1.y; scanline <= scanlineEnd; scanline++)
	            {
	                //System.out.println("ScanLine: " + scanline); // DEBUG
	                
	                list.clear();
	                
	                // loop all edges to see which are cut by the scanline
	                for (int i = 0; i < sortedEdges.length; i++)
	                {   
	                    
	                    // here the scanline intersects the smaller vertice
	                    if (scanline == sortedEdges[i].p1.y) 
	                    {
	                        if (scanline == sortedEdges[i].p2.y)
	                        {
	                            // the current edge is horizontal, so we add both vertices
	                            sortedEdges[i].deactivate();
	                            list.add((int)sortedEdges[i].curX);
	                        }
	                        else
	                        {
	                            sortedEdges[i].activate();
	                            // we don't insert it in the list cause this vertice is also
	                            // the (bigger) vertice of another edge and already handled
	                        }
	                    }
	                    
	                    // here the scanline intersects the bigger vertice
	                    if (scanline == sortedEdges[i].p2.y)
	                    {
	                        sortedEdges[i].deactivate();
	                        list.add((int)sortedEdges[i].curX);
	                    }
	                    
	                    // here the scanline intersects the edge, so calc intersection point
	                    if (scanline > sortedEdges[i].p1.y && scanline < sortedEdges[i].p2.y)
	                    {
	                        sortedEdges[i].update();
	                        list.add((int)sortedEdges[i].curX);
	                    }
	                    
	                }
	                
	                // now we have to sort our list with our x-coordinates, ascendend
	                int swaptmp;
	                for (int i = 0; i < list.size(); i++)
	                    for (int j = 0; j < list.size() - 1; j++)
	                    {
	                        if (list.get(j) > list.get(j+1))
	                        {
	                            swaptmp = list.get(j);
	                            list.set(j, list.get(j+1));
	                            list.set(j+1, swaptmp);
	                        }
	                    
	                    }
	             

	                if (list.size() < 2 || list.size() % 2 != 0) 
	                {
	                    System.out.printf("This should never happen! %d\n", list.size());
	                    continue;
	                }
	                 
	                // so draw all line segments on current scanline
	                for (int i = 0; i < list.size(); i+=2)
	                {
	                   ga.setStroke(new BasicStroke(2));
	                	ga.drawLine(list.get(i), scanline, 
	                               list.get(i+1), scanline);
	                    
	                }
	                
	            }
//	         	
	         	
	         }
	         
	         //If the shape is selected it will draw a grey rectangle around it
	         if (selected) {
	        	 ga.setStroke(new BasicStroke(1));
	             ga.setColor(Color.darkGray);
	             ga.drawRect(box.x, box.y, box.width, box.height);
	           
	         }
        }
    }
    
}
