package be.vdab.luigi.controllers;

import be.vdab.luigi.services.EuroService;
import be.vdab.luigi.services.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("pizzas")
public class PizzaController {
//    Hier worden de controller methods van de templates geschreven.
    private final EuroService euroService;
    private final PizzaService pizzaService;

    PizzaController(EuroService euroService, PizzaService pizzaService) {
        this.pizzaService = pizzaService;
        this.euroService = euroService;
    }

//    private List<BigDecimal> uniekePrijzen() {
//        return Arrays.stream(pizzas).map(pizza -> pizza.getPrijs())
//                .distinct().sorted().collect(Collectors.toList());
//    }
    @GetMapping("prijzen")
    public ModelAndView prijzen() {
        return new ModelAndView("prijzen", "prijzen",
                pizzaService.findUniekePrijzen());
    }
    @GetMapping("prijzen/{prijs}")
    public ModelAndView pizzasMetEenPrijs(@PathVariable BigDecimal prijs) {
        return new ModelAndView("prijzen",
                "pizzas", pizzaService.findByPrijs(prijs))
                .addObject("prijzen", pizzaService.findUniekePrijzen());
    }

    //----> ARRAY WORDT NIET MEER GEBRUIKT, CONNECTIE MET DE DATABASE
//    private final Pizza[] pizzas = {
//            new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
//            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
//            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false)};

//    pizzas en pizza ZIJN 2 METHODES van type ModelAndView die GET requests verwerken
//    pizzas werkt samen met pizzas.html en pizza werkt samen met pizza.html
//    handig om namen methode en html page overeen te laten komen
//    Dit is een URI TEMPLATE zonder path variabele
    @GetMapping
    public ModelAndView pizzas(){
        return new ModelAndView("pizzas", "pizzas", pizzaService.findAll());
        //    viewName: staat voor de html / Thymeleaf pagina
        //    modelName: deze naam spreek je aan in de Thymeleaf pagina als -> th:object="${pizza}"
        //    variabele: staat voor de variabele die je er in steekt, de array pizzas in dit geval
    }

//    DIT IS EEN URI TEMPLATE WAAR path variabele id MEEGEGEVEN WORDT
//    Je kan ook meerdere path variabelen hebben
    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("pizza");
        pizzaService.findById(id).ifPresent(pizza -> {
        modelAndView.addObject(pizza);
        modelAndView.addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));

    });
        return modelAndView;
    }



}
