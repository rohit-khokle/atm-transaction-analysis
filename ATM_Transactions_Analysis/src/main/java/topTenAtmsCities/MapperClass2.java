package topTenAtmsCities;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

public class MapperClass2 extends Mapper<LongWritable, Text, NullWritable, Text>{

    //    hadoop datatype
    Text word = new Text();
    IntWritable one = new IntWritable(1);
    static TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
    
    @Override
    protected void setup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
    		throws IOException, InterruptedException {
    	// TODO Auto-generated method stub
   // 	super.setup(context);
    }
    
    
    
    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        
        String row[] = value.toString().split("\\t");
 //       Text city_name = new Text(row[0]);
        
        String city_name = row[0].toString();
        Integer atmsCount = Integer.parseInt(row[1]);
     
        tm.put(atmsCount, city_name);
        
        if(tm.size() > 10)
        	tm.remove(tm.firstEntry());
    }
    
    
    @Override
    protected void cleanup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
    		throws IOException, InterruptedException {
    
        for(String t : tm.descendingMap().values()) {
            context.write(NullWritable.get(), new Text(t));
       }
       
    	
    }

}
