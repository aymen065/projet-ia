package ia.principal;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Depth {

	private Cell[][] grid;
    ArrayDeque<Cell> stack;

	   int       height;
	   int       width;
	   ArrayDeque<Cell> path;
		private int startI, startJ;
		private int endI, endJ;
	public Depth(int width, int height, int si, int sj, int ei, int ej, int[][] blocks) {
		super();
		grid = new Cell[width][height];
	    this.path   = new ArrayDeque<Cell>();
		startCell(si, sj);
		endCell(ei, ej);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(i, j);
				grid[i][j].Heuristique = Math.abs(i - endI) + Math.abs(j - endJ);
				grid[i][j].solution = false;
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(i, j);
				grid[i][j].solution = false;
			}
		}
		for (int i = 0; i < blocks.length; i++) {
			addBlockOnCell(blocks[i][0], blocks[i][1]);

		}
	}
	public void startCell(int i, int j) {
		startI = i;
		startJ = j;
	}

	public void endCell(int i, int j) {
		endI = i;
		endJ = j;
	}
	public void addBlockOnCell(int i, int j) {
		grid[i][j] = null;
	}
	 public boolean depthFirstSearch() {
		    Cell cell  = grid[startI][startJ];
		    stack = new ArrayDeque<Cell>();
		    stack.push(cell);
		    while (!stack.isEmpty()) {
		      cell = stack.pop();
		        
		      if (!cell.equals(grid[endI][endJ])) {
		    	  path.push(cell);
		        for (Cell adjCell : cell.adjCells) {
		        	if(adjCell != null)
		        	{
			    	//System.out.println(adjCell.toString());
		          if (!adjCell.visited) {
		            adjCell.visited = true;
		            stack.push(adjCell);
		          }
		          }
		        }
		      } else {
		        break;
		      }
		    }
		    return cell.equals(grid[endI][endJ]);
		  }
	 
	 public void addAdjCell(List<Cell> adjList, Cell cell) {
		      adjList.add(cell);
		    
		  }
	 
	 public void setAdjacentCells() {

		    for (int i = 0; i < grid.length; i++) {
		      for (int j = 0; j < grid[i].length; j++) {
		    	  //System.out.println("i" + i + "  j  "+j  );
		        /*if (i - 1 > -1)
		          addAdjCell(grid[i][j].adjCells, grid[i - 1][j]);
		        if (j - 1 > -1)
		          addAdjCell(grid[i][j].adjCells, grid[i][j - 1]);
		        if (i + 1 < height)
		          addAdjCell(grid[i][j].adjCells, grid[i + 1][j]);
		        if (j + 1 < width)
		          addAdjCell(grid[i][j].adjCells, grid[i][j + 1]);*/
		    	  
		    if(grid[i][j] == null) {
		    	continue;
		    }
		      else{
		    	  if(i - 1 >= 0) {
						
		    		  addAdjCell(grid[i][j].adjCells, grid[i - 1][j]);
		    		  
						if(j - 1 >=0) {
							addAdjCell(grid[i][j].adjCells, grid[i - 1][j - 1]);
							
						}
						if(j + 1 < grid[0].length) {
							addAdjCell(grid[i][j].adjCells, grid[i - 1][j + 1]);
							
						}
					}
					if(j - 1 >= 0) {
						addAdjCell(grid[i][j].adjCells, grid[i][j - 1]);
						
					}
					if(j + 1 < grid[0].length) {
						addAdjCell(grid[i][j].adjCells, grid[i][j + 1]);
					}
					
					if(i + 1 < grid.length) {
						addAdjCell(grid[i][j].adjCells, grid[i + 1][j]);
						
						if(j - 1 >=0) {
							addAdjCell(grid[i][j].adjCells, grid[i + 1][j - 1]);
							
						}
						if(j + 1 < grid[0].length) {
							addAdjCell(grid[i][j].adjCells, grid[i + 1][j + 1]);
							
						}
					}
		    	  }
		      }
		    }
		  }
	 
	 public void display() {
			System.out.println("Grid : ");
			for(int i= 0 ; i< grid.length; i++) {
				for(int j = 0 ; j<grid[i].length ; j++) {
					if(i == startI && j == startJ)
						System.out.print("SO  ");
					else if (i == endI && j == endJ)
						System.out.print("DE  ");
					else if (grid[i][j] != null)
					System.out.printf("%-3d ", 0);
					else 
						System.out.print("BL  ");
				}
				System.out.println("");
			}
			System.out.println();

		}
	 public void displaySolution() {
		 
			if(depthFirstSearch() == true) {
				System.out.println("Path : ");
				Cell current = grid[endI][endJ];
				System.out.println(current);
				grid[current.i][current.j].solution = true;
				
				while(path.isEmpty() == false ) {
					Cell cellPop = path.pop();
					System.out.println("-> "+ cellPop);
					grid[cellPop.i][cellPop.j].solution = true;
					//current = current.parent;
				}
				System.out.println("\n");
				for(int i= 0 ; i< grid.length; i++) {
					for(int j = 0 ; j<grid[i].length ; j++) {
						if(i == startI && j == startJ)
							System.out.print("SO  ");
						else if (i == endI && j == endJ)
							System.out.print("DE  ");
						else if (grid[i][j] != null) {
							System.out.printf("%-3s ", grid[i][j].solution ? "X" : "0");
							}
							
						else 
							System.out.print("BL  ");
					}
					System.out.println();
				}
				System.out.println();
			}
			else
				System.out.println("No possible path");
		}
}
