package automation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@SuppressWarnings("unchecked")
public class Response {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		
		String stringSplit[] = null, Strategy,stringSplit2[];
		File file1 = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\logs\\Response.xml");
        File newFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\logs\\Response.json");
        if(file1.renameTo(newFile)){
            System.out.println("File rename success");;
        }else{
            System.out.println("File rename failed");
        }
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\logs\\Response.json"));
        JSONObject jsonObject =  (JSONObject) obj;
        JSONArray cars = (JSONArray) jsonObject.get("Response");
        String txt =cars.toString();
        int index1 = txt.indexOf("<DE_SPL_Buydown>");
       String  RiskGp = txt.substring(index1+16, index1+17);
         
         System.out.println(RiskGp);
 //        String RiskGp = "Risk Group 3";
 //        stringSplit2 = RiskGp.split("p ");
// 		String rk = stringSplit2[1];
// 		int rg = Integer.parseInt(rk);
// 		System.out.println(rg);
//         double RandomNum = Double.valueOf(roar);
// 		int RandomNumber = (int) RandomNum;
// 		System.out.println(RandomNumber);
//SPL
 /*		 int index1 = txt.indexOf("<RandomNumber_Internal_SPLInterestRate>");
 		System.out.println("Index of the letter w :: "+index1); //+39, +41
 		String roar1 = txt.substring(18686, 18688);
        System.out.println(roar1); //Applicant 1
 */
      
    
    
}}
