package ia.principal;

import java.util.PriorityQueue;

public class Aetoile {

	public static final int DIAGONAL_COST = 14;
	public static final int V_H_COST = 10;
	private Cell[][] grid;
	private PriorityQueue<Cell> openCells;
	private boolean[][] closedCells;
	private int startI, startJ;
	private int endI, endJ;

	public Aetoile(int width, int height, int si, int sj, int ei, int ej, int[][] blocks) {
		grid = new Cell[width][height];
		closedCells = new boolean[width][height];
		openCells = new PriorityQueue<Cell>((Cell c1, Cell c2) -> {
			return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
		});
		startCell(si, sj);
		endCell(ei, ej);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(i, j);
				grid[i][j].Heuristique = Math.abs(i - endI) + Math.abs(j - endJ);
				grid[i][j].solution = false;
			}
		}
		grid[startI][startJ].finalCost = 0;
		for (int i = 0; i < blocks.length; i++) {
			addBlockOnCell(blocks[i][0], blocks[i][1]);

		}

	}

	public void addBlockOnCell(int i, int j) {
		grid[i][j] = null;
	}

	public void startCell(int i, int j) {
		startI = i;
		startJ = j;
	}

	public void endCell(int i, int j) {
		endI = i;
		endJ = j;
	}
	
	public void updateCostIfNeeded(Cell current , Cell t , int cost) {
		if(t == null || closedCells[t.i][t.j])
			return;
		int tFinalCost = t.Heuristique + cost;
		boolean isOpen = openCells.contains(t);
		if(!isOpen || tFinalCost < t.finalCost) {
			t.finalCost = tFinalCost;
			t.parent = current;
			if(!isOpen) {
				openCells.add(t);
			}
		}
	}
	public void process() {
		openCells.add(grid[startI][startJ]);
		Cell current;
		while(true) {
			current = openCells.poll();
			if(current == null)
				break;
			closedCells[current.i][current.j] = true;
			if(current.equals(grid[endI][endJ]))
				return;
			Cell t;
			if(current.i - 1 >= 0) {
				t = grid[current.i - 1][current.j];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
				
				if(current.j - 1 >=0) {
					t = grid[current.i - 1][current.j - 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
					
				}
				if(current.j + 1 < grid[0].length) {
					t = grid[current.i - 1][current.j + 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
					
				}
			}
			if(current.j - 1 >= 0) {
				t = grid[current.i][current.j - 1];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
				
			}
			if(current.j + 1 < grid[0].length) {
				t = grid[current.i][current.j + 1];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
				
			}
			
			if(current.i + 1 < grid.length) {
				t = grid[current.i + 1][current.j];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
				
				if(current.j - 1 >=0) {
					t = grid[current.i + 1][current.j - 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
					
				}
				if(current.j + 1 < grid[0].length) {
					t = grid[current.i + 1][current.j + 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
					
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
	public void displayScores() {
		System.out.println("\n Scores for cells : ");
		for(int i= 0 ; i< grid.length; i++) {
			for(int j = 0 ; j<grid[i].length ; j++) {
				if(grid[i][j] != null)
					System.out.printf("%-3d ", grid[i][j].finalCost);
				else
					System.out.print("BL  ");
			}
			System.out.println();
			}
		System.out.println();
		}
	
public void displaySolution() {
	if(closedCells[endI][endJ]) {
		System.out.println("Path : ");
		Cell current = grid[endI][endJ];
		System.out.println(current);
		grid[current.i][current.j].solution = true;
		
		while(current.parent != null ) {
			System.out.println("-> "+ current.parent);
			grid[current.parent.i][current.parent.j].solution = true;
			current = current.parent;
		}
		System.out.println("\n");
		for(int i= 0 ; i< grid.length; i++) {
			for(int j = 0 ; j<grid[i].length ; j++) {
				if(i == startI && j == startJ)
					System.out.print("SO  ");
				else if (i == endI && j == endJ)
					System.out.print("DE  ");
				else if (grid[i][j] != null)
				System.out.printf("%-3s ", grid[i][j].solution ? "X" : "0");
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
