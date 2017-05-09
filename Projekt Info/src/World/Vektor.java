package World;

import java.lang.Math;

public class Vektor {
	
	int x,y;
	
	Math math;
	
	public Vektor(int x, int y){
		this.x = x;
		this.y = y;
		math = new Math();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public double length(){
		return math.sqrt((x*x)+(y*y));
	}
	
	public void invert(){
		x = -x;
		y = -y;
	}
}
