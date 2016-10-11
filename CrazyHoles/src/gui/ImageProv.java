package gui;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageProv {
	private final Image greenHole;
	private final Image yellowHole;
	private final Image redHole;

	private final Image redBall;
	private final Image greenBall;
	private final Image yellowBall;

	private final Image directionBall;
	private final Image pause;
	private final Toolkit tk;
	private final Image level1;
	private final Image level2;
	private final Image level3;
	private final Image level4;
	private final Image level5;
	private final Image level6;
	private final Image gameOver;
	private final Image firstPlayer;
	private final Image secondPlayer;
	private final Image firstPlayerWin;
	private final Image secondPlayerWin;
	private final Image waiting;

	public ImageProv() {
		tk = Toolkit.getDefaultToolkit();

		yellowHole = tk.getImage("images/BucaGialla.png");
		greenHole = tk.getImage("images/BucaVerde.png");
		redHole = tk.getImage("images/BucaRossa.png");

		redBall = tk.getImage("images/PallaRossa.png");
		greenBall = tk.getImage("images/PallaVerde.png");
		yellowBall = tk.getImage("images/PallaGialla.png");
		directionBall = tk.getImage("images/da.png");

		pause = tk.getImage("images/PAUSE.png");
		level1 = tk.getImage("images/level1.png");
		level2 = tk.getImage("images/level2.png");
		level3 = tk.getImage("images/level3.png");
		level4 = tk.getImage("images/level4.png");
		level5 = tk.getImage("images/level5.png");
		level6 = tk.getImage("images/level6.png");
		gameOver = tk.getImage("images/gameover.png");
		firstPlayer = tk.getImage("images/Player1.png");
		secondPlayer = tk.getImage("images/Player2.png");
		firstPlayerWin = tk.getImage("images/Player1winner.png");
		secondPlayerWin = tk.getImage("images/Player2winner.png");
		waiting = tk.getImage("images/Waiting.png");
	}

	public Image getHole(final String color) {
		switch (color) {
		case "Green": {
			return greenHole;
		}
		case "Yellow": {
			return yellowHole;
		}
		case "Red": {
			return redHole;
		}
		default:
			break;
		}
		throw new IllegalArgumentException("Unsupported hole color");

	}

	public Image getGameOver() {
		return gameOver;
	}

	public Image getBall(final String color) {
		switch (color) {
		case "Green": {
			return greenBall;
		}
		case "Yellow": {
			return yellowBall;
		}
		case "Red": {
			return redBall;
		}
		default:
			break;

		}
		throw new IllegalArgumentException("Unsupported ball color");
	}

	public Image getDirectionBall() {
		return this.directionBall;
	}

	public Image getPause() {

		return pause;

	}

	public Image getLevel(int l) {
		switch (l) {
		case 1: {
			return level1;
		}
		case 2: {
			return level2;
		}
		case 3: {
			return level3;
		}
		case 4: {
			return level4;
		}
		case 5: {
			return level5;
		}
		case 6: {
			return level6;
		}
		default:
			break;
		}

		throw new IllegalArgumentException("unsupported level");
	}

	public Image getFirstPlayer() {
		return firstPlayer;
	}

	public Image getSecondPlayer() {
		return secondPlayer;
	}

	public Image getFirstPlayerWinner() {
		return firstPlayerWin;
	}

	public Image getSecondPlayerWinner() {
		return secondPlayerWin;
	}

	public Image getWaiting() {
		return waiting;
	}

}




