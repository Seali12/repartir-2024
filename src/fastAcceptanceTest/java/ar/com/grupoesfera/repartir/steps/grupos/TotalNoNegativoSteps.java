package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.exceptions.TotalNegativoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.services.MontosService;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalNoNegativoSteps extends FastCucumberSteps {

    private final MontosService montos = new MontosService();

    private Grupo grupo;

    private boolean reembolsoRechazado;

    @Dado("que el grupo tiene un total de {int}")
    public void queElGrupoTieneUnTotalDe(int total) {

        grupo = new Grupo();
        grupo.setTotal(BigDecimal.valueOf(total));
    }

    @Cuando("se registra un reembolso de {int}")
    public void seRegistraUnReembolsoDe(int monto) {

        Gasto reembolso = new Gasto();
        reembolso.setMonto(BigDecimal.valueOf(-monto));

        try {
            montos.acumularAlTotal(grupo, reembolso);
        } catch (TotalNegativoException e) {
            reembolsoRechazado = true;
        }
    }

    @Entonces("el reembolso es rechazado")
    public void elReembolsoEsRechazado() {

        assertThat(reembolsoRechazado).isTrue();
    }

    @Entonces("el total del grupo sigue siendo {int}")
    public void elTotalDelGrupoSigueSiendo(int total) {

        assertThat(grupo.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(total));
    }

    @Entonces("el total del grupo es {int}")
    public void elTotalDelGrupoEs(int total) {

        assertThat(grupo.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(total));
    }
}
