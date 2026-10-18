# language: es

Característica: Miembros de un grupo para repartir gastos

  Regla: Una persona no puede estar repetida en un mismo grupo

    Escenario: No puedo crear un grupo donde la misma persona figura dos veces
      Cuando el usuario intenta crear un grupo con los miembros 'pablo' y 'pablo'
      Entonces el grupo no se crea

    Escenario: Puedo crear un grupo con personas distintas
      Cuando el usuario intenta crear un grupo con los miembros 'pablo' y 'maria'
      Entonces el grupo se crea
