package hourlyTraffic;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable>{

    //    hadoop datatype
    Text word = new Text();
    IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String hourOfTheDay = "";
        
        
        try {
        	Integer hour = Integer.parseInt(line.split(",")[4]);
        	if(hour > 0 && hour <= 8)
        		hourOfTheDay = "Late night";
            else if(hour > 8 && hour <= 12)
            	hourOfTheDay = "Morning";
            else if(hour > 12 && hour <= 16)
            	hourOfTheDay = "Midday";
            else if(hour > 16 && hour <= 20)
            	hourOfTheDay = "Evening";
            else if(hour > 20 && hour <= 0)
            	hourOfTheDay = "Night";
        } catch (NumberFormatException e) {
        	hourOfTheDay = "Ambiguous data";
        	
        }
        
        
        
        if(hourOfTheDay.length() > 0) {
        	context.write(new Text(hourOfTheDay),one);
        }
    }

}
