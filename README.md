Brief description::
--

=
=================

This assignment involves designing and implementing a Java program that models celestial bodies within a planetary
system using object-oriented programming principles. The program features a class hierarchy rooted in the abstract base
class Planet, which encapsulates common attributes shared by all planet types. Specialized subclasses such as GasPlanet
and IcePlanet extend the Planet class, representing different categories of planets.

The program is organized into several packages:
The controller package contains the PlanetSystemAPI class, which manages operations and interactions within the
planetary system.

The main package includes the Driver class to run the application.

The models package defines the core celestial classes (Planet, GasPlanet, IcePlanet).

The utils package provides supporting utilities such as the ISerializer interface for data serialization.

This structure reflects real-world astronomical classifications and provides a clear, organized way to represent the
complexity of planetary systems

---------


=====> Main difficulties I came across in your development of solution and how I solved them:
Navigation Flow: Initially struggled with enabling users to return to the main menu without restarting the program.
➤ Solved by creating a reusable method (goBackToMainOrCrudMenu()) that handles user navigation cleanly.

Switch Statement in Infinite Loop: Had trouble managing case 0 to exit the loop without affecting other options.
➤ Resolved by isolating the exit logic and placing a break statement only within case 0.

XML File Handling with XStream: Faced challenges in saving and loading objects correctly using XML.
➤ Learned how to use XStream with aliasing and corrected object serialization to handle inheritance
(e.g., IcePlanet, GasPlanet).

Code Duplication & Hardcoding: Encountered hardcoded logic in classes like Planet.
➤ Refactored by integrating utility methods (e.g., for validation), making code more modular and reusable.

---------

=====> Main learnings from my engagement with assignment:
➤ Java OOP Concepts: Gained a solid understanding of inheritance ,abstraction, encapsulation, and polymorphism.
➤ Menu Design & Input Handling: Improved at designing structured menu systems and capturing user input safely.
➤ File I/O & XML Serialization: Developed hands-on skills with Java’s I/O streams and XStream for XML persistence.
➤ Debugging & Refactoring: Strengthened problem-solving through debugging and code refactoring for better clarity
and modularity.
➤ Code Maintainability: Learned the importance of breaking code into reusable methods and utility classes to keep the
codebase clean.


-----------------------------------

References / sources :
====================
To assist me during the development of this assignment , I have used the following resources :

Resources used to help understanding Planetary Systems as part of this assignment:
- Celestial Bodies Overview: https://solarsystem.nasa.gov/planets/overview/
- Understanding Planetary Systems: https://exoplanets.nasa.gov/what-is-an-exoplanet/planetary-systems-overview/
- Types of Stars: https://science.nasa.gov/astrophysics/focus-areas/how-stars-form-and-evolve/
- Classification of Planets: https://www.britannica.com/science/planet-astronomy


Resources that supported my understanding of Class Diagram | Unified Modeling Language (UML):
https://www.geeksforgeeks.org/unified-modeling-language-uml-class-diagrams/


Resources used to enhance comprehension in OOP /Abstract Methods and Classes:
https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html

Resources used to enhance comprehension in OOP /Java Polymorphism:
https://www.programiz.com/java-programming/polymorphism


Resources used to help me create javaDoc :
https://www.oracle.com/ie/technical-resources/articles/java/javadoc-tool.html

Resources Consulted for Deeper Understanding of XML file:
Handling XML Files in Java | DOM Parser Library | Parsing XML Files | Part 10
https://www.youtube.com/watch?v=wpXBwk3QSso
and ,
Xstream driver performance
https://stackoverflow.com/questions/11902514/xstream-driver-performance/11902561#11902561

Resources used to help me write a StringBuilder Class
https://docs.oracle.com/javase/tutorial/java/data/buffers.html
https://www.codecademy.com/resources/docs/java/stringbuilder

Resources that supported my understanding of Comparator Interface in Java - Tutorial for Beginners | Learn Comparator
in 5 minutes : https://www.youtube.com/watch?v=jUuzoSdjXSw

Resource used to help me to have a good overview of how to code using Sort Array -Java Program #17 - Sort an Array of
Integers in ascending order: https://www.youtube.com/watch?v=86B5U15c88w

Resources used to help me understand the sintaxe of Java ArrayList:
https://www.w3schools.com/java/java_arraylist.asp

=====
