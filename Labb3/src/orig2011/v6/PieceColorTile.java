package orig2011.v6;

import java.awt.Color;

/**
 * Tiles for the Reversi pieces.
 * 
 * @author Anton Myrholm
 * @author Pontus Malm
 * 
 */


public class PieceColorTile extends RoundTile {

	/**
	 * @param strokeColor
	 * @param fillColor
	 * @param thickness
	 * @param scale
	 */
	public PieceColorTile(Color strokeColor, Color fillColor, double thickness,
			double scale) {
		super(strokeColor, fillColor, thickness, scale);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param strokeColor
	 * @param fillColor
	 * @param thickness
	 */
	public PieceColorTile(Color strokeColor, Color fillColor, double thickness) {
		super(strokeColor, fillColor, thickness);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param strokeColor
	 * @param fillColor
	 */
	public PieceColorTile(Color strokeColor, Color fillColor) {
		super(strokeColor, fillColor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fillColor
	 */
	public PieceColorTile(Color fillColor) {
		super(fillColor);
		// TODO Auto-generated constructor stub
	}

	public enum PieceColor {
		BLACK, WHITE, EMPTY;

		public static PieceColor opposite(final PieceColor t) {
			return t == BLACK ? WHITE : BLACK;
		}
	}

	private static PieceColor color;

	public static PieceColor getColor() {
		return color;
	}

	public static void setColor(PieceColor color) {
		PieceColorTile.color = color;
	}

}
