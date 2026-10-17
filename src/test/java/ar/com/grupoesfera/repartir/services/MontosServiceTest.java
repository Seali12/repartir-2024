package ar.com.grupoesfera.repartir.services;

import static org.assertj.core.api.Assertions.*;

import ar.com.grupoesfera.repartir.exceptions.TotalNegativoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MontosServiceTest {

    MontosService montos;

    @BeforeEach
    void crear() {

        montos = new MontosService();
    }

    private BigDecimal $(int pesos, int centavos) {

        return BigDecimal.valueOf(pesos * 100 + centavos, 2);
    }

    @Test
    void inicializarTotalDelGrupo() {

        Grupo grupo = new Grupo();

        montos.inicializarTotal(grupo);

        assertThat(grupo.getTotal()).isEqualTo($(0,0));
    }

    @Test
    void acumularMontoDelGastoAlTotalDelGrupo() {

        Grupo grupo = new Grupo();
        grupo.setTotal($(300,51));

        Gasto gasto = new Gasto();
        gasto.setMonto($(23,90));

        montos.acumularAlTotal(grupo, gasto);

        assertThat(grupo.getTotal()).isEqualTo($(324,41));
    }

    @Test
    void rechazarReembolsoQueDejaElTotalEnNegativo() {

        Grupo grupo = new Grupo();
        grupo.setTotal($(50,0));

        Gasto reembolso = new Gasto();
        reembolso.setMonto($(-80,0));

        assertThatThrownBy(() -> montos.acumularAlTotal(grupo, reembolso))
                .isInstanceOf(TotalNegativoException.class);
        assertThat(grupo.getTotal()).isEqualTo($(50,0));
    }

    @Test
    void aceptarReembolsoQueDejaElTotalEnCero() {

        Grupo grupo = new Grupo();
        grupo.setTotal($(50,0));

        Gasto reembolso = new Gasto();
        reembolso.setMonto($(-50,0));

        montos.acumularAlTotal(grupo, reembolso);

        assertThat(grupo.getTotal()).isEqualTo($(0,0));
    }

    @Test
    void aceptarReembolsoParcialQueNoDejaElTotalEnNegativo() {

        Grupo grupo = new Grupo();
        grupo.setTotal($(50,0));

        Gasto reembolso = new Gasto();
        reembolso.setMonto($(-20,0));

        montos.acumularAlTotal(grupo, reembolso);

        assertThat(grupo.getTotal()).isEqualTo($(30,0));
    }
}
