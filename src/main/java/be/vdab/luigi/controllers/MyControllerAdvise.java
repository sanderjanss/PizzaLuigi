package be.vdab.luigi.controllers;

import be.vdab.luigi.sessions.Identificatie;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvise {
    private final Identificatie identificatie;

    public MyControllerAdvise(Identificatie identificatie) {
        this.identificatie = identificatie;
    }

    @ModelAttribute
    void extraDataToevoegenAanModel(Model model){
        model.addAttribute(identificatie);
    }
}
