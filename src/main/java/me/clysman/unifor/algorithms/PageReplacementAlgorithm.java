package me.clysman.unifor.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PageReplacementAlgorithm {
    private final List<Integer> pages;
    private final int capacity;
    private final List<Step> steps = new ArrayList<>();

    public PageReplacementAlgorithm(List<Integer> pages, int capacity) {
        this.pages = pages;
        this.capacity = capacity;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void addStep(StepType type, Collection<Integer> memoryState, int page) {
        steps.add(new Step(type, new ArrayList<>(memoryState), page));
    }

    public int getPageFaults() {
        return (int) steps.stream().filter(step -> step.getType() == StepType.MISS).count();
    }

    public abstract void execute();
}
