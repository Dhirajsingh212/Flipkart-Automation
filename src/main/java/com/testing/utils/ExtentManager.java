package com.testing.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            getReport();
        }
        return extent;
    }

    public static ExtentReports getReport() {

        ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReport.html");

        reporter.config().setReportName("Flipkart report");

        reporter.config().setDocumentTitle("Flipkart Automation report");

        extent = new ExtentReports();

        extent.attachReporter(reporter);

        return extent;
    }
}
