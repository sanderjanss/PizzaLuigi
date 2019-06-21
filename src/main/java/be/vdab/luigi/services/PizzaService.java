package be.vdab.luigi.services;

import be.vdab.luigi.domain.Pizza;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaService {
    long create(Pizza pizza);
    void update(Pizza pizza);
    void delete(long id);
    List<Pizza> findAll();
    Optional<Pizza> findById(long id);
    List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot);
    long findAantalPizzas();
    List<BigDecimal> findUniekePrijzen();
    List<Pizza> findByPrijs(BigDecimal prijs);
}

//Dit is een interface die geinjecteerd wordt als dependency naar een controller. Daar wordt er een bean van gemaakt eenmaal de website start
//Deze bean wordt in de constructor gestopt en op deze manier kan je de methodes ook in de controller gebruiken
