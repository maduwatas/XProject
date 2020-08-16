package es.xproject.workout.base;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ColorFactory {

	private static ColorFactory instance;

	private ColorFactory() {

	}

	public static ColorFactory getInstance() {
		if (instance == null)
			instance = new ColorFactory();

		return instance;
	}

	public Color getColor(int color) {
		Color result = null;
		Display d = Display.getDefault(); 

		switch (color) {
		case Z1:
			result = new Color(d, 0, 188, 212);
			break;
		case Z2:
			result = new Color(d, 205, 220, 57);
			break;
		case Z3:
			result = new Color(d, 255, 177, 0);
			break;
		case Z4:
			result = new Color(d, 253, 132, 3);
			break;
		case Z5:
			result = new Color(d, 253, 87, 3);
			break;
		case Z6:
			result = new Color(d, 252, 64, 14);
			break;
		case Z7:
			result = new Color(d, 250, 10, 10);
			break;
		case Z8:
			result = new Color(d, 178, 5, 5);
			break;
		default:
			d.getSystemColor(color);
		}

		return result;
	}

	public static final int ZR = -1;
	public static final int Z0 = 0;
	public static final int Z1 = 1;
	public static final int Z2 = 2;
	public static final int Z3 = 3;
	public static final int Z4 = 4;
	public static final int Z5 = 5;
	public static final int Z6 = 6;
	public static final int Z7 = 7;
	public static final int Z8 = 8;

	/*
	 * <color name="colorPrimary">#008577</color> <color
	 * name="colorPrimaryDark">#00574B</color> <color
	 * name="colorAccent">#D81B60</color> <color
	 * name="colorWorkout">#B9ECA618</color> <color
	 * name="colorWhite">#FFFFFF</color> <color name="colorSilver">#EEEBEB</color>
	 * <color name="colorRecovery">#00BCD4</color> <color
	 * 
	 * 
	 * name="colorEndurance">#CDDC39</color> <color
	 * name="colorTempo">#DCC139</color> <color
	 * name="colorSweetSpot">#FFC107</color> <color
	 * name="colorThresdhold">#FF9800</color> <color
	 * name="colorWOmax">#DDEB8038</color> <color
	 * name="colorAnaerobic">#F44336</color> <color
	 * name="colorNeuromuscular">#D3FF0000</color> <color
	 * name="timer_circle">#2ebfca</color>
	 */
}
