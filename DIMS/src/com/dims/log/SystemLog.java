package com.dims.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemLog {

	private final static String logdir="D:\\EclipseProject\\DIMS\\LOG";//Ĭ���ļ���
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
	 * @param path log���·��
	 * @param content log����
	 * @param Date log��ӡ��ʱ�侫ȷ��ʱ����
	 * @throws IOException 
	 */
	public static void out(String content) throws IOException {
		logDirCreate(logdir);//����Ĭ�ϴ��log��Ŀ¼
		
		String fn=logdir+"\\"+getDate(0)+".log";//�����ļ� �ļ���Ϊ����yymmdd.log
		if(logFileCreate(fn)) {//true�����ļ������ɹ����ļ�����
			//��ʼд������
			FileWriter writer = new FileWriter(fn,true);//׷�ӷ�ʽд��
			//���ļ���д������
			String data=getDate(1)+"		"+content;
			writer.write(data+"\r\n");//���д���
			writer.flush();
			writer.close();//�ر�
		}
	}
	private  static void logDirCreate(String path) {
		File file=new File(path);
		if(!file.exists()){//����ļ��в�����
			file.mkdir();//�����ļ���
			System.out.println("�����ɹ�");
		}
	}
	private static boolean logFileCreate(String filepath)
	{
		 File file = new File(filepath);
         //����һ�����ļ�
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
