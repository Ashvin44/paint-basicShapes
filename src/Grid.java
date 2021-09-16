
import java.awt.*;


public class Grid 
{
	public static boolean grid_toggle = true;
    public static boolean axis_toggle = true;
	
    
    public static Point Start_grid_center_point = new Point();
    public static Point grid_center_point = new Point();

    
    private int startUnit = 50;
    private int unit = startUnit;

    private float Start_grid_center_stroke_value = 5;
    private float grid_center_stroke_value = Start_grid_center_stroke_value;
    
    private float Start_grids_stroke_value = 2;
    private float grids_stroke_value = Start_grids_stroke_value;
    
    private float Start_font_size = 15;
    private float font_size = Start_font_size;
    
    private float Start_Offset_X = 6;
    private float Offset_X = Start_Offset_X;
    
    private float Start_Offset_Y = 6;
    private float Offset_Y = Start_Offset_Y;
    
	
    public Grid()
	{
    	//590,250
		Start_grid_center_point.setLocation(590,250);
		grid_center_point.setLocation(Start_grid_center_point);
		
	}
	
	
	public Point getStartGridCenterPoint()
	{
		return Start_grid_center_point;
	}
	
	public void refreshUnit()
	{
		unit = (int)(startUnit / Canvas.canvas_scale);
		grid_center_stroke_value = (float)(Start_grid_center_stroke_value/Canvas.canvas_scale);
		grids_stroke_value = (float)(Start_grids_stroke_value/Canvas.canvas_scale);
		font_size = (int)(Start_font_size/Canvas.canvas_scale);
		
		Offset_X = (float)(Start_Offset_X/Canvas.canvas_scale);
		Offset_Y = (float) (Start_Offset_Y/Canvas.canvas_scale);
		
	}
	
	public boolean getAxisToggle()
	{
		return axis_toggle;
	}
	
	public void setAxisToggle(boolean t)
	{
		this.axis_toggle = t;
	}
	
	public boolean getGridToggle()
	{
		return grid_toggle;
	}
	
	public void setGridToggle(boolean t)
	{
		this.grid_toggle = t;
	}
	
    public int getCenterX()
    {
        return grid_center_point.x;
    }

    public int getCenterY()
    {
    return grid_center_point.y;

    } 

    public void setCenterX(int x)
    {
        grid_center_point.x = x;

    }

    public void setCenterY(int y)
    {
        grid_center_point.y = y;
    }
    
	public void draw(Graphics g) 
	{
		Graphics2D ga = (Graphics2D)g;
		ga.setColor(new Color(181,179,174));
		ga.setFont(new Font("TimesRoman", Font.PLAIN, (int)font_size)); 
		ga.setStroke(new BasicStroke(grids_stroke_value));
		for (int i = (grid_center_point.x - (500 * unit)); i < 9000 ; i = i + unit)
		{
			ga.setColor(Color.black);
		    	 
			if(axis_toggle)
				ga.drawString(Integer.toString((i - getCenterX() )* -1) , getCenterX() + Offset_X, i - getCenterX() + getCenterY() - Offset_Y);
	
		    	 
		    ga.setColor(new Color(181,179,174));
		    if(grid_toggle)
		    	ga.drawLine(i , -9000, i, 9000);
		}
		for (int i = (grid_center_point.y - (500 * unit)); i < 9000 ; i += unit)
		{
		    ga.setColor(Color.black);
		    // ga.setStroke(new BasicStroke(grids_stroke_value));
		    if(axis_toggle) 
		    {
		    	if(i - getCenterY() != 0)
		    		ga.drawString(Integer.toString((i - getCenterY())) , i - getCenterY() + getCenterX() + Offset_X,getCenterY() - Offset_Y);
			}
		    ga.setColor(new Color(181,179,174));
		    
		    if(grid_toggle)
		        ga.drawLine(-9000 , i, 9000, i);
		} 
	
		g.setColor(Color.black);
	    ga.setStroke(new BasicStroke(grid_center_stroke_value)); 
	    
	    if(axis_toggle)
	    {
		    ga.drawLine(grid_center_point.x   , -9000, grid_center_point.x, 9000);
		    ga.drawLine(-9000, grid_center_point.y, 9000, grid_center_point.y);
	    }
	   
	}
}