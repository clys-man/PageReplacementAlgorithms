package me.clysman.unifor.algorithms;

import java.util.*;

public class FIFO extends PageReplacementAlgorithm {
	
    public FIFO(List<Integer> pages, int capacity) {
		super(pages, capacity);
	}

	@Override
    public void execute() {
        Queue<Integer> memory = new LinkedList<>();

        for (int page : this.getPages()) {
            if (!memory.contains(page)) {
                if (memory.size() == this.getCapacity()) {
                    memory.poll();
                }
                memory.add(page);
                
                this.addStep(StepType.MISS, new ArrayList<Integer>(memory), page);
            } else {
                this.addStep(StepType.HIT, memory, page);
            }
        }
    }
}

