package atmStatusAcrossCities;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;



public class DriverClass {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
	
    // Create a new Job
    Job job = Job.getInstance();//(conf,"MinMaxStock");
    job.setJarByClass(DriverClass.class);
    
    // Specify various job-specific parameters     
    job.setJobName("myjob");
    job.setMapperClass(MapperClass.class);
    job.setReducerClass(ReducerClass.class);
    job.setNumReduceTasks(1);
    TextInputFormat.addInputPath(job, new Path(args[0]));

    job.setInputFormatClass(TextInputFormat.class);

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(ActiveInactiveTuple.class);

    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(ActiveInactiveTuple.class);
    job.setOutputFormatClass(TextOutputFormat.class);

    // Submit the job, then poll for progress until the job is complete
    System.exit(job.waitForCompletion(true)?0:1);

	}

}
