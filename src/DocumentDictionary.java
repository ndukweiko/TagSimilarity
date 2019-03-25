import java.util.ArrayList;

public class DocumentDictionary {
//singleton to manage the strings present in each of the rows of data  -- maps strings to their number
//of total occurrances to help us calculate the idf values
	public ArrayList<Document> DocumentMasterList;
	private static DocumentDictionary dictionary_instance = null;
	private DocumentDictionary() {
		this.DocumentMasterList = new ArrayList<Document>();
	}
	
	//get method used by other files
	public static DocumentDictionary getInstance() {
		if(dictionary_instance == null) {
			// 
			dictionary_instance = new DocumentDictionary();
			
		}
		return dictionary_instance;
	}
	
	
}
