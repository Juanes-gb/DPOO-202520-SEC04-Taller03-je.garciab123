package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo
{
    private String fecha;
    private Ruta ruta;
    private Avion avion;
    private List<Tiquete> tiquetes = new ArrayList<>();
    private boolean realizado = false;

    public Vuelo( Ruta ruta, String fecha, Avion avion )
    {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
    }

    public Ruta getRuta( )
    {
        return ruta;
    }

    public Avion getAvion( )
    {
        return avion;
    }

    public String getFecha( )
    {
        return fecha;
    }

    public Collection<Tiquete> getTiquetes( )
    {
        return java.util.Collections.unmodifiableList( tiquetes );
    }

    public int venderTiquetes( Cliente cliente, CalculadoraTarifas calculadora, int cantidad ) throws VueloSobrevendidoException
    {
        int disponibles = avion.getCapacidad() - tiquetes.size();
        if (cantidad > disponibles) throw new VueloSobrevendidoException(this);
        int total = 0;
        for (int i = 0; i < cantidad; i++)
        {
            int tarifa = calculadora.calcularTarifa(this, cliente);
            Tiquete t = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
            tiquetes.add(t);
            cliente.agregarTiquete(t);
            total += tarifa;
        }
        return total;
    }

    public void registrarRealizado( )
    {
        this.realizado = true;
        for (Tiquete t : tiquetes)
        {
            if (!t.esUsado() && t.getVuelo() == this) t.marcarComoUsado();
        }
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj ) return true;
        if ( !( obj instanceof Vuelo ) ) return false;
        Vuelo other = (Vuelo) obj;
        String c1 = ruta != null ? ruta.getCodigoRuta() : null;
        String c2 = other.ruta != null ? other.ruta.getCodigoRuta() : null;
        return (fecha != null ? fecha.equals(other.fecha) : other.fecha == null)
                && (c1 != null ? c1.equals(c2) : c2 == null);
    }

    @Override
    public int hashCode( )
    {
        String c = ruta != null ? ruta.getCodigoRuta() : "";
        return ((fecha != null ? fecha : "") + "|" + c).hashCode();
    }
}
