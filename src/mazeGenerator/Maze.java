package mazeGenerator;

import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

public class Maze {

	
	int sizeX;
	int sizeY;
	Cell [][] cells;
	
	public Maze(){
		sizeX = 25;
		sizeY = 25;
		cells = new Cell [sizeX][sizeY];
		initializeCells ();
		generateMaze();
		
	}
	
	public Maze (int x, int y){
		sizeX = x;
		sizeY = y;
		cells = new Cell [sizeX][sizeY];
		initializeCells();
		generateMaze();
				
	}
	
	public void printAllCells(){
		for (int i=0; i< sizeX; i++){
			for (int j=0; j<sizeY; j++){
				System.out.println(i + " " +j);
				cells [i][j].printCell();
				System.out.println("\n");
				
				
			}
		}
		
	}
	// Exit=1, Start=2, Walls=3
	// Set borders, initialize  cells in array
	private void  initializeCells(){
		for ( int i =0; i<sizeX; i++ ){
			for (int j=0; j<sizeY; j++) {
			cells [i][j] = new Cell();
			cells[i][j].x=i;
			cells[i][j].y=j;
			if (i==0){
				cells[i][j].borders[0] = 1;
				
			}
			if (j==0){
				cells[i][j].borders[3] =1;
			}
			if (i==sizeX-1){
				cells[i][j].borders[2] =1;
				
			} 
			if (j==sizeY-1){
				cells[i][j].borders[1] =1;
				
			}
			
			}
		}
		
	}
	// Generate maze
	/*create a CellStack (LIFO) to hold a list of cell locations  
set TotalCells = number of cells in grid  
choose a cell at random and call it CurrentCell  
set VisitedCells = 1  

while VisitedCells < TotalCells 
find all neighbors of CurrentCell with all walls intact   
if one or more found 
choose one at random  
knock down the wall between it and CurrentCell  
push CurrentCell location on the CellStack  
make the new cell CurrentCell  
add 1 to VisitedCells
else 
pop the most recent cell entry off the CellStack  
make it CurrentCell
endIf
endWhile 

*/
	private void generateMaze (){
		Random rand = new Random ();
		
		int x =rand.nextInt(sizeX); // Random starting location
		int y = rand.nextInt(sizeY);
		
		Stack<Cell> cellStack = new Stack<Cell>(); // Stack for storing cells
		
		int totalCells = sizeX * sizeY;     // Number of cells
		int visitedCells = 1;        // counter to check that all cells visited
		Cell currentCell = cells[x][y]; // Variable for holding  the current cell
		
		ArrayList<Vertex> neighborCellList = new ArrayList <Vertex>();
		Vertex tmpV = new Vertex();
		while (visitedCells < totalCells){
			neighborCellList.clear(); // clear List
			
			tmpV = new Vertex(); // Clear  Vertex Variable
			if (y-1>= 0 && cells[x][y-1].checkWalls()==true){
				tmpV.x1 = x;
				tmpV.y1 = y;
				tmpV.x2= x;
				tmpV.y2 =y-1;
				tmpV.wall1=0;
				tmpV.wall2=2;
				neighborCellList.add(tmpV); //Add neighbor to List
			}
			
			tmpV = new Vertex ();
			if(y+1< sizeY &&cells[x][y+1].checkWalls()==true){
				tmpV.x1 = x;
				tmpV.y1 = y;
				tmpV.x2= x;
				tmpV.y2 =y+1;
				tmpV.wall1=2;
				tmpV.wall2=0;
				neighborCellList.add(tmpV);
			}
			tmpV = new Vertex ();
			if(x-1>= 0 && cells[x-1][y].checkWalls()==true){
				tmpV.x1 = x;
				tmpV.y1 = y;
				tmpV.x2= x-1;
				tmpV.y2 =y;
				tmpV.wall1=3;
				tmpV.wall2=1;
				neighborCellList.add(tmpV);
			}
			
			tmpV = new Vertex ();
			if(x+1< sizeX &&cells[x+1][y].checkWalls()==true){
				tmpV.x1 = x;
				tmpV.y1 = y;
				tmpV.x2= x+1;
				tmpV.y2 =y;
				tmpV.wall1=1;
				tmpV.wall2=3;
				neighborCellList.add(tmpV);
			}
			// if there are unvisited neighboring cells
			if (neighborCellList.size()>=1){
				int r1 =rand.nextInt(neighborCellList.size()); // Choose neighbor from the list
				tmpV = neighborCellList.get(r1);
				
				// Use Info saved in neighbor list
				cells[tmpV.x1][tmpV.y1].walls[tmpV.wall1] = 0;
				cells[tmpV.x2][tmpV.y2].walls[tmpV.wall2] =0;
				
				//Push current cell in stack so it can be revisited
				cellStack.push(currentCell);
				
				//Make the new cell to current cell
				currentCell = cells [tmpV.x2][tmpV.y2];
				
				//Update
				x=currentCell.x;
				y=currentCell.y;
				
				//Increment counter
				visitedCells++;
				
				// Else get the cell from stack and use  as current
			} else{
				currentCell = cellStack.pop();
				x=currentCell.x;
				y= currentCell.y;
				
			}
		}
		
		//Set the entrance and exit of the maze 
		//For the moment it is set as top left and bottom right
		cells [0][0].walls[3] =0;
		cells[sizeX-1][sizeY-1].walls[1] = 0; 
	}
}
