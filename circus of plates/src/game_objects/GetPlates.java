package game_objects;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;


public class GetPlates {
public List <GameObject>  last (List <GameObject> shapes){
	
	List <GameObject>  k = new ArrayList();
	k.add(shapes.get(shapes.size()-1));
	int x=shapes.get(shapes.size()-1).getX();
	if(shapes.size()>5)
	for (int i = shapes.size()-2;i>-1;i--){
		if(shapes.get(i).getX()-x<5||x-shapes.get(i).getX()<5)
		{
			k.add(shapes.get(i));
			
		}
	}
	return k;
	
	
}
}
