package game_objects;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class Clown extends AbstractObject {

	private int y = 0;

	public Clown(String path, World engine, int width, int height) {
		super(width, height, path);
		super.setX(engine.getWidth() / 2);
		y = engine.getHeight() - height;
	}

	@Override
	public int getY() {

		return this.y;
	}

	@Override
	public void setY(int y1) {

	}

}
