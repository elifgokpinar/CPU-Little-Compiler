
/* 
Elif Gökpınar
Ezgi Doğruer
İsra Nur Alperen */

import java.io.*;


public class Assembly_Language {
		
	public static void main(String[] args) throws IOException {
	
		int j=0,i;
	 
		String[] arr = new String[4]; 
		String strLine;
		
		//file input-output
		FileInputStream fstream = new FileInputStream("input.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		
		
		System.out.println("Output :\n");
		
		
		File file = new File("out.hex");
        if (!file.exists()) {
            file.createNewFile();
        }
		try (FileWriter fileWriter = new FileWriter(file, false)) {
			fileWriter.write("v2.0 raw\n\n");
      
		// this loop will receive and process input values ​​while reading the file
        while ((strLine = br.readLine()) != null) {
			if(strLine.equals("")) 
				continue;
		
			
			String[] splitted = strLine.split(" |, |,"); //splitting the inputs by commas 
			
			
			for ( i = 0; i < arr.length; i++) {
				
				arr[i] = null; //in the beginning setting null values ​​to arr 
			}
			
			// putting each input value into arr array by removing the commas
			
			for ( j = 0; j < splitted.length && arr[j] == null; j++) {
				
				arr[j] = splitted[j].replaceAll("[,]", ""); //Replace commas with empty elements 
			
			}
			
			// For each input, it calls the function that I convert to assembly cod and writes the results to the file.
			fileWriter.write(ConvertAssembly(arr)+" ");
			
			System.out.println(ConvertAssembly(arr)); //writing results
		}
        
	}
		br.close();
	}
	
	public static String ConvertAssembly(String[] arr ) {
		
		String opcode=arr[0]; // first element in arr array is opcode
		String imm="",op1="",op2="",address="";
	    String desRegister="",sourceReg1="",sourceReg2="";
	   
	    String finalString="",result="";
	    
	    
		switch(opcode) {
		 
		  
		  case "AND": 
			 
			  finalString= finalString.concat("000"); // AND instruction value is 000
			  // Destination Register :
			  desRegister = registerToBinary(arr[1],3); // convert first register to binary
			 
			  finalString=finalString.concat(desRegister); //add the binary result to final string result
			  // Source Register 1 :
			  sourceReg1 = registerToBinary(arr[2],3);  // convert second register to binary
			 
              finalString=finalString.concat(sourceReg1); //add the binary result to final string result
              
              finalString= finalString.concat("0000"); // adding 0000 value to final string to get 16 bits
              // Source Register 2:
              sourceReg2 = registerToBinary(arr[3],3); // convert third register to binary
              
              finalString=finalString.concat(sourceReg2); //add the binary result to final string result
              
              
              result = binaryToHex(finalString); // convert the final result to hexadecimal 
              
			break;
			
		  case "ANDI": 
			  
			  finalString= finalString.concat("001"); // ANDI instruction value is 000
			  
			  desRegister = registerToBinary(arr[1],3); // convert first register to binary
			  
			  finalString=finalString.concat(desRegister); //add the binary result to final string result
			  
			  // Source Register 1 :
			  sourceReg1 = registerToBinary(arr[2],3); // convert second register to binary
			  
			  finalString=finalString.concat(sourceReg1); //add the binary result to final string result
			  
			  //IMM value :
			  if(arr[3].contains("-")) {
				  finalString= finalString.concat("1"); // if imm value negative adding 1 value to final string to get 16 bits
			  }
			  else {
				  finalString= finalString.concat("0"); // if imm value positive adding 0 value to final string to get 16 bits
			  }
			  
              imm = registerToBinary(arr[3],6);  // convert imm value to binary --6 bits
              
              finalString=finalString.concat(imm);  //add the binary result to final string result
              
              
              result = binaryToHex(finalString);  // convert the final result to hexadecimal 
              
			break;
			
		  case "ADD":
			  finalString= finalString.concat("010"); // ADD instruction value is 001
			   
			  // Destination Register :
			  desRegister = registerToBinary(arr[1],3);  // convert first register to binary
			  
			  finalString=finalString.concat(desRegister); //add the binary result to final string result
			  
			  // Source Register 1 :
			  sourceReg1 = registerToBinary(arr[2],3); // convert second register to binary
			  
			  finalString=finalString.concat(sourceReg1); //add the binary result to final string result
			  
			  finalString= finalString.concat("0000"); // adding 0000 value to final string to get 16 bits
              // Source Register 2:  
              sourceReg2 = registerToBinary(arr[3],3); // convert third register to binary
              
              finalString=finalString.concat(sourceReg2); //add the binary result to final string result
      
              result = binaryToHex(finalString); // convert the final result to hexadecimal 
             
          break;
		    
		  case "ADDI":
			  
			  finalString= finalString.concat("011"); // ADDI instruction value is 001
			  //DEstination Register :
			  desRegister = registerToBinary(arr[1],3); // convert first register to binary
			  
			  finalString=finalString.concat(desRegister); //add the binary result to final string result
			  
			  // Source Register 1 :
			  sourceReg1 = registerToBinary(arr[2],3); // convert second register to binary
			  
			  finalString=finalString.concat(sourceReg1); //add the binary result to final string result
			  
			  //IMM value :
			  if(arr[3].contains("-")) {
				  finalString= finalString.concat("1"); // if imm value negative add 1 because of getting 16 bits
			  }
			  else {
				  finalString= finalString.concat("0"); // if imm value positive add 0 because of getting 16 bits
			  }
			  
              imm = registerToBinary(arr[3],6);  // convert imm value to binary --6 bits
              
              finalString=finalString.concat(imm); //add the binary result to final string result
              
              
              result = binaryToHex(finalString); // convert the final result to hexadecimal 
              
              
		    break;
			
		  case "LD": 
			  
			  finalString= finalString.concat("100");  // LD instruction value is 010
			  // Destination Register :
			  desRegister = registerToBinary(arr[1],3); // convert first register to binary
			  
			  finalString=finalString.concat(desRegister); //add the binary result to final string result
			  // Address : 
			  address= registerToBinary(arr[2],10); // convert address to binary -- 10 bits
			  
			  finalString=finalString.concat(address); //add the binary result to final string result
			
			  
			  result = binaryToHex(finalString); // convert the final result to hexadecimal 
				  
			break;
			
			
			
		  case "ST": 
			  
			  finalString= finalString.concat("101"); // ST instruction value is 100
			  // Source Register  : 
			  sourceReg1 = registerToBinary(arr[1],3); // convert first register to binary
			  
			  finalString=finalString.concat(sourceReg1); //add the binary result to final string result
			  // Address : 
			  address= registerToBinary(arr[2],10); // convert address to binary -- 10 bits
			  
			  finalString=finalString.concat(address); //add the binary result to final string result
			  
			   
			  result = binaryToHex(finalString); // convert the final result to hexadecimal 
              
			break;
			
	
			
		  case "CMP": 
			  finalString= finalString.concat("110"); // CMP instruction value is 110
			  //OP1 :  
			  op1=registerToBinary(arr[1],3); // convert first operation to binary 
			  
			  finalString= finalString.concat(op1);	 //add the binary result to final string result
			  
			  finalString= finalString.concat("0000000"); // adding 0000000 value to final string to get 16 bits
			  
			  //OP2 :
			  op2=registerToBinary(arr[2],3);   // convert second operation to binary
			  
			  finalString= finalString.concat(op2); //add the binary result to final string result
			 
			  
			  result = binaryToHex(finalString); // convert the final result to hexadecimal 
			  
			  break;
			
			
		  case "JUMP": 
			  finalString= finalString.concat("111");   // JUMP-JE-JA-JAE-JB-JBE instructions value is 111
			  
			  finalString= finalString.concat("000");   // adding 000 value to final string to get 16 bits
			  //Adress :
			  address=registerToBinary(arr[1], 10);     // convert address to binary --10 bit
			  finalString= finalString.concat(address); //add the binary result to final string result
			  
			  result = binaryToHex(finalString);	   // convert the final result to hexadecimal 
	           
             
			break;
		  case "JE": 
			  finalString= finalString.concat("111");   // JUMP-JE-JA-JAE-JB-JBE instructions value is 111
			  
			  finalString= finalString.concat("001");   // adding 001 value to final string to get 16 bits
			  //Adress :
			  address=registerToBinary(arr[1], 10);     // convert address to binary --10 bit
			  finalString= finalString.concat(address); //add the binary result to final string result
			  
			  result = binaryToHex(finalString);	   // convert the final result to hexadecimal 
	            
             
			break;
		  case "JA":
			  finalString= finalString.concat("111");   // JUMP-JE-JA-JAE-JB-JBE instructions value is 111
			  
			  finalString= finalString.concat("010");   // adding 010 value to final string to get 16 bits
			  //Adress :
			  address=registerToBinary(arr[1], 10);     // convert address to binary --10 bit
			  finalString= finalString.concat(address); //add the binary result to final string result
			  
			  result = binaryToHex(finalString);	   // convert the final result to hexadecimal 
	            
             
			break;
		  case "JAE": 
			  finalString= finalString.concat("111");   // JUMP-JE-JA-JAE-JB-JBE instructions value is 111
			  
			  finalString= finalString.concat("011");   // adding 011 value to final string to get 16 bits
			  //Adress :
			  address=registerToBinary(arr[1], 10);     // convert address to binary --10 bit
			  finalString= finalString.concat(address); //add the binary result to final string result
			  
			  result = binaryToHex(finalString);	   // convert the final result to hexadecimal 
	            
             
			break;
		  case "JB":
			  finalString= finalString.concat("111");   // JUMP-JE-JA-JAE-JB-JBE instructions value is 111
			  
			  finalString= finalString.concat("100");   // adding 000 value to final string to get 16 bits
			  //Adress :
			  address=registerToBinary(arr[1], 10);     // convert address to binary --10 bit
			  finalString= finalString.concat(address); //add the binary result to final string result
			  
			  result = binaryToHex(finalString);	   // convert the final result to hexadecimal 
	            
             
			break;
		  case "JBE": 
			  finalString= finalString.concat("111");   // JUMP-JE-JA-JAE-JB-JBE instructions value is 111
			  
			  finalString= finalString.concat("101");   // adding 000 value to final string to get 16 bits
			  //Adress :
			  address=registerToBinary(arr[1], 10);     // convert address to binary --10 bit
			  finalString= finalString.concat(address); //add the binary result to final string result
			  
			  result = binaryToHex(finalString);	   // convert the final result to hexadecimal 
	            
             
			break;  
		  default:
		    break;
		}
		
		
		return result;
	}
	
	
	
	// To check the R part to convert the register or imm  values ​​to binary
	public static String registerToBinary(String register,int size) {
		
		//for registers
		if(register.contains("R")) {
		String[] element = register.split("R");

		return decimalToBinary((Integer.parseInt(element[1])), size);
		}
		
		// for immediate values
		else {
			
			return decimalToBinary((Integer.parseInt(register)), size);
			
		}
		
		
	}
	
	 // converting decimal register to binary value
	 public static String decimalToBinary(int register, int size) {
		 
	        StringBuilder result = new StringBuilder();
	 
	        for (int i = size - 1; i >= 0 ; i--) {
	            int temp = 1 << i;
	            result.append((register & temp) != 0 ? 1 : 0);
	        }
	 
	        return result.toString();
	    }
	
	//converting binary result to hexadecimal value
	public static String binaryToHex(String binary) {
		int binarynum, temp;
        String hexresult="";
        String result="";
        // digits in hexadecimal number system
        
        char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        
        binarynum = Integer.parseInt(binary, 2); // convert binary string to binary int variable
        
        
        // converting the number in hexadecimal format
        while(binarynum>0)
        {
            temp = binarynum%16;
            hexresult = hex[temp] + hexresult;
            binarynum = binarynum/16;
        }
        
        // Checking hexresult's length because hex value must be 4 bits 
        
       if(hexresult.length()<4) {
    	   
    	   for(int i=0;i<4-hexresult.length();i++) {
    		result= "0"+result ;
    	   }
    	   
    	   result=result+hexresult;
    	   return result;
       }
		else {
			return hexresult; // result 4 bit
		}
        
	}
   	
}
