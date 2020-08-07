# BilleteraVirtual :dollar::credit_card:

_Web API. Este proyecto es una simulación de una billetera virtual semejante a las que estan en el mercado actualmente._

## Funcionalidades

* **Registro y login de usuarios**. Se guardan el nombre y la contraseña (encriptada).
* **Creación de cuentas**. En moneda ARS o USD.
* **Realización de operaciones**. Se pueden hacer recargas, recibir y/o enviar dinero.
* **Consulta de saldos y movimientos**. Se pueden listar de todos los usuarios o de una cuenta en particular.
* **Envio de notificaciones por email**. Al realizar el registro por primera vez y cuando se realiza alguna otra operación.

_Este proyecto fue construido con Spring Boot, MySQL, [Mailgun](https://www.mailgun.com/) para el envio de emails y [JWT](https://jwt.io/) para la parte de seguridad.
También se realizaron distintos test unitarios con junit._

## Deploy 🚀

_Esta API fue deployada en [Heroku](https://heroku.com) y se utilizó Postgre como base de datos en la nube._

Link de la documentación hecha en **Postman**: https://documenter.getpostman.com/view/12254996/T1LHJAic
