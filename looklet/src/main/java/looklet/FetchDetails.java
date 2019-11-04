package looklet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class FetchDetails {
	public static void main(String[] args) {
		
		Map<String,Byte> finalist = new HashMap<String,Byte>();
		File look1 = new File("src/main/resources/look1.jpg");
		finalist.put("look1.jpg", sumOfFirstThousand(look1));
		File look2 = new File("src/main/resources/look2.jpg");
		finalist.put("look2.jpg", sumOfFirstThousand(look2));
		File look3 = new File("src/main/resources/look3.jpg");
		finalist.put("look3.jpg", sumOfFirstThousand(look3));
		
		Comparator<? super Entry<String, Byte>> comparator = new Comparator<Entry<String, Byte>>()
	    {
	        public int compare(Entry<String, Byte> e0, Entry<String, Byte> e1)
	        {
	        	Byte v0 = e0.getValue();
	        	Byte v1 = e1.getValue();
	            return v0.compareTo(v1);
	        }
	    };
	    
	    PriorityQueue<Entry<String, Byte>> highest = new PriorityQueue<Entry<String,Byte>>(3, comparator);
	    for (Entry<String, Byte> entry : finalist.entrySet())
	    {
	        highest.offer(entry);
	    }
	    
	    List<Entry<String, Byte>> result = new ArrayList<Map.Entry<String, Byte>>();
	    
	    while (highest.size() > 0)
	    {
	        result.add(highest.poll());
	    }
	     
	    for (Entry<String, Byte> entry : result)
        {
            System.out.println(entry);
        }
	}

	public static byte sumOfFirstThousand(File file) {
		
		byte sum = 0;
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
 
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
        	FileInputStream fis = new FileInputStream(file);
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum); 
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        byte[] bytes = bos.toByteArray(); 
        
        for(int i =0;i<1000;i++) {
        	sum+=bytes[i];
        }
               
        return sum;
	}
}
