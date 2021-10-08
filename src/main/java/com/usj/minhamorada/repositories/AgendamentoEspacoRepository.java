package com.usj.minhamorada.repositories;

import com.usj.minhamorada.models.AgendamentoEspaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoEspacoRepository extends JpaRepository <AgendamentoEspaco, Long> {


}
