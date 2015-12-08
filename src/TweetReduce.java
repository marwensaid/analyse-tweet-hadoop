import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import javax.naming.Context;
import java.util.Iterator;
import java.io.IOException;


public class TweetReduce extends Reducer<Text, IntWritable, Text, IntWritable>
{
  public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{

		Iterator<IntWritable> i=values.iterator();  // Pour parcourir toutes les valeurs associées à la clef fournie.
		int count = 0;
		while(i.hasNext())   
		{
			count++;
			i.next();
		}
		
		IntWritable countText = new IntWritable(count);
		context.write(key, countText);
  }
}
