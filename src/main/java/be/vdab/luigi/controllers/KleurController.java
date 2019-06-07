package be.vdab.luigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("kleuren")
public class KleurController {
    @GetMapping
    public ModelAndView toonPagina(@CookieValue(name = "kleur", required = false) String kleur) {
            return new ModelAndView("kleuren", "kleur", kleur);
    }
        @GetMapping("{kleur}")
        public ModelAndView kiesKleur(@PathVariable String kleur,
        HttpServletResponse response) {
            Cookie cookie = new Cookie("kleur", kleur);
            cookie.setMaxAge(31_536_000);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new ModelAndView("kleuren");
        }

}
