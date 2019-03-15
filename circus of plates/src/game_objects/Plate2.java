package game_objects;

import java.awt.Color;
import java.awt.image.BufferedImage;

import classes.ColorShapes;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import shpe_state.Horizontal;
import shpe_state.ShapeState;

public class Plate2 extends AbstractObject {

	private ShapeState state;
	private int pointer;
	private World engine;
	private GameObject shelf;
    private BufferedImage [ ] spriteImages;
    private int x;
    private int y;
    private boolean newShape =false; 

	/**
	 * 
	 * @param width
	 * @param height
	 */
	public Plate2(String path,Color color, int width, int height, World engine, GameObject shelf) {
		super(width, height, path);
		ColorShapes m = new ColorShapes();
		spriteImages = new BufferedImage [1];
		spriteImages[0]=m.color(path, color);
		this.engine = engine;
		this.shelf = shelf;
		
	}

	@Override
	public void setX(int x) {
		this.x=x;
	}

	@Override
	public void setY(int y) {
		if(!newShape)
		this.y=y;
		newShape=true;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public BufferedImage[] getSpriteImages() {

		return spriteImages;
	}
	public boolean isVisible() {
		return false;
	}

}
