package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/oferta")
public class OfertasController {

    @GetMapping
    public ModelAndView getFormularioParaOrferta() {
        return new ModelAndView("oferta/home");
    }

}
