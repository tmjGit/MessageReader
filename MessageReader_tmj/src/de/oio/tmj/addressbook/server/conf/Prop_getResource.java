package de.oio.tmj.addressbook.server.conf;
/**
 * InputStream in=Prop_getResource.class.getResourceAsStream(PROPERTY_PATH);
 * This seems to not work with GWT!
 */















//package de.oio.tmj.addressbook.shared.conf;
//
//import java.io.BufferedInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
////import java.time.format.DateTimeFormatter;
//import java.util.Properties;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
//
//public class Prop_getResource {
//	
//	public static void main(String[] args) {
//		System.out.println("Prop: "+personNameMaxLength());
//	}
//	
////	final static Logger logger = LoggerFactory.getLogger(Prop.class);
//	public final static String PROPERTY_PATH = "/addressbook.properties"; // Absolute path based on src folder, no relative paths!
//	private static Properties props;
//	private Prop_getResource(){}
//		
//	static{
//		props = new Properties();
//		try {
////			logger.trace("Prop.load: Property_Path="+PROPERTY_PATH);
//			InputStream in=Prop_getResource.class.getResourceAsStream(PROPERTY_PATH);
//			if(null==in) {
////				logger.warn("Could not open main properties file \"{}\" (\"{}\")",theoreticalPath(PROPERTY_PATH),PROPERTY_PATH);
//			}else {
//				props.load(new BufferedInputStream(in) );
//			}
//////			logger.trace("Prop.load: Property_Path=/application.properties");
////			in=Prop.class.getResourceAsStream("/application.properties");
////			if(null==in) {
//////				logger.warn("Could not open application properties file \"{}\" (\"/application.properties\")",theoreticalPath("/application.properties"));
////			}else {
////				props.load(new BufferedInputStream(in) );
////			}
//			if(null!=propertiesFile()) {
//				in=Prop_getResource.class.getResourceAsStream(propertiesFile());
//				if(null==in) {
////					logger.info("Could not open second properties file \"{}\" (\"{}\")",theoreticalPath(propertiesFile()),propertiesFile());
//				}else {
//					props.load(new BufferedInputStream(in) );
//				}
//			}
//			
//		} catch (FileNotFoundException e) {
////			logger.error("FileNotFoundException loading properties! ",e);
////			throw new RuntimeException(e);
//		} catch (IOException e) {
////			logger.error("IOException loading properties! ",e);
////			throw new RuntimeException(e);
//		}
//	}
//	
//	private static String getProperty(String key, String defaultValue) {
//		String s=props.getProperty(key,defaultValue);
////		logger.debug("Getting property: key=\"{}\", defaultValue=\"{}\", returning \"{}\"",key,defaultValue,s);
//		return s;
//	}
//	
//	private static String getProperty(String key) {
//		String s=props.getProperty(key);
////		logger.debug("Getting property: key=\"{}\", defaultValue=null, returning \"{}\"",key,s);
//		return s;
//	}
//	
////	public static int bufferSize() {
////		return Integer.valueOf(getProperty("buffersize","1024"));
////	}
//	
//	//Does not work with GWT?
////	public static DateTimeFormatter dateTimeFormatter() {
////		 try {
////			 return DateTimeFormatter.ofPattern(getProperty("datetimeformat","yyyy-MM-dd hh:mm:ss"));
////		 }catch(Throwable t){
//////			logger.error("Could not get property dateTimeFormatter!",t);
////			return DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
////		 }
////	}
//	
//	public static int personNameMaxLength() {
//		return Integer.valueOf(getProperty("personNameMaxLength","50"));
//	}
//
//	public static String propertiesFile() {
//		String s=getProperty("propertiesfile");
////		if(null==s) { return null; }
//		return s;//.replaceAll("\\", "\\\\");
//	}
//	
////	public static int serverPort() {
////		String s=getProperty("server.port","8888");
////		return Integer.valueOf(s);
////	}
//	
//	private static String theoreticalPath(String resourcePath) {
//		if(resourcePath.startsWith("/")) {
//			return Prop_getResource.class.getResource("/").toString()+resourcePath.substring(1);
////				Paths.get(resource.toURI()).toFile();
//		}
//		if(":".equals( resourcePath.substring(1,2) ))	{
//			return resourcePath;
//		}
//		return Prop_getResource.class.getResource("").toString()+resourcePath;
//	}
//
////	public static String logFilename() {
////		return getProperty("logfilename","webwatchdog.log");
////	}
////	
////	public static String logFilepath() {
////		return getProperty("logfilepath","");
////	}
////	
////	public static String logLevel() {
////		return getProperty("loglevel","");
////	}
//	
//}
//
