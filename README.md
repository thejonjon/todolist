Todo List Sample Application
Using Java, Spring io, and angularJS.

Purpose is to help me explore the SpringIO framework and this development stack.

To compile and launch-
mvn package && java -jar target/todolist-0.1.0.jar

Misc non-critical TODO items
TODO - Write better readme file
TODO - Remove unused files in the static lib directory
TODO - Find better icons for actions
TODO - Write tests
TODO - clean up ListData and ItemData classes, removing unused methods
TODO - add in associtive array maping list's to their list items to depricate the inefficent itteration of the entire item "table" to find fk relational mumbo-jumbo.
TODO - Drop LISTS and ITEMS hashmaps into external library with methods and accessors. That way a DB backend (or any other  backend) could easily be swapped in without having to make modifications to the controller. If not for my rustyness with java and first time with spring, that's my prefeared organization.

Other research TODO items-
TODO - Understand the difference between @RestController and @Controller, and how to use them together.
TODO - Spring io seems magical when returning values for routes, figure out how it knows what it's doing.
