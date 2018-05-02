myHoistedFunction();
console.log("Hello World");

// Scope test
var global = "Globally scoped variable";

function print(){
    var local = "Locally scoped variable";
    console.log(global);
    console.log(local);
}

print();
//console.log(local);

let block = "Outside the block";
function printblock(){
    let block = "Inside the block";
    console.log(block);
}

printblock();
console.log(block);

const PI = 3.1415;
//PI = 15;

console.log(PI);

//Anon function
/* Alerts are bad practice */
// let sayHello = function(name) {
//     alert("Hello, " + name + '!');
// }

//sayHello("Eddy");
//sayHello("Anonymous Function");

//Self-Invoking functions
//AKA IIFE's which are immediately invoked function expressions
(function(){
    console.log("This function was invoked without my explicit call");
})()

//Template literals
//Note that these are not single quotes, they are backticks
// (function(a,b){
//     console.log(`The self-invoking additional function says that ${a} + ${b} = ${a+b} `);
// })(10,20);

//Function hoisting
function myHoistedFunction(){
    console.log("This function was declared on line 52");
}

//Callback functions - a bit of a weak example, but we haven't learned about events yet
//Once we do, it'l be a lot easier to show a better example
function sayHello(name){
    alert("Hello, " + name + "!");
}

function sayGoodbye(name){
    alert("Goodbye, " + name + "!");
}

function getName(callback){
    let name = prompt("What is your name?");
    //Defined this function right above the getName function
    callback(name);
}

//getName(sayHello);
//getName(sayGoodbye);

//Objects
//Example 1: Object literal notation
let p1 = {
    name: "Charmander",
    type: "Fire"
}

console.log("Charmander is typeof " + (typeof p1));
console.log(p1);

//Example 2: New Keyword
let p2 = new Object();
p2.name = "Squirtle";
p2.type = "Water";

console.log("Squirtle is typeof " + (typeof p2));
console.log(p2);

//Example 3: Using a constructor

function pokemon(name, type){
    this.name = name;
    this.type = type;
}

let p3 = new pokemon("Bulbasaur", "Grass/Poison");
console.log("Bulbasaur is of typeof " + (typeof p3));
console.log(p3);

//There are 2 ways you can access properties of an object
//The dot notation, which we know
console.log("p3.name = " + p3.name);
//There is also the square bracket notation
console.log('p3["name"] = ' + p3["name"]);
console.log("Are they equal? " + (p3.name === p3["name"]));

