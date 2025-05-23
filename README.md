Brief description:
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
