package pointOfSale;

import java.math.BigDecimal;

public class Checkout
{
	private final Display disp;
	private final Warehouse warehouse;

	public Checkout(Display disp)
	{
		this(disp, null);
	}

	public Checkout(Display disp, Warehouse warehouse)
	{
		this.disp = disp;
		this.warehouse = warehouse;
	}

	public void onBarcode(String code)
	{
		BigDecimal price = warehouse.getPriceFromCode(code);
		if (price == null)
		{
			String message = isValidCodeThenNullElseErrorMessage(code);
			disp.show(message);
		}
		else
		{
			disp.show("$ " + price.toEngineeringString());
		}
	}

	private String isValidCodeThenNullElseErrorMessage(String code)
	{
		if (code != null && !code.isEmpty())
		{
			return "ERROR: " + code + " is an invalid bar code";
		}
		else if (code == null)
		{
			return "ERROR: invalid bar code";
		}
		else
		{
			return "ERROR: invalid bar code";
		}
	}
}