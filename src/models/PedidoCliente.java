/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Josue Cabezas
 */
public class PedidoCliente {
    
    private int idPedidoCliente;
    private Date fechaOrden;
    private String concepto;
    private String formaPago;
    private Date fechaEnvio;
    private String rucCliente;
    private String rucTrans;

    public int getId_pedido_cliente() {
        return idPedidoCliente;
    }

    public void setIdPedidoCliente(int idPedidoCliente) {
        this.idPedidoCliente = idPedidoCliente;
    }

    public Date getFecha_orden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getForma_pago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Date getFecha_envio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getRuc_cliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public String getRuc_trans() {
        return rucTrans;
    }

    public void setRucTrans(String rucTrans) {
        this.rucTrans = rucTrans;
    }
    
}
