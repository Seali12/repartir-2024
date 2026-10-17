# language: es

Característica: Total de un grupo para repartir gastos

  Regla: El total de un grupo no debe ser negativo

    Escenario: Un reembolso mayor al total del grupo es rechazado
      Dado que el grupo tiene un total de 50
      Cuando se registra un reembolso de 80
      Entonces el total del grupo sigue siendo 50

    Escenario: Un reembolso igual al total del grupo deja el total en cero
      Dado que el grupo tiene un total de 50
      Cuando se registra un reembolso de 50
      Entonces el total del grupo es 0
