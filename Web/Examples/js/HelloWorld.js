console.log("Hello world");

myHoistedFunction();

// Scope test
var global = "Globally scoped variable";

(function print() {
    var local = "Locally scoped variable";
    console.log(global);
    console.log(local);
})();

let block = "outside the block";

function printBlock() {
    let block = "inside the block";
    console.log(block);
}
printBlock();
console.log(block);

const pi = 3.1415927;
//pi  = 13;
console.log(pi);

// Anonymous function
// Alerts are bad practice
let sayHello = function(name) {
    alert("Run, " + name + ", before it's too late!");
};

//sayHello("Curtis");

// Self-invoking functions
// Also known as IIFE's (Immediately Invoked Function Expressions)
(function(){console.log("IT WENT ON ITS OWN TERMS")}());

(function(a,b){
    console.log(`The self invoking function says ${a} + ${b} = ${a + b}`)}(3,4));

//Function Hoisting
function myHoistedFunction() {
    console.log("This function was declared on line 41");
}

//Callback functions - little bit of a weak example tbh, since we haven't learned about events yet.
function jiii(name) {
    alert(`*stares at ${name}*`);
};

function sss(name) {
    alert(`*hisses at ${name}*`);
};

function getName(callback) {
    let name = prompt("WHO ARE YOU?");
    callback(name);
};

// getName(jiii);
// getName(sss);

//Objects
//Example 1: Object literal notation
let p1 = {name:"Eriri", type:"tsundere"}
console.log("Eriri is typeof " + typeof p1);
console.log(p1);

//Example 2: with the new keyword
let p2 = new Object();
p2.name = "Utaha";
p2.type = "tired";

console.log("Utaha is typeof " + typeof p2);
console.log(p2);

// Example 3: using a "constructor"

function Saekano(name, type) {
    this.name = name;
    this.type = type;
};

let p3 = new Saekano("Megumi", "good");
console.log("Megumi is typeof " + typeof p3);
console.log(p3);

// There are two ways you can access properties of an object
// The dot notation, which we are all familiar with
console.log("p3.name = " + p3.name);

// Also, there is the square bracket notation
console.log('p3["name"] = ' + p3["name"]);

console.log("Are they equal? " + (p3.name === p3["name"]));