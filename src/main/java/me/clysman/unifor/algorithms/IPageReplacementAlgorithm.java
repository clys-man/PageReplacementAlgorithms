package me.clysman.unifor.algorithms;

import java.util.List;

public interface IPageReplacementAlgorithm {
    int execute(List<Integer> pages, int capacity);
    int getPageFaults();
    int getPageHits();
}

