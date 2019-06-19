package be.vdab.luigi.repositories;

import be.vdab.luigi.domain.Pizza;
import be.vdab.luigi.exceptions.PizzaNietGevondenException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

// enkele andere imports
@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcPizzaRepository.class)
@Sql("/insertPizzas.sql")
public class JdbcPizzaRepositoryTest
        extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String PIZZAS = "pizzas";
    @Autowired
    private JdbcPizzaRepository repository;
    @Test
    public void findAantal() {
        assertThat(repository.findAantalPizzas())
                .isEqualTo(super.countRowsInTable(PIZZAS));
    }
    @Test
    public void findAll() {
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable(PIZZAS))
.extracting(pizza->pizza.getId()).isSorted();
    }
    @Test
    public void create() {
        long id = repository.create(new Pizza(0, "test2", BigDecimal.TEN, false));
        assertThat(id).isPositive();
        assertThat(super.countRowsInTableWhere(PIZZAS, "id=" + id)).isOne();
    }
    private long idVanTestPizza() {
        return super.jdbcTemplate.queryForObject(
                "select id from pizzas where naam='test'", Long.class);
    }
    @Test
    public void delete() {
        long id = idVanTestPizza();
        repository.delete(id);
        assertThat(super.countRowsInTableWhere(PIZZAS, "id=" + id)).isZero();
    }
    @Test
    public void findById() {
        assertThat(repository.findById(idVanTestPizza()).get().getNaam()).isEqualTo("test");
    }
    @Test
    public void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isEmpty();
    }
    @Test
    public void update() {
        long id = idVanTestPizza();
        Pizza pizza = new Pizza(id, "test", BigDecimal.ONE, false);
        repository.update(pizza);
        assertThat(super.jdbcTemplate.queryForObject(
                "select prijs from pizzas where id=?", BigDecimal.class, id)).isOne();
    }
    @Test
    public void updateOnbestaandePizza() {
        assertThatExceptionOfType(PizzaNietGevondenException.class).isThrownBy(
                () -> repository.update(new Pizza(-1, "test", BigDecimal.ONE, false)));
    }
    @Test
    public void findByPrijsBetween() {
        assertThat(repository.findByPrijsBetween(BigDecimal.ONE, BigDecimal.TEN))
                .hasSize(super.countRowsInTableWhere(PIZZAS, "prijs between 1 and 10"))
                .allSatisfy(pizza ->
                        assertThat(pizza.getPrijs()).isBetween(BigDecimal.ONE, BigDecimal.TEN))
                .extracting(pizza->pizza.getPrijs()).isSorted();
    }
    @Test
    public void findUniekePrijzenGeeftPrijzenOplopend() {
        assertThat(repository.findUniekePrijzen())
                .hasSize(super.jdbcTemplate.queryForObject(
                        "select count(distinct prijs) from pizzas", Integer.class))
                .doesNotHaveDuplicates()
.isSorted();
    }
    @Test
    public void findByPrijs() {
        assertThat(repository.findByPrijs(BigDecimal.TEN))
                .hasSize(super.countRowsInTableWhere(PIZZAS, "prijs=10"))
                .extracting(pizza->pizza.getNaam().toLowerCase()).isSorted();
    }
    @Test
    public void findByIds() {
        long id = idVanTestPizza();
        assertThat(repository.findByIds(Collections.singleton(id)))
                .extracting(pizza->pizza.getId()).containsOnly(id);
    }
    @Test
    public void findByIdsGeeftLegeVerzamelingPizzasBijLegeVerzamelingIds() {
        assertThat(repository.findByIds(Collections.emptySet())).isEmpty();
    }
    @Test
    public void findByIdsGeeftLegeVerzamelingPizzasBijOnbestaandeIds() {
        assertThat(repository.findByIds(Collections.singleton(-1L))).isEmpty();
    }
}