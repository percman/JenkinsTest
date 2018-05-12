myHoistedFunction();
console.log("Hello world");

// Scope test
var global = "Globally scoped variable";

function print() {
    var local = "Locally scoped variable";
    console.log(global);
    console.log(local);
}

print();
// console.log(local);

let block = "Outside the block";
function printBlock() {
    let block = "Inside the block";
    console.log(block);
}

printBlock();
console.log(block);

const PI = 3.14;
// PI = 15;
console.log(PI);

// Anonymous function
/* Alert's are bad practice */
// let sayHello = function(name) {
//     alert("Hello, " + name + '!');
// }

// sayHello("William");
// sayHello("Anonymous Function");

// Self-Invoking Functions
// Also known as IIFE's (Immediately Invoked Function Expressions)
(function() {
    console.log("This function was invoked without my explicit call");
})();


// Template Literals
// NOTE: THESE ARE NOT SINGLE QUOTES ''
// They are backticks, located below the esc key ``
(function(a, b) {
    console.log(`The self-invoking addition function says that ${a} + ${b} = ${a + b}`);
})(10, 20);


// Function Hoisting
function myHoistedFunction() {
    console.log("This function was declared on line 53");
}


// Callback functions - little bit of weak example... but that's because we've yet
// to learn about events. There, they are much more useful
function sayHello(name) {
    alert("Hello " + name + "!");
};

function sayGoodbye(name) {
    alert("Goodbye" + name + "!");
};

function getName(callback) {
    let name = prompt("What is your name?");
    // Defined this function right above the getName function
    callback(name);
};

// getName(sayHello);
// getName(sayGoodbye);

// Objects
// Example 1: Object literal notation
let p1 = {
    name: "Charmander",
    type: "Fire"
};

console.log("Charmander is typeof " + (typeof p1));
console.log(p1);

// Example 2: With the new keyword
let p2 = new Object();
p2.name = "Squirtle";
p2.type = "Water";

console.log("Squirtle is typeof " + (typeof p2));
console.log(p2);

// Example 3: Using a "constructor"
function Pokemon(name, type) {
    this.name = name;
    this.type = type;
}

let p3 = new Pokemon("Bulbasaur", "Grass/Poison");
console.log("Bulbasaur is typeof " + (typeof p3));
console.log(p3);

// There are two ways you can access properties of an object
// The dot notation, which we are all familiar with
console.log("p3.name = " + p3.name);

// Also, there is the square bracket notation
console.log('p3["name"] = ' + p3["name"]);

console.log("Are they equal? " + (p3.name === p3["name"]));