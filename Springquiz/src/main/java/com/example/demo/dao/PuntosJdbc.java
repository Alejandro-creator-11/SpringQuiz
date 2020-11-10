package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Puntos;


@Repository
public class PuntosJdbc implements PuntosDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Puntos puntos) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("insert into puntos (nombre, puntuacion) values(?,?)", puntos.getNombre(), puntos.getPuntuacion());
	}

	@Override
	public List<Puntos> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from puntos",
				(rs, rowNum) -> new Puntos(rs.getInt("id"), rs.getString("nombre"), rs.getInt("puntuacion")));
	}

}
