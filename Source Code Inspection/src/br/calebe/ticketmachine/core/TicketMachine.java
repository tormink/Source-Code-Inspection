package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    private final int[] PAPELMOEDA = {2, 5, 10, 20, 50, 100};
    private final int PRECODOBILHETE;

    private int saldo;
    private Troco troco;

    public TicketMachine(int valor) {
        this.PRECODOBILHETE = valor;
        this.saldo = 0;
    }

    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou = false;
        for (int i = 0; i < PAPELMOEDA.length && !achou; i++) {
            if (PAPELMOEDA[i] == quantia) {
                achou = true;
            }
        }
        if (!achou) {
            throw new PapelMoedaInvalidaException();
        }
        this.saldo += quantia;
    }

    public int getSaldo() {
        return saldo;
    }

    public Iterator<Double> getTroco() {
        Troco troco = new Troco(this.PRECODOBILHETE);
        return (Iterator<Double>) troco;
    }

    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < PRECODOBILHETE) {
            throw new SaldoInsuficienteException();
        }
        saldo-=PRECODOBILHETE;
        String result = "*****************\n";
        result += "*** R$ " + saldo + ",00 ****\n";
        result += "*****************\n";
        return result;
    }
}
