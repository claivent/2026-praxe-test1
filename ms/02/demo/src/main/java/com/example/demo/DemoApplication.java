package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserService userService) {
        return args -> {
            userService.createUser(newUser("Alice", "alice@example.com", 25));
            userService.createUser(newUser("Bob", "bob@example.com", 30));
        };
    }

    private User newUser(String name, String email, int age) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setAge(age);
        return u;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/test")
    public String test() {
        return "Spring Boot funguje!";
    }

    @GetMapping("/")
    public String home() {
        return " funguje!";
    }

    @GetMapping("/version")
    public String version() {
        return "1.0.2";
    }

    record Page(int page, String text) {}
    record BookResponse(String title, String author, String source, List<Page> pages) {}

    @GetMapping("/book")
    public BookResponse book() {
        return new BookResponse(
            "Pride and Prejudice",
            "Jane Austen",
            "Project Gutenberg (public domain)",
            List.of(
                new Page(1,
                    "It is a truth universally acknowledged, that a single man in possession of a good fortune, " +
                    "must be in want of a wife. However little known the feelings or views of such a man may be " +
                    "on his first entering a neighbourhood, this truth is so well fixed in the minds of the " +
                    "surrounding families, that he is considered as the rightful property of some one or other " +
                    "of their daughters. \"My dear Mr. Bennet,\" said his lady to him one day, \"have you heard " +
                    "that Netherfield Park is let at last?\" Mr. Bennet replied that he had not. \"But it is,\" " +
                    "returned she; \"for Mrs. Long has just been here, and she told me all about it.\" " +
                    "Mr. Bennet made no answer. \"Do you not want to know who has taken it?\" cried his wife " +
                    "impatiently. \"You want to tell me, and I have no objection to hearing it.\" " +
                    "This was invitation enough."),
                new Page(2,
                    "\"Why, my dear, you must know, Mrs. Long says that Netherfield is taken by a young man of " +
                    "large fortune from the north of England; that he came down on Monday in a chaise and four " +
                    "to see the place, and was so much delighted with it, that he agreed with Mr. Morris " +
                    "immediately; that he is to take possession before Michaelmas, and some of his servants are " +
                    "to be in the house by the end of next week.\" \"What is his name?\" \"Bingley.\" " +
                    "\"Is he married or single?\" \"Oh! Single, my dear, to be sure! A single man of large " +
                    "fortune; four or five thousand a year. What a fine thing for our girls!\" " +
                    "\"How so? Can it affect them?\" \"My dear Mr. Bennet,\" replied his wife, \"how can you " +
                    "be so tiresome! You must know that I am thinking of his marrying one of them.\""),
                new Page(3,
                    "\"Is that his design in settling here?\" \"Design! Nonsense, how can you talk so! But it " +
                    "is very likely that he may fall in love with one of them, and therefore you must visit him " +
                    "as soon as he comes.\" \"I see no occasion for that. You and the girls may go, or you may " +
                    "send them by themselves, which perhaps will be still better, for as you are as handsome as " +
                    "any of them, Mr. Bingley may like you the best of the party.\" \"My dear, you flatter me. " +
                    "I certainly have had my share of beauty, but I do not pretend to be any thing extraordinary " +
                    "now. When a woman has five grown-up daughters, she ought to give over thinking of her own " +
                    "beauty.\" \"In such cases, a woman has not often much beauty to think of.\" " +
                    "\"But, my dear, you must indeed go and see Mr. Bingley when he comes into the neighbourhood.\""),
                new Page(4,
                    "\"It is more than I engage for, I assure you.\" \"But consider your daughters. Only think " +
                    "what an establishment it would be for one of them. Sir William and Lady Lucas are determined " +
                    "to go, merely on that account, for in general, you know, they visit no new comers. Indeed " +
                    "you must go, for it will be impossible for us to visit him if you do not.\" \"You are over " +
                    "scrupulous, surely. I dare say Mr. Bingley will be very glad to see you; and I will send a " +
                    "few lines by you to assure him of my hearty consent to his marrying whichever he chooses of " +
                    "our girls; though I must throw in a good word for my little Lizzy.\" \"I desire you will do " +
                    "no such thing. Lizzy is not a bit better than the others; and I am sure she is not half so " +
                    "handsome as Jane, nor half so good-humoured as Lydia.\""),
                new Page(5,
                    "\"But you forget, mamma,\" said Elizabeth, \"that we shall meet him at the assemblies, " +
                    "and that Mrs. Long has promised to introduce him.\" \"I do not believe Mrs. Long will do " +
                    "any such thing. She has two nieces of her own. She is a selfish, hypocritical woman, and " +
                    "I have no opinion of her.\" \"No more have I,\" said Mr. Bennet; \"and I am glad to find " +
                    "that you do not depend on her serving you.\" Mrs. Bennet deigned not to make any reply, " +
                    "but, unable to contain herself, began scolding one of her daughters. \"Stop your coughing " +
                    "so, Kitty, for Heaven's sake! Have a little compassion on my nerves. You tear them to " +
                    "pieces.\" \"Kitty has no discretion in her coughs,\" said her father; \"she times them ill.\" " +
                    "\"I do not cough for my own amusement,\" replied Kitty fretfully.")
            )
        );
    }
}
