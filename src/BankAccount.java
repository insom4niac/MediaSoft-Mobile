import java.time.LocalDateTime;

public class BankAccount {
    private String accountName;         // имя владельца
    private int balance;                // текущий баланс счета
    private LocalDateTime openingDate;  // дата и время открытия
    private boolean isAccountBlocked;   // флаг блокировки

    BankAccount(String accountName) {
        this.accountName = accountName;     // присваиваем указанное имя
        balance = 0;                        // значение по умолчанию
        openingDate = LocalDateTime.now();  // присваиваем дату и время на момент создания объекта
        isAccountBlocked = false;           // значение по умолчанию
    }

    // пополнение счета
    boolean deposit(int amount) {
        if (amount < 0 || isAccountBlocked) return false;
        balance += amount;
        return true;
    }

    // снятие денег
    boolean withdraw(int amount) {
        if (amount < 0 || balance < amount || isAccountBlocked) return false;
        balance -= amount;
        return true;
    }

    // перевод денег на другой счет
    boolean transfer(BankAccount otherAccount, int amount) {
        if (amount < 0 || balance < amount || isAccountBlocked ||
                otherAccount.isAccountBlocked || equals(otherAccount)) return false;
        balance -= amount;
        otherAccount.balance += amount;
        return true;
    }

    // проверка на идентичность
    boolean equals(BankAccount otherAccount) {
        // совпадение по всем 4 параметрам
        if (this.accountName.equals(otherAccount.accountName))
            if (this.balance == otherAccount.balance)
                if (this.openingDate == otherAccount.openingDate)
                    return this.isAccountBlocked == otherAccount.isAccountBlocked;
        // иначе различны
        return false;
    }

    // преобразование в строку
    public String toString(){
        String accountInfo;
        accountInfo = "account name:\t" + accountName;
        accountInfo += "\nbalance:\t\t" + balance;
        accountInfo += "\nopening date:\t" + openingDate;

        if (isAccountBlocked) accountInfo += "\n\t\t\t\taccount is blocked\n";
        else accountInfo += "\n\t\t\t\taccount isn`t blocked\n";

        return accountInfo;
    }

    public static void main(String[] args) {
        BankAccount accountKolya = new BankAccount("Kolya");
        BankAccount accountNikita = new BankAccount("Nikita");

        System.out.println("\n=============================================");
        System.out.println("   ACCOUNT INFORMATION [BEFORE] TRANSACTIONS");
        System.out.println("=============================================");
        System.out.println(accountKolya.toString());
        System.out.println(accountNikita.toString());

        System.out.println("=============================================");

        System.out.println("\ndeposit\t\t | Kolya + 600\t\t\t| " + accountKolya.deposit(600));
        System.out.println("withdraw\t | Kolya - 1000\t\t\t| " + accountKolya.withdraw(1000));
        System.out.println("transfer\t | Kolya ->(400) Nikita\t| " + accountKolya.transfer(accountNikita, 400));
        System.out.println("transfer\t | Kolya ->(100) Kolya\t| " + accountKolya.transfer(accountKolya, 100));

        System.out.println("\n=============================================");
        System.out.println("   ACCOUNT INFORMATION [AFTER] TRANSACTIONS");
        System.out.println("=============================================");
        System.out.println(accountKolya.toString());
        System.out.println(accountNikita.toString());
    }
}
