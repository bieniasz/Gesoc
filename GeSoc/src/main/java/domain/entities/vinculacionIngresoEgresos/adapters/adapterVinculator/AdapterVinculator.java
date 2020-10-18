package domain.entities.vinculacionIngresoEgresos.adapters.adapterVinculator;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import domain.entities.vinculacionIngresoEgresos.adapters.IAdapterVinculacion;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterVinculator implements IAdapterVinculacion {
    private JSONObject resultadoVinculacionObject;

    @Override
    public Map<OperacionEgreso, OperacionIngreso> obtenerEgresosVinculados(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso, LocalDate fechaHastaAceptable) throws IOException {
        JSONObject jsonRepoEgresosObject = new JSONObject().put("egresos", parsearEgresos(operacionesEgreso));
        JSONObject jsonRepoIngresosObject = new JSONObject().put("ingresos", parsearIngresos(operacionesIngreso, fechaHastaAceptable));

        JSONObject jsonGeneralObject = new JSONObject()
                .put("repositorioEgresos", jsonRepoEgresosObject)
                .put("repositorioIngresos", jsonRepoIngresosObject);

        String strIngresosVinculados = this.makeHTTPPutRequest("localhost:9000/vincular", jsonGeneralObject.toString());
        this.resultadoVinculacionObject = new JSONObject(strIngresosVinculados);

        return obtenerVinculaciones(operacionesIngreso, operacionesEgreso, this.resultadoVinculacionObject);
    }

    private Map<OperacionEgreso, OperacionIngreso> obtenerVinculaciones(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso, JSONObject resultadoVinculacionObject) {
        JSONArray arrayIngresosVinculados = resultadoVinculacionObject.getJSONArray("ingresosVinculados");
        Map<OperacionEgreso, OperacionIngreso> mapIngresosEgresos = new HashMap<>();

        for (int i = 0; i < arrayIngresosVinculados.length(); i++) {

            JSONObject ingresoVinculadoObject = arrayIngresosVinculados.getJSONObject(i);
            String strIdIngreso = ingresoVinculadoObject.getString("id_ingreso");

            OperacionIngreso operacionIngresoVinculado = operacionesIngreso.stream().filter(op ->
                op.getId() == Integer.parseInt(strIdIngreso)
            ).findFirst().get();

            JSONArray arrayEgresosVinculados = ingresoVinculadoObject.getJSONArray("egresos");
            for (int j = 0; j < arrayEgresosVinculados.length(); j++){
                String idEgreso = arrayIngresosVinculados.getString(i);

                OperacionEgreso operacionEgresoVinculado = operacionesEgreso.stream().filter(op ->
                        op.getId() == Integer.parseInt(idEgreso)
                ).findFirst().get();

                mapIngresosEgresos.put(operacionEgresoVinculado, operacionIngresoVinculado);
            }
        }

        return mapIngresosEgresos;
    }

    private String makeHTTPPutRequest(String url, String body) throws IOException {
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

    private JSONArray parsearEgresos(List<OperacionEgreso> operacionesEgreso) {
        JSONArray jsonEgresosArray = new JSONArray();
        for (OperacionEgreso egreso : operacionesEgreso) {
            JSONObject jsonEgresoObj = new JSONObject()
                    .put("id_egreso", String.valueOf(egreso.getId()))
                    .put("fecha", egreso.getFecha())
                    .put("valorTotal", Double.valueOf(egreso.getValorTotal()))
                    .put("documentoComercial", String.valueOf(egreso.getDocumentoComercial().getId()))
                    .put("detalle", egreso.getDetalle().toString());
            jsonEgresosArray.put(jsonEgresoObj);
        }
        return jsonEgresosArray;
    }

    private JSONArray parsearIngresos(List<OperacionIngreso> operacionesIngreso, LocalDate fechaHastaAceptable) {
        JSONArray jsonIngresosArray = new JSONArray();
        for (OperacionIngreso ingreso : operacionesIngreso) {
            JSONObject jsonIngresoObj = new JSONObject()
                    .put("id_ingreso", String.valueOf(ingreso.getId()))
                    .put("fecha", ingreso.getFecha())
                    .put("fecha_hasta", fechaHastaAceptable)
                    .put("valorTotal", Double.valueOf(ingreso.getMonto()))
                    .put("descripcion", ingreso.getDescripcion());
            jsonIngresosArray.put(jsonIngresoObj);
        }
        return jsonIngresosArray;
    }

}
