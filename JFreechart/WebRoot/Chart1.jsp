<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'Chart1.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
	<%@ page
		import="org.jfree.chart.ChartFactory,org.jfree.chart.JFreeChart,org.jfree.chart.servlet.ServletUtilities,
		org.jfree.chart.title.TextTitle,org.jfree.data.time.TimeSeries,org.jfree.data.time.Month,
		org.jfree.data.time.TimeSeriesCollection,java.awt.Font,org.jfree.chart.plot.*,
		org.jfree.chart.axis.DateAxis,java.text.SimpleDateFormat;"%>
	<%
		//流量统计时间线
		TimeSeries timeSeries = new TimeSeries(" 网站统计", Month.class);
		//时间曲线数据集合
		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		//构造数据集合
		timeSeries.add(new Month(1, 2013), 19300);
		timeSeries.add(new Month(2, 2013), 39390);
		timeSeries.add(new Month(3, 2013), 33990);
		timeSeries.add(new Month(4, 2013), 45533);
		timeSeries.add(new Month(5, 2013), 38799);
		timeSeries.add(new Month(6, 2013), 41000);
		timeSeries.add(new Month(7, 2013), 37899);
		timeSeries.add(new Month(8, 2013), 42999);
		timeSeries.add(new Month(9, 2013), 50383);
		timeSeries.add(new Month(10, 2013), 87666);
		timeSeries.add(new Month(11, 2013), 57897);
		timeSeries.add(new Month(12, 2013), 69399);
		lineDataset.addSeries(timeSeries);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("流量统计时间线","月份", "访问量", lineDataset, true, true, true);
	
		XYPlot xyplot = (XYPlot) chart.getPlot();
		Font font2 = new Font("SimSun", 10, 16); // 设定字体、类型、字号
		xyplot.getDomainAxis().setLabelFont(font2);// 相当于横轴或理解为X轴
		xyplot.getRangeAxis().setLabelFont(font2);// 相当于竖轴理解为Y轴
		DateAxis axis = (DateAxis) xyplot.getDomainAxis();//设置日期格式
		axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM")); 
		chart.getLegend().setItemFont(font2);
		//设置子标题
		TextTitle subtitle = new TextTitle("2013年度", font2);
		chart.addSubtitle(subtitle);
		//设置主标题
		chart.setTitle(new TextTitle("近期出勤情况统计",font2));
		chart.setAntiAlias(true);
		String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300,null, session);
		String graphURL = request.getContextPath()+ "/DisplayChart?filename=" + filename;
	%>

	<body>
		<img src="<%=graphURL%>" width="500" height="300" border="0">
	</body>
</html>
