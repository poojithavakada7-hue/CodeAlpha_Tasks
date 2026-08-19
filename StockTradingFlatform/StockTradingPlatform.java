import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    // STOCK CLASS 
    static class Stock {
        String symbol;
        String companyName;
        double price;

        Stock(String symbol, String companyName, double price) {
            this.symbol = symbol;
            this.companyName = companyName;
            this.price = price;
        }
    }

    // HOLDING CLASS 
    static class Holding {
        String symbol;
        int quantity;
        double averagePrice;

        Holding(String symbol, int quantity, double averagePrice) {
            this.symbol = symbol;
            this.quantity = quantity;
            this.averagePrice = averagePrice;
        }
    }

    // TRANSACTION CLASS
    static class Transaction {
        String type;
        String symbol;
        int quantity;
        double price;
        double total;

        Transaction(String type, String symbol, int quantity, double price) {
            this.type = type;
            this.symbol = symbol;
            this.quantity = quantity;
            this.price = price;
            this.total = quantity * price;
        }

        void display() {
            System.out.println(
                    type + " | " +
                    symbol + " | Quantity: " +
                    quantity + " | Price: ₹" +
                    price + " | Total: ₹" +
                    total
            );
        }
    }

    // USER CLASS
    static class User {
        String name;
        double balance;

        ArrayList<Holding> portfolio = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        User(String name, double balance) {
            this.name = name;
            this.balance = balance;
        }
    }

    static ArrayList<Stock> stocks = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static User user;

    // MAIN
    public static void main(String[] args) {

        createMarket();

        System.out.println("======================================");
        System.out.println("       STOCK TRADING PLATFORM");
        System.out.println("======================================");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        user = new User(name, 50000);

        System.out.println("\nWelcome, " + user.name + "!");
        System.out.println("Virtual Balance: ₹" + user.balance);

        while (true) {

            System.out.println("\n======================================");
            System.out.println("              MAIN MENU");
            System.out.println("======================================");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. View Balance");
            System.out.println("7. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewMarket();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                    viewPortfolio();
                    break;

                case 5:
                    viewTransactions();
                    break;

                case 6:
                    viewBalance();
                    break;

                case 7:
                    System.out.println("\nThank you for using Stock Trading Platform!");
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid choice!");
            }
        }
    }

    // CREATE MARKET
    static void createMarket() {

        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1800));
        stocks.add(new Stock("RELI", "Reliance Industries", 2900));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1650));
        stocks.add(new Stock("AAPL", "Apple", 22000));
    }

    // VIEW MARKET
    static void viewMarket() {

        System.out.println("\n------------- MARKET DATA -------------");

        System.out.printf("%-10s %-30s %-15s%n",
                "Symbol", "Company", "Price");

        System.out.println("----------------------------------------------------------");

        for (Stock stock : stocks) {

            System.out.printf("%-10s %-30s ₹%-15.2f%n",
                    stock.symbol,
                    stock.companyName,
                    stock.price);
        }
    }

    // FIND STOCK 
    static Stock findStock(String symbol) {

        for (Stock stock : stocks) {

            if (stock.symbol.equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }

    // BUY STOCK
    static void buyStock() {

        viewMarket();

        System.out.print("\nEnter stock symbol to buy: ");
        String symbol = sc.next();

        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        double totalCost = stock.price * quantity;

        if (totalCost > user.balance) {

            System.out.println("\nInsufficient balance!");
            System.out.println("Required: ₹" + totalCost);
            System.out.println("Available: ₹" + user.balance);

            return;
        }

        user.balance -= totalCost;

        Holding holding = findHolding(symbol);

        if (holding == null) {

            holding = new Holding(
                    stock.symbol,
                    quantity,
                    stock.price
            );

            user.portfolio.add(holding);

        } else {

            double oldValue =
                    holding.quantity * holding.averagePrice;

            double newValue =
                    quantity * stock.price;

            holding.quantity += quantity;

            holding.averagePrice =
                    (oldValue + newValue) / holding.quantity;
        }

        Transaction transaction =
                new Transaction(
                        "BUY",
                        stock.symbol,
                        quantity,
                        stock.price
                );

        user.transactions.add(transaction);

        System.out.println("\n======================================");
        System.out.println("          PURCHASE SUCCESSFUL");
        System.out.println("======================================");
        System.out.println("Stock     : " + stock.symbol);
        System.out.println("Quantity  : " + quantity);
        System.out.println("Price     : ₹" + stock.price);
        System.out.println("Total     : ₹" + totalCost);
        System.out.println("Balance   : ₹" + user.balance);
    }

    // SELL STOCK
    static void sellStock() {

        viewPortfolio();

        System.out.print("\nEnter stock symbol to sell: ");
        String symbol = sc.next();

        Holding holding = findHolding(symbol);

        if (holding == null) {
            System.out.println("You don't own this stock.");
            return;
        }

        Stock stock = findStock(symbol);

        System.out.print("Enter quantity to sell: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        if (quantity > holding.quantity) {

            System.out.println("You don't have enough shares.");
            System.out.println("Available shares: " + holding.quantity);

            return;
        }

        double totalValue =
                stock.price * quantity;

        user.balance += totalValue;

        holding.quantity -= quantity;

        if (holding.quantity == 0) {
            user.portfolio.remove(holding);
        }

        Transaction transaction =
                new Transaction(
                        "SELL",
                        stock.symbol,
                        quantity,
                        stock.price
                );

        user.transactions.add(transaction);

        System.out.println("\n======================================");
        System.out.println("             SALE SUCCESSFUL");
        System.out.println("======================================");
        System.out.println("Stock     : " + stock.symbol);
        System.out.println("Quantity  : " + quantity);
        System.out.println("Price     : ₹" + stock.price);
        System.out.println("Total     : ₹" + totalValue);
        System.out.println("Balance   : ₹" + user.balance);
    }

    // FIND HOLDING
    static Holding findHolding(String symbol) {

        for (Holding holding : user.portfolio) {

            if (holding.symbol.equalsIgnoreCase(symbol)) {
                return holding;
            }
        }

        return null;
    }

    // VIEW PORTFOLIO
    static void viewPortfolio() {

        System.out.println("\n------------- YOUR PORTFOLIO -------------");

        if (user.portfolio.isEmpty()) {

            System.out.println("Your portfolio is empty.");
            return;
        }

        double totalInvestment = 0;
        double currentValue = 0;

        System.out.printf(
                "%-10s %-10s %-15s %-15s%n",
                "Stock",
                "Quantity",
                "Avg Price",
                "Current Value"
        );

        System.out.println("------------------------------------------------------");

        for (Holding holding : user.portfolio) {

            Stock stock = findStock(holding.symbol);

            double investment =
                    holding.quantity * holding.averagePrice;

            double value =
                    holding.quantity * stock.price;

            totalInvestment += investment;
            currentValue += value;

            System.out.printf(
                    "%-10s %-10d ₹%-14.2f ₹%-14.2f%n",
                    holding.symbol,
                    holding.quantity,
                    holding.averagePrice,
                    value
            );
        }

        double profitLoss =
                currentValue - totalInvestment;

        System.out.println("\nTotal Investment : ₹" + totalInvestment);
        System.out.println("Current Value    : ₹" + currentValue);

        if (profitLoss >= 0) {
            System.out.println("Profit : ₹" + profitLoss);
        } else {
            System.out.println("Loss : ₹" + Math.abs(profitLoss));
        }
    }

    // VIEW TRANSACTIONS
    static void viewTransactions() {

        System.out.println("\n------------- TRANSACTION HISTORY -------------");

        if (user.transactions.isEmpty()) {

            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : user.transactions) {
            transaction.display();
        }
    }

    // VIEW BALANCE
    static void viewBalance() {

        System.out.println("\n------------- ACCOUNT BALANCE -------------");

        System.out.println("User Name : " + user.name);
        System.out.println("Balance   : ₹" + user.balance);
    }
}