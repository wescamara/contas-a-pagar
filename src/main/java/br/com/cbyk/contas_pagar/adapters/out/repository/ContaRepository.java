package br.com.cbyk.contas_pagar.adapters.out.repository;

import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

    Page<ContaEntity> findByDataVencimentoAndDescricao(Date dueDate, String description, Pageable pageable);

    Page<ContaEntity> findByDataVencimentoBetween(Date dateOf, Date dateUntil, Pageable pageable);
}

