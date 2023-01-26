package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.JugadorModel;

@Repository
public interface JugadorRepository extends CrudRepository<JugadorModel, Long>{
	public abstract ArrayList<JugadorModel> findByNacionalidad(String nacionalidad);
	public abstract JugadorModel findPlayerById(Long id);
}


