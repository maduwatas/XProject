package es.xproject.workout.plan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import es.xproject.workout.base.Constants;
import es.xproject.workout.base.Step;
import es.xproject.workout.base.Workout;

public class Creator {

	private static String phase;
	private static Calendar start;
	private static String week;
	private static String day;

	private static final String BASE = "B";
	private static final String BUILD = "C";
	private static final String SPECIFIC = "E";
	private static final String EXTRA = "X";
	private static StringBuffer planBuffer = null;

	private static final Logger log = Logger.getLogger(Creator.class);

	private static SimpleDateFormat calendarFormat = new SimpleDateFormat("yyyyMMdd");

	public static void main(String[] args) throws Exception {

		readArgs(args);

		if (BASE.equalsIgnoreCase(phase)) {
			baseWorkout();
		} else if (BUILD.equalsIgnoreCase(phase)) {
			buildWorkout();
		} else if (SPECIFIC.equalsIgnoreCase(phase)) {
			buildSpecificWorkout();
		} else if (EXTRA.equalsIgnoreCase(phase)) {
			buildExtraWorkout();

		} else
			AllWorkout();

		if (start != null && !EXTRA.equals(phase)) {
			File planFile = new File(Constants.FILE_PATH + Constants.PLANFILE_NAME);
			BufferedWriter planWriter = new BufferedWriter(new FileWriter(planFile));
			planBuffer.append("</plan>");
			planWriter.append(prettyFormat(planBuffer.toString()));
			planWriter.close();
		}

	}

	private static void buildExtraWorkout() {

		for (Workout wo : Configuration.getExtra()) {
			writeWorkout(wo, 0);
		}

	}

	private static void buildSpecificWorkout() {

		// time 1 to 2 hours

	}

	private static void buildWorkout() {
		// TODO Auto-generated method stub

	}

	private static void writePlan(Workout wo, String day, int mWeek) throws IOException {

		if (start != null) {

			if (planBuffer == null) {
				planBuffer = new StringBuffer();
				planBuffer.append("<plan>");
			}

			planBuffer.append("<day>");
			planBuffer.append("<week>" + mWeek + "</week>");
			planBuffer.append("<work>");
			planBuffer.append(wo.getName());
			planBuffer.append("</work>");

			planBuffer.append("<date>");
			planBuffer.append(calendarFormat.format(start.getTime()));
			planBuffer.append("</date>");
			start.add(Calendar.DATE, 1);

			planBuffer.append("</day>");
		}

	}

	private static void AllWorkout() {

		int week = 1;

		for (int mWeek = 1; mWeek <= 12; mWeek++) {
			ArrayList<Workout> conf = Configuration.getBaseWorkouts().get(mWeek);

			if (day == null) {
				for (int i = 1; i <= 7; i++) {
					generateByDay(conf, i, mWeek);
				}
			} else {
				generateByDay(conf, Integer.valueOf(day), mWeek);
			}
			week++;
		}

		for (int mWeek = 1; mWeek <= 7; mWeek++) {
			ArrayList<Workout> conf = Configuration.getBuildWorkouts().get(mWeek);

			if (day == null) {
				for (int i = 1; i <= 7; i++) {
					generateByDay(conf, i, week);
				}
			} else {
				generateByDay(conf, Integer.valueOf(day), week);
			}
			week++;
		}

		for (int mWeek = 1; mWeek <= 2; mWeek++) {
			ArrayList<Workout> conf = Configuration.getSpecialityWorkouts().get(mWeek);

			if (day == null) {
				for (int i = 1; i <= 8; i++) {
					generateByDay(conf, i, week);
				}
			} else {
				generateByDay(conf, Integer.valueOf(day), week);
			}
			week++;
		}
		
		
		
		for (Workout wo : Configuration.getExtra()) {
			writeWorkout(wo, 0);
		}
	}

	
	private static void baseWorkout() {

		for (int mWeek = 1; mWeek <= 12; mWeek++) {
			ArrayList<Workout> conf = Configuration.getBaseWorkouts().get(mWeek);

			if (day == null) {
				for (int i = 1; i <= 7; i++) {
					generateByDay(conf, i, mWeek);
				}
			} else {
				generateByDay(conf, Integer.valueOf(day), mWeek);
			}
		}
	}

	private static void generateByDay(ArrayList<Workout> conf, int day, int mWeek) {

		try {

			if (conf.size() >= Integer.valueOf(day)) {
				Workout wo = conf.get(Integer.valueOf(day) - 1);

				writeWorkout(wo, mWeek);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void writeWorkout(Workout wo, int mWeek) {
		BufferedWriter writer = null;

		try {
			log.debug("writeWorkout " + wo.getName());

			if (wo.getName() == null)
				wo.setName("Hamaca");

			wo.setName(wo.getName());
			String fileName = wo.getName() + ".xml";

			File file = new File(Constants.FILE_PATH + Constants.WORKOUTS_DIRECTORY + fileName);

			StringBuffer buff = new StringBuffer();

			writeWo(wo, buff, mWeek);

			writer = new BufferedWriter(new FileWriter(file));
			writer.append(prettyFormat(buff.toString()));
			writer.flush();
		} catch (Exception e) {
			log.error("Error " + wo.getName() + ":" + e);
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {

				}
		}
	}

	private static void writeWo(Workout wo, StringBuffer writer, int mWeek) throws IOException {
		try {
			writer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			writer.append("<work>");

			if (wo.isRest()) {
				writer.append("<name>Hamaca</name>");
				writer.append("<type>" + wo.getType() + "</type>");
			} else {

				writer.append("<name>" + wo.getName() + "</name>");
				writer.append("<type>" + wo.getType() + "</type>");
				writer.append("<warn>");

				for (Step step : wo.getWarn().getSteps()) {
					writeStep(step, writer);
				}
				writer.append("</warn>");
				writer.append("<main>");
				for (Step step : wo.getSteps()) {
					writeStep(step, writer);
				}
				writer.append("</main>");
				writer.append("<cold>");
				writeStep(wo.getCold(), writer);
				writer.append("</cold>");
			}
			writer.append("</work>");

			if (mWeek > 0)
				writePlan(wo, day, mWeek);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void writeStep(Step step, StringBuffer writer) throws IOException {
		writer.append("<step>");

		if (step.isRampLoop()) {
			writer.append("<rampLoop>true</rampLoop>");
			writer.append("<power>50</power>");

			writer.append("<time>1200</time>");
		} else {
			writer.append("<power>" + step.getPower() + "</power>");
			if (step.getRaisePower() > 0)
				writer.append("<raisePower>" + step.getRaisePower() + "</raisePower>");
			writer.append("<time>" + step.getTime() + "</time>");
			writer.append("<recoveryTime>" + step.getRecoveryTime() + "</recoveryTime>");
			writer.append("<recoveryPower>" + step.getRecoveryPower() + "</recoveryPower>");
		}
		writer.append("</step>");

	}

	private static void readArgs(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {

			if (args[i].equalsIgnoreCase("-phase")) {
				phase = args[++i];
			} else if (args[i].equalsIgnoreCase("-week")) {
				week = args[++i];
			} else if (args[i].equalsIgnoreCase("-day")) {
				day = args[++i];
			} else if (args[i].equalsIgnoreCase("-start")) {

				Calendar cal = Calendar.getInstance();

				cal.setTime(calendarFormat.parse(args[++i]));

				start = cal;
			}
		}

	}

	public static String prettyFormat(String input, int indent) {
		try {
			Source xmlInput = new StreamSource(new StringReader(input));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", indent);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			throw new RuntimeException(e); // simple exception handling, please review it
		}
	}

	public static String prettyFormat(String input) {
		return prettyFormat(input, 2);
	}
}
