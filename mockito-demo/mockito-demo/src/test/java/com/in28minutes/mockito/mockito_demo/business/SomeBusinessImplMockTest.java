package com.in28minutes.mockito.mockito_demo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {

	@Mock
	private DataService dataServiceMock;
	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void testFindGreatestBasicScenario() {
//		Replaced by @Mock & @InjectMocks		
//		DataService dataServiceMock = mock(DataService.class);
//		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);

		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 25, 15, 5 });

		int result = businessImpl.findTheGreatestFromAllData();

		assertEquals(25, result);
	}

	@Test
	void testEmptyArray() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});

		int result = businessImpl.findTheGreatestFromAllData();

		assertEquals(Integer.MIN_VALUE, result);
	}

}
