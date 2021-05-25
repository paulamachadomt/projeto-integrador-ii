package com.usj.minhamorada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.minhamorada.models.Apartamento;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {

}
