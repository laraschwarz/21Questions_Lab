import java.io.Serializable;

public class Game implements Serializable {
	public int[][] table;
	private int turn;

	public Game() {
		table = new int[3][3];
		turn = 1;
	}

	public int getTurn(){
		return turn;
	}

	public void printTable() {
		for (int r = 0; r < table.length; r++) {
			for (int c = 0; c < table[r].length; c++) {
				if (table[r][c] == 0) {
					System.out.print("");
				} else if (table[r][c] == 1) {
					System.out.print("X");
				} else if (table[r][c] == 2) {
					System.out.print("O");
				}
			}
			System.out.println("\t");
		}
	}

	public int[][] getGame(){
		return table;
	}

	public void insertXO(int a, int b) {
		if (table[a][b] == 0) {
			if (turn == 1) {
				table[a][b] = 1;
				turn = 2;
			} else if (turn == 2) {
				table[a][b] = 2;
				turn = 1;
			}
		}

	}

	public boolean checkFull() {
		boolean full = true;
		for (int r = 0; r < table.length; r++) {
			for (int c = 0; c < table[r].length; c++) {
				if (table[r][c] == 0) {
					full = false;
				}
			}
		}
		return full;
	}

	public int checkTicTacToe() {
		int winner = 0;
		// winner = 1 rows
		if ((table[0][0] == 1) && (table[0][1] == 1) && (table[0][2] == 1)) {
			winner = 1;
		} else if ((table[1][0] == 1) && (table[1][1] == 1) && (table[1][2] == 1)) {
			winner = 1;
		} else if ((table[2][0] == 1) && (table[2][1] == 1) && (table[2][2] == 1)) {
			winner = 1;
		}
		// winner = 1 collumns
		else if ((table[0][0] == 1) && (table[1][0] == 1) && (table[2][0] == 1)) {
			winner = 1;
		} else if ((table[0][1] == 1) && (table[1][1] == 1) && (table[2][1] == 1)) {
			winner = 1;
		} else if ((table[0][2] == 1) && (table[1][2] == 1) && (table[2][2] == 1)) {
			winner = 1;
		}
		// winner = 1 diagonals
		else if ((table[0][0] == 1) && (table[1][1] == 1) && (table[2][2] == 1)) {
			winner = 1;
		} else if ((table[2][0] == 1) && (table[1][1] == 1) && (table[0][2] == 1)) {
			winner = 1;
		}
		// winner = 2 rows
		if ((table[0][0] == 2) && (table[0][1] == 2) && (table[0][2] == 2)) {
			winner = 2;
		} else if ((table[1][0] == 2) && (table[1][1] == 2) && (table[1][2] == 2)) {
			winner = 2;
		} else if ((table[2][0] == 2) && (table[2][1] == 2) && (table[2][2] == 2)) {
			winner = 2;
		}
		// winner = 2 collumns
		else if ((table[0][0] == 2) && (table[1][0] == 2) && (table[2][0] == 2)) {
			winner = 2;
		} else if ((table[0][1] == 2) && (table[1][1] == 2) && (table[2][1] == 2)) {
			winner = 2;
		} else if ((table[0][2] == 2) && (table[1][2] == 2) && (table[2][2] == 2)) {
			winner = 2;
		}
		// winner = 2 diagonals
		else if ((table[0][0] == 2) && (table[1][1] == 2) && (table[2][2] == 2)) {
			winner = 2;
		} else if ((table[2][0] == 2) & (table[1][1] == 2) && (table[0][2] == 2)) {
			winner = 2;
		}

		return winner;
	}

	/*
	 * public void drawMe(Graphics g){
	 * 
	 * 
	 * g.setColor(Color.white);
	 * g.fillRect(0, 0, 800, 600);
	 * 
	 * g.setColor(Color.black);
	 * g.drawRect(100, 100, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(200, 100, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(300, 100, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(100, 200, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(200, 200, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(300, 200, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(100, 300, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(200, 300, 100, 100);
	 * g.setColor(Color.black);
	 * g.drawRect(300, 300, 100, 100);
	 * 
	 * }
	 */
}