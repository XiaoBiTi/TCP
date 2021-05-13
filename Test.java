import java.net.Socket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;

class TCP{
	private String ip = "192.168.1.102";
	
	/**�޲������췽��*/
	public TCP() {
	
	}
	
	/**��ȡip*/
	public String getIp(){
		return this.ip;
	}
	
	/**��������*/
	public void send(String message) throws IOException{
		//�����ͻ���Socket
		Socket socket = new Socket(ip,10088);
		//��ȡ�������д����
		OutputStream os = socket.getOutputStream();
		os.write(message.getBytes());
		//�ͷ���Դ
		socket.close();
	}
	
	/**��������*/
	public String receive() throws IOException{
		//���������ServerSocket
		ServerSocket serverSocket = new ServerSocket(10088);
		//�����ͻ���accept()
		Socket socket = serverSocket.accept();
		//��ȡ��������������
		InputStream is = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len = is.read(bytes);
		String data = new String(bytes,0,len);
		serverSocket.close();
		return data;
	}
}

public class Test{
	public static void main(String[] args) throws IOException
		TCP tcp = new TCP();
		Scanner scanner = new Scanner(System.in);
		//��ط�������
		Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
					
					try{
						tcp.send(scanner.next());
					}catch(IOException e){
						System.out.println(e.getMessage());
					}
				}
            }
        });
		sendThread.start();
	}
}