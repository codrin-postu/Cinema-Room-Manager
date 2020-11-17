package cinema;

import java.util.Scanner;

public class Cinema {

    public static void showSeats(char[][] seatArray) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seatArray[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < seatArray.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seatArray[i].length; j++) {
                if(seatArray[i][j] != 'S' && seatArray[i][j] != 'B')
                    seatArray[i][j] = 'S';
                System.out.print(" " + seatArray[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean buyTicket(char[][] seatArray) {
        Scanner scanner = new Scanner(System.in);

        int rows = seatArray.length;
        int seats = seatArray[0].length;

        System.out.println("Enter a row number:");
        int chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenSeat = scanner.nextInt();

        if(chosenRow - 1 < 0 || chosenRow - 1 >= seatArray.length
                || chosenSeat - 1 < 0 || chosenSeat - 1 >= seatArray[0].length) {
            System.out.println("Wrong input!");
            return false;
        }

        if (seatArray[chosenRow-1][chosenSeat-1] == 'B') {
            System.out.println("That ticket has already been purchased");
            return false;
        }

        seatArray[chosenRow - 1][chosenSeat - 1] = 'B';
        System.out.print("Ticket Price: ");
        if(rows * seats <= 60) {
            System.out.println("$" + 10);
        }
        else if (chosenRow <= rows / 2) {
            System.out.println("$" + 10);
        }
        else if (chosenRow > rows / 2) {
            System.out.println("$" + 8);
        }

        return true;
    }

    public static void showStatistics(char[][] seatArray) {
        int purchasedTickets = 0;
        int totalSeats = seatArray.length * seatArray[0].length;
        float percentageBought = 0f;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < seatArray.length; i++) {
            for (int j = 0; j < seatArray[0].length; j++) {
                if (seatArray[i][j] == 'B') {
                    purchasedTickets++;
                    if (totalSeats <= 60 ||
                            (totalSeats > 60 && i < seatArray.length / 2)) {
                        currentIncome += 10;
                    }
                    else if (i >= seatArray.length / 2) {
                        currentIncome += 8;
                    }
                }
            }
        }

        if (totalSeats <= 60) {
            totalIncome = seatArray.length * seatArray[0].length * 10;
        }
        else {
            totalIncome = ((seatArray.length / 2) * seatArray[0].length * 10) +
                    ((seatArray.length - (seatArray.length / 2)) * seatArray[0].length * 8);
        }

        percentageBought = (purchasedTickets *  100.00f) / totalSeats;

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%% \n", percentageBought);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void menu(char[][] seatArray) {
        Scanner scanner = new Scanner(System.in);
        boolean open = true;
        boolean purchase = false;


        while (open) {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 0:
                    open = false;
                    break;
                case 1:
                    showSeats(seatArray);
                    break;
                case 2:
                    while(!purchase) {
                        purchase = buyTicket(seatArray);
                    }
                    purchase = false;
                    break;
                case 3:
                    showStatistics(seatArray);
                    break;
                default:
                    System.out.println("Not a command!");
            }

        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] seatArray = new char[rows][seats];

        menu(seatArray);

    }
}