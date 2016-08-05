package app.gianglong.tracuudienthoai.Other;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.ParameterObject;
import app.gianglong.tracuudienthoai.Objects.ProductObject;

import static app.gianglong.tracuudienthoai.MainActivity.progressDialog;

/**
 * Created by Giang Long on 7/22/2016.
 */

public class JsonAsynTask extends AsyncTask<String, String, String> {
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    ParameterObject parameterObject;
    ProductObject productObject;
    @Override
    protected String doInBackground(String... url) {

        try {
            URL mUrl = new URL(url[0]);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }


            String strJson = buffer.toString();
            JSONObject obj = new JSONObject(strJson);
            JSONArray arr = obj.getJSONArray("InfomationSmartphone");
            for (int i = 0; i < arr.length(); i++){
                JSONObject object = arr.getJSONObject(i);
                String maSP = object.getString("MaSP");
                String tenSP = object.getString("TenSP");
                String thuongHieu = object.getString("ThuongHieu");
                String anhSP = object.getString("AnhSP");
                String namSX = object.getString("NamSX");
                String mauSac = object.getString("MauSac");
                String giaThamKhao = object.getString("GiaThamKhao");
                String linkDanhGia = object.getString("linkDanhGia");

                JSONArray linkStr = object.getJSONArray("linkDanhGia");
                for (int k = 0 ; k < linkStr.length(); k++){

                }


                JSONArray parameterStr = object.getJSONArray("ThongSoKyThuat");
                JSONObject parameterObjectJson = parameterStr.getJSONObject(0);
                for (int j = 0; j < 12; j++){
                    String screen = parameterObjectJson.getString("ManHinh");
                    String system = parameterObjectJson.getString("HeDieuHanh");
                    String camera = parameterObjectJson.getString("Camera");
                    String cpu = parameterObjectJson.getString("CPU");
                    String ram = parameterObjectJson.getString("Ram");
                    String internal = parameterObjectJson.getString("BoNhoTrong");
                    String external = parameterObjectJson.getString("BoNhoNgoai");
                    String sim = parameterObjectJson.getString("Sim");
                    String connect = parameterObjectJson.getString("KetNoi");
                    String pin = parameterObjectJson.getString("Pin");
                    String type = parameterObjectJson.getString("ThietKe");
                    String skill = parameterObjectJson.getString("ChucNangDacBiet");

                    parameterObject = new ParameterObject(screen, system, camera, cpu, ram, internal, external, sim, connect,pin, type, skill);
                }

                //productObject = new ProductObject(maSP, tenSP,thuongHieu, anhSP, Integer.parseInt(namSX), parameterObject, mauSac, giaThamKhao, linkDanhGia);
                MainActivity.db.insertRow(maSP, tenSP, thuongHieu, anhSP, Integer.parseInt(namSX), parameterObject, mauSac, giaThamKhao, linkDanhGia);
                MainActivity.arrl.add(new ProductObject(maSP, tenSP,thuongHieu, anhSP, Integer.parseInt(namSX), parameterObject, mauSac, giaThamKhao, linkDanhGia));


            }


            return "";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                connection.disconnect();
            }

            try {
                if(reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        Toast.makeText(MainActivity.activity, "Cập nhật thành công !!!", Toast.LENGTH_SHORT).show();
    }
}
