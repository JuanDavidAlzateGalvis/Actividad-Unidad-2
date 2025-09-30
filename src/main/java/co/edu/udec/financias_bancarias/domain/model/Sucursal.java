/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.model;

import co.edu.udec.financias_bancarias.domain.valueobjetcs.Direccion;

/**
 *
 * @author juana
 */
public class Sucursal {
    private final String numero; 
    private Direccion direccion;
    private String codigoPostal;
    private String ciudad; 
    private Empleado director; 

    public Sucursal(String numero, Direccion direccion, String codigoPostal, String ciudad) {
        if (numero == null || numero.isBlank()) throw new IllegalArgumentException("numero inválido");
        if (direccion == null) throw new IllegalArgumentException("direccion inválida");
        if (ciudad == null || ciudad.isBlank()) throw new IllegalArgumentException("ciudad inválida");
        this.numero = numero;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
    }

    public String getNumero() { return numero; }
    public Direccion getDireccion() { return direccion; }
    public String getCodigoPostal() { return codigoPostal; }
    public String getCiudad() { return ciudad; }
    public Empleado getDirector() { return director; }
    public void setDirector(Empleado director) { this.director = director; }
}
