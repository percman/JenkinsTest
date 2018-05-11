//a bunch of dashes

(function(url) {
    // Create a new `Image` instance
    var image = new Image();
  
    image.onload = function() {
      // Inside here we already have the dimensions of the loaded image
      var style = [
        // Hacky way of forcing image's viewport using `font-size` and `line-height`
        'font-size: 1px;',
        'line-height: ' + this.height + 'px;',
  
        // Hacky way of forcing a middle/center anchor point for the image
        'padding: ' + this.height * .5 + 'px ' + this.width * .5 + 'px;',
  
        // Set image dimensions
        'background-size: ' + this.width + 'px ' + this.height + 'px;',
  
        // Set image URL
        'background: url('+ url +');'
       ].join(' ');
  
       console.log('%c', style);
    };
  
    // Actually loads the image
    image.src = url;
  })('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6CS2w3Twz9Ct67_rGELVO1Oj5u28ttxGIERse6UQI7sZxGRE-DQ');
(function(url) {
    // Create a new `Image` instance
    var image = new Image();
  
    image.onload = function() {
      // Inside here we already have the dimensions of the loaded image
      var style = [
        // Hacky way of forcing image's viewport using `font-size` and `line-height`
        'font-size: 1px;',
        'line-height: ' + this.height + 'px;',
  
        // Hacky way of forcing a middle/center anchor point for the image
        'padding: ' + this.height * .5 + 'px ' + this.width * .5 + 'px;',
  
        // Set image dimensions
        'background-size: ' + this.width + 'px ' + this.height + 'px;',
  
        // Set image URL
        'background: url('+ url +');'
       ].join(' ');
  
       console.log('%c', style);
    };
  
    // Actually loads the image
    image.src = url;
  })('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6CS2w3Twz9Ct67_rGELVO1Oj5u28ttxGIERse6UQI7sZxGRE-DQ');
(function(url) {
    // Create a new `Image` instance
    var image = new Image();
  
    image.onload = function() {
      // Inside here we already have the dimensions of the loaded image
      var style = [
        // Hacky way of forcing image's viewport using `font-size` and `line-height`
        'font-size: 1px;',
        'line-height: ' + this.height + 'px;',
  
        // Hacky way of forcing a middle/center anchor point for the image
        'padding: ' + this.height * .5 + 'px ' + this.width * .5 + 'px;',
  
        // Set image dimensions
        'background-size: ' + this.width + 'px ' + this.height + 'px;',
  
        // Set image URL
        'background: url('+ url +');'
       ].join(' ');
  
       console.log('%c', style);
    };
  
    // Actually loads the image
    image.src = url;
  })('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6CS2w3Twz9Ct67_rGELVO1Oj5u28ttxGIERse6UQI7sZxGRE-DQ');

console.log("Start of prototypal inheritance");
function Person(name="N/A", age=-1) {
    this.name = name;
    this.age = age;
}

Person.prototype.sayHello = function() {
    console.log(`${this.name}, age ${this.age}, says hello!`)
};

var person1 = new Person("Iroha", 20);
var person2 = new Person();
person1.sayHello();
person2.sayHello();
console.log(person1);
console.log(person2);

person1.__proto__.sayHelloAgain = function() {
    console.log(`${this.name}, age ${this.age}, says hello again!`)
};

person1.sayHelloAgain();

person2.sayHelloAgain();

// console.log(person3);