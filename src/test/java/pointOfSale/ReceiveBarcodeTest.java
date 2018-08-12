package pointOfSale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReceiveBarcodeTest
{
	private Checkout sut;
	@Mock
	private Display disp;
	@Mock
	private Inventory inventory;

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		sut = new Checkout(disp, inventory);
	}

	@Test
	public void when_product_not_in_warehouse_then_show_error_message() throws Exception
	{
		String invalidBarcode = "124a45";
		when(inventory.getProductPriceFromCode(invalidBarcode)).thenReturn(Optional.empty());
		sut.onBarcode(invalidBarcode);
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		verify(disp).show(argument.capture());
		assertEquals("ERROR: " + invalidBarcode + " is an invalid bar code", argument.getValue());
	}

	@Test
	public void when_barcode_is_valid_then_show_product_price() throws Exception
	{
		String validBarcode = "063491028120";
		String priceAsString = "11.50";
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		when(inventory.getProductPriceFromCode(validBarcode)).thenReturn(Optional.of(new BigDecimal(priceAsString)));
		sut.onBarcode(validBarcode);
		verify(disp).show(argument.capture());
		BigDecimal price = new BigDecimal(priceAsString);
		String expectedMessage = "$ " + price.toString();
		assertEquals(expectedMessage, argument.getValue());
	}
	
	@Test
	public void when_barcode_is_valid_then_get_total_amount() throws Exception
	{
		String validBarcode = "063491028120";
		String priceAsString = "11.50";
		when(inventory.getProductPriceFromCode(validBarcode)).thenReturn(Optional.of(new BigDecimal(priceAsString)));
		sut.onBarcode(validBarcode);
		assertEquals(sut.getTotal(), new BigDecimal(priceAsString));
	}
	
	@Test
	public void when_multiple_barcodes_then_get_total_amount() throws Exception
	{
		String validBarcode1 = "063491028120";
		String priceAsString1 = "11.50";
		String validBarcode2 = "123491028120";
		String priceAsString2 = "5.99";
		when(inventory.getProductPriceFromCode(validBarcode1)).thenReturn(Optional.of(new BigDecimal(priceAsString1)));
		when(inventory.getProductPriceFromCode(validBarcode2)).thenReturn(Optional.of(new BigDecimal(priceAsString2)));
		sut.onBarcode(validBarcode1);
		sut.onBarcode(validBarcode2);
		assertEquals(sut.getTotal(), new BigDecimal("17.49"));
	}
}