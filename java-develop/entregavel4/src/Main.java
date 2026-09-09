class Pagamento {
    protected double valor;
    protected String status;

    public Pagamento(double valor) {
        this.valor = valor;
        this.status = "Pendente";
    }

    public boolean validarValor() {
        if (valor <= 0) {
            status = "Recusado";
            System.out.println("Pagamento recusado: valor inválido.");
            return false;
        }
        return true;
    }

    public void pagar() {
    }

    public void exibirDetalhes() {
        System.out.println("Valor: R$ " + valor);
        System.out.println("Status: " + status);
    }
}

class Pix extends Pagamento {

    public Pix(double valor) {
        super(valor);
    }

    public void pagar() {
        if (validarValor()) {
            status = "Aprovado";
            System.out.println("Pix realizado.");
        }
    }

    public void exibirDetalhes() {
        System.out.println("Pix - R$ " + valor + " - " + status);
    }
}

class Debito extends Pagamento {
    private double saldo;

    public Debito(double valor, double saldo) {
        super(valor);
        this.saldo = saldo;
    }

    public void pagar() {
        if (validarValor()) {
            if (saldo >= valor) {
                status = "Aprovado";
                System.out.println("Débito realizado.");
            } else {
                status = "Recusado";
                System.out.println("Saldo insuficiente.");
            }
        }
    }

    public void exibirDetalhes() {
        System.out.println("Débito - R$ " + valor + " - " + status);
    }
}

class Credito extends Pagamento {

    public Credito(double valor) {
        super(valor);
    }

    public void pagar() {
        if (validarValor()) {
            status = "Aprovado";
            System.out.println("Crédito realizado.");
        }
    }

    public void exibirDetalhes() {
        System.out.println("Crédito - R$ " + valor + " - " + status);
    }
}

public class Main {
    public static void main(String[] args) {

        Pix pix = new Pix(100);
        Debito debito = new Debito(200, 500);
        Credito credito = new Credito(-50);

        pix.pagar();
        pix.exibirDetalhes();

        debito.pagar();
        debito.exibirDetalhes();

        credito.pagar();
        credito.exibirDetalhes();
    }
}