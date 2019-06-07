package be.vdab.luigi.controllers;

import be.vdab.luigi.domain.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("pizzas")
public class PizzaController {
//    Hier worden de controller methods van de templates geschreven.

    private final Pizza[] pizzas = {
            new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false)};

//    pizzas en pizza ZIJN 2 METHODES van type ModelAndView die GET requests verwerken
//    pizzas werkt samen met pizzas.html en pizza werkt samen met pizza.htm
//    handig om namen methode en html page overeen te laten komen
//    Dit is een URI TEMPLATE zonder path variabele
    @GetMapping
    public ModelAndView pizzas(){
        return new ModelAndView("pizzas", "pizzas", pizzas);
    }

//    DIT IS EEN URI TEMPLATE WAAR path variabele id MEEGEGEVEN WORDT
//    Je kan ook meerdere path variabelen hebben
    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("pizza");
        Arrays.stream(pizzas).filter(pizza -> pizza.getId() == id).findFirst()
                .ifPresent(pizza -> modelAndView.addObject("pizza", pizza));
        return modelAndView;
    }
    


}
