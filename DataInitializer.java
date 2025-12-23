package com.abhay.calculator.web.config;

import com.abhay.calculator.web.model.Book;
import com.abhay.calculator.web.model.LibraryUser;
import com.abhay.calculator.web.repository.BookRepository;
import com.abhay.calculator.web.repository.LibraryUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedData(LibraryUserRepository userRepository,
                                      BookRepository bookRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // Seed Users
            if (userRepository.findByUsername("admin").isEmpty()) {
                LibraryUser admin = new LibraryUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setActive(true);
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                LibraryUser user = new LibraryUser();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole("USER");
                user.setActive(true);
                userRepository.save(user);
            }

            // Seed Books (only if database is empty)
            if (bookRepository.count() == 0) {
                seedBooks(bookRepository);
            }
        };
    }

    private void seedBooks(BookRepository bookRepository) {
        // Computer Science (10 books)
        createBook(bookRepository, "CS001", "Introduction to Algorithms", "Thomas H. Cormen", "Computer Science");
        createBook(bookRepository, "CS002", "The Art of Computer Programming", "Donald E. Knuth", "Computer Science");
        createBook(bookRepository, "CS003", "Structure and Interpretation of Computer Programs", "Harold Abelson", "Computer Science");
        createBook(bookRepository, "CS004", "Computer Systems: A Programmer's Perspective", "Randal E. Bryant", "Computer Science");
        createBook(bookRepository, "CS005", "Computer Organization and Design", "David A. Patterson", "Computer Science");
        createBook(bookRepository, "CS006", "Computer Networks", "Andrew S. Tanenbaum", "Computer Science");
        createBook(bookRepository, "CS007", "Operating System Concepts", "Abraham Silberschatz", "Computer Science");
        createBook(bookRepository, "CS008", "Database System Concepts", "Abraham Silberschatz", "Computer Science");
        createBook(bookRepository, "CS009", "Compilers: Principles, Techniques, and Tools", "Alfred V. Aho", "Computer Science");
        createBook(bookRepository, "CS010", "Computer Architecture: A Quantitative Approach", "John L. Hennessy", "Computer Science");

        // Programming (15 books)
        createBook(bookRepository, "PRG001", "Clean Code", "Robert C. Martin", "Programming");
        createBook(bookRepository, "PRG002", "The Pragmatic Programmer", "Andrew Hunt", "Programming");
        createBook(bookRepository, "PRG003", "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", "Programming");
        createBook(bookRepository, "PRG004", "Refactoring: Improving the Design of Existing Code", "Martin Fowler", "Programming");
        createBook(bookRepository, "PRG005", "Code Complete", "Steve McConnell", "Programming");
        createBook(bookRepository, "PRG006", "Effective Java", "Joshua Bloch", "Programming");
        createBook(bookRepository, "PRG007", "Python Crash Course", "Eric Matthes", "Programming");
        createBook(bookRepository, "PRG008", "JavaScript: The Good Parts", "Douglas Crockford", "Programming");
        createBook(bookRepository, "PRG009", "Head First Design Patterns", "Eric Freeman", "Programming");
        createBook(bookRepository, "PRG010", "You Don't Know JS", "Kyle Simpson", "Programming");
        createBook(bookRepository, "PRG011", "C++ Primer", "Stanley B. Lippman", "Programming");
        createBook(bookRepository, "PRG012", "Java: The Complete Reference", "Herbert Schildt", "Programming");
        createBook(bookRepository, "PRG013", "Programming Pearls", "Jon Bentley", "Programming");
        createBook(bookRepository, "PRG014", "The C Programming Language", "Brian W. Kernighan", "Programming");
        createBook(bookRepository, "PRG015", "Clean Architecture", "Robert C. Martin", "Programming");

        // Data Structures (10 books)
        createBook(bookRepository, "DS001", "Data Structures and Algorithms in Java", "Robert Lafore", "Data Structures");
        createBook(bookRepository, "DS002", "Data Structures and Algorithms Made Easy", "Narasimha Karumanchi", "Data Structures");
        createBook(bookRepository, "DS003", "Algorithms", "Robert Sedgewick", "Data Structures");
        createBook(bookRepository, "DS004", "Data Structures and Algorithms in Python", "Michael T. Goodrich", "Data Structures");
        createBook(bookRepository, "DS005", "Cracking the Coding Interview", "Gayle Laakmann McDowell", "Data Structures");
        createBook(bookRepository, "DS006", "Elements of Programming Interviews", "Adnan Aziz", "Data Structures");
        createBook(bookRepository, "DS007", "Problem Solving with Algorithms and Data Structures", "Brad Miller", "Data Structures");
        createBook(bookRepository, "DS008", "Data Structures Using C", "Aaron M. Tenenbaum", "Data Structures");
        createBook(bookRepository, "DS009", "Advanced Data Structures", "Peter Brass", "Data Structures");
        createBook(bookRepository, "DS010", "Data Structures and Problem Solving Using Java", "Mark Allen Weiss", "Data Structures");

        // Artificial Intelligence (12 books)
        createBook(bookRepository, "AI001", "Artificial Intelligence: A Modern Approach", "Stuart Russell", "Artificial Intelligence");
        createBook(bookRepository, "AI002", "Deep Learning", "Ian Goodfellow", "Artificial Intelligence");
        createBook(bookRepository, "AI003", "Pattern Recognition and Machine Learning", "Christopher M. Bishop", "Artificial Intelligence");
        createBook(bookRepository, "AI004", "Machine Learning: A Probabilistic Perspective", "Kevin P. Murphy", "Artificial Intelligence");
        createBook(bookRepository, "AI005", "Neural Networks and Deep Learning", "Michael Nielsen", "Artificial Intelligence");
        createBook(bookRepository, "AI006", "Hands-On Machine Learning", "Aurélien Géron", "Artificial Intelligence");
        createBook(bookRepository, "AI007", "Reinforcement Learning: An Introduction", "Richard S. Sutton", "Artificial Intelligence");
        createBook(bookRepository, "AI008", "The Hundred-Page Machine Learning Book", "Andriy Burkov", "Artificial Intelligence");
        createBook(bookRepository, "AI009", "Superintelligence: Paths, Dangers, Strategies", "Nick Bostrom", "Artificial Intelligence");
        createBook(bookRepository, "AI010", "Life 3.0: Being Human in the Age of Artificial Intelligence", "Max Tegmark", "Artificial Intelligence");
        createBook(bookRepository, "AI011", "Machine Learning Yearning", "Andrew Ng", "Artificial Intelligence");
        createBook(bookRepository, "AI012", "Deep Learning with Python", "François Chollet", "Artificial Intelligence");

        // Data Science (12 books)
        createBook(bookRepository, "DSN001", "The Data Science Handbook", "Field Cady", "Data Science");
        createBook(bookRepository, "DSN002", "Python for Data Analysis", "Wes McKinney", "Data Science");
        createBook(bookRepository, "DSN003", "Data Science from Scratch", "Joel Grus", "Data Science");
        createBook(bookRepository, "DSN004", "Hands-On Data Science and Python Machine Learning", "Frank Kane", "Data Science");
        createBook(bookRepository, "DSN005", "Data Science for Business", "Foster Provost", "Data Science");
        createBook(bookRepository, "DSN006", "Introduction to Statistical Learning", "Gareth James", "Data Science");
        createBook(bookRepository, "DSN007", "The Elements of Statistical Learning", "Trevor Hastie", "Data Science");
        createBook(bookRepository, "DSN008", "R for Data Science", "Hadley Wickham", "Data Science");
        createBook(bookRepository, "DSN009", "Big Data: A Revolution", "Viktor Mayer-Schönberger", "Data Science");
        createBook(bookRepository, "DSN010", "Data Mining: Concepts and Techniques", "Jiawei Han", "Data Science");
        createBook(bookRepository, "DSN011", "Storytelling with Data", "Cole Nussbaumer Knaflic", "Data Science");
        createBook(bookRepository, "DSN012", "Data Science and Big Data Analytics", "EMC Education Services", "Data Science");

        // Databases (10 books)
        createBook(bookRepository, "DB001", "Database Design for Mere Mortals", "Michael J. Hernandez", "Databases");
        createBook(bookRepository, "DB002", "SQL Performance Explained", "Markus Winand", "Databases");
        createBook(bookRepository, "DB003", "Learning SQL", "Alan Beaulieu", "Databases");
        createBook(bookRepository, "DB004", "MongoDB: The Definitive Guide", "Kristina Chodorow", "Databases");
        createBook(bookRepository, "DB005", "Redis in Action", "Josiah L. Carlson", "Databases");
        createBook(bookRepository, "DB006", "High Performance MySQL", "Baron Schwartz", "Databases");
        createBook(bookRepository, "DB007", "PostgreSQL: Up and Running", "Regina Obe", "Databases");
        createBook(bookRepository, "DB008", "Designing Data-Intensive Applications", "Martin Kleppmann", "Databases");
        createBook(bookRepository, "DB009", "NoSQL Distilled", "Pramod J. Sadalage", "Databases");
        createBook(bookRepository, "DB010", "Seven Databases in Seven Weeks", "Eric Redmond", "Databases");

        // Networking (8 books)
        createBook(bookRepository, "NET001", "TCP/IP Illustrated", "W. Richard Stevens", "Networking");
        createBook(bookRepository, "NET002", "Computer Networks", "Larry L. Peterson", "Networking");
        createBook(bookRepository, "NET003", "Network Security Essentials", "William Stallings", "Networking");
        createBook(bookRepository, "NET004", "The TCP/IP Guide", "Charles M. Kozierok", "Networking");
        createBook(bookRepository, "NET005", "Network Warrior", "Gary A. Donahue", "Networking");
        createBook(bookRepository, "NET006", "HTTP: The Definitive Guide", "David Gourley", "Networking");
        createBook(bookRepository, "NET007", "Web Protocols and Practice", "Balachander Krishnamurthy", "Networking");
        createBook(bookRepository, "NET008", "Network Programmability and Automation", "Jason Edelman", "Networking");

        // Operating Systems (8 books)
        createBook(bookRepository, "OS001", "Operating System Concepts", "Peter B. Galvin", "Operating Systems");
        createBook(bookRepository, "OS002", "Modern Operating Systems", "Andrew S. Tanenbaum", "Operating Systems");
        createBook(bookRepository, "OS003", "The Design and Implementation of the FreeBSD Operating System", "Marshall Kirk McKusick", "Operating Systems");
        createBook(bookRepository, "OS004", "Linux Kernel Development", "Robert Love", "Operating Systems");
        createBook(bookRepository, "OS005", "Understanding the Linux Kernel", "Daniel P. Bovet", "Operating Systems");
        createBook(bookRepository, "OS006", "Windows Internals", "Mark E. Russinovich", "Operating Systems");
        createBook(bookRepository, "OS007", "Operating Systems: Three Easy Pieces", "Remzi H. Arpaci-Dusseau", "Operating Systems");
        createBook(bookRepository, "OS008", "The Linux Programming Interface", "Michael Kerrisk", "Operating Systems");

        // Software Engineering (10 books)
        createBook(bookRepository, "SE001", "The Mythical Man-Month", "Frederick P. Brooks", "Software Engineering");
        createBook(bookRepository, "SE002", "Software Engineering: A Practitioner's Approach", "Roger S. Pressman", "Software Engineering");
        createBook(bookRepository, "SE003", "Software Architecture: The Hard Parts", "Neal Ford", "Software Engineering");
        createBook(bookRepository, "SE004", "Building Microservices", "Sam Newman", "Software Engineering");
        createBook(bookRepository, "SE005", "Domain-Driven Design", "Eric Evans", "Software Engineering");
        createBook(bookRepository, "SE006", "Continuous Delivery", "Jez Humble", "Software Engineering");
        createBook(bookRepository, "SE007", "The DevOps Handbook", "Gene Kim", "Software Engineering");
        createBook(bookRepository, "SE008", "Accelerate: The Science of Lean Software", "Nicole Forsgren", "Software Engineering");
        createBook(bookRepository, "SE009", "Release It!", "Michael T. Nygard", "Software Engineering");
        createBook(bookRepository, "SE010", "Site Reliability Engineering", "Betsy Beyer", "Software Engineering");

        // General Academics (15 books)
        createBook(bookRepository, "GEN001", "Discrete Mathematics and Its Applications", "Kenneth H. Rosen", "General Academics");
        createBook(bookRepository, "GEN002", "Linear Algebra Done Right", "Sheldon Axler", "General Academics");
        createBook(bookRepository, "GEN003", "Calculus: Early Transcendentals", "James Stewart", "General Academics");
        createBook(bookRepository, "GEN004", "Introduction to Probability", "Joseph K. Blitzstein", "General Academics");
        createBook(bookRepository, "GEN005", "Concrete Mathematics", "Ronald L. Graham", "General Academics");
        createBook(bookRepository, "GEN006", "Mathematics for Computer Science", "Eric Lehman", "General Academics");
        createBook(bookRepository, "GEN007", "Introduction to Linear Algebra", "Gilbert Strang", "General Academics");
        createBook(bookRepository, "GEN008", "The Algorithm Design Manual", "Steven S. Skiena", "General Academics");
        createBook(bookRepository, "GEN009", "How to Solve It", "George Pólya", "General Academics");
        createBook(bookRepository, "GEN010", "Gödel, Escher, Bach", "Douglas Hofstadter", "General Academics");
        createBook(bookRepository, "GEN011", "The Art of Doing Science and Engineering", "Richard W. Hamming", "General Academics");
        createBook(bookRepository, "GEN012", "Code: The Hidden Language of Computer Hardware", "Charles Petzold", "General Academics");
        createBook(bookRepository, "GEN013", "The Information: A History", "James Gleick", "General Academics");
        createBook(bookRepository, "GEN014", "The Innovator's Dilemma", "Clayton M. Christensen", "General Academics");
        createBook(bookRepository, "GEN015", "The Phoenix Project", "Gene Kim", "General Academics");
    }

    private void createBook(BookRepository bookRepository, String bookId, String title, String author, String category) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailable(true);
        bookRepository.save(book);
    }
}


