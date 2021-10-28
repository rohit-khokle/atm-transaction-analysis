package topTenAtmsCities;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class DriverClass {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{


        Configuration conf = new Configuration();
        Configuration conf2 = new Configuration();
        // Create a new Job
        Job job = Job.getInstance(conf,"toptentransactionsPerCity");
        job.setJarByClass(DriverClass.class);

        // Specify various job-specific parameters
        job.setJobName("toptentransactionsPerCity");


        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setMapperClass(MapperClass.class);
        job.setReducerClass(ReducerClass.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        boolean complete = job.waitForCompletion(true);

        
        Job job2 = Job.getInstance(conf2, "Chaining");

        if (complete) {
        	

            FileInputFormat.addInputPath(job2, new Path(args[2]));
            FileOutputFormat.setOutputPath(job2, new Path(args[3]));
 //         job2.setJarByClass(Top10_Categories.class);
            
            
            
            
            job2.setMapperClass(MapperClass2.class);
            job2.setMapOutputKeyClass(NullWritable.class);
            job2.setMapOutputValueClass(Text.class);

        //    job2.setReducerClass(ReducerClass2.class);
        //    job2.setOutputKeyClass(Text.class);
        //    job2.setOutputValueClass(FloatWritable.class);

        //    job2.setSortComparatorClass(SortKeyComparator.class);

        //    job2.setNumReduceTasks(1);


            System.exit(job2.waitForCompletion(true) ? 0 : 1);
        	
        }
    }
}
