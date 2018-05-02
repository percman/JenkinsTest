console.log("-------------------------------------");
console.log("Start of prototypal inheritance");
console.log("-------------------------------------");

// Prototypal Inheritance 
function Person(name = "N/A", age = 0) {
    this.name = name;
    this.age = age;
}

Person.prototype.sayHello = function() {
    console.log(`${this.name}, age ${this.age}, says hello! (From the prototype)`);
};

var person1 = new Person("William", 25);
var person2 = new Person();
console.log(person1);


person1.sayHello = function() {
    console.log(`${this.name}, age ${this.age}, says hello again! (From the instance)`);
};


person1.sayHello();
person2.sayHello();
// person1.onlyAvailableOnPersonOne();
console.log(person1);
console.log(person2);
// person2.sayHelloAgain();

console.log(person1.prototype === person2.prototype);