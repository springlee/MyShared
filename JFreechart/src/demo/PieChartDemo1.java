package demo;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import org.jfree.ui.RefineryUtilities;

public class PieChartDemo1 extends ApplicationFrame {
	/** 
   *  
   */
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChartDemo1(String string) {
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
		JFreeChart jfreechart = ChartFactory.createPieChart("软件外包公司员工职位分布统计",
				piedataset, true, true, false);

		// 标题
		TextTitle texttitle = jfreechart.getTitle();
		texttitle.setFont(new Font("SimSun", 0, 20));

		// 图例
		LegendTitle legendTitle = jfreechart.getLegend();
		legendTitle.setItemFont(new Font("SimSun", 0, 20));
		
		// 主体
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		pieplot.setLabelFont(new Font("SimSun", 0, 20));
		pieplot.setNoDataMessage("没有数据");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.1);

		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		PieChartDemo1 piechartdemo1 = new PieChartDemo1("软件外包公司员工职位分布统计");
		piechartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo1);
		piechartdemo1.setVisible(true);
	}
}
