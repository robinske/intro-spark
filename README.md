# Intro Spark

This is a small template for playing around with Spark and Scala. 
There are a few datasets in the `src/resources/` folder for your exploration!

I recommend https://jaceklaskowski.gitbooks.io/mastering-apache-spark/content/ for more details on Spark 2.x

## Set Up

Check your Java installation by typing `java -version` in your terminal/prompt. You should have Java 1.7.x or 1.8.x, both should work with this project.
(If you get an error or don't have Java installed, install the [Java 1.8 JDK from Oracle](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)).

Unzip the project and navigate to the root of the directory, then follow the instructions below for your Operating System (this will take a little while the first time you run it!)

**How to know it worked:**

For all OS installations, you should see 20 rows of words and their counts, starting with `the | 55`.
            

### MacOS / Linux

```
$ ./sbt.sh
.
.
. (you'll see some [info] logs here)
.
.
> console
.
.
. (you'll see some [info] logs here)
.
.
scala> Example.wordCount.show()
```

### Windows

```
C:\Users\Taylor> sbt.bat
.
.
. (you'll see some [info] logs here)
.
.
> console
.
.
. (you'll see some [info] logs here)
.
.
scala> Example.wordCount.show()

```

## Credits

The project pulls data from:
 * the [Star Wars API](https://swapi.co), [Copyright (c) 2015, Paul Hallett](https://github.com/phalt/swapi/blob/master/LICENCE)
 * https://catalog.data.gov/dataset
 * [Project Gutenberg](https://www.gutenberg.org/)

