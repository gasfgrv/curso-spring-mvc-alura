package br.com.alura.mvc.mudi.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.interceptor.InterceptadorDeAcessos;
import br.com.alura.mvc.mudi.interceptor.InterceptadorDeAcessos.Acesso;

@RestController
@RequestMapping("/api/acessos")
public class AcessosRest {

	@GetMapping
	public ResponseEntity<List<Acesso>> getAcessos() {
		return ResponseEntity.ok(InterceptadorDeAcessos.ACESSOS);
	}
	
}
