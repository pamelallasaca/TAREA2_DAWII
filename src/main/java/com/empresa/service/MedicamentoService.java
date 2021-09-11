package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {
	
	public List<Medicamento> listaMedicamento();
	public abstract Medicamento insertaActualizaMedicamento(Medicamento obj);
	public abstract Optional<Medicamento> obtenerPorId(int idMedicamento);
	
	public Optional<Medicamento> buscarPorId(int idAlumno);
	public List<Medicamento> obtenerPorStock(int stock);
	public List<Medicamento> obtenerPorNombre(String nombre);
	
	
}
