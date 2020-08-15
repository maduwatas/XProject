package es.xproject.workout.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import es.xproject.workout.base.ColorFactory;
import es.xproject.workout.base.Constants;
import es.xproject.workout.base.Step;
import es.xproject.workout.base.Workout;

public class WorkoutHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5420493893474744162L;

	private static final Logger log = Logger.getLogger(WorkoutHandler.class);

	protected Canvas canvas;
	protected Double dh;
	protected Double dw;
	protected Workout wo;
	protected ArrayList<Step> stepList;

	protected int totalLength;
	protected int totalIntervals;
	protected int ftp;
	protected String defaultFile;
	protected Float tss;

	protected float userTuneUp;

	private static final int STROKE_WIDTH = 2;

	public WorkoutHandler(String defaultFile, int ftp) {

		this.defaultFile = defaultFile;
		stepList = new ArrayList<Step>();
		totalLength = 0;
		totalIntervals = 0;
		this.ftp = ftp;
		tss = 0F;
		userTuneUp = 1f;

	}

	public void loadWorkout() {

		log.debug("loadWorkout " + defaultFile);
		XmlPullParserWorkoutHandler parser = new XmlPullParserWorkoutHandler();

		stepList = new ArrayList<Step>();

		try {

			File remoteFile = new File(Constants.FILE_PATH + Constants.WORKOUTS_DIRECTORY + defaultFile);

			InputStream is = null;

			if (remoteFile.exists()) {
				// open wo syncronized
				is = new FileInputStream(remoteFile);

				wo = parser.parse(is);

				if (wo != null) {
					for (Step step : wo.getWarnElement()) {
						writeStep(step);
					}
					for (Step step : wo.getMainElement()) {
						writeStep(step);
					}
					for (Step step : wo.getColdElement()) {
						writeStep(step);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void writeStep(Step step) {
		for (int i = 0; i < step.getRepeat(); i++) {

			totalLength += step.getTime();
			totalIntervals++;

			if (step.isEmpty()) {
				stepList.add(step);
				tss += (Float.valueOf(step.getTime()) * Float.valueOf(step.getPower())) / 3600F;
			} else {
				for (Step entry : step) {
					writeStep(entry);
				}
			}
			if (step.getRecoveryTime() > 0) {

				Step stepRec = new Step();
				stepRec.setTime(step.getRecoveryTime());
				stepRec.setPower(step.getRecoveryPower());

				stepList.add(stepRec);

				tss += (Float.valueOf(step.getRecoveryTime()) * Float.valueOf(step.getRecoveryPower())) / 3600F;

				totalLength += step.getRecoveryTime();
				totalIntervals++;
			}
		}

	}

	public static String getTextByInterval(float percent) {
		String result = "Z?";

		if (percent < Constants.LEVEL_RECOVERY)
			result = "Z1";
		else if (percent < Constants.LEVEL_ENDURANCE)
			result = "Z2";
		else if (percent < Constants.LEVEL_TEMPO)
			result = "Z3";
		else if (percent < Constants.LEVEL_SWEETSPOT)
			result = "Z4";
		else if (percent < Constants.LEVEL_THRESHOLD)
			result = "Z5";
		else if (percent < Constants.LEVEL_VOMAX)
			result = "Z6";
		else if (percent < Constants.LEVEL_ANAEROBIC)
			result = "Z7";
		else if (percent >= Constants.LEVEL_ANAEROBIC)
			result = "Z8";

		return result;
	}

	public static Color selectColorByInterval(double percent) {

		int result = Constants.Z1;
		if (percent < Constants.LEVEL_RECOVERY)
			result = Constants.Z1;
		else if (percent < Constants.LEVEL_ENDURANCE)
			result = Constants.Z2;
		else if (percent < Constants.LEVEL_TEMPO)
			result = Constants.Z3;
		else if (percent < Constants.LEVEL_SWEETSPOT)
			result = Constants.Z4;
		else if (percent < Constants.LEVEL_THRESHOLD)
			result = Constants.Z5;
		else if (percent < Constants.LEVEL_VOMAX)
			result = Constants.Z6;
		else if (percent < Constants.LEVEL_ANAEROBIC)
			result = Constants.Z7;
		else if (percent >= Constants.LEVEL_ANAEROBIC)
			result = Constants.Z8;

		return ColorFactory.getInstance().getColor(result);

	}

	public static Integer selectUpperPowerLimit(double percent) {

		if (percent < Constants.LEVEL_RECOVERY)
			return Constants.LEVEL_RECOVERY;
		else if (percent < Constants.LEVEL_ENDURANCE)
			return Constants.LEVEL_ENDURANCE;
		else if (percent < Constants.LEVEL_TEMPO)
			return Constants.LEVEL_TEMPO;
		else if (percent < Constants.LEVEL_SWEETSPOT)
			return Constants.LEVEL_SWEETSPOT;
		else if (percent < Constants.LEVEL_THRESHOLD)
			return Constants.LEVEL_THRESHOLD;
		else if (percent < Constants.LEVEL_VOMAX)
			return Constants.LEVEL_VOMAX;
		else if (percent < Constants.LEVEL_ANAEROBIC)
			return Constants.LEVEL_ANAEROBIC;
		else if (percent >= Constants.LEVEL_ANAEROBIC)
			return Constants.LEVEL_NEUROMUSCULAR;

		return 0;
	}

	public static Integer selectLowerPowerLimit(double percent) {

		if (percent >= Constants.LEVEL_NEUROMUSCULAR)
			return Constants.LEVEL_NEUROMUSCULAR;
		else if (percent >= Constants.LEVEL_ANAEROBIC)
			return Constants.LEVEL_ANAEROBIC;
		else if (percent >= Constants.LEVEL_VOMAX)
			return Constants.LEVEL_VOMAX;
		else if (percent >= Constants.LEVEL_THRESHOLD)
			return Constants.LEVEL_THRESHOLD;
		else if (percent >= Constants.LEVEL_SWEETSPOT)
			return Constants.LEVEL_SWEETSPOT;
		else if (percent >= Constants.LEVEL_TEMPO)
			return Constants.LEVEL_TEMPO;
		else if (percent >= Constants.LEVEL_ENDURANCE)
			return Constants.LEVEL_ENDURANCE;
		else if (percent >= Constants.LEVEL_RECOVERY)
			return Constants.LEVEL_RECOVERY;

		return Constants.LEVEL_COLDOWN;
	}

	public PaintListener getPaintListener(Shell shell) {
		log.debug("createWorkoutCanvas");
		dw = Double.valueOf(shell.getClientArea().width);
		dh = Double.valueOf(shell.getClientArea().height);

		Double hMeter = dw / totalLength;
		Double vMeter = dh / 200;

		return new PaintListener() {
			public void paintControl(PaintEvent e) {

				e.gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
				e.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
				Rectangle rect = new Rectangle(0, 0, shell.getClientArea().width, shell.getClientArea().height);
				e.gc.fillRectangle(rect);

				e.gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY));

				Font font = new Font(shell.getDisplay(), "Arial", 12, SWT.BOLD | SWT.ITALIC);
				// ...
				e.gc.setFont(font);
				e.gc.drawText(wo.getName(), 10, 10);

				font = new Font(shell.getDisplay(), "Arial", 10, SWT.DEFAULT);
				e.gc.setFont(font);
				e.gc.drawText("Duración: " + totalLength / 60 + " minutos en " + totalIntervals + " intervalos", 10,
						30);

				// ...
				font.dispose();
				// e.gc.setBackground(Display.getDefault().getSystemColor(SWT.TRANSPARENT));

				Double timeline = 0D;

				ArrayList<Step> paintableSteps = new ArrayList<Step>();

				for (Step step : stepList) {
					if (!splitIntervalApply(paintableSteps, step)) {
						paintableSteps.add(step);
					}
				}

				for (Step step : paintableSteps) {

					Double horizontalTime = step.getTime() * hMeter;
					Double verticalPower = step.getPower() * vMeter;

					if (step.isRampLoop()) {

						int powerRamp = step.getPower();

						for (int i = 0; i < step.getTime() / 60; i++) {

							Double hmRamp = 60 * hMeter;
							Double vmRamp = dh - (powerRamp * vMeter);

							rect = new Rectangle(timeline.intValue(), vmRamp.intValue(), timeline.intValue() + vmRamp.intValue(),
									dh.intValue());

							Color color = selectColorByInterval(powerRamp * userTuneUp);

							e.gc.setForeground(color);
							e.gc.setBackground(color);

							log.debug(String.format("draw rectangle: %d %d %d %d", timeline.intValue(), vmRamp.intValue(),
									timeline + vmRamp.intValue(), dh.intValue()));

							e.gc.fillRectangle(rect);

							timeline += hmRamp.intValue();

							powerRamp += 7;

						}
					} else {

						Double raise = null;

						Color color = selectColorByInterval(step.getPower() * userTuneUp);

						e.gc.setForeground(color);
						e.gc.setBackground(color);

						if (step.getRaisePower() > 0 && step.getRaisePower() != step.getPower()) {

							// vm es la medida de pontencia inicial y raise la final
							/*
							 * GC.drawPolyline(int[] pointArray); Draw a series of interconnecting lines
							 * with the int[] representing x and y positions for consecutive points. The
							 * statement: gc.drawPolyline(new int[] { 25,5,45,45,5,45 }); draws a line from
							 * 25,5 to 45,45 and then from 45,45 onto 5,45.
							 */
							/*
							 * raise = dh - (step.getRaisePower() * vMeter);
							 * 
							 * Path path = new Path(); path.moveTo(timeline, vm.intValue());
							 * path.lineTo(timeline + hm.intValue(), raise.intValue()); path.lineTo(timeline
							 * + hm.intValue(), dh.intValue()); path.lineTo(timeline, dh.intValue());
							 * path.lineTo(timeline, vm.intValue()); path.close(); canvas.drawPath(path,
							 * paint);
							 * 
							 * 
							 * 
							 * int[] pointArray = new int[] { timeline, dh.intValue(), // base timeline ,
							 * vm.intValue(), // pico superior izquierdo timeline + hm.intValue(),
							 * raise.intValue(), // pico superior derecho timeline + hm.intValue(),
							 * dh.intValue(), // pico inferior derecho timeline, dh.intValue() }; // vuelta
							 * al principio
							 */

							raise = step.getRaisePower() * vMeter;

							int[] pointArray = new int[] { timeline.intValue(), dh.intValue(), // base
									timeline.intValue(), dh.intValue() - verticalPower.intValue(), // pico superior izquierdo
									timeline.intValue() + horizontalTime.intValue(), dh.intValue() - raise.intValue(), // pico
																											// superior
																											// derecho
									timeline.intValue() + horizontalTime.intValue(), dh.intValue(), // pico inferior derecho
									timeline.intValue(), dh.intValue() }; // vuelta al principio
							log.debug(String.format("draw rectangle: %d %d %d %d", timeline.intValue(), verticalPower.intValue(),
									horizontalTime.intValue(), dh.intValue()));
							e.gc.fillPolygon(pointArray);

						} else {

							/*
							 * @param x the x coordinate of the origin of the rectangle
							 * 
							 * @param y the y coordinate of the origin of the rectangle
							 * 
							 * @param width the width of the rectangle
							 * 
							 * @param height the height of the rectangle
							 */

							Double recWith = step.getTime() * hMeter;
							Double recHeight = -step.getPower() * vMeter;

							rect = new Rectangle(timeline.intValue(), dh.intValue(), recWith.intValue(), recHeight.intValue());

							log.debug(String.format("draw rectangle: %d %d %d %d", timeline.intValue(), verticalPower.intValue(),
									recWith.intValue(), recHeight.intValue()));

							e.gc.fillRectangle(rect);
						}

						timeline += horizontalTime;
					}
				}

				e.gc.setLineWidth(STROKE_WIDTH);
				float vLine = Double.valueOf(dh / 2).floatValue() * userTuneUp;

				e.gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
				e.gc.drawLine(0, Float.valueOf(vLine).intValue(), timeline.intValue(), Float.valueOf(vLine).intValue());

			}
		};
	}

	private int getTimeRaised(Step step, Integer raisePower) {

		int result = 0;

		result = (raisePower - step.getPower()) * (step.getTime() / (step.getRaisePower() - step.getPower()));

		return result;
	}

	private boolean splitIntervalApply(ArrayList<Step> steps, Step step) {

		if (step.getRaisePower() > 0 && step.getPower() != step.getRaisePower()
				&& !selectColorByInterval(step.getPower()).equals(selectColorByInterval(step.getRaisePower()))) {

			Step begin = new Step();
			begin.setPower(step.getPower());

			if (step.getRaisePower() > step.getPower())
				begin.setRaisePower(selectUpperPowerLimit(step.getPower()).intValue());
			else
				begin.setRaisePower(selectLowerPowerLimit(step.getPower()).intValue() - 1);

			begin.setTime(getTimeRaised(step, begin.getRaisePower()));

			if (begin.getTime() > 0) {
				steps.add(begin);

				Step next = new Step();
				next.setPower(begin.getRaisePower());
				next.setRepeat(step.getRepeat());
				next.setRecoveryTime(step.getRecoveryTime());
				next.setParent(step.getParent());
				next.setRaisePower(step.getRaisePower());
				next.setTime(step.getTime() - begin.getTime());

				if (!splitIntervalApply(steps, next)) {
					steps.add(next);
				}
				return true;
			}

		}
		return false;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public int getTotalIntervals() {
		return totalIntervals;
	}

	public void setTotalIntervals(int totalIntervals) {
		this.totalIntervals = totalIntervals;
	}

	public Workout getWorkout() {
		return wo;
	}

	public ArrayList<Step> getStepList() {
		return stepList;
	}

	public int getTss() {
		return tss != null ? tss.intValue() : 0;
	}

	public ArrayList<Step> getMainSet() {

		ArrayList<Step> steps = new ArrayList<>();

		Step previous = null;

		for (Step step : wo.getMainElement()) {

			if (step.equals(previous)) {
				previous.setRepeat(previous.getRepeat() + 1);
			} else if (step.getTime() > 0) {
				steps.add(step);
				previous = step;
			}

		}
		return steps;
	}

}
