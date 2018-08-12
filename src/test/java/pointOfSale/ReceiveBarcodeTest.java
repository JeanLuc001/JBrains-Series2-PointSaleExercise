package pointOfSale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ReceiveBarcodeTest
{
	private Checkout sut;
	private Display disp;

	@Before
	public void init()
	{
		disp = mock(Display.class);
		sut = new Checkout(disp);
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
}