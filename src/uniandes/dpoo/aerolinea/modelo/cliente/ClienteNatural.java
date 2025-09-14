package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ClienteNatural extends Cliente
{
    public static final String NATURAL = "Natural";

    private String nombre;
    private List<Tiquete> tiquetes = new ArrayList<>();

    public ClienteNatural(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    @Override
    public String getTipoCliente()
    {
        return NATURAL;
    }

    @Override
    public String getIdentificador()
    {
        return nombre;
    }

    @Override
    public void agregarTiquete(Tiquete tiquete)
    {
        tiquetes.add(tiquete);
    }

    @Override
    public int calcularValorTotalTiquetes()
    {
        int total = 0;
        for (Tiquete t : tiquetes)
        {
            if (!t.esUsado()) total += t.getTarifa();
        }
        return total;
    }

    @Override
    public void usarTiquetes(Vuelo vuelo)
    {
        for (Tiquete t : tiquetes)
        {
            if (t.getVuelo().equals(vuelo) && !t.esUsado())
            {
                t.marcarComoUsado();
            }
        }
    }
}