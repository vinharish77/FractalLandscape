
public class LineEquation {
	double slope;
	double yIntercept;
	double xIntercept;
	
	public LineEquation(double x1, double y1,double x2, double y2){
		this.slope = ((y2-y1)/(x2-x1));
		this.yIntercept=(-x1*this.slope) +y1;
		this.xIntercept=((-y1/this.slope)+x1);
	}
	
	public double getSlope(){
		return this.slope;
	}
	
	public double getYIntercept(){
		return this.yIntercept;
	}
	
	public double getXIntercept(){
		return this.xIntercept;
	}
}
