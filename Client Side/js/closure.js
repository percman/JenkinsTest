// console.log(name);

function outer() {
    let a = 3;
    function inner() {
        let b = 4;
        function evenMoreInner() {
            let c = 5;
            console.log(a + b + c);
        }
        evenMoreInner();
        console.log(c);
    }
    inner();
    console.log(b);
}

// var name = "William";
// let name = "William";

var Person = function(name, age) {
    // Constructor Logic
    this.name = name;
    this.age = age;

    // We can create this closure to simulate encapsulation
    return {
        // We use the arrow function to ensure that the "this"
        // Refers to the Person object
        getName: () => {
            return this.name;
        },
        setName: (name) => {
            this.name = name;
        },
        getAge: () => {
            return this.age;
        },
        setAge: (age) => {
            this.age = age;
        },
        info: () => {
            console.log(`Name: ${this.name}\nAge: ${this.age}`);
        }
    }
}

// let William = new Person("William", 25);
// console.log(William.getName());
// console.log(William.getAge());
// William.setName("Bill");
// William.setAge(65);
// William.info();
