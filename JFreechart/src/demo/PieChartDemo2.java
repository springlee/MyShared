
package com.sj.xingzhe.hxas.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.xingzhe.hxas.domain.AttendanceStatistics;
import com.sj.xingzhe.hxas.domain.RegionCompany;
import com.sj.xingzhe.hxas.domain.User;
import com.sj.xingzhe.hxas.rm.JfreeTools;
import com.sj.xingzhe.hxas.services.JFreeChartService;

@Controller
@RequestMapping("/jfree")
public class JfreeController {
	private static final Log logger = LogFactory.getLog(JfreeController.class);
	@Autowired
	private JfreeTools jfreeTools;
	@Autowired
	private JFreeChartService jFreeChartService;
	private final String JFREECHART_IMG_JSP = "jsp/jfreechart/img";

	@RequestMapping("/xiaoshou.html")
	public String xiaoshou(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(110, "长沙", "白菜");
		dataset.addValue(220, "长沙", "生菜");
		dataset.addValue(330, "长沙", "西洋菜");
		dataset.addValue(440, "长沙", "豆芽菜");
		JFreeChart chart = ChartFactory.createBarChart3D("蔬菜类销售统计图", "蔬菜类",
				"销量", dataset, PlotOrientation.VERTICAL, true, false, false);
		jfreeTools.configFont(chart);
		try {
			String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300,
					null, session);
			String graphURL = request.getContextPath()
					+ "/servlet/DisplayChart?filename=" + filename;
			model.addAttribute("filename", filename);
			model.addAttribute("graphURL", graphURL);
		} catch (IOException e) {
			logger.error(e);
		}

		return JFREECHART_IMG_JSP;

	}

	@RequestMapping("attendancestatistics.html")
	public String AttendanceStatistics(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		AttendanceStatistics[] attendanceStatistics = null;
		// 判断是否为系统管理员
		if (user.isAdmin()) {
			attendanceStatistics = getjFreeChartService()
					.getAttendanceStatistics();
		} else if (user.isAttendAdmin() || user.isPersonnelAdmin()) {// 判断是否为地域考勤员或人事部经理
			RegionCompany rc = user.getCompany();
			if (null != rc) {
				attendanceStatistics = getjFreeChartService()
						.getAttendanceStatistics(rc);
			}
		}
		TimeSeries timeSeriesPresent = new TimeSeries("出勤人数", Day.class);
		TimeSeries timeSeriesAbsence = new TimeSeries("缺勤人数", Day.class);
		TimeSeries timeSeriesTraval = new TimeSeries("出差人数", Day.class);
		TimeSeries timeSeriesLeaves = new TimeSeries("请假人数", Day.class);
		TimeSeries timeSeriesAbnormal = new TimeSeries("异常人数", Day.class);
		if (null != attendanceStatistics) {
			for (AttendanceStatistics as : attendanceStatistics) {
				timeSeriesAbnormal.add(new Day(as.getAttendDate()),
						as.getAbnormalNum());
				timeSeriesPresent.add(new Day(as.getAttendDate()),
						as.getPresentNum());
				timeSeriesAbsence.add(new Day(as.getAttendDate()),
						as.getAbsenceNum());
				timeSeriesTraval.add(new Day(as.getAttendDate()),
						as.getTravalNum());
				timeSeriesLeaves.add(new Day(as.getAttendDate()),
						as.getLeavesNum());
			}
		}
		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
		timeSeriesCollection.addSeries(timeSeriesAbnormal);
		timeSeriesCollection.addSeries(timeSeriesAbsence);
		timeSeriesCollection.addSeries(timeSeriesPresent);
		timeSeriesCollection.addSeries(timeSeriesTraval);
		timeSeriesCollection.addSeries(timeSeriesLeaves);
		timeSeriesCollection.setXPosition(TimePeriodAnchor.MIDDLE);
		JFreeChart jFreeChart = ChartFactory.createTimeSeriesChart("近期出勤情况统计",
				"Date", "人数(人)", timeSeriesCollection, true, true, true);
		XYPlot xyPlot = jFreeChart.getXYPlot();
		// 设置网格背景色
		xyPlot.setBackgroundPaint(new Color(240, 240, 240));
		// 网格竖线的颜色
		xyPlot.setDomainGridlinePaint(Color.lightGray);
		// 网格横线的颜色
		xyPlot.setRangeGridlinePaint(Color.lightGray);
		xyPlot.setAxisOffset(new RectangleInsets(5D, 0D, 5D, 5D));
		xyPlot.setDomainCrosshairVisible(true);
		xyPlot.setRangeCrosshairVisible(true);
		ValueAxis rAxis = xyPlot.getRangeAxis();
		rAxis.setUpperMargin(0.1);
		XYItemRenderer xyItemRenderer = xyPlot.getRenderer();
		if (xyItemRenderer instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) xyItemRenderer;
			xyLineAndShapeRenderer.setBaseShapesVisible(true);
			xyLineAndShapeRenderer.setBaseShapesFilled(true);
			xyLineAndShapeRenderer.setBaseItemLabelsVisible(true);
			xyLineAndShapeRenderer
					.setBasePositiveItemLabelPosition(new ItemLabelPosition(
							ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			xyLineAndShapeRenderer
					.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
			xyLineAndShapeRenderer.setBaseItemLabelFont(new Font("微软雅黑",
					Font.ITALIC, 11));
			xyLineAndShapeRenderer.setBaseItemLabelPaint(Color.gray);
		}
		PeriodAxis periodAxis = new PeriodAxis("日期");
		periodAxis.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
		periodAxis.setAutoRangeTimePeriodClass(Day.class);
		periodAxis.setMajorTickTimePeriodClass(Day.class);
		PeriodAxisLabelInfo periodAxisLabelInfo[] = new PeriodAxisLabelInfo[2];
		periodAxisLabelInfo[0] = new PeriodAxisLabelInfo(Day.class,
				new SimpleDateFormat("dd"),
				new RectangleInsets(2D, 2D, 2D, 2D), new Font("微软雅黑", 1, 10),
				Color.blue, false, new BasicStroke(0.0F), Color.lightGray);
		periodAxisLabelInfo[1] = new PeriodAxisLabelInfo(Day.class,
				new SimpleDateFormat("yyyy-MM"));
		periodAxis.setLabelInfo(periodAxisLabelInfo);
		xyPlot.setDomainAxis(periodAxis);
		jfreeTools.configTimeSeriesFont(jFreeChart);
		String filename = null;
		try {
			filename = ServletUtilities.saveChartAsPNG(jFreeChart, 1000, 600,
					null, request.getSession());
		} catch (IOException e) {
			logger.error(e);
		}
		String graphURL = request.getContextPath()
				+ "/servlet/DisplayChart?filename=" + filename;
		model.addAttribute("filename", filename);
		model.addAttribute("graphURL", graphURL);
		return JFREECHART_IMG_JSP;
	}

	public JfreeTools getJfreeTools() {
		return jfreeTools;
	}

	public void setJfreeTools(JfreeTools jfreeTools) {
		this.jfreeTools = jfreeTools;
	}

	public JFreeChartService getjFreeChartService() {
		return jFreeChartService;
	}

	public void setjFreeChartService(JFreeChartService jFreeChartService) {
		this.jFreeChartService = jFreeChartService;
	}

}
