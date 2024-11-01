package me.clysman.unifor;

import me.clysman.unifor.algorithms.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PageReplacementSimulatorGraphic extends JFrame {
    public PageReplacementSimulatorGraphic() {
        super("Page Replacement Algorithms Comparison");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PageReplacementSimulatorGraphic simulator = new PageReplacementSimulatorGraphic();
            simulator.run();
            simulator.setSize(800, 600);
            simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            simulator.setVisible(true);
        });
    }

    private void run() {
        List<Integer> pages = Arrays.asList(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 3);
        int capacity = 4;

        List<PageReplacementAlgorithm> algorithms = Arrays.asList(
                new FIFO(pages, capacity),
                new LRU(pages, capacity),
                new MRU(pages, capacity),
                new Clock(pages, capacity),
                new Optimal(pages, capacity)
        );

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (PageReplacementAlgorithm algorithm : algorithms) {
            algorithm.execute();
            int pageFaults = algorithm.getPageFaults();
            dataset.addValue(pageFaults, "Page Faults", algorithm.getClass().getSimpleName());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Page Replacement Algorithms Comparison",
                "Algorithm",
                "Number of Page Faults",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }
}
