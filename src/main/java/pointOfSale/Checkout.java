package pointOfSale;

import java.math.BigDecimal;
import java.util.Optional;

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
		Optional<BigDecimal> price = warehouse.getPriceFromCode(code);
		if (!price.isPresent())
		{
			disp.show("ERROR: " + code + " is an invalid bar code");
		}
		else
		{
			disp.show("$ " + price.get().toEngineeringString());
		}
	}
}