package Objects;

public class EquazioniCirconferenza {
	private double noto;
	private double x;
	private double y;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getNoto() {
		return noto;
	}

	public void setNoto(double noto) {
		this.noto = noto;
	}

	public EquazioniCirconferenza(double xc, double yc, double r) {
		setNoto((Math.pow(xc, 2) + Math.pow(yc, 2) + (Math.pow(r, 2) * -1)));
		setX(-2 * xc);
		setY(-2 * yc);
	}

}
