package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

public class AbstractShape implements Shape {

	Point position = new Point();
	Color color = new Color(000);
	boolean fill = false;
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		this.position = position;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
this.color=color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setFillColor(Color color) {
fill=true;
this.color=color;
	}

	@Override
	public Color getFillColor() {
if(!fill)
{return null;}
else return color;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub

	}
	
	
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		return null;
	}

}
