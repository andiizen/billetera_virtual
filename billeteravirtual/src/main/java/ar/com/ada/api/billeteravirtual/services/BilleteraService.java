package ar.com.ada.api.billeteravirtual.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.repos.*;

@Service
public class BilleteraService {

    @Autowired
    BilleteraRepository billeteraRepository;

    public void grabar(Billetera billetera) {
        billeteraRepository.save(billetera);
    }

    /**
     * 1.Metodo: cargar saldo. 1.1 Recibir un importe. Se busca una billetera por ID
     * y una cuenta por la moneda. 1.2 hacer transaccion 1.3 actualizar saldo de la
     * billetera
     *
     */
    public void cargarSaldo(BigDecimal saldo, String moneda, Integer billeteraId, String conceptoOperacion,
            String detalle) {

        Billetera billetera = billeteraRepository.findByBilleteraId(billeteraId);

        Cuenta cuenta = billetera.getCuenta(moneda);

        Transaccion transaccion = new Transaccion();
        // transaccion.setCuenta(cuenta);
        transaccion.setMoneda(moneda);
        transaccion.setFecha(new Date());
        transaccion.setConceptoOperacion(conceptoOperacion);
        transaccion.setDetalle(detalle);
        transaccion.setImporte(saldo);
        transaccion.setTipoOperacion(1); // 1 = acreditar(entrada). 2 = debitar(salida)
        transaccion.setEstadoId(1); // 1 = aprobada . 0= pendiente -1 =rechazada
        transaccion.setDeCuentaId(cuenta.getCuentaId());
        transaccion.setaCuentaId(cuenta.getCuentaId());
        transaccion.setDeUsuarioId(billetera.getPersona().getUsuario().getUsuarioId());
        transaccion.setaUsuarioId(billetera.getPersona().getUsuario().getUsuarioId());

        cuenta.agregarTransaccion(transaccion);

        BigDecimal saldoActual = cuenta.getSaldo();
        BigDecimal saldoNuevo = saldoActual.add(saldo);
        cuenta.setSaldo(saldoNuevo);

        this.grabar(billetera);

    }

    /*
     * 2. Metodo: enviar dinero a otra cuenta. 2.1 recibir: (parametros) un importe.
     * Moneda en la que va a estar ese importe. Billetera origen a billetera de
     * destino (usuario desde hasta, o mail a mail). 2.2 Actualizar saldos de las
     * cuentas(suma y resta) 2.3 Generar dos transacciones
     * 
     * 3. Metodo: Consultar Saldo 3.1 Recibir id de billetera y id de moneda.
     * 
     * 
     */
}