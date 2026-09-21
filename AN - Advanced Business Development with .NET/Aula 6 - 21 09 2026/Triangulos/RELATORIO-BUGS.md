# Relatório de Bugs — Problema do Triângulo

A especificação do exercício é a fonte da verdade. Os defeitos abaixo foram identificados comparando o comportamento esperado das RN01–RN09 com a implementação original.

| ID | Regra | Defeito encontrado | Causa raiz | Correção |
|---|---|---|---|---|
| B01 | RN01 | `NaN` e infinitos podiam ser aceitos como lados | Ausência de validação de números finitos | `double.IsFinite` para os três lados |
| B02 | RN02 | Triângulos degenerados com soma igual eram aceitos | Comparação usava `<` em vez de `<=` | Rejeitar igualdade |
| B03 | RN02 | Uma das três desigualdades não era verificada | Faltava verificar `b + c <= a` | Adicionada terceira condição |
| B04 | RN03 | Isósceles com `a == c` era classificado como escaleno | Condição verificava somente `a == b` ou `b == c` | Adicionada comparação `a == c` |
| B05 | RN05 | Área era arredondada para 1 casa | `Math.Round(area, 1, ...)` | Alterado para 2 casas |
| B06 | RN06 | Fórmula do ângulo C usava denominador errado | Usava `2*a*c` em vez de `2*a*b` | Corrigido o denominador |
| B07 | RN07 | Retângulo dependia da ordem dos lados e de igualdade exata | Considerava somente C como ângulo reto | Maior lado passa a ser a hipotenusa e é aplicada tolerância relativa |
| B08 | RN10 | Parâmetro `c` da rota GET podia faltar e virar `0`, em vez de requisição inválida | `c` tinha valor padrão `0` | `c` passou a ser obrigatório |

## Evidências esperadas

- Suíte vermelha contra o estado original.
- Suíte verde após as correções.
- Cobertura do Core conforme os percentuais exigidos no enunciado.
- Testes de sensibilidade para pelo menos três defeitos.
