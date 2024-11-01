package me.clysman.unifor.algorithms;

import java.util.Collection;

public record Step(StepType type, Collection<Integer> memoryState, int page) {

	@Override
	public String toString() {
		return "Step [memoryState=" + memoryState + ", type=" + type + ", page=" + page + "]";
	}
}
