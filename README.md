Todo List Sample Application<br>
Using Java, Spring io, and angularJS.<br>

Purpose is to help me explore the SpringIO framework and this development stack.<br>

To compile and launch-<br>
mvn package && java -jar target/todolist-0.1.0.jar

Misc non-critical TODO items<br>
TODO - Write better readme file<br>
TODO - Remove unused files in the static lib directory<br>
TODO - Find better icons for actions<br>
TODO - Write tests<br>
TODO - clean up ListData and ItemData classes, removing unused methods<br>
TODO - add in associtive array maping list's to their list items to depricate the inefficent itteration of the entire item 
"table" to find fk relational mumbo-jumbo.<br>
TODO - Drop LISTS and ITEMS hashmaps into external library with methods and accessors. That way a DB backend (or any other  
backend) could easily be swapped in without having to make modifications to the controller. If not for my rustyness with 
java and first time with spring, that's my prefeared organization.<br>
<br>
Other research TODO items-<br>
TODO - Understand the difference between @RestController and @Controller, and how to use them together.<br>
TODO - Spring io seems magical when returning values for routes, figure out how it knows what it's doing.<br>
