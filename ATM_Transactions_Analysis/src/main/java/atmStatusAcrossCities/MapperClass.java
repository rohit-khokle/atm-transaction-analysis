/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmStatusAcrossCities;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MapperClass extends Mapper<Object, Text, Text, ActiveInactiveTuple> {
    
    private ActiveInactiveTuple outputTuple = new ActiveInactiveTuple();
    private final static SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        String[] atmLine= value.toString().split(",");
        if(!atmLine[0].equals("weather_city_name") && atmLine.length==9){

            String city = atmLine[1];
            try {
                outputTuple.setActive(1);     // (frmt.parse(atmLine[2]));
                outputTuple.setInactive(1); //        DateMinStockVolume(frmt.parse(atmLine[2]));
            } catch (Exception e) {
                e.printStackTrace();
            }

        
            context.write(new Text(city), outputTuple);
        }

    }
    
    
}
