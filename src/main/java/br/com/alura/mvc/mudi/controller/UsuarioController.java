package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/usuario/pedidos")
public class UsuarioController {

	private final PedidoRepository pedidoRepository;

	@Autowired
	public UsuarioController(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	@GetMapping
	public ModelAndView home(Principal principal) {
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());

		ModelAndView modelAndView = new ModelAndView("usuario/home");
		modelAndView.addObject("pedidos", pedidos);
		return modelAndView;
	}

	@GetMapping("/{status}")
	public ModelAndView porStatus(@PathVariable("status") String status) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		StatusPedido statusPedido = StatusPedido.valueOf(status.toUpperCase());
		List<Pedido> pedidos = pedidoRepository.findByStatusEUsuario(statusPedido, username);

		ModelAndView modelAndView = new ModelAndView("usuario/home");
		modelAndView.addObject("pedidos", pedidos);
		modelAndView.addObject("status", status);
		return modelAndView;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView onError() {
		return new ModelAndView("redirect:usuario/home");
	}

}
