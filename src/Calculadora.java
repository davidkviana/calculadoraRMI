import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe calculadora que implementa a interface ICalculadora, isso significa
 * que existe um contrato a ser seguido, a interface contém os métodos que serão 
 * distribuídos e deverão estar implementados na classe calculadora.
 */
public class Calculadora  implements ICalculadora {
	private static final long serialVersionUID = 1L;
	
	//Variável que é incrementada a cada vez que algum método remoto é chamado.
	private static int chamadas = 0;

	//Método soma inicialemnte implementado recebendo 2 inteiros e retorando 1 inteiro
	//incrementa o número de chamadas logo em seguida.
	public int soma(int a, int b) throws RemoteException {
		System.out.println("Método soma chamado " + chamadas++);
		return a + b;
	}

	//Método soma implementado recebendo 2 doubles e retornando a soma em um double
	//incrementa o número de chamadas logo em seguida.
	public double soma(double a, double b) throws RemoteException {
		System.out.println("Método soma chamado " + chamadas++);
		return a + b;
	}
	
	//Método subtrai implementado recebendo 2 doubles e retornando a subtração em um double
	//incrementa o número de chamadas logo em seguida.
	public double subtrai(double a, double b) throws RemoteException {
		System.out.println("Método subtrai chamado " + chamadas++);
		return a - b;
	}
	
	//Método multiplica implementado recebendo 2 doubles e retornando a multiplicação em um double
	//incrementa o número de chamadas logo em seguida.
	public double multiplica(double a, double b) throws RemoteException {
		System.out.println("Método multiplica chamado " + chamadas++);
		return a * b;
	}
	
	//Método divide implementado recebendo 2 doubles e retornando a divisão em um double
	//incrementa o número de chamadas logo em seguida.
	public double divide(double a, double b) throws RemoteException {
		System.out.println("Método divide chamado " + chamadas++);
		return a / b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		//Inicia a classe calculadora;
		Calculadora calculadora = new Calculadora();
		
		//Declara o registry para a classe ser registrada para a chamada remota.
		Registry reg = null;
		
		//Interface Icalculadora iniciando o objeto remoto para a classe calculadora poder ser exportada. 
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.exportObject(calculadora, 1100);
		try {
			System.out.println("Creating registry...");
			/**
			 * Aqui é criado e exportado a instância do registry que 
			 * usará sockets para a comunicação com a instância remota.
			 * O registry irá escutar os encaminhamentos das requisições
			 * sobre a porta passada usando ServerSocket para dar suporte
			 * ao RMIServerSocketFactory. 
			 */
			reg = LocateRegistry.createRegistry(1099); //Criando o registry
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);//Registrando
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		//Aqui é aonde os stubs são ligados ao registry 
		//e o nome do objeto remoto é atrelado ao nome. 
		reg.rebind("calculadora", stub);
	}
}
