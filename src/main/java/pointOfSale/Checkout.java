package pointOfSale;

public class Checkout
{
	private final Display disp;

	public Checkout(Display disp)
	{
		this.disp = disp;
	}

	public void onBarcode(String code)
	{
		disp.show("ERROR: invalid bar code");
	}
}