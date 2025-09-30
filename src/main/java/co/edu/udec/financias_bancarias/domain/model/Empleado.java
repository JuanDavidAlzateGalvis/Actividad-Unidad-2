/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.model;

import co.edu.udec.financias_bancarias.domain.enums.Sexo;
import java.time.LocalDate;

/**
 *
 * @author juana
 */
public class Empleado {
    private final String dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    private Sucursal sucursal;

    public Empleado(String dni, String nombre, LocalDate fechaNacimiento, Sexo sexo) {
        if (dni == null || dni.isBlank()) throw new IllegalArgumentException("DNI inválido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre inválido");
        if (fechaNacimiento == null) throw new IllegalArgumentException("fechaNacimiento inválida");
        if (sexo == null) throw new IllegalArgumentException("sexo inválido");
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Sexo getSexo() { return sexo; }
    public Sucursal getSucursal() { return sucursal; }
    public void setSucursal(Sucursal sucursal) { this.sucursal = sucursal; }
}
