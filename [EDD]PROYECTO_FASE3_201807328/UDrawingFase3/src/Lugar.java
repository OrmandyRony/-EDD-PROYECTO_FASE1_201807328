/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author orman
 */
public class Lugar {
    String id;
    String departamento;
    String nombre;
    Boolean existeSucursal;

    public Lugar(String id, String departamento, String nombre, Boolean existeSucursal) {
        this.id = id;
        this.departamento = departamento;
        this.nombre = nombre;
        this.existeSucursal = existeSucursal;
    }
    
    
}
