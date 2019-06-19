package be.vdab.luigi.services;

import be.vdab.luigi.restclients.KoersClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEuroServiceTest {
    @Mock
    private KoersClient koersClient;
    private EuroService euroService;
    @Before
    public void before() {
        when(koersClient.getDollarKoers()).thenReturn(BigDecimal.valueOf(1.5));
        euroService = new DefaultEuroService(new KoersClient[] {koersClient});
    }
    @Test
    public void naarDollar() {
        assertThat(euroService.naarDollar(BigDecimal.valueOf(2)))
                .isEqualByComparingTo("3");
        verify(koersClient).getDollarKoers();
    }
}


