package br.com.fiap.bookish_octo_guacamole.service;

import br.com.fiap.bookish_octo_guacamole.model.Recipe;
import br.com.fiap.bookish_octo_guacamole.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }
}
