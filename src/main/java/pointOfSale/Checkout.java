package pointOfSale;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checkout
{
	private final Display disp;
	private final Inventory inventory;
	private final List<Price> purchase;

	public Checkout(Display disp)
	{
		this(disp, null);
	}

	public Checkout(Display disp, Inventory inventory)
	{
		this.disp = disp;
		this.inventory = inventory;
		purchase = new ArrayList<>();
	}

	public void onBarcode(String code)
	{
		Optional<Price> oPrice = inventory.getProductPriceFromCode(code);
		if (!oPrice.isPresent())
		{
			disp.show("ERROR: " + code + " is an invalid bar code");
		}
		else
		{
			Price price = oPrice.get();
			purchase.add(price);
			disp.show(price.toString());
		}
	}

	public Price getTotal()
	{
		Price total = new Price("0.00");
		for (Price price : purchase)
		{
			total = total.add(price);
		}
		return total;
	}
}