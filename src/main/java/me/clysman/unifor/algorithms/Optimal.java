package me.clysman.unifor.algorithms;

import java.util.*;

public class Optimal extends PageReplacementAlgorithm {

    public Optimal(List<Integer> pages, int capacity) {
        super(pages, capacity);
    }

    @Override
    public void execute() {
        List<Integer> memory = new ArrayList<>();

        for (int i = 0; i < this.getPages().size(); i++) {
            int page = this.getPages().get(i);

            if (!memory.contains(page)) {
                if (memory.size() == this.getCapacity()) {
                    int pageToReplace = findOptimalReplacement(memory, i);
                    memory.remove((Integer) pageToReplace);
                }
                
                memory.add(page);
                this.addStep(StepType.MISS, new ArrayList<>(memory), page);
            } else {
                this.addStep(StepType.HIT, new ArrayList<>(memory), page);
            }
        }
    }

    private int findOptimalReplacement(List<Integer> memory, int currentIndex) {
        Map<Integer, Integer> futureUse = new HashMap<>();

        for (int page : memory) {
            int nextUse = Integer.MAX_VALUE;

            for (int j = currentIndex + 1; j < this.getPages().size(); j++) {
                if (this.getPages().get(j) == page) {
                    nextUse = j;
                    break;
                }
            }
            futureUse.put(page, nextUse);
        }

        return Collections.max(futureUse.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
