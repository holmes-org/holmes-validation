# holmes-validation

![Build status](https://api.travis-ci.org/holmes-org/holmes-validation.png)

## What about holmes-validation?

> **holmes-validation** is a library that provides a simple and fluent API for writing business rules validations on Java projects.

We all know that writing validations it's really boring, right? Maybe it's not boring for you, but checking if the user filled *correctly* all the required parameters / fields, almost always leads to codes like this:

```java
List<String> errors = ...;

if(user.getAge() <= 18) {
    errors.add("your parents won't like you visiting this page");
}

if(user.getName() == null || user.getName().isEmpty()) {
    errors.add("so you're the no-name user, uhn?");
}

if(DateUtils.calculateDiffInYears(user.getGraduationDate(), new Date()) < 5) {
	errors.add("you must be graduated for at least 5 years");
}

if(!errors.isEmpty()) {
	throw new MyException(errors);
}
```

This code pattern is repetitive among your methods, besides being very procedural.

And that's where **holmes-validation** can help you, providing validation-code that:

* is object-oriented friendly;
* is more readable and maintainable;
* sounds closer to the natural language;
* is dinamycally adaptable to the target data-type; 

Here is a simple code (with new lines added for better readability) written with **holmes-validation** API:

```java
HolmesEngine e = HolmesEngine.init();

e.ensureThat(user.getAge())
                 .isGreaterThanOrEqualTo(18)
                 .otherwise("your parents won't like you visiting this page");

e.ensureThat(user.getName())
                 .isNotEmpty()
                 .otherwise("so you're the no-name user, uhn?");

e.ensureThat(user.getGraduationDate())
                 .applying(Diff.toNow().inYears())
                 .isGreaterThanOrEqualTo(5)
                 .otherwise("you must be graduated for at least 5 years");

e.run(); // will throw a ValidationException or return a ValidationResult object, depending on the OperationMode.
```

## Getting Started

Looking for more code samples? Have a look at the [Getting Started Guide](https://github.com/holmes-org/holmes-validation/wiki/Getting-Started-Guide).

## Maven

Using [Apache Maven](http://maven.apache.org/)? Add holmes-validation dependency to the pom.xml and enjoy!

    <dependency>
    	<groupId>org.holmes-validation</groupId>
    	<artifactId>holmes-validation</artifactId>
    	<version>1.1</version>
    </dependency>
