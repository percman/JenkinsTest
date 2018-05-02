// Why do we even need the arrow function? 
// It does not create its own "this" value,
// it takes the "this" from its enclosing context

function Person() {
    this.age = 0;

    // Self invoking function to illustrate how it was before ES6

    (function () {
        for (let i = 0; i < 50; i++) {
            this.age++;
            console.log(this.age);
        }
    })();
}

// let p = new Person();

function betterPerson(){
    this.age = 0;

    // Self invoking function to illustrate how it was before ES6

    (() => {
        for (let i = 0; i < 50; i+=5) {
            this.age+=5;
            console.log(this.age);
        }
    })();
}

// let bp = betterPerson();


//

let add = (a, b) => a + b;
let greet = name => {
    alert("Hello, " + name + "!");
};

// console.log(add(2, 3)); 
// greet("World");