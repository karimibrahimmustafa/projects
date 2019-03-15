package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends Circle{
	
Map<String, Double> properties = new HashMap<>();
	
	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		Double x= properties.get("xAxis");
		this.properties.put("xAxis", x);
		Double y= properties.get("yAxis");
		this.properties.put("yAxis", y);
	}
	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return properties;
	}
        @Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
        	if(!properties.isEmpty()) {
           int x = (int) Math.round(properties.get("xAxis")); // 3
           int y = (int) Math.round(properties.get("yAxis"));
           int xs;
           int ys;
           Point p = new Point();
           p = getPosition();
      	 canvas.setColor(this.getColor());
           if(x<p.x&&y<p.y){
        	   xs=p.x;
        	   ys=p.y;
               canvas.drawOval(x , y , xs-x, ys-y);

           }
           else if(x<p.x&&y>p.y){
           canvas.drawOval(x , p.y , p.x-x, y-p.y);}
           else if(x>p.x&&y<p.y){
               canvas.drawOval(p.x , y , x-p.x, p.y-y);}
           else if(x>p.x&&y>p.y){
               canvas.drawOval(p.x , p.y , x-p.x, y-p.y);}
  }
        }

}
