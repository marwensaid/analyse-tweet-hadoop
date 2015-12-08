import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import java.util.StringTokenizer;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.Arrays;


// Notre classe MAP.
public class TweetMap extends Mapper<Object, Text, Text, IntWritable>
{
	private static final IntWritable one = new IntWritable(1);
	// La fonction MAP elle-même.
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException
	{
		String[] motPositif = {"excellent","cool","pas mal","bien","bravo","admirable","brillant","parfait","remarquable","extra","bon","bon plan","good","great","genial","not bad","amazing","awesome","marvelous","prodigious","positive","exceptional","nice","splendid","neat","super","wonderful","superior"};
		String[] motNegatif = {"bad","nul","pourri","decevant","deplorable","abominable","detestable","mauvais","insatisfait","insatisfaisant","affligeant","faille","minable","navrant","pietre","execrable","lamentable","bof","inferior","flaw","crappy","unacceptable","careless","not good","pas bon","pas bien","unsatisfactory","substandard","awful","atrocious","dreadful","deficient","defective","lousy","reliable","not good"};
		
		StringTokenizer tok = new StringTokenizer(value.toString(), " ");
		boolean positif = false;
		boolean negatif = false;
		
		while(tok.hasMoreTokens()){
		
			String leMot = tok.nextToken();
			
			if(Arrays.asList(motPositif).contains(leMot)){
				positif = true;
			}else if (Arrays.asList(motNegatif).contains(leMot)){
				negatif = true;
			}
		}
		
		
		if(positif && negatif){
			context.write(new Text(new String("Inconcluant")),one);
		}else if(positif){ 
			context.write(new Text(new String("Positif")), one);
		}else if(negatif){ 
			context.write(new Text(new String("Negatif")), one);
		}else{
			context.write(new Text(new String("Neutre")), one);
		}
	}
}
