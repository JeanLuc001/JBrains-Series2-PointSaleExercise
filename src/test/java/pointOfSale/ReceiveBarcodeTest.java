package pointOfSale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

	private void simulateScanningValidBarcode(String validBarcode, String priceAsString)
	{
		when(inventory.getProductPriceFromCode(validBarcode)).thenReturn(Optional.of(new Price(priceAsString)));
		sut.onBarcode(validBarcode);
	}

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
		simulateScanningValidBarcode(validBarcode, priceAsString);
		verify(disp).show(argument.capture());
		String expectedMessage = new Price(priceAsString).toString();
		assertEquals(expectedMessage, argument.getValue());
	}

	@Test
	public void when_barcode_is_valid_then_get_total_amount() throws Exception
	{
		simulateScanningValidBarcode("063491028120", "11.50");
		assertEquals(sut.getTotal(), new Price("11.50"));
	}

	@Test
	public void when_multiple_barcodes_then_get_total_amount() throws Exception
	{
		simulateScanningValidBarcode("063491028120", "11.50");
		simulateScanningValidBarcode("123491028120", "5.99");
		assertEquals(sut.getTotal(), new Price("17.49"));
	}
}