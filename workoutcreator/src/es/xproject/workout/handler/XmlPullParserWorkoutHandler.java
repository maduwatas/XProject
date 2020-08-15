package es.xproject.workout.handler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import es.xproject.workout.base.Cold;
import es.xproject.workout.base.Main;
import es.xproject.workout.base.Step;
import es.xproject.workout.base.Warn;
import es.xproject.workout.base.WorkElement;
import es.xproject.workout.base.Workout;

import java.io.IOException;
import java.io.InputStream;

public class XmlPullParserWorkoutHandler {

	private static final String WORK = "work";
	private static final String TYPE = "type";
	private static final String NAME = "name";
	private static final String STEP = "step";
	private static final String WARN = "warn";
	private static final String COLD = "cold";
	private static final String MAIN = "main";
	private static final String POWER = "power";
	private static final String RAMPLOOP = "rampLoop";
	private static final String TIME = "time";
	private static final String RAISEPOWER = "raisePower";

	private static final String REPEAT = "repeat";
	private static final String RECOVERYPOWER = "recoveryPower";
	private static final String RECOVERYTIME = "recoveryTime";

	private Workout work;
	private String text;

	public Workout parse(InputStream is) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();

			parser.setInput(is, null);

			WorkElement openElement = null;
			Step openStep = null;
			boolean onStep = false;
			int stepLevel = 0;

			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase(WORK)) {
						// create a new instance of employee
						work = new Workout();
					} else if (tagname.equalsIgnoreCase(STEP)) {

						if (stepLevel > 0) {
							Step step = new Step();
							step.setParent(openStep);
							openStep.add(step);

							openStep = step;
						} else {
							openStep = new Step();
							openElement.add(openStep);
							openStep.setParent(openElement);
						}

						stepLevel++;
					} else if (tagname.equalsIgnoreCase(WARN)) {
						openElement = new Warn();

					} else if (tagname.equalsIgnoreCase(MAIN)) {
						openElement = new Main();
					} else if (tagname.equalsIgnoreCase(COLD)) {
						openElement = new Cold();
					}
				case XmlPullParser.TEXT:
					text = parser.getText();
					break;
				case XmlPullParser.END_TAG:

					if (tagname.equalsIgnoreCase(NAME)) {
						work.setName(text);
					}
					if (tagname.equalsIgnoreCase(TYPE)) {
						work.setType(text);
					} else if (tagname.equalsIgnoreCase(WARN)) {
						work.setWarnElement((Warn) openElement);
					} else if (tagname.equalsIgnoreCase(MAIN)) {
						work.setMainElement((Main) openElement);
					} else if (tagname.equalsIgnoreCase(COLD)) {
						work.setColdElement((Cold) openElement);
					} else if (tagname.equalsIgnoreCase(STEP)) {
						stepLevel--;

						if (stepLevel > 0)
							openStep = (Step) openStep.getParent();
					} else if (tagname.equalsIgnoreCase(POWER)) {
						openStep.setPower(Integer.valueOf(text));
					} else if (tagname.equalsIgnoreCase(RAMPLOOP)) {
						openStep.setRampLoop(Boolean.valueOf(text));
					} else if (tagname.equalsIgnoreCase(RECOVERYTIME)) {
						openStep.setRecoveryTime(Integer.valueOf(text));
					} else if (tagname.equalsIgnoreCase(RECOVERYPOWER)) {
						openStep.setRecoveryPower(Integer.valueOf(text));
					} else if (tagname.equalsIgnoreCase(REPEAT)) {
						openStep.setRepeat(Integer.valueOf(text));
					} else if (tagname.equalsIgnoreCase(TIME)) {
						openStep.setTime(Integer.valueOf(text));
					} else if (tagname.equalsIgnoreCase(RAISEPOWER)) {
						openStep.setRaisePower(Integer.valueOf(text));
					}
					break;

				default:
					break;
				}
				eventType = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO validar que está todo correcto

		return work;
	}
}
