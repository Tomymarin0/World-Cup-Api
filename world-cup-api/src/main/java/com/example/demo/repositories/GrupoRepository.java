package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.GrupoModel;

@Repository
public interface GrupoRepository extends CrudRepository<GrupoModel, Long>{
	public abstract GrupoModel findByGrupo(String grupo);

}
