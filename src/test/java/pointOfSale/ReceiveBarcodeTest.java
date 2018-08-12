package pointOfSale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ReceiveBarcodeTest
{
	@Test
	public void when_empty_barcodestring_then_display_error_message() throws Exception
	{
		Display disp = mock(Display.class);
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		Checkout sut = new Checkout(disp);
		sut.onBarcode("");
		verify(disp).show(argument.capture());
		assertEquals("ERROR: invalid bar code", argument.getValue());
	}

	@Test
	public void when_barcodestring_is_null_then_display_error_message()
	{
		Display disp = mock(Display.class);
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		Checkout sut = new Checkout(disp);
		sut.onBarcode(null);
		verify(disp).show(argument.capture());
		assertEquals("ERROR: invalid bar code", argument.getValue());
	}
}