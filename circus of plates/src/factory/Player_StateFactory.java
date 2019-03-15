package factory;

import game_strategy.GameOver;
import game_strategy.PlayerWin;
import game_strategy.StartGame;
import game_strategy.State;

public class Player_StateFactory {

	public State changeState(String state) {
		if(state.equals("start")) {
			return new StartGame();
		}else if(state.equals("gameOver")) {
			return new GameOver();
		}else if(state.equals("win")) {
			return new PlayerWin();
		}
		
		return null;
	}
}
