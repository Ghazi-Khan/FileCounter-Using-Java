package Controller;

import java.io.File;
import java.util.Map;
import java.util.Date;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.stream.Stream;
import java.text.SimpleDateFormat;
import java.util.function.Function;
import java.util.stream.Collectors; 
//FileGeneratingLog
class FileGeneratingLog{
	
	//method group and count the number of file with same extension 
	static Map<String ,Long>  fileCountWithExt(Path p)throws Exception {

    	Stream<Path> stream = Files.list(p); 	//Store the path of files of all files
  		Map<String, Long> fileExtCountMap = stream.filter(Files::isRegularFile)		
        .map(f -> f.getFileName().toString().toLowerCase())
        .map(n -> n.substring(n.lastIndexOf(".") + 1))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return fileExtCountMap;
	}

	//method print all the file count of specific extension
	 static void printExt(File f,String fp,int init_lev) throws Exception {
		long number_of_files=0;
 		String f_path=null;
 		if(init_lev==0)		//Checking whether method is invoke by main of listOutFiles
 		f_path=fp;		//invoke by main method
 		else
 			f_path=fp+"/"+f.getName();
		Map<String,Long> fileWithExt= fileCountWithExt(Paths.get(f_path));
		if(!fileWithExt.isEmpty()){				//if not empty print all the file with count 
			System.out.print(" (");
			for(Map.Entry<String,Long> it : fileWithExt.entrySet())
				number_of_files+=it.getValue();
				//System.out.print(it.getKey()+" = "+it.getValue()+" ");
			System.out.print(number_of_files+")");
		}
	}


	 static void listOutFiles(File f[],int min_level,String full_path) throws Exception {

		for(int i=0;i<f.length;i++){
				if(f[i].isDirectory()){
				for(int j=0;j<=min_level;j++)
					System.out.print("  ");		//4 space for the next level
				System.out.print(f[i].getName());		//Printing the parent directory
				printExt(new File(f[i].getAbsolutePath()),full_path,1);		//print the file count of specific extension
				System.out.println("");
				listOutFiles(f[i].listFiles(),min_level+1,f[i].getAbsolutePath());	//Check sub directory
			}	
		}
	}

	//main method :( lol..!
	public static int mainLog(String source_path,String des_path) throws Exception {
         //       MainFramController mfc=new MainFramController();
		String path= source_path;    //mfc.getPath();
                String d_path= des_path;
		File[] list;
		int min_level;
		File file = null;
                PrintStream reportFile=null;
		//Map<String, Long> fileExtCountMap;
		//final PrintStream oldStream=System.out; 
				
			//Taking path as commandLine Argument
			
			min_level=0;

			//Replace all the occurrence of escape characters
			path=path.replace('\\','/');
                        
			
			try{
				//Initializing the file object
				file =new File(path);

				//Checking whether the specified file is a directory or not
			//	if(file.isDirectory() &&file.exists()){
                                        if(des_path==null){
                                               d_path= "./Report.txt";
                                         }
                                        else{
                                            d_path=d_path.replace('\\', '/');
                                           // d_path+= "./Report.txt";
                                        }
                                        
                                        //Redirecting Output to the Report.txt File
                                        reportFile= new PrintStream(d_path);                                            
                                        System.setOut(reportFile);

					//Writting the time and date while report is generated
					DateFormat format =new SimpleDateFormat("dd/MM//yy HH:mm:ss");
					Date dateObj=new Date();
					System.out.println("Report generated at: "+format.format(dateObj));

					list=file.listFiles();		//copy the list of all the files and directory into list array
	    			System.out.print("\n"+file.getName());
	    			//Path path_class = Paths.get(path);
	    			printExt(file,path,0);
	    			System.out.println("");
					//System.out.println(" "+fileCountWithExt(path_class));
	    			listOutFiles(list,min_level,path);
			//	}
			//	else{
					//System.setOut(oldStream);
					//System.out.println("Oops! It seems the provded Directory doesn't exists.");
					//System.exit(0);
			//	}

			}	catch (Exception e) {
	         // if any runtime error occurs
	         e.printStackTrace();
	      }
                        finally{
              reportFile.close();              
        }
	      System.out.println("\n-----------------------------------------------");
	      
	      //Redirecting the stream to the Standerd stream :) hurry
	     // System.setOut(oldStream);
	      //System.out.println("Report successfully generated..! :)");
              
              return 1;
	}
}