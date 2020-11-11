package domain.entities.vinculacionIngresoEgresos.adapters.adapterVinculator;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import domain.entities.vinculacionIngresoEgresos.adapters.IAdapterVinculacion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdapterVinculator implements IAdapterVinculacion {

    @Override
    public Map<OperacionEgreso, OperacionIngreso> obtenerVinculaciones(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso) throws IOException {
        Map<OperacionEgreso, OperacionIngreso> mapVinculaciones = new HashMap<>();

        //Hago la llamada al servicio y obtengo el string con el resultado
        String strJSONBody = this.generarPutJsonBody(operacionesEgreso, operacionesIngreso);
        String strIngresosVinculados = this.makeHTTPPutRequest("http://localhost:9000/vincular", strJSONBody);
        JSONObject jsonResultadoVinculacion = new JSONObject(strIngresosVinculados);

        //Hago la vinculacion en memoria
        JSONArray arrayIngresosVinculados = jsonResultadoVinculacion.getJSONArray("ingresosVinculados");
        //Recorro los ingresos
        for (int i = 0; i < arrayIngresosVinculados.length(); i++) {
            JSONObject jsonIngresoVinculado = arrayIngresosVinculados.getJSONObject(i);
            int idIngreso = Integer.parseInt(jsonIngresoVinculado.getString("id_ingreso"));

            Optional<OperacionIngreso> optIngresoVinculado = operacionesIngreso
                    .stream()
                    .filter(op -> op.getId() == idIngreso)
                    .findFirst();

            if (optIngresoVinculado.isPresent()) {
                OperacionIngreso ingresoVinculado = optIngresoVinculado.get();
                //Obtengo sus egresos vinculados
                JSONArray arrayEgresosVinculados = jsonIngresoVinculado.getJSONArray("egresos");
                for (int j = 0; j < arrayEgresosVinculados.length(); j++) {
                    int idEgreso = Integer.parseInt(arrayEgresosVinculados.getString(j));

                    OperacionEgreso egresoVinculado = operacionesEgreso
                            .stream()
                            .filter(op -> op.getId() == idEgreso)
                            .findFirst()
                            .get();

                    mapVinculaciones.put(egresoVinculado, ingresoVinculado);
                }
            }
        }

        return mapVinculaciones;
    }

    public String makeHTTPPutRequest(String url, String content) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);

        if (null != content) {
            HttpEntity httpEntity = new ByteArrayEntity(content.getBytes(StandardCharsets.UTF_8));
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setEntity(httpEntity);
        }

        ResponseHandler< String > responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        return httpclient.execute(httpPut, responseHandler);
    }

    public String generarPutJsonBody(List<OperacionEgreso> operacionesEgreso, List<OperacionIngreso> operacionesIngreso) {
        JSONObject jsonRepoEgresosObject = new JSONObject().put("egresos", parsearEgresos(operacionesEgreso));
        JSONObject jsonRepoIngresosObject = new JSONObject().put("ingresos", parsearIngresos(operacionesIngreso));

        JSONObject jsonGeneralObject = new JSONObject();
        jsonGeneralObject.put("repositorioEgresos", jsonRepoEgresosObject);
        jsonGeneralObject.put("repositorioIngresos", jsonRepoIngresosObject);

        return jsonGeneralObject.toString();
    }
    private JSONArray parsearEgresos(List<OperacionEgreso> operacionesEgreso) {
        JSONArray jsonEgresosArray = new JSONArray();
        for (OperacionEgreso egreso : operacionesEgreso) {
            JSONObject jsonEgresoObj = new JSONObject();
            jsonEgresoObj.put("id_egreso", String.valueOf(egreso.getId()));
            jsonEgresoObj.put("fecha", egreso.getFecha());
            jsonEgresoObj.put("valorTotal", egreso.getValorTotal());
            String docID = egreso.getDocumentoComercial() != null ? String.valueOf(egreso.getDocumentoComercial().getId()) : "";
            jsonEgresoObj.put("documentoComercial", docID);
            String strDetalle = egreso.getDetalle() != null ? egreso.getDetalle().toString() : "";
            jsonEgresoObj.put("detalle", strDetalle);
            jsonEgresosArray.put(jsonEgresoObj);
        }
        return jsonEgresosArray;
    }
    private JSONArray parsearIngresos(List<OperacionIngreso> operacionesIngreso) {
        JSONArray jsonIngresosArray = new JSONArray();
        for (OperacionIngreso ingreso : operacionesIngreso) {
            JSONObject jsonIngresoObj = new JSONObject()
                    .put("id_ingreso", String.valueOf(ingreso.getId()))
                    .put("fecha", ingreso.getFecha())
                    .put("fecha_hasta", ingreso.getFechaHastaAceptable())
                    .put("valorTotal", Double.valueOf(ingreso.getMonto()))
                    .put("descripcion", ingreso.getDescripcion());
            jsonIngresosArray.put(jsonIngresoObj);
        }
        return jsonIngresosArray;
    }

}
