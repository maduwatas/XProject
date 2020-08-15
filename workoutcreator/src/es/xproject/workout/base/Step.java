package es.xproject.workout.base;

import java.util.ArrayList;

public class Step extends WorkElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8317553295782841780L;
	Integer power;
	Integer recoveryPower;
	Integer recoveryTime;
	Integer raisePower;
	Integer time;
	boolean rampLoop;
	Integer repeat;
	ArrayList<Step> steps;

	public void addStep(Step step) {
		if (steps == null)
			steps = new ArrayList<Step>();
		steps.add(step);
	}

	public Step() {
		init(0, 0, 0, 0);
	}

	public Step(Integer power, Integer recovery, Integer duration) {
		init(power, 0, recovery, duration);
	}

	public Step(Integer power, Integer raisePower, Integer recovery, Integer duration) {
		init(power, raisePower, recovery, duration);
	}

	private void init(Integer power, int raisePower, Integer recovery, Integer duration) {
		this.power = power;
		this.repeat = 1;
		this.raisePower = raisePower;
		this.recoveryTime = recovery;
		this.time = duration;
		this.recoveryPower = Workout.DEFAULT_RECOVERY;

	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getRecoveryTime() {
		return recoveryTime;
	}

	public void setRecoveryTime(Integer recoveryTime) {
		this.recoveryTime = recoveryTime;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer duration) {
		this.time = duration;
	}

	public ArrayList<Step> getSteps() {
		return steps;
	}

	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}

	public Integer getRaisePower() {
		return raisePower;
	}

	public void setRaisePower(Integer raisePower) {
		this.raisePower = raisePower;
	}

	public boolean isRampLoop() {
		return rampLoop;
	}

	public void setRampLoop(boolean rampLoop) {
		this.rampLoop = rampLoop;
	}

	public Integer getRecoveryPower() {
		return recoveryPower;
	}

	public void setRecoveryPower(Integer recoveryPower) {
		this.recoveryPower = recoveryPower;
	}

	public Integer getRepeat() {
		return repeat;
	}

	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}

}
