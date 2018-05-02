console.log("-----------------------------------------------");
console.log("Start of prototypal inheritence");
console.log("-----------------------------------------------");

//Prototypal inheritence
function Person(name = "N/A", age = 0){
    this.name = name;
    this.age = age;
}

Person.prototype.sayHello = function(){
    console.log(`${this.name}, age ${this.age}, says hello!`);
}

var person1 = new Person("Eddy", 25);
var person2 = new Person();
person1.sayHello();
person2.sayHello();
//console.log(person1);

person1.sayHelloAgain = function(){
    console.log(`${this.name}, age ${this.age}, says hello again!`);
}

person1.sayHelloAgain();
console.log(person1);
console.log(person2);
console.log(person3);
//Person2.sayHelloAgain();