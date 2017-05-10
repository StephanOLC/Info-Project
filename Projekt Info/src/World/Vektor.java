package World;

import java.lang.Math;

public class Vektor {
	
	int x,y;
	double length;
	
	public Vektor(int x, int y){
		this.x = x;
		this.y = y;
		length = Math.sqrt((x*x)+(y*y));
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public double length(){
		return length;
	}
	
	public void invert(){
		x = -x;
		y = -y;
	}
	
	public Vektor connectingTo(Vektor to){
		Vektor connectionVektor = new Vektor((to.getX()-x), (to.getY() - y));
		return connectionVektor;
	}
}
