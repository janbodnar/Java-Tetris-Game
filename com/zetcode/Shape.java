package com.zetcode;

import java.util.Random;

/**
 * 
 * @author sergioMB Activitat EDD IES El Just
 * 
 */

public class Shape {

	protected enum Tetrominoe {
		NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape
	}

	private Tetrominoe pieceShape;
	private int coords[][];
	private int[][][] coordsTable;

	public Shape() {

		initShape();
	}

	private void initShape() {

		coords = new int[4][2];

		coordsTable = new int[][][] { { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } },
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } };

		setShape(Tetrominoe.NoShape);
	}

	/**
	 * Estableix la forma de la peça tetromino i actualitza les coordenades
	 * corresponents.
	 * 
	 * 
	 * @param shape La forma de la peça tetrominoe a establir
	 */
	protected void setShape(Tetrominoe shape) {

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 2; ++j) {

				coords[i][j] = coordsTable[shape.ordinal()][i][j];
			}
		}

		pieceShape = shape;
	}

	/**
	 * Estableix la coordenada x de un cuadrat de la peça tetrominoe
	 * 
	 * @param index Índex del quadrat la coordenada x del qual s'establirà.
	 * @param x     Valor de la coordenada x a establir.
	 */
	private void setX(int index, int x) {
		coords[index][0] = x;
	}

	/**
	 * Estableix la coordenada y de un quadrat de la peça tetrominoe
	 * 
	 * @param index Índex del quadrat la coordenada y del qual s'establirà.
	 * @param y     Valor de la coordenada y a establir.
	 */
	private void setY(int index, int y) {
		coords[index][1] = y;
	}

	/**
	 * Obté el valor de la coordenada x de n quadrat dela peça tetrominoe
	 * 
	 * @param index Índex del quadrat on s'obtindrà la coordenada x.
	 * @return El valor de la coordenada x del quadrat especificat.
	 */
	public int x(int index) {
		return coords[index][0];
	}

	/**
	 * Obté el valor de la coordenada y d'un quadrat de la peça tetromino.
	 * 
	 * @param Índex del quadrat la coordenada y del qual s'obtindrà.
	 * @return El valor de la coordenada y del quadrat especificat.
	 */
	public int y(int index) {
		return coords[index][1];
	}

	/**
	 * Obté la forma actual de l apeça tetrominoe
	 * 
	 * @return La forma actual de la peça tetrominoe.
	 */
	public Tetrominoe getShape() {
		return pieceShape;
	}

	/**
	 * Estableix una forma aleatòria per a la peça tetrominoe.
	 */
	public void setRandomShape() {

		var r = new Random();
		int x = Math.abs(r.nextInt()) % 7 + 1;

		Tetrominoe[] values = Tetrominoe.values();
		setShape(values[x]);
	}

	/**
	 * Obté la coordenada x més xicoteta de la peça tetrominoe
	 * 
	 * @return La coordenada x del quadrat més a l'esquerra de la peça tetrominoe.
	 * 
	 */

	public int minX() {

		int m = coords[0][0];

		for (int i = 0; i < 4; i++) {

			m = Math.min(m, coords[i][0]);
		}

		return m;
	}

	/**
	 * Obté la coordenada y més xicoteta de la peça tetrominoe.
	 * 
	 * @return La coordenada y del quadrat més a l'esquerra de la peça tetrominoe.
	 * 
	 */

	public int minY() {

		int m = coords[0][1];

		for (int i = 0; i < 4; i++) {

			m = Math.min(m, coords[i][1]);
		}

		return m;
	}

	/**
	 * Gira la peça Tetrominoe cap a l'esquerra. Si la peça és de tipus SquareShape,
	 * retorna la mateixa peça.
	 * 
	 * @return una nova instància de la classe Shape que representa la peça girada.
	 */
	public Shape rotateLeft() {

		if (pieceShape == Tetrominoe.SquareShape) {

			return this;
		}

		var result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, y(i));
			result.setY(i, -x(i));
		}

		return result;
	}

	/**  
	 *  Gira la peça Tetrominoe cap a l'esquerra. Si la peça és de tipus SquareShape,
	 * retorna la mateixa peça.
	 * 
	 * @return una nova instància de la classe Shape que representa la peça girada.
	 */

	public Shape rotateRight() {

		if (pieceShape == Tetrominoe.SquareShape) {

			return this;
		}

		var result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, -y(i));
			result.setY(i, x(i));
		}

		return result;
	}
}
