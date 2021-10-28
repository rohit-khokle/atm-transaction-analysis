package toptenwithmaxtraffic;

import java.io.IOException;
import java.util.TreeMap;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, LongWritable, NullWritable, Text> {
	
	private TreeMap<Integer, Text> treeMap = new TreeMap<Integer,Text>();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        int sum=0;
        for(LongWritable v: values){
            sum += v.get();
        }

        
        treeMap.put(sum, key);        
        if(treeMap.size() > 10)
        	treeMap.remove(treeMap.firstKey());
        
        
        
        
        for(Text t : treeMap.descendingMap().values()) {
             context.write(NullWritable.get(), t);
        }
        


    }

}
