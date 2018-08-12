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
		if (code != null && !code.isEmpty())
		{
			disp.show("ERROR: " + code + " is an invalid bar code");
		}
		else
		{
			disp.show("ERROR: invalid bar code");
		}
	}
}