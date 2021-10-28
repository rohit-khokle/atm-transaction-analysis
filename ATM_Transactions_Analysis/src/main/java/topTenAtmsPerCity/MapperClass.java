package topTenAtmsPerCity;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable>{

    // hadoop datatype
    Text word = new Text();
    IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String cityName = "";

                 
        
        String[] atmLine= value.toString().split(",");
   
        if(!atmLine[22].equals("weather_city_name")){

            cityName = atmLine[22];
            context.write(new Text(cityName),one);
        }
        /*
        try {
        	cityName = line.split(",")[22];
        } catch (Exception e) {
        	cityName = "Ambiguous data";
        	
        }
        
        */
       
        
        
//        if(cityName.length() > 0) {
//        	context.write(new Text(cityName),one);
//        }
    }

}
