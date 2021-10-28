package transactionMonthlyTrend;

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
        String monthDay = "";
        
        
        try {
        	Integer day = Integer.parseInt(line.split(",")[2]);
        	if(day <= 10)
            	monthDay = "First Ten days of the month";
            else if(day > 10 && day <= 20)
            	monthDay = "Middle of the month";
            else if(day > 20 && day <= 31)
            	monthDay = "End of the month";
        } catch (NumberFormatException e) {
        	monthDay = "Ambiguous data";
        	
        }
        
        
        
        if(monthDay.length() > 0) {
        	context.write(new Text(monthDay),one);
        }
    }

}
