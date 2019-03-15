package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class Circle extends AbstractShape{

	Map<String, Double> properties = new HashMap<>();
	
	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		Double x= properties.get("radius");
		this.properties.put("radius", x);

	}
	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return properties;
	}
        @Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
           int x = (int) Math.round(properties.get("radius")); // 3
 
           Point p = new Point();
           p = getPosition();
           canvas.drawOval(p.x - x/2, p.y - x/2, x, x);
  }

	
}
