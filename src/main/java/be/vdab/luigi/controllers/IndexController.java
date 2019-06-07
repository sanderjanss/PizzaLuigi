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

@Controller
@RequestMapping("/")
class IndexController {
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
            modelAndView.addObject("zaakvoerder", new Persoon("Sander", "Janssens",
                    0, false, LocalDate.of(1992,7,9), new Adres("Oversneslaan", "47", 2610, "Wilrijk")));
//    de kleur adden in een object zodat deze bruikbaar wordt in deze controller
            modelAndView.addObject("kleur", kleur);
            return modelAndView;

    }



}