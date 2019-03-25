import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvBindByName;
public class DataObject {
	@CsvBindByName
	protected String content_id;
	@CsvBindByName
	protected String content_type;
	@CsvBindByName
	protected String title;
	@CsvBindByName
	protected String headline;
	@CsvBindByName
	protected String description;
	@CsvBindByName
	protected String publish_date;
	//exists for saving to db
	protected Timestamp timestamp;
	@CsvBindByName
	protected String slug;
	//protected String description;
	@CsvBindByName
	protected String state;
	@CsvBindByName
	protected String duration;
	@CsvBindByName
	protected String video_series;
	@CsvBindByName
	protected String author_1;
	@CsvBindByName
	protected String author_2;
	@CsvBindByName
	protected String tag_1;
	@CsvBindByName
	protected String tag_2;
	@CsvBindByName
	protected String tag_3;
	@CsvBindByName
	protected String thumbnail_1_URL;
	@CsvBindByName
	protected String thumbnail_1_size;
	@CsvBindByName
	protected String thumbnail_1_width;
	@CsvBindByName
	protected String thumbnail_1_height;
	@CsvBindByName
	protected String thumbnail_2_URL;	
	@CsvBindByName
	protected String thumbnail_2_size;
	@CsvBindByName
	protected String thumbnail_2_width;
	@CsvBindByName
	protected String thumbnail_2_height;
	@CsvBindByName
	protected String thumbnail_3_URL;	
	@CsvBindByName
	protected String thumbnail_3_size;
	@CsvBindByName
	protected String thumbnail_3_width;	
	@CsvBindByName
	protected String thumbnail_3_height;
	protected String DocumentString;
	protected Boolean IsValid = true;
	public String getDocumentString() {
		return DocumentString;
	}
	public void setDocumentString(String DocumentString) {
		this.DocumentString = DocumentString;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVideo_series() {
		return video_series;
	}
	public void setVideo_series(String video_series) {
		this.video_series = video_series;
	}
	public String getAuthor_1() {
		return author_1;
	}
	public void setAuthor_1(String author_1) {
		this.author_1 = author_1;
	}
	public String getAuthor_2() {
		return author_2;
	}
	public void setAuthor_2(String author_2) {
		this.author_2 = author_2;
	}
	public String getTag_1() {
		return tag_1;
	}
	public void setTag_1(String tag_1) {
		this.tag_1 = tag_1;
	}
	public String getTag_2() {
		return tag_2;
	}
	public void setTag_2(String tag_2) {
		this.tag_2 = tag_2;
	}
	public String getTag_3() {
		return tag_3;
	}
	public void setTag_3(String tag_3) {
		this.tag_3 = tag_3;
	}
	public String getThumbnail_1_URL() {
		return thumbnail_1_URL;
	}
	public void setThumbnail_1_URL(String thumbnail_1_URL) {
		this.thumbnail_1_URL = thumbnail_1_URL;
	}
	public String getThumbnail_1_size() {
		return thumbnail_1_size;
	}
	public void setThumbnail_1_size(String thumbnail_1_size) {
		this.thumbnail_1_size = thumbnail_1_size;
	}
	public String getThumbnail_1_width() {
		return thumbnail_1_width;
	}
	public void setThumbnail_1_width(String thumbnail_1_width) {
		this.thumbnail_1_width = thumbnail_1_width;
	}
	public String getThumbnail_1_height() {
		return thumbnail_1_height;
	}
	public void setThumbnail_1_height(String thumbnail_1_height) {
		this.thumbnail_1_height = thumbnail_1_height;
	}
	public String getThumbnail_2_URL() {
		return thumbnail_2_URL;
	}
	public void setThumbnail_2_URL(String thumbnail_2_URL) {
		this.thumbnail_2_URL = thumbnail_2_URL;
	}
	public String getThumbnail_2_size() {
		return thumbnail_2_size;
	}
	public void setThumbnail_2_size(String thumbnail_2_size) {
		this.thumbnail_2_size = thumbnail_2_size;
	}
	public String getThumbnail_2_width() {
		return thumbnail_2_width;
	}
	public void setThumbnail_2_width(String thumbnail_2_width) {
		this.thumbnail_2_width = thumbnail_2_width;
	}
	public String getThumbnail_2_height() {
		return thumbnail_2_height;
	}
	public void setThumbnail_2_height(String thumbnail_2_height) {
		this.thumbnail_2_height = thumbnail_2_height;
	}
	public String getThumbnail_3_URL() {
		return thumbnail_3_URL;
	}
	public void setThumbnail_3_URL(String thumbnail_3_URL) {
		this.thumbnail_3_URL = thumbnail_3_URL;
	}
	public String getThumbnail_3_size() {
		return thumbnail_3_size;
	}
	public void setThumbnail_3_size(String thumbnail_3_size) {
		this.thumbnail_3_size = thumbnail_3_size;
	}
	public String getThumbnail_3_width() {
		return thumbnail_3_width;
	}
	public void setThumbnail_3_width(String thumbnail_3_width) {
		this.thumbnail_3_width = thumbnail_3_width;
	}
	public String getThumbnail_3_height() {
		return thumbnail_3_height;
	}
	public void setThumbnail_3_height(String thumbnail_3_height) {
		this.thumbnail_3_height = thumbnail_3_height;
	}
	public String toString() {
		return this.content_id + " " + this.title;
		
	}
	
	public static ArrayList<DataObject> parseListFromResultSet(ResultSet rs) throws SQLException{
		ArrayList<DataObject> result = new ArrayList<DataObject>();
		while(rs.next()) {
			DataObject obj = new DataObject();
			obj.content_id = rs.getString("content_id");
			obj.content_type = rs.getString("content_type");
			obj.publish_date = rs.getDate("publish_date").toString();
			obj.title = rs.getString("title");
			obj.headline = rs.getString("headline");
			obj.slug = rs.getString("slug");
			obj.duration = rs.getString("duration");
			obj.description = rs.getString("description");
			obj.tag_1 = rs.getString("tag_1");
			obj.tag_2 = rs.getString("tag_2");
			obj.tag_3 = rs.getString("tag_3");
			obj.video_series = rs.getString("video_series");
			obj.author_1 = rs.getString("author_1");
			obj.author_2 = rs.getString("author_2");
			obj.thumbnail_1_URL = rs.getString("thumbnail_1_URL");
			obj.thumbnail_1_size = rs.getString("thumbnail_1_size");
			obj.thumbnail_1_width = rs.getString("thumbnail_1_width");
			obj.thumbnail_1_height = rs.getString("thumbnail_1_height");
			
			obj.thumbnail_2_URL = rs.getString("thumbnail_2_URL");
			obj.thumbnail_2_size = rs.getString("thumbnail_2_size");
			obj.thumbnail_2_width = rs.getString("thumbnail_2_width");
			obj.thumbnail_2_height = rs.getString("thumbnail_2_height");
			
			obj.thumbnail_3_URL = rs.getString("thumbnail_3_URL");
			obj.thumbnail_3_size = rs.getString("thumbnail_3_size");
			obj.thumbnail_3_width = rs.getString("thumbnail_3_width");
			obj.thumbnail_3_height = rs.getString("thumbnail_3_height");
			result.add(obj);
			
		}
		return result;
		
	}
	public static ArrayList<ArrayList<String>> StartProcessingNodes(ArrayList<DataObject> obj_list, ArrayList<String> content_guids) throws FileNotFoundException {
		// TODO Auto-generated method stub
	    ArrayList<String> stop_string = new ArrayList<String>();
	    DocumentDictionary dict = DocumentDictionary.getInstance();
	    stop_string = StringFormat.FindStopWords();
		for(DataObject x : obj_list) {
	    	String DocumentString = x.headline + " " + x.title + " " + x.description;
	    	
	    	//x.DocumentString = RemoveStopWords.MatchPattern(stop_string, DocumentString);
	    	x.DocumentString = StringFormat.ReplaceNonAlphaNumeric(DocumentString);
	    	x.DocumentString = StringFormat.FormatWhiteSpace(x.DocumentString);
	    	//parse document object
	    	String [] str_list = StringUtils.split(x.DocumentString);
	    	str_list = StringFormat.ToLower(str_list);
	    	
	    	List<String> tmpList = Arrays.asList(str_list);
	    	//sort list for later
	    	Collections.sort(tmpList);
	    	ArrayList<String> document_str_list = new ArrayList();
	    	//add list to arraylist
	    	document_str_list.addAll(tmpList);
	    	
	    	Document doc = new Document(x.content_id, document_str_list);
	    	//add to DocumentList
	    	dict.DocumentMasterList.add(doc);
	    	//System.out.println(document_str_list.get(0));
	    	
	    }
	   
		//initialize Node Lists
		
		for(Document document : dict.DocumentMasterList) {
			document.documentNodeList = Document.InitializeNodeList(document);
		}
		
		for(Document document : dict.DocumentMasterList) {
			for(DocumentNode node : document.documentNodeList) {
				//calculate TF
				node = DocumentNode.CalculateTF(node, document.documentStrings);
				node = DocumentNode.CalculateIDF(node, document.documentStrings);
				Document.CalculateTF_IDF();
			}
		}
		
		//need to make document node lists unique for ease of processing now that the calculations are done.
		
		for(Document document : dict.DocumentMasterList) {
			ArrayList<DocumentNode> uniqueNodes = new ArrayList<DocumentNode>();
			for(int i = 0; i <= document.documentNodeList.size() - 1; i++  ) {
					if(!uniqueNodes.contains(document.documentNodeList.get(i))) {
						//add it
						uniqueNodes.add(document.documentNodeList.get(i));
					}
			}
			//before moving to the next document, copy over the list values
			document.documentNodeList = uniqueNodes;
		}
		//make copy of the document for which we want to base our recommendations off of
		ArrayList<ArrayList<CosineSimilarityNode>> cs_list = new ArrayList<ArrayList<CosineSimilarityNode>>();
		
		ArrayList<ArrayList<String>>ret_list = new ArrayList<ArrayList<String>>();
		
		//do this for each element in content_guids
		for(String content : content_guids) {
			ArrayList<CosineSimilarityNode> ndx_list = new ArrayList<CosineSimilarityNode>();
			ArrayList<CosineSimilarityNode> cs_list_selected = new ArrayList<CosineSimilarityNode>();
			for(int i = 0; i <= dict.DocumentMasterList.size() - 1; i++) {
				Document ReviewCopy =  new Document(Document.findbyGuid(content));
				ArrayList<Document> Doc_list = Document.MergeDocumentLists(ReviewCopy, dict.DocumentMasterList.get(i));
				//always has two elements the doc under review and what it's being compared to 
				CosineSimilarityNode CosineSimiliarity = Document.CalculateCosineSimilarity(Doc_list.get(0), Doc_list.get(1));
				ndx_list.add(CosineSimiliarity);
			}
		
			Collections.sort(ndx_list, Collections.reverseOrder());
			
			cs_list.add(ndx_list);
		}


		
		System.out.println("Given what you've enjoyed previously, here is some other content you might enjoy: ");
		
		
		for(ArrayList<CosineSimilarityNode> cs : cs_list) {
			ArrayList<String> str_for_guid = new ArrayList<String>();
			for(int i = 1; i <= 3; i++) {
				str_for_guid.add(cs.get(i).documentguid);
			}
			ret_list.add(str_for_guid);
		}
	
		return ret_list;
		
		
	}
	
}
