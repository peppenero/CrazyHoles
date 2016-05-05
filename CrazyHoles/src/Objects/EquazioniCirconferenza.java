package Objects;


public class EquazioniCirconferenza {
	private double noto;
	private double x;
	private double y;
	double xnoto;
	double yvar;
	double xy;
	double  delta;
	double a ;
	double b;
	double c;
	double x1;
	double x2;
	double y1;
	double y2;
	boolean spuria=false;
		
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
	
	private double getX1()
	{
		return x1;
	}
		
	private double getX2()
	{
		return x2;
	}
	private double getY1()
	{
		return y1;
	}
	private double getY2()
	{
		return y2;
	}
	
	
	
	public  EquazioniCirconferenza(int xc,int yc,int r)
	{
		setNoto( (Math.pow(xc, 2) + Math.pow(yc, 2) + (Math.pow(r, 2)*-1)));
		setX(-2*xc);
		setY(-2*yc);		
	}
	
	public int intersezioni(Ball ball,Hole eq)
	{
		EquazioniCirconferenza hole = eq.getEquation();
	 	EquazioniCirconferenza bal = ball.getEquation();
		if(this.x==hole.x)
		{
			spuria = true;
			double yeq = bal.y + (-1*hole.getY());
			double notoeq = bal.noto + (-1*hole.getNoto());
			yvar = (-1*notoeq)/yeq;
			 a = 1;
			 b = bal.x;
			 c = (Math.pow(yvar, 2)) + (bal.y * yvar) + bal.noto;
			delta = (Math.pow(b, 2)+((4*a*c)*-1));
		}
		else
		{
			double xeq = bal.x + (-1*hole.getX());
			double yeq = bal.y + (-1*hole.getY());
			double notoeq = bal.noto + (-1*hole.getNoto());
			
			
			 xy = (-1*yeq)/xeq;
			 xnoto = (-1*notoeq)/xeq;
			double yquadro = Math.pow(xy, 2);
			double dprod = 2*xy*xnoto;
			double nquadro =  Math.pow(xnoto, 2);
			double sx = bal.x*xy;     
			double nx = bal.x*xnoto;
			 a = yquadro+1;
			 
			 b = dprod+sx+bal.y;
			 c = nquadro+nx+bal.noto; 
			delta = (Math.pow(b, 2)+((4*a*c)*-1));
		}
			   			
			if(delta<0)
			{		
				return 0;
			}
			else
			{
//				if(ball.getColor()!=eq.getColor())
//					return 1;
				if(spuria)
				{
					x1= (-b +  Math.sqrt(delta))/(2*a);
					x2= (-b -  Math.sqrt(delta))/(2*a);
					y1= yvar;
					y2=yvar;	
				}
				else
				{
					y1 = (-b +  Math.sqrt(delta));
					y2 = (-b -  Math.sqrt(delta));
					y1/=2*a;
					y2/=2*a;
					x1 = xy*y1 + xnoto;
					x2 = xy*y2 + xnoto;	
					System.out.println(getX1());
					System.out.println(getY1());
					System.out.println(getX2());
					System.out.println(getY2());
				}
				
				if((eq.getX1()>eq.getX2() && eq.getY1()<eq.getY2()) && (getX1()<eq.getX1() && getX2()<eq.getX1() && getX1()>eq.getX2() && getX2()>eq.getX2() && getY1()>eq.getY()))
				{	
					
					return 2;
				}
				if((eq.getX1()<eq.getX2() && eq.getY1()<eq.getY2()) && (getX1()>eq.getX1() && getX1()<eq.getX2() && getX2()>eq.getX1() && getX2()<eq.getX2() && getY1()<eq.getY()))
				{		
					return 2;
				}
				if((eq.getX1()<eq.getX2() && eq.getY1()>eq.getY2()) && (getX1()>eq.getX1() && getX1()<eq.getX2() && getX2()>eq.getX1() && getX2()<eq.getX2() && getY1()<eq.getY()))
				{					
					return 2;
				}
				if((eq.getX1()>eq.getX2() && eq.getY1()>eq.getY2()) && (getX1()<eq.getX1() && getX1()>eq.getX2() && getX2()<eq.getX1() && getX2()>eq.getX2() && getY1()>eq.getY()))
				{					
					return 2;
				}
				if((eq.getY1()==eq.getY2() && eq.getX2()<eq.getX1()) && (getX1()<eq.getX1() && getX2()<eq.getX1() && getX1()>eq.getX2() && getX2()>eq.getX2() && getY1()>eq.getY()))
				
					return 2;
				}
				if((eq.getY1()==eq.getY2() && eq.getX1()<eq.getX2()) && (getX1()>eq.getX1() && getX2()>eq.getX1() && getX1()<eq.getX2() && getX2()<eq.getX2() && getY1()<eq.getY())) 
				{					
					return 2;
				}
				if((eq.getX1()==eq.getX2() && eq.getY1()>eq.getY2()) && (getY1()<eq.getY1() && getY2()<eq.getY1() && getY1()>eq.getY2() && getY2()>eq.getY2() && getX1()<eq.getX()))
				{
					return 2;
				}
				if((eq.getX1()==eq.getX2() && eq.getY1()<eq.getY2()) && (getY1()>eq.getY1() && getY2()>eq.getY1() && getY1()<eq.getY2() && getY2()<eq.getY2() && getX1()>eq.getX()))
				{
					return 2;
				}
				if((eq.getX1()>eq.getX2() && eq.getY1()>eq.getY2()) && (getY1()<eq.getY1() && getY1()>eq.getY2() && getY2()<eq.getY1() && getY2()>eq.getY2() && getX1()<eq.getX()))
				{
					return 2;
				}
				if((eq.getX1()>eq.getX2() && eq.getY1()<eq.getY2()) && (getY1()>eq.getY1() && getY1()<eq.getY2() && getY2()>eq.getY1() && getY2()<eq.getY2() && getX1()>eq.getX()))
				{
					return 2;
				}
				if((eq.getX1()<eq.getX2() && eq.getY1()<eq.getY2()) && (getY1()>eq.getY1() && getY1()<eq.getY2() && getY2()>eq.getY1() && getY2()<eq.getY2() && getX1()>eq.getX()))
				{
					return 2;
				}
				if((eq.getX1()<eq.getX2() && eq.getY1()>eq.getY2()) && (getY1()<eq.getY1() && getY1()>eq.getY2() && getY2()<eq.getY1() && getY2()>eq.getY2() && getX1()<eq.getX()))
				{
					return 2;
				}
					return 1;
				
				
			}
}
	




