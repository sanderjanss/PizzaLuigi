package be.vdab.luigi.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class VanTotPrijsForm {
    @NotNull @PositiveOrZero
    private BigDecimal van;
    @NotNull @PositiveOrZero
    private BigDecimal tot;

    public VanTotPrijsForm(BigDecimal van, BigDecimal tot) {
        this.van = van;
        this.tot = tot;
    }

    // Deze 2 variabelen geven we beiden de waarde null als we willen dat er geen waarde op de th pagina te zien is

    public BigDecimal getVan() {

        return van;
    }

    public BigDecimal getTot() {
        return tot;
    }
}
