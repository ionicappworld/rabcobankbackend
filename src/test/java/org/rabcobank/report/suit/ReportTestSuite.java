package org.rabcobank.report.suit;

/**
 * @author vinesh
 *
 */
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({ "org.rabcobank.report.business", "org.rabcobank.report.helper", "org.rabcobank.report.utils" })
public class ReportTestSuite {

}
