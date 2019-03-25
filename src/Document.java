import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;


public class Document {
	public ArrayList<String> documentStrings;
	public ArrayList<DocumentNode> documentNodeList;
	
	
	public String Guid;
	public Document(String Guid, ArrayList<String> documentStrings) {
		this.Guid = Guid;
		this.documentStrings = documentStrings;
		this.documentNodeList = new ArrayList<DocumentNode>();
		
	}
	
	public Document(Document d) {
		this.Guid = d.Guid;
		this.documentStrings = d.documentStrings;
		this.documentNodeList = d.documentNodeList;
	}
	
	public static ArrayList<DocumentNode> InitializeNodeList(Document document){
		for(String string : document.documentStrings) {
			//construct a node for each word
			String guid = document.Guid;
			String Word = string;
			//construct node
			DocumentNode node = new DocumentNode(guid, Word);
			//add to nodeList
			document.documentNodeList.add(node);
		}
		return document.documentNodeList;
	}
	public static int CalculateNumDocumentOccurrances(String Word) {
		DocumentDictionary dict = DocumentDictionary.getInstance();
		int DocumentCount = 0;
		if(dict.DocumentMasterList.size() > 0) {
			//proceed with calcuations
			
			for(Document document : dict.DocumentMasterList) {
				for(DocumentNode node : document.documentNodeList) {
					if(node.Word.equals(Word)) {
						//strings have equal value -- increment but only ONCE per document
						DocumentCount++;
						break;
					}
				}
			}
			return DocumentCount;
		}
		else {
			return 0;
		}
	}
	
	public static void CalculateTF_IDF() {
		DocumentDictionary dict = DocumentDictionary.getInstance();
		if(dict.DocumentMasterList.size() > 0) {
			//proceed with calcuations
			
			for(Document document : dict.DocumentMasterList) {
				for(DocumentNode node : document.documentNodeList) {
					//calculate TF_IDF
					Double TF_IDF = node.TF * node.IDF;
					node.TF_IDF = TF_IDF;
				}
			}
			
		}

	}
	
	public static ArrayList<Document> MergeDocumentLists(Document one, Document two) {
		ArrayList<DocumentNode> merge_list_one = new ArrayList<DocumentNode> ();
		ArrayList<DocumentNode> merge_list_two = new ArrayList<DocumentNode> ();
		ArrayList<Document> returnValues = new ArrayList<Document>();
		//find elements in doc one not in doc two
		for(DocumentNode node : one.documentNodeList) {
			if(!two.documentNodeList.contains(node)) {
				//if node with same name does not exist in list two
				//instantiate a Blank copy of node to act as a proxy when we perform consine similarity
				DocumentNode blank = new DocumentNode(node.Guid, node.Word);
				merge_list_one.add(blank);
			}
		}
		
		for(DocumentNode node : two.documentNodeList) {
			if(!one.documentNodeList.contains(node)) {
				//if node with same name does not exist in list two
				//instantiate a Blank copy of node to act as a proxy when we perform consine similarity
				DocumentNode blank = new DocumentNode(node.Guid, node.Word);
				merge_list_two.add(blank);
			}
		}
		
		one.documentNodeList.addAll(merge_list_two);
		Collections.sort(one.documentNodeList);
		two.documentNodeList.addAll(merge_list_one);
		Collections.sort(two.documentNodeList);
		returnValues.add(one);
		returnValues.add(two);
		return returnValues;
		
	
	}
	public static Double DotProduct(ArrayList<DocumentNode> a, ArrayList<DocumentNode> b) {
		Double RunningTotal = 0.0d;
		//already ensured that arrays are the same length and track the same information in MergeDocumentLists method
		for(int i = 0; i <= a.size() - 1; i++) {
			RunningTotal += a.get(i).TF_IDF * b.get(i).TF_IDF;
		}
		return RunningTotal;
	}
	
	public static CosineSimilarityNode CalculateCosineSimilarity(Document one , Document two) {
		Double CosineSimilarity;
		//calculate dot product of one and two
		Double dotProduct = DotProduct(one.documentNodeList, two.documentNodeList);
		
		
		//calculate the magnitude of a
		Double MagnitudeA = Magnitude(one.documentNodeList);
		
		
		//calculate the magnitude of b
		Double MagnitudeB = Magnitude(two.documentNodeList);
		
		//plug into the formula and return
		//to ensure we don't encounter a division error 
		if(dotProduct == 0.0d && (MagnitudeA == 0.0d ||MagnitudeB == 0.0d)) {
			CosineSimilarity = 0.0d;
			CosineSimilarityNode c = new CosineSimilarityNode(two.Guid, CosineSimilarity);
			return c;
		}
		else {
			CosineSimilarity = dotProduct/(MagnitudeA * MagnitudeB);
			CosineSimilarityNode c = new CosineSimilarityNode(two.Guid, CosineSimilarity);
			return c;
		}
		 
		
	
	}

	private static Double Magnitude(ArrayList<DocumentNode> documentNodeList) {
		Double Magnitude = Math.sqrt(DotProduct(documentNodeList, documentNodeList));
		return Magnitude;
	}

	public static Document findbyGuid(String guid) {
		// TODO Auto-generated method stub
		DocumentDictionary dict = DocumentDictionary.getInstance();
		if(dict.DocumentMasterList.size() > 0) {
			//proceed with calcuations
			
			for(Document document : dict.DocumentMasterList) {
				if(document.Guid.equals(guid)) {
					return document;
				}
			
		}
	}
		return null;
	}
	



}
