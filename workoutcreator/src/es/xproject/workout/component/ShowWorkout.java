package es.xproject.workout.component;

//Send questions, comments, bug reports, etc. to the authors:

//Rob Warner (rwarner@interspatial.com)
//Robert Harris (rbrt_harris@yahoo.com)

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import es.xproject.workout.base.Constants;
import es.xproject.workout.handler.WorkoutHandler;

/**
 * This class demonstrates a Canvas
 */
public class ShowWorkout {
	/**
	 * Runs the application
	 */

	private String file;
	private int ftp;
	private Display d;
	private Shell s;
	private Canvas canvas;

	public ShowWorkout(String file, int ftp) {
		this.ftp = ftp;
		this.file = file;
	}

	public void run() {
		d = new Display();

		s = new Shell(d);
		s.setText("Visualización de workouts");

		s.setLayout(new FillLayout());

		// createContents();
		createContents();
		createMenu();

		s.open();

		while (!s.isDisposed()) {
			if (!d.readAndDispatch()) {
				d.sleep();
			}
		}
		d.dispose();
	}

	private void createMenu() {

		Menu m = new Menu(s, SWT.BAR);
		// create a file menu and add an exit item
		final MenuItem fileItem = new MenuItem(m, SWT.CASCADE);
		fileItem.setText("&File");
		final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
		fileItem.setMenu(filemenu);
		final MenuItem openItem = new MenuItem(filemenu, SWT.PUSH);
		openItem.setText("&Open\tCTRL+O");
		openItem.setAccelerator(SWT.CTRL + 'O');
		final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
		final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
		exitItem.setText("E&xit");

		class Open implements SelectionListener {

			public void widgetSelected(SelectionEvent event) {
				FileDialog fd = new FileDialog(s, SWT.OPEN);
				fd.setText("Open");
				fd.setFilterPath(Constants.FILE_PATH + Constants.WORKOUTS_DIRECTORY);
				String[] filterExt = { "*.xml" };
				fd.setFilterExtensions(filterExt);
				fd.open();
				file = fd.getFileName();
				createContents();

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		}

		openItem.addSelectionListener(new Open());

		exitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(s, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				messageBox.setMessage("Do you really want to exit?");
				messageBox.setText("Exiting Application");
				int response = messageBox.open();
				if (response == SWT.YES)
					System.exit(0);
			}
		});
		s.setMenuBar(m);

	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param s
	 *            the main window
	 */
	private void createContents() {

		WorkoutHandler handler = new WorkoutHandler(file, ftp);
		handler.loadWorkout();
		if (canvas != null)
			canvas.dispose();
		canvas = new Canvas(s, SWT.NONE);
		canvas.addPaintListener(handler.getPaintListener(s));
		canvas.redraw();
		s.layout();

	}

	/**
	 * The application entry point
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		String file = null;
		String ftp = "240";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--file")) {
				file = args[++i];
			}
			if (args[i].equals("--ftp")) {
				ftp = args[++i];
			}
		}

		if (file != null)
			new ShowWorkout(file, Integer.valueOf(ftp)).run();

	}

}
