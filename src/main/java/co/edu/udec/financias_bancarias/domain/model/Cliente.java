/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.financias_bancarias.domain.model;

import co.edu.udec.financias_bancarias.domain.enums.ClienteTipo;
import co.edu.udec.financias_bancarias.domain.enums.Sexo;
import co.edu.udec.financias_bancarias.domain.enums.TipoOrganizacion;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.ClienteId;
import co.edu.udec.financias_bancarias.domain.valueobjetcs.Direccion;
import java.time.LocalDate;

/**
 *
 * @author juana
 */
public class Cliente {
    private final ClienteId id;
    private String nombre;
    private Direccion direccion;
    private final ClienteTipo tipo;

    private LocalDate fechaNacimiento;
    private Sexo sexo;

    private TipoOrganizacion tipoOrganizacion;
    private String representante;
    private Integer numEmpleados;

    public Cliente(ClienteId id, String nombre, Direccion direccion, ClienteTipo tipo) {
        if (id == null) throw new IllegalArgumentException("id inválido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre inválido");
        if (direccion == null) throw new IllegalArgumentException("direccion inválida");
        if (tipo == null) throw new IllegalArgumentException("tipo inválido");
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public void setDatosPersona(LocalDate fechaNacimiento, Sexo sexo) {
        if (this.tipo != ClienteTipo.PERSONA) throw new IllegalStateException("Cliente no es persona");
        if (fechaNacimiento == null) throw new IllegalArgumentException("fechaNacimiento inválida");
        if (sexo == null) throw new IllegalArgumentException("sexo inválido");
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public void setDatosOrganizacion(TipoOrganizacion tipoOrganizacion, String representante, Integer numEmpleados) {
        if (this.tipo != ClienteTipo.ORGANIZACION) throw new IllegalStateException("Cliente no es organización");
        if (tipoOrganizacion == null) throw new IllegalArgumentException("tipoOrganizacion inválido");
        if (representante == null || representante.isBlank()) throw new IllegalArgumentException("representante inválido");
        if (numEmpleados == null || numEmpleados < 0) throw new IllegalArgumentException("numEmpleados inválido");
        this.tipoOrganizacion = tipoOrganizacion;
        this.representante = representante;
        this.numEmpleados = numEmpleados;
    }

    public ClienteId getId() { return id; }
    public String getNombre() { return nombre; }
    public Direccion getDireccion() { return direccion; }
    public ClienteTipo getTipo() { return tipo; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Sexo getSexo() { return sexo; }
    public TipoOrganizacion getTipoOrganizacion() { return tipoOrganizacion; }
    public String getRepresentante() { return representante; }
    public Integer getNumEmpleados() { return numEmpleados; }
}
