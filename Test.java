import java.net.Socket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;

class TCP{
	private String ip = "192.168.1.102";
	
	/**无参数构造方法*/
	public TCP() {
	
	}
	
	/**获取ip*/
	public String getIp(){
		return this.ip;
	}
	
	/**发送数据*/
	public void send(String message) throws IOException{
		//创建客户端Socket
		Socket socket = new Socket(ip,10088);
		//获取输出流，写数据
		OutputStream os = socket.getOutputStream();
		os.write(message.getBytes());
		//释放资源
		socket.close();
	}
	
	/**接收数据*/
	public String receive() throws IOException{
		//创建服务端ServerSocket
		ServerSocket serverSocket = new ServerSocket(10088);
		//侦听客户端accept()
		Socket socket = serverSocket.accept();
		//获取输入流，读数据
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
		//监控发送数据
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