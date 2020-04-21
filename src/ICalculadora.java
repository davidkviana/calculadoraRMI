import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote{

	//Protótipo do método soma de dois números
	public double soma(double a, double b) throws RemoteException;
	
	//Protótipo do método subtrai dois números
	public double subtrai(double a, double b) throws RemoteException;
	
	//Protótipo do método multiplica dois números
	public double multiplica(double a, double b) throws RemoteException;
	
	//Protótipo do método divisão de dois números
	public double divide(double a, double b) throws RemoteException;
	
	//Protótipo do método soma de dois números inteiros
	public int soma(int a, int b) throws RemoteException;
}
