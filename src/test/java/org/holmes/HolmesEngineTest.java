package org.holmes;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.holmes.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

public class HolmesEngineTest {
	
	private static final String INT_FAILURE = "integer.failure";
	private static final String STRING_FAILURE = "string.failure";
	private static final String	BOOL_FAILURE = "boolean.failure";
	private static final String	DEFAULT_FAILURE = "default.failure";
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testGreedyMode() {
		
		expectedException.expectValidationException(INT_FAILURE, STRING_FAILURE, DEFAULT_FAILURE);
		
		HolmesEngine e = HolmesEngine.init(OperationMode.GREEDY);
		
		e.ensureThat(Integer.MAX_VALUE).isLessThan(Integer.MIN_VALUE).otherwise(INT_FAILURE);
		e.ensureThat("").isNotEmpty().otherwise(STRING_FAILURE);
		e.ensureThat(Boolean.FALSE).isFalse().otherwise(BOOL_FAILURE);
		e.ensureThat(Collections.emptyList()).isNotEmpty();
		
		e.withDefaultDescriptor(DEFAULT_FAILURE).run();
		
		fail();
	}
	
	@Test
	public void testEagerMode() {
		
		expectedException.expectValidationException(INT_FAILURE);
		
		HolmesEngine e = HolmesEngine.init(OperationMode.EAGER);
		
		e.ensureThat(Integer.MAX_VALUE).isLessThan(Integer.MIN_VALUE).otherwise(INT_FAILURE);
		e.ensureThat("").isNotEmpty().otherwise(STRING_FAILURE);
		e.ensureThat(Boolean.FALSE).isFalse().otherwise(BOOL_FAILURE);
		e.ensureThat(Collections.emptyList()).isNotEmpty();
		
		e.withDefaultDescriptor(DEFAULT_FAILURE).run();
		
		fail();
	}
	
	@Test
	public void testSilentMode() {
		
		HolmesEngine e = HolmesEngine.init(OperationMode.SILENT);
		
		e.ensureThat(Integer.MAX_VALUE).isLessThan(Integer.MIN_VALUE).otherwise(INT_FAILURE);
		e.ensureThat("").isNotEmpty().otherwise(STRING_FAILURE);
		e.ensureThat(Boolean.FALSE).isFalse().otherwise(BOOL_FAILURE);
		e.ensureThat(Collections.emptyList()).isNotEmpty();
		
		ValidationResult r = e.withDefaultDescriptor(DEFAULT_FAILURE).run();
		
		assertNotNull(r);
		assertTrue(r.hasViolations());
		assertThat(r.getViolationsDescriptors(), hasItems(INT_FAILURE, STRING_FAILURE, DEFAULT_FAILURE));
	}

}
