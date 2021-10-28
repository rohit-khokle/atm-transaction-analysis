/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmStatusAcrossCities;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class
ReducerClass extends Reducer<Text, ActiveInactiveTuple, Text, ActiveInactiveTuple>{
    
     private ActiveInactiveTuple outputTuple = new ActiveInactiveTuple();

    @Override
    protected void reduce(Text key, Iterable<ActiveInactiveTuple> values, Context context) throws IOException, InterruptedException {

    	// First Count
    	

        int active, inactive=0;
        for(ActiveInactiveTuple v: values){
   //         active += v.getActive();
     //       inactive += v.getInactive();
            // can we use this--  Integer.parseInt(v.toString());
        }
    	
    	
    	
    	
    	// Emit it
    	
    	
    	
    	
        long maxStockVolume = Long.MIN_VALUE;
        Date maxDate = new Date();
        long minStockVolume = Long.MAX_VALUE;
        Date minDate = new Date();
        double maxStockPriceClose = Double.MIN_VALUE;
        
        for(ActiveInactiveTuple v: values){

            if(v.getMaxStockVolume()>maxStockVolume){
                maxStockVolume= v.getMaxStockVolume();
                maxDate = v.getDateMaxStockVolume();
            }

           if(v.getMaxStockVolume()<minStockVolume){
                minStockVolume= v.getMaxStockVolume();
                minDate = v.getDateMaxStockVolume();
            }
           
             if(v.getMaxStockPriceClose()>maxStockPriceClose){
                maxStockPriceClose = v.getMaxStockPriceClose();
            }
             
             
        }
        
        outputTuple.setDateMaxStockVolume(maxDate);
        outputTuple.setDateMinStockVolume(minDate);
        outputTuple.setMaxStockPriceClose(maxStockPriceClose);
        outputTuple.setMaxStockVolume(maxStockVolume);
        
        context.write(key, outputTuple);
    }
    
    
}
