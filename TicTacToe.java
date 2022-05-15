import java.util.Scanner;

public class TicTacToe {

	private char[][] board;
	private byte     moves;

	public TicTacToe() {
		this.board = new char[ 3 ][ 3 ];

		// Top Row
		this.board[ 0 ][ 0 ] = ' ';
		this.board[ 0 ][ 1 ] = ' ';
		this.board[ 0 ][ 2 ] = ' ';

		// Middle Row
		this.board[ 1 ][ 0 ] = ' ';
		this.board[ 1 ][ 1 ] = ' ';
		this.board[ 1 ][ 2 ] = ' ';

		// Bottom Row
		this.board[ 2 ][ 0 ] = ' ';
		this.board[ 2 ][ 1 ] = ' ';
		this.board[ 2 ][ 2 ] = ' ';
	}


	private void printBoard() {
		System.out.printf(

			"  -———————————-%n" +
			"  | %c | %c | %c |%n" +
			"  |———+———+———|%n" +
			"  | %c | %c | %c |%n" +
			"  |———+———+———|%n" +
			"  | %c | %c | %c |%n" +
			"  -———————————-%n",

			this.board[ 0 ][ 0 ],
			this.board[ 0 ][ 1 ],
			this.board[ 0 ][ 2 ],

			this.board[ 1 ][ 0 ],
			this.board[ 1 ][ 1 ],
			this.board[ 1 ][ 2 ],

			this.board[ 2 ][ 0 ],
			this.board[ 2 ][ 1 ],
			this.board[ 2 ][ 2 ]
		);
	}


	private byte random() {
		return (byte)((Math.random() * 9) + 1);
	}


	private byte cpu() {
		byte position = this.random();

		while (!(this.isTileAvailable(position)))
			position = this.random();

		return position;
	}


	private boolean isTileAvailable(byte position) {
		switch (position) {
		case 1:
			return this.board[ 0 ][ 0 ] == ' ';

		case 2:
			return this.board[ 0 ][ 1 ] == ' ';

		case 3:
			return this.board[ 0 ][ 2 ] == ' ';

		case 4:
			return this.board[ 1 ][ 0 ] == ' ';

		case 5:
			return this.board[ 1 ][ 1 ] == ' ';

		case 6:
			return this.board[ 1 ][ 2 ] == ' ';

		case 7:
			return this.board[ 2 ][ 0 ] == ' ';

		case 8:
			return this.board[ 2 ][ 1 ] == ' ';

		case 9:
			return this.board[ 2 ][ 2 ] == ' ';
		}
		return false;
	}


	private void place(byte position, boolean player) {
		char piece = (player) ? 'x' : 'o';

		switch (position) {
		case 1:
			this.board[ 0 ][ 0 ] = piece;

			break;

		case 2:
			this.board[ 0 ][ 1 ] = piece;

			break;

		case 3:
			this.board[ 0 ][ 2 ] = piece;

			break;

		case 4:
			this.board[ 1 ][ 0 ] = piece;

			break;

		case 5:
			this.board[ 1 ][ 1 ] = piece;

			break;

		case 6:
			this.board[ 1 ][ 2 ] = piece;

			break;

		case 7:
			this.board[ 2 ][ 0 ] = piece;

			break;

		case 8:
			this.board[ 2 ][ 1 ] = piece;

			break;

		case 9:
			this.board[ 2 ][ 2 ] = piece;
		}
	}


	private boolean hasWinner(boolean player) {
		char piece  = (player) ? 'x' : 'o';

		if (
			this.board[ 0 ][ 0 ] == piece &&
			this.board[ 0 ][ 1 ] == piece &&
			this.board[ 0 ][ 2 ] == piece
		)
			return true;

		if (
			this.board[ 1 ][ 0 ] == piece &&
			this.board[ 1 ][ 1 ] == piece &&
			this.board[ 1 ][ 2 ] == piece
		)
			return true;

		if (
			this.board[ 2 ][ 0 ] == piece &&
			this.board[ 2 ][ 1 ] == piece &&
			this.board[ 2 ][ 2 ] == piece
		)
			return true;

		if (
			this.board[ 0 ][ 0 ] == piece &&
			this.board[ 1 ][ 0 ] == piece &&
			this.board[ 2 ][ 0 ] == piece
		)
			return true;

		if (
			this.board[ 0 ][ 1 ] == piece &&
			this.board[ 1 ][ 1 ] == piece &&
			this.board[ 2 ][ 1 ] == piece
		)
			return true;

		if (
			this.board[ 0 ][ 2 ] == piece &&
			this.board[ 1 ][ 2 ] == piece &&
			this.board[ 2 ][ 2 ] == piece
		)
			return true;

		if (
			this.board[ 0 ][ 0 ] == piece &&
			this.board[ 1 ][ 1 ] == piece &&
			this.board[ 2 ][ 2 ] == piece
		)
			return true;

		if (
			this.board[ 0 ][ 2 ] == piece &&
			this.board[ 1 ][ 1 ] == piece &&
			this.board[ 2 ][ 0 ] == piece
		)
			return true;

		return false;
	}


	public void play() {
		do {
			this.printBoard();

			System.out.print("  P1: ");

			var p1Move = new Scanner(System.in).nextByte();

			System.out.print("\n");

			if (this.isTileAvailable(p1Move)) {
				this.place(p1Move, /*player:*/true);
				this.moves++;

			} else {
				if (p1Move > 9 || p1Move < 1)
					System.out.println("\n  Tile " + p1Move + " is undefined.");

				else
					System.out.println("\n  Tile " + p1Move + " is occupied.");

				play();
				break;
			}

			if (this.hasWinner(/*player:*/true)) {
				System.out.println("\n  Player Wins!");
				break;
			}

			if (this.moves != 9) {
				this.place(this.cpu(), /*player:*/false);
				this.moves++;

			} else {
				System.out.println("\n  It's a Tie!");
				break;
			}

			if (this.hasWinner(/*player:*/false)) {
				System.out.println("\n  CPU Wins!");
				break;
			}

		} while (this.moves < 9);
	}


	public static void main(String[] args) {
		System.out.println("  Jhunrel Evangelista\n");
		while (true) {
			System.out.println("   Tic Tac Toe");
			TicTacToe game = new TicTacToe();
			game.play();
			game.printBoard();
			System.out.println("\n\n   ===========");
		}
	}
}
