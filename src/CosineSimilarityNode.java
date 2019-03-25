
public class CosineSimilarityNode implements Comparable<CosineSimilarityNode> {
	public String documentguid;
	public Double similarityValue;
	public CosineSimilarityNode(String documentguid, Double similarityValue) {
		this.documentguid = documentguid;
		this.similarityValue = similarityValue;
	}
	@Override
	public int compareTo(CosineSimilarityNode o) {
		// TODO Auto-generated method stub
		if(this.similarityValue > o.similarityValue) {
			return 1;
		}
		else if(this.similarityValue < o.similarityValue) {
			return -1;
		}
	
		else {
			return 0;

		}
	}
	
	
	
}
