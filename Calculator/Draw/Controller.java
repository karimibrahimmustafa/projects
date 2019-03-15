package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JTextField;


import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;



import javax.swing.JPanel;

public class Controller implements DrawingEngine{
	
	
	List<Shape> shapes = new ArrayList<>();
	
    @Override
    public void refresh(Graphics canvas) {
    	canvas.clearRect(0, 0, 2000, 2000);
    	for(int i = 0; i < shapes.size(); i++) {
    		
    		Shape s = (Shape) shapes.get(i);
    		s.draw(canvas);
    		
    	}
    
    
    
    
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    
    }

    @Override
    public void removeShape(Shape shape) {
    	shapes.remove(shape);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape[] getShapes() {
return (Shape[]) shapes.toArray();
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /* public void ReDraw (JPanel panel_1, int Shape, int numOfClicks, Point r,Shape shape, DrawingEngine paint) {
		
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel_1.setBackground(Color.WHITE);
				if(Shape == 1) {
					if( numOfClicks == 0) {
							
							r.x = arg0.getX();
							r.y = arg0.getY();
							
							
							
							numOfClicks = 1;
							Map<String, Double> properties = new HashMap<>();
							 properties.put("startPointx",(double)r.x);
						     properties.put("startPointy",(double)r.y);	
							
						     
							panel_1.addMouseMotionListener(new MouseMotionAdapter() {
								
								@Override
								public void mouseMoved(MouseEvent arg0) {
									if(numOfClicks == 1) {
									r.x = arg0.getX();
									r.y = arg0.getY();
									
								
									properties.put("endPointx",(double)r.x);
								    properties.put("endPointy",(double)r.y);	
							       				       
							        shape.setProperties(properties);
							        Graphics g = panel_1.getGraphics();
							        paint.addShape(shape);
							        paint.refresh(g);
							        
							        
							        panel_1.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseClicked(MouseEvent arg0) {
											
											shape = new LineSegment();
											numOfClicks = 0;
											
										}
							        });

									}

								}
							});
							
							
							
							
							
							
							
							
							
							
							
					}
					
					
					
					
					
					
					
					}
				if(Shape == 2)
				{
					
					
				}
				if(Shape == 3)
				{
					
					
				}
				if(Shape == 4)
				{if(numOfClicks == 0) {
					
					numOfClicks = 1;
					
					center.x = arg0.getX();
					center.y = arg0.getY();
					yR = 5;
					
					shape.setPosition(center);
							
					panel_1.addMouseMotionListener(new MouseMotionAdapter() {
						
						@Override
						public void mouseMoved(MouseEvent arg0) {
							if(numOfClicks == 1) {
							r.x = arg0.getX();
							r.y = arg0.getY();
							
							xR = r.distance(center.x, center.y);
						
							Map<String, Double> properties = new HashMap<>();
					        properties.put("xAxis",(double)r.x);
					        properties.put("yAxis",(double)r.y);					       
					        shape.setProperties(properties);
					        Graphics g = panel_1.getGraphics();
					        paint.addShape(shape);
					        paint.refresh(g);
					        
					        
					        panel_1.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {
									
									
									numOfClicks = 0;
									
									
								}
					        });

							}

						}
					});
				
				}}
			
			if(Shape == 5){
				
			}	
				
            if(Shape == 6){
                if(numOfClicks == 0) {
					numOfClicks = 1;
					center.x = arg0.getX();
					center.y = arg0.getY();
					yR = 5;
					
					shape.setPosition(center);
	panel_1.addMouseMotionListener(new MouseMotionAdapter() {
						
						@Override
						public void mouseMoved(MouseEvent arg0) {
							if(numOfClicks == 1) {
							r.x = arg0.getX();
							r.y = arg0.getY();
							
							xR = r.distance(center.x, center.y);
						
							Map<String, Double> properties = new HashMap<>();
					        properties.put("endx",(double)r.x);
					        properties.put("endy",(double)r.y);
					        if(isCreated){paint.removeShape(shape);}
					        shape.setProperties(properties);
					        Graphics g = panel_1.getGraphics();
					        paint.addShape(shape);
					        paint.refresh(g);
					        
					        
					        panel_1.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {
									
									isCreated=false;
									numOfClicks = 0;
									shape = null;
								}
					        });
							}

						}
					});

}
	
			}	
            if(Shape == 7){
			
	
			}		
				
				
				
				
				
				
			
				
				
				
				
				
				
				
				
			}
		});
		}
	
	
	*/
}
