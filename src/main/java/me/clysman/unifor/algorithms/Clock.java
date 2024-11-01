package me.clysman.unifor.algorithms;

import java.util.*;

public class Clock extends PageReplacementAlgorithm {
    
    public Clock(List<Integer> pages, int capacity) {
        super(pages, capacity);
    }

    @Override
    public void execute() {
        List<Integer> memory = new ArrayList<>(Collections.nCopies(this.getCapacity(), -1));
        Map<Integer, Boolean> referenceBits = new HashMap<>();
        int pointer = 0;

        for (int page : this.getPages()) {
            if (!memory.contains(page)) {
                if (memory.get(pointer) != -1 && memory.size() == this.getCapacity()) {
                    while (referenceBits.getOrDefault(memory.get(pointer), false)) {
                        referenceBits.put(memory.get(pointer), false);
                        pointer = (pointer + 1) % this.getCapacity();
                    }
                    
                    referenceBits.remove(memory.get(pointer));
                    memory.set(pointer, page);
                } else {
                    memory.set(pointer, page);
                }
                
                referenceBits.put(page, true);
                pointer = (pointer + 1) % this.getCapacity();
                this.addStep(StepType.MISS, new ArrayList<>(memory), page);
            } else {
                referenceBits.put(page, true);
                this.addStep(StepType.HIT, new ArrayList<>(memory), page);
            }
        }
    }
}
