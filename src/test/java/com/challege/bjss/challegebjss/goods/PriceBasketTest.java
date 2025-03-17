package com.challege.bjss.challegebjss.goods;


import com.challege.bjss.challegebjss.PriceBasket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PriceBasketTest  {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void testPriceBasket_withDiscountApples() {

		String[] items = {"Apples", "Milk", "Bread"};
		PriceBasket.main(items);
		BigDecimal expectedSubtotal = new BigDecimal("3.10");
		BigDecimal expectedDiscount = new BigDecimal("10");
		BigDecimal expectedTotal = new BigDecimal("3.00");
		String expectedOutput = String
				.format("Subtotal: £%s\r\nApples 10%% off: -%sp\r\nTotal: £%s", expectedSubtotal, expectedDiscount, expectedTotal);
		assertNotNull(outContent);
		assertTrue(expectedOutput.equals(outContent.toString().trim()));
	}

	@Test
	public void testPriceBasket_withDiscountSoup() {
		String[] items = {"Soup", "Soup", "Milk", "Bread"};
		PriceBasket.main(items);
		BigDecimal expectedSubtotal = new BigDecimal("3.40");
		BigDecimal expectedDiscount = new BigDecimal("40");
		BigDecimal expectedTotal = new BigDecimal("3.00");
		String expectedOutput = String
				.format("Subtotal: £%s\r\nBread 50%% off: -%sp\r\nTotal: £%s", expectedSubtotal, expectedDiscount, expectedTotal);
		assertNotNull(outContent);
		assertTrue(expectedOutput.equals(outContent.toString()));
//		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	public void testPriceBasket_withDiscountBreadAndApple() {
		String[] items = {"Soup", "Soup", "Apples", "Bread"};
		PriceBasket.main(items);
		BigDecimal expectedSubtotal = new BigDecimal("3.10");
		BigDecimal expectedDiscountApples = new BigDecimal("10");
		BigDecimal expectedDiscountBread = new BigDecimal("40");
		BigDecimal expectedTotal = new BigDecimal("2.60");
		String expectedOutput = String
				.format("Subtotal: £%s\r\n" +
						"Apples 10%% off: -%sp\r\nBread 50%% off: -%sp\r\nTotal: £%s", expectedSubtotal, expectedDiscountApples,
						expectedDiscountBread,  expectedTotal);
		assertNotNull(outContent);
		assertTrue(expectedOutput.equals(outContent.toString()));
	}


	@Test
	public void testPriceBasket_noDiscount() {
		String[] items = {"Milk"};
		PriceBasket.main(items);
		BigDecimal expectedSubtotal = new BigDecimal("1.30");
		String expectedOutput = String.format("Subtotal: £%s\r\n(no offers available)\r\nTotal: £%s",
				expectedSubtotal, expectedSubtotal);
		assertTrue(expectedOutput.equals(outContent.toString()));
	}

	@Test
	void testEmptyInputArguments() {
		String[] emptyArgs = new String[0];
		assertThrows(IllegalArgumentException.class, () -> PriceBasket.main(emptyArgs),
				"Error: No input provided.");
	}
}