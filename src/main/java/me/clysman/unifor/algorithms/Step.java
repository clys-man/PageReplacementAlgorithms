package me.clysman.unifor.algorithms;

import java.util.Collection;

public class Step  {
    public final Collection<Integer> memoryState;
    public final StepType type;
    public final int page;

	public Step(StepType type, Collection<Integer> memoryState, int page) {
		super();
		this.memoryState = memoryState;
		this.type = type;
		this.page = page;
	}
	
	public Collection<Integer> getMemoryState() {
		return memoryState;
	}

	public StepType getType() {
		return type;
	}

	public int getPage() {
		return page;
	}

	@Override
	public String toString() {
		return "Step [memoryState=" + memoryState + ", type=" + type + ", page=" + page + "]";
	}
}
