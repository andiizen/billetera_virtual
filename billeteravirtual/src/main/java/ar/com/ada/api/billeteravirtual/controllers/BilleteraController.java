package ar.com.ada.api.billeteravirtual.controllers;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BilleteraController {

    /**
     * Web Metodo 1: "consultar saldo": GET - URL:/billeteras/{id}/saldos
     * 
     * 
     * Web Metodo 2: "cargar saldo": POST URL:/billeteras/{id}/recargas
     * requestBody: {
     *                  "moneda":
     *                  "importe":
     *              }
     * WebMetodo 2: "enviar saldo": POST URL:/billeteras/{id}/envios
     * requestBody: {
     *                  "moneda":
     *                  "importe":
     *                  "email":
     *                  "motivo":
     *                  "detalleMotivo":          
     *              }
     */
}