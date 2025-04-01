
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Menu {
    private Produto[] produtos = new Produto[5];
    private Fornecedor[] fornecedores = new Fornecedor[5];
    private int indexProd=0;
    private int indexForn=0;

    public void MenuPrincipal(){
        int opcao;
        String selecao = "1- Cadadastrar produto \n 2- Buscar produto por nome \n 3- Buscar fornecedor por CPF \n 4- Sair";
        do {
            opcao = parseInt(showInputDialog(null,selecao));
            if(opcao>4||opcao<1){
                showMessageDialog(null, "Opção invalida");
            }else {
                switch (opcao){
                    case 1:
                        CadastrarProduto();
                        break;
                    case 2:
                        PesquisarProduto();
                        break;
                    case 3:
                        PesquisarFornecedor();
                        break;
                }
            }
        }while (opcao !=4);
    }

    private void CadastrarProduto(){
        String nomeFornecedor;
        long cnpj;
        String nomeProduto;
        double valorProduto;
        int quantidadeEstoque;
        Fornecedor fornecedor= PesquisarFornecedor();
        if (indexProd< produtos.length){
            if(fornecedor == null){
                nomeFornecedor = showInputDialog(null, "Digite o nome do fornecedor para cadastro: ");
                cnpj = parseLong(showInputDialog(null,"Digite o cnpj do fornecedor para cadastro: "));
                fornecedores[indexForn] = new Fornecedor(nomeFornecedor, cnpj);
                indexForn++;
            } else {
                showMessageDialog(null, fornecedor.getCnpj() +fornecedor.getNome());
            }
            nomeProduto = showInputDialog(null, "Digite o nome do produto");
            valorProduto = parseDouble(showInputDialog(null, "Digite o valor do produto"));
            quantidadeEstoque = parseInt(showInputDialog(null,"Digite a quantidade do produto em estoque"));
            produtos[indexProd] = new Produto(nomeProduto, valorProduto, quantidadeEstoque, fornecedores[indexForn-1]);
            indexProd++;
        }else {
            showMessageDialog(null, "Limite de produtos atingidos.");
        }
    }
    private void PesquisarProduto(){
        String nomeProduto;
        nomeProduto = showInputDialog(null, "Digite o nome do produto a ser encontrado: ");
        for (int i = 0; i < indexProd; i++) {
            if(nomeProduto.equalsIgnoreCase(produtos[i].getNomeProduto())){
               showMessageDialog(null, produtos[i].getNomeProduto() +"\n" +produtos[i].getValorProduto() +"\n" +produtos[i].getFornecedor().getCnpj() +"\n" +produtos[i].getQuantidadeEstoque());
               return;
            }

        }
        showMessageDialog(null, "Produto não encontrado");

    }

    private Fornecedor PesquisarFornecedor(){
        long cnpj;
        cnpj = parseLong(showInputDialog(null,"Digite o cnpj do fornecedor: "));
        for (int i = 0; i < indexForn; i++) {
            if(cnpj == fornecedores[i].getCnpj()){
                return fornecedores[i];
            }
        }
        showMessageDialog(null, "Fornecedor não encontrado");
        return null;
    }


}
