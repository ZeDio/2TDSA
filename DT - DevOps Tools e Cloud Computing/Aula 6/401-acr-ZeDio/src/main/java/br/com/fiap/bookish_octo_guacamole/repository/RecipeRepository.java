package br.com.fiap.bookish_octo_guacamole.repository;

import br.com.fiap.bookish_octo_guacamole.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
