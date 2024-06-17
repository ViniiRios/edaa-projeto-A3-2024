/**
 * 
 */
package com.ferramentainteligente.repository;

/**
 * 
 */
import com.ferramentainteligente.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}