# Durability

Durability is a CB1060 server plugin that allows users to check tool durability with a command, either `/durability` or `/uses`.

## Installation

Download the [latest release Durability.jar](https://github.com/obsidianlad/Durability/releases/latest) 
and put it in the plugins/ folder of your CB1060 server.

## Building

To build from source you will need a CB1060 jar and Java 8.

Then, simply

```
$ javac -cp path/to/cb1060.jar:. Durability.java
```

This will give you a compiled Durability.class.

Then you will have to pack your own Durability.jar with the following structure:

```
Durability.jar
├── lad/
│     └── obsidian/
│           └── durability/
│                 └── Durability.class
└── plugin.yml
```
