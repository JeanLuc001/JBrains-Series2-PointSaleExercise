package pointOfSale;

import java.math.BigDecimal;
import java.util.Optional;

public class Checkout
{
	private final Display disp;
	private final Inventory inventory;

	public Checkout(Display disp)
	{
		this(disp, null);
	}

	public Checkout(Display disp, Inventory inventory)
	{
		this.disp = disp;
		this.inventory = inventory;
	}

	public void onBarcode(String code)
	{
		Optional<BigDecimal> price = inventory.getProductPriceFromCode(code);
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