# calculadoraRMI

# O servidor
Nesta prática foram usados apenas 3 "classes": Calculadora, CalculadoraCliente e ICalculadora (na verdade é uma interface).

RMI é Remote Method Invoke. Em resumo, serve para invocar métodos de objetos remotos.

Para isso é necessário criar uma classe, que implemente uma interface, e esta interface é que estão declarados os métodos que podem ser chamados de outras máquinas que não tenham esses métodos implementados, precisam apenas ter a interface.

Aqui temos a classe Calculadora que implementa a interface ICalculadora que possui os métodos soma, subtrai, divide e multiplica. Estes métodos precisam estar implementados na classe Calculadora apenas. A classe do cliente só precisa chamar os métodos.

Contudo, no computador que "serve" as classes e os métodos, é preciso que o objeto remoto 'UnicastRemoteObject.exportObject' seja iniciado em alguma porta, no caso desta prática foi na 1100. Este médoto 'UnicastRemoteObject.exportObject' é que criará os stubs para a comunicação do cliente com o objeto.

Após inciar o objeto remoto é necessário criar o registro do objeto e acessar o registro do objeto que normalmente usa-se a porta 1099. Os métodos 'LocateRegistry.createRegistry(1099);' e 'LocateRegistry.getRegistry(1099);' fazem a função de registrar, uma após a outra. 

No entanto, para o cliente poder acessar a classe a partir do serviço de nomes do objeto remoto é necessário que o o registro seja ligada aos stubs e ao nome através da chamada 'reg.rebind("calculadora", stub);'. 

Assim, são 4 passos para um objeto estar disponível para ser acessado remotamente:

1 - Criar o objeto a partir de uma interface;

2 - Criar os stubs do objeto ... 'stub = UnicastRemoteObject.exportObject'

3 - Registrar o objeto ... 'reg = LocateRegistry.createRegistry(1099);' 'reg = LocateRegistry.getRegistry(1099);'

4 - Religar o registro aos stubs e ao nome da classe ... reg.rebind("calculadora", stub);

# O cliente

Do lado do cliente ele simplesmente deverá ter a mesma interface e utilizará a chamada do registro 'reg = LocateRegistry.getRegistry(1099);'. 

E na sequencia usará o método lookup para procurar o objeto através do nome que foi registrado no servidor... 'calc = (ICalculadora) reg.lookup("calculadora");' 

Dessa forma o objeto calc poderá chamar qualquer método da sua interface, que segue o mesmo padrão da interface do servidor e contudo, não houve a necessidade de implementar nenhum método, apenas usá-los.

# Como funciona?

 Os stubs fazem a comunicação entre o cliente e o servidor, que implementa de forma transparente uma comunicação através de sockets TCP, e implementa um protocolo de comunicação para realizar as chamadas entre os métodos e as passagens de parâmetros que são necessárias.
 
# O exemplo em questão
Foi implementado uma 'calculadora' com 5 métodos, 'int soma(int a, int b)', 'double soma(int double, int double)', 'double subtrai(int double, int double)', 'double divide(int double, int double)' e 'double multiplica(int double, int double)'.

Dessa forma, no exemplo, o cliente se conecta e executa cada um dos métodos sem utilizar a classe, apenas através do RMI.

Deixando um pouco mais simples a implementação da comunicação e do protocolo que normalmente precisa ser definido quando se usa os exemplos HTTP e socket.
