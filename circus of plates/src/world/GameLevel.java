package world;

import java.awt.Color;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface GameLevel {

	public void setSpeed(int speed);

	public int getSpeed();

	public void setColors(ArrayList<Color> colors);

	public ArrayList<Color> getColors();

	public void setShapePaths(ArrayList<String> paths);

	public ArrayList<String> getShapePaths();

	public void setScoreLimit(int score);

	public int getScoreLimet();

	public void setTimeLimit(int time);

	public int getTimeLimit();
}
