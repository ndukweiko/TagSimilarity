import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Collections;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.Calendar;
public class main  {
	
 private static final String CSV_FILE_PATH = "lib/codefoo.csv";
	
	public static void main(String [] args) throws IOException {
		
		//File file = new File(".");
		//for(String fileNames : file.list()) System.out.println(fileNames);	 
		try(
			
		    Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
	){
	
			
	CsvToBean<DataObject> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(DataObject.class)
                   	.withIgnoreLeadingWhiteSpace(true)
                   	.build();
                    
		    
		    List<DataObject> obj_list = csvToBean.parse();
		
		    ArrayList <String> guid_list = new ArrayList<String>();
		    
		    for(DataObject x : obj_list) {
		    	//validate csv data
		    	if(!guid_list.contains(x.content_id)) {
		    		guid_list.add(x.content_id);
		    	  	x = StringFormat.ValidateDataObject(x);
		    	}
		    	else {
		    		//duplicated row validation
		    		x.IsValid = false;
		    	}
		  
		    		    	
		    }
		   
	
			
			try{  
				MysqlDataSource dataSource = new MysqlDataSource();
				
				dataSource.setUser(args[0]);
				dataSource.setPassword(args[1]);
				
				dataSource.setServerName(args[2]);
			    dataSource.setPortNumber(3306);
			    dataSource.setDatabaseName("IGN_Content");
				Connection conn = (Connection) dataSource.getConnection();
				String checkstatusofDB = String.format("Select * from content");
				java.sql.PreparedStatement preliminaryStmt = conn.prepareStatement(checkstatusofDB);
				Boolean CreateFromFile = false;
				ResultSet testSet = preliminaryStmt.executeQuery();
				if(!testSet.isBeforeFirst()) {
					//no data
					CreateFromFile = true;
					
				}
				
				if(CreateFromFile == true) {
					for(DataObject x : obj_list) {
						if(x.IsValid == true) {
						      // the mysql insert statement
							
						  String query = String.format(" insert into content (content_id, title, content_type, headline, description"
						  		+ ", publish_date, slug, state, duration, video_series, author_1, author_2, tag_1, tag_2, tag_3,"
						  		+ "thumbnail_1_URL, thumbnail_1_size, thumbnail_1_width, thumbnail_1_height, thumbnail_2_URL,"
						  		+ "thumbnail_2_size, thumbnail_2_width, thumbnail_2_height, thumbnail_3_URL, thumbnail_3_size, "
						  		+ "thumbnail_3_width, thumbnail_3_height"
						  		+ ")"
						        + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?)");

						      // create the mysql insert preparedstatement
						      java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
						      preparedStmt.setString(1, x.content_id);
						      preparedStmt.setString(2, x.title);
						      preparedStmt.setString(3, x.content_type);
						      preparedStmt.setString(4, x.headline);
						      preparedStmt.setString(5, x.description);
						      preparedStmt.setTimestamp(6, x.timestamp);
						      preparedStmt.setString(7, x.slug);
						      preparedStmt.setString(8, x.state);
						      preparedStmt.setString(9, x.duration);
						      preparedStmt.setString(10, x.video_series);
						      preparedStmt.setString(11, x.author_1);
						      preparedStmt.setString(12, x.author_2);
						      preparedStmt.setString(13, x.tag_1);
						      preparedStmt.setString(14, x.tag_2);
						      preparedStmt.setString(15, x.tag_3);
						      preparedStmt.setString(16, x.thumbnail_1_URL);
						      preparedStmt.setString(17, x.thumbnail_1_size);
						      preparedStmt.setString(18, x.thumbnail_1_width);
						      preparedStmt.setString(19, x.thumbnail_1_height);
						      preparedStmt.setString(20, x.thumbnail_2_URL);
						      preparedStmt.setString(21, x.thumbnail_2_size);
						      preparedStmt.setString(22, x.thumbnail_2_width);
						      preparedStmt.setString(23, x.thumbnail_2_height);
						      preparedStmt.setString(24, x.thumbnail_3_URL);
						      preparedStmt.setString(25, x.thumbnail_3_size);
						      preparedStmt.setString(26, x.thumbnail_3_height);
						      preparedStmt.setString(27, x.thumbnail_3_width);
						      //preparedStmt.setDate(27, null);
						      preparedStmt.executeUpdate();
						      
						}
					}
					
					
		
				}
				//read from database after writing
				ArrayList<DataObject> sql_data = new ArrayList<DataObject>();
				ArrayList<ArrayList<String>> guids_of_content = new ArrayList<ArrayList<String>>();
				String retrieveData = String.format("Select * from content");
				java.sql.PreparedStatement sqlRetrieveStmt = conn.prepareStatement(retrieveData);
				ResultSet rs = sqlRetrieveStmt.executeQuery();
				
				sql_data = DataObject.parseListFromResultSet(rs);
				
				//get user data from db
				String retrieveuser = String.format("Select content_id from user_content where user_id = (?)");
				java.sql.PreparedStatement userStmt = conn.prepareStatement(retrieveuser);
				userStmt.setString(1, args[3]);
				ResultSet us = userStmt.executeQuery();
				ArrayList<String> content_guids = new ArrayList<String>();
				while(us.next()) {
					content_guids.add(us.getString("content_id"));
				}
			
				
				
				
				
				
				guids_of_content = DataObject.StartProcessingNodes(sql_data, content_guids);
				for(ArrayList <String> list : guids_of_content) {
					
						String answer_query = String.format("Select content_id, description from content where content_id "
								+ "in " + "(?, ?, ?)");
						java.sql.PreparedStatement lastStmt = conn.prepareStatement(answer_query);
						lastStmt.setString(1, list.get(0));
						lastStmt.setString(2, list.get(1));
						lastStmt.setString(3, list.get(2));
						ResultSet last = lastStmt.executeQuery();
						while(last.next()){
							System.out.println(last.getString("content_id") + " "+ last.getString("description"));
						}
					
			
				}
		
				conn.close();
				
				

				
			   
	
			}
			catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		
	
		}
		
	


		


	 
	}

}
