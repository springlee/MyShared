package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo4 extends ApplicationFrame {
	/** 
   *  
   */
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChartDemo4(String title) {
		super(title);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(10.0, "加班次数", "第一季度");
		dataset.addValue(15.0, "加班次数", "第二季度"); 
		dataset.addValue(13.0, "加班次数", "第三季度");
		dataset.addValue(9.0, "加班次数", "第四季度");
		dataset.addValue(2.0, "缺勤次数", "第一季度");
		dataset.addValue(3.0, "缺勤次数", "第二季度");
		dataset.addValue(2.0, "缺勤次数", "第三季度");
		dataset.addValue(3.0, "缺勤次数", "第四季度");
		JFreeChart chart = ChartFactory.createBarChart("个人出勤记录统计", title,"次数",dataset,PlotOrientation.VERTICAL, 
		true, 
		true, 
		false 
		);
		
		//标题
	    TextTitle texttitle = chart.getTitle();
	    texttitle.setFont(new Font("SimSun", 0, 20));
	    CategoryPlot plot = chart.getCategoryPlot();// 获得图表区域对象
	    // 设置图表的纵轴和横轴org.jfree.chart.axis.CategoryAxis
	    BarRenderer  renderer  = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, Color.gray);
		renderer.setSeriesPaint(1, Color.orange);
		renderer.setDrawBarOutline(false); 
		renderer.setItemMargin(0.0); 
	    CategoryAxis domainAxis = plot.getDomainAxis();
	    NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
	    domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
	    domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
	    numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
	    numberaxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
	    chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
	    //图例
	    LegendTitle legendTitle=chart.getLegend();
	    legendTitle.setItemFont(new Font("SimSun", 0, 20));
		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
		}
	public static void main(String[] args) {
		PieChartDemo4 demo = new PieChartDemo4("个人出勤记录统计");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		}
}
