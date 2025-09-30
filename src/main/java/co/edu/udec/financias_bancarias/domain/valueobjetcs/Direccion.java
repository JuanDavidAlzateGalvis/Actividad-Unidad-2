/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.valueobjetcs;

/**
 *
 * @author juana
 */
public record Direccion(String calle, String ciudad, String codigoPostal) {
    public Direccion {
        if (calle == null || calle.isBlank()) throw new IllegalArgumentException("Calle inválida");
        if (ciudad == null || ciudad.isBlank()) throw new IllegalArgumentException("Ciudad inválida");
    }
}
