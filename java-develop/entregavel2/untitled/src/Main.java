class Pessoa {
    String name;
    int age;
    double height;

    public Pessoa(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }
}

class Carro {
    String brand;
    String model;
    int year;

    public Carro(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}


public class Main {
    static void main(String[] args) {


        //Exercicio 1
        Pessoa p1 = new Pessoa("Pedro", 19, 1.69);
        Pessoa p2 = new Pessoa("Ana", 19, 1.68);
        Pessoa p3 = new Pessoa("Paula", 67, 1.90);

        Pessoa maisVelha = p1;
        // obs: sem tratamento de erros ou casos de igualdade
        if (p2.age > p1.age) {
            maisVelha = p2;
        }
        if (p3.age > maisVelha.age) {
            maisVelha = p3;
        }

        System.out.printf("A pessoa mais velha é %s \n\n", maisVelha.name);


        //Exercicio 2
        Carro carro1 = new Carro("Tesla", "Model S", 2026);
        Carro carro2 = new Carro("Chevrolet", "Onix", 2020);

        carro1.year = 2023;

        System.out.printf("Carro 1: %s %s | Ano: %d\n", carro1.brand, carro1.model, carro1.year);
        System.out.printf("Carro 2: %s %s | Ano: %d\n", carro2.brand, carro2.model, carro2.year);
        // Mudar um nao mudou o outro pq sao instancias diferentes, sao objetos diferentes criados a partir
        // de um molde
    }
}