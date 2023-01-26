package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.models.PartidoModel;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface PartidoRepository extends CrudRepository <PartidoModel, Long>{
	public abstract ArrayList<PartidoModel> findByInstancia(String instancia);
}
