# 🚗 Vehicle Platform – Java OOP Project

A Java-based vehicle management and simulation system, designed for a car dealership platform. The application supports creating and managing various types of vehicles (ground, air, and water), and allows customers to take them on virtual test drives.

## 🛠 Features

- Create and manage vehicles of different types using the **Abstract Factory Design Pattern**
- Simulate **test drives** for selected vehicles
- Use of **multithreading** for handling background operations and animations
- Rich GUI interface (Swing-based) for managing vehicle data and visualizing actions
- Support for **cloning**, **decorator pattern**, **factory** and **observer pattern** etc.
- Persistence of vehicles and user actions during the session

## 🔍 Key Technologies

- Java 8+
- OOP Principles (Encapsulation, Inheritance, Polymorphism)
- Design Patterns:
  - Abstract Factory
  - Decorator
  - Observer
  - Singleton
- Java Swing (GUI)
- Multithreading

## 📁 Project Structure

/src  
├── **Factories** – Vehicle creation logic by environment (e.g., Ground, Air, Water)  
├── **Graphics** – GUI components such as panels, dialogs, and menus  
├── **Vehicles** – Base classes and implementations for different vehicle types  
├── **Interfaces** – Interface definitions for extensibility and flexibility  
└── **Main** – Application entry point and startup logic
