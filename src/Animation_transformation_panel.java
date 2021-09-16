
import Transformations.Transformation;
import Transformations.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Each shapes can be animated with a list of transformation chosen by the user
// Each transformation will be stored in a panel of its own where corresponding values may be modified by the user. 
//	(example : A transformation rotation will have a panel of its own and the angle of the rotation can be modified by the user.)

public class Animation_transformation_panel extends JPanel implements MouseListener{

	private Transformation transformation;

	private JButton Transformation_remove_button = new JButton("X");
	
	private JPanel Center_Panel = new JPanel() ;
	
	private JPanel xOffset_Panel = new JPanel();
	private JPanel yOffset_Panel = new JPanel();
	
	private JSpinner xOffset_JSpinner = new JSpinner();	
	private JSpinner yOffset_JSpinner = new JSpinner();	
	
	private int index;
	
	public Animation_transformation_panel returnSuper()
	{
		return this;
	}
	
	public JPanel getCenter_Panel() {
		return Center_Panel;
	}

	public JPanel getxOffset_Panel() {
		return xOffset_Panel;
	}
	public JPanel getyOffset_Panel() {
		return yOffset_Panel;
	}
	
	public JSpinner getxOffset_JSpinner() {
		return xOffset_JSpinner;
	}
	public JSpinner getyOffset_JSpinner() {
		return yOffset_JSpinner;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setCenter_Panel(JPanel center_Panel) {
		Center_Panel = center_Panel;
	}


	public JButton getTransformation_remove_button() {
		return Transformation_remove_button;
	}


	public void setTransformation_remove_button(JButton transformation_remove_button) {
		Transformation_remove_button = transformation_remove_button;
	}


	public Transformation getTransformation() {
		
		return transformation;
	}

	
	public void setTransformation(Transformation tran) {
		this.transformation = tran;
	
	}
	public Dimension getPreferredSize() {
        return new Dimension(190, 150);
    }
	
	
	
	//Construction of animation transformation panel
	public Animation_transformation_panel(Transformation transformation) {
		
		super();
		addMouseListener(this);
		//Stores transformation in transformation variable
		this.transformation = transformation;
		
		
		
		// CREATING GUI of a animation transformation panel
		//User border layout?
		JPanel All_Panels = new JPanel();
		All_Panels.setPreferredSize(new Dimension(180,140));
		All_Panels.setLayout(new BorderLayout());
		All_Panels.setBackground(Color.PINK);
		
		JPanel Top_Panel = new JPanel();
		Top_Panel.setPreferredSize(new Dimension(180,25));
		Top_Panel.setBackground(Color.GRAY);
		
		//Top_Panel.setLayout(new FlowLayout());
		//Top_Panel.setLayout(new BorderLayout());
		Top_Panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel Transformation_Label = new JLabel(this.getTransformation().getType().toString().replaceAll("_", " "));
		Transformation_Label.setBackground(Color.orange);
		Transformation_Label.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 9;
		c.weightx = 1;
		c.insets = new Insets(0,8,0,0);
		c.ipadx = 40;      //make this component tall
		c.gridx = 0;
		c.gridy = 0;
		Top_Panel.add(Transformation_Label, c);
		//Top_Panel.add(Transformation_Label, BorderLayout.PAGE_START);
		//Top_Panel.add(Transformation_Label);
		
		
		Transformation_remove_button.setBackground(Color.red);
		Transformation_remove_button.setFont(new Font("TimesRoman", Font.BOLD, 20));
		Transformation_remove_button.setForeground(Color.WHITE);
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,0);
		c.ipadx = 0;   
		c.gridx = 10;
		c.gridy = 0;
		
		// Functionality of X button
		
		Top_Panel.add(Transformation_remove_button, c);
		
		
		
		Center_Panel.setLayout(new GridLayout(4,1));
		Center_Panel.setBackground(new Color(238,238,238));
		
		
		
		
		
		GridLayout oneRow_twoCol = new GridLayout(1,2);

		
				
		//Depending on the type of transformation, Center panel will change
		switch (this.transformation.getType())
		{
		
		case Translation:
			
			
			JPanel Translation_X_Panel = new JPanel();
			Translation_X_Panel.setLayout(oneRow_twoCol);
			Translation_X_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Translation_X_Label = new JLabel("X value :");
			//JTextField Translation_X_TextField = new JTextField(6);
			
			JSpinner Translation_X_JSpinner = new JSpinner();			
			Translation_X_JSpinner.setModel(new SpinnerNumberModel( ((Translation) getTransformation()).getX(), -100000, 100000, 5));
			Translation_X_JSpinner.addChangeListener(new ChangeListener() {

				 @Override
			     public void stateChanged(ChangeEvent e) {
					 JSpinner s = (JSpinner) e.getSource();
					 
					 ((Translation) getTransformation()).setX((double) s.getValue());
					 Animation_panel.selectPanel(returnSuper());
				  }
		     });
			
			Translation_X_Panel.add(Translation_X_Label);
			//Translation_X_Panel.add(Translation_X_TextField);
			Translation_X_Panel.add(Translation_X_JSpinner);
			
			JPanel Translation_Y_Panel = new JPanel();
			Translation_Y_Panel.setLayout(oneRow_twoCol);
			Translation_Y_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Translation_Y_Label = new JLabel("Y value :");
		//	JTextField Translation_Y_TextField = new JTextField(6);
			
			
			JSpinner Translation_Y_JSpinner = new JSpinner();			
			Translation_Y_JSpinner.setModel(new SpinnerNumberModel( ((Translation) getTransformation()).getY(), -100000, 100000, 5));
			Translation_Y_JSpinner.addChangeListener(new ChangeListener() {

				 @Override
			     public void stateChanged(ChangeEvent e) {
					 JSpinner s = (JSpinner) e.getSource();
					 
					 ((Translation) getTransformation()).setY((double) s.getValue());
					 Animation_panel.selectPanel(returnSuper());
				  }
		     });
			
			
			Translation_Y_Panel.add(Translation_Y_Label);
			Translation_Y_Panel.add(Translation_Y_JSpinner);
		//	Translation_Y_Panel.add(Translation_Y_TextField);
			
			
			
			
			Center_Panel.add(Translation_X_Panel);
			Center_Panel.add(Translation_Y_Panel);
			
			break;
			
		case Rotation :
			
			JPanel Rotation_Angle_Panel = new JPanel();
			Rotation_Angle_Panel.setLayout(oneRow_twoCol);
			Rotation_Angle_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Rotation_Angle_Label = new JLabel("Angle :");
		//	JTextField Rotation_Angle_TextField = new JTextField(6);
			
			

			JSpinner Rotation_Angle_JSpinner = new JSpinner();	
			Rotation_Angle_JSpinner.setModel(new SpinnerNumberModel( ((Rotation) getTransformation()).getAngle(), -360, 360, 5));
			Rotation_Angle_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Rotation) getTransformation()).setAngle((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			
			Rotation_Angle_Panel.add(Rotation_Angle_Label);
			Rotation_Angle_Panel.add(Rotation_Angle_JSpinner);
			
			Center_Panel.add(Rotation_Angle_Panel);
			
			break;
			
		case Rotation_fixed_point:
			
			JPanel Rotation_fixed_Angle_Panel = new JPanel();
			Rotation_fixed_Angle_Panel.setLayout(oneRow_twoCol);
			Rotation_fixed_Angle_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Rotation_Angle_fixed_Label = new JLabel("Angle :");
			//JTextField Rotation_Angle_fixed_TextField = new JTextField(6);
			Rotation_fixed_Angle_Panel.add(Rotation_Angle_fixed_Label);
			
			JSpinner Rotation_fixed_Angle_JSpinner = new JSpinner();	
			Rotation_fixed_Angle_JSpinner.setModel(new SpinnerNumberModel( ((Rotation_fixed_point) getTransformation()).getAngle(), -360, 360, 5));
			Rotation_fixed_Angle_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Rotation_fixed_point) getTransformation()).setAngle((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			Rotation_fixed_Angle_Panel.add(Rotation_fixed_Angle_JSpinner);
			
			
			
			
			//JPanel Rotation_fixed_xOffset_Panel = new JPanel();
			xOffset_Panel.setLayout(oneRow_twoCol);
			xOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Rotation_fixed_xOffset_Label = new JLabel("X offset :");
			//JTextField Rotation_fixed_xOffset_TextField = new JTextField(6);
			
			//JSpinner Rotation_fixed_xOffset_JSpinner = new JSpinner();	
			xOffset_JSpinner.setModel(new SpinnerNumberModel( ((Rotation_fixed_point) getTransformation()).getxOffset(), -100000, 100000, 5));
			xOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Rotation_fixed_point) getTransformation()).setxOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			xOffset_Panel.add(Rotation_fixed_xOffset_Label);
			xOffset_Panel.add(xOffset_JSpinner);
			
			//JPanel Rotation_fixed_yOffset_Panel = new JPanel();
			yOffset_Panel.setLayout(oneRow_twoCol);
			yOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Rotation_fixed_yOffset_Label = new JLabel("Y offset :");
			//JTextField Rotation_fixed_yOffset_TextField = new JTextField(6);
			
			
			//JSpinner Rotation_fixed_yOffset_JSpinner = new JSpinner();	
			yOffset_JSpinner.setModel(new SpinnerNumberModel( ((Rotation_fixed_point) getTransformation()).getyOffset(), -100000, 100000, 5));
			yOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Rotation_fixed_point) getTransformation()).setyOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			yOffset_Panel.add(Rotation_fixed_yOffset_Label);
			yOffset_Panel.add(yOffset_JSpinner);
			
			
			Center_Panel.add(Rotation_fixed_Angle_Panel);
			Center_Panel.add(xOffset_Panel);
			Center_Panel.add(yOffset_Panel);
			
			break;
			
		case Scaling:
			
			JPanel Scale_X_Panel = new JPanel();
			Scale_X_Panel.setLayout(oneRow_twoCol);
			Scale_X_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Scale_X_Label = new JLabel("X value :");
			
			JSpinner Scale_X_JSpinner = new JSpinner();	
			Scale_X_JSpinner.setModel(new SpinnerNumberModel( 1, -1000, 1000, 0.1));
			Scale_X_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Scaling) getTransformation()).setScaleX((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			//JTextField Scale_X_TextField = new JTextField(6);
			Scale_X_Panel.add(Scale_X_Label);
			Scale_X_Panel.add(Scale_X_JSpinner);
			
			JPanel Scale_Y_Panel = new JPanel();
			Scale_Y_Panel.setLayout(oneRow_twoCol);
			Scale_Y_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Scale_Y_Label = new JLabel("Y value :");
			

			JSpinner Scale_Y_JSpinner = new JSpinner();	
			Scale_Y_JSpinner.setModel(new SpinnerNumberModel( 1, -1000, 1000, 0.1));
			Scale_Y_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Scaling) getTransformation()).setScaleY((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			//JTextField Scale_Y_TextField = new JTextField(6);
			Scale_Y_Panel.add(Scale_Y_Label);
			Scale_Y_Panel.add(Scale_Y_JSpinner);
			
			Center_Panel.add(Scale_X_Panel);
			Center_Panel.add(Scale_Y_Panel);
			
			break;
			
		case Scaling_fixed_point:
			
			JPanel Scale_fixed_X_Panel = new JPanel();
			Scale_fixed_X_Panel.setLayout(oneRow_twoCol);
			Scale_fixed_X_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Scale_fixed_X_Label = new JLabel("X value :");
			//JTextField Scale_fixed_X_TextField = new JTextField(6);
			
			JSpinner Scale_fixed_X_JSpinner = new JSpinner();	
			Scale_fixed_X_JSpinner.setModel(new SpinnerNumberModel( ((Scaling_fixed_point) getTransformation()).getScaleX(), -1000, 1000, 0.1));
			Scale_fixed_X_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Scaling_fixed_point) getTransformation()).setScaleX((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			
			Scale_fixed_X_Panel.add(Scale_fixed_X_Label);
			Scale_fixed_X_Panel.add(Scale_fixed_X_JSpinner);
			
			JPanel Scale_fixed_Y_Panel = new JPanel();
			Scale_fixed_Y_Panel.setLayout(oneRow_twoCol);
			Scale_fixed_Y_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Scale_fixed_Y_Label = new JLabel("Y value :");
			//JTextField Scale_fixed_Y_TextField = new JTextField(6);
			
			JSpinner Scale_fixed_Y_JSpinner = new JSpinner();	
			Scale_fixed_Y_JSpinner.setModel(new SpinnerNumberModel( ((Scaling_fixed_point) getTransformation()).getScaleY(), -1000, 1000, 0.1));
			Scale_fixed_Y_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Scaling_fixed_point) getTransformation()).setScaleY((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			Scale_fixed_Y_Panel.add(Scale_fixed_Y_Label);
			Scale_fixed_Y_Panel.add(Scale_fixed_Y_JSpinner);
			
			//JPanel Scale_fixed_xOffset_Panel = new JPanel();
			xOffset_Panel.setLayout(oneRow_twoCol);
			xOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Scale_fixed_xOffset_X_Label = new JLabel("X offset :");
			//JTextField Scale_fixed_xOffset_X_TextField = new JTextField(6);
			
			//JSpinner Scale_fixed_xOffset_JSpinner = new JSpinner();	
			xOffset_JSpinner.setModel(new SpinnerNumberModel( ((Scaling_fixed_point) getTransformation()).getxOffset(), -100000, 100000, 5));
			xOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Scaling_fixed_point) getTransformation()).setxOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			xOffset_Panel.add(Scale_fixed_xOffset_X_Label);
			xOffset_Panel.add(xOffset_JSpinner);
			
			//JPanel Scale_fixed_yOffset_Panel = new JPanel();
			yOffset_Panel.setLayout(oneRow_twoCol);
			yOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Scale_fixed_yOffset_Label = new JLabel("Y offset :");
			//JTextField Scale_fixed_yOffset_TextField = new JTextField(6);
			
			//JSpinner Scale_fixed_yOffset_JSpinner = new JSpinner();	
			yOffset_JSpinner.setModel(new SpinnerNumberModel( ((Scaling_fixed_point) getTransformation()).getyOffset(), -100000, 100000, 5));
			yOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Scaling_fixed_point) getTransformation()).setyOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			yOffset_Panel.add(Scale_fixed_yOffset_Label);
			yOffset_Panel.add(yOffset_JSpinner);
			
			Center_Panel.add(Scale_fixed_X_Panel);
			Center_Panel.add(Scale_fixed_Y_Panel);
			Center_Panel.add(xOffset_Panel);
			Center_Panel.add(yOffset_Panel);
			
			break;
			
		case Reflection:
			
			JPanel Reflection_Angle_Panel = new JPanel();
			Reflection_Angle_Panel.setLayout(oneRow_twoCol);
			Reflection_Angle_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Reflection_Angle_Label = new JLabel("Angle :");
			//JTextField Reflection_Angle_TextField = new JTextField(6);
			
			JSpinner Reflection_Angle_JSpinner = new JSpinner();	
			Reflection_Angle_JSpinner.setModel(new SpinnerNumberModel( ((Reflection) getTransformation()).getAngle(), 0, 180, 5));
			Reflection_Angle_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Reflection) getTransformation()).setAngle((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			Reflection_Angle_Panel.add(Reflection_Angle_Label);
			Reflection_Angle_Panel.add(Reflection_Angle_JSpinner);
			
			//JPanel Reflection_xOffset_Panel = new JPanel();
			xOffset_Panel.setLayout(oneRow_twoCol);
			xOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Reflection_xOffset_Label = new JLabel("X offset :");
			//JTextField Reflection_xOffset_TextField = new JTextField(6);
			
			//JSpinner Reflection_xOffset_JSpinner = new JSpinner();	
			xOffset_JSpinner.setModel(new SpinnerNumberModel( ((Reflection) getTransformation()).getxOffset(), -100000, 100000, 5));
			xOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Reflection) getTransformation()).setxOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			xOffset_Panel.add(Reflection_xOffset_Label);
			xOffset_Panel.add(xOffset_JSpinner);
			
			//JPanel Reflection_yOffset_Panel = new JPanel();
			yOffset_Panel.setLayout(oneRow_twoCol);
			yOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Reflection_yOffset_Label = new JLabel("Y offset :");
			//JTextField Reflection_yOffset_TextField = new JTextField(6);
			
			//JSpinner Reflection_yOffset_JSpinner = new JSpinner();	
			yOffset_JSpinner.setModel(new SpinnerNumberModel( ((Reflection) getTransformation()).getyOffset(), -100000, 100000, 5));
			yOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Reflection) getTransformation()).setyOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			yOffset_Panel.add(Reflection_yOffset_Label);
			yOffset_Panel.add(yOffset_JSpinner);
			
			
			
			Center_Panel.add(Reflection_Angle_Panel);
			Center_Panel.add(xOffset_Panel);
			Center_Panel.add(yOffset_Panel);
			
			break;
			
		case Shear:
			
			JPanel Shear_X_Panel = new JPanel();
			Shear_X_Panel.setLayout(oneRow_twoCol);
			Shear_X_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Shear_X_Label = new JLabel("X value :");
			//JTextField Shear_X_TextField = new JTextField(6);
			
			JSpinner Shear_X_JSpinner = new JSpinner();	
			Shear_X_JSpinner.setModel(new SpinnerNumberModel( ((Shear) getTransformation()).getX(), -1000, 1000, 0.5));
			Shear_X_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Shear) getTransformation()).setX((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			Shear_X_Panel.add(Shear_X_Label);
			Shear_X_Panel.add(Shear_X_JSpinner);
			
			JPanel Shear_Y_Panel = new JPanel();
			Shear_Y_Panel.setLayout(oneRow_twoCol);
			Shear_Y_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Shear_Y_Label = new JLabel("Y value :");
			//JTextField Shear_Y_TextField = new JTextField(6);
			
			JSpinner Shear_Y_JSpinner = new JSpinner();	
			Shear_Y_JSpinner.setModel(new SpinnerNumberModel( ((Shear) getTransformation()).getY(), -1000, 1000, 0.5));
			Shear_Y_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Shear) getTransformation()).setY((double) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			
			Shear_Y_Panel.add(Shear_Y_Label);
			Shear_Y_Panel.add(Shear_Y_JSpinner);
			
			//JPanel Shear_xOffset_Panel = new JPanel();
			xOffset_Panel.setLayout(oneRow_twoCol);
			xOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Shear_xOffset_Label = new JLabel("X offset :");
			//JTextField Shear_xOffset_TextField = new JTextField(6);
			
			//JSpinner Shear_xOffset_JSpinner = new JSpinner();	
			xOffset_JSpinner.setModel(new SpinnerNumberModel( ((Shear) getTransformation()).getxOffset(), -100000, 100000, 5));
			xOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Shear) getTransformation()).setxOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			
			xOffset_Panel.add(Shear_xOffset_Label);
			xOffset_Panel.add(xOffset_JSpinner);
			
			//JPanel Shear_yOffset_Panel = new JPanel();
			yOffset_Panel.setLayout(oneRow_twoCol);
			yOffset_Panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
			JLabel Shear_yOffset_Label = new JLabel("Y offset :");
			//JTextField Shear_yOffset_TextField = new JTextField(6);
			
			//JSpinner Shear_yOffset_JSpinner = new JSpinner();	
			yOffset_JSpinner.setModel(new SpinnerNumberModel( ((Shear) getTransformation()).getyOffset(), -100000, 100000, 5));
			yOffset_JSpinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {
				 JSpinner s = (JSpinner) e.getSource();
				 
				 ((Shear) getTransformation()).setyOffset((int) s.getValue());
				 Animation_panel.selectPanel(returnSuper());
			  }
		     });
			
			yOffset_Panel.add(Shear_yOffset_Label);
			yOffset_Panel.add(yOffset_JSpinner);
			
			Center_Panel.add(Shear_X_Panel);
			Center_Panel.add(Shear_Y_Panel);
			Center_Panel.add(xOffset_Panel);
			Center_Panel.add(yOffset_Panel);
			
			break;
		}
		
		
		All_Panels.add(Top_Panel, BorderLayout.NORTH);
		All_Panels.add(Center_Panel, BorderLayout.CENTER);
		this.add(All_Panels);
		
	}


	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		//Animation_panel.SelectedPanel = this;
		Animation_panel.selectPanel(this);
		revalidate();
		
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {	
	}
	@Override
	public void mouseEntered(MouseEvent e) {	
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	
	
	
}
