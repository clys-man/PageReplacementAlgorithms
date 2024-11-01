package me.clysman.unifor.algorithms;

public interface IPageReplacementAlgorithm {
    void execute();
    int getPageFaults();
    int getPageHits();
}

