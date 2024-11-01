package me.clysman.unifor;

import me.clysman.unifor.algorithms.*;

import java.util.Arrays;
import java.util.List;

public class PageReplacementSimulator {
    public static void main(String[] args) {
        List<Integer> pages = Arrays.asList(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 3);
        int capacity = 4;

        List<PageReplacementAlgorithm> algorithms = Arrays.asList(
                new FIFO(pages, capacity),
                new LRU(pages, capacity),
                new MRU(pages, capacity),
                new Clock(pages, capacity),
                new Optimal(pages, capacity)
        );

        for (PageReplacementAlgorithm algorithm : algorithms) {
            algorithm.execute();
            System.out.println("\nAlgorithm: " + algorithm.getClass().getSimpleName());
            printSteps(algorithm);
            System.out.println("Page Faults: " + algorithm.getPageFaults() + "\n");
        }
    }

    private static void printSteps(PageReplacementAlgorithm algorithm) {
        String format = "| %-4s | %-15s | %-5s |\n";
        System.out.println("+------+-----------------+-------+");
        System.out.printf("| %4s | %-15s | %-5s |\n", "Page", "Memory State", "Type");
        System.out.println("+------+-----------------+-------+");

        for (Step step : algorithm.getSteps()) {
            String memory = step.getMemoryState().toString();
            System.out.printf(format, step.getPage(), memory, step.getType());
        }

        System.out.println("+------+-----------------+-------+");
    }
}
