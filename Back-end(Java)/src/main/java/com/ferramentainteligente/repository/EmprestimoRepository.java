/**
 * 
 */
package com.ferramentainteligente.repository;

/**
 * 
 */

import com.ferramentainteligente.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
