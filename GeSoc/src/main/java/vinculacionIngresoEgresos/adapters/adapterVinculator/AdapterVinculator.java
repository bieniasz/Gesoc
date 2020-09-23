package vinculacionIngresoEgresos.adapters.adapterVinculator;

import operacionComercial.OperacionEgreso;
import operacionComercial.OperacionIngreso;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import vinculacionIngresoEgresos.adapters.IAdapterVinculacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterVinculator implements IAdapterVinculacion {
    private JSONObject resultadoVinculacionObject;

    @Override
    public Map<OperacionEgreso, OperacionIngreso> obtenerEgresosVinculados(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso) throws IOException {
        JSONObject jsonRepoEgresosObject = new JSONObject();
        jsonRepoEgresosObject.put("egresos", operacionesIngreso);
        JSONObject jsonRepoIngresosObject = new JSONObject();
        jsonRepoIngresosObject.put("ingresos", operacionesEgreso);

        JSONObject jsonGeneralObject = new JSONObject();
        jsonGeneralObject.put("repositorioEgresos", jsonRepoEgresosObject);
        jsonGeneralObject.put("repositorioIngresos", jsonRepoIngresosObject);

        String strIngresosVinculados = this.llamarAServicio("localhost:9000/vincular", jsonGeneralObject.toString());
        this.resultadoVinculacionObject = new JSONObject(strIngresosVinculados);

        return this.obtenerVinculaciones(operacionesIngreso, operacionesEgreso, resultadoVinculacionObject);
    }

    private Map<OperacionEgreso, OperacionIngreso> obtenerVinculaciones(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso, JSONObject resultadoVinculacionObject) {
        JSONArray arrayIngresosVinculados = resultadoVinculacionObject.getJSONArray("ingresosVinculados");
        Map<OperacionEgreso, OperacionIngreso> mapIngresosEgresos = new HashMap<>();

        for (int i = 0; i < arrayIngresosVinculados.length(); i++) {

            JSONObject ingresoVinculadoObject = arrayIngresosVinculados.getJSONObject(i);
            String idIngreso = ingresoVinculadoObject.getString("id_ingreso");

            OperacionIngreso operacionIngresoVinculado = operacionesIngreso.stream().filter(op ->
                op.getid() == Integer.parseInt(idIngreso)
            ).findFirst().get();

            JSONArray arrayEgresosVinculados = ingresoVinculadoObject.getJSONArray("egresos");
            for (int j = 0; j < arrayEgresosVinculados.length(); j++){
                String idEgreso = arrayIngresosVinculados.getString(i);

                OperacionEgreso operacionEgresoVinculado = operacionesEgreso.stream().filter(op ->
                        op.getid() == Integer.parseInt(idEgreso)
                ).findFirst().get();

                mapIngresosEgresos.put(operacionEgresoVinculado, operacionIngresoVinculado);
            }
        }

        return mapIngresosEgresos;
    }

    private String llamarAServicio(String url, String body) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);

        StringEntity jsonData = new StringEntity(body, "UTF-8");
        httpPut.setEntity(jsonData);

        ResponseHandler< String > responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        String responseBody = httpclient.execute(httpPut, responseHandler);

        return responseBody;
    }
}
