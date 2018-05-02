console.log("----------------------------------");
console.log("Start of a prototypal inheritance");
console.log("----------------------------------")

// Prototypal Inheritance
function Person(name = "N/A", age = 0){
    this.name = name;
    this.age = age;
};


Person.prototype.sayHello = function() {
    console.log(`${this.name}, age ${this.age}, says hello!`);
};

var p5 = new Person("Moon moon", 3);
var p6 = new Person();

console.log(p5);

p5.sayHello();
p6.sayHello();

p5.__proto__.sayHelloAgain = function(){
    console.log(`${this.name}, age ${this.age}, says hello again!`);
};

p5.sayHelloAgain();

console.log(p5);
console.log(p6);
// console.log(p7); // UNDECLARED