package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends AbstractShape{
	private Map<String, Double> properties = new HashMap<>();
	int width;
	int height;
	
	
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		Double x= properties.get("endx");
		this.properties.put("endx", x);
		Double y= properties.get("endy");
		this.properties.put("endy", y);
	}
	
	
	
	public void draw(Graphics canvas) {
		canvas.setColor(this.getColor());
		if(!properties.isEmpty()) {
		 int x = (int) Math.round(properties.get("endx")); // 3
         int y = (int) Math.round(properties.get("endy"));
         
         Point p = new Point();
         p = getPosition();
         
         if(x<p.x&&y<p.y){
        	 
             canvas.drawRect(x , y , p.x-x,p.y-y);

         }
         else if(x<p.x&&y>p.y){
        	 canvas.setColor(this.getColor());
         canvas.drawRect(x , p.y , p.x-x, y-p.y);}
         else if(x>p.x&&y<p.y){
             canvas.drawRect(p.x , y , x-p.x, p.y-y);}
         else if(x>p.x&&y>p.y){
             canvas.drawRect(p.x , p.y , x-p.x, y-p.y);}
	}
	}
}
