package pointOfSale;

import java.math.BigDecimal;
import java.util.Optional;

public interface Warehouse
{
	Optional<BigDecimal> getPriceFromCode(String validBarcode);
}