Por que sua configuração atende o cenário da startup?
Por conta que eles não pediram especificação minima e esta rodando o sistema suave e lizo na vm.

O que aconteceria se o número de usuários dobrasse?
Iria rodar normalmente com folga, por conta que definimos um armazenamento, numero de nucleos, e memoria ram para suportar até o triplo da capacidade de usuários.

Qual recurso você escalaria primeiro e por quê?
O espaço de armazenamento para armazenar mais dados de cliente.

Qual é a principal diferença entre esta VM local e uma instância em nuvem?
Que a vm local, ela roda localmente e tem uma resposta mais rapida que está mais perto, uma instância em nuvem tem o tempo de resposta praticamente igual, mas demora um pouquinho em questão de milesimos.

Quais vantagens você enxerga em provisionar servidores na nuvem em vez de máquinas virtuais locais?
Por conta que se acontecer algo de cair um sevirdor, tem outro rodando com o backup em outro lugar na nuvem. Se for local e acontecer alguma coisa não tem como continuar rodando por conta que vai ter parado a execução por conta de alguma coisa.