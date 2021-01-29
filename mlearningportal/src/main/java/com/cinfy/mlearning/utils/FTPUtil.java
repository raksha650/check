package com.cinfy.mlearning.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.Base64;

//FTP Username: tourism@praadiscg.com
//FTP server: ftp.praadiscg.com
//FTP & explicit FTPS port:  21
public class FTPUtil {
	
	static  String server = "ftp.cinfysystems.com";
	static  int port = 21;
	static  String user = "mlearning@mlearning.cinfysystems.com";
	static  String pass = "Cinfy!systems.com";
	static String domainName="https://mlearning.cinfysystems.com/";
	

	
	
	public static String uplo(MultipartFile file,String folder){

	    FTPClient con = null;
	  
	    String storeLoc="";
	    String newFilename="";
	    try {
	        con = new FTPClient();
	        con.connect(server);

	        if (con.login(user, pass)) {
	            con.enterLocalPassiveMode(); // important!
	            con.setFileType(FTP.BINARY_FILE_TYPE);
	            newFilename=System.currentTimeMillis()+"_"+file.getOriginalFilename().replaceAll(" ","_");
                storeLoc=folder+"/"+newFilename;
                System.out.println("storeLoc)"+storeLoc);
	            boolean result = con.storeFile(storeLoc, file.getInputStream());
	            System.out.println("You successfully result " + result + "!");
	            con.logout();
	            con.disconnect();
	           // redirectAttributes.addFlashAttribute("message",
	            System.out.println("You successfully uploaded " + file.getOriginalFilename() + "!");
	                    
	        }
	    } catch (Exception e) {
	      //  redirectAttributes.addFlashAttribute("message",
	                //"//Could not upload " + file.getOriginalFilename() + "!");
	    	e.printStackTrace();
	    }
	  //  return "http://www.praadiscg.com/tourism/"+storeLoc;
	    return domainName+"/"+folder+"/"+newFilename;
	}
	
	public static String uploadFileByte(String fileString ,String fileName, String folder) {
	      

		 
        FTPClient ftpClient = new FTPClient();
        String filePath="";
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
           
            filePath=folder+"/"+System.currentTimeMillis()+"_"+"fileName";
           
            byte[] mydata = Base64.decode(fileString);
            InputStream stream = new ByteArrayInputStream(mydata);
            ftpClient.storeFile(filePath, stream);
            stream.close();
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
       // return "http://www.praadiscg.com/tourism/"+filePath;
        return domainName+"/"+filePath;
    }
	
	
	
//	public static String uploadFile(File file,String folder) {
//	      
//
//		 
//        FTPClient ftpClient = new FTPClient();
//        String filePath="";
//        try {
// 
//            ftpClient.connect(server, port);
//            ftpClient.login(user, pass);
//            ftpClient.enterLocalPassiveMode();
// 
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
// 
//            // APPROACH #1: uploads first file using an InputStream
//            File firstLocalFile =  file;
// 
//            filePath = file.getName();
//            InputStream inputStream = new FileInputStream(file);
// 
//            System.out.println("Start uploading first file");
//            boolean done = ftpClient.storeFile(filePath, inputStream);
//            inputStream.close();
//            if (done) {
//                System.out.println("The first file is uploaded successfully.");
//               // return "http://www.praadiscg.com/tourism/"+filePath;
//                return domainName+uploadDir+filePath;
//            }
// 
//            // APPROACH #2: uploads second file using an OutputStream
//           /* File secondLocalFile = new File("C:\\Users\\jpathak\\Desktop\\images\\logo.png");
//            String secondRemoteFile = "test/logo.png";
//            inputStream = new FileInputStream(secondLocalFile);
// 
//            System.out.println("Start uploading second file");
//            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
//            byte[] bytesIn = new byte[4096];
//            int read = 0;
// 
//            while ((read = inputStream.read(bytesIn)) != -1) {
//                outputStream.write(bytesIn, 0, read);
//            }*/
//           /* inputStream.close();*/
//          //  outputStream.close();
// 
//           /* boolean completed = ftpClient.completePendingCommand();
//            if (completed) {
//                System.out.println("The second file is uploaded successfully.");
//            }*/
// 
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        
//      //  return "http://www.praadiscg.com/tourism/"+filePath;
//        return domainName+uploadDir+filePath;
//    }
	
	/* public static void main(String[] args) {
	      

	 
	        FTPClient ftpClient = new FTPClient();
	        try {
	 
	            ftpClient.connect(server, port);
	            ftpClient.login(user, pass);
	            ftpClient.enterLocalPassiveMode();
	 
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	 
	            // APPROACH #1: uploads first file using an InputStream
	            File firstLocalFile =  new File("C:\\Users\\jpathak\\Desktop\\Airport.jpg");
	 
	            String firstRemoteFile = "beacon_images/Airport.jpg";
	            InputStream inputStream = new FileInputStream(firstLocalFile);
	 
	            System.out.println("Start uploading first file");
	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
	            inputStream.close();
	            if (done) {
	                System.out.println("The first file is uploaded successfully.");
	            }
	 
	            // APPROACH #2: uploads second file using an OutputStream
	            File secondLocalFile = new File("C:\\Users\\jpathak\\Desktop\\images\\logo.png");
	            String secondRemoteFile = "test/logo.png";
	            inputStream = new FileInputStream(secondLocalFile);
	 
	            System.out.println("Start uploading second file");
	            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
	            byte[] bytesIn = new byte[4096];
	            int read = 0;
	 
	            while ((read = inputStream.read(bytesIn)) != -1) {
	                outputStream.write(bytesIn, 0, read);
	            }
	            inputStream.close();
	          //  outputStream.close();
	 
	            boolean completed = ftpClient.completePendingCommand();
	            if (completed) {
	                System.out.println("The second file is uploaded successfully.");
	            }
	 
	        } catch (IOException ex) {
	            System.out.println("Error: " + ex.getMessage());
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }*/
}
