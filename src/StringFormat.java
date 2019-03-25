import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.sql.Date;

import java.util.regex.Pattern;

public class StringFormat {
	ArrayList<String> SymbolsToAvoid;
	String [] TargetString;
	private static final String StopWords_FILE_PATH = "lib/StopWords.txt";


	
	public static ArrayList<String> FindStopWords() throws FileNotFoundException{
		Scanner stop_word_string = new Scanner(new File(StopWords_FILE_PATH));
		ArrayList<String> list = new ArrayList<String>();
		while (stop_word_string.hasNext()){
		    list.add(stop_word_string.next());
		}
		stop_word_string.close();
		return list;
		
	}
	
	public static String ReplaceNonAlphaNumeric(String TargetString) {

		TargetString = TargetString.replaceAll("[^A-Za-z0-9.$\\xA2-\\xA5\\u058F\\u060B\\u09F2\\u09F3\\u09FB\\u0AF1\\u0BF9\\u0E3F\\u17DB\\u20A0-\\u20BD\\uA838\\uFDFC\\uFE69\\uFF04\\uFFE0\\uFFE1\\uFFE5\\uFFE6]"," ");
		
		return TargetString;
	}
	public static String FormatWhiteSpace(String TargetString) {
		TargetString = TargetString.replace("  ", " ");
		return TargetString;
	}
	public static String MatchPattern(ArrayList<String> stop, String TargetString) {
		String [] str_stop_arr = stop.toArray(new String [stop.size()]);
		String stopWordsPattern = String.join("|", str_stop_arr);
		Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(TargetString);
		TargetString = matcher.replaceAll("");
		return TargetString;

	}
	public static String MakeStandardASCII(String string) {
		String resultString = string.replaceAll("[^\\x00-\\x7F]", "");
		return resultString;
	}
	
	

	public static String[] ToLower(String[] str_list) {
		String [] return_str = new String[str_list.length];
	
		for(int i= 0; i <= str_list.length - 1; i++) {
			return_str[i] = str_list[i].toLowerCase();
		}
		
		return return_str;
	}
	public static String Format_title(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
	}
	
	public static String Format_headline(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
		
	}

	public static String Format_description(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
	}
	
	public static String Format_content_type(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		assert(string.equals("article") || string.equals("video"));
		return string;
	}
		
	public static java.sql.Timestamp Format_publish_date(String string) {
		String [] dateTime  = string.split("T");
		String date = dateTime[0];
		String time = dateTime[0].split("\\+")[0];

		try {
			
			//convert from string to dt and back
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern();
			java.util.Date utilDate  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(string);
			java.sql.Date sql_date = new java.sql.Date(utilDate.getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sql_date);
			java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
			return timestamp;
			
			
		}
		catch(Exception e) {
			//default to current time string if parse fails
			return null;
			
		}
		
		
		
	}
	public static String Format_slug(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
	}
	
	public static String Format_state(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		assert(string.equals("published") || string.equals("not published"));
		return string;
	}
	
	public static String Format_duration(String string) {
		try {
			Integer.parseInt(string);
			return string;
		}
		catch(NumberFormatException e) {
			//default to 0 if parse error 
			return "0";
		}
	}
	
	public static String Format_video_series(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
	}
	
	public static String Format_author(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
	}
	
	public static String Format_tag(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
	}
	
	public static String Format_url(String string) {
		try {
			URL url = new URL(string);
			url.toURI();
			return string;
		}
		catch(Exception e) {
			return "";
		}
	
	}
	
	public static String Format_size(String string) {
		string = ReplaceNonAlphaNumeric(string);
		string = FormatWhiteSpace(string);
		return string;
	}
	
	public static String Format_height_width(String string) {
		try {
			Integer.parseInt(string);
			return string;
		}
		catch(NumberFormatException e) {
			return "0";
		}
	}

	public static DataObject ValidateDataObject(DataObject x) {
		// TODO Auto-generated method stub
		x.title = Format_title(x.title);
		x.headline = Format_description(x.description);
		
		x.author_1 = Format_author(x.author_1);
		x.author_2 = Format_author(x.author_2);
		x.tag_1 = Format_tag(x.tag_1);
		x.tag_2 = Format_tag(x.tag_2);
		x.tag_3 = Format_tag(x.tag_3);
		x.video_series = Format_video_series(x.video_series);
		x.description = Format_description(x.description);
		x.timestamp = Format_publish_date(x.publish_date);
		x.state = Format_state(x.state);
		x.duration = Format_duration(x.duration);
		
		x.thumbnail_1_width = Format_height_width(x.thumbnail_1_width);
		x.thumbnail_1_height = Format_height_width(x.thumbnail_1_height);
		x.thumbnail_1_URL = Format_url(x.thumbnail_1_URL);
		x.thumbnail_1_size = Format_size(x.thumbnail_1_size);
		
		x.thumbnail_2_width = Format_height_width(x.thumbnail_2_width);
		x.thumbnail_2_height = Format_height_width(x.thumbnail_2_height);
		x.thumbnail_2_URL = Format_url(x.thumbnail_2_URL);
		x.thumbnail_2_size = Format_size(x.thumbnail_2_size);
		
		x.thumbnail_3_width = Format_height_width(x.thumbnail_3_width);
		x.thumbnail_3_height = Format_height_width(x.thumbnail_3_height);
		x.thumbnail_3_URL = Format_url(x.thumbnail_3_URL);
		x.thumbnail_3_size = Format_size(x.thumbnail_3_size);
		
		x.content_type = Format_content_type(x.content_type);
		return x;
	}
	
	

}
