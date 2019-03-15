package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class LineSegment extends AbstractShape{

Map<String, Double> properties = new HashMap<>();
	

	Double x;
	Double y;
	Double x2;
	Double y2;
	

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		
		
		x = properties.get("startPointx");
		this.properties.put("startPointx", x);
		
		y = properties.get("startPointy");
		this.properties.put("startPointy", y);
		
		
		x2 = properties.get("endPointx");
		this.properties.put("endPointx", x2);
		
		y2 = properties.get("endPointy");
		this.properties.put("endPointy", y2);
		
		
		

	}
	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return properties;
	}
        @Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
        	if(!properties.isEmpty()){
        		int a = (int) (x - 0);
            	int b = (int) (y - 0);
            	int a1 = (int) (x2 - 0);
            	int b1 = (int) (y2 - 0);
           	 canvas.setColor(this.getColor());
            	canvas.drawLine(a, b, a1, b1);
        	}
        	
        	
        	
        	
 
  }

}
