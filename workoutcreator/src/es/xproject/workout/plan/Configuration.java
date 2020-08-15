package es.xproject.workout.plan;

import java.util.ArrayList;
import java.util.HashMap;

import es.xproject.workout.base.Workout;
import es.xproject.workout.base.WorkoutFactory;

public class Configuration {

	private static HashMap<Integer, ArrayList<Workout>> baseWorkouts = null;
	private static HashMap<Integer, ArrayList<Workout>> specalityWorkouts = null;
	private static ArrayList<Workout> extraWorkouts = null;
	private static HashMap<Integer, ArrayList<Workout>> buildWorkouts = null;

	static {
		createExtra();
		createBase();
		createBuild();
		// createSpeciality();
	}

	private static void createBase() {
		baseWorkouts = new HashMap<Integer, ArrayList<Workout>>();

		ArrayList<Workout> workouts = new ArrayList<Workout>();

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.RAMP_TEST));
		workouts.add(new Workout("Vamos", 3, 12 * 60, 3 * 60, 88, 91));
		workouts.add(new Workout("Diez", 5, 10 * 60, 5 * 60, 88, 91));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(new Workout(true));
		workouts.add(new Workout("Tatorri", 3, 20 * 60, 10 * 60, 88, 91));
		workouts.add(new Workout("Supertempo", 3, 25 * 60, 5 * 60, 85));

		baseWorkouts.put(1, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(new Workout("Spinner", 7, 6 * 60, 1 * 60, 88, 94));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(new Workout("Bandobani", 5, 12 * 60, 4 * 60, 88, 92));
		workouts.add(new Workout(true));

		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.WRIGHT_PEAK));

		workouts.add(WorkoutFactory.getInstance().buildLowRecovery("Mandoban", 3, 20 * 60, 15 * 60, 85, 92, 70));

		baseWorkouts.put(2, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(new Workout("12V", 5, 12 * 60, 4 * 60, 88, 94));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		// base es new Workout(60,3,20*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.CARRILLON));

		workouts.add(new Workout(true));

		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.JUNEAU));

		workouts.add(WorkoutFactory.getInstance().buildLowRecovery("Fully", 3, 20 * 60, 15 * 60, 85, 92, 65));

		baseWorkouts.put(3, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(new Workout("Sevenup", 7, 6 * 60, 1 * 60, 88, 94));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(new Workout("Runedia", 5, 12 * 60, 4 * 60, 88, 92));
		workouts.add(new Workout(true));

		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.WRIGHT_PEAK));

		workouts.add(WorkoutFactory.getInstance().buildLowRecovery("Quincetto", 3, 15 * 60, 15 * 60, 85, 92, 67));
		baseWorkouts.put(4, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ECLIPSE));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TEMPO));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ECLIPSE));
		workouts.add(new Workout(true));

		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TALLAC));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.JUNEAU));

		baseWorkouts.put(5, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.GALENA));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TEMPO));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.SIXERS));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.CARRILLON));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.CAMARA));

		baseWorkouts.put(6, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TEMPO1));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(30));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(120));

		baseWorkouts.put(7, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.RAMP_TEST));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.CARRILLON));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.HUNTER));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TALLAC));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ANTELOPE_PLUS));

		baseWorkouts.put(8, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ANTELOPE));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.GEIGER));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.HUNTER2));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ECLIPSE_PLUS));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.HUNTER));

		baseWorkouts.put(9, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ECLIPSE));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ANVIL));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ANTELOPE_PLUS));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.GALENA));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.WRIGHT_PEAK));

		baseWorkouts.put(10, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.INDIO));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TALLAC));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.JUNEAU));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.WRIGHT_PEAK));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ANTELOPE_PLUS));

		baseWorkouts.put(11, workouts);

		workouts = new ArrayList<Workout>();

		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(30));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().buildEndurance(120));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));

		baseWorkouts.put(12, workouts);
	}

	private static void createSpeciality() {
		specalityWorkouts = new HashMap<Integer, ArrayList<Workout>>();

	}

	private static void createExtra() {
		extraWorkouts = new ArrayList<Workout>();

		extraWorkouts.add(WorkoutFactory.getInstance().buildOverUnder("Hojo", 2, 8 * 60, 5 * 60, 84, 4, 110, true));
		extraWorkouts.add(WorkoutFactory.getInstance().buildOverUnder("Exin", 4, 6 * 60, 6 * 60, 85, 4, 105, false));
		extraWorkouts.add(WorkoutFactory.getInstance().buildOverUnder("Exin+", 4, 8 * 60, 6 * 60, 85, 4, 108, false));
		extraWorkouts
				.add(WorkoutFactory.getInstance().buildOverUnder("Castlevania", 6, 8 * 60, 6 * 60, 85, 4, 105, false));
		extraWorkouts.add(WorkoutFactory.getInstance().buildOverUnder("Shogun", 6, 8 * 60, 5 * 60, 84, 4, 110, true));

		extraWorkouts.add(WorkoutFactory.getInstance().buildWithSurge("Rocinante", 5, 3 * 60, 1 * 60, 95, 150, 30,
				false, 15 * 60, 65));
		extraWorkouts.add(WorkoutFactory.getInstance().buildWithSurge("Kona", 6, 5 * 60, 2 * 60, 88, 120, 60, false,
				15 * 60, 65));

		extraWorkouts.add(WorkoutFactory.getInstance().buildSprints("4020", 10, 40, 60, 120, 150, 1, 0, 20));

		extraWorkouts.add(WorkoutFactory.getInstance().buildBernhardt("Bernhardt", 3, 3, 3, 30, 20, 0));
		extraWorkouts.add(WorkoutFactory.getInstance().buildBernhardt("Bernhardt+", 3, 3, 3, 30, 20, 10));

		extraWorkouts.add(WorkoutFactory.getInstance().buildOverUnder("Ska", 3, 20 * 60, 6 * 60, 80, 5, 95, false));

		extraWorkouts.add(WorkoutFactory.getInstance().buildOverUnder("Ska", 3, 20 * 60, 6 * 60, 80, 5, 95, false));
		extraWorkouts
				.add(WorkoutFactory.getInstance().buildOverUnder("RaiseSweet", 4, 18 * 60, 6 * 60, 76, 6, 98, true));

		extraWorkouts.add(WorkoutFactory.getInstance().buildLowRecovery("Temporal", 5, 10 * 60, 5 * 60, 70, 94, 70));

		// buildLowRecovery String name, int intervals, int duration, int recovery, int
		// power, int raisePower,int recoveryPower) {
		extraWorkouts
				.add(WorkoutFactory.getInstance().buildLowRecovery("BradburyRamps", 5, 5 * 60, 5 * 60, 90, 90, 55));

		// buildStromlo(String name, int intervals, int duration, int recovery, int
		// power, int raisePower,int recoveryDuration, int recoveryPower) {
		extraWorkouts
				.add(WorkoutFactory.getInstance().buildStromlo("Bradbury", 2, 15 * 60, 5 * 60, 75, 85, 20 * 60, 75));

		// stages vo max
		extraWorkouts.add(WorkoutFactory.getInstance().buildStages("Stages", 4, 5, 30, 30, 5 * 60, 120, 80));
		extraWorkouts.add(WorkoutFactory.getInstance().buildStages("Rotor", 5, 4, 30, 30, 4 * 60, 115, 85));
		extraWorkouts.add(WorkoutFactory.getInstance().buildStages("Molinos", 3, 15, 15, 45, 5 * 60, 115, 82));
		extraWorkouts.add(WorkoutFactory.getInstance().buildStages("Hacha", 4, 1, 3 * 60, 4 * 60, 5 * 60, 106, 94));

		// stages umbral
		extraWorkouts.add(WorkoutFactory.getInstance().buildStages("O2", 5, 6, 30, 30, 4 * 60, 105, 88));

		// stages tempo-sweet spot
		extraWorkouts.add(WorkoutFactory.getInstance().buildStages("Metro", 4, 9, 30, 60, 4 * 60, 93, 76));
		extraWorkouts.add(WorkoutFactory.getInstance().buildStages("Aqua", 2, 15, 60, 60 * 2, 5 * 60, 88, 68));

		extraWorkouts.add(WorkoutFactory.getInstance().buildLadder("Ladder", 4, 90, 5 * 60));

	}

	private static void createBuild() {
		buildWorkouts = new HashMap<Integer, ArrayList<Workout>>();

		ArrayList<Workout> workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.RAMP_TEST));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.BAYS));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.STROMLO));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		workouts.add(new Workout("Etna", 4, 20 * 60, 4 * 60, 85, 85));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PALISADE));
		buildWorkouts.put(1, workouts);

		workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.SHORTOFF));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(60));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.BUDAWANG));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.MCADIE));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.WRIGHT_PEAK));
		buildWorkouts.put(2, workouts);

		workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.MONADKNOCK));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.BAYS));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.STROMLO_PLUS));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PICKETWARD));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TRYMOUNTAIN));

		buildWorkouts.put(3, workouts);

		workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(30));
		// base es new Workout(60,3,30*60,5*60,85)
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));

		workouts.add(WorkoutFactory.getInstance().buildEndurance(120));

		buildWorkouts.put(4, workouts);

		workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.RAMP_TEST));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.CHIRRI));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.THIMBLE));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.SPANISHNEEDLE));

		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.JUNEAU));

		buildWorkouts.put(5, workouts);

		workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PISHGAH));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ENGLISH));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.LIONROCK));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.ECLIPSE));

		buildWorkouts.put(6, workouts);

		workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.SOUTHTWIN));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.BAYS));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.MRO));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.MACDUFFIE));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.TALLAC_PLUS));

		buildWorkouts.put(7, workouts);

		workouts = new ArrayList<Workout>();
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		workouts.add(WorkoutFactory.getInstance().get(WorkoutFactory.PETTIT));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(90));
		workouts.add(new Workout(true));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(120));
		workouts.add(WorkoutFactory.getInstance().buildEndurance(120));

		buildWorkouts.put(8, workouts);

		/*
		 * .add(0); .add(120); .add(60); .add(120); .add(60); .add(90); .add(120);
		 * 
		 * buildWorkouts.put(1, ); buildWorkouts.put(2, ); buildWorkouts.put(3, );
		 * 
		 * ArrayList<Workout> Rec = new ArrayList<Workout>(); .add(0); .add(90);
		 * .add(60); .add(120); .add(0); .add(90); .add(120);
		 * 
		 * buildWorkouts.put(4, Rec); buildWorkouts.put(5, ); buildWorkouts.put(6, );
		 * buildWorkouts.put(7, );
		 * 
		 * buildWorkouts.put(8, Rec);
		 */

	}

	public static HashMap<Integer, ArrayList<Workout>> getBaseWorkouts() {
		return baseWorkouts;
	}

	public static ArrayList<Workout> getExtra() {
		return extraWorkouts;
	}

	public static void setBaseWorkouts(HashMap<Integer, ArrayList<Workout>> baseWorkouts) {
		Configuration.baseWorkouts = baseWorkouts;
	}

	public static HashMap<Integer, ArrayList<Workout>> getSpecalityWorkouts() {
		return specalityWorkouts;
	}

	public static void setSpecalityWorkouts(HashMap<Integer, ArrayList<Workout>> specalityWorkouts) {
		Configuration.specalityWorkouts = specalityWorkouts;
	}

	public static HashMap<Integer, ArrayList<Workout>> getBuildWorkouts() {
		return buildWorkouts;
	}

	public static void setBuildWorkouts(HashMap<Integer, ArrayList<Workout>> buildWorkouts) {
		Configuration.buildWorkouts = buildWorkouts;
	}

}
