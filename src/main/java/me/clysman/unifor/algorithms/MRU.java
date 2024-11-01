package me.clysman.unifor.algorithms;

import java.util.*;

public class MRU extends PageReplacementAlgorithm {

    public MRU(List<Integer> pages, int capacity) {
        super(pages, capacity);
    }

    @Override
    public void execute() {
        List<Integer> memory = new ArrayList<>();
        Integer mostRecentlyUsedPage = null;

        for (int page : this.getPages()) {
            if (!memory.contains(page)) {
                if (memory.size() == this.getCapacity()) {
                    memory.remove(mostRecentlyUsedPage);
                }

                memory.add(page);
                this.addStep(StepType.MISS, new ArrayList<>(memory), page);
            } else {
                this.addStep(StepType.HIT, new ArrayList<>(memory), page);
            }

            mostRecentlyUsedPage = page;
        }
    }
}
