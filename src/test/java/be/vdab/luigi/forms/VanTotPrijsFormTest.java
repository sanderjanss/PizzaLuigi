package be.vdab.luigi.forms;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class VanTotPrijsFormTest {
    private static final BigDecimal MIN_EEN = BigDecimal.valueOf(-1);
    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void vanOk() {
        assertThat(validator.validateValue(
                VanTotPrijsForm.class, "van", BigDecimal.ONE)).isEmpty();
    }

    @Test
    public void vanMoetIngeVuldZijn() {
        assertThat(validator.validateValue(VanTotPrijsForm.class, "van", null))
                .isNotEmpty();
    }

    @Test
    public void vanMoetMinstensNulZijn() {
        assertThat(validator.validateValue(VanTotPrijsForm.class, "van", MIN_EEN)).isNotEmpty();
    }

    @Test
    public void totOk() {
        assertThat(validator.validateValue(
                VanTotPrijsForm.class, "tot", BigDecimal.ONE)).isEmpty();
    }

    @Test
    public void totMoetIngeVuldZijn() {
        assertThat(validator.validateValue(
                VanTotPrijsForm.class, "tot", null)).isNotEmpty();
    }

    @Test
    public void totnMoetMinstensNulZijn() {
        assertThat(validator.validateValue(VanTotPrijsForm.class, "tot", MIN_EEN))
                .isNotEmpty();
    }
}
