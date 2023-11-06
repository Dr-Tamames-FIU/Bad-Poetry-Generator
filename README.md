# Rhyming Poetry Generator

This project involves the creation of a rhyming poetry generator using a custom hashtable and a rhyming dictionary. The primary goal of the project is to generate rhyming poems based on a dataset of words and their associated rhyme groups. This document provides an overview of the project's objectives and functionality.

## Project Objectives

### Objective 1: Hashtable Implementation
I have developed a custom hashtable class named `MyHashtable`, which adheres to the `DictionaryInterface`. This hashtable utilizes string keys and object values, allowing for the storage of various objects within the hashtable. The `MyHashtable` class comprises the following methods:
- `isEmpty()`: Checks if the hashtable is empty.
- `size()`: Returns the number of key-value pairs in the hashtable.
- `put(String key, Object value)`: Adds a new key-value pair to the hashtable or updates an existing one.
- `get(String key)`: Retrieves the value associated with a given key.
- `remove(String key)`: Removes a key-value pair from the hashtable.
- `clear()`: Empties the hashtable.
- `getKeys()`: Returns an array of all keys stored in the hashtable.
- Constructor: Initializes the hashtable with a specific table size.

### Hash Codes
The hashtable employs hash codes to compute an index for keys. The `hashCode()` method is applied to string keys to generate hash codes. To ensure the index remains within the bounds of the table size, the modulo operator is employed to map the hash code to a valid index.

## Poetry Generation

The main functionality of this project revolves around the generation of rhyming poems. This is accomplished by storing words in a custom data structure and selecting rhyming pairs of words to compose poems. The following steps outline the poetry generation process:

### Step 1: Storing Rhyme Data
The project reads data from the CMU Pronunciation Dictionary, which contains words and their associated rhyme groups. Each line from the dictionary is processed to extract the word and its corresponding rhyme group. The `storeRhyme()` method is used to store this data in the hashtable. It checks if a rhyme group is already present in the hashtable and adds the word to the corresponding list if it exists. If the rhyme group does not exist, a new list is created.

### Step 2 (Optional): Removing Unrhymable Words
Some words appear in a rhyme group by themselves, signifying that nothing rhymes with them. To enhance the quality of generated poems, an optional `removeUnrhymables()` method is provided. It identifies and removes such unrhymable words from the dictionary. This step is not mandatory but can improve the poem generation process.

## Example Input and Output

To run the rhyming poetry generator, you can execute the `RhymingDict.java` file with optional command-line arguments, specifying the seed for the random number generator and the number of poems to generate. The generator selects random rhyming pairs and composes poems based on the provided data.

