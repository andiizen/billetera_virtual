package ar.com.ada.api.billeteravirtual.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.repos.BilleteraRepository;

@Service
public class BilleteraService {

    @Autowired
    BilleteraRepository repo;

    /*
     * 3. Metodo: consultar saldo 3.1-- recibir el id de la billetera y la moneda en
     * la que esta la cuenta
     */

    public void grabar(Billetera billetera) {
        repo.save(billetera);
    }

    public void cargarSaldo(BigDecimal saldo, String moneda, Integer billeteraId, String conceptoOperacion,
            String detalle) {

        Billetera billetera = repo.findByBilleteraId(billeteraId);

        Cuenta cuenta = billetera.getCuenta(moneda);

        Transaccion transaccion = new Transaccion();
        transaccion.setMoneda(moneda);
        transaccion.setFecha(new Date());
        transaccion.setConceptoOperacion(conceptoOperacion);
        transaccion.setDetalle(detalle);
        transaccion.setImporte(saldo);
        transaccion.setTipoOperacion(1);// 1 Entrada, 0 Salida
        transaccion.setEstadoId(1);// -1 Rechazada 0 Pendiente 1 Aprobada
        transaccion.setDeCuentaId(cuenta.getCuentaId());
        transaccion.setDeUsuarioId(billetera.getPersona().getUsuario().getUsuarioId());
        transaccion.setaUsuarioId(billetera.getPersona().getUsuario().getUsuarioId());
        transaccion.setaCuentaId(cuenta.getCuentaId());

        cuenta.agregarTransaccion(transaccion);

        this.grabar(billetera);
    }

    public BigDecimal consultarSaldo(Integer billeteraId, String moneda) {

        Billetera billetera = repo.findByBilleteraId(billeteraId);

        Cuenta cuenta = billetera.getCuenta(moneda);

        return cuenta.getSaldo();

    }

    public Billetera buscarPorId(Integer id) {

        return repo.findByBilleteraId(id);
    }
    /*
     * 2. Metodo: enviar plata 2.1-- recibir un importe, la moneda en la que va a
     * estar ese importe recibir una billetera de origen y otra de destino 2.3--
     * generar dos transacciones
     */

    public void enviarSaldo(BigDecimal importe, String moneda, Integer billeteraOrigenId, Integer billeteraDestinoId,
            String conceptoOperacion, String detalle) {

        Billetera billeteraSaliente = this.buscarPorId(billeteraOrigenId);
        Billetera billeteraEntrante = this.buscarPorId(billeteraDestinoId);

        Cuenta cuentaSaliente = billeteraSaliente.getCuenta(moneda);
        Cuenta cuentaEntrante = billeteraEntrante.getCuenta(moneda);

        Transaccion tSaliente = new Transaccion();
        tSaliente = cuentaSaliente.generarTransaccion(conceptoOperacion, detalle, importe, 1);
        tSaliente.setaCuentaId(cuentaEntrante.getCuentaId());
        tSaliente.setaUsuarioId(billeteraEntrante.getPersona().getUsuario().getUsuarioId());

        Transaccion tEntrante = new Transaccion();
        tEntrante = cuentaEntrante.generarTransaccion(conceptoOperacion, detalle, importe, 0);
        tEntrante.setDeCuentaId(cuentaSaliente.getCuentaId());
        tEntrante.setDeUsuarioId(billeteraSaliente.getPersona().getUsuario().getUsuarioId());

        // 2.2--* actualizar los saldos de las cuentas (a una se le suma y a la otra se
        // le resta)
        // tratar de encapsular y luego agregar la transaccion.
    }
}