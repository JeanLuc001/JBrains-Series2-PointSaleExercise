package pointOfSale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checkout
{
	private final Display disp;
	private final Inventory inventory;
	private List<Item> purchase;
	private static class Item
	{
		private final BigDecimal price;

		public Item(BigDecimal price)
		{
			this.price = price;
		}
	}

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
		Optional<BigDecimal> oPrice = inventory.getProductPriceFromCode(code);
		if (!oPrice.isPresent())
		{
			disp.show("ERROR: " + code + " is an invalid bar code");
		}
		else
		{
			BigDecimal price = oPrice.get();
			purchase.add(new Item(price));
			disp.show("$ " + price.toString());
		}
	}

	public BigDecimal getTotal()
	{
		BigDecimal total = BigDecimal.ZERO;
		for (Item item : purchase)
		{
			total = total.add(item.price);
		}
		return total;
	}
}