1.������Ŀ
new Eclipse 4 Application Project

2.�޸ı����ͼ��
Application.e4xmi -> Windows -> Trimmed Window
�޸�Label��Icon URI��ͼƬʹ��32*32����

3.���õ�¼����
MANIFEST.MF -> Extensions -> org.eclipse.core.runtime.products
���property��lifeCycleURI = bundleclass://com.dsideal.jbp.login/com.dsideal.jbp.login.Login

MANIFEST.MF -> Dependencies
�������com.dsideal.jbp.login

com.dsideal.jbp.product -> Dependencies -> Add Required Plug-ins
�������com.dsideal.jbp.login����������ʱȱʧ����

��¼��ϸ�μ�com.dsideal.jbp.login���

4.��Ļ��С��λ��
Application.e4xmi -> Windows -> Trimmed Window -> Bounds(x,y,w,h)
������ô���ܾ�����ʾ��? ��ʱ��֪��, ��ʱ���ó�ȫ����ʾ

ȫ����ʾ
Application.e4xmi -> Windows -> Trimmed Window -> Supplementary
���tags��shellMaximized
��С����shellMinimized

5.ע������
���г���ʱ���Run Configurations����
�޸Ĳ��������޸�Run Configurations����