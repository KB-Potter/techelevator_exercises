package com.techelevator;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExercisesTest {
	
	Exercises exercises = new Exercises();
	
	
	/*
	 sameFirstLast([1, 2, 3]) â†’ false
	 sameFirstLast([1, 2, 3, 1]) â†’ true
	 sameFirstLast([1, 2, 1]) â†’ true
	 */
	@Test
	public void sameFirstLast() {
		assertEquals("Input: sameFirstLast(new int[]{1, 2, 3})", false, exercises.sameFirstLast(new int[]{1, 2, 3}));
		assertEquals("Input: sameFirstLast(new int[]{1, 2, 3, 1})", true, exercises.sameFirstLast(new int[]{1, 2, 3, 1}));
		assertEquals("Input: sameFirstLast(new int[]{1, 2, 1})", true, exercises.sameFirstLast(new int[]{1, 2, 1}));
	}

	/*
	 intMax(1, 2, 3) → 3
	 intMax(1, 3, 2) → 3
	 intMax(3, 2, 1) → 3
	 */
	@Test
	public void intMax() {
		assertEquals("Input: intMax(1, 2, 3)", 3, exercises.intMax(1, 2, 3));
		assertEquals("Input: intMax(1, 3, 2)", 3, exercises.intMax(1, 3, 2));
		assertEquals("Input: intMax(3, 2, 1)", 3, exercises.intMax(3, 2, 1));
		assertEquals("Input: intMax(4, 4, 1)", 4, exercises.intMax(4, 4, 1));
		assertEquals("Input: intMax(5, 3, 5)", 5, exercises.intMax(5, 2, 5));
		assertEquals("Input: intMax(3, 5, 5)", 5, exercises.intMax(3, 5, 5));
		assertEquals("Input: intMax(9, 1, 1)", 9, exercises.intMax(9, 1, 1));
		assertEquals("Input: intMax(9, 9, 9)", 9, exercises.intMax(9, 9, 9));
	}
	
	@Test
	public void frontTimes() {
		assertEquals("Input: frontTimes(\"Chocolate\", 2)", "ChoCho", exercises.frontTimes("Chocolate", 2));
		assertEquals("Input: frontTimes(\"Chocolate\", 3)", "ChoChoCho", exercises.frontTimes("Chocolate", 3));
		assertEquals("Input: frontTimes(\"Abc\", 3)", "AbcAbcAbc", exercises.frontTimes("Abc", 3));
		assertEquals("Input: frontTimes(\"Ab\", 4)", "AbAbAbAb", exercises.frontTimes("Ab", 4));
		assertEquals("Input: frontTimes(\"A\", 4)", "AAAA", exercises.frontTimes("A", 4));
		assertEquals("Input: frontTimes(\"\", 4)", "", exercises.frontTimes("", 4));
		assertEquals("Input: frontTimes(\"Abc\", 0)", "", exercises.frontTimes("Abc", 0));
	}
	
    @Test
	public void arrayInt2ListDouble() {
		String failureMessage = compareArrayLists(new ArrayList<Double>(Arrays.asList(2.5d, 4.0d, 5.5d, 100.0d, 48.5d)),
				exercises.arrayInt2ListDouble(new int[] {5, 8, 11, 200, 97}));
		if (failureMessage != null) fail("Input: arrayInt2ListDouble(new int[] {5, 8, 11, 200, 97})" + failureMessage);
	
		failureMessage = compareArrayLists(new ArrayList<Double>(Arrays.asList(372.5d, 11.5d, 22.0d, 4508.5d, 3.0d)),
				exercises.arrayInt2ListDouble(new int[] {745, 23, 44, 9017, 6}));
		if (failureMessage != null) fail("Input: arrayInt2ListDouble(new int[] {745, 23, 44, 9017, 6})" + failureMessage);
	
		failureMessage = compareArrayLists(new ArrayList<Double>(Arrays.asList(42.0d, 49.5d, 1642.5d, 6.5d, 438.5d)),
				exercises.arrayInt2ListDouble(new int[] {84, 99, 3285, 13, 877}));
		if (failureMessage != null) fail("Input: arrayInt2ListDouble(new int[] {84, 99, 3285, 13, 877})" + failureMessage);		
	}
    
    
	/*
	 * Compares two ArrayLists, and returns a null failure message if the lists are equal.
	 * Otherwise, if the lists are not equal, returns a failure message explaining why.
	 * Note, method returns with failure message on the first problem. 
	 * 
	 * Comparisons:
	 * 		Neither String ArrayList may be null.
	 * 		Both String ArrayLists must be the same length, 
	 * 			and the strings must be in the same order.
	 */
	private String compareArrayLists(List<?> expectedList, List<?> actualList) {
		String failureMessage = null;
		if ((expectedList == null) || (actualList == null)) {
			failureMessage = expectedList == null? "expectedList is null " : "";
			failureMessage = actualList == null? "actualList is null" : "";
			return failureMessage.trim();
		}
		if (expectedList.size() != actualList.size()) {
			failureMessage = "array lengths differed, expected.length=" + expectedList.size() + " actual.length=" + actualList.size();
			return failureMessage;
		}
		int i = 0;
		for (Object expectedString : expectedList) {
			if ( ! expectedString.equals(actualList.get(i))) {
				failureMessage = "array elements at index " + i + " differ, expected:<" + expectedString + "> but was:<" + actualList.get(i) + ">";
				return failureMessage;
			}
			i++;
		}
		return failureMessage;
	}

}
