import java.util.ArrayList;
import java.lang.Math;
public class DocumentNode implements Comparable<DocumentNode> {
	String Word;
	String Guid;//so that we can trace what document each word came from. Important for calculating the idf
	int NumOccurrance;
	int TotalListCount;
	Float TF;
	Double IDF;
	Double TF_IDF;
	public DocumentNode(String Guid, String Word) {
		this.Guid = Guid; 
		this.Word = Word;
		this.NumOccurrance = 0;
		this.TF_IDF = 0.0d;
		this.IDF = 0.0d;
		this.TF = 0.0f;
		
	}
	
	public static DocumentNode CalculateTF(DocumentNode Node, ArrayList<String> string_list){
		//calculate tf
		//find count of word in list
		int count = 0;
		for(String s : string_list) {
			if(s.equals(Node.Word)) {
				count++;
			}
			
		}
		//associate with object
		Node.NumOccurrance = count;
		
		
		//divide by total count of list
		Node.TF = ((float)(Node.NumOccurrance))/ string_list.size();
		
		
	
		
		
		return Node;
	}
	
	@Override
	public boolean equals(Object one) {
		if(one instanceof DocumentNode) {
			DocumentNode doc = (DocumentNode) one;
			if(this.Word.equals(doc.Word)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;

	}
	public static DocumentNode CalculateIDF(DocumentNode Node, ArrayList<String> string_list) {
		//calculate number of documents containing this specific word
		int NumberOfDocumentsContainingNodeWord = Document.CalculateNumDocumentOccurrances(Node.Word);
		//find number of documents in total
		DocumentDictionary dict = DocumentDictionary.getInstance();
		int TotalNumberofDocuments = dict.DocumentMasterList.size();
		
		double Quotient = TotalNumberofDocuments / NumberOfDocumentsContainingNodeWord;
		double Log = Math.log(Quotient);
		Node.IDF = Log;
		return Node;
	}

	@Override
	public int compareTo(DocumentNode o) {
		// TODO Auto-generated method stub
		if(this.Word.compareTo(o.Word) > 0) {
			return 1;
		}
		else if(this.Word.compareTo(o.Word) < 0) {
			return -1;
		}
		else {
			return 0;
		}
		
	}

}
