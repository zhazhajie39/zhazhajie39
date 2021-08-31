package com.dims.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemLog {

	private final static String logdir="D:\\EclipseProject\\DIMS\\LOG";//默认文件夹
	private  SystemLog() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SystemLog.logDirCreate(logdir);
		//SystemLog.getDate();
		//		try {
		//			SystemLog.out("adsdasdasdas");
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}
	/**
	 * 
	 * @param path log存放路径
	 * @param content log内容
	 * @param Date log打印的时间精确到时分秒
	 * @throws IOException 
	 */
	public static void out(String content) throws IOException {
		logDirCreate(logdir);//创建默认存放log的目录
		
		String fn=logdir+"\\"+getDate(0)+".log";//创建文件 文件名为日期yymmdd.log
		if(logFileCreate(fn)) {//true代表文件创建成功和文件存在
			//开始写入数据
			FileWriter writer = new FileWriter(fn,true);//追加方式写入
			//向文件中写入内容
			String data=getDate(1)+"		"+content;
			writer.write(data+"\r\n");//换行处理
			writer.flush();
			writer.close();//关闭
		}
	}
	private  static void logDirCreate(String path) {
		File file=new File(path);
		if(!file.exists()){//如果文件夹不存在
			file.mkdir();//创建文件夹
			System.out.println("创建成功");
		}
	}
	private static boolean logFileCreate(String filepath)
	{
		 File file = new File(filepath);
         //创建一个新文件
		 if(!file.exists()) {
          boolean fvar=false;
		try {
			fvar = file.createNewFile();
			file.setWritable(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return fvar;
		 }
		 return true;
	}
public static String getDate(int type) {
	Date date=new Date();
	//DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
	DateFormat format;
		switch(type) {
		case 1:format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");break;
		case 2:format=new SimpleDateFormat("yyyyMMdd-HHmmss");break;
		case 3:format=new SimpleDateFormat("yyyy-MM-dd");break;
		default:format=new SimpleDateFormat("yyyyMMdd");break;
		}
	return format.format(date);
}
	
}
