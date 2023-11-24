Manual de Uso da Aplicação de Cadastro e Venda de Carros

°Visão Geral
Este manual descreve como usar a aplicação de cadastro e venda de carros. A aplicação permite cadastrar informações sobre carros, clientes e realizar vendas. Ela é composta por três painéis principais: CarrosPainel, ClientesPainel, e VendaCarrosPainel.

°Iniciando a Aplicação
Abra seu ambiente de desenvolvimento Java.
Importe os arquivos fonte da aplicação.
Execute a classe principal (geralmente aquela com o método main), que pode ser a classe que contém o método main ou um arquivo separado para a inicialização da aplicação.
Painel de Cadastro de Carros (CarrosPainel)
Este painel é responsável pelo cadastro, edição e exclusão de informações sobre carros.

°Cadastrar um Novo Carro
Preencha os campos obrigatórios no painel de entrada de dados.
Marca
Modelo
Ano
Preço
Cor
Placa
Clique no botão "Cadastrar".
Uma mensagem de confirmação será exibida, indicando que o carro foi cadastrado com sucesso.

°Editar um Carro Existente
Selecione um carro na tabela.
Os campos de entrada de dados serão preenchidos automaticamente com as informações do carro selecionado.
Faça as alterações desejadas nos campos.
Clique no botão "Editar".
Uma mensagem de confirmação será exibida, indicando que o cadastro foi atualizado com sucesso.

°Excluir um Carro Existente
Selecione um carro na tabela.
Clique no botão "Apagar".
Uma mensagem de confirmação será exibida, indicando que o cadastro foi apagado com sucesso.
Painel de Cadastro de Clientes (ClientesPainel)
Este painel é responsável pelo cadastro, edição e exclusão de informações sobre clientes.

°Cadastrar um Novo Cliente
Preencha os campos obrigatórios no painel de entrada de dados.
Nome
CPF
Telefone
E-mail
Endereço
Clique no botão "Cadastrar".
Uma mensagem de confirmação será exibida, indicando que o cliente foi cadastrado com sucesso.

°Editar um Cliente Existente
Selecione um cliente na tabela.
Os campos de entrada de dados serão preenchidos automaticamente com as informações do cliente selecionado.
Faça as alterações desejadas nos campos.
Clique no botão "Editar".
Uma mensagem de confirmação será exibida, indicando que o cadastro foi atualizado com sucesso.

°Excluir um Cliente Existente
Selecione um cliente na tabela.
Clique no botão "Apagar".
Uma mensagem de confirmação será exibida, indicando que o cadastro foi apagado com sucesso.
Painel de Venda de Carros (VendaCarrosPainel)
Este painel permite selecionar um carro disponível para venda.

°Selecionar Carro para Venda
Escolha um carro no menu suspenso (JComboBox).
O JComboBox é preenchido com os carros disponíveis.
Selecione o carro desejado no menu.
O painel está pronto para integrar com funcionalidades adicionais de venda.

°Considerações Finais
A aplicação fornece uma interface intuitiva para cadastrar e gerenciar informações sobre carros e clientes, além de facilitar a seleção de carros disponíveis para venda. Siga as instruções deste manual para aproveitar ao máximo os recursos da aplicação.


DOCUMENTAÇÃO:

ConnectionFactory:
O código fornece uma implementação simples de uma fábrica de conexões JDBC para um banco de dados PostgreSQL em Java. Ele possui métodos para obter uma conexão com o banco de dados, fechar a conexão, fechar a conexão junto com um objeto PreparedStatement e fechar a conexão, PreparedStatement e ResultSet. Os atributos incluem informações como a URL do banco, nome do usuário e senha do administrador. O manual de uso destaca a obtenção de conexão, a execução de consultas usando PreparedStatement, e enfatiza a importância de fechar adequadamente os recursos para evitar vazamento de recursos.

CarrosControl:
O código é um controlador em Java para interação entre uma interface gráfica Swing e operações em um banco de dados PostgreSQL relacionadas a carros. Ele inclui métodos para cadastrar, atualizar e apagar registros de carros, validações de dados, atualização da tabela de exibição e uso de caixas de diálogo para interação com o usuário. O código depende de uma classe CarrosDAO para realizar as operações no banco de dados.

CarrosDAO:
O código representa um Data Access Object (DAO) em Java para interagir com um banco de dados PostgreSQL, realizando operações CRUD relacionadas a carros. Ele utiliza a classe Carros para modelar os dados e a classe ConnectionFactory para obter conexões com o banco. Os métodos incluem a criação da tabela, listagem de todos os carros, cadastro, atualização e exclusão de registros no banco de dados. As operações são realizadas de maneira segura usando PreparedStatement para evitar injeção de SQL, e a conexão é devidamente gerenciada. Mensagens de status são impressas no console após cada operação.

ClientesDAO:
O código implementa um Data Access Object (DAO) em Java para interagir com um banco de dados PostgreSQL, executando operações CRUD relacionadas a clientes. Ele inclui métodos para criar uma tabela, listar todos os clientes, cadastrar, atualizar e apagar registros. A conexão com o banco é gerenciada pela classe ConnectionFactory. As operações são realizadas de forma segura, utilizando PreparedStatement para evitar injeção de SQL. O código depende da classe Clientes para modelar os dados e imprime mensagens informativas no console após cada operação no banco de dados.

ClientesControl:
O código representa um controlador em Java para interação entre uma interface gráfica Swing e operações em um banco de dados PostgreSQL relacionadas a clientes. Ele inclui métodos para cadastrar, atualizar e apagar registros de clientes, validações de dados, atualização da tabela de exibição e uso de caixas de diálogo para interação com o usuário. As operações são realizadas de maneira segura, com verificação de dados antes de realizar as ações no banco de dados. Mensagens informativas são exibidas ao usuário após cada operação. O código depende de uma classe ClientesDAO para realizar as operações no banco de dados.

CarrosPainel:
O código representa um painel de interface gráfica em Java para um sistema de cadastro de carros. Ele inclui campos para inserir informações de carros, botões para cadastrar, editar e apagar registros, e uma tabela para exibir dados. A interação com o banco de dados PostgreSQL é realizada por meio das classes CarrosControl e CarrosDAO. O código implementa funcionalidades de validação, tratamento de eventos e atualização dinâmica da tabela. O design visual é organizado verticalmente usando um layout de caixa (BoxLayout). As operações no banco de dados são realizadas de forma segura, com validações para garantir a integridade dos dados.

ClientesPainel:
O código representa um painel de interface gráfica em Java para um sistema de cadastro de clientes. Ele inclui campos para inserir informações de clientes, botões para cadastrar, editar e apagar registros, e uma tabela para exibir dados. A interação com o banco de dados PostgreSQL é realizada por meio das classes ClientesControl e ClientesDAO. O código implementa funcionalidades de validação, tratamento de eventos e atualização dinâmica da tabela. O design visual é organizado verticalmente usando um layout de caixa (BoxLayout). As operações no banco de dados são realizadas de forma segura, com validações para garantir a integridade dos dados.

VendasCarrosPainel:
O código representa um painel de interface gráfica em Java para uma funcionalidade de venda de carros. O painel inclui um JComboBox que permite selecionar um carro a ser vendido. Os dados dos carros são obtidos do banco de dados por meio da classe CarrosDAO. O JComboBox é inicializado com a opção "Selecione o Carro", seguida pela lista de carros disponíveis. A classe possui um método atualizarComboBox() que atualiza dinamicamente o conteúdo do JComboBox com os carros mais recentes do banco de dados. Este painel pode ser integrado a uma aplicação mais ampla para gerenciar operações de venda de carros.







