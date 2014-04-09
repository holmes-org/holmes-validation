package org.holmes;

import org.holmes.resolver.SimpleMessageResolver;
import org.holmes.resolver.StringFormatterMessageResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HolmesFactoryTest {

	private HolmesFactory factory;

	@Before
	public void setUp() {

		this.factory = new HolmesFactory();
	}

	@Test
	public void testDefaultEngineCreation() {
		
		HolmesEngine e = factory.createEngine();
		
		Assert.assertEquals(OperationMode.GREEDY.getResultCollector().getClass(), e.getCollector().getClass());
		Assert.assertNull(e.getDefaultViolationDescriptor());
		Assert.assertEquals(SimpleMessageResolver.class, e.getMessageResolver().getClass());
	}
	
	@Test
	public void testEngineCreation() {
		
		factory.withDefaultViolationDescriptor("some.violation.descriptor");
		factory.withMessageResolver(new StringFormatterMessageResolver());
		factory.withOperationMode(OperationMode.SILENT);
		
		HolmesEngine e = factory.createEngine();
		
		Assert.assertEquals(OperationMode.SILENT.getResultCollector().getClass(), e.getCollector().getClass());
		Assert.assertEquals("some.violation.descriptor", e.getDefaultViolationDescriptor());
		Assert.assertEquals(StringFormatterMessageResolver.class, e.getMessageResolver().getClass());
	}
}
