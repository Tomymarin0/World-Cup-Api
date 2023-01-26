package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.SeleccionModel;

@Repository
public interface SeleccionRepository extends CrudRepository<SeleccionModel, Long>{
	public abstract SeleccionModel findByPais(String pais);
}
