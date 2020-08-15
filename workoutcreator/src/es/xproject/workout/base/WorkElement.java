package es.xproject.workout.base;

import java.util.ArrayList;

public abstract class WorkElement extends ArrayList<Step>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7284329057096567087L;
	protected WorkElement parent;

    public WorkElement getParent() {
        return parent;
    }
    public void setParent(WorkElement parent) {
        this.parent = parent;
    }
}
