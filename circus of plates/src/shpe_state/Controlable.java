package shpe_state;

import eg.edu.alexu.csd.oop.game.GameObject;
import game_objects.Plate;

public class Controlable implements ShapeState{

	private Plate shape;
	public Controlable(Plate shape) {
		this.shape = shape;
	}
	@Override
	public int changeX(int x) {
		// TODO Auto-generated method stub
		return shape.getEngine().getControlableObjects().get(0).getX();
	}

	@Override
	public int changeY(int y) {
		// TODO Auto-generated method stub
		return shape.getY();
	}



}
