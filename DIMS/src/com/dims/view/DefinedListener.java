package com.dims.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 * ���˵��ڲ���������¼�
 * @author 86157
 *
 */
public class DefinedListener implements  WindowListener {

	JInternalFrame inframe;
	JDesktopPane dp;
	public DefinedListener() {
		// TODO Auto-generated constructor stub
		inframe=new JInternalFrame("����");
	}
	public DefinedListener(JInternalFrame inframe,JDesktopPane dp) {
		// TODO Auto-generated constructor stub
		this.inframe=inframe;
		this.dp=dp;
		System.out.println(dp);
		this.dp.add(this.inframe);
	}

	public void windowActivated(WindowEvent e) {// ���屻����ʱ����
	//System.out.println("���ڱ����");
	// TODO Auto-generated method stub
	/**
	 * ������ڱ��رջ�Ϊ�գ����������״̬ʱ
	 * ���¼����ڲ����屣֤�ڲ����岻��ʧ
	 */
	if(inframe==null||inframe.isClosed()) {
//		JInternalFrame[] allframe=dp.getAllFrames();
//		int count=allframe.length;
//		int tbh=30*count;//����ÿ���ڲ������ˮƽƫ����
//		int x=10+tbh,y=x;//������ʾ��λ��
//		inframe=new UserInFrame("����");
//		inframe.setBounds(x, y, 250, 180);
//		inframe.setVisible(true);
//		dp.add(inframe);
//		try {
//			inframe.setSelected(true);//ѡ�д���
//		} catch (PropertyVetoException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		System.out.println("��ִ��");
	}
	else {
	System.out.println("�����ڲ�����");
	}
}
public void windowOpened(WindowEvent e) {// ���屻��ʱ����
	
}
public void windowIconified(WindowEvent e) {// ���屻��С��ʱ����
	
}
public void windowDeiconified(WindowEvent e) {// ���屻����С��ʱ����
	
}
public void windowClosing(WindowEvent e) {// ���彫Ҫ���ر�ʱ����
	
}
// ���岻�ٴ��ڼ���״̬ʱ����
public void windowDeactivated(WindowEvent e) {
	
	
}
public void windowClosed(WindowEvent e) {// �����Ѿ����ر�ʱ����

}
	

}
