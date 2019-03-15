package game_objects;

import java.awt.Color;
import java.awt.image.BufferedImage;

import classes.ColorShapes;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import shpe_state.Horizontal;
import shpe_state.ShapeState;

public class Plate extends AbstractObject {

	private ShapeState state;
	private int pointer;
	private World engine;
	private GameObject shelf;
    private BufferedImage [ ] spriteImages;

	/**
	 * 
	 * @param width
	 * @param height
	 */
	public Plate(String path,Color color, int width, int height, World engine, GameObject shelf) {
		super(width, height, path);
		ColorShapes m = new ColorShapes();
		spriteImages = new BufferedImage [1];
		spriteImages[0]=m.color(path, color);
		this.engine = engine;
		this.shelf = shelf;
		if (shelf.getX() == 0)//left shelf
			super.setX(0);
		else//right shelf
			super.setX(shelf.getX()+shelf.getWidth());
		super.setY(this.shelf.getY());
		state = new Horizontal(this);
	}

	@Override
	public void setX(int x) {
		int newX = state.changeX(x);
		super.setX(newX);
	}

	@Override
	public void setY(int y) {
		int newY = state.changeY(y);
		super.setY(newY);
	}

	public void setState(ShapeState state) {
		this.state = state;
	}

	public ShapeState getState() {
		return this.state;
	}
	public World getEngine() {
		return engine;
	}

	public GameObject getShelf() {
		return shelf;
	}

	public BufferedImage[] getSpriteImages() {

		return spriteImages;
	}


}
