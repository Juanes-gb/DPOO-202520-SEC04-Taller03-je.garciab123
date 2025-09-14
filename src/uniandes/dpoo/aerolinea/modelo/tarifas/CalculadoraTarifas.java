package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas
{
    public static final double IMPUESTO = 0.28;

    public int calcularTarifa(Vuelo vuelo, Cliente cliente)
    {
        int base = calcularCostoBase(vuelo, cliente);
        int impuestos = calcularValorImpuestos(base);
        double descuento = calcularPorcentajeDescuento(cliente);
        return (int)Math.round((base + impuestos) * (1.0 - descuento));
    }

    protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);

    protected abstract double calcularPorcentajeDescuento(Cliente cliente);

    protected int calcularDistanciaVuelo(Ruta ruta)
    {
        Aeropuerto o = ruta.getOrigen();
        Aeropuerto d = ruta.getDestino();
        return Aeropuerto.calcularDistancia(o, d);
    }

    protected int calcularValorImpuestos(int costoBase)
    {
        return (int)Math.round(costoBase * IMPUESTO);
    }
}
