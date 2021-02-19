package be.vdab.luigi.controllers;

import be.vdab.luigi.domain.Adres;
import be.vdab.luigi.domain.Persoon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
//Controller schrijf je bij een controller die samenwerkt met een thymeleaf pagina
//Nu kan spring een bean maken van deze class
@RequestMapping("/")
class IndexController {
    private final AtomicInteger aantalKeerBekeken = new AtomicInteger();

    private String boodschap() {
        int uur = LocalTime.now().getHour();
        if (uur < 12) {
            return "morgen";
        }
        if (uur < 18) {
            return "middag";
        }
        return "avond";
    }
    @GetMapping
//    de kleur waarde wordt meegegeven in de parameter
    public ModelAndView index(@CookieValue(name = "kleur", required = false) String kleur) {
            ModelAndView modelAndView = new ModelAndView("index", "boodschap", boodschap());
            modelAndView.addObject("zaakvoerder", new Persoon("John", "Johnson",
                    0, false, LocalDate.of(1950,1,1), new Adres("Meir", "2000", 2000, "Antwerpen")));
//    de kleur adden in een object zodat deze bruikbaar wordt in deze controller
            modelAndView.addObject("kleur", kleur);
//    incrementAndGet verhoogt de teller in AtomicInteger op een threadSafeManier
            modelAndView.addObject("aantalKeerBekeken", aantalKeerBekeken.incrementAndGet());
            return modelAndView;

    }
//De controller bevat verwijzingen naar de thymeleaf pagina en kan op die manier data doorgeven naar th.
//Deze data kunnen objecten zijn, variabelen, lijsten, arrays, ...


}