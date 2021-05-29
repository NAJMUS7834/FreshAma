package com.getwayssolution.www;

import java.util.*;

public class Test
{
	static int ROW = 3;
	static int COL = 3;

	// matrix cell coordinates
	static class Point
	{
		int x;
		int y;

		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	};

	// A Data Structure for queue used in BFS
	static class queueNode
	{
		Point pt; // The coordinates of a cell
		int dist; // cell's distance of from the source

		public queueNode(Point pt, int dist)
		{
			this.pt = pt;
			this.dist = dist;
		}
	};

	static boolean isValid(int row, int col)
	{
		// return true if row number and
		// column number is in range
		return (row >= 0) && (row < ROW) &&
				(col >= 0) && (col < COL);
	}

	// numbers of 4 neighbors of a given cell
	static int rowNum[] = {-1, 0, 0, 1};
	static int colNum[] = {0, -1, 1, 0};

	// function to find the shortest path between a given source cell to a destination cell.
	static int BFS(int mat[][], Point src,Point dest)
	{
		//o means obstacle
		if (mat[src.x][src.y] == 0 || mat[dest.x][dest.y] == 0 )
			return -1;

		boolean [][]visited = new boolean[ROW][COL];

		// Mark the source cell as visited
		visited[src.x][src.y] = true;

		// Create a queue for BFS
		Queue<queueNode> q = new LinkedList<>();

		// Distance of source cell is 0
		queueNode s = new queueNode(src, 0);
		q.add(s); // Enqueue source cell

		// Do a BFS starting from source cell
		while (!q.isEmpty())
		{
			queueNode curr = q.peek();
			Point pt = curr.pt;

			// If we have reached the destination cell
			if (pt.x == dest.x && pt.y == dest.y)
				return curr.dist;

			// Otherwise dequeue the front cell
			q.remove();

			for (int i = 0; i < 4; i++)
			{
				int row = pt.x + rowNum[i];
				int col = pt.y + colNum[i];

				// if adjacent cell is valid, has path
				// and not visited yet, enqueue it.
				if (isValid(row, col) && (mat[row][col] == 1 || mat[row][col] == 9)   && !visited[row][col])
				{
					// mark cell as visited and enqueue it
					visited[row][col] = true;
					queueNode Adjcell = new queueNode(new Point(row, col),curr.dist + 1 );
					q.add(Adjcell);
				}
			}
		}

		// Return -1 if destination cannot be reached
		return -1;
	}

	public static void main(String[] args)
	{
		int mat[][] = {{ 1, 0, 0},
				{ 1, 0, 9 },
				{ 1, 1, 1 }};

		Point source = new Point(0, 0);
		Point dest = new Point(1, 2);

		int dist = BFS(mat, source, dest);

		if (dist != -1)
			System.out.println("Shortest Path is " + dist);
		else
			System.out.println("Shortest Path doesn't exist");
	}
}

