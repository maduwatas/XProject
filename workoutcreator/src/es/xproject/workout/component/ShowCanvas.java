package es.xproject.workout.component;

//Send questions, comments, bug reports, etc. to the authors:

//Rob Warner (rwarner@interspatial.com)
//Robert Harris (rbrt_harris@yahoo.com)

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import es.xproject.workout.base.Constants;

/**
 * This class demonstrates a Canvas
 */
public class ShowCanvas {
	/**
	 * Runs the application
	 */
	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Canvas Example");
		createContents(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param shell
	 *            the main window
	 */
	private void createContents(Shell shell) {
		shell.setLayout(new FillLayout());

		// Create a canvas
		Canvas canvas = new Canvas(shell, SWT.NONE);

		// Create a button on the canvas
		Button button = new Button(canvas, SWT.PUSH);
		button.setBounds(10, 10, 300, 40);
		button.setText("You can place widgets on a canvas");

		// Create a paint handler for the canvas
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// Do some drawing
				Rectangle rect = ((Canvas) e.widget).getBounds();
				e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_RED));
				e.gc.drawFocus(5, 5, rect.width - 10, rect.height - 10);
				e.gc.drawText("You can draw text directly on a canvas", 60, 60);

				Rectangle clientArea = shell.getClientArea();
				int width = clientArea.width;
				int height = clientArea.height;
				e.gc.setClipping(20, 20, width - 40, height - 40);
				e.gc.setBackground(selectColorByInterval(30));
				e.gc.fillPolygon(new int[] { 0, 0, width, 0, width / 2, height });

				e.gc.setBackground(selectColorByInterval(90));
				e.gc.fillPolygon(new int[] { 0, 0, width, 0, width / 4, height });
			}
		});
	}

	public static Color selectColorByInterval(double percent) {

		int result = SWT.COLOR_BLUE;
		if (percent < Constants.LEVEL_RECOVERY)
			result = SWT.COLOR_BLUE;
		else if (percent < Constants.LEVEL_ENDURANCE)
			result = SWT.COLOR_GREEN;
		else if (percent < Constants.LEVEL_TEMPO)
			result = SWT.COLOR_YELLOW;
		else if (percent < Constants.LEVEL_SWEETSPOT)
			result = SWT.COLOR_DARK_YELLOW;
		else if (percent < Constants.LEVEL_THRESHOLD)
			result = SWT.COLOR_RED;
		else if (percent < Constants.LEVEL_VOMAX)
			result = SWT.COLOR_DARK_RED;
		else if (percent < Constants.LEVEL_ANAEROBIC)
			result = SWT.COLOR_DARK_MAGENTA;
		else if (percent >= Constants.LEVEL_ANAEROBIC)
			result = SWT.COLOR_BLACK;

		return Display.getDefault().getSystemColor(result);

	}

	/**
	 * The application entry point
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		new ShowCanvas().run();

	}
}
