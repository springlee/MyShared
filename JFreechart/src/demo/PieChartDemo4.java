package demo;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChartDemo4 extends ApplicationFrame { 
  /** 
   *  
   */ 
  private static final long serialVersionUID = 2598557557724085474L; 
 
  public PieChartDemo4(String string) { 
    super(string); 
    JPanel jpanel = createDemoPanel(); 
    jpanel.setPreferredSize(new Dimension(500, 270)); 
    setContentPane(jpanel); 
  } 
 
  private static XYDataset createDataset() { 
	    TimeSeries timeseries = new TimeSeries( 
	        "L&G European Index Trust"); 
	    timeseries.add(new Month(2, 2001), 181.8); 
	    timeseries.add(new Month(3, 2001), 167.3); 
	    timeseries.add(new Month(4, 2001), 153.8); 
	    timeseries.add(new Month(5, 2001), 167.6); 
	    timeseries.add(new Month(6, 2001), 158.8); 
	    timeseries.add(new Month(7, 2001), 148.3); 
	    timeseries.add(new Month(8, 2001), 153.9); 
	    timeseries.add(new Month(9, 2001), 142.7); 
	    timeseries.add(new Month(10, 2001), 123.2); 
	    timeseries.add(new Month(11, 2001), 131.8); 
	    timeseries.add(new Month(12, 2001), 139.6); 
	    timeseries.add(new Month(1, 2002), 142.9); 
	    timeseries.add(new Month(2, 2002), 138.7); 
	    timeseries.add(new Month(3, 2002), 137.3); 
	    timeseries.add(new Month(4, 2002), 143.9); 
	    timeseries.add(new Month(5, 2002), 139.8); 
	    timeseries.add(new Month(6, 2002), 137.0); 
	    timeseries.add(new Month(7, 2002), 132.8); 
	    TimeSeries timeseries_0_ = new TimeSeries( 
	        "L&G UK Index Trust"); 
	    timeseries_0_.add(new Month(2, 2001), 129.6); 
	    timeseries_0_.add(new Month(3, 2001), 123.2); 
	    timeseries_0_.add(new Month(4, 2001), 117.2); 
	    timeseries_0_.add(new Month(5, 2001), 124.1); 
	    timeseries_0_.add(new Month(6, 2001), 122.6); 
	    timeseries_0_.add(new Month(7, 2001), 119.2); 
	    timeseries_0_.add(new Month(8, 2001), 116.5); 
	    timeseries_0_.add(new Month(9, 2001), 112.7); 
	    timeseries_0_.add(new Month(10, 2001), 101.5); 
	    timeseries_0_.add(new Month(11, 2001), 106.1); 
	    timeseries_0_.add(new Month(12, 2001), 110.3); 
	    timeseries_0_.add(new Month(1, 2002), 111.7); 
	    timeseries_0_.add(new Month(2, 2002), 111.0); 
	    timeseries_0_.add(new Month(3, 2002), 109.6); 
	    timeseries_0_.add(new Month(4, 2002), 113.2); 
	    timeseries_0_.add(new Month(5, 2002), 111.6); 
	    timeseries_0_.add(new Month(6, 2002), 108.8); 
	    timeseries_0_.add(new Month(7, 2002), 101.6); 
	    TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(); 
	    timeseriescollection.addSeries(timeseries); 
	    timeseriescollection.addSeries(timeseries_0_); 
	    return timeseriescollection; 
	 } 
 
  public static JPanel createDemoPanel() { 
    JFreeChart jfreechart = createChart(createDataset()); 
    return new ChartPanel(jfreechart); 
  } 
 
  private static JFreeChart createChart(XYDataset createDataset) {
	// TODO Auto-generated method stub
	return null;
}

public static void main(String[] strings) { 
    PieChartDemo4 piechartdemo1 = new PieChartDemo4("Pie Chart Demo 1"); 
    piechartdemo1.pack(); 
    RefineryUtilities.centerFrameOnScreen(piechartdemo1); 
    piechartdemo1.setVisible(true); 
  } 
} 
