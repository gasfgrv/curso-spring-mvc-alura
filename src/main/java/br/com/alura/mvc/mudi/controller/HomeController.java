package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private final PedidoRepository pedidoRepository;
			
	@Autowired
	public HomeController(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	@GetMapping
    public ModelAndView home() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("pedidos", pedidos);
        return modelAndView;
    }
	
	@GetMapping("/{status}")
    public ModelAndView porStatus(@PathVariable("status") String status, Model model) {
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		
		ModelAndView modelAndView = new ModelAndView("home");
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
        return modelAndView;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView onError() {
		return new ModelAndView("redirect:/home");
	}

}
