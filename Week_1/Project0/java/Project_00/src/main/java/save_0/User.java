package save_0;

public class User {
    //private static final long serialVersionUID = 5058997999481525938L;
    private final int balance;
    private final String name;
    private final String username;
    private final String password;

    private User(UserBuilder userBuilder) {
        this.balance = userBuilder.balance;
        this.name = userBuilder.name;
        this.username = userBuilder.username;
        this.password = userBuilder.password;
    }

    User(){
        balance = 0;
        name = null;
        password = null;
        username = null;
    }

    public static class UserBuilder {
        private int balance = 0;
        private String name = null;
        private String password = null;
        private String username = null;


        public UserBuilder balance(int balance) {
            this.balance = balance;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "balance=" + balance +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
