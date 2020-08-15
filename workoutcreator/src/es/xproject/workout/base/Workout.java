package es.xproject.workout.base;

import java.util.ArrayList;
import java.util.HashMap;

public class Workout {

	private static final Integer ZR = -1;
	private static final Integer Z0 = 0;
	private static final Integer Z1 = 1;
	private static final Integer Z2 = 2;
	private static final Integer Z3 = 3;
	private static final Integer Z4 = 4;
	private static final Integer Z5 = 5;
	private static final Integer Z6 = 6;
	private static final Integer Z7 = 7;
	private static final Integer Z8 = 8;

	public static final Double LEVEL_COLDDOWN = 39.99D;
	public static final Double LEVEL_RECOVERY = 60D;
	public static final Double LEVEL_ENDURANCE = 75D;
	public static final Double LEVEL_TEMPO = 87D;
	public static final Double LEVEL_SWEETSPOT = 94D;
	public static final Double LEVEL_THRESHOLD = 106D;
	public static final Double LEVEL_VOMAX = 120D;
	public static final Double LEVEL_ANAEROBIC = 150D;
	public static final Double LEVEL_NEUROMUSCULAR = 1000D;
	public static int DEFAULT_RECOVERY = 40;

	private String type;
	private Workout warn;
	private Step cold;
	private String name;
	private ArrayList<Step> steps;
	private boolean rest = false;
	
	private Warn warnElement;
	private Main mainElement;
	private Cold coldElement;

	public static Step defaultCold;
	public static Workout thresholdWarn;

	public Workout(boolean rest) {
		this.rest = rest;
	}

	public Workout() {
		this.steps = new ArrayList<Step>();
	}

	static {
		defaultCold = new Step();
		defaultCold.setTime(300);
		defaultCold.setPower(40);

		thresholdWarn = new Workout();
		thresholdWarn.addStep(new Step(40, 70, 0, 10 * 60));
		thresholdWarn.addStep(new Step(115, 45, 45));
		thresholdWarn.addStep(new Step(115, 60, 60));
		thresholdWarn.addStep(new Step(115, 3 * 60, 90));
	}
	
	

	public Workout(String name, int intervals, int intervalDuration, int recovery, int power) {
		init(name, intervals, intervalDuration, recovery, power, 0);
	}

	public Workout(String name, int intervals, int intervalDuration, int recovery, int power, int raise) {
		init(name, intervals, intervalDuration, recovery, power, raise);
	}

	private void init(String name, int intervals, int intervalDuration, int recovery, int power, int raise) {

		this.name = name;

		for (int i = 0; i < intervals; i++) {
			this.addStep(new Step(power, raise, recovery, intervalDuration));
		}

		warn = getWarn(power);
		cold = defaultCold;

	}

	public static Workout getWarn(int power) {

		// TODO: en función de la potencia máxima del workout debería crear un
		// calentamiento distinto
		Workout wo = new Workout();
		if (power > 120) {
			
			wo.addStep(new Step(40, 70, 0, 10 * 60));
			wo.addStep(new Step(115, 45, 45));
			wo.addStep(new Step(115, 2*60, 60));
			wo.addStep(new Step(power, 20, 10));
			wo.addStep(new Step(power, 3*60, 20));
		}
		else if (power > 100) {
			return thresholdWarn;
		}
		else if (power >80) {
			
			wo.addStep(new Step(50, 0, 180));
			wo.addStep(new Step(65, 0, 180));
			wo.addStep(new Step(75, 0, 180));
			wo.addStep(new Step(85, 300, 180));
		}
		else {
			wo.addStep(new Step(40, Double.valueOf(power*0.9).intValue(), 2*60, 6*60));
			wo.addStep(new Step(power, 90, 3*60));
		}
		
		
		
	

		return wo;
	}

	public void addStep(Step step) {
		if (steps == null)
			steps = new ArrayList<Step>();
		steps.add(step);
	}

	public Integer getTss() {

		int tss = 0;
		try {
			for (Step step : steps) {
				tss += (Float.valueOf(step.getTime()) * Float.valueOf(step.getPower())) / 3600F;
			}

			tss += (Float.valueOf(cold.getTime()) * Float.valueOf(cold.getPower())) / 3600F;

			for (Step step : warn.getSteps()) {
				tss += (Float.valueOf(step.getTime()) * Float.valueOf(step.getPower())) / 3600F;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tss;
	}

	
	public Workout getWarn() {
		return warn;
	}

	public void setWarn(Workout warn) {
		this.warn = warn;
	}

	public Step getCold() {
		return cold;
	}

	public void setCold(Step cold) {
		this.cold = cold;
	}

	public ArrayList<Step> getSteps() {
		return steps;
	}

	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Workout clone() {
		Workout wo = new Workout();
		wo.setWarn(this.getWarn());
		wo.setName(this.getName());
		wo.setCold(this.getCold());
		wo.getSteps().addAll(this.getSteps());

		return wo;
	}

	public boolean isRest() {
		return rest;
	}

	public void setRest(boolean rest) {
		this.rest = rest;
	}

	private boolean splitIntervalApply(ArrayList<Step> steps, Step step) {

		if (step.getRaisePower() > 0 && getZone(step.getPower()) != getZone(step.getRaisePower())) {

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

				next.setRecoveryTime(step.getRecoveryTime());
				next.setRecoveryPower(step.getRecoveryPower());

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

	private int getTimeRaised(Step step, Integer raisePower) {

		int result = 0;

		result = (raisePower - step.getPower()) * (step.getTime() / (step.getRaisePower() - step.getPower()));

		return result;
	}

	public static Double selectUpperPowerLimit(double percent) {

		if (percent < LEVEL_RECOVERY)
			return LEVEL_RECOVERY;
		else if (percent < LEVEL_ENDURANCE)
			return LEVEL_ENDURANCE;
		else if (percent < LEVEL_TEMPO)
			return LEVEL_TEMPO;
		else if (percent < LEVEL_SWEETSPOT)
			return LEVEL_SWEETSPOT;
		else if (percent < LEVEL_THRESHOLD)
			return LEVEL_THRESHOLD;
		else if (percent < LEVEL_VOMAX)
			return LEVEL_VOMAX;
		else if (percent < LEVEL_ANAEROBIC)
			return LEVEL_ANAEROBIC;
		else if (percent >= LEVEL_ANAEROBIC)
			return LEVEL_NEUROMUSCULAR;

		return 0D;
	}

	public static Double selectLowerPowerLimit(double percent) {

		if (percent > LEVEL_NEUROMUSCULAR)
			return LEVEL_NEUROMUSCULAR;
		else if (percent > LEVEL_ANAEROBIC)
			return LEVEL_ANAEROBIC;
		else if (percent > LEVEL_VOMAX)
			return LEVEL_VOMAX;
		else if (percent > LEVEL_THRESHOLD)
			return LEVEL_THRESHOLD;
		else if (percent > LEVEL_SWEETSPOT)
			return LEVEL_SWEETSPOT;
		else if (percent > LEVEL_TEMPO)
			return LEVEL_TEMPO;
		else if (percent > LEVEL_ENDURANCE)
			return LEVEL_ENDURANCE;
		else if (percent > LEVEL_RECOVERY)
			return LEVEL_RECOVERY;

		return LEVEL_COLDDOWN;
	}

	public Integer getType() {
		try {
			HashMap<Integer, Integer> colorMap = new HashMap<Integer, Integer>();

			colorMap = new HashMap<Integer, Integer>();
			colorMap.put(Z1, 0);
			colorMap.put(Z2, 0);
			colorMap.put(Z3, 0);
			colorMap.put(Z4, 0);
			colorMap.put(Z5, 0);
			colorMap.put(Z6, 0);
			colorMap.put(Z7, 0);
			colorMap.put(Z8, 0);

			ArrayList<Step> paintableSteps = new ArrayList<Step>();

			if (steps!=null) {
				for (Step step : steps) {
					if (!splitIntervalApply(paintableSteps, step)) {
						paintableSteps.add(step);
					}
				}
			}

			long totalLength = 0;

			for (Step step : paintableSteps) {

				if (step.isRampLoop())
					return Z0;

				totalLength += step.getTime();

				colorMap.put(getZone(step.getPower()), colorMap.get(getZone(step.getPower())) + step.getTime());
			}

			float type = colorMap.get(Z8);

			if (type / totalLength > 0.05)
				return Z8;

			type += colorMap.get(Z7);

			if (type / totalLength > 0.08)
				return Z7;

			type += colorMap.get(Z6);

			if (type / totalLength > 0.1)
				return Z6;

			type += colorMap.get(Z5);

			if (type / totalLength > 0.12)
				return Z5;

			type += colorMap.get(Z4);

			if (type / totalLength > 0.20)
				return Z4;

			type += colorMap.get(Z3);

			if (type / totalLength > 0.25)
				return Z3;

			type += colorMap.get(Z2);

			if (type / totalLength > 0.3) {
				return getTss() > 35 ? Z2 : Z1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ZR;

	}

	private Integer getZone(Integer percent) {
		if (percent <= LEVEL_RECOVERY)
			return Z1;
		else if (percent <= LEVEL_ENDURANCE)
			return Z2;
		else if (percent <= LEVEL_TEMPO)
			return Z3;
		else if (percent <= LEVEL_SWEETSPOT)
			return Z4;
		else if (percent <= LEVEL_THRESHOLD)
			return Z5;
		else if (percent <= LEVEL_VOMAX)
			return Z6;
		else if (percent <= LEVEL_ANAEROBIC)
			return Z7;
		else if (percent > LEVEL_ANAEROBIC)
			return Z8;
		return 0;
	}

	public void setType(String type) {
		this.type=type;
		
	}

	public void setWarnElement(Warn warnElement) {
		this.warnElement = warnElement;
		
	}
	
	public void setColdElement(Cold coldElement) {
		this.coldElement = coldElement;
		
	}
	
	public void setMainElement(Main mainElement) {
		this.mainElement = mainElement;
		
	}

	public Warn getWarnElement() {
		return warnElement;
	}

	public Main getMainElement() {
		return mainElement;
	}

	public Cold getColdElement() {
		return coldElement;
	}

}
