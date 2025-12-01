package com.in28minutes.mockito.mockito_demo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ListTest {

	@Test
	void simpleTest() {
		List listMock = mock(List.class);

		when(listMock.size()).thenReturn(3);

		assertEquals(3, listMock.size());
	}

	@Test
	void multipleReturnsTest() {
		List listMock = mock(List.class);

		when(listMock.size()).thenReturn(3).thenReturn(2);

		assertEquals(3, listMock.size());
	}

	@Test
	void specificParametersTest() {
		List listMock = mock(List.class);

		when(listMock.get(0)).thenReturn("Some String");

		assertEquals("Some String", listMock.get(0));
		assertNull(listMock.get(1));
	}

	@Test
	void genericParametersTest() {
		List listMock = mock(List.class);

		when(listMock.get(Mockito.anyInt())).thenReturn("Some other String");

		assertEquals("Some other String", listMock.get(0));
		assertEquals("Some other String", listMock.get(1));
	}

}
