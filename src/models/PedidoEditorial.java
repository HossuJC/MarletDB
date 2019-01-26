/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Josue Cabezas
 */
public class PedidoEditorial {
    
    private int idPedido;
    private Date fechaOrden;
    private String concepto;
    private String formaPago;
    private Date fechaRecepcion;
    private String rucEditorial;

    public int getId_pedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    public Date getFecha_recepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getRuc_editorial() {
        return rucEditorial;
    }

    public void setRucEditorial(String rucEditorial) {
        this.rucEditorial = rucEditorial;
    }
    
}
