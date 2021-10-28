package topTenAtmsPerCity;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

public class ReducerClass extends Reducer<Text, IntWritable, NullWritable, Text > {
	
	private TreeMap<Integer, Text> treeMap = new TreeMap<Integer,Text>();

    // just like in mongoDB values is iterable
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum=0;
        for(IntWritable v: values){
            sum += v.get();
        }

        treeMap.put(sum, key);        
        if(treeMap.size() > 10)
        	treeMap.remove(treeMap.firstKey());
        
    }
    
    
    @Override
    protected void cleanup(Reducer<Text, IntWritable, NullWritable, Text>.Context context)
    		throws IOException, InterruptedException {   
        for(Text t : treeMap.descendingMap().values()) {
             context.write(NullWritable.get(), t);
        }  
    }
    
    

}
