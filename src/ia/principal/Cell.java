package ia.principal;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	
	public int i,j;
	public Cell parent;
	public int Heuristique;
	public int finalCost;
	public boolean solution;
	public boolean visited;
	public List<Cell> adjCells;
	public Cell(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	    this.adjCells = new ArrayList<>();
	    this.visited  = false;


	}
	
	@Override
	public String toString() {
		return "["+i+", "+j+"]";
	}
	
	
}
