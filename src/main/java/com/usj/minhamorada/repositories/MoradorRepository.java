package com.usj.minhamorada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usj.minhamorada.models.Morador;

@Repository
public interface MoradorRepository extends JpaRepository <Morador, Long> {


}
