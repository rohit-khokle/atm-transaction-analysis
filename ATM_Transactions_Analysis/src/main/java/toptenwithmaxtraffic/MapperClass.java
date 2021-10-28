package toptenwithmaxtraffic;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperClass  extends Mapper<LongWritable, Text, Text, LongWritable>{

    //    hadoop datatype
    Text word = new Text();
    LongWritable one = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String atm = value.toString().split(",")[6];
           
        if(atm.length() > 0) {
        	context.write(new Text(atm),one);
        }
    }
    
//    cleanup code

}











