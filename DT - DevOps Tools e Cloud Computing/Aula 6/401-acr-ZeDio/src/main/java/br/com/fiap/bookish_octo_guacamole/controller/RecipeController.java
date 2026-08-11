package br.com.fiap.bookish_octo_guacamole.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bookish_octo_guacamole.model.Recipe;
import br.com.fiap.bookish_octo_guacamole.service.RecipeService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
  private final RecipeService service;

  public RecipeController(RecipeService service) {
    this.service = service;
  }

  @GetMapping
  public List<Recipe> getAllRecipes() {
      return this.service.getAllRecipes();
  }

}
