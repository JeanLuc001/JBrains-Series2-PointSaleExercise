package pointOfSale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

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
		sut = new Checkout(disp,warehouse);
	}

	private ArgumentCaptor<String> captureDisplayedMessage(String code)
	{
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		sut.onBarcode(code);
		verify(disp).show(argument.capture());
		return argument;
	}

	@Test
	public void when_empty_barcodestring_then_display_error_message() throws Exception
	{
		ArgumentCaptor<String> argument = captureDisplayedMessage("");
		assertEquals("ERROR: invalid bar code", argument.getValue());
	}

	@Test
	public void when_barcodestring_is_null_then_display_error_message()
	{
		ArgumentCaptor<String> argument = captureDisplayedMessage(null);
		assertEquals("ERROR: invalid bar code", argument.getValue());
	}

	@Test
	public void when_barcodestring_has_wrong_format_then_display_error_message()
	{
		String invalidBarcode = "124a45";
		ArgumentCaptor<String> argument = captureDisplayedMessage(invalidBarcode);
		assertEquals("ERROR: " + invalidBarcode + " is an invalid bar code", argument.getValue());
	}

	@Test
	public void when_barcode_is_valid_then_show_product_price() throws Exception
	{
		String validBarcode = "063491028120";
		String priceAsString = "11.50";
		when(warehouse.getPriceFromCode(validBarcode)).thenReturn(new BigDecimal(priceAsString));
		ArgumentCaptor<String> argument = captureDisplayedMessage(validBarcode);
		BigDecimal price = new BigDecimal(priceAsString);
		String expectedMessage = "$ " + price.toString();
		assertEquals(expectedMessage, argument.getValue());
	}
}