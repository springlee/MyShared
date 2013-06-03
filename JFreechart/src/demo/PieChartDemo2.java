package demo;

import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Font;
 
import javax.swing.JPanel; 
 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot; 
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset; 
import org.jfree.data.general.PieDataset; 
import org.jfree.ui.ApplicationFrame; 
 
import org.jfree.ui.RefineryUtilities; 
 
public class PieChartDemo2 extends ApplicationFrame { 
  /** 
   *  
   */ 
  private static final long serialVersionUID = 2598557557724085474L; 
 
  public PieChartDemo2(String string) { 
    super(string); 
    JPanel jpanel = createDemoPanel(); 
    jpanel.setPreferredSize(new Dimension(500, 270)); 
    setContentPane(jpanel); 
  } 
 
  private static PieDataset createDataset() { 
	  DefaultPieDataset defaultpiedataset = new DefaultPieDataset(); 
	    defaultpiedataset.setValue("集团总裁 ", new Double(3.0)); 
	    defaultpiedataset.setValue("地域考勤员 ", new Double(10.0)); 
	    defaultpiedataset.setValue("人事总经理 ", new Double(10.0)); 
	    defaultpiedataset.setValue("高级程序员", new Double(30.0)); 
	    defaultpiedataset.setValue("程序员 ", new Double(100.0)); 
	    defaultpiedataset.setValue("销售部经理 ", new Double(20.0)); 
	    defaultpiedataset.setValue("高级销售员 ", new Double(10.0)); 
	    defaultpiedataset.setValue("普通销售员 ", new Double(150.0)); 
    return defaultpiedataset; 
  } 
 
  private static JFreeChart createChart(PieDataset piedataset) { 
	    JFreeChart jfreechart = ChartFactory.createPieChart("软件外包公司员工职位分布统计", piedataset, true, true, false); 
	    PiePlot pieplot = (PiePlot) jfreechart.getPlot(); 
	    pieplot.setSectionPaint("集团总裁", new Color(160, 160, 255)); 
	    pieplot.setSectionPaint("地域考勤员", new Color(128, 128, 223)); 
	    pieplot.setSectionPaint("人事总经理", new Color(96, 96, 191)); 
	    pieplot.setSectionPaint("高级程序员", new Color(64, 64, 159)); 
	    pieplot.setSectionPaint("程序员", new Color(32, 32, 127)); 
	    pieplot.setSectionPaint("销售部经理 ", new Color(0, 0, 111)); 
	    pieplot.setSectionPaint("高级销售员 ", new Color(0, 0, 111)); 
	    pieplot.setSectionPaint("普通销售员  ", new Color(0, 0, 111)); 
	    pieplot.setExplodePercent("程序员", 0.8); 
	    pieplot.setNoDataMessage("没有数据"); 

	    pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator( 
	        "{0} ({2} percent)")); 
	    pieplot.setLabelBackgroundPaint(new Color(220, 220, 220)); 
	    pieplot.setLegendLabelToolTipGenerator(new 
	    StandardPieSectionLabelGenerator("Tooltip for legend item {0}")); 
	  //标题
	    TextTitle texttitle = jfreechart.getTitle();
	    texttitle.setFont(new Font("SimSun", 0, 20));

	    //图例
	    LegendTitle legendTitle=jfreechart.getLegend();
	    legendTitle.setItemFont(new Font("SimSun", 0, 20));
	    //主体
	    pieplot.setLabelFont(new Font("SimSun", 0, 20)); 
	    pieplot.setCircular(false); 
	    pieplot.setLabelGap(0.02); 
	    pieplot.setIgnoreZeroValues(true);
	    pieplot.setIgnoreNullValues(true);
	    return jfreechart; 
	  }
 
  public static JPanel createDemoPanel() { 
    JFreeChart jfreechart = createChart(createDataset()); 
    return new ChartPanel(jfreechart); 
  } 
 
  public static void main(String[] strings) { 
    PieChartDemo2 piechartdemo1 = new PieChartDemo2("软件外包公司员工职位分布统计"); 
    piechartdemo1.pack(); 
    RefineryUtilities.centerFrameOnScreen(piechartdemo1); 
    piechartdemo1.setVisible(true); 
  } 
} 