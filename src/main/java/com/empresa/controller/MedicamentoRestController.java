package com.empresa.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@RestController
@RequestMapping("/rest/medicamento")
public class MedicamentoRestController {
	

	@Autowired
	private MedicamentoService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>>listaMedicamento(){
		List<Medicamento> lista= service.listaMedicamento();
		return ResponseEntity.ok(lista);
}
	
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Medicamento> insertaMedicamento(@RequestBody Medicamento obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			obj.setIdMedicamento(0);
			Medicamento objSalida = service.insertaActualizaMedicamento(obj);
			return ResponseEntity.ok(objSalida);
		}
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Medicamento> actualizaMedicamento(@RequestBody Medicamento obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			Optional<Medicamento> optMedicamento = service.obtenerPorId(obj.getIdMedicamento());
			if (optMedicamento.isEmpty()) {
				return ResponseEntity.noContent().build();
			}else {
				Medicamento objSalida = service.insertaActualizaMedicamento(obj);
				return ResponseEntity.ok(objSalida);	
			}
		}
	}
	
	
	
	
	@GetMapping("/id/{paramId}")
	@ResponseBody
	public ResponseEntity<Medicamento> buscaPorId(@PathVariable("paramId") int idMedicamento){
		Optional<Medicamento> optMedicamento = service.buscarPorId(idMedicamento);
		if (optMedicamento.isPresent()) {
			return ResponseEntity.ok(optMedicamento.get());
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@GetMapping("/stock/{paramStock}")
    @ResponseBody
    public ResponseEntity<List<Medicamento>> buscarPorStock(@PathVariable("paramStock") int stock) {
        List<Medicamento> lista = service.obtenerPorStock(stock);
        if (CollectionUtils.isEmpty(lista)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(lista);
        }
    }
	
	
	
	@GetMapping("/nombre/{paramNombre}")
    @ResponseBody
    public ResponseEntity<List<Medicamento>> buscarPorNombre(@PathVariable("paramNombre") String nombre) {
        List<Medicamento> lista = service.obtenerPorNombre(nombre);
        if (CollectionUtils.isEmpty(lista)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(lista);
        }
    }
	
	
	
}
