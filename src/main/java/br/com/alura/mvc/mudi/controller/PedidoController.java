package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	private final PedidoRepository pedidoRepository;

	@Autowired
	public PedidoController(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	@GetMapping("/formulario")
	public ModelAndView formulario(RequisicaoNovoPedido requisicao) {
		return new ModelAndView("pedido/formulario");
	}

	@PostMapping("/novo")
	public ModelAndView novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		if (result.hasErrors())
			return new ModelAndView("pedido/formulario");

		Pedido pedido = requisicao.toPedido();
		pedidoRepository.save(pedido);

		return new ModelAndView("redirect:/home");
	}
}
