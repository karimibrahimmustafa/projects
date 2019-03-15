package shpe_state;

import eg.edu.alexu.csd.oop.game.GameObject;
import game_objects.Plate;

public class Vertical implements ShapeState {

	private Plate shape;

	public Vertical(Plate shape) {
		this.shape = shape;
	}

	@Override
	public int changeX(int x) {

		return shape.getX();
	}

	@Override
	public int changeY(int y) {
		int numOfPlates = shape.getEngine().getControlableObjects().size() - 1;
		if (y + shape.getHeight() == shape.getEngine().getControlableObjects().get(numOfPlates).getY()) {
			shape.setState(new Controlable(shape));
			shape.getEngine().getControlableObjects().add(shape);// ?????????????
			shape.getEngine().getMovableObjects().remove(shape);// ??????????????
			return y;
		} else if (y >= shape.getEngine().getHeight()) {
			
		} 
		return y;
	}

}
