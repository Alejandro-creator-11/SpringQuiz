package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Puntos;

public interface PuntosDao {
	
	int save(Puntos puntos);
	List<Puntos> findAll();

}
