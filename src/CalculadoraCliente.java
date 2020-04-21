import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalculadoraCliente {
	
	public static void main(String[] args) {
		//Declarando a variável reg para o registro
		Registry reg = null;
		
		//Declarando a interface ICalculadora para calc ter acesso a seus métodos
		ICalculadora calc;		
		try {
			/**
			 * Assim como no servidor aqui é criado e exportado a instância 
			 * do registry que usará sockets para a comunicação com a instância remota.
			 * O registry irá escutar os encaminhamentos das requisições
			 * sobre a porta passada usando ServerSocket para dar suporte
			 * ao RMIServerSocketFactory. 
			 */
			reg = LocateRegistry.getRegistry(1099);
			
			//Neste ponto o registro busca o objeto através do nome, 
			//faz um cast para se apropriar dos métodos do objeto "calculadora".
			calc = (ICalculadora) reg.lookup("calculadora");
			System.out.println(calc.soma(3,2)); //soma de 3+2
			System.out.println(calc.subtrai(10.0, 2.7)); //subtração de 10.0-2.7
			System.out.println(calc.multiplica(3.9, 2.1)); //multiplicação de 3.9*2.1
			System.out.println(calc.divide(27.6, 3.3)); //divide de 3.5+27.03
			System.out.println(calc.soma(3.5, 27.03)); //soma de 3.5+27.03

		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
	}		

}
