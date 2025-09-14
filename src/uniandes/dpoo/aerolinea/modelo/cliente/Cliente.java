package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Clase abstracta para los clientes de la aerolínea.
 * Define los métodos que deben implementar ClienteNatural y ClienteCorporativo.
 */
public abstract class Cliente
{
    /**
     * Retorna el tipo de cliente (natural o corporativo).
     */
    public abstract String getTipoCliente();

    /**
     * Retorna el identificador único del cliente (nombre o empresa).
     */
    public abstract String getIdentificador();

    /**
     * Agrega un tiquete al cliente.
     */
    public abstract void agregarTiquete(Tiquete tiquete);

    /**
     * Calcula el valor total de todos los tiquetes del cliente.
     */
    public abstract int calcularValorTotalTiquetes();

    /**
     * Marca como usados los tiquetes del cliente asociados a un vuelo dado.
     */
    public abstract void usarTiquetes(Vuelo vuelo);
}
