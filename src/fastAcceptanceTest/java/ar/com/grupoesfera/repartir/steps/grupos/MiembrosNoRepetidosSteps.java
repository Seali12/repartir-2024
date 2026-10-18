package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MiembrosNoRepetidosSteps extends FastCucumberSteps {

    private Grupo grupo;

    @Cuando("el usuario intenta crear un grupo con los miembros {miembros}")
    public void elUsuarioIntentaCrearUnGrupoConLosMiembros(List<String> miembros) {

        grupo = new Grupo();
        grupo.setMiembros(miembros);
    }

    @Entonces("el grupo no se crea")
    public void elGrupoNoSeCrea() {

        assertThat(grupo.estaFormado()).isFalse();
    }

    @Entonces("el grupo se crea")
    public void elGrupoSeCrea() {

        assertThat(grupo.estaFormado()).isTrue();
    }
}
