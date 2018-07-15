package org.rabcobank.report.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.rabco.report.pojo.CustomerStatements;

/**
 * @author vinesh
 *
 */
public class ReportsValidationUtilTest {
	CustomerStatements stataments1;
	CustomerStatements stataments2;
	List<Integer> reference = new ArrayList<>();

	@Before
	public void setUp() {
		stataments1 = new CustomerStatements(1234, "testing1", "testing2", new BigDecimal(11), new BigDecimal(11),
				new BigDecimal(22));
		stataments2 = new CustomerStatements(1235, "testing2", "testing3", new BigDecimal(3), new BigDecimal(-1),
				new BigDecimal(3));
		reference.add(1);
		reference.add(1234);
		reference.add(2);

	}

	@Test
	public void validateDuplicateTest() {
		ReportsValidationUtil util = new ReportsValidationUtil();
		assertEquals("Duplicate", util.validateDuplicate(stataments1, reference));
		assertEquals("Unique", util.validateDuplicate(stataments2, reference));

	}

	@Test
	public void validateEndBalanceTest() {
		ReportsValidationUtil util = new ReportsValidationUtil();
		assertEquals("End Balance is Correct", util.validateEndBalance(stataments1));
		assertEquals("End balance is Wrong", util.validateEndBalance(stataments2));

	}

}
