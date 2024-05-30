<div align="center">

# DELI-cious App
  
</div>

<p>
  Welcome to the Sekwanaas-Deli repository! This project is a comprehensive Java-based application designed to manage a delicatessen's orders efficiently
  
## Features
### Order Processing
* Create Orders: Generate new orders by selecting whether users want a Sandwich, Drinks, or Chips..
  * When ordering sandwiches, users can customize their sandwich based on size, bread type, which toppings they want, and the types of cheese or sauces on their sandwich.
  * When ordering drinks, users can choose the size of the drink that they would like and also choose the flavor/brand of the drink.
  * When ordering chips, users can choose which chips they would like to add.
* Update Orders: Modify existing orders to reflect changes in customer requests.
## Object-Oriented Programming (OOP) Principles
### Encapsulation
* Classes and Objects: The application is structured into various classes representing different entities like Product, Order, and Customer. Each class encapsulates its attributes and methods, promoting modularity and reusability.
* Access Modifiers: Use of private and public access modifiers to protect the integrity of the data and expose only necessary methods.
### Inheritance
* Base and Derived Classes: The application utilizes inheritance where common functionalities are defined in base classes and specific behaviors are implemented in derived classes.
### Polymorphism
* Method Overriding: The application allows method overriding to enable specific implementations in derived classes, enhancing flexibility and scalability.
### Abstraction
* Abstract Classes and Interfaces: Abstract classes and interfaces are used to define the blueprint of the application, allowing different parts of the application to interact through well-defined interfaces.
</p>

## A Look Into the Application
### File Directory / Home Screen
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/6d22f054-8f61-4e71-a582-f2715d868fc4)

### Home Screen Class
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/7c6b82ab-bd9c-4acf-b323-034f8040a158)

### Ordering Screen
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/8751389d-f7f3-4261-bdf8-bfb7355d04f0)

### Sandwich Creation Screen
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/ef8eea82-2ca6-4dce-a378-ba56e232e40d)

### Scanner Manager Class
The goal of this class is to allow the scanner to be used throughout the entire application without having to have a static scanner or have a scanner that is having to be passed through different methods. This class is able to get String, ints, or even doubles and handle any errors or wrong inputs without throwing exceptions.

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/ef90b5dc-f1ca-4606-a971-6c6e436daea3)

<details>

<summary>
  
  ## Running Through the Application
  
</summary>

### Home Screen
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/605d41e2-acc3-4ee0-a353-b89d53129adc)

### Adding custom sandwich
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/d3d9fc76-2f91-41fa-b2b4-914d75a1106e)

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/e2585435-139e-4302-9c58-cd69e61eb57f)

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/508ae046-e6df-43c3-84a2-07d302a05f7a)

### Adding drinks
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/5daacca5-0fa8-4870-a75b-1ba66445b59b)

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/45928752-21ca-4562-9f0e-83495f7d4aa3)

### Adding chips
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/ef4d64c2-f510-405a-9488-1a164ccbf6e3)

### Order being printed and saved to receipts folder
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/0cd9fe48-8571-450f-94a0-ba45fc3942c9)

</details>

## Interesting Piece of Code
> I chose this piece of code because the Inputs class allows for me to be able to use the scanner anywhere in my application without having to pass a scanner through any methods. It's also really interesting because it handles all the incorrect inputs as well.
``` java
import java.util.Scanner;

public class Inputs {
    private static Scanner scanner;

    private Inputs() {
    }

    //Methods
    // Method to open the Scanner
    public static void openScanner() {
        scanner = new Scanner(System.in);
    }

    // Method to close the Scanner
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }

    // Helper method to make sure scanner is open when calling a method
    private static void ensureScannerIsOpen() {
        if (scanner == null) {
            openScanner();
        }
    }

    public static String getString() {
        ensureScannerIsOpen();
        return scanner.nextLine();
    }

    public static int getInt() {
        ensureScannerIsOpen();
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number... Please enter an integer:");
            scanner.next(); // Discard invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); //eat CRLF
        return input;
    }

    public static void awaitInput() {
        ensureScannerIsOpen();
        System.out.print("\nPress ENTER to continue...");
        scanner.nextLine();
    }
}
```

<details>

<summary>

## Error Handling
  
</summary>

### Not having added the required customizations
If you haven't added the required sandwich customizations, you will not be allowed to finalize your sandwich.

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/f216553d-cd3d-44ec-b448-4a95d6230bec)


### Editing an item that doesn't exist
You are not able to edit items if they are not currently in your order.

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/ad177e80-36ad-4a08-ae70-85d4c9664a67)

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/d6608520-ccd4-4b62-94d8-d911305ea158)

### Other Miscellaneous Errors
![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/c1dbe9f0-3480-47e1-9d28-85441c5fb652)

![image](https://github.com/sekwanaa/Sekwanaas-Deli/assets/112197395/4cd58adf-f156-4cee-837b-8d2096d0bf0d)
  
</details>


