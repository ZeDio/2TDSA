package br.com.fiap.bookish_octo_guacamole.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.fiap.bookish_octo_guacamole.model.Recipe;
import br.com.fiap.bookish_octo_guacamole.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class RecipeServiceTest {
  @Mock
  RecipeRepository repository;

  @InjectMocks
  RecipeService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void listRecipeTest() {
    List<Recipe> fakeList = List.of(
            new Recipe(),
            new Recipe()
    );
    when(repository.findAll()).thenReturn(fakeList);

    List<Recipe> allRecipes = service.getAllRecipes();

    assertEquals(2, allRecipes.size());
  }
}
