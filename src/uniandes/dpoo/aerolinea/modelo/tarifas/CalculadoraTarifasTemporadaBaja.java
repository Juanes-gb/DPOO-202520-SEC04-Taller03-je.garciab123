package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas
{
    protected static final int COSTO_POR_KM_NATURAL = 600;
    protected static final int COSTO_POR_KM_CORPORATIVO = 900;

    protected static final double DESCUENTO_PEQ = 0.02;
    protected static final double DESCUENTO_MEDIANAS = 0.10;
    protected static final double DESCUENTO_GRANDES = 0.20;

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente)
    {
        int km = calcularDistanciaVuelo(vuelo.getRuta());
        boolean corp = (cliente instanceof ClienteCorporativo);
        int costoKm = corp ? COSTO_POR_KM_CORPORATIVO : COSTO_POR_KM_NATURAL;
        return km * costoKm;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente)
    {
        if (cliente instanceof ClienteCorporativo)
        {
            ClienteCorporativo c = (ClienteCorporativo) cliente;
            int tam = c.getTamanoEmpresa();
            if (tam == ClienteCorporativo.GRANDE)   return DESCUENTO_GRANDES;
            if (tam == ClienteCorporativo.MEDIANA)  return DESCUENTO_MEDIANAS;
            if (tam == ClienteCorporativo.PEQUENA)  return DESCUENTO_PEQ;
        }
        return 0.0;
    }
}