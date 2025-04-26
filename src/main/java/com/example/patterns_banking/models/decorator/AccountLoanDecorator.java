package com.example.patterns_banking.models.decorator;

import com.example.patterns_banking.models.Account;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AccountLoanDecorator extends AccountDecorator {
    private static final double MAX_LOAN = 20_000;

    public AccountLoanDecorator(Account account) {
        super(account);
    }

    @Override
    public void withdraw(double amount) {
        double balance = getBalance();
        if (amount <= balance) {
            super.withdraw(amount);
        }
        else if (amount <= balance + MAX_LOAN) {
            double overdraft = amount - balance;
            super.withdraw(balance);
            setBalance(0.0);
            System.out.println(
                    "Se utilizó sobregiro por: " + overdraft
            );
        }
        else {
            throw new IllegalArgumentException(
                    "Retiro de " + amount +
                            " supera saldo + préstamo máximo (" + (balance + MAX_LOAN) + ")"
            );
        }
    }

}
