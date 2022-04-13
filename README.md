# Spaghetti Bridge Design

### DOCUMENTAÇÃO

![](https://i.ibb.co/W6sGrbx/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-001.jpg)

Requisitos do sistema:

- Java 8 ou superior
- Windows 7, 8 ou 10

Java JRE Download: https://www.java.com/pt-BR/download/manual.jsp

### Começando:

Instale o arquivo executável “SpaghettiBridgeDesign.exe”. Após concluir a instalação execute o arquivo clicando duas vezes sobre o mesmo.

![](https://i.ibb.co/ZVnyvBP/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-002.png)

*Arquivo executável do programa*

O aplicativo iniciará em sua tela principal, caso apareça uma mensagem de erro verifique os requisitos do sistema.

![](https://i.ibb.co/fGhgrpk/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-003.jpg)

*Tela principal*

### Instruções de Uso:

Na tela principal do aplicativo existem alguns menus de funcionalidades, que serão detalhadas nas próximas seções:

 #### - Menu Superior
- Arquivo
- Editor
- Tela
 #### - Menu Lateral Esquerdo
- Criar Nó
- Criar Barra
- Mostrar Equações
- Calcular Número de Fios
- Cotagem
#### - Menu Lateral Direito
- Saída
- Ponte
- Ângulos

### Menu Superior:

![](https://i.ibb.co/1rHS5Hv/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-004.png)

*Menu superior*

Este menu tem como objetivo auxiliar o usuário nas operações com arquivos, visualização da ponte e opções do material usado.

O primeiro botão “Arquivo” contém as opções de salvar e abrir uma ponte, além de também possuir as funcionalidades de limpar a área de visualização e fechar o programa

![](https://i.ibb.co/mhYNYKr/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-005.png)

*Botão Arquivo*

No botão “Editor” temos as opções de editar as características do material, editar o valor da carga de teste e modificar o nó receptor da carga de teste.

![](https://i.ibb.co/F6r7hb1/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-006.png)

*Botão Editor*

Ao clicar em “Material” um menu de opções será aberto. Neste menu podemos modificar:

- Carga limite de resistência à tração do material (**Newtons**)
- Módulo de Elasticidade (**Mega Pascal**)
- Diâmetro do fio (**Milímetros**)
- Coeficiente de segurança (**Escalar**)

![](https://i.ibb.co/r2LFsG6/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-007.png)

*Menu de material*

Após realizar as modificações desejadas clique em salvar, desse modo as configurações serão aplicadas ao programa.

Além disso, um arquivo é gerado, desse modo, as configurações ficam salvas sempre que o programa for fechado.

O botão de “Carga de Teste” tem a função de abrir a janela de opções de carga teste, nela você poderá modificar a força (**Newtons**) que será usada para calcular o número de fios da ponte.

![](https://i.ibb.co/jkYvbpm/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-008.png)

*Menu de carga de teste*

Observação: A força aplicada será a metade do valor selecionado, já que consideramos que o peso será distribuído em uma estrutura tridimensional com duas estruturas de suporte.

Por último, o botão “Selecionar nó de carga”, ao clicar neste botão o menu de seleção será aberto, nele você poderá escolher em que ponto da sua ponte a carga será aplicada.

![](https://i.ibb.co/kBg6Tkr/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-009.jpg)

*Menu de seleção de carga.*

Após clicar em “Selecionar”, as opções serão atualizadas no programa.

No botão “Tela”, temos as opções de visualização da estrutura. Entre as opções de visualização temos:

- Padrão

![](https://i.ibb.co/tqWk0mL/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-010.jpg)

- Forças

![](https://i.ibb.co/ykpxKPY/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-011.jpg)

- Vetores em Barras

![](https://i.ibb.co/JndMKV2/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-012.jpg)

- Corpo Livre

![](https://i.ibb.co/SB7ysyJ/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-013.jpg)

- Vista Explodida

![](https://i.ibb.co/DPJcSb9/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-014.jpg)

### Menu Lateral Esquerdo:

![](https://i.ibb.co/nDm3b9b/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-015.png)

*Menu lateral esquerdo*

O menu lateral esquerdo possui as opções de criar barras/nos, mostrar as equações, calcular número de fios e criar cotas.

O botão “Criar nó” exibe o menu de criação de nós, neste menu podemos colocar o nome e a posição de um nó da estrutura.

![](https://i.ibb.co/8xf9Yc8/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-016.png)

*Botão criar nó*

![](https://i.ibb.co/q9tyHyk/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-017.png)

*Menu de criação de nó*

- Na entrada de texto “Nome do nó”, colocaremos o nome do nó.
- Nas entradas X e Y colocamos as coordenadas do nó (centímetros).

A opção de “Criar barra” exibe o menu de criação de barras, neste menu podemos selecionar dois nós para serem conectados por uma barra.

![](https://i.ibb.co/chqx7sC/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-018.png)

*Botão criar barra*

![](https://i.ibb.co/zxG9LGw/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-019.png)

*Menu de criação de barras.*

Temos também o botão de “Mostrar Equação", que possui a função de mostrar as equações de equilíbrio da estrutura, na janela “Saída” do menu lateral direito.

![](https://i.ibb.co/y6zy7qR/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-020.png)

*Botão mostrar equações*

![](https://i.ibb.co/3TMwqFB/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-021.jpg)

*Resultado da operação mostrar equações*

O botão “Calcular fios” tem a tarefa de calcular o número de fios de macarrão em cada barra da estrutura, o resultado será mostrado na janela "Saída" do menu lateral direito, e também na tabela “Ponte”.

![](https://i.ibb.co/VpSHXL0/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-022.png)

*Botão calcular fios*

![](https://i.ibb.co/HqBFfTy/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-023.jpg)

*Resultado da operação calcular fios*

![](https://i.ibb.co/0hfhDC1/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-024.png)

*Tabela ponte no menu lateral direito Tamanho (Centímetros)*

Por último, o botão “Criar cota” que possui a função de exibir o menu de cotagem, onde é possível medir a distância entre dois nós.

![](https://i.ibb.co/6mYdLcd/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-025.png)

*Botão criar cota*

![](https://i.ibb.co/2P4Jxb4/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-026.png)

*Menu de cotagem*

Na entrada de texto "Espaçamento" coloque à distância da cota ao centro da linha que conecta os dois nós.

Não é necessário possuir uma barra conectando os nós.

![](https://i.ibb.co/9bQWNGM/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-027.png)

*Resultado da operação cotagem*

### Menu Lateral Direito:

![](https://i.ibb.co/8K5wYGc/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-028.png)

*Menu lateral direito*

Este menu possui as funções de mostrar os detalhes da estrutura, com as opções saída, ponte e ângulos.

A opção "Saída" mostra os resultados de algumas operações executadas no programa, como criar nós e calcular fios.

A tabela “Ponte” mostra todas as barras de ponte, juntamente com seu tamanho, força e número de fios.

![](https://i.ibb.co/0hfhDC1/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-024.png)

*Tabela ponte*

Por último temos a tabela "Ángulos" que tem como função mostrar os ângulos entre as barras da estrutura.

![](https://i.ibb.co/23Cnk4d/Aspose-Words-a5ab2d37-1524-4f3d-b26b-111ee8fa9b5a-029.png)

*Tabela ângulos*
