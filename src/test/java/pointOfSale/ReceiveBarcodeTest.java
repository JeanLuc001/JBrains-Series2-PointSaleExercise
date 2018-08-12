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
	private Warehouse warehouse;

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		sut = new Checkout(disp, warehouse);
	}

	@Test
	public void when_product_not_in_warehouse_then_show_error_message() throws Exception
	{
		String invalidBarcode = "124a45";
		when(warehouse.getPriceFromCode(invalidBarcode)).thenReturn(Optional.empty());
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
		when(warehouse.getPriceFromCode(validBarcode)).thenReturn(Optional.of(new BigDecimal(priceAsString)));
		sut.onBarcode(validBarcode);
		verify(disp).show(argument.capture());
		BigDecimal price = new BigDecimal(priceAsString);
		String expectedMessage = "$ " + price.toString();
		assertEquals(expectedMessage, argument.getValue());
	}
}