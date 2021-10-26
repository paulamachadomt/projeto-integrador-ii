package com.usj.minhamorada.repositories;

import com.usj.minhamorada.models.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssembleiaRepository extends JpaRepository <Assembleia, Long> {

}
