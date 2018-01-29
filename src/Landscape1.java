import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;

import acm.breadboards.AbstractBreadboard;
import acm.breadboards.OneButtonBreadboard;
import acm.breadboards.TwoButtonBreadboard;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.toys.PixelGrid;


public class Landscape1 extends AbstractBreadboard{
	/**
	 * Created by @authorVineethHarish
	 * July 10 2016
	 */
	
	int numberOfPoints;
	int numberOfIterations = 15;
	ArrayList <LineEquation> equations;
	int randomCase;
	final int TOTAL_X = 99;
	final int TOTAL_Y = 99;
	GPoint [] points;
	double [][] gridHeights = new double[TOTAL_X+1][TOTAL_Y+1];
	PixelGrid grid = new PixelGrid(600,600,TOTAL_X,TOTAL_Y,0);
	
	
	public void init(){
		JButton button1 = new JButton("Generate Landscape"); 
		button1.addActionListener( 
	 				new ActionListener() { 
	 					public void actionPerformed(ActionEvent e) { 
	 						onButtonClick(); 
	 					}
	 				}); 
		add(button1,SOUTH);
		
		Timer timer = new Timer(5000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				run();
			}
			
		});
		timer.start();
	}
	

	public void run(){
		
		for(int r = 0; r < gridHeights.length; r++)
			for(int c = 0; c < gridHeights[0].length; c++){
				gridHeights[r][c] = 0;
			}
			
		
		
		for(int i = 0; i < numberOfIterations; i++){
			for(int j = 0; j<Math.pow(2.0,i); j++ ){
			earthquake(3);
			//displayPoints();
			plotLine();
			changeHeights(1000,i);
			equations.clear();
			}
		}
		colorLandscape();
		
		
		/*earthquake(10);
		plotLine();*/
		
		//grid.setLocation(540, 150);
		add(grid);
		
		

		
	}

	
	public void onButtonClick(){
		
		
			run();
		
		
	}
	
	
	public void earthquake(int numberOfPoints){
		this.numberOfPoints = numberOfPoints;
		points = new GPoint [numberOfPoints];
		System.out.println(points);
		int randomCase = (int)(Math.random()*2);
		this.randomCase = randomCase;
		switch(randomCase){
		case 0:
			points[0]= new GPoint(0, (int)((Math.random()*(TOTAL_Y))));
			points[numberOfPoints-1] = new GPoint(TOTAL_X, (int)(Math.random()*(TOTAL_Y+1)));
			int tempRandomX = 1+(int)Math.random()*TOTAL_X;
			for(int i =1; i< numberOfPoints-1; i++){
				points[i] = new GPoint(tempRandomX, 1+(int)(Math.random()*TOTAL_Y+1));
				tempRandomX = tempRandomX+(int)(Math.random()*TOTAL_X);
			
			}
		break;
	
		case 1:
			points[0]= new GPoint((int)(Math.random()*(TOTAL_X)),0);
			points[numberOfPoints-1] = new GPoint(1+(int)(Math.random()*(TOTAL_X+1)),TOTAL_Y);
			int tempRandomY = 1+(int)(Math.random()*TOTAL_Y);
			for(int i =1; i< numberOfPoints-1; i++){
				points[i] = new GPoint(1+(int)(Math.random()*TOTAL_X+1),tempRandomY);
				tempRandomY = tempRandomY+(int)(Math.random()*TOTAL_Y);	
			}
			break;
			}
	
	}
	
	public void plotLine(){
		LineEquation e;
		equations = new ArrayList <LineEquation>();
		for(int i = 0; i < points.length-1; i++){
			System.out.println(points[i]+" " + i);
			e = new LineEquation((int)points[i].getX(), (int)points[i].getY(), (int)points[i+1].getX(), (int)points[i+1].getY());
			equations.add(e);
			//grid.plotLine((int)points[i].getX(), (int)points[i].getY(), (int)points[i+1].getX(), (int)points[i+1].getY(), Color.BLACK);
		}
		
	}
	
	public void changeHeights(int initialBaseChange, int numOfIterations){
		//int randomHeight = minimumHeightLossOrGain+((int)(Math.random()*maximumHeightLossOrGain));
		int randomUpOrDown = (int)(Math.random()*2);
	switch(randomUpOrDown){
	case 0:	
		switch(this.randomCase){
		
		case 0:
			for(int i = 0; i<equations.size(); i++){
		for(int r = 0; r < gridHeights.length; r++)
			for(int c = 0; c < gridHeights[0].length; c++){
				
			
					if(r<= (equations.get(i).slope*c)+equations.get(i).yIntercept && c <= points[i+1].getX()){
			
						gridHeights[c][r] = gridHeights[c][r] - (initialBaseChange*(Math.pow(0.95, numOfIterations))); 
					
					}
					
					if(r> (equations.get(i).slope*c)+equations.get(i).yIntercept && c <= points[i+1].getX()){
						gridHeights[c][r] = gridHeights[c][r] + (initialBaseChange*(Math.pow(0.95, numOfIterations))); 

					}
				}
			}
		break;
		
		case 1:
			
			for(int r = 0; r < gridHeights.length; r++)
				for(int c = 0; c < gridHeights[0].length; c++){
					for(int i = 0; i<equations.size(); i++){
				
						if(c<= (equations.get(i).slope*r)+equations.get(i).yIntercept && r <= points[i+1].getX()){
				
							gridHeights[c][r] = gridHeights[c][r] - (initialBaseChange*(Math.pow(0.95, numOfIterations))); 
						
						}
						
						 if(c> (equations.get(i).slope*r)+equations.get(i).yIntercept && r <= points[i+1].getX()){
							gridHeights[c][r] = gridHeights[c][r] + (initialBaseChange*(Math.pow(0.95, numOfIterations))); 

						}
					}
				}
		
		break;
		}
		break;	
	case 1:
switch(this.randomCase){
		
		case 0:
			for(int i = 0; i<equations.size(); i++){
		for(int r = 0; r < gridHeights.length; r++)
			for(int c = 0; c < gridHeights[0].length; c++){
				
			
					if(r<= (equations.get(i).slope*c)+equations.get(i).yIntercept && c <= points[i+1].getX()){
			
						gridHeights[c][r] = gridHeights[c][r] + (initialBaseChange*(Math.pow(0.95, numOfIterations))); 
					
					}
					
					if(r> (equations.get(i).slope*c)+equations.get(i).yIntercept && c <= points[i+1].getX()){
						gridHeights[c][r] = gridHeights[c][r] - (initialBaseChange*(Math.pow(0.95, numOfIterations))); 

					}
				}
			}
		break;
		
		case 1:
			
			for(int r = 0; r < gridHeights.length; r++)
				for(int c = 0; c < gridHeights[0].length; c++){
					for(int i = 0; i<equations.size(); i++){
				
						if(c<= (equations.get(i).slope*r)+equations.get(i).yIntercept && r <= points[i+1].getX()){
				
							gridHeights[c][r] = gridHeights[c][r] + (initialBaseChange*(Math.pow(0.95, numOfIterations))); 
						
						}
						
						 if(c> (equations.get(i).slope*r)+equations.get(i).yIntercept && r <= points[i+1].getX()){
							gridHeights[c][r] = gridHeights[c][r] - (initialBaseChange*(Math.pow(0.95, numOfIterations))); 

						}
					}
				}
		
		break;
		}
		break;	
		}
	}
	
	
	
	public void colorLandscape(){
		double lowestHeight = Double.MAX_VALUE;
		double greatestHeight = Double.MIN_VALUE;
		double range;
		for(int r = 0; r < gridHeights.length; r++)
			for(int c = 0; c< gridHeights[0].length; c++){
				if(gridHeights[r][c] > greatestHeight ){
					greatestHeight = gridHeights[r][c];
				}
			}
		
		for(int r = 0; r < gridHeights.length; r++)
			for(int c = 0; c< gridHeights[0].length; c++){
				if(gridHeights[r][c] < lowestHeight ){
					lowestHeight = gridHeights[r][c];
				}
			}
		
	System.out.println(lowestHeight);
	System.out.println(greatestHeight);
	//range = (Math.abs(greatestHeight)) - (Math.abs(lowestHeight));
	range = greatestHeight - lowestHeight;
	System.out.println(range);
	double subDividedRange = range/255;
	
	
	

	Color color;
	for(int r = 0; r < gridHeights.length; r++)
		for(int c = 0; c< gridHeights[0].length; c++){
			int normalizedColorValue = (int) ((int) ((gridHeights[r][c]+Math.abs(lowestHeight))*255/range));
			grid.plot(r, c, color = new Color(255-normalizedColorValue,255-normalizedColorValue,255-normalizedColorValue));
		}
	}
	
	public void displayPoints(){
		for(int i = 0; i < points.length; i ++){
			grid.plot((int)points[i].getX(), (int)points[i].getY(), Color.BLACK);
		}
	}
	
	
	
}
	/*

*/

