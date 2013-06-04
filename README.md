# holmes-validation
----

![Build status](https://api.travis-ci.org/holmes-org/holmes-validation.png)

## What about holmes-validation?
----

> **holmes-validation** is a library that provides a simple and fluent API for writing business rules validations on Java projects.

We all know that writing validations it's really boring, right? Maybe it's not boring for you, but checking if the user filled *correctly* all the required parameters / fields, almost always leads to codes like this:

```java
    if(user.getAge() <= 18) {
    	throw new MyException("your parents won't like you visiting this page");
    }

    if(user.getName() == null || user.getName().isEmpty()) {
    	throw new MyException("so you're the no-name user, uhn?");
    }
```

This code pattern is repetitive among your methods, besides being very procedural.

And that's where **holmes-validation** can help you, providing validation-code that is:

* object-oriented friendly;
* more readable and maintainable;
* sounds closer to the natural language;

Here is a simple code written with **holmes-validation** API:

```java
    HolmesEngine e = HolmesEngine.init();

    e.ensureThat(user.getAge())
                        .isGreaterThan(18)
                        .otherwise("your parents won't like you visiting this page");

    e.ensureThat(user.getName())
                        .isNotEmpty()
                        .otherwise("so you're the no-name user, uhn?");

    e.run();
```

## Getting started
----

Looking for more code samples? Have a look at the [Getting started](https://github.com/holmes-org/holmes-validation) page.

## Maven
----

Using [Apache Maven](http://maven.apache.org/)? Add holmes-validation dependency to the pom.xml and enjoy!

    <dependency>
    	<groupId>org.holmes-validation</groupId>
    	<artifactId>holmes-validation</artifactId>
    	<version>1.0</version>
    </dependency>