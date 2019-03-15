package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Square extends Rectangle{

	private Map<String, Double> properties = new HashMap<>();
	
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		Double x= properties.get("endx");
		this.properties.put("endx", x);
		Double y= properties.get("endy");
		this.properties.put("endy", y);
	}
	
	
	
	public void draw(Graphics canvas) {
		if(!properties.isEmpty()) {
		 int x = (int) Math.round(properties.get("endx")); // 3
        int y = (int) Math.round(properties.get("endy"));
        
        Point p = new Point();
        p = getPosition();
        canvas.setColor(this.color);
        if(x<p.x&&y<p.y){
        	if(fill)
           	 canvas.fillRect(x , y , p.x-x, p.x-x);
           	 else        	 
           	 canvas.drawRect(x , y , p.x-x, p.x-x);
        }
        else if(x<p.x&&y>p.y){
        	if(fill)
              	 canvas.fillRect(x , p.y , p.x-x, p.x-x);
              	 else        	 
              	 canvas.drawRect(x , p.y , p.x-x, p.x-x);
        }
        else if(x>p.x&&y<p.y){
        	if(fill)
              	 canvas.fillRect(p.x , y , x-p.x, x-p.x);
              	 else        	 
              	 canvas.drawRect(p.x , y , x-p.x, x-p.x);
           }
        else if(x>p.x&&y>p.y){
        	if(fill)
              	 canvas.fillRect(p.x , p.y , x-p.x, x-p.x);
              	 else        	 
              	 canvas.drawRect(p.x , p.y , x-p.x, x-p.x);
            }
	}
	
	}
	
	
	
}



