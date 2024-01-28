# Clojure File Compression and Decompression Application

This project introduces you to functional programming concepts using the Clojure programming language. The goal is to create a small application that allows users to compress and decompress simple text files. The project also includes a menu system for manipulating and displaying input.

## Features

1. **Display List of Files**
   - Selecting this option will display a list of one or more file names in the current folder.
   - The list includes your Clojure source files, a file named `frequency.txt`, and a group of `tn.txt` files (sample/test files).
   - The directory listing is easily obtained using Clojure's `file-seq` function.

2. **Display File Contents**
   - This option allows users to display the content of a selected input file.
   - Users are prompted for the file name, and the application reads and prints the text content to the screen.
   - File content retrieval is simplified using Clojure's `slurp` function.

3. **Compress a File**
   - Choose Option 3 to compress a text file.
   - Enter the file name when prompted (e.g., `t1.txt`).
   - The program will compress the text file by converting words into numbers based on their frequency in the English language.
   - A new compressed file (e.g., `t1.txt.ct`) will be created.

4. **Decompress a File**
   - Select Option 4 to decompress a previously compressed file.
   - Enter the name of the compressed file (e.g., `t1.txt.ct`).
   - The program will decompress the file and display the uncompressed content.

## Error Handling
- Basic error checking is implemented to ensure valid file names are provided.
- Users will receive an "Invalid file" message if the entered file name is not valid.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/clojure-file-compressor.git
   cd clojure-file-compressor
   ```

2. Run the Clojure program:

   ```bash
   clojure program.clj
   ```

3. Follow the on-screen menu to explore file listing, content display, compression, and decompression functionalities.

## Usage

1. **Display List of Files**
   - Choose Option 1 to view a list of files in the current folder.

2. **Display File Contents**
   - Choose Option 2 to display the content of a specific input file.
   - Enter the file name when prompted.

3. **Compress a File**
   - Choose Option 3 to compress a text file.
   - Enter the file name when prompted (e.g., `t1.txt`).

4. **Decompress a File**
   - Choose Option 4 to decompress a previously compressed file.
   - Enter the name of the compressed file (e.g., `t1.txt.ct`).
