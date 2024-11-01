package me.clysman.unifor.algorithms;

import java.util.*;

public class LRU extends PageReplacementAlgorithm {

    public LRU(List<Integer> pages, int capacity) {
        super(pages, capacity);
    }

    @Override
    public void execute() {
        LinkedHashSet<Integer> memory = new LinkedHashSet<>();

        for (int page : this.getPages()) {
            if (!memory.contains(page)) {
                if (memory.size() == this.getCapacity()) {
                    int leastRecentlyUsed = memory.iterator().next();
                    memory.remove(leastRecentlyUsed);
                }
                
                memory.add(page);
                
                this.addStep(StepType.MISS, new ArrayList<>(memory), page);
            } else {
                memory.remove(page);
                memory.add(page);
                
                this.addStep(StepType.HIT, new ArrayList<>(memory), page);
            }
        }
    }
}
