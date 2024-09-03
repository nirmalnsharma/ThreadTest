# Alternating Number Printer

## Overview

This Java project demonstrates how to print numbers from 1 to 100 using two threads that alternate between printing odd
and even numbers. The project utilizes Java's `ExecutorService` for thread management and synchronization mechanisms to
ensure correct sequence printing.

## Features

- **Alternating Printing**: Two threads print numbers alternately in the sequence from 1 to 100. One thread prints odd
  numbers, while the other prints even numbers.
- **ExecutorService**: Manages the threads efficiently using a thread pool.
- **Synchronization**: Uses a shared lock object and a boolean flag to control the turn between threads.

## Requirements

- JDK 17 or later

## How to Run

1. **Clone the Repository**

   ```bash
   git clone <repository-url>
   cd <repository-directory>

interview task