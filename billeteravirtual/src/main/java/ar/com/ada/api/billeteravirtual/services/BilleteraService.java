package ar.com.ada.api.billeteravirtual.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.Billetera;
import ar.com.ada.api.billeteravirtual.repos.BilleteraRepositoty;

@Service
public class BilleteraService {
    @Autowired
    BilleteraRepositoty repo;

    public void grabar(Billetera billetera){
        repo.save(billetera);
    }
    
    /*1.Metodo: cargar saldo.
    1.1 Recibir un importe. Se busca una billetera por ID y una cuenta por la moneda.
    1.2 hacer transaccion
    1.3 actualizar saldo de la billetera
    
    2. Metodo: enviar dinero a otra cuenta.
    2.1 recibir: (parametros) un importe. Moneda en la que va a estar ese importe. Billetera origen a 
    billetera de destino (usuario desde hasta, o mail a mail).
    2.2 Actualizar saldos de las cuentas(suma y resta)
    2.3 Generar dos transacciones

    3. Metodo: Consultar Saldo
    3.1 Recibir id de billetera y id de moneda.

    
    */
}