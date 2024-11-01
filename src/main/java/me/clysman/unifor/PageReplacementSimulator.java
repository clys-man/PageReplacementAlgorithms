package me.clysman.unifor;

import me.clysman.unifor.algorithms.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PageReplacementSimulator extends JFrame {
    public PageReplacementSimulator() {
        super("Page Replacement Simulator with Comparison");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PageReplacementSimulator simulator = new PageReplacementSimulator();
            simulator.run();
            simulator.setSize(800, 600);
            simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            simulator.setVisible(true);
        });
    }

    private void run() {
        List<Integer> pages = Arrays.asList(2, 5, 10, 1, 2, 2, 6, 9, 1, 2, 10, 2, 6, 1, 2, 1, 6, 9, 5, 1);
        int capacity = 4;

        List<PageReplacementAlgorithm> algorithms = Arrays.asList(
                new FIFO(pages, capacity),
                new LRU(pages, capacity),
                new MRU(pages, capacity),
                new Clock(pages, capacity),
                new Optimal(pages, capacity)
        );

        JTabbedPane tabbedPane = new JTabbedPane();

        for (PageReplacementAlgorithm algorithm : algorithms) {
            algorithm.execute();
            JPanel panel = createAlgorithmPanel(algorithm);
            tabbedPane.addTab(algorithm.getClass().getSimpleName(), panel);
        }

        tabbedPane.addTab("Comparison Chart", createChartPanel(algorithms));

        add(tabbedPane, BorderLayout.CENTER);
    }

    private static JPanel createAlgorithmPanel(PageReplacementAlgorithm algorithm) {
        int pageFaults = algorithm.getPageFaults();
        int pageHits = algorithm.getPageHits();
        double hitPercentage = (pageHits * 100.0) / (pageHits + pageFaults);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 3));
        infoPanel.add(new JLabel("Page Faults: " + pageFaults));
        infoPanel.add(new JLabel("Page Hits: " + pageHits));
        infoPanel.add(new JLabel(String.format("Hit Percentage: %.2f%%", hitPercentage)));

        JTable table = createTableForAlgorithm(algorithm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private static JTable createTableForAlgorithm(PageReplacementAlgorithm algorithm) {
        PageReplacementTableModel model = new PageReplacementTableModel(algorithm.getSteps());
        JTable table = new JTable(model);
        table.setDefaultRenderer(Object.class, new CellRenderer());
        return table;
    }

    private static JPanel createChartPanel(List<PageReplacementAlgorithm> algorithms) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (PageReplacementAlgorithm algorithm : algorithms) {
            int pageFaults = algorithm.getPageFaults();
            int pageHits = algorithm.getPageHits();

            dataset.addValue(pageFaults, "Page Faults", algorithm.getClass().getSimpleName());
            dataset.addValue(pageHits, "Page Hits", algorithm.getClass().getSimpleName());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Page Replacement Algorithms Comparison",
                "Algorithm",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        return chartPanel;
    }

    static class PageReplacementTableModel extends AbstractTableModel {
        private final List<Step> steps;
        private final String[] columnNames = {"Page", "Memory State", "Type"};

        public PageReplacementTableModel(List<Step> steps) {
            this.steps = steps;
        }

        @Override
        public int getRowCount() {
            return steps.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Step step = steps.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> step.page();
                case 1 -> step.memoryState();
                case 2 -> step.type();
                default -> null;
            };
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    static class CellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            StepType type = (StepType) table.getModel().getValueAt(row, 2);
            if (type == StepType.MISS) {
                cell.setBackground(Color.RED);
            } else if (type == StepType.HIT) {
                cell.setBackground(Color.GREEN);
            } else {
                cell.setBackground(Color.WHITE);
            }

            cell.setForeground(Color.BLACK);
            return cell;
        }
    }
}
