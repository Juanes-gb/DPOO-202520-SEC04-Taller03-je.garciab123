package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ClienteCorporativo extends Cliente
{
    public static final String CORPORATIVO = "Corporativo";
    public static final int PEQUENA = 0;
    public static final int MEDIANA = 1;
    public static final int GRANDE = 2;

    private String nombreEmpresa;
    private int tamanoEmpresa;
    private List<Tiquete> tiquetes = new ArrayList<>();

    public ClienteCorporativo(String nombreEmpresa, int tamanoEmpresa)
    {
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamanoEmpresa;
    }

    public String getNombreEmpresa()
    {
        return nombreEmpresa;
    }

    public int getTamanoEmpresa()
    {
        return tamanoEmpresa;
    }

    public static ClienteCorporativo cargarDesdeJSON(JSONObject cliente)
    {
        String nombreEmpresa = cliente.getString("nombreEmpresa");
        int tam = cliente.getInt("tamanoEmpresa");
        return new ClienteCorporativo(nombreEmpresa, tam);
    }

    public JSONObject salvarEnJSON()
    {
        JSONObject jobject = new JSONObject();
        jobject.put("nombreEmpresa", this.nombreEmpresa);
        jobject.put("tamanoEmpresa", this.tamanoEmpresa);
        jobject.put("tipo", CORPORATIVO);
        return jobject;
    }

    @Override
    public String getTipoCliente()
    {
        return CORPORATIVO;
    }

    @Override
    public String getIdentificador()
    {
        return nombreEmpresa;
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