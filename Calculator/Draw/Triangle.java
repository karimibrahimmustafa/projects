package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.util.Map;

public class Triangle extends LineSegment{
	
	Double x;
	Double y;
	Double x2;
	Double y2;
	Double x3;
	Double y3;
	
	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		
		
		x = properties.get("startPointx");
		this.properties.put("startPointx", x);
		
		y = properties.get("startPointy");
		this.properties.put("startPointy", y);
		
		
		x2 = properties.get("secondPointx");
		this.properties.put("secondPointx", x2);
		
		y2 = properties.get("secondPointy");
		this.properties.put("secondPointy", y2);
		
		x3 = properties.get("endPointx");
		this.properties.put("endPointx", x3);
		
		y3 = properties.get("endPointy");
		this.properties.put("endPointy", y3);
		
		
		

	}
	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return properties;
	}
        @Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
        	
        	int a = (int) (x - 0);
        	int b = (int) (y - 0);
        	int a1 = (int) (x2 - 0);
        	int b1 = (int) (y2 - 0);
        	int a2 = (int) (x3 - 0);
        	int b2 = (int) (y3 - 0);
        	canvas.setColor(this.color);
        	if(fill)
        	canvas.fillPolygon(new int[]{a,a1,a2},new int[] {b,b1,b2}, 3);
        	else
        		{canvas.drawPolyline(new int[]{a,a1,a2}, new int[] {b,b1,b2}, 3);
        	canvas.drawLine(a, b, a2, b2);
        		}
 
  }

}
