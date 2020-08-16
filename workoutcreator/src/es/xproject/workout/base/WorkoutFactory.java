package es.xproject.workout.base;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkoutFactory {

	private static WorkoutFactory instance;

	public static final String WRIGHT_PEAK = "Ronda";

	public static final String CARRILLON = "Tuercas";
	public static final String CAMARA = "Camara";
	public static final String HUNTER = "Ladalmendral";
	public static final String HUNTER2 = "Ito";
	public static final String INDIO = "Indio";
	public static final String JUNEAU = "3@Team";
	public static final String GEIGER = "Paneles";
	public static final String PETTIT = "PerryLap";
	public static final String TALLAC = "Torri";
	public static final String TALLAC_PLUS = "Torri+";
	public static final String ECLIPSE = "Arroyo";
	public static final String ECLIPSE_PLUS = "Arroyo+";
	public static final String GALENA = "Castillos";
	public static final String SIXERS = "Sixers";
	public static final String ENDURANCE = "Scotty";
	public static final String TEMPO = "Anvil";
	public static final String ANVIL = "Anvil+";
	public static final String TEMPO1 = "RevolK";
	public static final String ANTELOPE_PLUS = "Lavazzota+";
	public static final String ANTELOPE = "Lavazzota";
	public static final String STROMLO = "Diramno";
	public static final String STROMLO_PLUS = "Diramno+";
	public static final String PALISADE = "Overunder";
	public static final String BUDAWANG = "TresArroyos";
	public static final String RAMP_TEST = "Test";
	public static final String CHIRRI = "Chirri";
	public static final String MCADIE = "Cuervo";

	public static final String PICKETWARD = "Suffery";
	public static final String BAYS = "Pergo";

	public static final String SHORTOFF = "Forza";
	public static final String MONADKNOCK = "Raise";
	public static final String THIMBLE = "Adrenalina";
	public static final String SPANISHNEEDLE = "Micros";
	public static final String PISHGAH = "Quatro";
	public static final String ENGLISH = "Exha";
	public static final String LIONROCK = "FR";
	public static final String MRO = "MRO";
	public static final String SOUTHTWIN = "Gusano";
	public static final String MACDUFFIE = "Matutano";

	public static final String TRYMOUNTAIN = "Contador";

	private HashMap<String, Workout> mMap = null;

	private WorkoutFactory() {

		mMap = new HashMap<String, Workout>();

		mMap.put(RAMP_TEST, buildRampTest(RAMP_TEST));

		mMap.put(TEMPO, buildTempo(TEMPO, 3, 15 * 60, 3 * 60, 75, 90));
		mMap.put(ANVIL, buildTempo(ANVIL, 4, 15 * 60, 4 * 60, 75, 94));
		mMap.put(TEMPO1, buildTempo(TEMPO1, 4, 12 * 60, 2 * 60, 75, 90));
		mMap.put(WRIGHT_PEAK, divide(new Workout(WRIGHT_PEAK, 3, 30 * 60, 5 * 60, 85), 30 * 60, 2, 1 * 60));

		mMap.put(JUNEAU, divide(new Workout(JUNEAU, 4, 18 * 60, 4 * 60, 85), 18 * 60, 2, 2 * 60));
		mMap.put(HUNTER2, buildLowRecovery(HUNTER2, 3, 18 * 60, 10 * 60, 88, 92, 65));

		mMap.put(INDIO, buildLowRecovery(INDIO, 3, 26 * 60, 8 * 60, 87, 73, 60));

		mMap.put(HUNTER, buildLowRecovery(HUNTER, 6, 10 * 60, 4 * 60, 88, 92, 65));
		mMap.put(TALLAC, new Workout(TALLAC, 3, 15 * 60, 3 * 60, 88, 94));
		mMap.put(TALLAC_PLUS, new Workout(TALLAC_PLUS, 4, 15 * 60, 7 * 60, 88, 94));
		mMap.put(ECLIPSE, new Workout(ECLIPSE, 3, 20 * 60, 3 * 60, 88, 94));
		mMap.put(ECLIPSE_PLUS, new Workout(ECLIPSE_PLUS, 4, 20 * 60, 3 * 60, 88, 94));
		mMap.put(GALENA, new Workout(GALENA, 3, 20 * 60, 5 * 60, 90, 94));
		mMap.put(SIXERS, new Workout(SIXERS, 6, 10 * 60, 2 * 60, 86, 94));
		mMap.put(GEIGER, new Workout(GEIGER, 3, 12 * 60, 3 * 60, 88, 91));
		mMap.put(PETTIT, buildEndurance(PETTIT, 60));
		mMap.put(ANTELOPE_PLUS, new Workout(ANTELOPE_PLUS, 5, 10 * 60, 6 * 60, 94, 94));
		mMap.put(ANTELOPE, new Workout(ANTELOPE, 5, 10 * 60, 5 * 60, 88, 94));

		mMap.put(THIMBLE, buildThreshold(THIMBLE, 3, 7, 1 * 60, 1 * 60, 125, 125, 6 * 60, 65, 40 * 60));
		mMap.put(SHORTOFF, buildThreshold(SHORTOFF, 3, 3, 3 * 60, 3 * 60, 115, 115, 3 * 60, 65, 40 * 60));
		mMap.put(MONADKNOCK, buildThreshold(MONADKNOCK, 3, 3, 3 * 60, 3 * 60, 115, 115, 3 * 60, 65, 35 * 60));
		mMap.put(TRYMOUNTAIN, buildThreshold(TRYMOUNTAIN, 3, 3, 3 * 60, 3 * 60, 120, 105, 3 * 60, 65, 0));
		mMap.put(SPANISHNEEDLE, buildThreshold(SPANISHNEEDLE, 6, 8, 15, 15, 150, 150, 5 * 60, 65, 0));
		mMap.put(PISHGAH, buildThreshold(PISHGAH, 2, 4, 4 * 60, 4 * 60, 110, 110, 5 * 60, 65, 30 * 60));
		mMap.put(ENGLISH, buildThreshold(ENGLISH, 3, 7, 60, 30, 120, 120, 7 * 60, 65, 25 * 60));
		mMap.put(SOUTHTWIN, buildThreshold(SOUTHTWIN, 3, 3, 4 * 60, 4 * 60, 120, 120, 5 * 60, 65, 25 * 60));

		mMap.put(BAYS, buildTempoWithSprint(BAYS, 5, 20 * 60, 0, 70, 75, 20));
		mMap.put(CHIRRI, buildLadder(CHIRRI, 8, 88, 6 * 60));
		mMap.put(CAMARA, carrillon(new Workout(CAMARA, 1, 24 * 60, 0, 80), 24 * 60, 4, 3 * 60));
		mMap.put(CARRILLON, carrillon(new Workout(CARRILLON, 3, 20 * 60, 5 * 60, 85), 20 * 60, 2, 30));
		mMap.put(PALISADE, carrillon(new Workout(PALISADE, 5, 9 * 60, 6 * 60, 95), 9 * 60, 2, 0));
		mMap.put(MCADIE, carrillon(new Workout(MCADIE, 4, 12 * 60, 6 * 60, 95), 12 * 60, 2, 30));
		mMap.put(PICKETWARD, carrillon(new Workout(PICKETWARD, 4, 13 * 60, 5 * 60, 96), 14 * 60, 2, 20));

		mMap.put(LIONROCK, buildWithSurge(LIONROCK, 3, 5 * 60, 1 * 60, 95, 150, 30, true, 35 * 60, 70));
		mMap.put(MRO, buildWithSurge(MRO, 5, 4 * 60, 1 * 60, 92, 150, 20, false, 25 * 60, 70));

		mMap.put(STROMLO, buildStromlo(STROMLO, 5, 8 * 60, 5 * 60, 102, 102, 35 * 60, 75));
		mMap.put(STROMLO_PLUS, buildStromlo(STROMLO_PLUS, 6, 8 * 60, 5 * 60, 102, 102, 20 * 60, 75));
		mMap.put(BUDAWANG, buildStromlo(BUDAWANG, 4, 9 * 60, 5 * 60, 102, 102, 40 * 60, 75));

		mMap.put(MACDUFFIE, buildSprints(MACDUFFIE, 5, 4 * 60, 5 * 60, 105, 200, 3, 45, 15));

	}

	private Workout buildThreshold(String name, int series, int repeats, int duration, int repeatsRecovery, int power,
			int raisePower, int seriesRecovery, int endurancPower, int enduranceDuration) {

		Workout wo = new Workout();
		wo.setName(name);

		wo.setWarn(Workout.thresholdWarn);

		for (int j = 1; j <= series; j++) {
			for (int i = 1; i <= repeats; i++) {

				if (i == repeats)
					wo.addStep(new Step(power, raisePower, 0, duration));
				else
					wo.addStep(new Step(power, raisePower, repeatsRecovery, duration));
			}

			if (j != series) {
				// descanso entre series un minuto por cada repetición
				wo.addStep(new Step(Workout.DEFAULT_RECOVERY, 0, seriesRecovery));
			}
		}

		wo.addStep(new Step(endurancPower, 0, enduranceDuration));

		wo.setCold(Workout.defaultCold);

		return wo;
	}

	public Workout get(String name) {
		return mMap.get(name);
	}

	public Workout buildWithSurge(String name, int intervals, int duration, int recovery, int power, int surgePower,
			int surgeDuration, boolean surgeAtInit, int recoveryDuration, int recoveryPower) {

		Workout wo = new Workout(name, intervals, duration, recovery, power, power);

		ArrayList<Step> steps = new ArrayList<Step>();

		for (Step step : wo.getSteps()) {

			// en cada intervalo de la sesión princpial meto un surge antes después
			if (step.getPower() == power) {
				if (surgeAtInit) {
					steps.add(new Step(surgePower, 0, surgeDuration));
					steps.add(step);
				} else {

					steps.add(new Step(step.getPower(), 0, step.getTime()));
					steps.add(new Step(surgePower, step.getRecoveryTime(), surgeDuration));
				}
			} else
				steps.add(step);
		}

		wo.setSteps(steps);

		// cambio el recovery del ultimo intervalo por una serie larga al recoverypower
		Step step = wo.getSteps().get(wo.getSteps().size() - 1);
		step.setRecoveryTime(0);

		wo.getSteps().add(new Step(recoveryPower, 0, recoveryDuration));

		return wo;

	}

	public Workout buildSprints(String name, int intervals, int duration, int recovery, int power, int sprintPower,
			int sprints, int recoverySprints, int sprintDuration) {

		Workout wo = new Workout(name, intervals, duration, recovery, power, power);

		// justo antes de cada intervalo normal meto una serie de intervalos de sprint
		ArrayList<Step> steps = new ArrayList<Step>();

		for (Step step : wo.getSteps()) {

			// en cada intervalo de la sesión princpial meto un surge antes después
			if (step.getPower() == power) {

				for (int i = 1; i <= sprints; i++) {
					steps.add(new Step(sprintPower, recoverySprints, sprintDuration));
				}

				steps.add(step);
			} else
				steps.add(step);
		}

		wo.setSteps(steps);

		return wo;

	}

	public Workout buildStromlo(String name, int intervals, int duration, int recovery, int power, int raisePower,
			int recoveryDuration, int recoveryPower) {
		Workout wo = new Workout(name, intervals, duration, recovery, power, raisePower);

		// cambio el recovery del ultimo intervalo por una serie larga al recoverypower
		Step step = wo.getSteps().get(wo.getSteps().size() - 1);
		step.setRecoveryTime(0);

		wo.getSteps().add(new Step(recoveryPower, 0, recoveryDuration));

		return wo;

	}

	public Workout buildLowRecovery(String name, int intervals, int duration, int recovery, int power, int raisePower,
			int recoveryPower) {
		Workout lowRecovery = new Workout();
		lowRecovery.setName(name);
		Workout warn = new Workout();
		warn.getSteps().add(new Step(40, 85, 3 * 60, 10 * 60));

		lowRecovery.setWarn(warn);
		lowRecovery.setCold(new Step(65, 40, 0, 5 * 60));

		if (raisePower < power) {
			int aux = raisePower;
			raisePower = power;
			power = aux;
		}

		int diff = raisePower - power;

		for (int i = 0; i < intervals; i++) {

			int middle = Double.valueOf(diff * Math.random()).intValue();

			lowRecovery.getSteps().add(new Step(power, raisePower - middle, 0, duration / 4));

			middle = Double.valueOf(diff * Math.random()).intValue();

			lowRecovery.getSteps().add(new Step(raisePower, power + middle, 0, duration / 4));

			middle = Double.valueOf(diff * Math.random()).intValue();

			lowRecovery.getSteps().add(new Step(raisePower - middle, power, 0, duration / 4));

			middle = Double.valueOf(diff * Math.random()).intValue();

			lowRecovery.getSteps().add(new Step(power, power + middle, 0, duration / 4));

			lowRecovery.getSteps().add(new Step(recoveryPower, 0, recovery));
		}

		return lowRecovery;
	}

	public Workout buildTempoWithSprint(String name, int intervals, int duration, int recovery, int power,
			int raisePower, int sprint) {
		Workout tempo = new Workout();
		tempo.setName(name);
		Workout warn = new Workout();
		warn.getSteps().add(new Step(40, 85, 0, 10 * 60));

		tempo.setWarn(warn);
		tempo.setCold(new Step(65, 40, 0, 5 * 60));

		if (raisePower < power) {
			int aux = raisePower;
			raisePower = power;
			power = aux;
		}

		int diff = raisePower - power;

		for (int i = 0; i < intervals; i++) {

			int middle = Double.valueOf(diff * Math.random()).intValue();

			tempo.getSteps().add(new Step(power, raisePower - middle, 0, duration));
			tempo.getSteps().add(new Step(200, 0, sprint));

		}

		return tempo;

	}

	private Workout buildTempo(String name, int intervals, int duration, int recovery, int power, int raisePower) {

		Workout tempo = new Workout();
		tempo.setName(name);
		Workout warn = new Workout();
		warn.getSteps().add(new Step(40, 85, 0, 10 * 60));

		tempo.setWarn(warn);
		tempo.setCold(new Step(65, 40, 0, 5 * 60));

		if (raisePower < power) {
			int aux = raisePower;
			raisePower = power;
			power = aux;
		}

		int diff = raisePower - power;

		for (int i = 0; i < intervals; i++) {

			int middle = Double.valueOf(diff * Math.random()).intValue();

			tempo.getSteps().add(new Step(power, raisePower - middle, 0, duration / 4));

			middle = Double.valueOf(diff * Math.random()).intValue();

			tempo.getSteps().add(new Step(raisePower, power + middle, 0, duration / 4));

			middle = Double.valueOf(diff * Math.random()).intValue();

			tempo.getSteps().add(new Step(raisePower - middle, power, 0, duration / 4));

			middle = Double.valueOf(diff * Math.random()).intValue();

			if (intervals == i - 1)
				tempo.getSteps().add(new Step(power, power + middle, 0, duration / 4));
			else
				tempo.getSteps().add(new Step(power, power + middle, recovery, duration / 4));
		}

		return tempo;
	}

	private Workout buildRampTest(String name) {

		Workout ramp = new Workout();
		ramp.setName(name);
		Workout warn = new Workout();
		warn.getSteps().add(new Step(40, 0, 5 * 60));
		// TODO Auto-generated method stub
		ramp.setWarn(warn);

		Step step = new Step(0, 0, 1 * 60);
		step.setRampLoop(true);

		ramp.getSteps().add(step);

		ramp.setCold(new Step(65, 40, 0, 5 * 60));
		return ramp;
	}

	private Workout buildEndurance(String name, int duration) {
		Workout endurance = new Workout();
		endurance.setName(name);
		Workout warn = new Workout();
		warn.getSteps().add(new Step(40, 65, 0, 5 * 60));

		endurance.setWarn(warn);
		endurance.setCold(new Step(65, 40, 0, 5 * 60));
		int timeSpent = 10;
		while (timeSpent < duration) {

			int low = Double.valueOf(10 * Math.random()).intValue();
			int hight = Double.valueOf(10 * Math.random()).intValue();

			endurance.getSteps().add(new Step(59 + low, 76 - hight, 0, 5 * 60));

			timeSpent += 5;
		}

		return endurance;
	}

	public Workout buildEndurance(int duration) {
		return buildEndurance(ENDURANCE + duration, duration);
	}

	private Workout divide(Workout base, int time, int breaks, int recovery) {
		// parte los intervalos princpales del base metiendo descansos pequeños

		Workout wo = (Workout) base.clone();

		wo.getSteps().clear();

		for (Step step : base.getSteps()) {

			if (step.getTime() == time) {
				for (int i = 1; i < breaks; i++) {
					wo.getSteps().add(new Step(step.getPower(), recovery, step.getTime() / breaks));
				}
				wo.getSteps().add(new Step(step.getPower(), step.getRecoveryTime(), step.getTime() / breaks));
			} else
				wo.getSteps().add(step);
		}

		return wo;
	}

	private Workout carrillon(Workout base, int time, int breaks, int recovery) {
		// parte los intervalos princpales del base metiendo descansos pequeños

		base = divide(base, time, breaks, recovery);

		Workout wo = base.clone();
		// wo.setName(CARRILLON);
		int interval = time / breaks;
		int timeRemaining = 0;

		wo.getSteps().clear();

		for (Step step : base.getSteps()) {

			// intervalo a sustituir por los surges de carrillon
			if (step.getTime() == interval) {

				for (int i = 1; i < breaks; i++) {
					while (timeRemaining < step.getTime()) {

						wo.getSteps().add(new Step(step.getPower(), 0, 120));
						wo.getSteps().add(new Step(step.getPower() + step.getPower() * 5 / 100,
								step.getPower() + step.getPower() * 15 / 100, 0, 30));

						timeRemaining += (120 + 30);
					}
					timeRemaining = 0;

				}
				wo.getSteps().add(new Step(step.getRecoveryPower(), 0, step.getRecoveryTime()));
			} else
				wo.getSteps().add(step);
		}

		return wo;
	}

	public Workout buildOverUnder(String name, int series, int duration, int seriesRecovery, int basePower, int repeats,
			int raisePower, boolean progresive) {

		Workout base = new Workout(name, series, duration, seriesRecovery, basePower);

		Workout wo = base.clone();

		// esto es lo que dura cada surge
		int interval = duration / repeats;

		wo.getSteps().clear();

		for (Step step : base.getSteps()) {

			// intervalo a sustituir por los surges de carrillon
			if (step.getTime() == duration) {

				int timeRemaining = 0;

				while (timeRemaining < step.getTime()) {

					if (progresive) {
						wo.getSteps().add(new Step(step.getPower(), 0, 2 * interval / 3));
						wo.getSteps().add(new Step(step.getPower(), raisePower, 0, 2 * interval / 3));
						wo.getSteps().add(new Step(raisePower, step.getPower(), 0, 2 * interval / 3));
					} else {
						wo.getSteps().add(new Step(step.getPower(), 0, interval));
						wo.getSteps().add(new Step(raisePower, 0, interval));
					}

					timeRemaining += 2 * interval;
				}

				wo.getSteps().add(new Step(step.getRecoveryPower(), 0, step.getRecoveryTime()));
			} else
				wo.getSteps().add(step);
		}

		return wo;
	}

	public static WorkoutFactory getInstance() {

		if (instance == null) {
			instance = new WorkoutFactory();
		}

		return instance;
	}

	public Workout buildBernhardt(String name, int interval_1, int interval_2, int interval_3, int duration_1,
			int duration_2, int duration_3) {

		Workout wo1 = new Workout(name, interval_1, duration_1, 5 * 60, 200);
		Workout wo2 = new Workout("2", interval_2, duration_2, 5 * 60, 200);

		wo1.setWarn(Workout.thresholdWarn);
		wo1.getSteps().addAll(wo2.getSteps());

		if (interval_3 > 0) {

			Workout wo3 = new Workout("3", interval_3, duration_3, 5 * 60, 200);
			wo1.getSteps().addAll(wo3.getSteps());
		}

		// cambiar recovery power de todos por 55
		for (Step step : wo1.getSteps()) {
			if (step.getRecoveryTime() > 0) {
				step.setRecoveryPower(55);
			}
		}

		return wo1;
	}

	public Workout buildStages(String name, int series, int repeats, int timeUp, int timeDown, int recovery,
			int powerUp, int powerDown) {

		Workout wo1 = new Workout();
		;
		wo1.setName(name);
		wo1.setWarn(Workout.thresholdWarn);
		wo1.setCold(new Step(65, 40, 0, 5 * 60));

		for (int i = 0; i < repeats; i++) {
			wo1.addStep(new Step(powerUp, 0, timeUp));
			wo1.addStep(new Step(powerDown, 0, timeDown));
		}

		ArrayList<Step> steps = new ArrayList<Step>();
		steps.addAll(wo1.getSteps());

		for (int i = 1; i < series; i++) {
			wo1.addStep(new Step(Workout.DEFAULT_RECOVERY, 0, recovery));
			wo1.getSteps().addAll(steps);
		}

		return wo1;
	}

	public Workout buildLadder(String name, int series, int base, int recovery) {

		Workout wo1 = new Workout();
		;
		wo1.setName(name);
		wo1.setWarn(Workout.getWarn(base));
		wo1.setCold(new Step(65, 40, 0, 5 * 60));

		int i = 0;

		while (i < series) {
			wo1.addStep(new Step(base, 0, 4 * 60));
			wo1.addStep(new Step(Double.valueOf(base * 1.1).intValue(), 0, 3 * 60));
			wo1.addStep(new Step(Double.valueOf(base * 1.2).intValue(), 3 * 60, 1 * 60));

			i++;
			if (i < series) {
				wo1.addStep(new Step(Double.valueOf(base * 1.2).intValue(), 0, 4 * 60));
				wo1.addStep(new Step(Double.valueOf(base * 1.1).intValue(), 0, 3 * 60));
				wo1.addStep(new Step(base, 0, 1 * 60));
				i++;
			}
			wo1.addStep(new Step(Workout.DEFAULT_RECOVERY, 0, recovery));
		}

		return wo1;
	}
}
