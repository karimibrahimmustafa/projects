package game_objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class AbstractObject implements GameObject {
	private int x;
	private int y;
	private int width;
	private int Height;

	private BufferedImage[] spriteImages = new BufferedImage[1];

	public AbstractObject(int width, int height, String path) {
		this.width = width;
		this.Height = height;
		try {
			spriteImages[0] = ImageIO.read(new File(path));;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;

	}

	@Override
	public int getY() {

		return this.y;
	}

	@Override
	public void setY(int y) {
		this.y = y;

	}

	@Override
	public int getWidth() {

		return this.width;
	}

	@Override
	public int getHeight() {

		return this.Height;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public BufferedImage[] getSpriteImages() {

		return spriteImages;
	}
}
