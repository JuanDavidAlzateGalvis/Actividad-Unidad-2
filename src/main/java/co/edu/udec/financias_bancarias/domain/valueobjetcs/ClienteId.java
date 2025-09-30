/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.valueobjetcs;

/**
 *
 * @author juana
 */
public record ClienteId(String value) {
    public ClienteId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("ClienteId no puede estar vacío");
    }
}
