package com.in28minutes.mockito.mockito_demo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SomeBusinessImplStubTest {

	@Test
	void testFindGreatestBasicScenario() {
		DataServiceStub stub = new DataServiceStub();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(stub);

		int result = businessImpl.findTheGreatestFromAllData();

		assertEquals(25, result);
	}

}

class DataServiceStub implements DataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 25, 15, 5 };
	}
}
